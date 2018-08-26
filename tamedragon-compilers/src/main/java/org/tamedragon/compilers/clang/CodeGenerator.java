package org.tamedragon.compilers.clang;

import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.AssemArgPassStm;
import org.tamedragon.assemblyabstractions.constructs.AssemBinOpExp;
import org.tamedragon.assemblyabstractions.constructs.AssemCJump;
import org.tamedragon.assemblyabstractions.constructs.AssemCallExp;
import org.tamedragon.assemblyabstractions.constructs.AssemConst;
import org.tamedragon.assemblyabstractions.constructs.AssemExp;
import org.tamedragon.assemblyabstractions.constructs.AssemJump;
import org.tamedragon.assemblyabstractions.constructs.AssemLabel;
import org.tamedragon.assemblyabstractions.constructs.AssemMemory;
import org.tamedragon.assemblyabstractions.constructs.AssemMove;
import org.tamedragon.assemblyabstractions.constructs.AssemName;
import org.tamedragon.assemblyabstractions.constructs.AssemReturnStm;
import org.tamedragon.assemblyabstractions.constructs.AssemStm;
import org.tamedragon.assemblyabstractions.constructs.AssemStmList;
import org.tamedragon.assemblyabstractions.constructs.AssemTemp;
import org.tamedragon.assemblyabstractions.constructs.AssemUnaryOpExp;
import org.tamedragon.assemblyabstractions.constructs.AssemValueProperties;
import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.ArgumentPassIns;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.CJump;
import org.tamedragon.common.llvmir.instructions.Call;
import org.tamedragon.common.llvmir.instructions.Jump;
import org.tamedragon.common.llvmir.instructions.LabelInstr;
import org.tamedragon.common.llvmir.instructions.Operation;
import org.tamedragon.common.llvmir.instructions.Return;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class CodeGenerator  {

	private Vector<AssemblyInstruction> instructionsList;
	private boolean leafProcedure;

	private static final int CHAR_SIZE = 1;
	private static final int INTEGER_SIZE = 4;
	private static final int LONG_SIZE = 8;
	private static final int FP_SINGLE_PRECISION = 4;
	private static final int FP_DOUBLE_PRECISION = 8;

	public CodeGenerator() {
		instructionsList = new Vector<AssemblyInstruction>();
		leafProcedure = true;
	}

	public void generateCode(AssemStmList list) {

		for(AssemStmList tempList = list; tempList != null; 
		tempList = tempList.getStmList())
		{
			AssemStm stm = tempList.getStm();
			if(stm != null)
				generateCode(stm);
		}
	}

	private void generateCode(AssemStm stm) {
		int stmType = stm.getStmType();
		
		if(stmType == AssemStm.MOVE)
		{
			AssemMove assemMove = (AssemMove) stm;
			AssemExp dstExp = assemMove.getDst();
			AssemExp srcExp = assemMove.getSrc();
			int dstExpType = dstExp.getExpType();
			if(dstExpType == AssemExp.MEM)
			{
				// The destination is a memory address
				AssemMemory dstMemory = (AssemMemory) dstExp;		
				AssemExp memChild = dstMemory.getMemExp();
				int memChildType = memChild.getExpType();
				AssemValueProperties avpOfContainedType = dstMemory.getValueProperties();
				if(memChildType == AssemExp.BIN_OP)
				{
					AssemBinOpExp opExp = (AssemBinOpExp) memChild;
					munchMoveMemBinOpDstStm(opExp, srcExp, avpOfContainedType);
				}
				else if(memChildType == AssemExp.TEMP)
				{
					AssemTemp assemTemp = (AssemTemp) memChild;
					
					munchMoveMemTempDstStm(assemTemp.getTemp(), srcExp, avpOfContainedType);
				}
				else if(memChildType == AssemExp.CONST)
				{
					AssemConst assemConst = (AssemConst) memChild;
					munchMoveMemConstDstStm(assemConst, srcExp);
				}
				else if(memChildType == AssemExp.CALL)
				{
					AssemCallExp callExp = (AssemCallExp) memChild;
					munchMoveMemCallDstStm(callExp, srcExp, avpOfContainedType);
				}
				else if(memChildType == AssemExp.MEM)
				{
					AssemMemory memExp = (AssemMemory) memChild;
					munchMoveMemMemDstStm(memExp, srcExp, avpOfContainedType);
				}
			}
			else
			{
				// The only other possible destination of a move is a temp
				AssemTemp assemTemp = (AssemTemp) dstExp;
				Temp temp = assemTemp.getTemp();

				munchMoveTempDestStm(temp, srcExp);
			}
		}
		else if(stmType == AssemStm.CJUMP)
		{
			AssemCJump cjump = (AssemCJump) stm;
			munchCJumpStm(cjump);
		}
		else if(stmType == AssemStm.JUMP)
		{
			AssemJump jump = (AssemJump) stm;
			munchJumpStm(jump);
		}
		else if(stmType == AssemStm.LABEL)
		{
			AssemLabel lbl = (AssemLabel) stm;
			munchLblStm(lbl);
		}
		else if(stmType == AssemStm.ARG_PASS_STM)
		{
			AssemArgPassStm ret = (AssemArgPassStm) stm;
			munchArgPassStm(ret);
			
		}
		else if(stmType == AssemStm.RETURN)
		{
			AssemReturnStm ret = (AssemReturnStm) stm;
			munchRetStm(ret);
		}
	}

	private void munchMoveMemBinOpDstStm(AssemBinOpExp opExp, AssemExp srcExp, AssemValueProperties avpOfContainedType) {
		AssemExp opLeftChild = opExp.getLeft();
		AssemExp opRightChild = opExp.getRight();

		int leftChildType = opLeftChild.getExpType();
		int rightChildType = opRightChild.getExpType();

		int op = opExp.getOperator();
		if(op == AssemBinOpExp.PLUS || op == AssemBinOpExp.MINUS)
		{
			if(leftChildType == AssemExp.TEMP) {
				AssemTemp assemTemp = (AssemTemp) opLeftChild;
				Temp temp = assemTemp.getTemp();
				if(opRightChild.getExpType() == AssemExp.CONST)
				{
					// Found a  tile -> Move(Mem(BinOp(Plus/Minus, TEMP, i)), e1)
					AssemConst assemConst = (AssemConst) opRightChild;
					
					emitMoveMemOpTempConst(srcExp, opExp.getOperator(),
							assemConst.getIntValue(), temp);
				}
				else
				{
					//	The right child is not a const
					Temp rhsTemp = null;
					if(leftChildType == AssemExp.CALL) 
						rhsTemp = munchCallExp((AssemCallExp) opRightChild);
					else if(leftChildType == AssemExp.BIN_OP) 
						rhsTemp = munchBinOpExp((AssemBinOpExp) opRightChild);
					else if(leftChildType == AssemExp.MEM) 
						rhsTemp = munchMemoryExp((AssemMemory) opRightChild);
					else if(leftChildType == AssemExp.TEMP) 
						rhsTemp = munchTempExp((AssemTemp) opRightChild);

					Temp newTemp = addOrSubtractTemps(op, assemTemp.getTemp(), rhsTemp);						
					munchMoveMemTempDstStm(newTemp, srcExp, avpOfContainedType);	
				}
			}
			else if(leftChildType == AssemExp.CONST)
			{
				AssemConst assemConst = (AssemConst) opLeftChild;

				if(rightChildType == AssemExp.TEMP)
				{
					AssemTemp assemTemp = (AssemTemp) opRightChild;
					Temp temp = assemTemp.getTemp();
					if(op == AssemBinOpExp.PLUS )
					{
						// Found a  tile -> Move(Mem(BinOp(Plus, i, TEMP)), e1)								
						emitMoveMemOpTempConst(srcExp, opExp.getOperator(),
								assemConst.getIntValue(), temp);
					}
					else
					{
						// The Binop is of the form const - Temp (Minus does not commute)
						// Translate to $t0 = const - $t1, ($t0)
						Temp newTemp = emitOpConstTemp(op, assemConst, temp);
						munchMoveMemTempDstStm(newTemp, srcExp, avpOfContainedType);
					}
				}
				else
				{
					Temp rhsTemp = munchExp(opRightChild);
					Temp newTemp = emitOpConstTemp(op, assemConst, rhsTemp);
					munchMoveMemTempDstStm(newTemp, srcExp, avpOfContainedType);
				}
			}
			else
			{
				// The left child is neither a const nor a temp
				Temp lhsTemp = null;
				if(leftChildType == AssemExp.CALL) 
					lhsTemp = munchCallExp((AssemCallExp) opLeftChild);
				else if(leftChildType == AssemExp.BIN_OP) 
					lhsTemp = munchBinOpExp((AssemBinOpExp) opLeftChild);
				else if(leftChildType == AssemExp.MEM) 
					lhsTemp = munchMemoryExp((AssemMemory) opLeftChild);

				Temp rhsTemp = munchExp(opRightChild);
				Temp newTemp = addOrSubtractTemps(op, lhsTemp, rhsTemp);						
				munchMoveMemTempDstStm(newTemp, srcExp, avpOfContainedType);		
			}
		}
		else
		{
			// The operator is neither plus nor minus
			Temp lhsTemp = munchExp(opLeftChild);
			Temp rhsTemp = munchExp(opRightChild);
			Temp newTemp = opTemps(op,lhsTemp, rhsTemp, null);
			munchMoveMemTempDstStm(newTemp, srcExp, avpOfContainedType);
		}
	}

	private Temp opTemps(int op, Temp lhsTemp, Temp rhsTemp, AssemConst assemConst) {

		// If the third parameter is null, it means that the fourth 
		// parameter is an immediate value

		AssemValueProperties avp = null;		
		Numeric immediateValue = null;

		if(rhsTemp == null) {
			immediateValue = getNumericBasedOnAssemConst(assemConst);
			avp = findWiderValue(lhsTemp, assemConst);
		}
		else{
			avp = findWiderValue(lhsTemp, rhsTemp);
		}

		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, avp);

		// Create the instruction
		int operationCode = 0;
		if(op == AssemBinOpExp.PLUS)
			operationCode = Operation.ADD;
		else if(op == AssemBinOpExp.MINUS)
			operationCode = Operation.MINUS;
		else if(op == AssemBinOpExp.MUL)
			operationCode = Operation.MUL;
		else if(op == AssemBinOpExp.DIV)
			operationCode = Operation.DIV;
		else if(op == AssemBinOpExp.MODULO)
			operationCode = Operation.MODULO;
		else if(op == AssemBinOpExp.BITWISE_AND)
			operationCode = Operation.BITWISE_AND;
		else if(op == AssemBinOpExp.BITWISE_OR)
			operationCode = Operation.BITWISE_OR;
		else if(op == AssemBinOpExp.BITWISE_XOR)
			operationCode = Operation.BITWISE_XOR;
		else if(op == AssemBinOpExp.LSHIFT)
			operationCode = Operation.LEFT_SHIFT;
		else if(op == AssemBinOpExp.RSHIFT)
			operationCode = Operation.RIGHT_SHIFT;

		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		destList.addElement(newTemp);
		srcList.addElement(lhsTemp);
		if(rhsTemp != null)
			srcList.addElement(rhsTemp);

		//Operation instr = new Operation(resultInstr, destList, srcList, false);
		Operation instr = new Operation(destList, srcList, operationCode, immediateValue, null);
		instructionsList.addElement(instr);

		return newTemp;
	}

	private Temp emitOpConstTemp(int op, AssemConst assemConst, Temp temp) {
		Temp constTemp = new Temp();
		updateWithTypeInfo(constTemp, assemConst.getValueProperties());

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(constTemp);
		Numeric immd = getNumericBasedOnAssemConst(assemConst);

		Operation instr1 = new Operation(destList, null,Operation.LOAD_IMMEDIATE_VALUE,
				immd, null);

		instructionsList.addElement(instr1);
		return opTemps(op, constTemp, temp, null);
	}

	private void munchMoveMemTempDstStm(Temp temp, AssemExp srcExp, AssemValueProperties avpTypeOfContainedValue) {
		Temp srcTemp = munchExp(srcExp);
		
		updateWithTypeInfo(srcTemp, avpTypeOfContainedValue);

		Vector<Operand> srcList = new Vector<Operand>();
		srcList.addElement(srcTemp);    // First element is the source temporary
		srcList.addElement(temp);       // Second element is the temporary that represents the address

		Numeric tempNumeric = new Numeric(Numeric.INTEGER_TYPE, "" + 0);
		Operation instr = new Operation(null, srcList, Operation.STORE_WORD_INTO_ADDRESS_AT_TEMP,
				tempNumeric, null);
		instructionsList.addElement(instr);
	}

	private void munchMoveMemConstDstStm(AssemConst assemConst, AssemExp srcExp) {
		Temp srcTemp = munchExp(srcExp);

		Vector<Operand> srcList = new Vector<Operand>();
		srcList.addElement(srcTemp);

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, "" + assemConst.getIntValue());
		Operation instr = new Operation(null, srcList, Operation.STORE_WORD_INTO_CONST_ADDRESS,
				immd, null);
		instructionsList.addElement(instr);
	}

	private void munchMoveMemCallDstStm(AssemCallExp assemCall, AssemExp srcExp, AssemValueProperties avpOfContainedType) {

		leafProcedure = false;  // We now know this is NOT a leaf procedure

		String funcName = assemCall.getName();	

		Vector<Operand> usesTemp = assemCall.getUsesTemp();

		Vector<Temp>defTemps = new Vector<Temp>();
		AssemTemp returnAssemTemp = assemCall.getDefTemp();
		Temp returnTemp = null;
		if(returnAssemTemp != null){			
			returnTemp = returnAssemTemp.getTemp();
			defTemps.add(returnTemp);
		}

		Call instr = new Call(funcName, defTemps, usesTemp);
		instructionsList.addElement(instr);

		if(returnTemp != null)
			munchMoveMemTempDstStm(returnTemp, srcExp, avpOfContainedType);
		else{
			// TODO : issue a warning here that we cannot have a void value
			// returned from a function call storing into memory (or can be store zero at the memory location?)
		}
	}

	private void munchMoveMemMemDstStm(AssemMemory assemMem, AssemExp srcExp, AssemValueProperties avpOfContainedType) {
		Temp memChildTemp = munchMemoryExp(assemMem);
		munchMoveMemTempDstStm(memChildTemp, srcExp, avpOfContainedType);	
	}

	private void munchMoveTempDestStm(Temp temp, AssemExp srcExp) {
		int srcType = srcExp.getExpType();

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(temp);
		Vector<Operand> srcList = new Vector<Operand>();

		if(srcType == AssemExp.MEM) {
			AssemMemory assemMem = (AssemMemory) srcExp;
			munchMoveTempMemDestStm(temp, assemMem);
			return;
		}

		Operation instr = null;
		if(srcType == AssemExp.BIN_OP) {
			AssemBinOpExp assemOp = (AssemBinOpExp) srcExp;
			Temp opTemp = munchBinOpExp(assemOp);
			srcList.addElement(opTemp);
			srcList.addElement(opTemp);
			instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  
					null, null);
		}
		else if(srcType == AssemExp.TEMP)
		{
			AssemTemp tempExp = (AssemTemp) srcExp;
			srcList.addElement(tempExp.getTemp());
			instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,  null, 
					null);
		}
		else if(srcType == AssemExp.CALL)
		{
			AssemCallExp callExp = (AssemCallExp) srcExp;
			Temp callTemp = munchCallExp(callExp);
			srcList.addElement(callTemp);
			instr = new Operation(destList, srcList,Operation.MOVE_TEMP_TO_TEMP,  null,
					null);
		}
		else if(srcType == AssemExp.CONST)
		{
			srcList = null;
			AssemConst assemConst = (AssemConst) srcExp;			

			Numeric immd = null;

			int constType = assemConst.getConstantType();
			if(constType == AssemConst.CONSTANT_CHAR)
				immd = new Numeric(Numeric.CHAR_TYPE, ""+ assemConst.getCharValue());
			else if(constType == AssemConst.CONSTANT_INTEGER)
				immd = new Numeric(Numeric.INTEGER_TYPE, ""+ assemConst.getIntValue());
			else if(constType == AssemConst.CONSTANT_LONG)
				immd = new Numeric(Numeric.LONG_TYPE, ""+ assemConst.getLongValue());
			else if(constType == AssemConst.CONSTANT_FLOAT)
				immd = new Numeric(Numeric.FLOAT_TYPE, ""+ assemConst.getFloatValue());
			else
				immd = new Numeric(Numeric.DOUBLE_TYPE, ""+ assemConst.getDoubleValue());

			instr = new Operation(destList, srcList, Operation.LOAD_IMMEDIATE_VALUE,  
					immd, null);
		}
		else if(srcType == AssemExp.NAME)
		{
			srcList = null;
			AssemName assemName = (AssemName) srcExp;			
			instr = new Operation(destList, srcList, Operation.LOAD_ADDRESS, null, 
					assemName.getLabel().toString());
		}
		else if(srcType == AssemExp.UNARY_EXP){
			AssemUnaryOpExp unaryOpExp = (AssemUnaryOpExp) srcExp;
			Temp unaryTemp = munchUnaryExp(unaryOpExp);
			srcList.addElement(unaryTemp);
			instr = new Operation(destList, srcList,Operation.MOVE_TEMP_TO_TEMP,  null,
					null);
		}
		instructionsList.addElement(instr);
	}

	private void  munchMoveTempMemDestStm(Temp temp, AssemMemory assemMem)
	{
		AssemExp memChild = assemMem.getMemExp();

		int memChildType = memChild.getExpType();
		if(memChildType == AssemExp.BIN_OP)
		{
			AssemBinOpExp binOpExp = (AssemBinOpExp) memChild;
			int opr = binOpExp.getOperator();
			AssemExp leftChild = binOpExp.getLeft();
			AssemExp rightChild = binOpExp.getRight();
			if(opr == AssemBinOpExp.PLUS || opr == AssemBinOpExp.MINUS)
			{
				// This is a plus or minus operator; check further for a tile	
				int leftChildType = leftChild.getExpType();
				int rightChildType = rightChild.getExpType();
				if(leftChildType == AssemExp.TEMP)
				{
					Temp lcTemp = ((AssemTemp) leftChild).getTemp();
					if(rightChildType == AssemExp.CONST)
					{
						// Found a tile -> Move(Temp,(Mem(BinOp(Plus/Minus, Temp, Const)))
						AssemConst assemConst = (AssemConst) rightChild;
						int val = assemConst.getIntValue();
					
						String signStr = "";
						if(opr == AssemBinOpExp.MINUS) signStr = "-";

						Vector<Temp> destList = new Vector<Temp>();
						destList.addElement(temp);
						Vector<Operand> srcList = new Vector<Operand>();
						srcList.addElement(lcTemp);
						Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ signStr + val);
						Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
								immd, null);
						instructionsList.addElement(instr);
					}
					else
					{
						Temp rhsTemp = munchExp(rightChild);						
						Vector<Temp> destList = new Vector<Temp>();
						destList.addElement(temp);
						Vector<Operand> srcList = new Vector<Operand>();
						srcList.addElement(rhsTemp);
						Operation instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,
								null, null);
						instructionsList.addElement(instr);
					}
				}
				else if(leftChildType == AssemExp.CONST) {
					AssemConst assemConst = (AssemConst) leftChild;
					
					if(opr == AssemBinOpExp.PLUS && rightChildType == AssemExp.TEMP) {
						int val = assemConst.getIntValue();
						// Found another tile -> Move(Temp,(Mem(BinOp(Plus, Const, Temp)))
						Temp rightTemp = ((AssemTemp)rightChild).getTemp();

						Vector<Temp> destList = new Vector<Temp>();
						destList.addElement(temp);
						Vector<Operand> srcList = new Vector<Operand>();
						srcList.addElement(rightTemp);

						Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ val);
						Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
								immd, null);
						instructionsList.addElement(instr);
					}
					else {
						Temp newTemp = new Temp();
						updateWithTypeInfo(newTemp, assemConst.getValueProperties());

						Vector<Temp> destList1 = new Vector<Temp>();
						destList1.addElement(newTemp);
						Numeric immd = getNumericBasedOnAssemConst(assemConst);
						Operation instr = new Operation(destList1, null, Operation.LOAD_IMMEDIATE_VALUE, 
								immd, null);
						instructionsList.addElement(instr);

						Vector<Temp> destList = new Vector<Temp>();
						destList.addElement(temp);
						Vector<Operand> srcList = new Vector<Operand>();
						srcList.addElement(newTemp);
						Operation instr1 = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,
								null, null);
						instructionsList.addElement(instr1);
					}
				}
				else {
					// The left child is neither temp nor const
					Temp leftTemp = munchExp(leftChild);
					Temp rightTemp = munchExp(rightChild);
					Temp resultTemp = opTemps(opr, leftTemp, rightTemp, null);

					Vector destList = new Vector();
					destList.addElement(temp);
					Vector srcList = new Vector();
					srcList.addElement(resultTemp);

					Operation instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,
							null, null);
					instructionsList.addElement(instr);
				}
			}
			else
			{
				// The operator is neither plus nor minus
				Temp leftTemp = munchExp(leftChild);
				Temp rightTemp = munchExp(rightChild);
				Temp resultTemp = opTemps(opr, leftTemp, rightTemp, null);

				Vector destList = new Vector();
				destList.addElement(temp);
				Vector srcList = new Vector();
				srcList.addElement(resultTemp);

				Operation instr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP,
						null, null);
				instructionsList.addElement(instr);
			}
		}
		else
		{
			// The memory expression is not a binary operation.
			Temp memChildTemp = munchExp(memChild);

			Vector<Temp> destList = new Vector<Temp>();
			destList.addElement(temp);
			Vector<Operand> srcList = new Vector<Operand>();
			srcList.addElement(memChildTemp);

			Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ 0);
			Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
					immd, null);
			instructionsList.addElement(instr);
		}
	}

	private void  munchCJumpStm(AssemCJump cjumpStm) {
		Label ifLabel = cjumpStm.getIfTrueLabel();
		Label elseLabel = cjumpStm.getIfFalseLabel();

		AssemExp leftExp = cjumpStm.getLeft();
		AssemExp rightExp = cjumpStm.getRight();

		int leftExpType = leftExp.getExpType();
		int rightExpType = rightExp.getExpType();
		int op = cjumpStm.relop;
		int relop = getRelOp(op);

		Vector<Operand> srcList = new Vector<Operand>();
		AssemblyInstruction instr; 
		if(leftExpType == AssemExp.CONST) {
			AssemConst constLeft = (AssemConst) leftExp;

			if(!(relop == CJump.EQ || relop == CJump.NE))
				relop = AssemCJump.notRel(relop);  // Reverse the condition, since the const is first

			Temp rhsTemp = null;
			if(rightExpType == AssemExp.TEMP)
				rhsTemp = ((AssemTemp) rightExp).getTemp();
			else
				rhsTemp = munchExp(rightExp);

			srcList.addElement(rhsTemp);

			instr = createCJumpWhereOneOperandIsConst(constLeft, srcList, ifLabel, elseLabel, relop);			
		}
		else {
			Temp lhsTemp = munchExp(leftExp);
			srcList.addElement(lhsTemp);
			if(rightExpType == AssemExp.CONST) {
				AssemConst constRight =  (AssemConst) rightExp;
				instr = createCJumpWhereOneOperandIsConst(constRight, srcList, ifLabel, elseLabel, relop);		
			}
			else {
				// Neither the left nor the right are const values
				Temp rhsTemp = munchExp(rightExp);
				srcList.addElement(rhsTemp);
				instr = new CJump(srcList, ifLabel.toString(), 
						elseLabel.toString(), relop, null);
			}
		}

		instructionsList.addElement(instr);
	}

	public AssemblyInstruction createCJumpWhereOneOperandIsConst(AssemConst assemConst, Vector<Operand> srcList, Label ifLabel,
			Label elseLabel, int relop){

		AssemblyInstruction instr = null;

		int constType = assemConst.getConstantType();		

		if(constType == AssemConst.CONSTANT_CHAR){
			char value = assemConst.getCharValue();
			instr = new CJump(srcList, ifLabel.toString(), elseLabel.toString(), relop, 
					new Numeric(Numeric.CHAR_TYPE, "" + value));
		}
		else if(constType == AssemConst.CONSTANT_INTEGER){
			int value = assemConst.getIntValue();
			instr = new CJump(srcList, ifLabel.toString(), elseLabel.toString(), relop, 
					new Numeric(Numeric.INTEGER_TYPE, "" + value));
		}
		else if(constType == AssemConst.CONSTANT_LONG){
			long value = assemConst.getLongValue();
			instr = new CJump(srcList, ifLabel.toString(), elseLabel.toString(), relop, 
					new Numeric(Numeric.LONG_TYPE, "" + value));
		}
		else if(constType == AssemConst.CONSTANT_FLOAT){
			float value = assemConst.getFloatValue();
			instr = new CJump(srcList, ifLabel.toString(), elseLabel.toString(), relop, 
					new Numeric(Numeric.FLOAT_TYPE, "" + value));
		}
		else if(constType == AssemConst.CONSTANT_DOUBLE){
			double value = assemConst.getDoubleValue();
			instr = new CJump(srcList, ifLabel.toString(), elseLabel.toString(), relop, 
					new Numeric(Numeric.DOUBLE_TYPE, "" + value));
		}

		return instr;
	}

	/**
	 * For a given relative operator defined in MipsAssemCJump, this function returns the corresponding
	 * relative operation defined in CJump. Both are identical; however, they have been kept separate in
	 * order to achieve modularity.
	 * @param op
	 * @return
	 */
	private int getRelOp(int op){
		switch (op) 
		{
		case AssemCJump.EQ:  return CJump.EQ;
		case AssemCJump.NE:  return CJump.NE;
		case AssemCJump.LT:  return CJump.LT;
		case AssemCJump.GE:  return CJump.GE;
		case AssemCJump.GT:  return CJump.GT;
		case AssemCJump.LE:  return CJump.LE;
		case AssemCJump.ULT: return CJump.ULT;
		case AssemCJump.UGE: return CJump.UGE;
		case AssemCJump.UGT: return CJump.UGT;
		case AssemCJump.ULE: return CJump.ULE;

		default: return -1;
		}
	}

	private void munchJumpStm(AssemJump jumpStm)
	{
		// The expression for the jump must be either a name having a label or an exp 
		// that should be a temp
		AssemExp jmpExp = jumpStm.getLabelExp();
		String lblStr = "";
		if(jmpExp.getExpType() == AssemExp.NAME)
		{
			AssemName lblName = (AssemName) jmpExp;
			lblStr = lblName.getLabel().toString();	
		}
		else
		{
			Temp jmpTargetTemp = munchExp(jmpExp);
			lblStr = jmpTargetTemp.toString();
		}
		Jump instr = new Jump(lblStr);
		instructionsList.addElement(instr);
	}

	private void munchLblStm(AssemLabel lblStm)
	{
		String lbl = lblStm.getLabel().toString();
		LabelInstr instr = new LabelInstr(lbl);
		instructionsList.addElement(instr);
	}

	private void munchArgPassStm(AssemArgPassStm argPassStm){

		AssemExp assemExp  = argPassStm.getExp();
		if(assemExp == null)
			return;

		Temp argTemp = munchExp(assemExp);

		ArgumentPassIns instr = new ArgumentPassIns(argTemp);
		instructionsList.addElement(instr);
	}

	private void munchRetStm(AssemReturnStm retExpr) {
		Vector<Operand> oprs = new Vector<Operand>();
		AssemExp assemExp  = retExpr.getReturnExpr();
		if(assemExp != null){
			Temp retTemp = munchExp(assemExp);
			oprs.add(retTemp);
		}

		Return instr = new Return(oprs);
		instructionsList.addElement(instr);
	}

	private Temp munchExp(AssemExp exp) {
		Temp ret = null;
		
		int expType = exp.getExpType();
		if(expType == AssemExp.MEM) {
			AssemMemory memExp = (AssemMemory) exp;
			AssemExp memChild = memExp.getMemExp();
			AssemValueProperties avp = memExp.getValueProperties();
			
			int memChildType = memChild.getExpType();
			if(memChildType == AssemExp.BIN_OP) {
				AssemBinOpExp binOpExp = (AssemBinOpExp) memChild;
				ret = munchMemBinOpExp(binOpExp, avp);
			}
			else if(memChildType ==  AssemExp.CONST) {
				AssemConst constExp = (AssemConst) memChild;
				ret = munchMemConstExp(constExp, avp);				
			}
			else if(memChildType ==  AssemExp.CALL) {
				AssemCallExp callExp = (AssemCallExp) memChild;
				ret = munchMemCallExp(callExp);
			}
			else if(memChildType == AssemExp.MEM) {
				AssemMemory mem = (AssemMemory) memChild;
				ret = munchMemMemExp(mem, avp);
			}
			else if(memChildType == AssemExp.TEMP) {
				AssemTemp tempExp = (AssemTemp) memChild;
				ret = munchMemTempExp(tempExp, avp);
			}
		}
		else if(expType == AssemExp.BIN_OP) {
			AssemBinOpExp binOpExp = (AssemBinOpExp) exp;
			ret = munchBinOpExp(binOpExp);
		}
		else if(expType == AssemExp.CONST) {
			AssemConst constExp = (AssemConst) exp;
			ret = munchConstExp(constExp);
		}
		else if(expType == AssemExp.CALL)
		{
			AssemCallExp callExp = (AssemCallExp) exp;
			ret = munchCallExp(callExp);
		}
		else if(expType == AssemExp.TEMP)
		{
			AssemTemp tempExp = (AssemTemp) exp;
			ret = tempExp.getTemp();
		}

		return ret;
	}

	private Temp munchMemBinOpExp(AssemBinOpExp binOpExp, AssemValueProperties avp) {
		Temp ret = new Temp();
		updateWithTypeInfo(ret, avp);
		
		AssemExp opLeftChild = binOpExp.getLeft();
		AssemExp opRightChild = binOpExp.getRight();	
		int op = binOpExp.getOperator();

		int leftChildType = opLeftChild.getExpType();
		int rightChildType = opRightChild.getExpType();

		if(op == AssemBinOpExp.PLUS || op == AssemBinOpExp.MINUS) {
			if(leftChildType == AssemExp.TEMP) {
				AssemTemp assemTemp = (AssemTemp) opLeftChild;
				Temp temp = assemTemp.getTemp();
				if(opRightChild.getExpType() == AssemExp.CONST) {
					// Found a  tile -> Mem(BinOp(Plus/Minus, TEMP, i)), e1)
					AssemConst assemConst = (AssemConst) opRightChild;

					int val = assemConst.getIntValue();
					
					if(op == AssemBinOpExp.MINUS) val *= -1;

					Vector<Temp> destList = new Vector<Temp>();
					Vector<Operand> srcList = new Vector<Operand>();
					srcList.addElement(temp);
					destList.addElement(ret);
					Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ val);
					Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
							immd, null);
					instructionsList.addElement(instr);
				}
				else {
					//	The right child is not a const
					Temp rhsTemp = null;
					if(leftChildType == AssemExp.CALL) 
						rhsTemp = munchCallExp((AssemCallExp) opRightChild);
					else if(leftChildType == AssemExp.BIN_OP) 
						rhsTemp = munchBinOpExp((AssemBinOpExp) opRightChild);
					else if(leftChildType == AssemExp.MEM) 
						rhsTemp = munchMemoryExp((AssemMemory) opRightChild);
					else if(leftChildType == AssemExp.TEMP) 
						rhsTemp = munchTempExp((AssemTemp) opRightChild);
					
					Temp resultTemp = addOrSubtractTemps(op, assemTemp.getTemp(), rhsTemp);
					createLoadInstructionAtMemExpTemp(resultTemp, ret);
				}
			}
			else if(leftChildType == AssemExp.CONST) {
				AssemConst assemConst = (AssemConst) opLeftChild;
				if(rightChildType == AssemExp.TEMP) {
					AssemTemp assemTemp = (AssemTemp) opRightChild;
					Temp temp = assemTemp.getTemp();
					if(op == AssemBinOpExp.PLUS ) {
						// Found a  tile -> Mem(BinOp(Plus, i, TEMP)), e1)								

						Vector<Temp> destList = new Vector<Temp>();
						Vector<Operand> srcList = new Vector<Operand>();
						srcList.addElement(temp);
						destList.addElement(ret);

						int val = assemConst.getIntValue();

						Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ val);
						Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
								immd, null);
						instructionsList.addElement(instr);
					}
					else {
						// The Binop is of the form "const - Temp" (Minus does not commute)
						// Translate to $t0 = const - $t1, ($t0)
						Temp resultTemp = emitOpConstTemp(op, assemConst, temp);
						createLoadInstructionAtMemExpTemp(resultTemp, ret);
					}
				}
				else {
					Temp resultTemp = munchExp(opRightChild);	
					createLoadInstructionAtMemExpTemp(resultTemp, ret);
				}
			}
			else {
				// The left child is neither a const nor a temp
				Temp lhsTemp = null;
				if(leftChildType == AssemExp.CALL) 
					lhsTemp = munchCallExp((AssemCallExp) opLeftChild);
				else if(leftChildType == AssemExp.BIN_OP) 
					lhsTemp = munchBinOpExp((AssemBinOpExp) opLeftChild);
				else if(leftChildType == AssemExp.MEM) 
					lhsTemp = munchMemoryExp((AssemMemory) opLeftChild);

				Temp rhsTemp = munchExp(opRightChild);
				Temp resultTemp = addOrSubtractTemps(op, lhsTemp, rhsTemp);	
				createLoadInstructionAtMemExpTemp(resultTemp, ret);
			}
		}
		else {
			// The operator is neither plus nor minus
			Temp lhsTemp = munchExp(opLeftChild);
			Temp rhsTemp = munchExp(opRightChild);
			Temp resultTemp = opTemps(op,lhsTemp, rhsTemp, null);
			createLoadInstructionAtMemExpTemp(resultTemp, ret);
		}
		
		return ret;
	}

	/**
	 * Creates an instruction to load from memory (that has address represented by the child temp addressChildTemp)
	 * into the newTemp passed in the second parameter.
	 * @param resultTemp
	 * @param newTemp
	 */
	private void createLoadInstructionAtMemExpTemp(Temp addressChildTemp, Temp newTemp){
		
		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		srcList.addElement(addressChildTemp);
		destList.addElement(newTemp);						

		Numeric immdZeroOffset = new Numeric(Numeric.INTEGER_TYPE, "0");
		Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM,
				immdZeroOffset, null);
		instructionsList.addElement(instr);
	}

	private Temp munchMemConstExp(AssemConst constExp, AssemValueProperties avp) {
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, avp);
		int val = constExp.getIntValue();

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(newTemp);

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ val);
		Operation instr = new Operation(destList, null, Operation.LOAD_ADDRESS, immd, null);
		instructionsList.addElement(instr);
		return newTemp;
	}

	private Temp munchMemCallExp(AssemCallExp callExp) {

		leafProcedure = false;  // We now know this is NOT a leaf procedure

		Temp returnTemp = null;

		// Instruction to invoke the function
		String funcName = callExp.getName();

		Vector<Operand> usesTemp = callExp.getUsesTemp();

		Vector<Temp>defTemps = new Vector<Temp>();
		AssemTemp returnAssemTemp = callExp.getDefTemp();
		if(returnAssemTemp != null){			
			returnTemp = returnAssemTemp.getTemp();
			defTemps.add(returnTemp);
		}

		Call instr = new Call(funcName, defTemps, usesTemp);
		instructionsList.addElement(instr);

		return returnTemp;
	}

	private Temp munchMemMemExp(AssemMemory mem, AssemValueProperties avp) {
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, avp);
		Temp memTemp = munchMemoryExp(mem);

		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		destList.addElement(newTemp);
		srcList.addElement(memTemp);

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, "0");
		Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, 
				immd, null);
		instructionsList.addElement(instr);
		return newTemp;
	}

	private Temp munchMemTempExp(AssemTemp assemTemp, AssemValueProperties avp) {
		
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, avp);
		
		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		destList.addElement(newTemp);
		srcList.addElement(assemTemp.getTemp());

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ 0);
		Operation instr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, 
				immd, null);
		instructionsList.addElement(instr);
		return newTemp;
	}

	private Temp munchCallExp(AssemCallExp callExp) {

		leafProcedure = false;  // We now know this is NOT a leaf procedure

		String funcName = callExp.getName();
		Vector<Operand> usesTemp = callExp.getUsesTemp();

		Vector<Temp>defTemps = new Vector<Temp>();
		AssemTemp returnAssemTemp = callExp.getDefTemp();
		Temp returnTemp = null;
		if(returnAssemTemp != null){			
			returnTemp = returnAssemTemp.getTemp();
			defTemps.add(returnTemp);
		}

		Call instr = new Call(funcName, defTemps, usesTemp);
		instructionsList.addElement(instr);

		return returnTemp;
	}

	private Temp munchConstExp(AssemConst constExp)
	{
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, constExp.getValueProperties());

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(newTemp);

		Numeric immd = getNumericBasedOnAssemConst(constExp);
		Operation instr = new Operation(destList, null, Operation.LOAD_IMMEDIATE_VALUE, 
				immd, null);
		instructionsList.addElement(instr);
		return newTemp;
	}

	public Numeric getNumericBasedOnAssemConst(AssemConst assemConst){

		int constType = assemConst.getConstantType();		
		Numeric numeric = null;
		if(constType == AssemConst.CONSTANT_CHAR){
			numeric = new Numeric(Numeric.CHAR_TYPE, "" + assemConst.getCharValue());
		}
		else if(constType == AssemConst.CONSTANT_INTEGER){
			numeric = new Numeric(Numeric.INTEGER_TYPE, "" + assemConst.getIntValue());
		}
		else if(constType == AssemConst.CONSTANT_LONG){
			numeric = 
				new Numeric(Numeric.LONG_TYPE, "" + assemConst.getLongValue());
		}
		else if(constType == AssemConst.CONSTANT_FLOAT){
			numeric = 
				new Numeric(Numeric.FLOAT_TYPE, "" + assemConst.getFloatValue());
		}
		else if(constType == AssemConst.CONSTANT_DOUBLE){
			numeric = 
				new Numeric(Numeric.DOUBLE_TYPE, "" + assemConst.getDoubleValue());
		}

		return numeric;

	}

	private Temp munchUnaryExp(AssemUnaryOpExp unaryOpExp) {
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, unaryOpExp.getValueProperties());

		Vector<Temp> destList = new Vector<Temp>();
		destList.addElement(newTemp);
		Vector<Operand> srcList = new Vector<Operand>();
		AssemTemp srcTemp = (AssemTemp)unaryOpExp.getExpr();
		srcList.add(srcTemp.getTemp());
		Operation instr = new Operation(destList, srcList, Operation.ONES_COMPLEMENT, 
				null, null);
		instructionsList.addElement(instr);
		return newTemp;
	}

	private Temp munchBinOpExp(AssemBinOpExp binOpExp) {
		AssemExp leftExp = binOpExp.getLeft();
		AssemExp rightExp = binOpExp.getRight();

		int opr = binOpExp.getOperator();

		int leftType = leftExp.getExpType();
		int rightType = rightExp.getExpType();
		if(leftType == AssemExp.TEMP)
		{
			AssemTemp leftTemp = (AssemTemp) leftExp;
			if(rightType == AssemExp.CONST)
			{				
				return opTemps(opr, leftTemp.getTemp(), null, (AssemConst) rightExp);
			}
		}
		else if(leftType == AssemExp.CONST) {

			if(rightType == AssemExp.TEMP && (opr == AssemBinOpExp.PLUS 
					|| opr == AssemBinOpExp.MINUS)) {
				Temp tmpRight = ((AssemTemp)rightExp).getTemp();
				return opTemps(opr, tmpRight, null, (AssemConst) leftExp);
			}
		}

		Temp leftTemp = munchExp(leftExp);
		Temp rightTemp = munchExp(rightExp);

		return opTemps(binOpExp.getOperator(), leftTemp, rightTemp, null);
	}

	private Temp munchMemoryExp(AssemMemory memExp) {
		
		Temp newTemp = new Temp();
		AssemValueProperties avp = createTypeForAddress();
		updateWithTypeInfo(newTemp, avp);
		
		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		destList.addElement(newTemp);

		AssemExp memChild = memExp.getMemExp();
		int memChildTemp = memChild.getExpType();
		Operation instr;
		if(memChildTemp == AssemExp.MEM) {
			Temp memTemp = munchMemoryExp((AssemMemory) memChild);
			srcList.addElement(memTemp);
			instr = new Operation(destList, srcList, Operation.LOAD_ADDRESS, null, null);
		}
		else if(memChildTemp == AssemExp.CONST) {
			AssemConst assemConst = (AssemConst) memChild;
			int val = assemConst.getIntValue();
			Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ val);
			instr = new Operation(destList, srcList, Operation.LOAD_ADDRESS, immd, null);
		}
		else if(memChildTemp == AssemExp.CALL) {
			Temp callTemp = munchCallExp((AssemCallExp) memChild);
			srcList.addElement(callTemp);
			instr = new Operation(destList, srcList, Operation.LOAD_ADDRESS, null, 
					null);
		}
		else {
			// Must be temp
			AssemTemp assemTemp = (AssemTemp) memChild;
			srcList.addElement(assemTemp.getTemp());
			instr = new Operation(destList, srcList, Operation.LOAD_ADDRESS, null, null);

		}
		instructionsList.addElement(instr);
		
		return newTemp;
	}

	private Temp munchTempExp(AssemTemp tempExp)
	{
		return tempExp.getTemp();
	}

	private Temp addOrSubtractTemps(int op, Temp lhsTemp, Temp rhsTemp) {
		Temp newTemp = new Temp();
		updateWithTypeInfo(newTemp, findWiderValue(lhsTemp, rhsTemp));
		
		int oprCode = Operation.ADD;
		if(op != AssemBinOpExp.PLUS)
			oprCode = Operation.MINUS;

		Vector<Temp> destList = new Vector<Temp>();
		Vector<Operand> srcList = new Vector<Operand>();
		destList.addElement(newTemp);
		srcList.addElement(lhsTemp);
		srcList.addElement(rhsTemp);

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ 0);
		Operation instr = new Operation(destList, srcList, oprCode, immd, null);
		instructionsList.addElement(instr);
		
		return newTemp;
	}

	private void emitMoveMemOpTempConst(AssemExp srcExp, int signNum, 
			int num, Temp temp)
	{
		Temp srcTemp = munchExp(srcExp);

		Vector srcList = new Vector();
		srcList.addElement(temp);
		srcList.addElement(srcTemp);

		if(signNum == AssemBinOpExp.MINUS) num *= -1;

		Numeric immd = new Numeric(Numeric.INTEGER_TYPE, ""+ num);
		Operation instr = new Operation(null, srcList, Operation.STORE_WORD_INTO_ADDRESS_AT_TEMP,
				immd, null);
		instructionsList.addElement(instr);
	}

	/**
	 * Updates the temporary that is passed with the type properties that are passed in the second
	 * parameter.
	 * @param temp
	 * @param avp
	 */
	private void updateWithTypeInfo(Temp temp, AssemValueProperties avp){
		if(avp.isInteger()){
			temp.setInteger(true);
			temp.setIntegerSize(avp.getIntegerSize());
			temp.setSigned(!avp.isUnSigned());
		}
		else{
			temp.setInteger(false);
			temp.setFloatPrecision(avp.getFloatPrecision());
		}
	}

	private AssemValueProperties findWiderValue(Temp lhsTemp, Temp rhsTemp){
		AssemValueProperties avpRet = new AssemValueProperties();

		Temp widerTemp = lhsTemp;
		if(rhsTemp.isWiderThan(lhsTemp))
			widerTemp = rhsTemp;

		avpRet.setInteger(widerTemp.isInteger());
		avpRet.setIntegerSize(widerTemp.getIntegerSize());
		avpRet.setFloatPrecision(widerTemp.getFloatPrecision());

		return avpRet;

	}

	private AssemValueProperties findWiderValue(Temp lhsTemp, AssemConst assemConst){
		AssemValueProperties avpRet = new AssemValueProperties();

		if(lhsTemp.isInteger()){
			// The temp is an integer
			int tempIntegerSize = lhsTemp.getIntegerSize();
			if(assemConst.isInteger()){
				int constSize = assemConst.getConstantType();
				if(constSize == AssemConst.CONSTANT_LONG){
					if(tempIntegerSize < LONG_SIZE)
						return assemConst.getValueProperties();
				}
				else if(constSize > AssemConst.CONSTANT_INTEGER)
					if(tempIntegerSize < INTEGER_SIZE)
						return assemConst.getValueProperties();
					else
						if(tempIntegerSize < CHAR_SIZE)
							return assemConst.getValueProperties();
			}
			else{
				// The constant is a floating point; lets consider it wider
				return assemConst.getValueProperties();
			}
		}
		else{
			// The temp is a floating point value
			if(!assemConst.isInteger()){
				int constType = assemConst.getConstantType();
				if(constType == AssemConst.CONSTANT_DOUBLE){
					if(lhsTemp.getFloatPrecision() == FP_SINGLE_PRECISION)	
						return assemConst.getValueProperties();
				}
			}
		}

		// The temp is wider
		avpRet.setInteger(lhsTemp.isInteger());
		avpRet.setIntegerSize(lhsTemp.getIntegerSize());
		avpRet.setFloatPrecision(lhsTemp.getFloatPrecision());

		return avpRet;

	}

	/**
	 * Creates a type for an address type - assumes that address are unsigned integers.
	 * @return
	 */
	private AssemValueProperties createTypeForAddress(){
		AssemValueProperties avp = new AssemValueProperties();
		avp.setInteger(true);
		avp.setSigned(false);
		avp.setIntegerSize(INTEGER_SIZE);
		
		return avp;
	}
	

	public Vector<AssemblyInstruction> getInstructionsList() {
		return instructionsList;
	}

	public boolean isLeafProcedure() {
		return leafProcedure;
	}

	public void setLeafProcedure(boolean leafProcedure) {
		this.leafProcedure = leafProcedure;
	}
}
