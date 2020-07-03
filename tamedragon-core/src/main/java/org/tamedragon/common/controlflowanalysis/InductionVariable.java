package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.controlflowanalysis.ExpressionElement.ElementType;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * The InductionVariable class defines an induction variable in the context
 * of an SSA variable. 
 *
 */
public class InductionVariable {

	private LLVMIREmitter emitter;

	// The instruction that defines the induction variable
	private Instruction definingInstruction;

	// The induction variable on which this induction variable
	// is based upon. For example, if this induction variable
	// is defined by j = i * a + b, baseIndVar represents i.
	private InductionVariable baseIndVar;

	// The constant that denotes the factor by which baseIndVar
	// is multiplied by
	private Value factor;

	// The difference (or increment) by which this induction
	// variable is added.
	private Value difference;

	// The expression that defines this induction variable
	private IndVarExpression expression;

	// The operator (multiplication, subtraction or addition)
	private BinaryOperatorID binOpId;

	// The flag to indicate whether the operator occurs precedes or appears
	// after the base induction variable
	private boolean postFix;

	// The fundamental induction variable of which this induction variable
	// belongs to the class of
	private InductionVariable fundamentalIndVar;

	// The factor in the canonical form: the b in the form (j -> b * i + c)
	private List<Value> factorInCanonicalForm;

	// The difference in the canonical form: the c in the form (j -> b * i + c)
	private HashMap<List<Value>, BinaryOperatorID> differenceInCanonicalForm;

	// The loop that contains this induction variable
	private LoopInfo containingLoop;

	// The instruction that defines the increment of the basic induction
	// variable. Will be null for non-fundamental induction variables.
	private BinaryOperator incrementor;

	private static final Logger LOG = LoggerFactory.getLogger(InductionVariable.class);
	
	private static final String INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION = 
					"Incompatible operand or operator for an induction variable";
	
	public InductionVariable(Instruction defInstruction, InductionVariable
			baseIndVar, Value factor, Value difference, boolean isPost, 
			BinaryOperatorID operator, LoopInfo containingLoop ){

		emitter = LLVMIREmitter.getInstance();

		this.definingInstruction = defInstruction;
		this.baseIndVar = baseIndVar;
		this.factor = factor;
		this.difference = difference;
		this.binOpId = operator;
		this.postFix = isPost;
		this.containingLoop = containingLoop;

		try{
			setExpression();
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

		// Set the fundamental induction variable for this variable.
		setFundamentalIndVar();

		// Create the final canonical form (j -> b * i + c)
		updateCanonicalForm();
	}

	private void setExpression() throws Exception {
		if(baseIndVar == null){
			// This is a fundamental induction variable
			expression = new IndVarExpression(definingInstruction, null, null, true);
			return;
		}

		Value incrOrFactor = difference;
		if(difference == null)
			incrOrFactor = factor;

		expression = new IndVarExpression(incrOrFactor, 
				baseIndVar.getExpression(), binOpId, postFix);
	}

	public Instruction getDefiningInstruction() {
		return definingInstruction;
	}

	public void setDefiningInstruction(Instruction definingInstruction) {
		this.definingInstruction = definingInstruction;
	}

	public InductionVariable getBaseIndVar() {
		return baseIndVar;
	}

	public void setBaseIndVar(InductionVariable baseIndVar) {
		this.baseIndVar = baseIndVar;
	}

	public Value getFactor() {
		return factor;
	}

	public void setFactor(Constant factor) {
		this.factor = factor;
	}

	public Value getDifference() {
		return difference;
	}

	public void setDifference(Constant difference) {
		this.difference = difference;
	}

	public boolean isBasicInductionVariable(){
		if(baseIndVar == null)
			return true;

		return false;
	}

	public boolean isDependentInductionVariable(){
		return !isBasicInductionVariable();
	}

	/**
	 * Returns true if this induction variable has a basic induction
	 * variable as it's base.
	 * @return
	 */
	public boolean hasBasicIndVarAsBase(){
		if(baseIndVar == null)
			return false;

		if(baseIndVar.isBasicInductionVariable())
			return true;

		return false;
	}

	public IndVarExpression getExpression() {
		return expression;
	}

	public String toString(){
		String desc = "{";
		desc += "value: " + emitter.getValidName(definingInstruction);
		desc += ", base: " + (baseIndVar == null? "this" :  
			emitter.getValidName(baseIndVar.getDefiningInstruction()));
		desc += ", factor: " + (factor == null ? "none" : emitter.getValidName(factor));
		desc += ", diff: " + (difference == null ? "none" : emitter.getValidName(difference));
		desc += "}";
		return desc;
	}

	public BinaryOperatorID getBinOpId() {
		return binOpId;
	}

	public boolean isPostFix() {
		return postFix;
	}

	public InductionVariable getFundamentalIndVar() {
		return fundamentalIndVar;
	}

	private void setFundamentalIndVar() {
		if(baseIndVar == null){
			fundamentalIndVar = this;
		}

		InductionVariable current = baseIndVar;
		InductionVariable temp = baseIndVar;
		while(temp != null){
			current = temp;
			temp = temp.getBaseIndVar();
			if(temp == null){
				fundamentalIndVar = current;
				break;
			}
		}
	}

	private void updateCanonicalForm(){

		if(emitter.getValidName(definingInstruction).equals("%8")){
			LOG.debug("WAIT HTERE");
		}

		factorInCanonicalForm = new ArrayList<Value>();
		differenceInCanonicalForm = new HashMap<List<Value>, BinaryOperatorID> ();

		List<Integer> additionIndices = expression.getAdditionIndicesForIndVar();
		List<List<ExpressionElement>> subExpressions = 
			expression.getSubExpressionsForIndVar(additionIndices);

		int count = 0;
		boolean factorOperatorPrefixIsSubtraction = false;

		if(subExpressions.size() == 0){
			// All expression elements are part of the factor
			List<ExpressionElement> elements = expression.getExpression();
			for(ExpressionElement element : elements){
				if(element.getType() == ElementType.OPERAND){
					Value value = element.getOperand();
					if(value != fundamentalIndVar.getDefiningInstruction()){
						factorInCanonicalForm.add(value);
					}
				}
			}

			return;
		}

		for(List<ExpressionElement> subExpr : subExpressions){
			boolean isFactor = false;
			inner : for(ExpressionElement element : subExpr){
				if(element.getType() == ElementType.OPERAND){
					Value value = element.getOperand();
					if(value == fundamentalIndVar.getDefiningInstruction()){
						isFactor = true;
						if(count != 0){
							ExpressionElement factorOperatorElement = 
								expression.getExpression().get(additionIndices.get(count-1));
							if(factorOperatorElement.getBinOpId().isSubtraction()){
								factorOperatorPrefixIsSubtraction = true;
							}
						}
						break inner;
					}
				}
			}
			if(!isFactor){

				List<Value> constsInFactor = new ArrayList<Value>();
				for(ExpressionElement element: subExpr){
					if(element.getType() == ElementType.OPERAND){
						constsInFactor.add(element.getOperand());
					}
				}

				BinaryOperatorID binOpID = null;
				if(count == 0)
					binOpID = definingInstruction.getType().isIntegerType()?
							BinaryOperatorID.ADD : BinaryOperatorID.FADD;
				else
					binOpID = expression.getExpression().get(additionIndices.get(count-1)).getBinOpId();
				differenceInCanonicalForm.put(constsInFactor, binOpID);
			}
			else{
				// Set up the factor
				factorInCanonicalForm = new ArrayList<Value>();
				Constant constInFactor = null;
				for(ExpressionElement element: subExpr){
					if(element.getType() == ElementType.OPERAND){
						Value value = element.getOperand();
						if(value.isAConstant()){
							constInFactor = (Constant) value;
						}
						if(value != fundamentalIndVar.getDefiningInstruction())
							factorInCanonicalForm.add(value);
					}
				}
				if(factorOperatorPrefixIsSubtraction){
					try {
						// Multiply by -1 and fold if possible
						if(constInFactor != null){
							// Can be folded with a multiplying factor of -1
							BinaryOperatorID binOpID = definingInstruction.getType().isIntegerType()?
									BinaryOperatorID.MUL : BinaryOperatorID.FMUL;
							int indexOfConst = factorInCanonicalForm.indexOf(constInFactor);
							Constant negatedConst =  null;

							negatedConst = Constant.getConstant(binOpID, 
									Constant.getNegativeUnityConstant(constInFactor), constInFactor);

							factorInCanonicalForm.set(indexOfConst, negatedConst);
						}
						else{
							// No constants were found

							factorInCanonicalForm.add(0, Constant.getNegativeUnityConstant(definingInstruction));
						}
					} 
					catch (InstantiationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					catch (Exception e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}

			count++;
		}
	}

	public List<Value> getFactorInCanonicalForm() {
		return factorInCanonicalForm;
	}

	public HashMap<List<Value>, BinaryOperatorID> getDifferenceInCanonicalForm() {
		return differenceInCanonicalForm;
	}

	public LoopInfo getContainingLoop() {
		return containingLoop;
	}

	public void setIncrementor(BinaryOperator incrementor) {
		this.incrementor = incrementor;
	}

	public BinaryOperator getIncrementor() {
		return incrementor;
	}

	public static List<ExpressionElement> evaluate(BinaryOperatorID opCode,
			List<ExpressionElement> lhsExpr, List<ExpressionElement> rhsExpr){
		if(lhsExpr == null || rhsExpr == null){
			return null;
		}

		boolean isIntOperation = (opCode == BinaryOperatorID.ADD || opCode == BinaryOperatorID.SUB) ?
				true: false;

		List<ExpressionElement> result = new ArrayList<ExpressionElement>();

		List<Integer> additionIndicesForLHSExp = IndVarExpression.getAdditionIndices(lhsExpr);
		List<Integer> additionIndicesForRHSExp = IndVarExpression.getAdditionIndices(rhsExpr);

		int lhsSubExprCount = 0;
		List<List<ExpressionElement>> subExpressionsLhs = 
			IndVarExpression.getSubExpressions(additionIndicesForLHSExp,lhsExpr);

		List<List<ExpressionElement>> subExpressionsRhs = 
			IndVarExpression.getSubExpressions(additionIndicesForRHSExp,rhsExpr);

		// Ensure the operation code is compatible with the values in the expression
		Value firstOperand = (subExpressionsLhs.get(0).get(0).getType() == ElementType.OPERAND) ?
				subExpressionsLhs.get(0).get(0).getOperand() : subExpressionsLhs.get(0).get(1).getOperand();
				if(isIntOperation){
					if(!firstOperand.getType().isIntegerType()){
						LOG.error(INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION);
						assert false : INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION;
					}
				}
				else{
					if(firstOperand.getType().isIntegerType()){
						LOG.error(INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION);
						assert false : INCOMPATIBLE_INDUCTION_VARIABLE_OPERATION;
					}
				}

				Set<List<ExpressionElement>> foldedOrEliminatedSubExprFromRhs = new HashSet<List<ExpressionElement>>();

				outer : for(List<ExpressionElement> subExrLhs : subExpressionsLhs){

					// Determine the sign of the current lhs element
					BinaryOperatorID lhsSign = null;
					if(lhsSubExprCount == 0){
						if(additionIndicesForLHSExp.size() == 1 && subExpressionsLhs.size() == 1){
							lhsSign = lhsExpr.get(additionIndicesForLHSExp.get(0)).getBinOpId();
						}
						else{
							lhsSign = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
						}
					}
					else{
						lhsSign = lhsExpr.get(
								additionIndicesForLHSExp.get(lhsSubExprCount-1)).getBinOpId();
					}

					int rhsSubExprCount = 0;

					for(List<ExpressionElement> subExpRhs : subExpressionsRhs){

						if(foldedOrEliminatedSubExprFromRhs.contains(subExpRhs)){
							rhsSubExprCount++;
							continue;
						}

						// Determine the sign of the current rhs element
						BinaryOperatorID rhsSign = null;
						if(rhsSubExprCount == 0){
							rhsSign = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
						}
						else{
							rhsSign = rhsExpr.get(
									additionIndicesForRHSExp.get(rhsSubExprCount-1)).getBinOpId();
						}

						if(multiplicativeExpressionsAreSimilar(subExrLhs, subExpRhs)){
							// Found a common sub-expression; we can either eliminate if
							// this is a subtraction OR add them
							List<ExpressionElement> resultOfEliminateOrFold = eliminateOrFold(opCode, lhsSign,
									rhsSign, subExrLhs, isIntOperation);
							result.addAll(resultOfEliminateOrFold);
							lhsSubExprCount++;

							foldedOrEliminatedSubExprFromRhs.add(subExpRhs);

							continue outer;
						}

						rhsSubExprCount++;
					}

					// The  sub-expression pairs did not fold neither did they cancel, so add the
					// the lhs
					result.add(new ExpressionElement(lhsSign));
					result.addAll(subExrLhs);

					lhsSubExprCount++;
				}

				// Those that have been folded or eliminated in the rhs should now be added to the 
				// result
				int rhsSubExprCount = 0;
				for(List<ExpressionElement> subExpRhs : subExpressionsRhs){
					if(foldedOrEliminatedSubExprFromRhs.contains(subExpRhs)){
						rhsSubExprCount++;
						continue;
					}

					BinaryOperatorID rhsSign = null;
					if(rhsSubExprCount == 0){
						rhsSign = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
					}
					else{
						rhsSign = rhsExpr.get(
								additionIndicesForRHSExp.get(rhsSubExprCount-1)).getBinOpId();
					}

					BinaryOperatorID finalOperator = null;
					if(opCode.isAddition()){
						if(rhsSign.isAddition()){
							finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
						}
						else{
							finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
						}
					}
					else{
						if(rhsSign.isSubtraction()){
							finalOperator = isIntOperation? BinaryOperatorID.ADD : BinaryOperatorID.FADD;
						}
						else{
							finalOperator = isIntOperation? BinaryOperatorID.SUB : BinaryOperatorID.FSUB;
						}
					}

					result.add(new ExpressionElement(finalOperator));
					result.addAll(subExpRhs);

					rhsSubExprCount++;
				}

				return result;
	}

	public static boolean multiplicativeExpressionsAreSimilar(
			List<ExpressionElement> subExpr, List<ExpressionElement> otherSubExpr) {
		if(subExpr.size() != otherSubExpr.size()){
			return false;
		}

		if(subExpr.containsAll(otherSubExpr)){
			return true;
		}

		// Sometimes, the constants might differ (different objects), but the value of the
		// constant might be same; lets see if there is a match there
		Constant constInFirstExpr = null;
		for(ExpressionElement element : subExpr){
			if(element.getType() == ElementType.OPERATOR){
				continue;
			}

			Value value = element.getOperand();
			if(!value.isAConstant()){
				boolean foundMatch = false;
				for(ExpressionElement otherElem : otherSubExpr){
					if(otherElem.getType() == ElementType.OPERATOR){
						continue;
					}
					Value otherValue = otherElem.getOperand();
					if(otherValue == value){
						foundMatch = true;
						break;
					}
				}

				if(!foundMatch){
					return false;
				}

			}
			else{
				constInFirstExpr = (Constant) value;
			}
		}

		if(constInFirstExpr == null){
			return true;
		}

		// If we have come here, it means that we have constant in the expression that does
		// not match another constant (or a value) in the other expression.
		Constant constInSecondExpr = null;
		for(ExpressionElement element : otherSubExpr){
			if(element.getType() == ElementType.OPERATOR){
				continue;
			}

			if(element.getOperand().isAConstant()){
				constInSecondExpr = (Constant) element.getOperand();
			}
		}

		if(constInSecondExpr == null){
			return false;
		}

		// Check if the value of the constInFirstExpr is the same as that of the 
		// value of the constInSecondExpr
		return constInFirstExpr.equals(constInSecondExpr);
	}

	private static Constant foldIfPossible(BinaryOperatorID operator,
			BinaryOperatorID lhsOperatorPrefix, Value lhs, BinaryOperatorID rhsOperatorPrefix,
			Value rhs){

		if(lhs.isAConstant() && rhs.isAConstant()){
			Constant lhsConst = (Constant) lhs;
			Constant rhsConst = (Constant) rhs;

			Constant foldedConstant = null;

			BinaryOperatorID additionOperator = lhs.getType().isIntegerType()? BinaryOperatorID.ADD :
				BinaryOperatorID.FADD;

			BinaryOperatorID subtractionOperator = lhs.getType().isIntegerType()? BinaryOperatorID.SUB :
				BinaryOperatorID.FSUB;


			// Decide the operator
			BinaryOperatorID finalOperator = null;
			Constant finalLhs = lhsConst; Constant finalRhs = rhsConst;
			boolean postNegate = false;
			if(operator.isAddition()){
				if(lhsOperatorPrefix.isAddition()){
					if(rhsOperatorPrefix.isAddition()){         // +x + (+y) = x + y
						finalOperator = additionOperator;
					}
					else{                                       // +x + (-y) = x -y
						finalOperator = subtractionOperator;
					}
				}
				else{
					if(rhsOperatorPrefix.isAddition()){         // -x + (+y)  = y - x
						finalLhs = rhsConst; finalRhs = lhsConst; 
						finalOperator = subtractionOperator;
					}
					else{                                       // -x + (-y) = -1 * (x + y)
						finalOperator = additionOperator;
						postNegate = true;
					}
				}
			}
			else{
				if(lhsOperatorPrefix.isAddition()){
					if(rhsOperatorPrefix.isAddition()){         // +x - (+y) = x - y
						finalOperator = subtractionOperator;
					}
					else{                                       // +x - (-y) = x + y
						finalOperator = additionOperator;
					}
				}
				else{
					if(rhsOperatorPrefix.isAddition()){         // -x - (+y)  = -1 * (x +y)
						finalOperator = additionOperator;
						postNegate = true;
					}
					else{                                       // -x - (-y)  = y -x
						finalLhs = rhsConst; finalRhs = lhsConst; 
						finalOperator = subtractionOperator;
					}
				}
			}

			try{
				foldedConstant = Constant.getConstant(finalOperator, finalLhs, finalRhs);
				if(postNegate){
					foldedConstant = Constant.getConstWithOppositeSign(foldedConstant);
				}
			}
			catch(Exception e){
				e.printStackTrace();
				System.exit(-1);
			}

			return foldedConstant;
		}
		else{
			return null;
		}
	}

	private static List<ExpressionElement> eliminateOrFold(BinaryOperatorID opCode, 
			BinaryOperatorID lhsSign, BinaryOperatorID rhsSign, List<ExpressionElement> subExpr,
			boolean isIntOperation){

		List<ExpressionElement> result = new ArrayList<ExpressionElement>();
		boolean eliminate = true;

		if(opCode.isSubtraction()){
			if(lhsSign.isAddition()){
				if(!rhsSign.isAddition()){
					// x*y*z - (-x*y*z) = 2 *x *y*z
					eliminate = false;
				}
			}
			else{
				if(rhsSign.isAddition()){
					// -x*y*z - (+x*y*z) = -(2*x*y*z)
					eliminate = false;
				}
			}
		}
		else{
			if(lhsSign.isAddition()){
				if(rhsSign.isAddition()){
					// x*y*z + (+x*y*z) =  2 *x *y*z
					eliminate = false;
				}
			}
			else{
				if(!rhsSign.isAddition()){
					// -x*y*z + (-x*y*z) = - 2 * x *y*z
					eliminate = false;
				}
			}
		}

		// Eliminate or fold?
		if(eliminate){
			return result;
		}
		else{
			List<ExpressionElement> doublingResult = doubleExpression(isIntOperation, subExpr); 
			result.add(new ExpressionElement(lhsSign));
			result.addAll(doublingResult);
			return result;
		}
	}

	private static List<ExpressionElement> doubleExpression(boolean isIntegerOperation,
			List<ExpressionElement> subExrLhs) {

		BinaryOperatorID operator = isIntegerOperation? BinaryOperatorID.MUL : BinaryOperatorID.FMUL;

		List<ExpressionElement> result = new ArrayList<ExpressionElement>();

		Type type = subExrLhs.get(0).getOperand().getType();

		Constant two = null;
		try{
			if(type.isIntegerType()){
				IntegerType intType = (IntegerType)type;
			}
			else{
				two = new ConstantFP(type, new APFloat(APFloat.IEEEdouble,"2.0"));
			}

			Constant resultOfDoubling = null;
			int indexOfConst = 0;
			for(ExpressionElement exprElement : subExrLhs){
				if(exprElement.getType() == ElementType.OPERATOR){
					indexOfConst++;
					continue;
				}

				Value value = exprElement.getOperand();
				if(value.isAConstant()){
					Constant constElement = (Constant) value;
					resultOfDoubling = Constant.getConstant(operator, two, constElement);
				}
				indexOfConst++;
			}

			result.addAll(subExrLhs);
			if(resultOfDoubling != null){
				result.set(indexOfConst -1, new ExpressionElement(resultOfDoubling));
			}
			else{
				result.add(0, new ExpressionElement(operator));
				result.add(0, new ExpressionElement(two));
			}

		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		return result;
	}
}