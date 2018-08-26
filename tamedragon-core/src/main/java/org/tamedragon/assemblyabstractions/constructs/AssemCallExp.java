package org.tamedragon.assemblyabstractions.constructs;

import java.util.Stack;
import java.util.Vector;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;

public class AssemCallExp extends AssemExp {
	private AssemExp func;
	private AssemExpList args;

	private Vector<AssemTemp> uses;
	private AssemTemp defTemp;

	public Vector<AssemTemp> getUses() {
		return uses;
	}

	public void setUses(Vector<AssemTemp> uses) {
		this.uses = uses;
	}

	public AssemTemp getDefTemp() {
		return defTemp;
	}

	public void setDefTemp(AssemTemp defTemp) {
		this.defTemp = defTemp;
	}

	public AssemCallExp(){
		uses = new Vector<AssemTemp>();
	}
	
	public AssemCallExp(AssemExp f,  AssemExpList a, AssemTemp def) {
		func = f;
		args = a;
		this.defTemp = def;
		uses = new Vector<AssemTemp>();
	}

	public AssemExpList children() {
		return new AssemExpList(func,args);
	}

	public AssemExp getFunction() {
		return func;
	}

	public AssemExpList getArgs() {
		return args;
	}

	public int getAssemTypeType() {
		return AssemType.VALUE_TYPE;
	}

	public String getName() {
		AssemName assemName = (AssemName)func;
		return assemName.getLabel().toString();
	}

	public AssemType translateToCJump(Label testLabel, Label endLabel, boolean isNegation) {
		// Move this value to a temporary
		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());
		AssemMove assemMov = new AssemMove(assemTemp, this);

		// Create a constant 0
		AssemConst assemConst0 = (AssemConst) new AssemConst(0);

		// Create a conditional based on whether the above temporary is 1 or 0
		AssemCJump tempCJump = null;
		if(!isNegation)
			tempCJump = new AssemCJump(AssemCJump.GT, assemTemp, assemConst0,
					testLabel, endLabel);
		else
			tempCJump = new AssemCJump(AssemCJump.LTE, assemTemp, assemConst0,
					testLabel, endLabel);

		Stack<AssemType> seqStack = new Stack<AssemType>();
		seqStack.push(assemMov);
		seqStack.push(tempCJump);

		return translateSeqStatement(seqStack);
	}

	public AssemType translateToIntType() {
		// Create a new temporary to hold this constant
		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());

		// Move this value into the temporary and return the seq-exp
		AssemMove assemMove = new AssemMove(assemTemp, this);

		return new AssemSeqExp(assemMove, assemTemp);

	}

	public String getDescription() {
		return "call (" + func.getDescription() + ")";
	}

	public AssemExp build(AssemExpList list) {
		AssemCallExp newCallExp = new AssemCallExp(list.getHead(), list.getTail(), defTemp);
		newCallExp.setUses(uses);
		return newCallExp;
	}

	public int getNumIncomingParams(){
		int numIncomingParams = 0;
		for(AssemExpList tail = args; tail != null; tail = tail.getTail())
			numIncomingParams++;

		return numIncomingParams;

	}

	public AssemSeqExp canonize() {
		// Canonize the arguments first
		Vector<AssemSeqExp> expStms = new Vector<AssemSeqExp>();
		for(AssemExpList tail = args; tail != null; tail = tail.getTail())
		{
			AssemSeqExp seqExp = tail.getHead().canonize();
			expStms.addElement(seqExp);
		}

		StmExpList stmExpList = getStmExpList(expStms);
		AssemStm stm = stmExpList.getStm();
		AssemExpList currentArgs = stmExpList.getExps();

		// Add the statements for moving the expressions into $a0 - $a3 or onto to the stack
		// if there are more than four arguments
		Stack<AssemType> expArgStack = new Stack<AssemType>();
		Stack<AssemExp> tempsInRegisterStack = new Stack<AssemExp>();
		int count = 0;

		for(AssemExpList tail = currentArgs; tail != null; tail = tail.getTail()) {
			AssemExp exp = tail.getHead();
			Temp tmp = new Temp();

			AssemType assemType = translateTemp(tmp, exp.getValueProperties());
			AssemType assemMove = translateMove(assemType, exp);

			expArgStack.push(assemMove);

			tempsInRegisterStack.push((AssemExp) assemType);
			count++;

			uses.add((AssemTemp) assemType);

		}
		
		AssemSeq initSeq = translateSeqStatement(expArgStack);
		stm = new AssemSeq(stm, initSeq);

		// Canonize the function itself
		Temp temp = new Temp();
		AssemTemp assemTemp = new AssemTemp(temp, getValueProperties());

		// Create a new argument list based on the parameters that can be passed 
		// in machine registers
		AssemExpList newArgs = null;
		while(!tempsInRegisterStack.empty()){
			AssemExp tempExp = tempsInRegisterStack.pop();
			newArgs = new AssemExpList(tempExp, newArgs);
		}

		// Move this value into the temporary and return the seq-exp
		AssemCallExp newCallExp = new AssemCallExp(func, newArgs, defTemp);
		newCallExp.setUses(uses);
		AssemMove assemMove = new AssemMove(assemTemp, newCallExp);
		
		// Final canonize
		AssemSeqExp retValue = new AssemSeqExp(new AssemSeq(stm, assemMove), assemTemp);
		return retValue;
	}

	public AssemSeq translateSeqStatement(Stack<AssemType> seq)
	{
		AssemSeq retAssemType = null;

		while(!seq.isEmpty())
		{
			AssemType temp = (AssemType)seq.pop();
			// If there is an expression in this sequence, coerce it to a statement,
			// since we are building a list of statements 
			if(temp instanceof AssemExp)
			{
				AssemExp exp = (AssemExp) temp;
				temp = exp.translateToStatement();
			}
			retAssemType  = new AssemSeq((AssemStm)temp, (AssemStm)retAssemType);
		}

		return retAssemType;
	}

	private StmExpList getStmExpList(Vector<AssemSeqExp> expStms)
	{
		int numArgs = expStms.size();

		AssemSeq seqStm = null;
		Stack<AssemExp> expStack = new Stack<AssemExp>();
		for(int i = 0; i < numArgs; i++)
		{
			AssemSeqExp seqExp = (AssemSeqExp) expStms.elementAt(i);
			AssemStm stm = seqExp.getStm();
			AssemExp exp = seqExp.getExp();
			if(seqStm == null)
			{
				// Entered loop for the first time
				seqStm = new AssemSeq(stm, null);
			}
			else
			{
				// Check if the current stm commutes with the previous exp
				// Get the previous exp
				AssemExp prevExp = (AssemExp)expStack.pop();
				if(commutes(stm, prevExp))
				{
					seqStm = new AssemSeq(seqStm, stm);
				}
				else
				{
					Temp temp = new Temp();
					AssemTemp assemTemp = new AssemTemp(temp, prevExp.getValueProperties());
					AssemMove moveStm = new AssemMove(assemTemp, prevExp);
					seqStm = new AssemSeq(seqStm, new AssemSeq(moveStm, stm));
					prevExp = assemTemp;
				}
				//Push back the prev. exp
				expStack.push(prevExp);
			}
			expStack.push(exp);
		}

		// Create the expList from the stack created in the loop above
		AssemExpList expList = null;
		while(!expStack.isEmpty())
		{
			AssemExp exp = (AssemExp) expStack.pop();
			expList = new AssemExpList(exp, expList);
		}

		StmExpList stmExpList = new StmExpList(seqStm, expList);
		return stmExpList;
	}

	public boolean commutes(AssemStm stm, AssemExp exp)
	{
		return (stm == null) || (exp instanceof AssemName) || (exp instanceof AssemConst);
	}

	public int getExpType()
	{
		return AssemExp.CALL;
	}

	//public Stack getPostCallStmStack() {
	//	return postCallStmStack;
	//}

	//	 TODO - these functions exist in TranslatingMediator too ... how to get rid of it?
	public AssemType translateMove(AssemType dst, AssemType src)
	{
		return new AssemMove((AssemExp)dst, (AssemExp)src);
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

	public AssemType translateConstant(int i)
	{
		return new AssemConst(i);
	}

	public AssemType translateBinOp(int operType, AssemType assemTypeLeft, AssemType assemTypeRight)
	{
		return new AssemBinOpExp(operType, (AssemExp) assemTypeLeft,
				(AssemExp)  assemTypeRight);
	}

	public AssemValueProperties getValueProperties(){		
		return func.getValueProperties();
	}

	public Vector<Operand> getUsesTemp(){
		Vector<Operand> usesTemp = new Vector<Operand>();
		if(uses == null){
			return usesTemp;
		}
		usesTemp = new Vector<Operand>();
		for(AssemTemp assemTemp : uses){
			usesTemp.add(assemTemp.getTemp());
		}
		
		return usesTemp;
	}

}

class StmExpList
{
	private AssemStm stm;
	private AssemExpList exps;

	public StmExpList(AssemStm stm, AssemExpList exps)
	{
		this.stm = stm;
		this.exps = exps;
	}

	public AssemExpList getExps() {
		return exps;
	}

	public void setExps(AssemExpList exps) {
		this.exps = exps;
	}

	public AssemStm getStm() {
		return stm;
	}

	public void setStm(AssemStm stm) {
		this.stm = stm;
	}
}
