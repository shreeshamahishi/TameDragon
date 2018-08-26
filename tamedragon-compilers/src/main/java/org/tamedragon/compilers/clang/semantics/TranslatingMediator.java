package org.tamedragon.compilers.clang.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.tamedragon.assemblyabstractions.constructs.AssemArgPassStm;
import org.tamedragon.assemblyabstractions.constructs.AssemBinOpExp;
import org.tamedragon.assemblyabstractions.constructs.AssemCJump;
import org.tamedragon.assemblyabstractions.constructs.AssemCallExp;
import org.tamedragon.assemblyabstractions.constructs.AssemConst;
import org.tamedragon.assemblyabstractions.constructs.AssemExp;
import org.tamedragon.assemblyabstractions.constructs.AssemExpList;
import org.tamedragon.assemblyabstractions.constructs.AssemExpStm;
import org.tamedragon.assemblyabstractions.constructs.AssemJump;
import org.tamedragon.assemblyabstractions.constructs.AssemLabel;
import org.tamedragon.assemblyabstractions.constructs.AssemMemory;
import org.tamedragon.assemblyabstractions.constructs.AssemMove;
import org.tamedragon.assemblyabstractions.constructs.AssemName;
import org.tamedragon.assemblyabstractions.constructs.AssemReturnStm;
import org.tamedragon.assemblyabstractions.constructs.AssemSeq;
import org.tamedragon.assemblyabstractions.constructs.AssemSeqExp;
import org.tamedragon.assemblyabstractions.constructs.AssemStm;
import org.tamedragon.assemblyabstractions.constructs.AssemTemp;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.assemblyabstractions.constructs.AssemUnaryOpExp;
import org.tamedragon.assemblyabstractions.constructs.AssemValueProperties;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.compilers.clang.abssyntree.BinaryOpExpr;
import org.tamedragon.compilers.clang.abssyntree.PostfixExpr;

public class TranslatingMediator {

	public static final int CONSTANT_INT = 0;
	public static final int CONSTANT_SHORT_INT = 1;
	public static final int CONSTANT_LONG_INT = 2;
	public static final int CONSTANT_CHAR = 3;
	public static final int CONSTANT_FLOAT = 4;
	public static final int CONSTANT_DOUBLE = 5;
	public static final int CONSTANT_STRING = 6;

	public static final int CONSTANT_ATTRIBUTE_SIGNED = 0;
	public static final int CONSTANT_ATTRIBUTE_UNSIGNED = 1;

	public AssemType translateWhileExp(AssemType whileTestAssemType,
			AssemType whileBodyAssemType, Label testLabel, Label endwhileLabel,
			boolean isDoWhile) {
		AssemType assemType = null;
		Label bodyLabel = new Label("");
		if (!isDoWhile) {
			Temp temp = new Temp();
			
			//AssemTemp assemTemp = translateTemp(temp, assemValueProperties);
			AssemConst assemConst0 = new AssemConst(0);
			Stack<AssemType> seqAssemTypes = new Stack<AssemType>();
			AssemLabel testAssemLabel = new AssemLabel(testLabel);
			seqAssemTypes.push(testAssemLabel);
			AssemExp whileTestExp = (AssemExp) whileTestAssemType;
			
			AssemTemp assemTemp = new AssemTemp(temp, whileTestExp.getValueProperties());
			
			AssemMove move = new AssemMove(assemTemp, whileTestExp);
			seqAssemTypes.push(move);
			AssemCJump cJump = new AssemCJump(AssemCJump.GT, assemTemp,
					assemConst0, bodyLabel, endwhileLabel);
			seqAssemTypes.push(cJump);
			AssemLabel bodyAssemLabel = new AssemLabel(bodyLabel);
			seqAssemTypes.push(bodyAssemLabel);
			AssemStm bodyAssemStm = (AssemStm) whileBodyAssemType;
			seqAssemTypes.push(bodyAssemStm);
			seqAssemTypes.push(translateJump(testLabel));
			AssemLabel endWhileAssemLabel = new AssemLabel(endwhileLabel);
			seqAssemTypes.push(endWhileAssemLabel);
			assemType = translateSeqStatement(seqAssemTypes);
		}else{
			Temp temp = new Temp();

			//AssemTemp assemTemp = translateTemp(temp, assemValueProperties);
			AssemConst assemConst0 = new AssemConst(0);
			Stack<AssemType> seqAssemTypes = new Stack<AssemType>();
			AssemLabel bodyAssemLabel = new AssemLabel(bodyLabel);
			seqAssemTypes.push(bodyAssemLabel);
			AssemStm bodyAssemStm = (AssemStm) whileBodyAssemType;
			seqAssemTypes.push(bodyAssemStm);
			AssemLabel testAssemLabel = new AssemLabel(testLabel);
			seqAssemTypes.push(testAssemLabel);
			AssemExp whileTestExpr = (AssemExp) whileTestAssemType;
			
			AssemTemp assemTemp = new AssemTemp(temp, whileTestExpr.getValueProperties());
			
			AssemMove move = new AssemMove(assemTemp, whileTestExpr);
			seqAssemTypes.push(move);
			AssemCJump cJump = new AssemCJump(AssemCJump.GT, assemTemp,
					assemConst0, bodyLabel, endwhileLabel);
			seqAssemTypes.push(cJump);
			seqAssemTypes.push(translateJump(testLabel));
			AssemLabel endWhileAssemLabel = new AssemLabel(endwhileLabel);
			seqAssemTypes.push(endWhileAssemLabel);
			assemType = translateSeqStatement(seqAssemTypes);
		}
		return assemType;
	}

	public AssemType translateForStmt(AssemType condExprTree,
			AssemType initExprTree, AssemType incrExprTree,
			AssemType forStmtTree, Label forTestLabel, Label forEndTestLabel) {

		// Create a conditional first
		AssemType assemCond = null;
		Label continueLabel = new Label("");

		if (condExprTree != null) {
			AssemExp assemExp = (AssemExp) condExprTree;
			assemCond = assemExp.translateToCJump(continueLabel,
					forEndTestLabel, false);
		}

		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();

		AssemType lblForTest = new AssemName(forTestLabel);
		AssemType lblForEndTest = translateLabel(forEndTestLabel);

		// Add to sequence of statements
		if (initExprTree != null)
			seqAssemTypes.push(initExprTree);
		seqAssemTypes.push(translateLabel(forTestLabel));
		if (assemCond != null)
			seqAssemTypes.push(assemCond);

		seqAssemTypes.push(translateLabel(continueLabel));
		if (forStmtTree != null)
			seqAssemTypes.push(forStmtTree);

		if (incrExprTree != null)
			seqAssemTypes.push(incrExprTree);

		seqAssemTypes.push(translateJump(lblForTest));
		seqAssemTypes.push(lblForEndTest);

		AssemType assemSeq = translateSeqStatement(seqAssemTypes);

		return assemSeq;

	}

	public AssemType translateSwitchStm(AssemType switchExprTree,
			AssemType switchBodyTree, Label switchEndLabel) {
		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();
		seqAssemTypes.push(switchExprTree);
		// seqAssemTypes.push(switchBodyTree);
		// seqAssemTypes.push(translateLabel(switchEndLabel));
		AssemType assemSeq = translateSeqStatement(seqAssemTypes);
		return assemSeq;
	}

	public AssemType translateConstant(String value, int constType) {
				
		if (constType == CONSTANT_CHAR) {
			char charVal = value.toCharArray()[0];
			if(value.startsWith("'") && value.endsWith("'")){
				int startIndex = value.indexOf("'");
				int endIndex = value.lastIndexOf("'");
				value = value.substring(startIndex + 1, endIndex);
				charVal = value.toCharArray()[0];
			}
			
			return new AssemConst(charVal);
		} 
		else if (constType == CONSTANT_INT) {
			try {
				int val = Integer.parseInt(value);				
				return new AssemConst(val);
			} catch (NumberFormatException nfe) {
				// TODO Handle this : could be in hexadecimal numbers like 0x0?
				int val = 0;
				return new AssemConst(val);
			}
		} 
		else if (constType == CONSTANT_LONG_INT) {
			long longVal = Long.parseLong(value);
			return new AssemConst(longVal);
		} 
		else if (constType == CONSTANT_FLOAT) {
			float floatVal = Float.parseFloat(value);
			return new AssemConst(floatVal);
		}
		else if (constType == CONSTANT_DOUBLE) {
			double doubleVal = Double.parseDouble(value);
			return new AssemConst(doubleVal);
		}
		else if (constType == CONSTANT_STRING) {
			// Lets save a string in a data block and get the address of the
			// string in memory. Since this is a C string, we append '\0' at the
			// end
			value += "\0";
			AssemConst constTree = new AssemConst(value);
			Label stringLabel = constTree.getLabelForStringValue();
			return translateName(stringLabel);
		}

		return null;
	}

	public AssemType translateAssignStm(AssemType assemTypeLHS,
			AssemType assemTypeRHS, int assignOperType) {
		AssemExp assemExpLHS = (AssemExp) assemTypeLHS;
		AssemExp assemExpRHS = (AssemExp) assemTypeRHS;
		
		if(assignOperType == -1){  // Normal assign statement
			AssemMove moveAssemType = new AssemMove(assemExpLHS, assemExpRHS);
			
			AssemExp exp = null;
			if (assemExpLHS instanceof AssemTemp) 
				exp = (AssemTemp) assemExpLHS;
			
			else if (assemExpLHS instanceof AssemMemory) 
				exp = (AssemMemory) assemExpLHS;			

			AssemSeqExp seqExp = new AssemSeqExp(moveAssemType, exp);
			return seqExp;
		}
		else{
			Stack<AssemType> seqAssemTypes = new Stack<AssemType>();
			AssemSeqExp operationRes = (AssemSeqExp)translateOperatorExp(assignOperType, assemExpLHS, assemExpRHS);
			seqAssemTypes.push(operationRes);
			AssemExp resExp = (AssemExp) operationRes.getExp();
			AssemMove moveAssemType = new AssemMove(assemExpLHS, resExp);
			seqAssemTypes.push(moveAssemType);
			
			AssemStm allStms = (AssemStm) translateSeqStatement(seqAssemTypes);

			AssemSeqExp operationResultTree = new AssemSeqExp(allStms, assemExpLHS);
			
			return operationResultTree;
		}
		
	}

	public AssemType translateIncrementOrDecrementOp(AssemType childTree,
			boolean isIncrement) {

		int plusOrMinus = BinaryOpExpr.PLUS;
		if (!isIncrement)
			plusOrMinus = BinaryOpExpr.MINUS;

		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();

		AssemConst assemConst = new AssemConst(1);

		// Child tree can only be of type AssemType or AssemMemory
		if (childTree instanceof AssemTemp) {
			AssemTemp childTreeTemp = (AssemTemp) childTree;
			AssemBinOpExp incrementedTree = (AssemBinOpExp) translateBinOp(
					plusOrMinus, childTree, assemConst);

			Temp temp = new Temp();
			//AssemTemp assemTemp = translateTemp(temp, assemValueProperties);
			AssemTemp assemTemp = new AssemTemp(temp, incrementedTree.getValueProperties());

			AssemMove assemMove = new AssemMove(assemTemp, incrementedTree);

			seqAssemTypes.push(assemMove);

			AssemMove move = new AssemMove(childTreeTemp, assemTemp);
			seqAssemTypes.push(move);

			AssemType seq = translateSeqStatement(seqAssemTypes);

			AssemSeqExp seqExp = new AssemSeqExp((AssemStm) seq, childTreeTemp);

			return seqExp;
		} else if (childTree instanceof AssemMemory) {
			AssemMemory childTreeMem = (AssemMemory) childTree;

			AssemBinOpExp incrementedTree = (AssemBinOpExp) translateBinOp(
					plusOrMinus, childTree, assemConst);
			seqAssemTypes.push(incrementedTree);

			AssemMove move = new AssemMove(childTreeMem, incrementedTree);
			seqAssemTypes.push(move);

			AssemType seq = translateSeqStatement(seqAssemTypes);

			AssemSeqExp seqExp = new AssemSeqExp((AssemStm) seq,
					(AssemExp) childTreeMem);

			return seqExp;
		}

		return null;
	}

	public AssemType translateOnesComplement(AssemType expr) {
		AssemExp exprTree = (AssemExp) expr;
		return new AssemUnaryOpExp(AssemUnaryOpExp.ONES_COMPLEMENT, exprTree);
	}

	public AssemType translateComparisonOperator(int comparisonOperator,
			AssemType leftTree, AssemType rightTree) {

		AssemType operationResultTree = null;

		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();

		// The result temp
		Temp finalTemp = new Temp();
		AssemValueProperties avpFinalTemp = new AssemValueProperties();
		avpFinalTemp.setInteger(true);
		avpFinalTemp.setIntegerSize(AssemType.SIZE_WORD);
		AssemTemp assemFinalTemp = new AssemTemp(finalTemp, avpFinalTemp);

		// The result will ultimately be one of these
		AssemConst assemConst0 = new AssemConst(0);
		AssemConst assemConst1 = new AssemConst(1);

		Temp newTempLeft = new Temp();
		AssemTemp newTempForLeftTree = new AssemTemp(newTempLeft, ((AssemExp)leftTree).getValueProperties());
		AssemMove moveLeftTreeToTemp = new AssemMove(newTempForLeftTree,
				(AssemExp) leftTree);
		seqAssemTypes.push(moveLeftTreeToTemp);

		Temp newTempRight = new Temp();
		AssemTemp newTempForRightTree = new AssemTemp(newTempRight, ((AssemExp)rightTree).getValueProperties());
		AssemMove moveRightTreeToTemp = new AssemMove(newTempForRightTree,
				(AssemExp) rightTree);
		seqAssemTypes.push(moveRightTreeToTemp);

		Label trueLabel = new Label("");
		Label falseLabel = new Label("");
		Label endElseLbl = new Label(""); // The "join" label

		AssemName assemName = new AssemName(endElseLbl);

		AssemCJump cjmp = new AssemCJump(comparisonOperator,
				newTempForLeftTree, newTempForRightTree, trueLabel, falseLabel);
		seqAssemTypes.push(cjmp);
		AssemLabel assemTrueLabel = new AssemLabel(trueLabel);
		seqAssemTypes.push(assemTrueLabel);
		AssemMove moveForTrueCondition = new AssemMove(assemFinalTemp,
				assemConst1);
		seqAssemTypes.push(moveForTrueCondition);
		AssemJump jmp = new AssemJump(assemName);
		seqAssemTypes.push(jmp);
		AssemLabel assemFalseLabel = new AssemLabel(falseLabel);
		seqAssemTypes.push(assemFalseLabel);
		AssemMove moveForFalseCondition = new AssemMove(assemFinalTemp,
				assemConst0);
		seqAssemTypes.push(moveForFalseCondition);

		// Final join label
		AssemLabel finalLabelTree = new AssemLabel(endElseLbl);
		seqAssemTypes.push(finalLabelTree);

		// Create the final list of statements and the seq-exp
		AssemStm allStms = (AssemStm) translateSeqStatement(seqAssemTypes);
		operationResultTree = new AssemSeqExp(allStms, assemFinalTemp);

		return operationResultTree;

	}

	public AssemType translateLogicalOperator(int logicalOperator,
			AssemType leftTree, AssemType rightTree) {

		AssemType operationResultTree = null;

		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();

		// The result temp
		Temp finalTemp = new Temp();
		AssemValueProperties avpFinalTemp = new AssemValueProperties();
		avpFinalTemp.setInteger(true);
		avpFinalTemp.setIntegerSize(AssemType.SIZE_WORD);
		AssemTemp assemFinalTemp = new AssemTemp(finalTemp, avpFinalTemp);

		// The result will ultimately be one of these
		AssemConst assemConst0 = new AssemConst(0);
		AssemConst assemConst1 = new AssemConst(1);

		Temp newTempLeft = new Temp();

		AssemTemp newTempForLeftTree = new AssemTemp(newTempLeft, ((AssemExp)leftTree).getValueProperties());
		AssemMove moveLeftTreeToTemp = new AssemMove(newTempForLeftTree,
				(AssemExp) leftTree);
		Temp newTempRight = new Temp();
		AssemTemp newTempForRightTree = new AssemTemp(newTempRight, ((AssemExp)rightTree).getValueProperties());
		AssemMove moveRightTreeToTemp = new AssemMove(newTempForRightTree,
				(AssemExp) rightTree);

		seqAssemTypes.push(moveLeftTreeToTemp);

		// Create two labels - one for true for the if case and the other false
		// for the else case
		Label firstIfLbl = new Label("");
		Label firstElseLbl = new Label("");
		Label endElseLbl = new Label(""); // The "join" label

		Label secondIfLbl = new Label("");
		Label secondElseLbl = new Label("");

		AssemType cJumpForLeftTree = newTempForLeftTree.translateToCJump(
				firstIfLbl, firstElseLbl, false);

		seqAssemTypes.push(cJumpForLeftTree);
		AssemLabel firstTrueLabel = new AssemLabel(firstIfLbl);
		seqAssemTypes.push(firstTrueLabel);

		AssemLabel firstFalseLabel = new AssemLabel(firstElseLbl);

		AssemType cJumpForRightTree = newTempForRightTree.translateToCJump(
				secondIfLbl, secondElseLbl, false);
		AssemLabel secondTrueLabel = new AssemLabel(secondIfLbl);
		AssemLabel secondFalseLabel = new AssemLabel(secondElseLbl);
		AssemMove moveTrueValue = new AssemMove(assemFinalTemp, assemConst1);
		AssemMove moveFalseValue = new AssemMove(assemFinalTemp, assemConst0);
		AssemName assemName = new AssemName(endElseLbl);
		AssemJump continueFromJoinLabel = new AssemJump(assemName);

		if (logicalOperator == BinaryOpExpr.OR) {
			// The path that evaluates to true
			seqAssemTypes.push(moveTrueValue);
			seqAssemTypes.push(continueFromJoinLabel);

			// The path that evaluates to false (must evaluate the next one in
			// this branch)
			seqAssemTypes.push(firstFalseLabel);
			seqAssemTypes.push(moveRightTreeToTemp);
			seqAssemTypes.push(cJumpForRightTree);
			seqAssemTypes.push(secondTrueLabel);
			seqAssemTypes.push(moveTrueValue);
			seqAssemTypes.push(continueFromJoinLabel);
			seqAssemTypes.push(secondFalseLabel);
			seqAssemTypes.push(moveFalseValue);
		} else {
			seqAssemTypes.push(moveRightTreeToTemp);
			seqAssemTypes.push(cJumpForRightTree);
			seqAssemTypes.push(secondTrueLabel);
			seqAssemTypes.push(moveTrueValue);
			seqAssemTypes.push(continueFromJoinLabel);
			seqAssemTypes.push(secondFalseLabel);
			seqAssemTypes.push(moveFalseValue);
			seqAssemTypes.push(continueFromJoinLabel);

			// The path that leads to false
			seqAssemTypes.push(firstFalseLabel);
			seqAssemTypes.push(moveFalseValue);
		}

		// Final join label
		AssemLabel continueLabel = new AssemLabel(endElseLbl);
		seqAssemTypes.push(continueLabel);

		AssemStm allStms = (AssemStm) translateSeqStatement(seqAssemTypes);

		operationResultTree = new AssemSeqExp(allStms, assemFinalTemp);

		return operationResultTree;

	}

	public AssemType translateIfStm(AssemType assemTypeTest,
			AssemType assemTypeIf, AssemType assemTypeElse) {
		AssemType operationResultTree = null;
		// create a temp variable to carry the value of the if condition
		
		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();

		// create a AssemConstant 0
		AssemConst assemConst0 = new AssemConst(0);

		// create three labels ifLabel, elseLabel and endOfElseLabel
		Label ifLabel = new Label("");
		Label elseLabel = new Label("");
		Label endOfElseLabel = new Label("");

		// put the value of the expression in a temp variable
		AssemSeqExp ifConditionExp = (AssemSeqExp) assemTypeTest;
		
		Temp temp1 = new Temp();
		
		AssemTemp assemTemp1 = new AssemTemp(temp1, ifConditionExp.getValueProperties());
		
		AssemMove moveIFExpValToTemp = new AssemMove(assemTemp1, ifConditionExp);
		seqAssemTypes.push(moveIFExpValToTemp);

		// If the condition satisfy then only go to if block or else go to else
		// block
		AssemCJump cJump = new AssemCJump(AssemCJump.GT, assemTemp1,
				assemConst0, ifLabel, elseLabel);
		seqAssemTypes.push(cJump);

		// If label creation
		AssemLabel ifLabelAssemLabel = new AssemLabel(ifLabel);
		seqAssemTypes.push(ifLabelAssemLabel);

		// put the statements under if block in the stack
		if (assemTypeIf instanceof AssemExpStm) {
			AssemExpStm assemSeqExp = (AssemExpStm) assemTypeIf;
			seqAssemTypes.push(assemSeqExp);
		} else if (assemTypeIf instanceof AssemSeq) {
			AssemSeq assemSeq = (AssemSeq) assemTypeIf;
			seqAssemTypes.push(assemSeq);
		}

		// jump to label after else block
		AssemName endOfElseLabelAssemName = new AssemName(endOfElseLabel);
		AssemJump endOfElseAssemJump = new AssemJump(endOfElseLabelAssemName);
		seqAssemTypes.push(endOfElseAssemJump);

		AssemLabel elseLabelAssemLabel = new AssemLabel(elseLabel);
		seqAssemTypes.push(elseLabelAssemLabel);
		
		if (assemTypeElse != null) {
			
			// put the statements under else block in the stack
			if (assemTypeElse instanceof AssemExpStm) {
				AssemExpStm assemSeqElseExp = (AssemExpStm) assemTypeElse;
				seqAssemTypes.push(assemSeqElseExp);
			}
			if (assemTypeElse instanceof AssemSeq) {
				AssemSeq assemSeq = (AssemSeq) assemTypeElse;			
				seqAssemTypes.push(assemSeq);
			}
		}

		AssemLabel endOfElseLabelAssemLabel = new AssemLabel(endOfElseLabel);
		seqAssemTypes.push(endOfElseLabelAssemLabel);

		// Create the final list of statements and the seq-exp
		operationResultTree = translateSeqStatement(seqAssemTypes);

		return operationResultTree;
	}

	public AssemType translateIntLiteral(int val) {
		return translateConstant(val);
	}

	public AssemType translateOperatorExp(int operType,
			AssemType assemTypeLeft, AssemType assemTypeRight) {		
		AssemBinOpExp opExp = (AssemBinOpExp) translateBinOp(operType,
				assemTypeLeft, assemTypeRight);
		return opExp.translateToIntType();
	}

	public AssemType translateIndirection(AssemType pointerTree, AssemValueProperties avp) {
		return new AssemMemory(AssemMemory.FRAME_POINTER, (AssemExp) pointerTree, avp);
	}

	/**
	 * Create and return a tree of the minus expr
	 * 
	 * @param addressTree
	 * @return
	 */
	public AssemType translateNegativeValOfExpr(AssemType addressTree) {
		AssemConst negativeVal = new AssemConst(-1);
		return translateBinOp(AssemBinOpExp.MUL, negativeVal, addressTree);
	}

	/**
	 * Create and return the negation of an expression
	 * 
	 * @param addressTree
	 * @return
	 */
	public AssemType translateNegationOfExpr(AssemExp assemExpr) {
		// TODO Implement this function
		// return assemExpr.translateToCJump(new Label(), new Label(), true);
		return assemExpr;
	}
	
	public AssemType translateVarDec(int offSetFromFP, int wordSize,
			AssemType assemInitExpr, AssemValueProperties assemValueProperties) {
		
		Temp tempFP = new Temp();
		AssemConst assemTypeRight = (AssemConst) translateConstant(offSetFromFP
				* wordSize);
		AssemTemp assemTypeLeft = (AssemTemp) translateTemp(tempFP, assemValueProperties);

		AssemBinOpExp assemOffSet = (AssemBinOpExp) translateBinOp(
				AssemBinOpExp.MINUS, assemTypeLeft, assemTypeRight);

		AssemMemory newVarMem = new AssemMemory(AssemMemory.FRAME_POINTER, 
				assemOffSet, assemValueProperties);

		// Create a move statement between the above and the init expression
		AssemMove assemMove = (AssemMove) translateMove(newVarMem,
				assemInitExpr);

		return assemMove;
	}

	public AssemType translateVarDec(Temp newVarTemp, AssemType assemInitExpr, 
			AssemValueProperties assemValueProperties, 
			HashMap<Temp, List<AssemType>> tempVsListOfUseTree) {
				
		AssemTemp assemTypeLeft = (AssemTemp) translateTemp(newVarTemp, assemValueProperties);

		// Create a move statement between the above and the init expression
		AssemMove assemMove = (AssemMove) translateMove(assemTypeLeft,
				assemInitExpr);
		 
		//Update the temp vs uses
		updateTempAndUses(assemTypeLeft, assemMove, tempVsListOfUseTree);

		return assemMove;
	}


	public void updateTempAndUses(AssemType assemType, AssemType useTree,
			HashMap<Temp, List<AssemType>> tempVsListOfUseTree){
		if(assemType instanceof AssemTemp){
			AssemTemp assemTemp = (AssemTemp) assemType;
			List<AssemType> usesTreeList = tempVsListOfUseTree.get(assemTemp.getTemp());
			if(usesTreeList == null)
				usesTreeList = new ArrayList<AssemType>();
			usesTreeList.add(useTree);

			tempVsListOfUseTree.put(assemTemp.getTemp(), usesTreeList);
		}
	}

	
	public AssemType translateCallExp(Label fLabel, Stack argsStack,
			int paramCount, AssemValueProperties avp) {

		AssemName assemFuncName = (AssemName) translateName(fLabel);
		AssemExpList expList = null;
		while (!argsStack.isEmpty()) {
			AssemExp exp = (AssemExp) argsStack.pop();
			exp = (AssemExp) exp.translateToIntType(); // must be converted to
			// an int type
			expList = new AssemExpList(exp, expList);
		}
		
		Temp retTemp = null;
		AssemTemp retAssemTemp = null;
		if(avp != null){
			retTemp = new Temp();
			retAssemTemp  = (AssemTemp) translateTemp(retTemp, avp);
		}
		
		return new AssemCallExp(assemFuncName, expList, retAssemTemp);
	}

	public AssemType translateFunctionArgument(Temp registerTemp,  AssemValueProperties avp, int index){

		AssemTemp argAssemTemp  = (AssemTemp) translateTemp(registerTemp, avp);
		return new AssemArgPassStm(argAssemTemp, index);		
	}
	
	private int getAssemOperatorType(int operType) {
		if (operType == BinaryOpExpr.AND)
			return AssemBinOpExp.BITWISE_AND;
		else if (operType == BinaryOpExpr.INCLUSIVE_OR)
			return AssemBinOpExp.BITWISE_OR;
		else if (operType == BinaryOpExpr.EXCLUSIVE_OR)
			return AssemBinOpExp.BITWISE_XOR;
		else if (operType == BinaryOpExpr.DIVIDE)
			return AssemBinOpExp.DIV;
		// else if (operType == BinaryOpExpr.EQUALS) return AssemBinOpExp.EQ;
		// else if (operType == BinaryOpExpr.GT) return AssemBinOpExp.GT;
		// else if (operType == BinaryOpExpr.GTE) return AssemBinOpExp.GTE;
		else if (operType == BinaryOpExpr.LEFT_SHIFT)
			return AssemBinOpExp.LSHIFT;
		// else if (operType == BinaryOpExpr.LT) return AssemBinOpExp.LT;
		// else if (operType == BinaryOpExpr.LTE) return AssemBinOpExp.LTE;
		else if (operType == BinaryOpExpr.MINUS)
			return AssemBinOpExp.MINUS;
		else if (operType == BinaryOpExpr.MODULO)
			return AssemBinOpExp.MODULO;
		else if (operType == BinaryOpExpr.MULTIPLY)
			return AssemBinOpExp.MUL;
		// else if (operType == BinaryOpExpr.NOT_EQUALS) return
		// AssemBinOpExp.NE;
		else if (operType == BinaryOpExpr.OR)
			return AssemBinOpExp.BITWISE_OR;
		else if (operType == BinaryOpExpr.PLUS)
			return AssemBinOpExp.PLUS;
		else if (operType == BinaryOpExpr.RIGHT_SHIFT)
			return AssemBinOpExp.RSHIFT;

		else
			return -1; // Invalid

	}

	public AssemType translateSeqStatement(Stack<AssemType> seq) {
		AssemType previousTree = null;
		int count = 0;
		while (!seq.isEmpty()) {
			AssemType currentTree = (AssemType) seq.pop();
			if (currentTree instanceof AssemExp) {
				// Coerce to a statement since we are building a tree of sequential statements
				AssemExp currentTreeExp = (AssemExp) currentTree;
				currentTree = currentTreeExp.translateToStatement();
			}

			if (count == 0) { // This is the LAST statement
				previousTree = currentTree;
			} else {
				previousTree = new AssemSeq((AssemStm) currentTree,
						(AssemStm) previousTree);
			}
			count++;
		}

		return previousTree;
	}

	public AssemType translateReturnExpr(AssemType assemReturnExpr, AssemValueProperties assemValueProperties) {

		if (assemReturnExpr == null){ // Nothing to return (void)			
			return new AssemReturnStm(null);
		}
		
		// A value is being returned
		
		Stack<AssemType> seqAssemTypes = new Stack<AssemType>();			
		
		AssemExp assemExp = (AssemExp) assemReturnExpr;
		Temp returnValueTemp = new Temp();		
		AssemExp assemRetTemp = (AssemExp) translateTemp(returnValueTemp, assemValueProperties);
		AssemMove movStm = new AssemMove(assemRetTemp, assemExp);
		
		seqAssemTypes.push(movStm);
		seqAssemTypes.push(new AssemReturnStm(assemRetTemp));
		
		return translateSeqStatement(seqAssemTypes);
	}

	public AssemType translateAssign(AssemType assemTypeLHS,
			AssemType assemTypeRHS) {
		return new AssemMove((AssemExp) assemTypeLHS, (AssemExp) assemTypeRHS);
	}

	public AssemType translateConditional(int rel, AssemType l, AssemType r,
			Label t, Label f) {
		return new AssemCJump(rel, (AssemExp) l, (AssemExp) r, t, f);
	}

	public AssemType translateJump(Label lbl) {
		AssemType lblForTest = new AssemName(lbl);
		return translateJump(lblForTest);
	}

	public AssemType translateJump(AssemType e) {
		return new AssemJump((AssemExp) e);
	}

	public AssemType translateLabel(Label label) {
		return new AssemLabel(label);
	}

	public AssemType translateMove(AssemType dst, AssemType src) {
		return new AssemMove((AssemExp) dst, (AssemExp) src);
	}

	public AssemType translateTemp(Temp temp, AssemValueProperties assemValueProperties) {
		AssemTemp assemTemp = new AssemTemp(temp, assemValueProperties);
		assemTemp.setAssemValueProperties(assemValueProperties);
		
		temp.setSigned(!assemValueProperties.isUnSigned());
		temp.setInteger(assemValueProperties.isInteger());
		temp.setFloatPrecision(assemValueProperties.getFloatPrecision());
		temp.setIntegerSize(assemValueProperties.getIntegerSize());
		
		return assemTemp;
	}

	public AssemType translateConstant(int i) {
		return new AssemConst(i);
	}

	public AssemType translateCJump(int op, AssemType left, AssemType right,
			Label tr, Label fl) {
		return new AssemCJump(op, (AssemExp) left, (AssemExp) right, tr, fl);
	}

	/*
	 * public AssemType translateSeqExp(AssemType stm, AssemType exp) { return
	 * new AssemSeqExp((AssemStm)stm,(AssemExp) exp); }
	 */

	public AssemType translateBinOp(int operType, AssemType assemTypeLeft,
			AssemType assemTypeRight) {
		return new AssemBinOpExp(getAssemOperatorType(operType),
				(AssemExp) assemTypeLeft, (AssemExp) assemTypeRight);
	}

	public AssemType translateName(Label label) {
		return new AssemName(label);
	}

	public AssemSeqExp addPostfixTree(AssemType translatedAssignExp,
			HashMap<AssemExp, Integer> postfixOperativeExprs) {

		AssemExp expOfAssignmentStm = null;
		AssemStm stmOfAssignmentStm = null;

		if (translatedAssignExp instanceof AssemSeqExp) {
			AssemSeqExp assemSeqExp = (AssemSeqExp) translatedAssignExp;
			expOfAssignmentStm = assemSeqExp.getExp();
			stmOfAssignmentStm = assemSeqExp.getStm();
		} else {
			// Must be of type AssemTemp
			expOfAssignmentStm = (AssemTemp) translatedAssignExp;
		}

		Stack<AssemType> seqStack = new Stack<AssemType>();
		if (stmOfAssignmentStm != null)
			seqStack.push(stmOfAssignmentStm);

		Set<AssemExp> postfixExprTrees = postfixOperativeExprs.keySet();
		Iterator<AssemExp> assemExprTreeIter = postfixExprTrees.iterator();
		while (assemExprTreeIter.hasNext()) {
			AssemExp assemExpTree = assemExprTreeIter.next();
			int operatorType = postfixOperativeExprs.get(assemExpTree);
			boolean isIncrement = true;
			if (operatorType == PostfixExpr.DECR)
				isIncrement = false;

			AssemExp incrementedTree = (AssemExp) translateIncrementOrDecrementOp(
					assemExpTree, isIncrement);

			seqStack.push(incrementedTree.translateToStatement());
		}

		AssemStm allStms = (AssemStm) translateSeqStatement(seqStack);

		AssemSeqExp finalTree = new AssemSeqExp(allStms, expOfAssignmentStm);
		return finalTree;

	}

	public int backTrack(Temp temp,
			HashMap<Temp, List<AssemType>> tempVsListOfUseTree, int offsetFromFP, 
			AssemValueProperties assemValueProperties) {
		int numReplacements = 0;

		List<AssemType> usesTree = tempVsListOfUseTree.get(temp);
		if (usesTree == null || usesTree.size() == 0)
			return numReplacements;

		// The temp was used earlier; lets back-track now.
		for (AssemType useAssem : usesTree) {
			// The temp was used in another expression; perform the replacements
			numReplacements += replaceTempNodeWithMemoryNode(temp, useAssem,
					offsetFromFP, numReplacements, assemValueProperties);

		}

		return numReplacements;
	}

	/**
	 * Replaces all references to the temp passed in the first parameter with a
	 * memory reference recursively in the second parameter.
	 */
	private int replaceTempNodeWithMemoryNode(Temp temp, AssemType useTree,
			int offsetFromFP, int numReplacements, AssemValueProperties assemValueProperties) {

		if (useTree instanceof AssemConst || useTree instanceof AssemLabel
				|| useTree instanceof AssemName || useTree instanceof AssemTemp) { 
			// Not valid, return 0
			return 0;
		}

		if (useTree instanceof AssemBinOpExp) {
			// The temporary is a child of a binary operation

			AssemBinOpExp assemBinOpExpUse = (AssemBinOpExp) useTree;
			AssemExp leftExp = assemBinOpExpUse.getLeft();
			if (leftExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) leftExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemBinOpExpUse.setLeft(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, leftExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}

			AssemExp rightExp = assemBinOpExpUse.getRight();
			if (rightExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) rightExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemBinOpExpUse.setRight(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, rightExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemCallExp) {
			// The temporary is a part of the exp list of a function call

			AssemCallExp assemCallExp = (AssemCallExp) useTree;
			AssemExpList assemExpList = assemCallExp.getArgs();
			while (assemExpList != null) {
				AssemExp expInList = assemExpList.getHead();
				if (expInList instanceof AssemTemp) {
					if (temp == ((AssemTemp) expInList).getTemp()) {
						AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP,
								assemValueProperties);
						assemExpList.setHead(assemMemory);
						numReplacements++;
					}
				} else {
					numReplacements = replaceTempNodeWithMemoryNode(temp,
							expInList, offsetFromFP, numReplacements, assemValueProperties);
				}

				assemExpList = assemExpList.getTail();
			}
		} else if (useTree instanceof AssemCJump) {
			// The temporary is a child of a CJump

			AssemCJump assemCJump = (AssemCJump) useTree;
			AssemExp leftExp = assemCJump.getLeft();
			if (leftExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) leftExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP,
							assemValueProperties);
					assemCJump.setLeft(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, leftExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
			AssemExp rightExp = assemCJump.getLeft();
			if (rightExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) rightExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemCJump.setRight(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, rightExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemExpStm) {
			// The temporary is a child of a AssemExpStm

			AssemExpStm assemExpStm = (AssemExpStm) useTree;
			AssemExp exp = assemExpStm.getExp();
			if (exp instanceof AssemTemp) {
				if (temp == ((AssemTemp) exp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemExpStm.setExp(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, exp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemJump) {
			// The temporary is a child of a AssemJump tree

			AssemJump assemJump = (AssemJump) useTree;
			AssemExp exp = assemJump.getLabelExp();
			if (exp instanceof AssemTemp) {
				if (temp == ((AssemTemp) exp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemJump.setLabelExp(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, exp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemMemory) {
			// The temporary is a child of a AssemMemory tree

			AssemMemory assemMemoryParent = (AssemMemory) useTree;
			AssemExp exp = assemMemoryParent.getMemExp();
			if (exp instanceof AssemTemp) {
				if (temp == ((AssemTemp) exp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemMemoryParent.setMemExp(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, exp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemMove) {
			// The temporary is a child of a AssemMove tree

			AssemMove assemMoveParent = (AssemMove) useTree;
			AssemExp srcExp = assemMoveParent.getSrc();
			if (srcExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) srcExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemMoveParent.setSrc(assemMemory);
					numReplacements++;
				}
			} 
			else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, srcExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}

			AssemExp dstExp = assemMoveParent.getDst();
			if (dstExp instanceof AssemTemp) {
				if (temp == ((AssemTemp) dstExp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemMoveParent.setDst(assemMemory);
					numReplacements++;
				}
			}
			else 
			{
				numReplacements = replaceTempNodeWithMemoryNode(temp, dstExp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		} else if (useTree instanceof AssemSeq) {
			// The temporary is a child of a AssemSeq tree

			AssemSeq assemSeq = (AssemSeq) useTree;
			AssemStm leftStm = assemSeq.getLeftStm();
			numReplacements = replaceTempNodeWithMemoryNode(temp, leftStm,
					offsetFromFP, numReplacements, assemValueProperties);
			AssemStm rightStm = assemSeq.getLeftStm();
			numReplacements = replaceTempNodeWithMemoryNode(temp, rightStm,
					offsetFromFP, numReplacements, assemValueProperties);
		} else if (useTree instanceof AssemSeqExp) {
			// The temporary is a child of a AssemSeqExp tree

			AssemSeqExp assemSeqExp = (AssemSeqExp) useTree;
			AssemExp exp = assemSeqExp.getExp();
			AssemStm stm = assemSeqExp.getStm();

			if (exp instanceof AssemTemp) {
				if (temp == ((AssemTemp) exp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemSeqExp.setExp(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, exp,
						offsetFromFP, numReplacements, assemValueProperties);
			}

			// For the statement list now
			numReplacements = replaceTempNodeWithMemoryNode(temp, stm,
					offsetFromFP, numReplacements, assemValueProperties);
		} else if (useTree instanceof AssemUnaryOpExp) {
			// The temporary is a child of a AssemMemory tree

			AssemUnaryOpExp assemUnaryOpParent = (AssemUnaryOpExp) useTree;
			AssemExp exp = assemUnaryOpParent.getExpr();
			if (exp instanceof AssemTemp) {
				if (temp == ((AssemTemp) exp).getTemp()) {
					AssemMemory assemMemory = createValueAtAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
					assemUnaryOpParent.setExpr(assemMemory);
					numReplacements++;
				}
			} else {
				numReplacements = replaceTempNodeWithMemoryNode(temp, exp,
						offsetFromFP, numReplacements, assemValueProperties);
			}
		}

		return numReplacements;
	}

	/**
	 * Creates a memory tree (value-at construct) with an offset from the frame
	 * pointer.
	 */

	public AssemMemory createValueAtAddressWithOffsetFromFP(int offsetFromFP, 
			AssemValueProperties assemValueProperties) {
		
		AssemExp assemExpWithOffsetFromFP = createAddressWithOffsetFromFP(offsetFromFP, assemValueProperties);
		AssemMemory assemMemory = new AssemMemory(AssemMemory.FRAME_POINTER, 
				assemExpWithOffsetFromFP, assemValueProperties);
		return assemMemory;
	}

	/**
	 * Creates a memory tree (value-at construct) with an offset from the frame
	 * pointer.
	 */

	public AssemBinOpExp createAddressWithOffsetFromFP(int offsetFromFP, AssemValueProperties assemValueProperties) {
		Temp tempFP = Temp.getTemp("$fp");
		
		AssemValueProperties avpForRegister = new AssemValueProperties();
		avpForRegister.setInteger(true);
		avpForRegister.setIntegerSize(AssemType.SIZE_WORD);
		avpForRegister.setSigned(false);
		
		AssemType assemTypeLeft = (AssemTemp) translateTemp(tempFP, avpForRegister);
		AssemConst assemConstOffset = (AssemConst) translateConstant(offsetFromFP);
		AssemBinOpExp assemOffSetFromFP = (AssemBinOpExp) translateBinOp(
				BinaryOpExpr.MINUS, assemTypeLeft, assemConstOffset);

		return assemOffSetFromFP;
	}
}
