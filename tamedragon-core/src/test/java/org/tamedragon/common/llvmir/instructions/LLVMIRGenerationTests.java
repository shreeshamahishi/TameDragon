package org.tamedragon.common.llvmir.instructions;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeArgPassStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeBinaryExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConst;
import org.tamedragon.assemblyabstractions.constructs.IRTreeDeclaration;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMemory;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMove;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturn;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeTempOrVar;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.instructions.LLVMIRGenerator;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;

public class LLVMIRGenerationTests {

	private CompilationContext compilationContext;
	private LLVMIRGenerator llvmirGenerator;
	
	
		/*
	
	@Before
	public void setUp(){
		compilationContext = new CompilationContext();
		llvmirGenerator = new LLVMIRGenerator(compilationContext);
		ValueName.resetTempCount();
	}
	
	@Test
	public void testSimpleFunctionEmit(){

		// CREATE THE VALUES
		Value p1Value = new Value(Type.getInt32Type(compilationContext)); p1Value.setName("%a");
		Value p2Value = new Value(Type.getInt32Type(compilationContext)); p2Value.setName("%b");
		Value dValue = new Value(Type.getDoubleType(compilationContext)); dValue.setName("%d");
		Value mValue = new Value (Type.getInt32Type(compilationContext));  mValue.setName("%m");
		Value nValue = new Value (Type.getInt32Type(compilationContext)); nValue.setName("%n");
		Value pValue = new Value (Type.getInt32Type(compilationContext)); pValue.setName("%p");
		Value qValue = new Value (Type.getInt32Type(compilationContext)); qValue.setName("%q");
		IRTreeTempOrVar p1ValueTree = new IRTreeTempOrVar(p1Value);
		IRTreeTempOrVar p2ValueTree = new IRTreeTempOrVar(p2Value);

		// CREATE THE ARGUMENT PASS VALUES
		IRTreeArgPassStm argPassTree1 = createArgPassTree(p1ValueTree);
		IRTreeArgPassStm argPassTree2 = createArgPassTree(p2ValueTree);

		// CREATE THE CONSTANTS
		ConstantInt constInt1 = null, constInt2 = null, constInt3 = null, constInt4 = null;

		ConstantFP constFP1 = null, constFP2 = null;
		try{
			constInt1 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "10", true));
			constInt2 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "20", true));
			constInt3 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "3", true));
			constInt4 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "12", true));
			constFP1 = new ConstantFP(Type.getDoubleType(compilationContext), new APFloat(APFloat.IEEEdouble, "20.0"));
			constFP2 = new ConstantFP(Type.getDoubleType(compilationContext), new APFloat(APFloat.IEEEdouble, "40.0"));
		}
		catch(InstantiationException ie){ }
		catch(InstructionCreationException ice){ }
		assertNotNull(constInt1);
		assertNotNull(constInt2);
		assertNotNull(constInt3);
		assertNotNull(constInt4);
		assertNotNull(constFP1);
		assertNotNull(constFP2);

		try{
			constInt1 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "10", true));
			constInt2 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "20", true));
			constInt3 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "3", true));
			constInt4 = new ConstantInt(IntegerType.getInt32Type(compilationContext), new APInt(32, "12", true));
		}
		catch(InstantiationException ie){ }

		// CREATE THE DECLARATION NODES
		IRTreeDeclaration decl3 = createDeclarationTree(dValue, null);

		IRTreeDeclaration decl4 = createDeclarationTree(mValue, 
				new IRTreeConst(constInt1));
		IRTreeDeclaration decl5 = createDeclarationTree(nValue, 
				new IRTreeConst(constInt2));
		IRTreeDeclaration decl6 = createDeclarationTree(pValue, 
				new IRTreeConst(constInt3));
		IRTreeDeclaration decl7 = createDeclarationTree(qValue, 
				new IRTreeConst(constInt4));

		// CREATE CONDITIONAL TREE NODE
		// 1. Start with the condition
		IRTreeBinaryExp binOpLeft = new IRTreeBinaryExp(BinaryOperatorID.ADD,
				argPassTree1.getMemory(), decl4.getMemoryTree());
		IRTreeBinaryExp binOpRight = new IRTreeBinaryExp(BinaryOperatorID.SUB, 
				argPassTree1.getMemory(), decl5.getMemoryTree());

		// 2. Create the basic blocks for the conditional
		// 2.a The first statement for the true bb		
		IRTreeBinaryExp binExp1 = new IRTreeBinaryExp(BinaryOperatorID.ADD, 
				decl6.getMemoryTree(), decl7.getMemoryTree()); 
		IRTreeMove mv1 = new IRTreeMove(decl5.getMemoryTree(), binExp1);
		// 2.b The second statement for the true bb
		IRTreeConst constExp1 = new IRTreeConst(constFP1);
		IRTreeMove mv2 = new IRTreeMove(decl3.getMemoryTree(), constExp1);
		// 2.c Create the statement list for the true bb and create the true basic block
		IRTreeStatementList bbTrueStmListChild1 = new IRTreeStatementList(mv1, null);
		IRTreeStatementList bbTrueStmList = new IRTreeStatementList(mv2, bbTrueStmListChild1);
		// 2.d The first statement for the false bb		
		IRTreeBinaryExp binExp2 = new IRTreeBinaryExp(BinaryOperatorID.MUL, 
				decl6.getMemoryTree(), decl7.getMemoryTree()); 
		IRTreeMove mv3 = new IRTreeMove(decl5.getMemoryTree(), binExp2);
		// 2.e The second statement for the false bb
		IRTreeConst constExp2 = new IRTreeConst(constFP2);
		IRTreeMove mv4 = new IRTreeMove(decl3.getMemoryTree(), constExp2);
		// 2.f Create the statement list for the false bb and create the false basic block
		IRTreeStatementList bbFalseStmListChild1 = new IRTreeStatementList(mv3, null);
		IRTreeStatementList bbFalseStmList = new IRTreeStatementList(mv4, bbFalseStmListChild1);

		// 3. The actual conditional branch
		IRTreeConditionalBranch conditionalBranch = 
			new IRTreeConditionalBranch(Predicate.ICMP_SGT, binOpLeft, binOpRight, 
					bbTrueStmList, bbFalseStmList);
		
		// The final return statement
		IRTreeReturn irTreeRet = new IRTreeReturn(decl3.getMemoryTree());
		
		IRTreeStatementList finalList0 = new IRTreeStatementList(irTreeRet, null);
		IRTreeStatementList finalList1 = new IRTreeStatementList(conditionalBranch, finalList0);
		IRTreeStatementList finalList2 = new IRTreeStatementList(decl7, finalList1);
		IRTreeStatementList finalList3 = new IRTreeStatementList(decl6, finalList2);
		IRTreeStatementList finalList4 = new IRTreeStatementList(decl5, finalList3);
		IRTreeStatementList finalList5 = new IRTreeStatementList(decl4, finalList4);
		IRTreeStatementList finalList6 = new IRTreeStatementList(decl3, finalList5);
		IRTreeStatementList finalList7 = new IRTreeStatementList(argPassTree2, finalList6);
		IRTreeStatementList finalList = new IRTreeStatementList(argPassTree1, finalList7);

		llvmirGenerator.generateCodeForBasicBlock(null, finalList);

		List<String> instructions = llvmirGenerator.emitInstructions();
		print(instructions);
	}
	
	@Test
	public void testSimpleNestedIfElse(){
		Value argcValue = new Value(Type.getInt32Type(compilationContext)); argcValue.setName("%argc");
		//creating pointer to a pointer
		PointerType pointerType;
		PointerType pointerType2 = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext), 0);
			pointerType2 = Type.getPointerType(compilationContext, pointerType, 0);
		} catch (TypeCreationException e) {}
		assertNotNull(pointerType2);
		Value argvValue = new Value(pointerType2); argvValue.setName("%argv");
		Value iValue = new Value(Type.getInt32Type(compilationContext)); iValue.setName("%i");
		Value kValue = new Value(Type.getInt32Type(compilationContext)); kValue.setName("%k");
		
		IRTreeTempOrVar argcValueTree = new IRTreeTempOrVar(argcValue);
		IRTreeTempOrVar argvValueTree = new IRTreeTempOrVar(argvValue);
		
		IRTreeArgPassStm argcTreeArgStm = createArgPassTree(argcValueTree);
		IRTreeArgPassStm argvTreeArgStm = createArgPassTree(argvValueTree);
		
		ConstantInt const10  = null;
		ConstantInt const20  = null;
		ConstantInt const9 = null;
		ConstantInt const2 = null;
		ConstantInt const30 = null;
		try {
			const10 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "10", true));
			const20 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "20", true));
			const9 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "9", true));
			const2 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "2", true));
			const30 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "30", true));
		} 
		catch (InstantiationException e) {}
		assertNotNull(const10);
		assertNotNull(const20);
		assertNotNull(const9);
		assertNotNull(const2);
		assertNotNull(const30);
		
		IRTreeConst const10Tree = new IRTreeConst(const10);
		IRTreeConst const20Tree = new IRTreeConst(const20);
		IRTreeConst const9Tree = new IRTreeConst(const9);
		IRTreeConst const2Tree = new IRTreeConst(const2);
		IRTreeConst const30Tree = new IRTreeConst(const30);
		
		IRTreeDeclaration declI = createDeclarationTree(iValue, const10Tree);
		IRTreeDeclaration declK = createDeclarationTree(kValue, const2Tree);
		
		//creating BinaryExpr
		IRTreeBinaryExp binaryExpTree = new IRTreeBinaryExp(BinaryOperatorID.ADD, declI.getMemoryTree(), declK.getMemoryTree());
		
		//creating the outer if else block
			//creating outer true block
				//creating inner true block
				IRTreeMove innerTrueMove = new IRTreeMove(declK.getMemoryTree(), const10Tree);
				IRTreeStatementList innerTrueStmtList = new IRTreeStatementList(innerTrueMove, null);
				//creating inner false block
				IRTreeMove innerFalseMove = new IRTreeMove(declK.getMemoryTree(), const20Tree);
				IRTreeStatementList innerFalseStmtList = new IRTreeStatementList(innerFalseMove, null);
				IRTreeConditionalBranch innerCondBr = new IRTreeConditionalBranch(Predicate.ICMP_SGT, binaryExpTree, const10Tree, innerTrueStmtList, innerFalseStmtList);
			IRTreeStatementList outertrueStmtList = new IRTreeStatementList(innerCondBr, null);
			//creating outer false block
			IRTreeMove outerFalseMove = new IRTreeMove(declK.getMemoryTree(), const30Tree);
			IRTreeStatementList outerFalseStmtList = new IRTreeStatementList(outerFalseMove, null);
		IRTreeConditionalBranch outerCondBr = new IRTreeConditionalBranch(Predicate.ICMP_SGT, binaryExpTree, const9Tree, outertrueStmtList, outerFalseStmtList);
		
		//creating return statement
		IRTreeReturn returnStmt = new IRTreeReturn(declK.getMemoryTree());
		IRTreeStatementList stmtList1 = new IRTreeStatementList(returnStmt, null);
		IRTreeStatementList stmtList2 = new IRTreeStatementList(outerCondBr, stmtList1);
		IRTreeStatementList stmtList3 = new IRTreeStatementList(declK, stmtList2);
		IRTreeStatementList stmtList4 = new IRTreeStatementList(declI, stmtList3);
		IRTreeStatementList stmtList5 = new IRTreeStatementList(argvTreeArgStm, stmtList4);
		IRTreeStatementList stmtList6 = new IRTreeStatementList(argcTreeArgStm, stmtList5);
		
		llvmirGenerator.generateCodeForBasicBlock(null, stmtList6);

		List<String> instructions = llvmirGenerator.emitInstructions();
		print(instructions);
	}
	
	@Test
	public void testSimpleIfElseIntegerType(){
		Value aValue = new Value(Type.getInt32Type(compilationContext)); aValue.setName("%a");
		Value bValue = new Value(Type.getInt32Type(compilationContext)); bValue.setName("%b");
		Value cValue = new Value(Type.getInt32Type(compilationContext)); cValue.setName("%c");
		
		ConstantInt const10 = null;
		ConstantInt const20 = null;
		try{
			const10 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "10", true));
			const20 = new ConstantInt(Type.getInt32Type(compilationContext), new APInt(32, "20", true));
		}
		catch(InstantiationException e){}
		assertNotNull(const10);
		assertNotNull(const20);
		
		IRTreeConst const10Tree = new IRTreeConst(const10);
		IRTreeConst const20Tree = new IRTreeConst(const20);
		
		IRTreeDeclaration decA = createDeclarationTree(aValue, const10Tree);
		IRTreeDeclaration decB = createDeclarationTree(bValue, const20Tree);
		IRTreeDeclaration decC = createDeclarationTree(cValue, null);
		
		IRTreeMove moveCtoB = new IRTreeMove(decC.getMemoryTree(), decB.getMemoryTree());
		IRTreeStatementList trueStmList = new IRTreeStatementList(moveCtoB, null);
		
		IRTreeMove move20toC = new IRTreeMove(decC.getMemoryTree(), const20Tree);
		IRTreeStatementList falseStmList = new IRTreeStatementList(move20toC, null);
		
		IRTreeConditionalBranch condBr = new IRTreeConditionalBranch(Predicate.ICMP_SGT, decA.getMemoryTree(), decB.getMemoryTree(), trueStmList, falseStmList);
		
		IRTreeReturn returnStmt = new IRTreeReturn(decC.getMemoryTree());
		IRTreeStatementList stmtList5 = new IRTreeStatementList(returnStmt, null);
		IRTreeStatementList stmtList4 = new IRTreeStatementList(condBr, stmtList5);
		IRTreeStatementList stmtList3 = new IRTreeStatementList(decC, stmtList4);
		IRTreeStatementList stmtList2 = new IRTreeStatementList(decB, stmtList3);
		IRTreeStatementList stmtList1 = new IRTreeStatementList(decA, stmtList2);
		
		llvmirGenerator.generateCodeForBasicBlock(null, stmtList1);

		List<String> instructions = llvmirGenerator.emitInstructions();
		print(instructions);
	}
	
	@Test
	public void testSimpleIfElseFloatingPoint(){
		Value aValue = new Value(Type.getDoubleType(compilationContext)); aValue.setName("%a");
		Value bValue = new Value(Type.getDoubleType(compilationContext)); bValue.setName("%b");
		Value cValue = new Value(Type.getDoubleType(compilationContext)); cValue.setName("%c");
		
		ConstantFP const10_10 = null;
		ConstantFP const20_20 = null;
		try{
			const10_10 = new ConstantFP(Type.getDoubleType(compilationContext), new APFloat(APFloat.IEEEdouble, "10.10"));
			const20_20 = new ConstantFP(Type.getDoubleType(compilationContext), new APFloat(APFloat.IEEEdouble, "20.20"));
		}
		catch(InstructionCreationException e){}
		assertNotNull(const10_10);
		assertNotNull(const20_20);
		
		IRTreeConst const10Tree = new IRTreeConst(const10_10);
		IRTreeConst const20Tree = new IRTreeConst(const20_20);
		
		IRTreeDeclaration decA = createDeclarationTree(aValue, const10Tree);
		IRTreeDeclaration decB = createDeclarationTree(bValue, const20Tree);
		IRTreeDeclaration decC = createDeclarationTree(cValue, null);
		
		IRTreeMove moveCtoB = new IRTreeMove(decC.getMemoryTree(), decB.getMemoryTree());
		IRTreeStatementList trueStmList = new IRTreeStatementList(moveCtoB, null);
		
		IRTreeMove move20toC = new IRTreeMove(decC.getMemoryTree(), const20Tree);
		IRTreeStatementList falseStmList = new IRTreeStatementList(move20toC, null);
		
		IRTreeConditionalBranch condBr = new IRTreeConditionalBranch(Predicate.FCMP_OGT, decA.getMemoryTree(), decB.getMemoryTree(), trueStmList, falseStmList);
		
		IRTreeReturn returnStmt = new IRTreeReturn(decC.getMemoryTree());
		IRTreeStatementList stmtList5 = new IRTreeStatementList(returnStmt, null);
		IRTreeStatementList stmtList4 = new IRTreeStatementList(condBr, stmtList5);
		IRTreeStatementList stmtList3 = new IRTreeStatementList(decC, stmtList4);
		IRTreeStatementList stmtList2 = new IRTreeStatementList(decB, stmtList3);
		IRTreeStatementList stmtList1 = new IRTreeStatementList(decA, stmtList2);
		
		llvmirGenerator.generateCodeForBasicBlock(null, stmtList1);

		List<String> instructions = llvmirGenerator.emitInstructions();
		print(instructions);
	}

	private IRTreeDeclaration createDeclarationTree(Value value, 
			IRTreeExp initializer){

		Type containedType = value.getType();
		CompilationContext compilationContext = containedType.getCompilationContext();
		PointerType ptrType = new PointerType(compilationContext, containedType, 0);
		Value memoryValue = new Value(ptrType);
		memoryValue.setName(value.getName());

		IRTreeMemory memoryTree = new IRTreeMemory(memoryValue);

		IRTreeDeclaration nodeDecl =
			new IRTreeDeclaration(memoryTree, initializer);

		return nodeDecl;
	}

	private IRTreeArgPassStm createArgPassTree(IRTreeTempOrVar argValTree){

		Value val = argValTree.getValue();
		Type containedType = val.getType();
		CompilationContext compilationContext = containedType.getCompilationContext();
		PointerType ptrType = new PointerType(compilationContext, containedType, 0);
		Value newVal = new Value(ptrType);
		newVal.setName(ValueName.create().getName());
		IRTreeMemory memoryTree = new IRTreeMemory(newVal);

		IRTreeArgPassStm argPassTreeStm = new IRTreeArgPassStm(argValTree, memoryTree);
		return argPassTreeStm;
	}

	private void print(List<String> instructions){
		for(String ins : instructions){
			System.out.println(ins);
		}
	}
	*/
	
	
}
