package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.controlflowanalysis.ExpressionElement;
import org.tamedragon.common.controlflowanalysis.IndVarExpression;
import org.tamedragon.common.controlflowanalysis.InductionVariable;
import org.tamedragon.common.controlflowanalysis.InductionVariableAnalysis;
import org.tamedragon.common.controlflowanalysis.LoopInfo;
import org.tamedragon.common.controlflowanalysis.ExpressionElement.ElementType;
import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.Value.ValueTypeID;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This optimization identifies induction variables in loops, and hoists multiplication operations
 * outside the loops.
 *
 */
public class LoopStrengthReduce {	

	private final static String NEW_IND_VAR_NAME_PREFIX = ".iv";

	private int currentIndVarCount;

	private int totalIndVarCount;

	private LLVMIREmitter emitter;

	private List<InductionVariable> seedInductionVariables;

	private List<InductionVariable> newInductionVariables;

	private static final Logger LOG = LoggerFactory.getLogger(LoopStrengthReduce.class);
	
	public Transformations execute(Function function){

		emitter = LLVMIREmitter.getInstance();

		Transformations transformations = new Transformations(Transformations.LOOP_STRENGTH_REDUCE);

		currentIndVarCount = 1;

		seedInductionVariables = new ArrayList<InductionVariable>();

		newInductionVariables = new ArrayList<InductionVariable>();

		try{
			// Identify the induction variables
			InductionVariableAnalysis indVarAnalysis = 
				new InductionVariableAnalysis(function);
			HashMap<InductionVariable, List<InductionVariable>> indVarMap =	
				indVarAnalysis.getInductionVarMap();

			// Iterate over each class of induction variable
			Set<InductionVariable> fundamentalIndVarSet = indVarMap.keySet();
			Iterator<InductionVariable> fundamentalIndVarIterator =
				fundamentalIndVarSet.iterator();
			while(fundamentalIndVarIterator.hasNext()){
				InductionVariable fundamentalIndVar = fundamentalIndVarIterator.next();
				List<InductionVariable> dependentIndVars = indVarMap.get(fundamentalIndVar);

				seedInductionVariables.addAll(dependentIndVars);

				processIndVarClass(fundamentalIndVar, dependentIndVars);

				// Reset current induction variable count to 0
				currentIndVarCount = 0;
			}
		}
		catch(InstantiationException ie){
			ie.printStackTrace();
			System.exit(-1);
		} 
		catch (InstructionDetailAccessException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		catch(Exception ie){
			ie.printStackTrace();
			System.exit(-1);
		} 
		return transformations;
	}

	/**
	 * Processes a class of induction variables replacing any multiplication 
	 * operation as follows:
	 * 
	 * @param fundamentalIndVar
	 * @param dependentIndVars
	 * @throws InstructionDetailAccessException 
	 */
	private void processIndVarClass(InductionVariable fundamentalIndVar,
			List<InductionVariable> dependentIndVars) throws InstantiationException, 
			Exception {

		List<Instruction> deletedInstructions = new ArrayList<Instruction>();
		
		// Iterate over each of the dependent induction variables in this class
		for(InductionVariable indVar : dependentIndVars){

			Instruction definingInstruction = indVar.getDefiningInstruction();

			LOG.debug("Considering instruction : {} with ind var expression: {}", emitter.getValidName(definingInstruction),
					indVar.getExpression().toString());

			if(definingInstruction == fundamentalIndVar.getIncrementor()){
				continue;
			}

			if(definingInstruction.getInstructionID() != InstructionID.BINARY_INST){
				continue;
			}

			BinaryOperator binOp = (BinaryOperator)definingInstruction;
			if(!binOp.getOpCode().isMultiplication()){
				//propagateIndVarExpression(indVar, dependentIndVars, deletedInstructions);
				continue;
			}

			List<BasicBlock> predecessors = indVar.getContainingLoop().getPreHeader();

			// TODO create a preheader if there is more than one predecessor
			BasicBlock preheader = (BasicBlock) predecessors.get(0);

			// Get the list of new binary operators derived from the factor
			List<Value> newBinOperatorsFromFactor = 
				implementMultiplierInCanonicalForm(preheader, indVar);

			// Get the "b" in the canonical form j -> b * i + c;
			Value finalFactor = newBinOperatorsFromFactor.get(newBinOperatorsFromFactor.size() -1);

			// Get the b * i from the the canonical form j -> b * i + c
			Value resultOfMultiplyingValuesInFactor =
				getResultOfMultiplyingValuesInFactor(preheader, fundamentalIndVar,
						newBinOperatorsFromFactor);

			List<Value> newValuesFromDifference = 
				implementDifferenceInCanonicalForm(preheader, indVar, resultOfMultiplyingValuesInFactor);

			Value resultInCanonicalForm = 
				newValuesFromDifference.get((newValuesFromDifference.size() -1));

			// Now that we have j -> b * i + c (where j is resutInCanonicalForm), lets
			// add the new instruction (tj -> j + b * incrementedBy) after (i -> i + incrementedBy)
			Instruction newIns = insertNewIncrementingIns(indVar, newValuesFromDifference, 
					finalFactor,resultInCanonicalForm, preheader);

			// Replace the uses of the value associated with the induction variable with the 
			// new instruction and remove that instruction.
			
			LOG.debug("Removing instruction for strength reduction: {}",
					emitter.getValidName(indVar.getDefiningInstruction())); 
			 
			Instruction definingIns = indVar.getDefiningInstruction();
			definingIns.replaceAllUsesOfThisWith(newIns);
			definingIns.eraseFromParent();
			
			deletedInstructions.add(definingIns);

			// Update the induction variable count for the current fundamental
			// induction variable
			currentIndVarCount++;

			// ... and the total count too
			totalIndVarCount++;
		}
	}

	private void propagateIndVarExpression(InductionVariable indVar,
			List<InductionVariable> dependentIndVars,
			List<Instruction> deletedInstructions) {

		Instruction definingInstruction = indVar.getDefiningInstruction();

		BinaryOperator binOp = (BinaryOperator)definingInstruction;
		if(binOp.getOpCode().isMultiplication()){
			// Handle only additions and subtractions
			return;
		}

		Map<Instruction, List<ExpressionElement>> evaluatedExpressionForInstruction =
			new HashMap<Instruction, List<ExpressionElement>>();
		List<Instruction> workList = new ArrayList<Instruction>();
		workList.add(definingInstruction);

		evaluatedExpressionForInstruction.put(binOp, indVar.getExpression().getExpression());

		while(workList.size() != 0){
			Instruction instruction = workList.remove(0);
			int numUses = instruction.getNumUses();
			for(int i = 0; i < numUses; i++){
				Instruction use = (Instruction)instruction.getUserAt(i);
				if(getInductionVariableForInstruction(use) != null){
					// Ignore uses that are used in the definition of induction variables
					continue;
				}

				if(use.getInstructionID() != InstructionID.BINARY_INST){
					continue;
				}

				BinaryOperator useBinOp = (BinaryOperator) use;
				if(useBinOp.getOpCode().isMultiplication()){
					continue;
				}

				Value currentIns = null; Value otherOperand = null;
				Value opr1 = useBinOp.getOperand(0); Value opr2 = useBinOp.getOperand(1);
				boolean currentInsIsLHS = true;
				if(opr1 == instruction){
					currentIns = opr1; otherOperand = opr2;
				}
				else{
					currentIns = opr2; otherOperand = opr1;
					currentInsIsLHS = false;
				}

				List<ExpressionElement> exprElementsForOtherOperand = 
					evaluatedExpressionForInstruction.get(otherOperand);
				if(exprElementsForOtherOperand == null){
					InductionVariable indVarForOtherOperand = getInductionVariableForInstruction(otherOperand);
					if(indVarForOtherOperand != null){
						exprElementsForOtherOperand = indVarForOtherOperand.getExpression().getExpression();
					}
				}

				if(exprElementsForOtherOperand == null ){
					continue;
				}

				// We have two induction variables or folded expressions that define the current
				// instruction under consideration; see if any uses can be folded further
				
				List<ExpressionElement> result = null;
				if(currentInsIsLHS){
					result = InductionVariable.evaluate(useBinOp.getOpCode(), evaluatedExpressionForInstruction.get(currentIns), 
							exprElementsForOtherOperand);
				}
				else{
					result = InductionVariable.evaluate(useBinOp.getOpCode(), exprElementsForOtherOperand,
							evaluatedExpressionForInstruction.get(currentIns));
				}

				List<ExpressionElement> modifiedResult = analyzePropagatedResult(result, 
						indVar.getContainingLoop(), dependentIndVars, deletedInstructions);
				if(modifiedResult != result){
					// some change occurred 
					evaluatedExpressionForInstruction.put(useBinOp, modifiedResult);
					workList.add(useBinOp);
				}
			}
		}

		if(evaluatedExpressionForInstruction.size() > 0){
			propagate(evaluatedExpressionForInstruction);
		}
	}


	private void propagate(
			Map<Instruction, List<ExpressionElement>> evaluatedExpressionForInstruction) {
		Iterator<Instruction> iter = evaluatedExpressionForInstruction.keySet().iterator();
		while(iter.hasNext()){
			Instruction ins = iter.next();
			List<ExpressionElement> expression = evaluatedExpressionForInstruction.get(ins);
			if(expression.size() != 3){
				continue;
			}

			BinaryOperator binOp = (BinaryOperator)ins;

			Value newLhs = expression.get(0).getOperand();
			BinaryOperatorID binOpID = expression.get(1).getBinOpId();
			Value newRhs = expression.get(2).getOperand();

			BinaryOperator newBinOp = null;
			try{
			newBinOp = BinaryOperator.create(binOpID, newLhs.getType(), ins.getName(), newLhs, newRhs, 
					false, false, false, binOp.getParent());
			}
			catch(Exception e){
				e.printStackTrace();
				System.exit(-1);
			}
			
			binOp.replaceAllUsesOfThisWith(newBinOp);
			eliminateDeadInstruction(binOp);

		}
	}

	private void eliminateDeadInstruction(Instruction instruction){
		LOG.debug("Eliminating dead instruction: {}", emitter.getValidName(instruction));
		
		// Gather operands before removing
		List<Instruction> seedIns = new ArrayList<Instruction>();
		int numOperands = instruction.getNumOperands();
		for(int i= 0; i < numOperands; i++){
			Value opr = instruction.getOperand(i);
			if(opr.getValueTypeID() == ValueTypeID.INSTRUCTION){
				seedIns.add((Instruction) opr);
			}
		}

		instruction.eraseFromParent();

		// If any uses that are instructions are not used anymore, remove them
		// using a worklist

		List<Instruction> workList = seedIns;
		while(!workList.isEmpty()){
			Instruction ins = workList.remove(0);
			if(ins.getNumUses() == 0){
				int numOprs = ins.getNumOperands();
				for(int i = 0; i < numOprs; i++){
					Value val = ins.getOperand(i);
					if(val.getValueTypeID() == ValueTypeID.INSTRUCTION){
						workList.add((Instruction)val);
					}
				}

				ins.eraseFromParent();
			}
		}
	}

	private List<ExpressionElement>  analyzePropagatedResult(List<ExpressionElement> result, 
			LoopInfo containingLoop, List<InductionVariable> dependentIndVars,
			List<Instruction> deletedInstructions){

		List<ExpressionElement> finalResult = result;

		if(result.size() == 2 && isLoopConstant(containingLoop, result.get(1).getOperand())){
			finalResult = new ArrayList<ExpressionElement>();
			finalResult.addAll(result);
			return finalResult;
		}

		// Verify if the result can be further propagated
		List<ExpressionElement> resultWithoutSign = result;
		boolean resultSignIsPositive = true;
		if(result.get(0).getType() == ElementType.OPERATOR){
			resultWithoutSign = result.subList(1, result.size());
			BinaryOperatorID resultBinOp = result.get(0).getBinOpId();
			if(resultBinOp == BinaryOperatorID.SUB || resultBinOp == BinaryOperatorID.FSUB){
				resultSignIsPositive = false;
			}
		}

		List<InductionVariable> allIndVars = new ArrayList<InductionVariable>();
		allIndVars.addAll(dependentIndVars);
		allIndVars.addAll(newInductionVariables);
		
		for(InductionVariable iv : allIndVars){
			LOG.debug("Ind var expression = {}", iv.getExpression().getExpression());

			int matchingIndex = IndVarExpression.getFirstMatchingIndex(iv.getExpression(), resultWithoutSign);
			if(matchingIndex < 0){
				continue;
			}

			Instruction defIns = iv.getDefiningInstruction();
			if(deletedInstructions.contains(defIns)){
				continue;
			}

			List<ExpressionElement> restOfResult = null;
			if(matchingIndex == 0){
				restOfResult = resultWithoutSign.subList(iv.getExpression().getExpression().size(), 
						resultWithoutSign.size());
				if(restOfResult.size() == 2 && 
						isLoopConstant(containingLoop, restOfResult.get(1).getOperand())){
					if(resultSignIsPositive){
						finalResult = new ArrayList<ExpressionElement>();
						finalResult.add(new ExpressionElement(defIns)); finalResult.addAll(restOfResult);
					}
					LOG.debug("Final result {}", finalResult);
				}
			}
			else{
				restOfResult = resultWithoutSign.subList(0, matchingIndex);
				if(restOfResult.size() == 2 && 
						isLoopConstant(containingLoop, restOfResult.get(1).getOperand())){
					finalResult = new ArrayList<ExpressionElement>();
					finalResult.addAll(restOfResult); finalResult.add(new ExpressionElement(defIns)); 
					LOG.debug("Final result = {}", finalResult);
				}
			}

			break;

		}

		return finalResult;
	}

	private boolean isLoopConstant(LoopInfo loopInfo, Value value) {

		if(value.isAConstant())
			return true;

		Set<Value> loopInvariants = loopInfo.getLoopInvariants();

		if(loopInvariants.contains(value))
			return true;

		return false;

	}

	/**
	 * Returns the induction variable if this instruction defines one. Returns
	 * null if this does not define an induction variable. 
	 * @param instruction
	 * @return
	 */
	private InductionVariable getInductionVariableForInstruction(Value value){

		if(value.getValueTypeID() != ValueTypeID.INSTRUCTION){
			return null;
		}

		Instruction instruction = (Instruction) value;

		for(InductionVariable indVar : seedInductionVariables){
			if(indVar.getDefiningInstruction() == instruction)
				return indVar;
		}

		for(InductionVariable indVar : newInductionVariables){
			if(indVar.getDefiningInstruction() == instruction)
				return indVar;
		}

		return null;
	}

	private Value getResultOfMultiplyingValuesInFactor(BasicBlock preheader,
			InductionVariable fundamentalIndVar,
			List<Value> operatorsFromFactor) throws Exception {

		// Get the actual value of the instruction that defines the induction 
		// variable.
		PhiNode phiNodeDefIns = (PhiNode) fundamentalIndVar.getDefiningInstruction();
		Value valueInHeader = 
			phiNodeDefIns.getIncomingValue(phiNodeDefIns.getBasicBlockIndex(preheader));

		if(operatorsFromFactor.size() == 0){
			return valueInHeader;
		}

		// Get the final operator from the factor
		Value lastOperator = operatorsFromFactor.get(operatorsFromFactor.size() -1);

		// If both values are constants, fold the values and return
		if(valueInHeader.isAConstant()){
			boolean isInt = fundamentalIndVar.getDefiningInstruction().getType().isIntegerType()? true : false;
			Constant foldedConst = foldFactorIfPossible(operatorsFromFactor, (Constant) valueInHeader, isInt);
			return foldedConst;
		}

		// Create a new binary operator with the last binary operator in 
		// that list with the fundamental induction variable. This will give
		// us b * i in the canonical form j -> b * i + c

		return createNewMultiplierInstruction(preheader,
				lastOperator, valueInHeader);

	}

	private List<Value> implementMultiplierInCanonicalForm(BasicBlock preheader, 
			InductionVariable indVar) {

		List<Value> valuesInFactor = indVar.getFactorInCanonicalForm();
		int numValuesInFactor = valuesInFactor.size();
		if(numValuesInFactor == 0){
			// There are no factors that are multiplied with the
			// fundamental induction variable
			return new ArrayList<Value>();
		}

		return createNewBinaryOperatorsFromFactor(preheader, valuesInFactor);
	}

	private List<Value> implementDifferenceInCanonicalForm(BasicBlock preheader, 
			InductionVariable indVar, Value previousResult) {

		List<Value> newBinOperators = new ArrayList<Value>();

		HashMap<List<Value>, BinaryOperatorID> diffExpressions = 
			indVar.getDifferenceInCanonicalForm();

		if(diffExpressions.size() == 0){
			// There is no "c" in j -> b * i + c, just return the previous result
			newBinOperators.add(previousResult);
			return newBinOperators;
		}


		Set<List<Value>> expressions = diffExpressions.keySet();
		Iterator<List<Value>> iter = expressions.iterator();
		int count = 0;
		Value newValue = null;
		while(iter.hasNext()){
			List<Value> valuesInSubExpr = iter.next();
			BinaryOperatorID prefixBinOp = diffExpressions.get(valuesInSubExpr);

			// Get the list of new binary operators created from this sub-expression
			List<Value> binOprsFromThisSubExpr = 
				createNewBinaryOperatorsFromFactor(preheader, valuesInSubExpr);

			// Get the last binary operator - this will be the new operand for the next operation 
			Value nextOperand = binOprsFromThisSubExpr.get(binOprsFromThisSubExpr.size() -1);
			if(count == 0){
				newValue = createNewAdditionInstruction(preheader, prefixBinOp, previousResult,
						nextOperand, preheader.numInstructions());
			}
			else{
				newValue = createNewAdditionInstruction(preheader, prefixBinOp, newValue,
						nextOperand, preheader.numInstructions());
			}

			newBinOperators.add(newValue);
			count++;
		}

		return newBinOperators;
	}


	/** For each pair of values in the factor, create a new variable that
	 * multiplies them; they are guaranteed NOT to be folded since they
	 * are derived from the canonical expression
	 * 
	 */

	private List<Value> createNewBinaryOperatorsFromFactor(BasicBlock preheader,
			List<Value> values){
		List<Value> newValues = new ArrayList<Value>();

		int numValuesInFactor = values.size();

		if(numValuesInFactor == 1){
			newValues.add(values.get(0));
			return newValues;
		}

		Value newVal = null;
		for(int i = 0; i < numValuesInFactor; i++){
			if(i == 0){
				newVal = createNewMultiplierInstruction(preheader,
						values.get(0), values.get(1));
			}
			else if(i == numValuesInFactor -1){
				// The last one, we are already done
				return newValues;
			}
			else{
				newVal = createNewMultiplierInstruction(preheader,
						newVal, values.get(i));
			}
			newValues.add(newVal);
		}

		return newValues;
	}

	private Value createNewMultiplierInstruction(BasicBlock parentForNewInstruction, 
			Value lhs, Value rhs){
		boolean noSignedWrap = false;
		boolean noUnsignedWrap = false;
		boolean exact = false;
		BinaryOperator binOpr = null;


		BinaryOperatorID multiplierBinOp = 
			lhs.getType().isIntegerType()?
					BinaryOperatorID.MUL : BinaryOperatorID.FMUL;

		// We can consider special cases like  multiplication by zero or by 1, etc.
		Constant constInExpr = null;
		Value otherVal = null;
		if(lhs.isAConstant()){
			constInExpr = (Constant) lhs;
			otherVal = rhs;
		}
		else if(rhs.isAConstant()){
			constInExpr = (Constant) rhs;
			otherVal = lhs;
		}

		if(constInExpr != null){
			if(constInExpr.isZero()){
				return constInExpr;
			}
			else if(constInExpr.isPositiveUnity()){
				return otherVal;
			}
		}

		try {
			binOpr = BinaryOperator.create(
					multiplierBinOp, 
					lhs.getType(), null,
					lhs, rhs, noSignedWrap, noUnsignedWrap, exact, parentForNewInstruction);
		} 
		catch (InstructionCreationException e) {
			e.printStackTrace();
		}

		// Add the new binary operator instruction to the preheader before returning
		parentForNewInstruction.addInstruction(binOpr);

		return binOpr;
	}

	private Value createNewAdditionInstruction(BasicBlock parentForNewInstruction, 
			BinaryOperatorID additionBinOp, Value lhs, Value rhs, int indexToInsert){
		boolean noSignedWrap = false;
		boolean noUnsignedWrap = false;
		boolean exact = false;
		BinaryOperator binOpr = null;

		try{
			// If both the operands are constants, fold and return
			if(lhs.isAConstant() && rhs.isAConstant()){
				return Constant.getConstant(additionBinOp, (Constant) lhs, (Constant) rhs);
			}

			// We can consider special cases like  addition or subtraction by zero, etc.
			Constant constInExpr = null;
			Value otherVal = null;
			if(lhs.isAConstant()){
				constInExpr = (Constant) lhs;
				otherVal = rhs;
			}
			else if(rhs.isAConstant()){
				constInExpr = (Constant) rhs;
				otherVal = lhs;
			}

			if(constInExpr != null){
				if(additionBinOp.isAddition() &&constInExpr.isZero()){
					return otherVal;
				}
				else if(additionBinOp.isSubtraction() &&constInExpr.isZero()
						&& otherVal == lhs){
					return otherVal;
				}
			}


			binOpr = BinaryOperator.create(
					additionBinOp, 
					lhs.getType(), null,
					lhs, rhs, noSignedWrap, noUnsignedWrap, exact, parentForNewInstruction);


			// Add the new binary operator instruction to the preheader before returning
			parentForNewInstruction.insertInstructionAt(binOpr, indexToInsert);
		} 
		catch (InstructionCreationException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return binOpr;
	}

	private Instruction insertNewIncrementingIns(InductionVariable indVar, 
			List<Value> newValuesFromFactor, 
			Value resultOfMultiplyingValuesInFactor, 
			Value resultInCanonicalForm, BasicBlock preheader) throws InstantiationException,
			InstructionUpdateException, Exception{

		// Identify the incrementor, the value of the increment and the basic block it lives in
		InductionVariable fundamentalIndVar = indVar.getFundamentalIndVar();
		BinaryOperator incrementor = fundamentalIndVar.getIncrementor();
		Value incrementedBy = null;
		if(incrementor.getOperand(0) == fundamentalIndVar.getDefiningInstruction()){
			incrementedBy = incrementor.getOperand(1);
		}
		else{
			incrementedBy = incrementor.getOperand(0);
		}

		BasicBlock bbOfIncrementor = incrementor.getParent();

		// Decide if this an integer operation
		boolean isInt = incrementedBy.getType().isIntegerType()?
				true : false;

		// Create the phi node 
		PhiNode phiNode = PhiNode.create(incrementedBy.getType(), createNameForNewPhiNodeForIndVar(fundamentalIndVar), 2, null, 
				fundamentalIndVar.getDefiningInstruction().getParent());

		// Create the new instruction (tj -> tj + b * d)
		Instruction newIns =  createNewInsForIndVar(preheader, resultOfMultiplyingValuesInFactor,
				incrementedBy, isInt, phiNode, bbOfIncrementor, incrementor);

		phiNode.addIncoming(newIns, bbOfIncrementor);
		phiNode.addIncoming(resultInCanonicalForm, preheader);

		Instruction fundamentalIndVarDefIns = fundamentalIndVar.getDefiningInstruction();

		int indexToInsertAt = fundamentalIndVarDefIns.getParent().getInstructionIndex(fundamentalIndVarDefIns)
		+ (currentIndVarCount);
		phiNode.setParent(fundamentalIndVarDefIns.getParent());
		fundamentalIndVarDefIns.getParent().insertInstructionAt(phiNode, indexToInsertAt);

		// Create a new induction variable based on the new factor and difference
		InductionVariable newIndVar = new InductionVariable(phiNode, indVar.getBaseIndVar(), indVar.getFactor(), 
				indVar.getDifference(), indVar.isPostFix(),
				indVar.getBinOpId(), indVar.getContainingLoop());

		newInductionVariables.add(newIndVar);

		LOG.debug("Added new induction variable with phi node {} with equations: {}",emitter.getValidName(phiNode),
				newIndVar.getExpression().toString());

		return phiNode;
	}

	private String createNameForNewPhiNodeForIndVar(InductionVariable fundamentalIndVar) {

		String newName = "";
		PhiNode ins = (PhiNode)fundamentalIndVar.getDefiningInstruction();
		if(ins.hasName()){
			newName +=  ins.getName();
		}

		newName += NEW_IND_VAR_NAME_PREFIX + currentIndVarCount;
		return newName;

	}

	private Constant foldFactorIfPossible(List<Value> newValuesFromFactor,
			Constant multiplier, boolean isInt){
		try {
			for(Value value : newValuesFromFactor){
				if(value.isAConstant()){
					// Fold it!
					Constant foldedConstant = Constant.getConstant(isInt? BinaryOperatorID.MUL:
						BinaryOperatorID.FMUL, (Constant)value, (Constant) multiplier);

					return foldedConstant;
				}

				BinaryOperator binOp = (BinaryOperator) value;
				Value lhs = binOp.getOperand(0);
				Value rhs = binOp.getOperand(1);

				Constant constInBinOp = null;
				int constIndex = 0;
				if(lhs.isAConstant()){
					constInBinOp = (Constant) lhs;
				}
				else if(rhs.isAConstant()){
					constInBinOp = (Constant) rhs;
					constIndex = 1;
				}

				if(constInBinOp != null){
					// Fold it!
					Constant foldedConstant = Constant.getConstant(isInt? BinaryOperatorID.MUL:
						BinaryOperatorID.FMUL, constInBinOp, (Constant) multiplier);

					// Update the binary operator instruction with the folded constant
					binOp.setOperand(constIndex, foldedConstant);

					return foldedConstant;

				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	private BinaryOperator createNewInsForIndVar(BasicBlock preheader,
			Value resultOfMultiplyingValuesInFactor, Value incrementedBy,
			boolean isInt, Value resultInCanonicalForm, BasicBlock bbOfIncrementor,
			BinaryOperator incrementor) throws Exception{

		Value newIncrement = null;
		if(resultOfMultiplyingValuesInFactor.isAConstant() && incrementedBy.isAConstant()){
			// Can be folded
			newIncrement = Constant.getConstant(isInt? BinaryOperatorID.MUL : BinaryOperatorID.FMUL,
					(Constant)resultOfMultiplyingValuesInFactor, (Constant) incrementedBy);
		}
		else{
			// Create instruction for b * d in the canonical form tj -> tj + bd
			newIncrement = createNewMultiplierInstruction(preheader, 
					resultOfMultiplyingValuesInFactor, incrementedBy);
		}

		// Create tj -> tj + bd;
		BinaryOperator newIns = null;

		try {
			newIns = BinaryOperator.create(
					isInt? BinaryOperatorID.ADD : BinaryOperatorID.FADD,
							resultInCanonicalForm.getType(), null,
							resultInCanonicalForm, newIncrement, false, false, false, bbOfIncrementor);
		} 
		catch (InstructionCreationException e) {
			e.printStackTrace();
		}

		// Add the new binary operator instruction to the basic block having the incrementor
		bbOfIncrementor.insertInstructionAt(
				newIns, bbOfIncrementor.getInstructionIndex(incrementor) +(currentIndVarCount));

		return newIns;
	}
}
