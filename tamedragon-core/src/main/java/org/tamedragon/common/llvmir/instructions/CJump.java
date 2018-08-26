package org.tamedragon.common.llvmir.instructions;

import java.util.Vector;

// import com.compilervision.compiler.abstractassembly.AssemCJump;



import org.tamedragon.assemblyabstractions.constructs.AssemCJump;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;
public class CJump  implements AssemblyInstruction{

	public final static int EQ = 0;
	public final static int NE = 1;
	public final static int LT = 2;
	public final static int GT = 3;
	public final static int LE = 4;
	public final static int GE = 5;
	public final static int ULT = 6;
	public final static int ULE = 7;
	public final static int UGT = 8;
	public final static int UGE = 9;

	public static final int CJUMP_TYPE_TARGET_LABELS = 0;
	public static final int CJUMP_TYPE_TARGET_REGISTER_FLAG = 1;

	// TODO -> What are ULT, ULE, UGT, UGE? They are not being handled in the code..

	private int relop;
	private Vector<Operand> srcList;
	private String ifLabel;
	private String elseLabel;

	private Numeric immediateValue;


	public CJump(Vector<Operand> srcList, String ifLabel, String falseLabel, int relop,
			Numeric immediateValue){
		this.srcList = srcList;
		this.ifLabel = ifLabel;
		this.elseLabel = falseLabel;
		this.relop = relop;
		this.immediateValue = immediateValue;
	}

	public void updateSourceList(int index, Operand operand){
		if(index < srcList.size()){
			srcList.set(index, operand);
		}
	}

	/**
	 * Reverses the condition while preserving the semantics. 
	 * For example, "bgt $t0, $t1, L5 (else L6) " 
	 * will be converted to "
	 */

	public void setConverse(){

		int newRelop = -1;
		if(relop == CJump.EQ){
			newRelop = CJump.NE;
		}
		else if(relop == CJump.NE){
			newRelop = CJump.EQ;
		}
		else if(relop == CJump.LT){
			newRelop = CJump.GE;
		}
		else if(relop == CJump.GT){
			newRelop = CJump.LE;
		}
		else if(relop == CJump.LE){
			newRelop = CJump.GT;
		}
		else if(relop == CJump.GE){
			newRelop = CJump.LT;
		}

		relop = newRelop;

		// If this instruction has two temps as source, swap the if-else labels
		String temp = ifLabel;
		ifLabel = elseLabel;
		elseLabel = temp;

		// Regenerate instruction
	}


	/**
	 * 
	 */

	public String getPathTakenForValue(Numeric value){
		if(relop == CJump.EQ){
			if(value.performCheck(immediateValue, Numeric.EQ)) return ifLabel;
		}
		else if(relop == CJump.NE){
			if(value.performCheck(immediateValue, Numeric.NE)) return ifLabel;
		}
		else if(relop == CJump.LT){
			if(value.performCheck(immediateValue, Numeric.LT)) return ifLabel;
		}
		else if(relop == CJump.GT){
			if(value.performCheck(immediateValue, Numeric.GT)) return ifLabel;
		}
		else if(relop == CJump.LE){
			if(value.performCheck(immediateValue, Numeric.LE)) return ifLabel;
		}
		else if(relop == CJump.GE){
			if(value.performCheck(immediateValue, Numeric.GE)) return ifLabel;
		}
		return elseLabel;
	}

	public Vector<Operand> getSrcList(){
		return srcList;
	}

	public Vector<Temp> getDestList(){
		return null;
	}

	public Vector<String> getJumps(){
		Vector<String> lblList = new Vector<String>();
		lblList.addElement(ifLabel);
		lblList.addElement(elseLabel);
		return lblList;
	}

	public String getLabelStr(){
		return null;
	}

	public int getType(){
		return AssemblyInstruction.CJUMP;
	}

	public boolean isMove(){
		return false;
	}

	public String getTrueLabel(){ return ifLabel; }
	public String getFalseLabel(){ return elseLabel; }
	public int getRelOp(){ return relop;}
	public Numeric getImmediateValue(){ return immediateValue;}

	/**
	 * Returns the temp at the index that is passed. If it is not a temporary, and a numeric value
	 * at that index, it returns a null
	 */

	public Temp getTempOperandAtIndex(int index){
		if(index >= srcList.size())
			return null;

		Operand operand = srcList.get(index);
		if(operand == null)
			return null;

		if(operand.getOperandType() == Operand.TEMP_TYPE)
			return (Temp) operand;

		return null;
	}

	/**
	 * Returns the numeric at the index that is passed. If it is not a numeric, and a temp value
	 * at that index, it returns a null
	 */

	public Numeric getNumericOperandAtIndex(int index){

		if(index >= srcList.size())
			return null;

		Operand operand = srcList.get(index);
		if(operand == null)
			return null;

		if(operand.getOperandType() == Operand.NUMERIC_TYPE)
			return (Numeric) operand;

		return null;
	}

	/**
	 * Returns true if the one of the operands of this conditional jump has floating point values.
	 * The operands can include at least one of the source lists and the numeric value
	 * @return
	 */
	public boolean hasFloatingPointOperands(){
		Operand opr1 = srcList.elementAt(0);

		if(opr1.getOperandType() == Operand.NUMERIC_TYPE){
			Numeric numeric1 = (Numeric) opr1;
			if(!numeric1.isInteger())
				return true;
		}
		else{
			Temp temp1 = (Temp) opr1;
			if(!temp1.isInteger())
				return true;
		}

		if(srcList.size() == 2){
			Operand opr2 = srcList.elementAt(1);
			if(opr2.getOperandType() == Operand.NUMERIC_TYPE){
				Numeric numeric2 = (Numeric) opr2;
				if(!numeric2.isInteger())
					return true;
			}
			else{
				Temp temp2 = (Temp) opr2;
				if(!temp2.isInteger())
					return true;
			}
		}

		if(immediateValue != null){
			if(!immediateValue.isInteger())
				return true;
		}

		return false;
	}

	public String toString(){
		if(immediateValue != null){
			// Comparison with a constant value
			Operand opr = (Operand) srcList.elementAt(0);   // Only one temp in the statement			
			return getCJumpString(relop, opr, immediateValue, ifLabel);
		}
		else{
			// Comparison with two temporaries
			Operand lhsOpr = (Operand) srcList.elementAt(0);
			Operand rhsOpr = (Operand) srcList.elementAt(1);
			return getCJumpString(relop, lhsOpr.toString(), rhsOpr.toString(), ifLabel);
		}
	}

	private String getCJumpString(int op, Operand src1, Numeric immediateValue,
			String ifLabel){
		String branchOnStr = "";

		int numericType = immediateValue.getType();
		if(numericType == Numeric.INTEGER_TYPE){
			int value = Integer.parseInt(immediateValue.toString());
			if(op == AssemCJump.EQ || op == AssemCJump.NE){
				if(value == 0){
					if(op == AssemCJump.EQ) 
						branchOnStr = EnvironmentConstants.TAB + "beqz " + src1.toString() + ", " + 
						ifLabel + EnvironmentConstants.NEWLINE;
					else 
						branchOnStr = EnvironmentConstants.TAB + "bnez " + src1.toString() + ", " 
						+ ifLabel + EnvironmentConstants.NEWLINE;
				}
				else {
					branchOnStr = getCJumpString(op, src1.toString(), ""+value, ifLabel);
				}
			}
			else{
				if(value == 0){
					if(op == AssemCJump.GT)
						branchOnStr = EnvironmentConstants.TAB + "bgtz " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else if(op == AssemCJump.GE)
						branchOnStr = EnvironmentConstants.TAB + "bgez " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else if(op == AssemCJump.LT)
						branchOnStr = EnvironmentConstants.TAB + "bltz " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
					else 
						branchOnStr = EnvironmentConstants.TAB + "blez " + src1.toString() + ", " + 
						ifLabel.toString() + EnvironmentConstants.NEWLINE;
				}
				else{
					branchOnStr = getCJumpString(op, src1.toString(), ""+ value, ifLabel);
				}
			}
		}
		else if(numericType == immediateValue.FLOAT_TYPE){
			// TODO - Implement this
			float value = Float.parseFloat(immediateValue.toString());	
		}
		else{ // Double type
			// TODO - Implement this
			double value = Double.parseDouble(immediateValue.toString());
		}				

		return branchOnStr;
	}

	private String getCJumpString(int op, String src1, String src2, String ifLabel){
		String branchOnStr = "";
		if(op == AssemCJump.EQ) 
			branchOnStr = EnvironmentConstants.TAB + "beq " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.NE)
			branchOnStr = EnvironmentConstants.TAB + "bne " + src1.toString() + ", " 
			+ src2 + ", " +  ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.GT)
			branchOnStr = EnvironmentConstants.TAB + "bgt " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.GE)
			branchOnStr = EnvironmentConstants.TAB + "bge " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else if(op == AssemCJump.LT)
			branchOnStr = EnvironmentConstants.TAB + "blt " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;
		else 
			branchOnStr = EnvironmentConstants.TAB + "ble " + src1.toString() + ", "  
			+ src2 + ", " + ifLabel + EnvironmentConstants.NEWLINE;

		return branchOnStr;
	}

}
