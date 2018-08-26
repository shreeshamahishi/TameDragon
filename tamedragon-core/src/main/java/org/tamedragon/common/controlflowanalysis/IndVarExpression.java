package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.controlflowanalysis.ExpressionElement.ElementType;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents an expression for the factor and increment of
 * an induction variable of the form i = f * bv + diff where
 * i denotes a dependent induction variable,
 * bv denotes the basic induction variable on which i is dependent upon, 
 * f denotes the factor by which bv is multiplied by and 
 * diff denotes the increment.
 * 
 * The factor f and diff can be a compile-time constant or any arbitrary
 * constant unknown at compile time.
 *
 */
public class IndVarExpression {

	private List<ExpressionElement> expression;
	private IndVarExpression exprOfBaseIndVar;
	private Value newValue;

	private LLVMIREmitter emitter;
	
	private static final Logger LOG = LoggerFactory.getLogger(IndVarExpression.class);
	private static final String INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION = 
					"Incompatible operand or operator for an induction variable";

	public IndVarExpression(Value value, IndVarExpression exprOfBaseIndVar,
			BinaryOperatorID binOp, boolean isPostFix){

		if(!ensureCompatibility(exprOfBaseIndVar, value, binOp)){
			assert false : INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION;
			LOG.error(INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION);
		}		

		emitter = LLVMIREmitter.getInstance();

		newValue = value;
		this.exprOfBaseIndVar = exprOfBaseIndVar;
		createExpression(binOp, isPostFix);
	}

	private boolean ensureCompatibility(IndVarExpression exprOfBaseIndVar,
			Value newValue, BinaryOperatorID binOp) {

		if(newValue == null) // Must be base induction variable
			return true;

		if(exprOfBaseIndVar == null || exprOfBaseIndVar.getExpression().size() == 0)
			return true;

		List<ExpressionElement> baseIndVarExpressions =
			exprOfBaseIndVar.getExpression();

		ExpressionElement firstElement = baseIndVarExpressions.get(0);

		Value val = firstElement.getOperand();

		if(val.getType() != newValue.getType())
			return false;

		if(val.getType().isIntegerType() && !(binOp == BinaryOperatorID.ADD ||
				binOp == BinaryOperatorID.SUB ||
				binOp == BinaryOperatorID.MUL))
			return false;

		if(!val.getType().isIntegerType() && !(binOp == BinaryOperatorID.FADD ||
				binOp == BinaryOperatorID.FSUB ||
				binOp == BinaryOperatorID.FMUL))
			return false;

		return true;
	}

	private void createExpression(BinaryOperatorID binOp,
			boolean isPostFix) {

		try{
			expression = new ArrayList<ExpressionElement>();

			if(exprOfBaseIndVar == null){
				// Must be a basic induction variable
				ExpressionElement base = new ExpressionElement(newValue);
				expression.add(base);
				return; 
			}

			List<ExpressionElement> baseIndVarExpressions =
				exprOfBaseIndVar.getExpression();

			for(ExpressionElement exprElement : baseIndVarExpressions){
				expression.add(exprElement);
			}

			if(!(newValue instanceof Constant)){
				updateExpressionWithNonConstantLiteral(binOp, newValue, isPostFix);
			}
			else{

				// The new value is a constant; fold it if there are constants in the
				// expression element list of the base induction variable
				updateExpressionWithConstantLiteral(binOp, newValue, isPostFix);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private void updateExpressionWithConstantLiteral(BinaryOperatorID binOp,
			Value newValue, boolean isPostFix) throws Exception{

		Constant newConstValue = (Constant) newValue;
		boolean folded = false;

		List<Integer> additionIndices = getAdditionIndicesForIndVar();
		List<List<ExpressionElement>> subExpressions = 
			getSubExpressionsForIndVar(additionIndices);

		int currentIndex = 0;   // Index on the additionIndices list
		int subExprFoldedAt = -1;
		if(!(binOp == BinaryOperatorID.MUL || binOp == BinaryOperatorID.FMUL)){
			// Try and fold if there is a constant in the existing base
			// expression
			for(List<ExpressionElement> subExpr : subExpressions){
				if(subExpr.size() > 1){
					currentIndex++;
					continue;   // Cannot fold with this subression
				}

				ExpressionElement exprElement = subExpr.get(0);
				Value operandValue = exprElement.getOperand();
				if(!(operandValue instanceof Constant)){
					currentIndex++;
					continue;
				}

				// Fold it!
				Constant operandConst = (Constant) operandValue;
				Constant foldedConstant =  null;
				int index = -1;
				if(currentIndex == 0){
					// The first sub-expression should be folded
					if(isPostFix)
						foldedConstant = Constant.getConstant(binOp, operandConst, newConstValue);
					else
						foldedConstant = Constant.getConstant(binOp, newConstValue, operandConst);
					index = -1;
				}
				else{
					index = additionIndices.get(currentIndex -1);
					BinaryOperatorID operatorPrefix = 
						expression.get(index).getBinOpId();
					foldedConstant = foldConstants(newConstValue, operandConst, binOp, 
							operatorPrefix, isPostFix);
					if(!isPostFix && (binOp == BinaryOperatorID.FSUB 
							|| binOp == BinaryOperatorID.SUB)){
						if(foldedConstant.isNegative()){
							BinaryOperatorID sign = (binOp == BinaryOperatorID.SUB ) ? 
									BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
							expression.set(index, new ExpressionElement(sign));
						}
						else{
							BinaryOperatorID sign = (binOp == BinaryOperatorID.SUB ) ? 
									BinaryOperatorID.ADD : BinaryOperatorID.FADD;
							expression.set(index, new ExpressionElement(sign));
						}
					}
				}

				expression.set(index+1, new ExpressionElement(foldedConstant));
				subExprFoldedAt = index +1;

				folded = true;
				break;
			}


			if(!folded){
				// Add new operand at appropriate
				addToExpression(new ExpressionElement(binOp), 
						new ExpressionElement(newConstValue), isPostFix);
			}
			else{
				// If this is a prefix and a subtraction, reverse the sign (except
				// of the sub-expression that has been folded)
				if(!isPostFix && (binOp == BinaryOperatorID.FSUB 
						|| binOp == BinaryOperatorID.SUB)){
					reverseSignForPrefixSubtraction(subExprFoldedAt);
				}
			}

			return;
		}

		// Multiply with each element in the expression

		if(additionIndices.size() == 0){
			if(isPostFix){
				// No increments; might be a the first operand
				expression.add(new ExpressionElement(binOp));
				expression.add(new ExpressionElement(newValue));
			}
			else{
				expression.add(0, new ExpressionElement(binOp));
				expression.add(0, new ExpressionElement(newValue));
			}

			return;
		}

		int count = 0;
		int startIndex = 0;
		int numAddedElements = 0;
		for(List<ExpressionElement> subExpr : subExpressions){
			folded = 
				foldMultiplicativeOrAppendToSubExpression(subExpr, startIndex,
						newValue, isPostFix);
			count++;
			if(count == subExpressions.size())
				break;
			if(!folded){
				numAddedElements += 2;
			}

			// Update the start index for the next iteration. 
			startIndex = additionIndices.get(count -1) + 
			numAddedElements + 1;
		}
	}

	private void reverseSignForPrefixSubtraction(int subExprFoldedAt) {
		try{

			List<Integer> additionIndices = getAdditionIndicesForIndVar();
			List<List<ExpressionElement>> subExpressions = 
				getSubExpressionsForIndVar(additionIndices);

			if(subExprFoldedAt == 0){
				for(Integer index : additionIndices){
					ExpressionElement eeAtIndx = expression.get(index);
					BinaryOperatorID binOpIdAtIndex = eeAtIndx.getBinOpId();
					eeAtIndx.setBinOpID(getOppositeSign(binOpIdAtIndex));
				}

				return;
			}

			// If the first sub-expression, has some constant, reverse its sign
			List<ExpressionElement> firstSubExpr = subExpressions.get(0);

			boolean changed = false;
			for(ExpressionElement expr : firstSubExpr){
				if(expr.getType() != ElementType.OPERAND)
					continue;

				Value exprValue = expr.getOperand();
				if(!(exprValue instanceof Constant))
					continue;

				Constant exprConst = (Constant) exprValue;
				Constant negatedConst = Constant.getConstWithOppositeSign(exprConst);
				changed = true;
				replaceExpressionWith(expr, negatedConst);
			}

			boolean isIntegerOperation = true;
			if(!newValue.getType().isIntegerType())
				isIntegerOperation = false;

			if(!changed){
				// We will need to multiply with -1
				Value refValue = expression.get(0).getOperand();
				expression.set(0, isIntegerOperation? 
						new ExpressionElement(BinaryOperatorID.MUL) :
							new ExpressionElement(BinaryOperatorID.FMUL));
				expression.set(0, new ExpressionElement(Constant.getNegativeUnityConstant(refValue)));
				if(subExprFoldedAt > -1)
					subExprFoldedAt += 2;
			}

			// Get the updated addition indices and the negate each one except
			// the one that already got folded
			additionIndices = getAdditionIndicesForIndVar();
			for(Integer index : additionIndices){
				if(index == subExprFoldedAt -1)
					continue;

				ExpressionElement eeAtIndx = expression.get(index);
				BinaryOperatorID binOpIdAtIndex = eeAtIndx.getBinOpId();
				eeAtIndx.setBinOpID(getOppositeSign(binOpIdAtIndex));
			}

		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void replaceExpressionWith(ExpressionElement expr, Constant newConst){
		int index = -1;

		int count = 0;
		for(ExpressionElement e : expression){
			if(expr == e){
				index = count;
				break;
			}
			count++;
		}

		expression.set(index, new ExpressionElement(newConst));
	}

	/**
	 * Returns the indexes at which the operator is either a "+" or a "-".
	 * Note : in the future, we might support more non-multiplicative operators.
	 */

	public List<Integer> getAdditionIndicesForIndVar() {
		return IndVarExpression.getAdditionIndices(expression);
	}

	public List<List<ExpressionElement>> getSubExpressionsForIndVar(
			List<Integer> additionIndices){
		return IndVarExpression.getSubExpressions(additionIndices, expression);
	}

	private boolean foldMultiplicativeOrAppendToSubExpression(
			List<ExpressionElement> subOperand, int start,
			Value newValue, boolean isPostfix) {

		boolean foldedSubExpr = false;

		boolean isIntegerOperation = true;
		if(!newValue.getType().isIntegerType())
			isIntegerOperation = false;

		try{
			// If there is an constant in the multiplicative expression, fold it

			for(ExpressionElement exprElement : subOperand){
				if(exprElement.getType() == ElementType.OPERATOR)
					continue;

				Value value = exprElement.getOperand();
				if(value instanceof Constant){
					// Fold it!
					Constant operandConst = (Constant) value;
					int operandIndex = expression.indexOf(value);
					if(operandIndex < 0){
						// Maybe this constant already got folded and the new folded
						// constant is sitting in the updated expression; try and the
						// constant with that same value
						int count = 0;
						for(ExpressionElement ee : expression){
							if(ee.getType() == ElementType.OPERAND && 
									ee.getOperand() instanceof Constant && 
									((Constant)ee.getOperand()).equals(value)){
								operandIndex = count;
								break;
							}
							count++;
						}
					}

					Constant foldedConstant = Constant.getConstant(
							isIntegerOperation? BinaryOperatorID.MUL :
								BinaryOperatorID.FMUL,
								(Constant)newValue, operandConst);

					expression.set(operandIndex, new ExpressionElement(foldedConstant));
					foldedSubExpr = true;
				}
			}

			if(!foldedSubExpr){
				// Not folded; append the operator and the operand to the end
				// or at the beginning of the list
				if(isPostfix){
					int insertionPoint = start + subOperand.size();
					int currentExpressionSize = expression.size();

					if(insertionPoint >= currentExpressionSize){
						// Add at the end
						expression.add(isIntegerOperation?
								new ExpressionElement(BinaryOperatorID.MUL):
									new ExpressionElement(BinaryOperatorID.FMUL));
						expression.add(new ExpressionElement(newValue));
					}
					else{
						expression.add(insertionPoint, isIntegerOperation?
								new ExpressionElement(BinaryOperatorID.MUL):
									new ExpressionElement(BinaryOperatorID.FMUL));
						expression.add(insertionPoint + 1, new ExpressionElement(newValue));
					}
				}
				else{
					expression.add(start, isIntegerOperation?
							new ExpressionElement(BinaryOperatorID.MUL):
								new ExpressionElement(BinaryOperatorID.FMUL));
					expression.add(start, new ExpressionElement(newValue));
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

		return foldedSubExpr;
	}

	private void updateExpressionWithNonConstantLiteral(BinaryOperatorID binOp,
			Value newValue, boolean isPostFix) {

		// Not a compile time constant; add to expression.

		boolean isIntegerOperation = true;
		if(!newValue.getType().isIntegerType())
			isIntegerOperation = false;

		if(!(binOp == BinaryOperatorID.MUL || binOp == BinaryOperatorID.FMUL)){
			// Just add to the expression, since this is an addition or
			// subtraction expression
			ExpressionElement operatorElement = new ExpressionElement(binOp);
			ExpressionElement operandElement = new ExpressionElement(newValue);
			addToExpression(operatorElement, operandElement, isPostFix);

			return;
		}

		// Multiply with each element in the expression
		List<Integer> additionIndices = getAdditionIndicesForIndVar();
		if(additionIndices.size() == 0){
			if(isPostFix){
				// No increments; might be a the first operand
				expression.add(new ExpressionElement(binOp));
				expression.add(new ExpressionElement(newValue));
			}
			else{
				expression.add(0, new ExpressionElement(newValue));
				expression.add(0, new ExpressionElement(binOp));
			}

			return;
		}

		int count = 0;
		int i = 0;
		ExpressionElement operatorElement = isIntegerOperation?
				new ExpressionElement(BinaryOperatorID.MUL) :
					new ExpressionElement(BinaryOperatorID.FMUL);
				ExpressionElement operandElement = new ExpressionElement(newValue);
				for(Integer index : additionIndices){
					if(isPostFix){
						expression.add(count + index, operatorElement);
						expression.add(count + index + 1, operandElement);

						if(i == additionIndices.size() -1){
							expression.add(operatorElement);
							expression.add(operandElement);
						}
					}
					else{
						if(count == 0){
							// Prefix, add at the beginning for the the first element
							expression.add(0, operatorElement);
							expression.add(0, operandElement);
							count += 2;
						}

						expression.add(count + index+1, operatorElement);
						expression.add(count + index+1, operandElement);
					}

					count += 2; i++;

				}
	}

	private void addToExpression(ExpressionElement operatorElement,
			ExpressionElement operandElement, boolean isPostFix) {
		if(isPostFix){
			expression.add(operatorElement);
			expression.add(operandElement);
		}
		else{
			BinaryOperatorID binOpID = operatorElement.getBinOpId();
			if(binOpID == BinaryOperatorID.SUB || binOpID == BinaryOperatorID.FSUB){
				// We will need to reverse the signs of the operator in the
				// expression

				reverseOperators();
			}

			expression.add(0, operatorElement);
			expression.add(0, operandElement);

		}
	}

	/**
	 * Reverses the signs of the '+' and the '-' operators in the expression.
	 */
	private void reverseOperators() {

		int elementCount = 0;
		for(ExpressionElement exprElement : expression){
			if(exprElement.getType() != ElementType.OPERATOR)
				continue;

			BinaryOperatorID binOpId = exprElement.getBinOpId();
			BinaryOperatorID replacement = getOppositeSign(binOpId);
			if(replacement != null){
				try{
					exprElement.setBinOpID(replacement);
				}
				catch(Exception e){
					e.printStackTrace();
					System.exit(-1);
				}
			}

			elementCount++;
		}
	}

	private BinaryOperatorID getOppositeSign(BinaryOperatorID binOpId){
		BinaryOperatorID replacement = null;
		if(binOpId == BinaryOperatorID.ADD || 
				binOpId == BinaryOperatorID.FADD || 
				binOpId == BinaryOperatorID.SUB || 
				binOpId == BinaryOperatorID.FSUB  ){
			if(binOpId == BinaryOperatorID.ADD)
				replacement = BinaryOperatorID.SUB;
			else if(binOpId == BinaryOperatorID.SUB)
				replacement = BinaryOperatorID.ADD;
			else if(binOpId == BinaryOperatorID.FADD)
				replacement = BinaryOperatorID.FSUB;
			else
				replacement = BinaryOperatorID.FADD;
		}

		return replacement;

	}

	private Constant foldConstants(Constant constValue, Constant constOperand,
			BinaryOperatorID binaryOperator, BinaryOperatorID lhsSign, boolean isPostFix) {

		Constant foldedConstant = null;

		try{
			if(binaryOperator == BinaryOperatorID.MUL || binaryOperator == BinaryOperatorID.FMUL){
				// Multiplication is commutative, so just fold and return
				foldedConstant = Constant.getConstant(binaryOperator,
						constValue, constOperand);
				return foldedConstant;
			}

			boolean isIntOperation = true;
			if(constValue instanceof ConstantFP)
				isIntOperation = false;

			BinaryOperatorID finalOperator = null;
			boolean negateResult = false;

			Constant lhsOperand = null, rhsOperand = null;

			if(isPostFix){
				lhsOperand = constOperand; rhsOperand = constValue;
				if(lhsSign == BinaryOperatorID.ADD || lhsSign == BinaryOperatorID.FADD){
					// LHS is positive
					if(binaryOperator == BinaryOperatorID.ADD || binaryOperator == BinaryOperatorID.FADD)
						finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
					else
						finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
				}
				else{
					// LHS is negative
					if(binaryOperator == BinaryOperatorID.ADD || binaryOperator == BinaryOperatorID.FADD){
						// The operand sign is positive  // -a + b; 
						finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;

						// Reverse the operands (b -a)
						lhsOperand = constValue; rhsOperand = constOperand;
					}
					else{
						// The operand sign in negative (-a - b) = -1 *(a + b)
						finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
						negateResult = true;
					}
				}
			}
			else{
				lhsOperand = constValue; rhsOperand = constOperand;
				if(binaryOperator == BinaryOperatorID.ADD || binaryOperator == BinaryOperatorID.FADD){
					if(lhsSign == BinaryOperatorID.ADD || lhsSign == BinaryOperatorID.FADD) // a + b
						finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
					else   // a + (-b)
						finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
				}
				else{
					if(lhsSign == BinaryOperatorID.ADD || lhsSign == BinaryOperatorID.FADD) // a - (+ b) : a -b
						finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
					else // a - (- b) : a +b
						finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
				}
			}

			foldedConstant = Constant.getConstant(finalOperator, lhsOperand, rhsOperand);
			if(negateResult){
				foldedConstant = Constant.getConstWithOppositeSign(foldedConstant);
			}
		}
		catch(Exception e){
			System.exit(-1);
		}

		return foldedConstant;
	}

	public List<ExpressionElement> getExpression() {
		return expression;
	}

	/**
	 * Evaluates the result of adding or subtracting a new value to an induction
	 * variable. For example, if an induction variable "iv" having the expressions:
	 *  
	 * (4*iv + 3j -34) + (34) = 4*iv + 3j
	 * (2*iv + 3j -34) + 1 = 2*iv + 3j -3
	 * 3j - (9*iv + 101 -3j) = -9*iv -101 +6j,
	 * etc.   
	 * @param indVar
	 * @param binOpId
	 * @param newValue
	 * @return
	 */
	/*public List<ExpressionElement> evaluate(BinaryOperatorID binOpId, Value newValue,
			boolean isPostfix){

		List<Integer> additionIndices = getAdditionIndices();
		List<List<ExpressionElement>> subExpressions = 
			getSubExpressions(additionIndices);

		int count = 0;
		for(List<ExpressionElement> subExpr : subExpressions){

			if(subExpr.size() != 0){
				count++;
				continue;
			}

			if(isPostfix){

			}


			count++;
		}


	}*/

	/**
	 * Returns the indexes at which the operator is either a "+" or a "-".
	 * Note : in the future, we might support more non-multiplicative operators.
	 */

	public static List<Integer> getAdditionIndices(List<ExpressionElement> expression) {
		List<Integer> indices = new ArrayList<Integer>();

		int count = 0;
		for(ExpressionElement exprElement : expression){
			if(exprElement.getType() == ElementType.OPERATOR
					&& (!(exprElement.getBinOpId() == BinaryOperatorID.MUL
							|| exprElement.getBinOpId() == BinaryOperatorID.FMUL)))
				indices.add(count);
			count++;
		}

		return indices;
	}

	public static List<List<ExpressionElement>> getSubExpressions(
			List<Integer> additionIndices, List<ExpressionElement> expression){
		List<List<ExpressionElement>> subExpressions = 
			new ArrayList<List<ExpressionElement>>();

		if(additionIndices.size() == 0){
			// No addition operations
			List<ExpressionElement> allElements = new ArrayList<ExpressionElement>();
			allElements.addAll(expression);
			subExpressions.add(allElements);

			return subExpressions;
		}

		int subExprCount = 0;
		int start = 0;
		for(Integer index : additionIndices){
			List<ExpressionElement> subExp = new ArrayList<ExpressionElement>();
			subExp.addAll(expression.subList(start, index));
			if(subExp.size() > 0){
				subExpressions.add(subExp);
			}
			start = index + 1;
			subExprCount++;
			if(subExprCount == additionIndices.size()){
				List<ExpressionElement> lastSubExp = new ArrayList<ExpressionElement>();
				lastSubExp.addAll(expression.subList(start, expression.size()));
				if(lastSubExp.size() > 0){
					subExpressions.add(lastSubExp);
				}
			}
		}

		return subExpressions;
	}


	public String toString(){
		String expr = "";
		if(exprOfBaseIndVar == null){
			expr = emitter.getValidName(newValue);
		}
		else{
			for(ExpressionElement element : expression){
				expr += element.toString();
			}
		}

		return expr;
	}

	public static int getFirstMatchingIndex(IndVarExpression ivExpression,
			List<ExpressionElement> referenceList) {
		int matchingIndex = -1;
		List<ExpressionElement> expression = ivExpression.getExpression();

		if(expression.size() > referenceList.size()){
			return matchingIndex;
		}

		ExpressionElement firstInRef = referenceList.get(0);
		List<Integer> matchingIndices = new ArrayList<Integer>();
		int count = 0;
		for(ExpressionElement elementInIV : expression){
			if(firstInRef.equals(elementInIV)){
				matchingIndices.add(count);
			}
			count++;
		}

		if(matchingIndices.size() == 0){
			return matchingIndex;
		}

		for(Integer firstMatchingIndex : matchingIndices){

			if(firstMatchingIndex + expression.size() > referenceList.size()){
				return matchingIndex;
			}

			count = 1;
			int matchCount = 1;
			for(int i = firstMatchingIndex +1; i < firstMatchingIndex + expression.size(); i++){
				ExpressionElement refElement = referenceList.get(i);
				ExpressionElement elementInIV = expression.get(count);

				if(elementInIV.equals(refElement)){
					matchCount++;
				}
				count++;
			}

			if(matchCount == expression.size()){
				return firstMatchingIndex;
			}
		}	
		
		return matchingIndex;
	}
}

