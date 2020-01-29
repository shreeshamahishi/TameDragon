package org.tamedragon.compilers.clang.semantics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAggregateData;
import org.tamedragon.assemblyabstractions.constructs.IRTreeAlloca;
import org.tamedragon.assemblyabstractions.constructs.IRTreeArgPassStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeBinaryExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCallExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeCast;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalExpr;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConst;
import org.tamedragon.assemblyabstractions.constructs.IRTreeDeclaration;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp.TreeNodeExpType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpressionStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeFunction;
import org.tamedragon.assemblyabstractions.constructs.IRTreeIndex;
import org.tamedragon.assemblyabstractions.constructs.IRTreeLabel;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMemory;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMove;
import org.tamedragon.assemblyabstractions.constructs.IRTreePhiExpr;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturn;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturnAlloca;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement.TreeStatementType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeSwitch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeTempOrVar;
import org.tamedragon.assemblyabstractions.constructs.IRTreeType;
import org.tamedragon.assemblyabstractions.constructs.IRTreeUnConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeUnReachable;
import org.tamedragon.common.Label;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.math.APInt;
import org.tamedragon.common.llvmir.types.APFloat;
import org.tamedragon.common.llvmir.types.ArrayType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.ConstantArray;
import org.tamedragon.common.llvmir.types.ConstantExpr;
import org.tamedragon.common.llvmir.types.ConstantFP;
import org.tamedragon.common.llvmir.types.ConstantInt;
import org.tamedragon.common.llvmir.types.ConstantStruct;
import org.tamedragon.common.llvmir.types.FunctionType;
import org.tamedragon.common.llvmir.types.GlobalValue.LinkageTypes;
import org.tamedragon.common.llvmir.types.IntegerType;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.StructType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.compilers.clang.abssyntree.BinaryOpExpr;
import org.tamedragon.compilers.clang.abssyntree.JumpStatement;
import org.tamedragon.compilers.clang.abssyntree.PostfixExpr;

/**
 * This class represents a mediator that acts as a interface between the
 * semantic analyzer and the IR Tree. It will translate high-level constructs
 * like "for" loops, "if-else"s, "while" loops, etc.
 * @author ipsg
 */
public class Translator {

	public static final int CONSTANT_INT = 0;
	public static final int CONSTANT_SHORT_INT = 1;
	public static final int CONSTANT_LONG_INT = 2;
	public static final int CONSTANT_CHAR = 3;
	public static final int CONSTANT_FLOAT = 4;
	public static final int CONSTANT_DOUBLE = 5;
	public static final int CONSTANT_STRING = 6;

	public static final int CONSTANT_ATTRIBUTE_SIGNED = 0;
	public static final int CONSTANT_ATTRIBUTE_UNSIGNED = 1;

	public static int count_for_string_constants = 0;

	protected Map<IRTreeTempOrVar,IRTreeMemory> valVsMemoryMap;
	protected Map<IRTree, IRTreeLabel> caseValuesVsLabels;
	protected Stack<IRTreeExp> postfixExprStack;
	protected Stack<IRTree> translatingIndirectionStack;
	protected Label globalReturnLabel = null;
	protected Set<String> constantStrings;
	protected Map<String,IRTreeDeclaration> constStrVsDeclr;
	protected Set<IRTreeDeclaration> listOfConstArrDeclrs;
	protected boolean islastStatementAReturnStmt;
	protected IRTreeFunction irTreeLLVMMemCpyFunctionDeclr = null;
	protected IRTreeFunction irTreeLLVMMemSetFunctionDeclr = null;
	protected IRTreeFunction irTreeLLVMStackRestoreFunctionDeclr = null;
	protected IRTreeFunction irTreeLLVMStackSaveFunctionDeclr = null;
	protected Set<StructType> intermediateNamedTypes = null;
	protected Integer nosOfIntermediateNamedType;
	
	private Properties properties;

	protected Translator(Properties properties) {
		valVsMemoryMap = new HashMap<IRTreeTempOrVar, IRTreeMemory>();
		caseValuesVsLabels = new LinkedHashMap<IRTree, IRTreeLabel>();
		postfixExprStack = new Stack<IRTreeExp>();
		translatingIndirectionStack = new Stack<IRTree>();
		constantStrings = new HashSet<String>();
		constStrVsDeclr = new HashMap<String, IRTreeDeclaration>();
		listOfConstArrDeclrs = new HashSet<IRTreeDeclaration>();
		islastStatementAReturnStmt = true;
		nosOfIntermediateNamedType = 0;
		intermediateNamedTypes = new HashSet<StructType>();
		this.properties = properties;
	}

	public IRTree translateWhileExp(IRTree whileTestAssemType,
			IRTree whileBodyAssemType, boolean isDoWhile, CompilationContext compilationContext, 
			Label iterStartLabel, Label iterEndLabel) {
		IRTree assemType = null;
		Stack<IRTree> seqAssemTypes = new Stack<IRTree>();

		//create an unconditional jump from the previous basic block to the current basic block 
		//to mark the starting of while or do-while loop, and add it to the sequence of statement list
		IRTreeUnConditionalBranch brToWhileLoop = new IRTreeUnConditionalBranch(iterStartLabel);
		IRTreeStatementList brToWhileLoopStmtList = new IRTreeStatementList(brToWhileLoop, null);
		seqAssemTypes.push(brToWhileLoopStmtList);

		IRTreeLabel irTreeStartLabel = new IRTreeLabel(iterStartLabel);
		IRTreeStatementList labeltoMarkBegOfLoopStmtList = new IRTreeStatementList(irTreeStartLabel, null);
		seqAssemTypes.push(labeltoMarkBegOfLoopStmtList);

		IRTreeLabel irTreeLoopEndLabel = new IRTreeLabel(iterEndLabel);
		IRTreeStatementList labelOutOfLoopStmtList = new IRTreeStatementList(irTreeLoopEndLabel, null);

		Label label = new Label();
		IRTreeLabel irTreeLabel = new IRTreeLabel(label);
		IRTreeStatementList irTreeLabelStmtList = new IRTreeStatementList(irTreeLabel, null);


		if (!isDoWhile) {
			createConditionalBrInstr(label, iterEndLabel, compilationContext, whileTestAssemType, seqAssemTypes);
			seqAssemTypes.push(irTreeLabelStmtList);
			IRTreeStatementList bodyAssemStm = (IRTreeStatementList) whileBodyAssemType;
			seqAssemTypes.push(bodyAssemStm);
			//create a unconditional jump that is analogous to continue statement
			IRTreeUnConditionalBranch brToHeadOfLoop =  new IRTreeUnConditionalBranch(iterStartLabel);
			IRTreeStatementList brToHeadOfLoopStmtList = new IRTreeStatementList(brToHeadOfLoop, null);
			seqAssemTypes.push(brToHeadOfLoopStmtList);
			seqAssemTypes.push(labelOutOfLoopStmtList);
			assemType = translateSeqStatement(seqAssemTypes);
		}
		else{
			seqAssemTypes.push(whileBodyAssemType);

			IRTreeUnConditionalBranch brToDoWhileCondExpr = new IRTreeUnConditionalBranch(label);
			IRTreeStatementList brToDoWhileCondExprStmtList = new IRTreeStatementList(brToDoWhileCondExpr, null);
			seqAssemTypes.push(brToDoWhileCondExprStmtList);
			seqAssemTypes.push(irTreeLabelStmtList);
			createConditionalBrInstr(iterStartLabel, iterEndLabel, compilationContext, whileTestAssemType, seqAssemTypes);
			seqAssemTypes.push(labelOutOfLoopStmtList);
			assemType = translateSeqStatement(seqAssemTypes);
		}
		return assemType;
	}


	public IRTree translateForStmt(IRTree condExprTree,	IRTree initExprTree, IRTree incrExprTree,
			IRTree forStmtTree, CompilationContext compilationContext,
			Label iterStartLabel, Label iterEndLabel, Label incrLabel, IRTree returnValueTree, IRTree forDeclr) {

		Label forLoopStmtsLabel = new Label();
		IRTreeLabel forLoopStmtsLabelTree = new IRTreeLabel(forLoopStmtsLabel);
		IRTreeStatementList forLoopStmtLabelList = new IRTreeStatementList(forLoopStmtsLabelTree, null);
		Stack<IRTree> seqAssemTypes = new Stack<IRTree>();
		IRTreeUnConditionalBranch brToForLoop = new IRTreeUnConditionalBranch(iterStartLabel);
		IRTreeStatementList brToForLoopStmtList = new IRTreeStatementList(brToForLoop, null);
		IRTreeLabel irTreeStartLabel = new IRTreeLabel(iterStartLabel);
		IRTreeStatementList labeltoMarkBegOfLoop = new IRTreeStatementList(irTreeStartLabel, null);

		// Add to sequence of statements
		if (initExprTree != null || forDeclr != null){

			if(initExprTree != null)
				seqAssemTypes.push(initExprTree);
			else
				seqAssemTypes.push(forDeclr);

			seqAssemTypes.push(brToForLoopStmtList);
			seqAssemTypes.push(labeltoMarkBegOfLoop);
		}
		else if(condExprTree != null){
			seqAssemTypes.push(brToForLoopStmtList);
			seqAssemTypes.push(labeltoMarkBegOfLoop);
		}
		if (condExprTree != null){
			createConditionalBrInstr(forLoopStmtsLabel, iterEndLabel, compilationContext, condExprTree, seqAssemTypes);
		}
		//here we have to put an unconditional branch instr that is analogous to continue statement
		IRTreeUnConditionalBranch branchToIncrExpr = new IRTreeUnConditionalBranch(incrLabel);
		IRTreeStatementList branchToIncrExprStmtList = new IRTreeStatementList(branchToIncrExpr, null);

		IRTreeLabel labelBeToIncrExpr = new IRTreeLabel(incrLabel);
		IRTreeStatementList labelBeToIncrExprStmtList = new IRTreeStatementList(labelBeToIncrExpr, null);

		if (forStmtTree != null){
			if(condExprTree == null && (!((initExprTree != null || forDeclr != null)))){
				seqAssemTypes.push(brToForLoopStmtList);
				seqAssemTypes.push(labeltoMarkBegOfLoop);
			}
			else
				seqAssemTypes.push(forLoopStmtLabelList);
			// May be a return statement, so we have to convert it to a IRTreeStatementList
			if(!(forStmtTree instanceof IRTreeStatementList)){
				Stack<IRTree> seq = new Stack<IRTree>();
				seq.push(forStmtTree);
				forStmtTree = translateSeqStatement(seq);
			}
			IRTreeStatementList leafOfIfStmtList = getLeafStatementList((IRTreeStatementList)forStmtTree);
			if(leafOfIfStmtList != null){
				IRTreeStatement leafStmt = leafOfIfStmtList.getStatement();
				// If the leaf statement is a Return Statement then the 'branchToIncrExpr label' should go to the block containing return statement
				if(leafStmt instanceof IRTreeReturn){
					IRTreeStatementList newStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafOfIfStmtList, (IRTreeStatementList)forStmtTree);
					forStmtTree = handleMultipleReturnStmt(newStatementList, leafStmt, returnValueTree, branchToIncrExpr);
				}
			}
			seqAssemTypes.push(forStmtTree);
		}
		else if(condExprTree != null)
			seqAssemTypes.push(forLoopStmtLabelList);
		else{
			seqAssemTypes.push(brToForLoopStmtList);
			seqAssemTypes.push(labeltoMarkBegOfLoop);
		}

		if (incrExprTree != null){
			seqAssemTypes.push(branchToIncrExprStmtList);
			seqAssemTypes.push(labelBeToIncrExprStmtList);
			seqAssemTypes.push(incrExprTree);
		}

		IRTreeUnConditionalBranch brToForLoop2 = new IRTreeUnConditionalBranch(iterStartLabel);
		IRTreeStatementList brToForLoopStmtList2 = new IRTreeStatementList(brToForLoop2, null);
		seqAssemTypes.push(brToForLoopStmtList2);
		IRTreeLabel irTreeLabel2 = new IRTreeLabel(iterEndLabel);
		IRTreeStatementList irTreeStatementList = new IRTreeStatementList(irTreeLabel2, null);
		seqAssemTypes.push(irTreeStatementList);
		IRTree assemSeq = translateSeqStatement(seqAssemTypes);
		return assemSeq;
	}


	public IRTree translateSwitchStm(IRTree switchExprTree,
			IRTree switchBodyTree, Label switchEndLabel) {
		Stack<IRTree> seqAssemTypes = new Stack<IRTree>();

		IRTreeLabel defaultLabel = null;
		IRTreeLabel switchEndLbl = new IRTreeLabel(switchEndLabel);

		// Get the default Label
		Set<Entry<IRTree, IRTreeLabel>> entries = caseValuesVsLabels.entrySet();
		Iterator<Entry<IRTree, IRTreeLabel>> iterator = entries.iterator();
		while(iterator.hasNext()){
			Entry<IRTree, IRTreeLabel> entry = iterator.next();
			if(entry.getKey() == null){
				defaultLabel = entry.getValue();
				break;
			}
		}
		// Switch statement doesn't have the body, then the default label will jump to the end of the switch statement
		if(caseValuesVsLabels.isEmpty() && switchBodyTree == null){
			defaultLabel = switchEndLbl;
		}
		// If the switch statement doesn't contain default block, but it contains only case blocks
		if(defaultLabel == null){
			defaultLabel = switchEndLbl;
		}

		IRTreeSwitch irTreeSwitch = new IRTreeSwitch(switchExprTree, defaultLabel, caseValuesVsLabels);
		seqAssemTypes.push(irTreeSwitch);
		// If Switch statement have body
		if(switchBodyTree != null){
			switchBodyTree = implementFallthroughIfRequired(switchBodyTree);
			seqAssemTypes.push(switchBodyTree);
			IRTreeUnConditionalBranch unCondBranch = new IRTreeUnConditionalBranch(switchEndLabel);
			seqAssemTypes.push(unCondBranch);
		}

		seqAssemTypes.push(switchEndLbl);
		IRTree assemSeq = translateSeqStatement(seqAssemTypes);
		caseValuesVsLabels = new LinkedHashMap<IRTree, IRTreeLabel>();
		return assemSeq;
	}

	private IRTree implementFallthroughIfRequired(IRTree switchBodyTree) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		IRTreeStatementList irTreeStatementList = (IRTreeStatementList)switchBodyTree;
		IRTreeStatementList prevStmtList = null;
		while(irTreeStatementList != null){
			IRTreeStatement irTreeStatement = irTreeStatementList.getStatement();
			if(irTreeStatement instanceof IRTreeLabel && prevStmtList != null){
				IRTreeLabel irTreeLabel = (IRTreeLabel)irTreeStatement;
				Label targetBlock = irTreeLabel.getLabel();
				IRTreeStatement irTreeStatement2 = prevStmtList.getStatement();
				if(!(irTreeStatement2 instanceof IRTreeUnConditionalBranch) && !(irTreeStatement2 instanceof IRTreeUnReachable)){
					IRTreeUnConditionalBranch unConditionalBranch = new IRTreeUnConditionalBranch(targetBlock);
					irTreeStack.push(unConditionalBranch);
				}
			}
			prevStmtList = irTreeStatementList;
			if(irTreeStatement != null)
				irTreeStack.push(irTreeStatement);
			irTreeStatementList = irTreeStatementList.getStatementList();
		}
		return translateSeqStatement(irTreeStack);
	}


	public IRTree translateConstant(String value, int constType, CompilationContext compilationContext, boolean isSigned) {
		try{
			// TODO Vikash : add others later
			if(constType == CONSTANT_INT){
				return new IRTreeConst(new ConstantInt(Type.getInt32Type(compilationContext, isSigned),
						new APInt(32, value, isSigned)));
			}
			else if(constType == CONSTANT_DOUBLE){
				try {
					return new IRTreeConst(new ConstantFP(Type.getDoubleType(compilationContext), new APFloat(APFloat.IEEEdouble, value)));
				} catch (InstantiationException e) {
					System.exit(-1);
				}
			}
			else if(constType == CONSTANT_FLOAT){
				try {
					return new IRTreeConst(new ConstantFP(Type.getFloatType(compilationContext), new APFloat(APFloat.IEEEsingle, value)));
				} catch (InstantiationException e) {
					System.exit(-1);
				}
			}
			else if(constType == CONSTANT_CHAR){
				int asciiValue = (int)value.charAt(1);

				if(value.equals("'\\0'"))
					asciiValue = 0;

				return new IRTreeConst(new ConstantInt(Type.getInt8Type(compilationContext, isSigned),
						new APInt(8, new Integer(asciiValue).toString(), isSigned)));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public IRTree translateAssignStm(IRTree irTreeLHS,
			IRTree assemTypeRHS, int assignOperType, boolean isFloatingPointAssignment, CompilationContext compilationContext) {

		Stack<IRTree> irTreeStack = new Stack<IRTree>();
		IRTree irTree = null;

		IRTreeExp irTreeExpr = null;
		if(irTreeLHS instanceof IRTreeMemory)
			irTreeExpr = (IRTreeMemory) irTreeLHS;
		else if(irTreeLHS instanceof IRTreeTempOrVar){
			IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)irTreeLHS;
			irTreeExpr = valVsMemoryMap.get(irTreeTempOrVar);
			if(irTreeExpr == null)
				irTreeExpr = irTreeTempOrVar;
		}
		else if(irTreeLHS instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTreeLHS;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafStatementList.getStatement();

			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				irTreeExpr = irTreeExp;
			}
		}
		else if(irTreeLHS instanceof IRTreeAggregateData){
			irTreeExpr = (IRTreeAggregateData)irTreeLHS;
		}
		else if(irTreeLHS instanceof IRTreeCast){
			irTreeExpr = (IRTreeCast)irTreeLHS;
		}

		IRTreeExp expTreeFromStmtList = null;

		// If 'assemTypeRHS' is not just a expr, but a sequence of stmts leading to a expr
		if(assemTypeRHS instanceof IRTreeStatementList){

			// Get the expr tree from 'assemTypeTest'
			IRTreeStatementList irTreeLeafStmtList = getLeafStatementList((IRTreeStatementList)assemTypeRHS);
			IRTree irTree2 = irTreeLeafStmtList.getStatement();
			if(irTree2 instanceof IRTreeExpressionStm){
				IRTreeExpressionStm exprStmt = (IRTreeExpressionStm)irTreeLeafStmtList.getStatement();
				expTreeFromStmtList = exprStmt.getExpressionTree();
			}
			else if(irTree2 instanceof IRTreeMove){ 
				IRTreeMove irTreeMove = (IRTreeMove)irTree2;
				//expTreeFromStmtList = irTreeMove.getDest();
				expTreeFromStmtList = irTreeMove.getSrc();
				if(expTreeFromStmtList instanceof IRTreeBinaryExp){ 
					IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)expTreeFromStmtList;
					Value value = null;
					if(!postfixExprStack.empty() && (binaryExp.getBinaryOperatorID() == BinaryOperatorID.ADD || binaryExp.getBinaryOperatorID() == BinaryOperatorID.FADD || binaryExp.getBinaryOperatorID() == BinaryOperatorID.SUB || binaryExp.getBinaryOperatorID() == BinaryOperatorID.FSUB)){
						//Post fix or prefix
						postfixExprStack.pop();
						IRTreeExp leftExp = binaryExp.getLeft();
						if(leftExp.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
							IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)leftExp;
							value = irTreeTempOrVar.getValue();
						}
						else if(leftExp.getExpType() == TreeNodeExpType.MEMORY){
							IRTreeMemory irTreeMemory = (IRTreeMemory)leftExp;
							value = irTreeMemory.getMemory();
						}
						expTreeFromStmtList = new IRTreeTempOrVar(value);
					}
					else{
						expTreeFromStmtList = binaryExp;
						IRTreeMove moveAssemType = null;
						if(assignOperType == -1){  // Normal assign statement
							moveAssemType = new IRTreeMove(irTreeExpr, expTreeFromStmtList);
						}
						else{
							IRTreeExp resExp = (IRTreeExp)translateOperatorExp(assignOperType, irTreeLHS, expTreeFromStmtList, isFloatingPointAssignment);
							moveAssemType = new IRTreeMove(irTreeExpr, resExp);
						}

						irTreeStack.push(assemTypeRHS);
						irTreeStack.push(moveAssemType);
						irTree = translateSeqStatement(irTreeStack);
						return irTree;
					}
				}
				else if(irTreeMove.getSrc().getExpType() == TreeNodeExpType.MEMORY){
					IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeMove.getSrc();
					IRTreeTempOrVar irTreeTempOrVar = new IRTreeTempOrVar(irTreeMemory.getMemory());
					expTreeFromStmtList = irTreeTempOrVar;
				}
			}
		}
		else if(assemTypeRHS instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)assemTypeRHS;
			Value value = irTreeMemory.getMemory();
			Type type = value.getType();
			if(type.isPointerType()){
				PointerType pointerType = (PointerType)type;
				Type containedType = pointerType.getContainedType();
				if(containedType.isArrayType()){
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType );
					IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType);
					assemTypeRHS = aggregateData;
				}
				else if(containedType.isStructType()){
					if(irTreeLHS instanceof IRTreeMemory){
						IRTreeMemory irTreeMemory2 = (IRTreeMemory)irTreeLHS;
						Value value2 = irTreeMemory2.getMemory();
						Type type2 = value2.getType();
						if(type2.isPointerType()){
							PointerType pointerType2 = (PointerType)type2;
							Type containType = pointerType2.getContainedType();
							if(containType.isStructType()){
								IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(irTreeMemory, null, false, false, false, null);
								IRTreeCallExp callExp = createIntrinsicCallExpr("llvm.memcpy.p0i8.p0i8.i64", compilationContext, irTreeMemory2, false, false, containedType, null, irTreeDeclaration, null, irTreeStack, false, null);
								irTreeStack.push(callExp);
								return translateSeqStatement(irTreeStack);
							}
						}
					}
					else if(irTreeLHS instanceof IRTreeAggregateData){
						IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(irTreeMemory, null, false, false, false, null);
						IRTreeCallExp callExp = createIntrinsicCallExpr("llvm.memcpy.p0i8.p0i8.i64", compilationContext, irTreeLHS, false, false, containedType, null, irTreeDeclaration, null, irTreeStack, false, null);
						irTreeStack.push(callExp);
						return translateSeqStatement(irTreeStack);
					}
				}
			}
		}
		else if(assemTypeRHS instanceof IRTreeDeclaration){
			IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)assemTypeRHS;
			IRTreeMemory irTreeMemory = irTreeDeclaration.getMemoryTree();
			IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
			if(irTreeExp instanceof IRTreeConst){
				IRTreeConst irTreeConst = (IRTreeConst)irTreeExp;
				Value value = irTreeConst.getExpressionValue();
				if(value instanceof ConstantArray){
					ArrayType arrType = (ArrayType)value.getType();
					PointerType pointerType = null;
					try {
						pointerType = Type.getPointerType(compilationContext, arrType, 0);
					} catch (TypeCreationException e1) {
						e1.printStackTrace();
						System.exit(-1);
					}
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					createTwoZeroIndexes(compilationContext, pointerType, arrType, indexVsType);
					IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType);
					assemTypeRHS = aggregateData;

					if(!listOfConstArrDeclrs.contains(irTreeDeclaration))
						irTreeStack.push(irTreeDeclaration);

					listOfConstArrDeclrs.add(irTreeDeclaration);
				}
			}
		}

		IRTreeExp assemExpRHS = null;

		if(expTreeFromStmtList == null)
			assemExpRHS = (IRTreeExp) assemTypeRHS;
		else
			assemExpRHS = expTreeFromStmtList;

		IRTreeMove moveAssemType = null;

		if(assignOperType == -1) // Normal assign statement
			moveAssemType = new IRTreeMove(irTreeExpr, assemExpRHS);
		else{
			if(assemExpRHS.getExpType() == TreeNodeExpType.MEMORY){
				IRTreeMemory irTreeMemory = (IRTreeMemory)assemExpRHS;
				if(irTreeMemory.getIrTreeExp() != null && irTreeMemory.getIrTreeExp().getExpType() == TreeNodeExpType.MEMORY)
					assemExpRHS = new IRTreeTempOrVar(((IRTreeMemory)irTreeMemory.getIrTreeExp()).getMemory());
			}
			IRTreeExp resExp = (IRTreeExp)translateOperatorExp(assignOperType, irTreeLHS, assemExpRHS, isFloatingPointAssignment);

			if(resExp instanceof IRTreeBinaryExp){
				IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)resExp;
				IRTreeExp leftExp = binaryExp.getLeft();
				if(leftExp == irTreeExpr && irTreeExpr.getExpType() == TreeNodeExpType.MEMORY){
					IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExpr;
					if(irTreeMemory.getIrTreeExp() != null && irTreeMemory.getIrTreeExp().getExpType() == TreeNodeExpType.MEMORY){
						IRTreeExp irTreeExpr2 = new IRTreeTempOrVar(((IRTreeMemory)irTreeMemory.getIrTreeExp()).getMemory());
						moveAssemType = new IRTreeMove(irTreeExpr2, resExp);
					}
					else{
						moveAssemType = new IRTreeMove(irTreeExpr, resExp);
					}
				}
			}
			else
				moveAssemType = new IRTreeMove(irTreeExpr, resExp);
		}

		IRTreeMemory irTreeMemory2 = null;
		if(irTreeExpr instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExpr;
			irTreeMemory2 = (IRTreeMemory)irTreeMemory.getIrTreeExp();
		}

		if(irTreeLHS instanceof IRTreeStatementList && (irTreeExpr instanceof IRTreeMemory)){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTreeLHS;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafStatementList, irTreeStatementList);
			if(irTreeMemory2 != null){
				leafStatementList = getLeafStatementList(irTreeStatementList);
				irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafStatementList, irTreeStatementList);
			}
			irTreeLHS = irTreeStatementList;
		}
		if(assemTypeRHS instanceof IRTreeStatementList && (assemExpRHS instanceof IRTreeMemory)){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)assemTypeRHS;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafStatementList, irTreeStatementList);
			assemTypeRHS = irTreeStatementList;
		}
		if(!(assemTypeRHS instanceof IRTreeMemory))
			irTreeStack.push(assemTypeRHS);
		if(!(irTreeLHS instanceof IRTreeMemory))
			irTreeStack.push(irTreeLHS);

		irTreeStack.push(moveAssemType);
		irTree = translateSeqStatement(irTreeStack);
		return irTree;
	}

	public IRTreeExp translateIncrementOrDecrementOp(IRTreeExp childTree,
			boolean isIncrement) throws InstantiationException, InstructionCreationException {
		int plusOrMinus = BinaryOpExpr.PLUS;

		if (!isIncrement)
			plusOrMinus = BinaryOpExpr.MINUS;

		boolean isFloatingPointOpr = false;
		IRTreeConst assemConst = null;

		// Child tree can only be of type IRTreeTempOrVar or IRTreeMemory
		if (childTree.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP) {
			IRTreeTempOrVar childTreeTemp = (IRTreeTempOrVar) childTree;

			Type type = childTreeTemp.getValue().getType();

			if(type.isFloatType() || type.isDoubleType()){
				isFloatingPointOpr = true;
				ConstantFP constFP = new ConstantFP(type, new APFloat(APFloat.IEEEdouble, "1.0"));
				assemConst = new IRTreeConst(constFP);
			}
			else if(type.isIntegerType()){
				IntegerType integerType = (IntegerType)type;
				ConstantInt constInt = new ConstantInt(integerType, new APInt(integerType.getNumBits(), "1", integerType.isSigned()));
				assemConst = new IRTreeConst(constInt);
			}
			IRTreeExp incrementedTree = (IRTreeExp) translateBinOp(
					plusOrMinus, childTree, assemConst, isFloatingPointOpr);

			return incrementedTree;

		} else if (childTree.getExpType() == TreeNodeExpType.MEMORY) {
			IRTreeMemory childTreeMem = (IRTreeMemory) childTree;

			Type type = childTreeMem.getMemory().getType();
			CompilationContext compilationContext = type.getCompilationContext();

			IRTreeExp incrTree = getIncrTree(type, compilationContext, plusOrMinus, childTree, childTreeMem);
			return incrTree;
		}
		else if(childTree.getExpType() == TreeNodeExpType.AGR_EXP){
			IRTreeAggregateData aggregateData = (IRTreeAggregateData)childTree;
			List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
			int index = entries.size() - 1;
			Entry<IRTreeIndex, Type> entry = entries.get(index);
			Type type = entry.getValue();
			CompilationContext compilationContext = type.getCompilationContext();
			IRTreeExp incrTree = getIncrTree(type, compilationContext, plusOrMinus, childTree, aggregateData);
			return incrTree;
		}
		return null;
	}

	private IRTreeExp getIncrTree(Type type, CompilationContext compilationContext, int plusOrMinus, IRTree childTree, IRTree childTreeMem) throws InstantiationException, InstructionCreationException {
		if(type instanceof PointerType){
			PointerType ptrType = (PointerType)type;
			type = ptrType.getContainedType();
		}

		boolean isFloatingPointOpr = false;
		IRTreeConst assemConst = null;
		if(type.isFloatType() || type.isDoubleType()){
			isFloatingPointOpr = true;
			ConstantFP constFP = new ConstantFP(type, new APFloat(APFloat.IEEEdouble, "1.0"));
			assemConst = new IRTreeConst(constFP);
		}
		else if(type.isIntegerType()){
			IntegerType integerType = (IntegerType)type;
			ConstantInt constInt = new ConstantInt(integerType, new APInt(integerType.getNumBits(), "1", integerType.isSigned()));
			assemConst = new IRTreeConst(constInt);
		}
		// Postfix or Prefix Pointers
		else if(type.isPointerType()){
			ConstantInt constInt = new ConstantInt(Type.getInt32Type(compilationContext, false), new APInt(32, "1", false));
			assemConst = new IRTreeConst(constInt);
			IRTreeIndex irTreeIndex = new IRTreeIndex(assemConst, false);
			Entry<IRTreeIndex, Type> entry_indexVsType = getMapEntry(irTreeIndex, type);
			List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex, Type>>();
			indexVsType.add(entry_indexVsType);
			IRTreeAggregateData aggrData = new IRTreeAggregateData(childTreeMem, indexVsType);
			return aggrData;
		}

		IRTreeExp incrementedTree = (IRTreeExp) translateBinOp(
				plusOrMinus, childTree, assemConst, isFloatingPointOpr);
		return incrementedTree;
	}

	public IRTree translateOnesComplement(IRTree expr, CompilationContext compilationContext, TypeEntryWithAttributes typeEntryWithAttributes) {
		IRTreeExp exprTree = (IRTreeExp) expr;

		BasicType basicType =  typeEntryWithAttributes.getBasicType();
		boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
		IntegerType intType = (IntegerType)getLLVMType(basicType, isUnSigned, compilationContext);

		APInt val = new APInt(intType.getNumBits(), "-1", !isUnSigned);
		ConstantInt constInt = null;
		try {
			constInt = new ConstantInt(intType, val);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst = new IRTreeConst(constInt);

		IRTreeBinaryExp binaryExp = new IRTreeBinaryExp(BinaryOperatorID.XOR, exprTree, irTreeConst);
		return binaryExp;
	}

	public IRTree translateComparisonOperator(Predicate predicate,
			IRTree leftTree, IRTree rightTree) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if((leftTree instanceof IRTreeConst) && (rightTree instanceof IRTreeConst)){
			return getConstantValueIRTreeForComparisionOp(leftTree, rightTree, predicate);
		}

		if((leftTree instanceof IRTreeStatementList)){
			IRTreeStatementList leftIrTreeStatementList = (IRTreeStatementList)leftTree;
			IRTreeStatementList leafLeftIrTreeStatementList = getLeafStatementList(leftIrTreeStatementList);
			IRTreeStatement leafLeftIrTreeStatement = leafLeftIrTreeStatementList.getStatement();

			if((leafLeftIrTreeStatement instanceof IRTreeExpressionStm)){
				IRTreeExpressionStm leafLeftExpressionStm = (IRTreeExpressionStm)leafLeftIrTreeStatement;
				leftTree = removeStmtListFromIRTree(leafLeftIrTreeStatementList, (IRTreeStatementList)leftTree);
				IRTreeExp leftIrTreeExp = leafLeftExpressionStm.getExpressionTree();
				leftTree = leftIrTreeExp;
			}
			else if(leafLeftIrTreeStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafLeftIrTreeStatement;
				irTreeStack.push(irTreeMove);
				leftTree = irTreeMove.getSrc();
			}
		}
		if((rightTree instanceof IRTreeStatementList)){
			IRTreeStatementList rightIrTreeStatementList = (IRTreeStatementList)rightTree;
			IRTreeStatementList leafRightIrTreeStatementList = getLeafStatementList(rightIrTreeStatementList);
			IRTreeStatement leafRightIrTreeStatement = leafRightIrTreeStatementList.getStatement();

			if((leafRightIrTreeStatement instanceof IRTreeExpressionStm)){
				IRTreeExpressionStm leafRightExpressionStm = (IRTreeExpressionStm)leafRightIrTreeStatement;
				rightTree = removeStmtListFromIRTree(leafRightIrTreeStatementList, (IRTreeStatementList)rightTree);
				IRTreeExp rightIrTreeExp = leafRightExpressionStm.getExpressionTree();
				rightTree = rightIrTreeExp;
			}
		}
		IRTreeConditionalExpr irCondExpr = new IRTreeConditionalExpr(predicate,
				(IRTreeExp)leftTree, (IRTreeExp) rightTree);
		irTreeStack.push(irCondExpr);
		return translateSeqStatement(irTreeStack);
	}

	public IRTree translateLogicalOperator(int logicalOperator,
			IRTree leftTree, IRTree rightTree, CompilationContext compilationContext) {

		IRTree operationResultTree = null;
		Label currentLabel = Label.getCurrentLabel();
		if(currentLabel.getName().equals("L1"))// This means till now no Labels have been created, i.e still it is the first basic block
		{
			currentLabel = new Label("L0");
		}
		Stack<IRTree> seqAssemTypes = new Stack<IRTree>();
		//create constant zero
		APInt zero = new APInt(32, "0", true);
		ConstantInt zeroConst = null;
		try {
			zeroConst = new ConstantInt(Type.getInt32Type(compilationContext, true), zero);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst zeroIRTree = new IRTreeConst(zeroConst);

		IRTreeExp leftExp = getIRTreeExprFromIRTree(leftTree, seqAssemTypes);
		IRTreeConditionalExpr leftConditionalExpr = null;
		IRTreeConditionalBranch conditionalBranch = null;

		if(leftExp.getExpType() == TreeNodeExpType.CONDITIONAL_EXPR){
			leftConditionalExpr = (IRTreeConditionalExpr)leftExp;
		}
		else{
			//first create a conditional expression between the left expr and constant zero
			leftConditionalExpr = new IRTreeConditionalExpr(Predicate.ICMP_NE, leftExp, zeroIRTree);
		}

		//create three labels
		Label trueLabel = new Label();
		if(trueLabel.equals(currentLabel)){
			String s = trueLabel.getName();
			s = s.replaceFirst("L", "");
			Integer i = Integer.valueOf(s)-1;
			s = "L" + i;
			currentLabel = new Label(s);
		}
		Label falseLabel = new Label();
		//create a conditional jump 
		if (logicalOperator == BinaryOpExpr.OR) {
			conditionalBranch = new IRTreeConditionalBranch(falseLabel, trueLabel, leftConditionalExpr);
		}
		else{
			conditionalBranch = new IRTreeConditionalBranch(trueLabel, falseLabel, leftConditionalExpr);
		}
		seqAssemTypes.push(conditionalBranch);

		IRTreeLabel irTreeTrueLab = new IRTreeLabel(trueLabel);
		seqAssemTypes.push(irTreeTrueLab);

		IRTreeExp rightExp = getIRTreeExprFromIRTree(rightTree, seqAssemTypes);

		if(leftExp != null && rightExp != null)
			if(leftExp.getExpType() == TreeNodeExpType.CONST_EXP && rightExp.getExpType() == TreeNodeExpType.CONST_EXP){
				BinaryOperatorID binaryOperatorID = null;
				if (logicalOperator == BinaryOpExpr.OR)
					binaryOperatorID = BinaryOperatorID.OR;
				else
					binaryOperatorID = BinaryOperatorID.AND;
				IRTree irTree = getConstantValueIRTree(leftExp, rightExp, binaryOperatorID );
				return irTree;
			}
		IRTreeConditionalExpr rightConditionalExpr = null;

		if(rightExp != null)
			if(rightExp.getExpType() == TreeNodeExpType.CONDITIONAL_EXPR){
				rightConditionalExpr = (IRTreeConditionalExpr)rightExp;
			}
			else{
				//first create a conditional expression between the right expr and constant zero
				rightConditionalExpr = new IRTreeConditionalExpr(Predicate.ICMP_NE, rightExp, zeroIRTree);
			}

		IRTreeUnConditionalBranch unCondBrToFalseBlk = new IRTreeUnConditionalBranch(falseLabel);
		seqAssemTypes.push(rightConditionalExpr);
		seqAssemTypes.push(unCondBrToFalseBlk);
		IRTreeLabel irTreeFalseLab = new IRTreeLabel(falseLabel);
		seqAssemTypes.push(irTreeFalseLab);
		IRTreePhiExpr phiExpr = null;
		IRTreeExp val1 = null;
		IRTreeConst irTreeConstTrue = null;
		try {
			irTreeConstTrue = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "true", false)));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConstFalse = null;
		try {
			irTreeConstFalse = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "false", false)));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeExp val2 = rightConditionalExpr;

		if(logicalOperator == BinaryOpExpr.OR){
			val1 = irTreeConstTrue;
		}else{
			val1 = irTreeConstFalse;
		}

		Map<Label, IRTreeExp> labelVsValueMap = new LinkedHashMap<Label, IRTreeExp>();
		labelVsValueMap .put(currentLabel, val1);
		labelVsValueMap.put(trueLabel, val2);
		phiExpr = new IRTreePhiExpr(Type.getInt1Type(compilationContext, false), labelVsValueMap);
		IRTreeExpressionStm phiExprStmt = new IRTreeExpressionStm(phiExpr);
		seqAssemTypes.push(phiExprStmt);
		operationResultTree = translateSeqStatement(seqAssemTypes); 
		return operationResultTree;
	}


	public IRTree translateIfStm(IRTree assemTypeTest,
			IRTree assemTypeIf, IRTree assemTypeElse, CompilationContext compilationContext, IRTree returnValueTree) {

		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		// create three labels ifLabel, elseLabel and endOfElseLabel
		Label ifLabel = new Label();
		Label elseLabel = null;

		if(assemTypeElse != null){
			elseLabel = new Label();
		}

		Label endOfIfElseLabel = new Label();
		IRTreeConditionalBranch irTreeCondBranch = null;
		irTreeCondBranch = createConditionalBrInstr(ifLabel, elseLabel, compilationContext,assemTypeTest, irTreeStack);
		IRTreeUnConditionalBranch unconditionalBrForIf = new IRTreeUnConditionalBranch(endOfIfElseLabel);
		IRTreeStatementList unconditionalBrStmtListAfterIf = new IRTreeStatementList(unconditionalBrForIf, null);
		IRTreeLabel irTreeIfLabel = new IRTreeLabel(ifLabel);
		IRTreeStatementList irTreeIfLabelStmtList = new IRTreeStatementList(irTreeIfLabel, null);
		irTreeStack.push(irTreeIfLabelStmtList);

		if(assemTypeIf != null){

			// May be a return statement, so we have to convert it to a IRTreeStatementList
			if(!(assemTypeIf instanceof IRTreeStatementList)){
				Stack<IRTree> seq = new Stack<IRTree>();
				seq.push(assemTypeIf);
				assemTypeIf = translateSeqStatement(seq);
			}

			IRTreeStatementList leafOfIfStmtList = getLeafStatementList((IRTreeStatementList)assemTypeIf);
			if(leafOfIfStmtList != null){
				IRTreeStatement leafStmt = leafOfIfStmtList.getStatement();
				//if the last statement of the if the block is a unconditional jump or unreachable instruction then don't create a new unconditional jump
				if(!(leafStmt instanceof IRTreeUnConditionalBranch) && !(leafStmt instanceof IRTreeUnReachable)){
					leafOfIfStmtList.setStatementList(unconditionalBrStmtListAfterIf);
				}
				// If the leaf statement is a Return Statement then the 'endOfIfElseLabel' should go to the block containing return statement
				if(leafStmt instanceof IRTreeReturn){
					IRTreeStatementList newStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafOfIfStmtList, (IRTreeStatementList)assemTypeIf);
					assemTypeIf = handleMultipleReturnStmt(newStatementList, leafStmt, returnValueTree, unconditionalBrForIf);
				}
			}
			else{
				assemTypeIf = unconditionalBrStmtListAfterIf;
			}
		}
		else{
			assemTypeIf = unconditionalBrStmtListAfterIf;
		}
		irTreeStack.push(assemTypeIf);
		//adding unconditional branch statement list to the else statement list
		IRTreeUnConditionalBranch unconditionalBrForElse = new IRTreeUnConditionalBranch(endOfIfElseLabel);
		IRTreeStatementList unconditionalBrStmtListAfterElse = new IRTreeStatementList(unconditionalBrForElse, null);

		IRTreeLabel irTreeElseLabel = null;

		if(assemTypeElse != null){
			irTreeElseLabel = new IRTreeLabel(elseLabel);
		}

		if(assemTypeElse != null){
			IRTreeStatementList irTreeElseLabelStmtList = new IRTreeStatementList(irTreeElseLabel, null);
			irTreeStack.push(irTreeElseLabelStmtList);
		}

		if(assemTypeElse != null){
			if(!(assemTypeElse instanceof IRTreeStatementList)){
				Stack<IRTree> seq = new Stack<IRTree>();
				seq.push(assemTypeElse);
				assemTypeElse = translateSeqStatement(seq);
			}
			IRTreeStatementList leafOfElseStmtList = getLeafStatementList((IRTreeStatementList)assemTypeElse);
			IRTreeStatement leafOfElseStatement = leafOfElseStmtList.getStatement();

			//if the last statement of the else the block is a unconditional jump or unreachable instruction then don't create a new unconditional jump
			if(leafOfElseStmtList != null){
				if(!(leafOfElseStatement instanceof IRTreeUnConditionalBranch) && !(leafOfElseStatement instanceof IRTreeUnReachable))
					leafOfElseStmtList.setStatementList(unconditionalBrStmtListAfterElse);

				if((leafOfElseStatement instanceof IRTreeReturn)){
					IRTreeStatementList newStmtList = (IRTreeStatementList)removeStmtListFromIRTree(leafOfElseStmtList, (IRTreeStatementList)assemTypeElse);
					assemTypeElse = handleMultipleReturnStmt(newStmtList,leafOfElseStatement, returnValueTree, unconditionalBrForElse);
				}
			}
		}
		else{
			//update the conditional branch with the end of the if-else part
			irTreeCondBranch.setRightLabel(endOfIfElseLabel);
		}
		// Add the IRTree statements for the true and false paths
		if(assemTypeElse != null)
			irTreeStack.push(assemTypeElse);

		IRTreeLabel irTreeEndOfElseLabel = new IRTreeLabel(endOfIfElseLabel);
		IRTreeStatementList irTreeEndOfElseLabelStmtList = new IRTreeStatementList(irTreeEndOfElseLabel, null);
		irTreeStack.push(irTreeEndOfElseLabelStmtList);

		return translateSeqStatement(irTreeStack);
	}

	private IRTreeConditionalBranch createConditionalBrForType(Type type, Label ifLabel, Label elseLabel, IRTreeExp irTreeExpr) {
		if(type.isPointerType()){
			PointerType pointerType = (PointerType)type;
			Type containedType = pointerType.getContainedType();
			type = containedType;
		}
		Predicate predicate = null;
		IRTreeConst rightExp = null;
		if(type.isIntegerType()){
			IntegerType integerType = (IntegerType)type;
			predicate = Predicate.ICMP_NE;
			APInt val = new APInt(integerType.getNumBits(), "0", integerType.isSigned());
			ConstantInt constantZero = null;
			try {
				constantZero = new ConstantInt(integerType, val);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			rightExp = new IRTreeConst(constantZero);
		}
		else if(type.isFloatingPointType()){
			predicate = Predicate.FCMP_ONE;
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "0.0");
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(type, apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			rightExp = new IRTreeConst(constantFP);
		}
		IRTreeConditionalExpr conditionalExpTree = new IRTreeConditionalExpr(predicate, irTreeExpr, rightExp);
		IRTreeConditionalBranch irTreeCondBranch = new IRTreeConditionalBranch(ifLabel, elseLabel, conditionalExpTree);
		return irTreeCondBranch;
	}


	private IRTreeStatementList handleMultipleReturnStmt(IRTreeStatementList irTreeStatementList, IRTree irTree, IRTree returnValueTree,
			IRTreeUnConditionalBranch unconditionalBr) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(globalReturnLabel == null)
			globalReturnLabel = new Label();

		if(irTreeStatementList != null){
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafIrTreeStatement = leafIrTreeStatementList.getStatement();
			if(leafIrTreeStatement instanceof IRTreeUnConditionalBranch){
				IRTreeUnConditionalBranch unConditionalBranch = (IRTreeUnConditionalBranch)leafIrTreeStatement;
				if(unConditionalBranch == unconditionalBr){
					irTreeStatementList = (IRTreeStatementList)removeStmtListFromIRTree(leafIrTreeStatementList, irTreeStatementList);
				}
			}
			irTreeStack.push(irTreeStatementList);
		}

		unconditionalBr.setTargetLabel(globalReturnLabel);
		IRTreeReturn irTreeReturn = (IRTreeReturn)irTree;
		IRTreeExp irTreeExp = irTreeReturn.getExpTree();
		Type originalReturnType = irTreeReturn.getOriginalReturnType();
		CompilationContext compilationContext = originalReturnType.getCompilationContext();

		if(originalReturnType == Type.getVoidType(compilationContext)){
			irTreeStack.push(unconditionalBr);

			return (IRTreeStatementList) translateSeqStatement(irTreeStack);
		}

		IRTreeReturnAlloca irTreeReturnValue = (IRTreeReturnAlloca)returnValueTree;
		IRTreeMemory destExp = irTreeReturnValue.getMemory();
		IRTreeMove irTreeMove = new IRTreeMove(destExp, irTreeExp);
		irTreeStack.push(irTreeMove);
		irTreeStack.push(unconditionalBr);
		return (IRTreeStatementList)translateSeqStatement(irTreeStack);
	}


	private IRTree removeStmtListFromIRTree(IRTreeStatementList stmtListToRemove,
			IRTreeStatementList srcStmtList) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(stmtListToRemove == srcStmtList)
			return translateSeqStatement(irTreeStack);

		irTreeStack.push(srcStmtList.getStatement());
		IRTreeStatementList list = srcStmtList.getStatementList();
		while(list != null){
			if(list == stmtListToRemove){
				list = list.getStatementList();
				continue;
			}
			irTreeStack.push(list.getStatement());
			list = list.getStatementList();
		}
		if(!irTreeStack.empty()){
			IRTreeStatementList tempsrcStmtList = (IRTreeStatementList)translateSeqStatement(irTreeStack);
			srcStmtList = tempsrcStmtList;
		}
		return srcStmtList;
	}


	public IRTree translateOperatorExp(int operType,
			IRTree assemTypeLeft, IRTree assemTypeRight, boolean isFloatingPointAssignment) {		
		IRTreeExp opExp = (IRTreeExp) translateBinOp(operType,
				assemTypeLeft, assemTypeRight, isFloatingPointAssignment);
		return opExp;
	}

	public IRTree translateIndirection(IRTree pointerTree, CompilationContext compilationContext) {
		translatingIndirectionStack.push(pointerTree);
		Stack<IRTree> irTreeStack = new Stack<IRTree>();
		if(pointerTree instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)pointerTree;

			Value value = irTreeMemory.getMemory();
			Type type = value.getType();
			if(type.isPointerType()){
				PointerType pointerType = (PointerType)type;
				Type containedType = pointerType.getContainedType();

				if(containedType.isAggregateType()){
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType);
					IRTreeAggregateData aggregateData1 = new IRTreeAggregateData(irTreeMemory, indexVsType);

					if(containedType.isAggregateType()){
						if(containedType.isArrayType()){
							ArrayType arrayType = (ArrayType)containedType;
							Type containedType2 = arrayType.getContainedType();

							if(containedType2.isArrayType() && translatingIndirectionStack.size() == 1){
								ArrayType arrayType2 = (ArrayType)containedType2;
								PointerType pointerType2 = null;
								try {
									pointerType2 = Type.getPointerType(compilationContext, arrayType2, 0);
								} catch (TypeCreationException e) {
									e.printStackTrace();
									System.exit(-1);
								}
								Value pointerValue = new Value(pointerType2);
								IRTreeMemory irTreeMemory2 = new IRTreeMemory(pointerValue, aggregateData1);
								indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
								createTwoZeroIndexes(compilationContext, pointerType2, pointerType2.getContainedType(), indexVsType);
								IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(irTreeMemory2, indexVsType);
								irTreeStack.push(aggregateData1);
								irTreeStack.push(aggregateData2);
								return translateSeqStatement(irTreeStack);
							}
						}
						else if(containedType.isStructType()){

						}
					}
					translatingIndirectionStack.pop();
					irTreeStack.push(aggregateData1);
					return translateSeqStatement(irTreeStack);
				}
				// For Function Pointer
				else if(containedType.isFunctionType()){
					return pointerTree;
				}

				Value value2 = new Value(containedType);
				IRTreeMemory irTreeMemory2 = new IRTreeMemory(value2, irTreeMemory);
				irTreeStack.push(irTreeMemory);
				irTreeStack.push(irTreeMemory2);

				translatingIndirectionStack.pop();
				return translateSeqStatement(irTreeStack);
			}
		}
		else if(pointerTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)pointerTree;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTree leafStatement = leafIrTreeStatementList.getStatement();
			// This is to handle multiple ******(stars)
			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				if(irTreeExp instanceof IRTreeMemory){
					IRTreeStatementList treeStatementList = (IRTreeStatementList)translateIndirection(irTreeExp, compilationContext);
					IRTreeStatementList irTreeStatementList2 = treeStatementList.getStatementList();

					if(irTreeStatementList2 != null){
						IRTreeStatement leafStmt = treeStatementList.getStatementList().getStatement();
						IRTreeExpressionStm exprStm = (IRTreeExpressionStm)leafStmt;
						irTreeStack.push(pointerTree);
						irTreeStack.push(exprStm.getExpressionTree());
					}
					else{
						irTreeStack.push(treeStatementList.getStatement());
					}

					translatingIndirectionStack.pop();
					return translateSeqStatement(irTreeStack);
				}
				else if(irTreeExp instanceof IRTreeAggregateData){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp;
					List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
					int lastIndex = entries.size() - 1;
					Type type = entries.get(lastIndex).getValue();
					if(type.isArrayType()){
						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, type, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						Type containedType = pointerType.getContainedType();
						List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
						createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType );
						IRTreeMemory aggregateDataStruct = new IRTreeMemory(new Value(pointerType), aggregateData);
						IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(aggregateDataStruct, indexVsType);

						irTreeStack.push(pointerTree);
						irTreeStack.push(aggregateData2);

						translatingIndirectionStack.pop();
						return translateSeqStatement(irTreeStack);
					}
					else if(type.isPointerType()){
						PointerType pointerType = (PointerType)type;
						Type containedType = pointerType.getContainedType();
						if(containedType.isAggregateType()){
							if(containedType.isArrayType()){
								List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
								createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType );
								IRTreeMemory aggregateDataStruct = new IRTreeMemory(new Value(pointerType), aggregateData);
								IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(aggregateDataStruct, indexVsType);

								irTreeStack.push(pointerTree);
								irTreeStack.push(aggregateData2);

								translatingIndirectionStack.pop();
								return translateSeqStatement(irTreeStack);
							}
							else if(containedType.isStructType()){
								// TODO
							}
						}
					}
				}
			}
			else if(leafStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
				IRTreeExp irTreeExp = irTreeMove.getSrc();
				// This could be a prefix Expression
				if(irTreeExp instanceof IRTreeAggregateData){

					translatingIndirectionStack.pop();
					return pointerTree;
				}
			}
		}
		else if(pointerTree instanceof IRTreeBinaryExp){
			IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)pointerTree;
			BinaryOperatorID binaryOperatorID = binaryExp.getBinaryOperatorID();
			IRTreeExp leftExp = binaryExp.getLeft();
			IRTreeExp rightExp = binaryExp.getRight();
			IRTree irTree = null;
			IRTree irTree2 = null;

			if(leftExp instanceof IRTreeMemory){
				irTree = translateIndirection(leftExp, compilationContext);
				irTree2 = rightExp;
			}
			else if(leftExp instanceof IRTreeBinaryExp){
				irTree = translateIndirection(leftExp, compilationContext);
				irTree2 = rightExp;
			}
			else if(leftExp instanceof IRTreeAggregateData){
				irTree = leftExp;
				irTree2 = rightExp;
			}
			else if(rightExp instanceof IRTreeMemory){
				irTree = translateIndirection(rightExp, compilationContext);
				irTree2 = leftExp;
			}
			else if(rightExp instanceof IRTreeBinaryExp){
				irTree = translateIndirection(rightExp, compilationContext);
				irTree2 = leftExp;
			}

			if(irTree instanceof IRTreeAggregateData){
				IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTree;
				IRTree irTree3 = aggregateData.getAggregateDataStruct();
				if(irTree3 instanceof IRTreeMemory){
					IRTreeMemory irTreeMemory = (IRTreeMemory)irTree3;
					Value value = irTreeMemory.getMemory();
					Type type = value.getType();
					if(type.isPointerType()){
						PointerType pointerType = (PointerType)type;
						Type containedType = pointerType.getContainedType();
						if(containedType.isAggregateType()){
							if(containedType.isArrayType()){
								ArrayType arrayType = (ArrayType)containedType;
								Type containedType2 = arrayType.getContainedType();
								PointerType pointerType2 = null;
								try {
									pointerType2 = Type.getPointerType(compilationContext, containedType2, 0);
								} catch (TypeCreationException e) {
									e.printStackTrace();
									System.exit(-1);
								}
								Value value2 = new Value(pointerType2);
								IRTreeMemory irTreeMemory2 = new IRTreeMemory(value2, aggregateData);

								boolean isNegative = false;

								if(binaryOperatorID == BinaryOperatorID.SUB){
									isNegative = true;
								}

								IRTreeIndex irTreeIndex = new IRTreeIndex(irTree2, isNegative);

								if(irTree2 instanceof IRTreeConst){
									IRTreeConst const1 = (IRTreeConst)irTree2;
									Value value3 = const1.getExpressionValue();
									APInt apInt = const1.getApInt();
									value3.setType(Type.getInt64Type(compilationContext, false));
									apInt.setNumBits(64);
								}

								Entry<IRTreeIndex, Type> entry_indexVsType = null;
								entry_indexVsType = getMapEntry(irTreeIndex, pointerType2);
								List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
								indexVsType.add(entry_indexVsType);
								IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(irTreeMemory2, indexVsType);

								// If the array is not a multidimensional array
								if((!containedType2.isAggregateType())){
									irTreeStack.push(aggregateData);
									irTreeStack.push(aggregateData2);
									translatingIndirectionStack.pop();
									return translateSeqStatement(irTreeStack);
								}

								if(translatingIndirectionStack.size() == 1){
									indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
									Type containedType3 = pointerType2.getContainedType();
									createTwoZeroIndexes(compilationContext, pointerType2, containedType3, indexVsType);
									IRTreeMemory irTreeMemory3 = new IRTreeMemory(value2, aggregateData2);
									IRTreeAggregateData aggregateData3 = new IRTreeAggregateData(irTreeMemory3, indexVsType);
									irTreeStack.push(aggregateData);
									irTreeStack.push(aggregateData2);
									irTreeStack.push(aggregateData3);
									translatingIndirectionStack.pop();
									return translateSeqStatement(irTreeStack);
								}
								else{
									irTreeStack.push(aggregateData);
									irTreeStack.push(aggregateData2);

									translatingIndirectionStack.pop();
									return translateSeqStatement(irTreeStack);
								}
							}
							else{
								// TODO Must be a Structure type
							}
						}
					}
				}
				pointerTree = aggregateData;
			}
			else if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
				IRTreeStatement irTreeStatement = leafIrTreeStatementList.getStatement();
				if(irTreeStatement instanceof IRTreeExpressionStm){
					IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
					IRTreeExp irTreeExp = expressionStm.getExpressionTree();
					if(irTreeExp.getExpType() == TreeNodeExpType.AGR_EXP){
						IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp;
						boolean isNegative = false;

						if(binaryOperatorID == BinaryOperatorID.SUB)
							isNegative = true;

						IRTreeIndex irTreeIndex = new IRTreeIndex(irTree2, isNegative);

						if(irTree2 instanceof IRTreeConst){
							IRTreeConst const1 = (IRTreeConst)irTree2;
							Value value3 = const1.getExpressionValue();
							APInt apInt = const1.getApInt();
							value3.setType(Type.getInt64Type(compilationContext, false));
							apInt.setNumBits(64);
						}

						PointerType pointerType2 = null;
						Value value2 = null;

						IRTree aggregateDataStruct = aggregateData.getAggregateDataStruct();
						if(aggregateDataStruct instanceof IRTreeMemory){
							IRTreeMemory irTreeMemory = (IRTreeMemory)aggregateDataStruct;
							Value value = irTreeMemory.getMemory();
							value2 = value;
							Type type = value.getType();
							pointerType2 = (PointerType)type;
						}

						IRTreeMemory irTreeMemory2 = new IRTreeMemory(value2, aggregateData);
						Entry<IRTreeIndex, Type> entry_indexVsType = null;
						entry_indexVsType = getMapEntry(irTreeIndex, pointerType2);
						List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
						indexVsType.add(entry_indexVsType);
						IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(irTreeMemory2, indexVsType);

						if(translatingIndirectionStack.size() == 1){
							indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
							Type containedType = pointerType2.getContainedType();
							createTwoZeroIndexes(compilationContext, pointerType2, containedType, indexVsType);
							IRTreeMemory irTreeMemory3 = new IRTreeMemory(value2, aggregateData2);
							IRTreeAggregateData aggregateData3 = new IRTreeAggregateData(irTreeMemory3, indexVsType);
							irTreeStack.push(irTreeStatementList);
							irTreeStack.push(aggregateData2);
							irTreeStack.push(aggregateData3);
							translatingIndirectionStack.pop();
							return translateSeqStatement(irTreeStack);
						}
						else{
							irTreeStack.push(irTreeStatementList);
							irTreeStack.push(aggregateData2);
							translatingIndirectionStack.pop();
							return translateSeqStatement(irTreeStack);
						}
					}
				}
			}
		}
		translatingIndirectionStack.pop();
		return pointerTree;
	}

	/**
	 * Create and return a tree of the minus expr
	 * 
	 * @param addressTree
	 * @return
	 */

	public IRTree translateNegativeValOfExpr(IRTree addressTree, CompilationContext compilationContext) {
		boolean isFloatingPointExpr = false;
		if(addressTree instanceof IRTreeConst){
			IRTreeConst irTreeConst = (IRTreeConst)addressTree;
			Value value = irTreeConst.getExpressionValue();
			if(value.getType().isFloatingPointType()){
				isFloatingPointExpr = true;
			}
		}
		APInt val = new APInt(32, "0", true);
		ConstantInt constInt = null;
		try {
			constInt = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst negativeVal = new IRTreeConst(constInt);
		return translateBinOp(BinaryOpExpr.MINUS, negativeVal, addressTree, isFloatingPointExpr);
	}

	/**
	 * This method creates two zero indexes for GetElementPtr instruction.
	 * @param compilationContext
	 * @param pointerType
	 * @param containedType
	 * @param indexVsType
	 */
	protected void createTwoZeroIndexes(CompilationContext compilationContext, Type pointerType, Type containedType, List<Entry<IRTreeIndex, Type>> indexVsType) {
		ConstantInt constInt = null;
		try {
			constInt = new ConstantInt(Type.getInt32Type(compilationContext, false), new APInt(32, "0", false));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		IRTreeConst zeroConstTree1 = new IRTreeConst(constInt);
		IRTreeConst zeroConstTree2 = new IRTreeConst(constInt);

		IRTreeIndex irTreeIndex1 = new IRTreeIndex(zeroConstTree1, false);
		IRTreeIndex irTreeIndex2 = new IRTreeIndex(zeroConstTree2, false);

		if(containedType.isArrayType()){
			ArrayType arrayType = (ArrayType)containedType;
			containedType = arrayType.getContainedType();
		}

		Entry<IRTreeIndex, Type> entry_indexVsType1 = getMapEntry(irTreeIndex1, pointerType);
		Entry<IRTreeIndex, Type> entry_indexVsType2 = getMapEntry(irTreeIndex2, containedType);
		indexVsType.add(entry_indexVsType1);
		indexVsType.add(entry_indexVsType2);
	}



	/**
	 * Create and return the negation of an expression
	 * 
	 * @param addressTree
	 * @return
	 */
	public IRTree translateNegationOfExpr(IRTree irTree, TypeEntryWithAttributes typeEntryWithAttributes, CompilationContext compilationContext) {
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		IRTreeExp leftExp = null;

		if(irTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafStatementList.getStatement();
			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				leftExp = expressionStm.getExpressionTree();
			}
			else if(leafStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
				leftExp = irTreeMove.getSrc();
			}
			irTreeStack.push(irTree);
		}
		else if(irTree instanceof IRTreeDeclaration){
			IRTreeDeclaration declaration = (IRTreeDeclaration)irTree;
			IRTreeExp exp = declaration.getInitializerExpression();
			if(exp != null)
				leftExp = exp;
		}
		else{
			leftExp = (IRTreeExp)irTree;
		}

		BasicType basicType = typeEntryWithAttributes.getBasicType();
		boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
		Type type = getLLVMType(basicType, isUnSigned, compilationContext);

		IRTreeConditionalExpr conditionalExpr = null;

		if(type.isFloatingPointType()){
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, "0.0");
			ConstantFP constFP = null;
			try {
				constFP = new ConstantFP(type, apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			IRTreeConst rightExp = new IRTreeConst(constFP);
			conditionalExpr = new IRTreeConditionalExpr(Predicate.FCMP_ONE, leftExp, rightExp);
		}
		else if(type.isIntegerType()){
			IntegerType integerType = (IntegerType)type;
			APInt val = new APInt(integerType.getNumBits(), "0", !isUnSigned);
			ConstantInt constInt = null;
			try {
				constInt = new ConstantInt(integerType, val);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			IRTreeConst rightExp = new IRTreeConst(constInt);
			conditionalExpr = new IRTreeConditionalExpr(Predicate.ICMP_NE, leftExp, rightExp);
		}

		IRTreeConst irTreeConstTrue = null;
		try {
			irTreeConstTrue = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "true", false)));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		IRTreeBinaryExp binaryExp = new IRTreeBinaryExp(BinaryOperatorID.XOR, conditionalExpr, irTreeConstTrue);

		irTreeStack.push(conditionalExpr);
		irTreeStack.push(binaryExp);


		return translateSeqStatement(irTreeStack);
	}

	/**
	 * This method is used for creating declaration statements for variables
	 * @param newValue
	 * @param initializer
	 * @param isGlobal
	 * @param isConstant
	 * @param compilationContext
	 * @param baseEntry
	 * @return
	 */
	public IRTree translateVarDec(Value newValue, IRTreeExp initializer, boolean isGlobal, boolean isConstant, CompilationContext compilationContext, TypeEntryWithAttributes baseEntry) {
		IRTreeMemory memoryTree = new IRTreeMemory(newValue);

		if(initializer instanceof IRTreeCallExp){
			IRTreeCallExp callExp = (IRTreeCallExp)initializer;
			Type returnType = callExp.getReturnType();
			if(returnType.isVoidType())
				return callExp;
		}
		else if(initializer instanceof IRTreeConst){
			if(newValue.getType().isPointerType()){
				PointerType pointerType = (PointerType)newValue.getType();
				Type containedType = pointerType.getContainedType();
				if(containedType.isArrayType() && !isGlobal)
					return null;
			}
		}

		LinkageTypes linkageTypes = null;

		if(baseEntry.isStatic()){
			linkageTypes = LinkageTypes.InternalLinkage;
			isGlobal = true;
		}
		else if(initializer == null && !baseEntry.isExtern()){
			linkageTypes = LinkageTypes.CommonLinkage;
			isConstant = false;
		}
		else if(baseEntry.isExtern())
			linkageTypes = LinkageTypes.ExternalLinkage;

		IRTreeDeclaration nodeDecl = new IRTreeDeclaration(memoryTree, initializer, isGlobal, isConstant, false, linkageTypes);
		return nodeDecl;
	}

	private List<Type> getParamTypesFromParamValues(
			List<Value> formalParamValues) {
		List<Type> paramTypes = new ArrayList<Type>();
		for(Value value : formalParamValues)
			paramTypes.add(value.getType());
		return paramTypes;
	}

	/**
	 * This method is used to create call instructions
	 * @param funcName
	 * @param argsStack
	 * @param returnType
	 * @param listOfFormalTypes
	 * @param hasEllipses
	 * @param compilationContext
	 * @param funcMemory
	 * @return
	 */
	public IRTree translateCallExp(String funcName, Stack<IRTree> argsStack, Type returnType, List<Type> listOfFormalTypes, boolean hasEllipses, CompilationContext compilationContext, IRTreeMemory funcMemory) {
		boolean isOnlyReadsMemory = false;
		boolean isNoReturn = false;
		int func_attrs = 0;
		int return_attrs = 0;
		if(funcName != null && (funcName.equals("scanf") || funcName.equals("strlen"))){
			isOnlyReadsMemory = true;
		}
		if(funcName != null && (funcName.equals("exit") && returnType.isVoidType() && listOfFormalTypes.size() == 1 && listOfFormalTypes.get(0).isInt32Type())){
			isNoReturn = true;
			func_attrs = LLVMUtility.getParamAttributeAsIntegerValue(properties,"noreturn");
		}
		if(funcName != null && funcName.equals("malloc")){
			return_attrs = LLVMUtility.getParamAttributeAsIntegerValue(properties,"noalias");
			func_attrs = LLVMUtility.getParamAttributeAsIntegerValue(properties,"nounwind");
		}
		if(funcName != null && (funcName.equals("free") || funcName.equals("rand"))){
			func_attrs = LLVMUtility.getParamAttributeAsIntegerValue(properties,"nounwind");
		}
		Stack<IRTree> irTreeStack = new Stack<IRTree>();
		List<IRTree> paramList = new ArrayList<IRTree>();
		while (!argsStack.isEmpty()) {
			IRTree irTree = argsStack.pop();
			if(irTree instanceof IRTreeDeclaration){
				// This is the case when a string is passed as a argument
				IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
				// put this declaration in the stack
				irTreeStack.push(irTreeDeclaration);
				IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
				if(irTreeExp instanceof IRTreeConst){
					IRTreeConst irTreeConst = (IRTreeConst)irTreeExp;
					Value value = irTreeConst.getExpressionValue();
					if(value instanceof ConstantArray){
						ConstantArray constantArray = (ConstantArray)value;
						ArrayType arrayType = constantArray.getArrayType();
						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, arrayType, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();;
						createTwoZeroIndexes(compilationContext, pointerType, arrayType, indexVsType );
						IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeDeclaration.getMemoryTree(), indexVsType);
						paramList.add(aggregateData);
					}
				}
			}
			else if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				irTreeStack.push(irTreeStatementList);

				IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
				IRTreeStatement leafStatement = leafStatementList.getStatement();
				if(leafStatement instanceof IRTreeMove){
					IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
					paramList.add(irTreeMove.getSrc());
				}
				else if(leafStatement instanceof IRTreeExpressionStm){
					IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
					IRTreeExp irTreeExp = expressionStm.getExpressionTree();
					paramList.add(irTreeExp);
				}
			}
			else{
				IRTreeExp exp = (IRTreeExp) irTree;
				// If the argument is array the create a IRTreeAggregateData
				if(exp.getExpType() == TreeNodeExpType.MEMORY){
					IRTreeMemory irTreeMemory = (IRTreeMemory)exp;
					Value value = irTreeMemory.getMemory();
					Type type = value.getType();
					if(type.isPointerType()){
						PointerType pointerType = (PointerType)type;
						Type containedType = pointerType.getContainedType();
						if(containedType.isArrayType()){
							List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
							createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType);
							exp = new IRTreeAggregateData(irTreeMemory, indexVsType );
						}
					}
				}
				if(exp.getExpType() == TreeNodeExpType.CONST_EXP){
					if(funcName != null && funcName.equals("malloc")){
						IRTreeConst irTreeConst = (IRTreeConst)exp;
						Value value = irTreeConst.getExpressionValue();
						value.setType(Type.getInt64Type(compilationContext, true));
					}
				}
				paramList.add(exp);
			}
		}
		Collections.reverse(paramList);
		IRTreeCallExp callExp = new IRTreeCallExp(returnType, paramList, funcMemory, listOfFormalTypes, hasEllipses, isOnlyReadsMemory, return_attrs, func_attrs);
		irTreeStack.push(callExp);

		if(isNoReturn){
			IRTreeUnReachable irTreeUnReachable = new IRTreeUnReachable();
			irTreeStack.push(irTreeUnReachable);
		}
		return translateSeqStatement(irTreeStack);
	}

	public IRTree translateFunctionArgument(Value value, CompilationContext compilationContext){
		Type type = value.getType();
		if(type.isArrayType()){
			Type containedType = ((ArrayType)type).getContainedType();
			PointerType pointerType = null;
			try {
				pointerType = Type.getPointerType(compilationContext, containedType, 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			value.setType(pointerType);
		}
		IRTreeTempOrVar argcValueTree = new IRTreeTempOrVar(value);
		IRTreeArgPassStm argcTreeArgStm = createArgPassTree(argcValueTree);
		valVsMemoryMap.put(argcValueTree, argcTreeArgStm.getMemory());
		return argcTreeArgStm;
	}

	private IRTreeArgPassStm createArgPassTree(IRTreeTempOrVar argValTree){

		Value val = argValTree.getValue();
		Type containedType = val.getType();
		CompilationContext compilationContext = containedType.getCompilationContext();
		PointerType ptrType = null;
		try {
			ptrType = Type.getPointerType(compilationContext, containedType, 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Value newVal = new Value(ptrType);
		newVal.setName(val.getName());
		IRTreeMemory memoryTree = new IRTreeMemory(newVal);
		IRTreeArgPassStm argPassTreeStm = new IRTreeArgPassStm(argValTree, memoryTree);
		return argPassTreeStm;

	}
	
	public IRTreeReturnAlloca createReturnValueTree(Type returnType){
		CompilationContext compilationContext = returnType.getCompilationContext();
		IRTreeMemory memoryTree = null;

		if(!returnType.isVoidType()){
			PointerType ptrType = null;
			try {
				ptrType = Type.getPointerType(compilationContext, returnType, 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			Value newVal = new Value(ptrType);
			newVal.setName(null);
			memoryTree = new IRTreeMemory(newVal);
		}
		else
			memoryTree = new IRTreeMemory(null);

		IRTreeReturnAlloca retValTreeStm = new IRTreeReturnAlloca(memoryTree);
		return retValTreeStm;
	}

	private BinaryOperatorID getAssemOperatorType(int operType, boolean isFloatingPointAssignment) {

		if (operType == BinaryOpExpr.AND)
			return BinaryOperatorID.AND;
		else if (operType == BinaryOpExpr.INCLUSIVE_OR)
			return BinaryOperatorID.OR;
		else if (operType == BinaryOpExpr.EXCLUSIVE_OR)
			return BinaryOperatorID.XOR;
		else if (operType == BinaryOpExpr.DIVIDE){
			if(isFloatingPointAssignment)
				return BinaryOperatorID.FDIV;
			else
				return BinaryOperatorID.SDIV;
		}
		else if (operType == BinaryOpExpr.LEFT_SHIFT)
			return BinaryOperatorID.SHL;
		else if (operType == BinaryOpExpr.MINUS){
			if(isFloatingPointAssignment)
				return BinaryOperatorID.FSUB;
			else
				return BinaryOperatorID.SUB;
		}
		else if (operType == BinaryOpExpr.MODULO){
			if(isFloatingPointAssignment)
				return BinaryOperatorID.FREM;
			else
				return BinaryOperatorID.SREM;
		}
		else if (operType == BinaryOpExpr.MULTIPLY){
			if(isFloatingPointAssignment)
				return BinaryOperatorID.FMUL;
			else
				return BinaryOperatorID.MUL;
		}
		else if (operType == BinaryOpExpr.PLUS){
			if(isFloatingPointAssignment)
				return BinaryOperatorID.FADD;
			else
				return BinaryOperatorID.ADD;
		}
		else if (operType == BinaryOpExpr.RIGHT_SHIFT)
			return BinaryOperatorID.ASHR;

		else
			return null; // Invalid

	}

	public IRTree translateSeqStatement(Stack<IRTree> seq) {
		IRTreeStatementList previousTree = null;

		IRTreeStatementList previouslyPoppedStmList = null;

		while (!seq.isEmpty()) {
			IRTree irTree = seq.pop();

			IRTreeStatement currentTree =  null;
			if(irTree instanceof IRTreeExp){
				// This is an expression; coerce into corresponding statement
				currentTree = new IRTreeExpressionStm((IRTreeExp) irTree);
				previousTree = new IRTreeStatementList(currentTree, previousTree);
				previouslyPoppedStmList = previousTree;
			}
			else if(irTree instanceof IRTreeStatementList){
				// If the first Statement of the 'previouslyPoppedStmList' is IRTreeType, then it 
				// should be put at the beginning
				if(previouslyPoppedStmList != null){
					IRTreeStatement irTreeStatement = previouslyPoppedStmList.getStatement();
					while(irTreeStatement instanceof IRTreeType){
						// Remove the 'IRTreeType' from 'previouslyPoppedStmList'
						previouslyPoppedStmList = previouslyPoppedStmList.getStatementList();
						// Add it at the beginning of 'irTree' StatementList
						irTree = new IRTreeStatementList(irTreeStatement, (IRTreeStatementList)irTree);
						irTreeStatement = previouslyPoppedStmList.getStatement();
					}
				}

				// This is a list of statements; traverse along it and 
				// add it to the main tree
				IRTreeStatementList list = (IRTreeStatementList) irTree;
				IRTreeStatementList leafStatementList = getLeafStatementList(list);
				leafStatementList.setStatementList(previouslyPoppedStmList);
				previouslyPoppedStmList = list;
				previousTree = list;
			}
			else{
				IRTreeStatement currStatement = (IRTreeStatement) irTree;
				IRTreeStatementList newStmList = null;
				ArrayList<IRTreeStatement> typestmts = new ArrayList<IRTreeStatement>();

				if(previouslyPoppedStmList != null){
					IRTreeStatement irTreeStatement = previouslyPoppedStmList.getStatement();
					while(previouslyPoppedStmList != null && irTreeStatement != null){
						if(irTreeStatement.getStatementType() == TreeStatementType.TYPE){
							typestmts.add(irTreeStatement);

							previouslyPoppedStmList = previouslyPoppedStmList.getStatementList();

							if(previouslyPoppedStmList != null)
								irTreeStatement = previouslyPoppedStmList.getStatement();
							continue;
						}
						newStmList = new IRTreeStatementList(currStatement, previouslyPoppedStmList);

						for(IRTreeStatement typeStatement : typestmts){
							newStmList = new IRTreeStatementList(typeStatement, newStmList);
						}

						previouslyPoppedStmList = newStmList; // reset
						previousTree = newStmList;
						break;
					}
				}
				else{
					newStmList = new IRTreeStatementList(currStatement, previouslyPoppedStmList);
					previouslyPoppedStmList = newStmList; // reset
					previousTree = newStmList;
				}
			}
		}

		return previousTree;
	}

	protected IRTreeStatementList getLeafStatementList(IRTreeStatementList list){
		IRTreeStatementList temp = list;
		IRTreeStatementList prev = null;
		while(temp != null){
			prev = temp; 
			temp = temp.getStatementList();
		}

		return prev;
	}

	public IRTree translateReturnExpr(TypeEntryWithAttributes expectedTeWithAttrs, TypeEntryWithAttributes teWithAttrsRetExpr, IRTree assemReturnExpr, CompilationContext compilationContext, IRTree returnValueTree) {

		IRTreeExp expTreeFromStmtList = null;

		// If 'assemReturnExpr' is not just a expr, but a sequence of stmts leading to a expr
		if(assemReturnExpr instanceof IRTreeStatementList){

			// Get the expr tree from 'assemReturnExpr'
			IRTreeStatementList irTreeLeafStmtList = getLeafStatementList((IRTreeStatementList)assemReturnExpr);
			IRTree irTree = irTreeLeafStmtList.getStatement();
			if(irTree instanceof IRTreeExpressionStm){
				IRTreeExpressionStm exprStmt = (IRTreeExpressionStm)irTree;
				expTreeFromStmtList = exprStmt.getExpressionTree();
				// Remove the expr tree from 'assemReturnExpr'
				assemReturnExpr = removeStmtListFromIRTree(irTreeLeafStmtList, (IRTreeStatementList)assemReturnExpr);
			}
			else if(irTree instanceof IRTreeMove){//Might be Postfix or Prefix expr
				IRTreeMove irTreeMove = (IRTreeMove)irTree;
				expTreeFromStmtList = irTreeMove.getSrc();
				if(expTreeFromStmtList instanceof IRTreeBinaryExp){
					IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)expTreeFromStmtList;
					Value value = null;
					if(!postfixExprStack.empty()){
						postfixExprStack.pop();
						IRTreeExp leftExp = binaryExp.getLeft();
						if(leftExp.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
							IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)leftExp;
							value = irTreeTempOrVar.getValue();
						}
						else if(leftExp.getExpType() == TreeNodeExpType.MEMORY){
							IRTreeMemory irTreeMemory = (IRTreeMemory)leftExp;
							value = irTreeMemory.getMemory();
						}
						expTreeFromStmtList = new IRTreeTempOrVar(value);
					}
					else{
						expTreeFromStmtList = binaryExp;
					}
				}
			}
		}
		else if(assemReturnExpr instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)assemReturnExpr;
			Value memory = irTreeMemory.getMemory();
			if(memory != null){
				Type type = memory.getType();
				if(type.isPointerType()){
					PointerType pointerType = (PointerType)type;
					Type containedType = pointerType.getContainedType();
					if(containedType.isAggregateType()){
						if(containedType.isArrayType()){
							List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();

							createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType);
							IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType );
							assemReturnExpr = aggregateData;
						}
						else if(containedType.isStructType()){
							// TODO
						}
					}
				}
			}
		}

		IRTreeExp irTreeExp = null;

		if(expTreeFromStmtList == null)
			irTreeExp = (IRTreeExp)assemReturnExpr;
		else
			irTreeExp = expTreeFromStmtList;

		if(irTreeExp.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
			IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)irTreeExp;
			irTreeExp = valVsMemoryMap.get(irTreeTempOrVar);

			if(irTreeExp == null)
				irTreeExp = irTreeTempOrVar;
		}

		// If expected type and return type is not same then we need a Casting Expr
		BasicType expectedBasicType = expectedTeWithAttrs.getBasicType();
		Type expectedReturnType = getLLVMType(expectedBasicType, expectedTeWithAttrs.isUnsigned(), compilationContext);

		BasicType originalBasicType = teWithAttrsRetExpr.getBasicType();
		Type originalReturnType = getLLVMType(originalBasicType, teWithAttrsRetExpr.isUnsigned(), compilationContext);

		IRTreeReturn irTreeRet = new IRTreeReturn(irTreeExp, expectedReturnType, originalReturnType);

		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(globalReturnLabel != null){
			if(assemReturnExpr != null && !(assemReturnExpr instanceof IRTreeMemory))
				irTreeStack.push(assemReturnExpr);

			IRTreeUnConditionalBranch unconditionalBr = new IRTreeUnConditionalBranch(globalReturnLabel);
			irTreeExp = irTreeRet.getExpTree();
			originalReturnType = irTreeRet.getOriginalReturnType();

			if(originalReturnType == Type.getVoidType(compilationContext)){
				irTreeStack.push(unconditionalBr);
				return (IRTreeStatementList) translateSeqStatement(irTreeStack);
			}

			IRTreeReturnAlloca irTreeReturnValue = (IRTreeReturnAlloca)returnValueTree;
			IRTreeMemory destExp = irTreeReturnValue.getMemory();
			IRTreeMove irTreeMove = new IRTreeMove(destExp, irTreeExp);
			irTreeStack.push(irTreeMove);
			irTreeStack.push(unconditionalBr);
			return (IRTreeStatementList)translateSeqStatement(irTreeStack);
		}

		if(expTreeFromStmtList != null){
			irTreeStack.push(assemReturnExpr);
			irTreeStack.push(irTreeRet);
			IRTree irTree = translateSeqStatement(irTreeStack);
			return irTree;
		}
		return irTreeRet;
	}

	public IRTree translateJump(int jmpStmtType, Label label) {
		IRTreeUnConditionalBranch unConditionalJump = null;
		if(jmpStmtType == JumpStatement.BREAK){
			unConditionalJump = new IRTreeUnConditionalBranch(label);
			//unConditionalJump.setBreak(true);
		}
		else if(jmpStmtType == JumpStatement.CONTINUE){
			unConditionalJump = new IRTreeUnConditionalBranch(label);
			//unConditionalJump.setContinue(true);
		}
		return unConditionalJump;
	}

	public IRTree translateValue(Value value, boolean isParam) {


		if(value instanceof Constant)
			return new IRTreeConst((Constant) value);

		Type typeOfValue = value.getType();
		if(typeOfValue.isPointerType()){
			PointerType pointerTyp = (PointerType)typeOfValue;
			Type containedTyp = pointerTyp.getContainedType();

			if(containedTyp.isArrayType() && isParam){
				ArrayType arrayType = (ArrayType)containedTyp;
				CompilationContext compilationContext = arrayType.getCompilationContext();
				PointerType pointerType = null;
				try {
					pointerType = Type.getPointerType(compilationContext, arrayType, 0);
				} catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				Value value2 = new Value(pointerType);
				value2.setName(value.getName());
				IRTreeTempOrVar irTree = new IRTreeTempOrVar(value2);
				IRTreeMemory irTreeMemory = valVsMemoryMap.get(irTree);
				return irTreeMemory;
			}
			else if(containedTyp.isFunctionType()){
				return new IRTreeMemory(value);
			}
			// Of pointer type, return the "value-at" for this address
			return new IRTreeMemory(value);
		}
		else{
			// Of simple type, return the same value
			IRTree irTree = new IRTreeTempOrVar(value);

			if(isParam){

				if(typeOfValue.isArrayType()){
					ArrayType arrayType = (ArrayType)typeOfValue;
					Type containedType = arrayType.getContainedType();
					CompilationContext compilationContext = containedType.getCompilationContext();
					PointerType pointerType = null;
					try {
						pointerType = Type.getPointerType(compilationContext, containedType, 0);
					} catch (TypeCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					Value value2 = new Value(pointerType);
					value2.setName(value.getName());
					irTree = new IRTreeTempOrVar(value2);
				}

				irTree = valVsMemoryMap.get(irTree);
			}

			return irTree;
		} 


	}

	public IRTree translateConstant(int i, CompilationContext compilationContext) {
		APInt val = new APInt(32, new Integer(i).toString(), true);
		ConstantInt constInt = null;
		try {
			constInt = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst = new IRTreeConst(constInt);
		return irTreeConst;
	}

	public IRTree translateBinOp(int operType, IRTree assemTypeLeft,
			IRTree assemTypeRight, boolean isFloatingPointAssignment) {

		BinaryOperatorID  binaryOperatorID = getAssemOperatorType(operType, isFloatingPointAssignment);

		if((assemTypeLeft instanceof IRTreeConst) && (assemTypeRight instanceof IRTreeConst)){
			return getConstantValueIRTree(assemTypeLeft, assemTypeRight, binaryOperatorID);
		}
		else if((assemTypeLeft instanceof IRTreeStatementList) && (assemTypeRight instanceof IRTreeStatementList)){
			Stack<IRTree> irTreeStack = new Stack<IRTree>();
			IRTreeStatementList leftIrTreeStatementList = (IRTreeStatementList)assemTypeLeft;
			IRTreeStatementList rightIrTreeStatementList = (IRTreeStatementList)assemTypeRight;

			IRTreeStatementList leafLeftIrTreeStatementList = getLeafStatementList(leftIrTreeStatementList);
			IRTreeStatementList leafRightIrTreeStatementList = getLeafStatementList(rightIrTreeStatementList);

			IRTreeStatement leafLeftIrTreeStatement = leafLeftIrTreeStatementList.getStatement();
			IRTreeStatement leafRightIrTreeStatement = leafRightIrTreeStatementList.getStatement();


			if((leafLeftIrTreeStatement instanceof IRTreeExpressionStm) && (leafRightIrTreeStatement instanceof IRTreeExpressionStm)){
				IRTreeExpressionStm leafLeftExpressionStm = (IRTreeExpressionStm)leafLeftIrTreeStatement;
				IRTreeExpressionStm leafRightExpressionStm = (IRTreeExpressionStm)leafRightIrTreeStatement;

				IRTreeExp leftIrTreeExp = leafLeftExpressionStm.getExpressionTree();
				IRTreeExp rightIrTreeExp = leafRightExpressionStm.getExpressionTree();

				if((leftIrTreeExp.getExpType() == TreeNodeExpType.AGR_EXP) && (rightIrTreeExp.getExpType() == TreeNodeExpType.AGR_EXP)){
					IRTreeAggregateData leftAggregateData = (IRTreeAggregateData)leftIrTreeExp;
					IRTreeAggregateData rightAggregateData = (IRTreeAggregateData)rightIrTreeExp;
					IRTreeBinaryExp irTreeBinaryExp = new IRTreeBinaryExp(binaryOperatorID,
							(IRTreeExp) leftAggregateData, (IRTreeExp) rightAggregateData);

					irTreeStack.push(leftIrTreeStatementList);
					irTreeStack.push(rightIrTreeStatementList);
					irTreeStack.push(irTreeBinaryExp);

					return translateSeqStatement(irTreeStack);
				}
				else{
					assemTypeLeft = leftIrTreeExp;
					assemTypeRight = rightIrTreeExp;
				}
			}
		}
		else if((assemTypeLeft instanceof IRTreeStatementList)){
			IRTreeStatementList leftIrTreeStatementList = (IRTreeStatementList)assemTypeLeft;
			IRTreeStatementList leafLeftIrTreeStatementList = getLeafStatementList(leftIrTreeStatementList);
			IRTreeStatement leafLeftIrTreeStatement = leafLeftIrTreeStatementList.getStatement();

			if((leafLeftIrTreeStatement instanceof IRTreeExpressionStm)){
				IRTreeExpressionStm leafLeftExpressionStm = (IRTreeExpressionStm)leafLeftIrTreeStatement;
				assemTypeLeft = removeStmtListFromIRTree(leafLeftIrTreeStatementList, (IRTreeStatementList)assemTypeLeft);
				IRTreeExp leftIrTreeExp = leafLeftExpressionStm.getExpressionTree();
				assemTypeLeft = leftIrTreeExp;
			}
		}
		else if((assemTypeRight instanceof IRTreeStatementList)){
			IRTreeStatementList rightIrTreeStatementList = (IRTreeStatementList)assemTypeRight;
			IRTreeStatementList leafRightIrTreeStatementList = getLeafStatementList(rightIrTreeStatementList);
			IRTreeStatement leafRightIrTreeStatement = leafRightIrTreeStatementList.getStatement();

			if((leafRightIrTreeStatement instanceof IRTreeExpressionStm)){
				IRTreeExpressionStm leafRightExpressionStm = (IRTreeExpressionStm)leafRightIrTreeStatement;
				assemTypeRight = removeStmtListFromIRTree(leafRightIrTreeStatementList, (IRTreeStatementList)assemTypeRight);
				IRTreeExp rightIrTreeExp = leafRightExpressionStm.getExpressionTree();
				assemTypeRight = rightIrTreeExp;
			}
		}

		IRTreeBinaryExp irTreeBinaryExp = new IRTreeBinaryExp(binaryOperatorID,	(IRTreeExp) assemTypeLeft, (IRTreeExp) assemTypeRight);
		return irTreeBinaryExp;
	}

	private IRTree getConstantValueIRTree(IRTree assemTypeLeft,	IRTree assemTypeRight, BinaryOperatorID binaryOperatorID) {

		IRTreeConst irTreeConst = null;

		IRTreeConst irTreeConst1 = (IRTreeConst)assemTypeLeft;
		IRTreeConst irTreeConst2 = (IRTreeConst)assemTypeRight;

		Value value = irTreeConst2.getExpressionValue();
		Type type = value.getType();
		CompilationContext compilationContext = type.getCompilationContext();

		APFloat leftApFloat = irTreeConst1.getApFloat();
		APInt leftApInt = irTreeConst1.getApInt();
		APFloat rightApFloat = irTreeConst2.getApFloat();
		APInt rightApInt = irTreeConst2.getApInt();

		String leftVal = null;
		String rightVal = null;
		Integer leftIntVal = null;
		Float leftFloatVal = null;
		Integer rightIntVal = null;
		Float rightFloatVal = null;

		if(leftApFloat != null){
			leftVal = leftApFloat.toString();
			leftFloatVal = Float.valueOf(leftVal);
		}
		else if(leftApInt != null){
			leftVal = leftApInt.getVal();
			leftIntVal = Integer.valueOf(leftVal);
		}
		if(rightApFloat != null){
			rightVal = rightApFloat.toString();
			rightFloatVal = Float.valueOf(rightVal);
		}
		else if(rightApInt != null){
			rightVal = rightApInt.getVal();
			rightIntVal = Integer.valueOf(rightVal);
		}

		if(leftIntVal != null && rightIntVal != null){
			Integer result = null;

			if(binaryOperatorID == BinaryOperatorID.ADD)
				result = leftIntVal + rightIntVal;
			else if(binaryOperatorID == BinaryOperatorID.SUB)
				result = leftIntVal - rightIntVal;
			else if(binaryOperatorID == BinaryOperatorID.SHL)
				result = leftIntVal << rightIntVal;
			else if(binaryOperatorID == BinaryOperatorID.ASHR)
				result = leftIntVal >> rightIntVal;
			else if(binaryOperatorID == BinaryOperatorID.MUL){
				result = leftIntVal * rightIntVal;
			}
			else if(binaryOperatorID == BinaryOperatorID.SDIV){
				result = leftIntVal / rightIntVal;
			}
			else if(binaryOperatorID == BinaryOperatorID.OR){
				result = (leftIntVal == 0)? (rightIntVal == 0)? 0 : 1 : 1;
			}
			else if(binaryOperatorID == BinaryOperatorID.AND){
				result = (leftIntVal == 0)? 0 : (rightIntVal == 0)? 0 : 1;
			}

			String val = result.toString();
			APInt apInt = new APInt(32, val, true);
			ConstantInt constantInt = null;
			try {
				constantInt = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantInt);
			return irTreeConst;
		}
		else if(leftIntVal != null && rightFloatVal != null){
			Float result = null;

			if(binaryOperatorID == BinaryOperatorID.FADD)
				result = leftIntVal + rightFloatVal;
			else if(binaryOperatorID == BinaryOperatorID.FSUB)
				result = leftIntVal - rightFloatVal;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}
		else if(leftFloatVal != null && rightIntVal != null){
			Float result = null;

			if(binaryOperatorID == BinaryOperatorID.FADD)
				result = leftFloatVal + rightIntVal;
			else if(binaryOperatorID == BinaryOperatorID.FSUB)
				result = leftFloatVal - rightIntVal;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}
		else if(leftFloatVal != null && rightFloatVal != null){
			Float result = null;

			if(binaryOperatorID == BinaryOperatorID.FADD)
				result = leftFloatVal + rightFloatVal;
			else if(binaryOperatorID == BinaryOperatorID.FSUB)
				result = leftFloatVal - rightFloatVal;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}

		return irTreeConst;
	}

	private IRTree getConstantValueIRTreeForComparisionOp(IRTree assemTypeLeft,	IRTree assemTypeRight, Predicate predicate) {

		IRTreeConst irTreeConst = null;

		IRTreeConst irTreeConst1 = (IRTreeConst)assemTypeLeft;
		IRTreeConst irTreeConst2 = (IRTreeConst)assemTypeRight;

		Value value = irTreeConst2.getExpressionValue();
		CompilationContext compilationContext = value.getContext();

		APFloat leftApFloat = irTreeConst1.getApFloat();
		APInt leftApInt = irTreeConst1.getApInt();
		APFloat rightApFloat = irTreeConst2.getApFloat();
		APInt rightApInt = irTreeConst2.getApInt();

		String leftVal = null;
		String rightVal = null;
		Integer leftIntVal = null;
		Float leftFloatVal = null;
		Integer rightIntVal = null;
		Float rightFloatVal = null;

		if(leftApFloat != null){
			leftVal = leftApFloat.toString();
			leftFloatVal = Float.valueOf(leftVal);
		}
		else if(leftApInt != null){
			leftVal = leftApInt.getVal();
			leftIntVal = Integer.valueOf(leftVal);
		}
		if(rightApFloat != null){
			rightVal = rightApFloat.toString();
			rightFloatVal = Float.valueOf(rightVal);
		}
		else if(rightApInt != null){
			rightVal = rightApInt.getVal();
			rightIntVal = Integer.valueOf(rightVal);
		}

		if(leftIntVal != null && rightIntVal != null){
			Integer result = null;

			if(predicate == Predicate.ICMP_EQ)
				result = (leftIntVal == rightIntVal)? 1 : 0;
			else if(predicate == Predicate.ICMP_NE)
				result = (leftIntVal != rightIntVal)? 1 : 0;
			else if(predicate == Predicate.ICMP_SGE || predicate == Predicate.ICMP_UGE)
				result = (leftIntVal >= rightIntVal)? 1 : 0;
			else if(predicate == Predicate.ICMP_SGT || predicate == Predicate.ICMP_UGT)
				result = (leftIntVal > rightIntVal)? 1 : 0;
			else if(predicate == Predicate.ICMP_SLE || predicate == Predicate.ICMP_ULE){
				result = (leftIntVal <= rightIntVal)? 1 : 0;
			}
			else if(predicate == Predicate.ICMP_SLT || predicate == Predicate.ICMP_ULT){
				result = (leftIntVal < rightIntVal)? 1 : 0;
			}

			String val = result.toString();
			APInt apInt = new APInt(32, val, true);
			ConstantInt constantInt = null;
			try {
				constantInt = new ConstantInt(Type.getInt32Type(compilationContext, true), apInt);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantInt);
			return irTreeConst;
		}
		else if(leftIntVal != null && rightFloatVal != null){
			Double result = null;
			Double leftDoubleVal = leftIntVal.doubleValue();
			Double rightDoubleVal = rightFloatVal.doubleValue();
			if(predicate == Predicate.FCMP_OEQ || predicate == Predicate.FCMP_UEQ)
				result = (leftDoubleVal == rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGE || predicate == Predicate.FCMP_UGE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGT || predicate == Predicate.FCMP_UGT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLE || predicate == Predicate.FCMP_ULE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLT || predicate == Predicate.FCMP_ULT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_ONE || predicate == Predicate.FCMP_UNE)
				result = (leftDoubleVal != rightDoubleVal)? 1.0 : 0.0;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}
		else if(leftFloatVal != null && rightIntVal != null){
			Double result = null;
			Double leftDoubleVal = leftFloatVal.doubleValue();
			Double rightDoubleVal = rightIntVal.doubleValue();
			if(predicate == Predicate.FCMP_OEQ || predicate == Predicate.FCMP_UEQ)
				result = (leftDoubleVal == rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGE || predicate == Predicate.FCMP_UGE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGT || predicate == Predicate.FCMP_UGT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLE || predicate == Predicate.FCMP_ULE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLT || predicate == Predicate.FCMP_ULT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_ONE || predicate == Predicate.FCMP_UNE)
				result = (leftDoubleVal != rightDoubleVal)? 1.0 : 0.0;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}
		else if(leftFloatVal != null && rightFloatVal != null){
			Double result = null;
			Double leftDoubleVal = leftFloatVal.doubleValue();
			Double rightDoubleVal = rightFloatVal.doubleValue();
			if(predicate == Predicate.FCMP_OEQ || predicate == Predicate.FCMP_UEQ)
				result = (leftDoubleVal == rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGE || predicate == Predicate.FCMP_UGE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OGT || predicate == Predicate.FCMP_UGT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLE || predicate == Predicate.FCMP_ULE)
				result = (leftDoubleVal >= rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_OLT || predicate == Predicate.FCMP_ULT)
				result = (leftDoubleVal > rightDoubleVal)? 1.0 : 0.0;
			else if(predicate == Predicate.FCMP_ONE || predicate == Predicate.FCMP_UNE)
				result = (leftDoubleVal != rightDoubleVal)? 1.0 : 0.0;

			String val = result.toString();
			APFloat apFloat = new APFloat(APFloat.IEEEdouble, val);
			ConstantFP constantFP = null;
			try {
				constantFP = new ConstantFP(Type.getFloatType(compilationContext), apFloat);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			irTreeConst = new IRTreeConst(constantFP);
			return irTreeConst;
		}

		return irTreeConst;
	}

	public IRTree addPostfixTree(IRTree translatedAssignExp,
			HashMap<IRTreeExp, Integer> postfixOperativeExprs) {

		IRTreeStatementList stmOfAssignmentStm = null;

		if (translatedAssignExp instanceof IRTreeStatementList) {
			stmOfAssignmentStm = (IRTreeStatementList)translatedAssignExp;
		} 

		Stack<IRTree> seqStack = new Stack<IRTree>();
		if (stmOfAssignmentStm != null)
			seqStack.push(stmOfAssignmentStm);

		Set<IRTreeExp> postfixExprTrees = postfixOperativeExprs.keySet();
		Iterator<IRTreeExp> assemExprTreeIter = postfixExprTrees.iterator();
		while (assemExprTreeIter.hasNext()) {
			IRTreeExp assemExpTree = assemExprTreeIter.next();
			int operatorType = postfixOperativeExprs.get(assemExpTree);
			boolean isIncrement = true;
			if (operatorType == PostfixExpr.DECR)
				isIncrement = false;

			IRTreeExp incrementedTree = null;
			try {
				incrementedTree = translateIncrementOrDecrementOp(assemExpTree, isIncrement);
				postfixExprStack.push(incrementedTree);
			} catch (InstructionCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}

			seqStack.push(incrementedTree);

			IRTreeMove irTreeMove = new IRTreeMove(assemExpTree, incrementedTree);
			seqStack.push(irTreeMove);
		}

		IRTree allStms = translateSeqStatement(seqStack);

		// Here we put tempOrVar as source of the original postfix assignment, which we will load it from memory, before performing the binary expression
		IRTreeStatement firstStmt = ((IRTreeStatementList)allStms).getStatement();

		// For normal postfix
		if(firstStmt instanceof IRTreeExpressionStm){
			IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)firstStmt;
			IRTreeExp treeExp = expressionStm.getExpressionTree();
			if(treeExp.getExpType() == TreeNodeExpType.BINARY_EXP)
				return allStms;
			// Must be a post fix pointer expression
			else if(postfixExprStack.peek().getExpType() == TreeNodeExpType.AGR_EXP){
				// Remove the first two Memory instructions
				IRTreeStatementList secondStmtList = ((IRTreeStatementList)allStms).getStatementList();
				IRTreeStatement secondMemoryTree = secondStmtList.getStatement();
				IRTreeStatementList secondMemoryTreeStmtList = new IRTreeStatementList(secondMemoryTree, null);
				IRTreeStatementList finalStmtList = secondStmtList.getStatementList();
				if(finalStmtList != null){
					IRTreeStatementList moveAgrStmtList = finalStmtList.getStatementList();
					moveAgrStmtList.setStatementList(secondMemoryTreeStmtList);
					return finalStmtList;
				}
				else{
					return allStms;
				}
			}
		}

		return translatePostfixTree(allStms);
	}


	private IRTree translatePostfixTree(IRTree finalTree) {
		IRTreeExp expTreeFromStmtList = null;

		if(finalTree instanceof IRTreeStatementList){

			IRTreeStatementList irTreeLeafStmtList = getLeafStatementList((IRTreeStatementList)finalTree);
			IRTree irTree = irTreeLeafStmtList.getStatement();

			if(irTree instanceof IRTreeMove){//Must be a Postfix expr

				IRTreeMove irTreeMove = (IRTreeMove)irTree;
				expTreeFromStmtList = irTreeMove.getSrc();

				if(expTreeFromStmtList instanceof IRTreeBinaryExp){
					IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)expTreeFromStmtList;
					Value value = null;
					postfixExprStack.pop();
					IRTreeExp leftExp = binaryExp.getLeft();

					if(leftExp.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
						IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)leftExp;
						value = irTreeTempOrVar.getValue();
					}
					else if(leftExp.getExpType() == TreeNodeExpType.MEMORY){
						IRTreeMemory irTreeMemory = (IRTreeMemory)leftExp;
						value = irTreeMemory.getMemory();
					}
					else if(leftExp.getExpType() == TreeNodeExpType.AGR_EXP){
						IRTreeAggregateData aggregateData = (IRTreeAggregateData)leftExp;
						List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
						int index = entries.size() - 1;
						Entry<IRTreeIndex, Type> entry = entries.get(index);
						Type type = entry.getValue();
						value = new Value(type);
					}

					expTreeFromStmtList = new IRTreeTempOrVar(value);
				}
				else if(expTreeFromStmtList instanceof IRTreeAggregateData){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)expTreeFromStmtList;
					IRTree irTree2 = aggregateData.getAggregateDataStruct();
					if(irTree2 instanceof IRTreeMemory){
						IRTreeMemory irTreeMemory = (IRTreeMemory)irTree2;
						Value value = irTreeMemory.getMemory();
						expTreeFromStmtList = new IRTreeTempOrVar(value );
					}
				}
			}
		}

		IRTreeExp irTreeExp = null;

		IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)expTreeFromStmtList;
		irTreeExp = valVsMemoryMap.get(irTreeTempOrVar);

		if(irTreeExp == null)
			irTreeExp = irTreeTempOrVar;

		// Here we put tempOrVar as source of the original postfix assignment, which we will load it from memory, before performing the binary expression
		IRTreeStatement firstStmt = ((IRTreeStatementList)finalTree).getStatement();

		IRTreeStatementList irTreeStatementLst = (IRTreeStatementList)finalTree;
		while(irTreeStatementLst != null){
			IRTreeStatement irTreeStatement = irTreeStatementLst.getStatement();
			if(irTreeStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
				IRTreeExp irTreeExp2 = expressionStm.getExpressionTree();
				if(irTreeExp2 instanceof IRTreeAggregateData){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp2;
					List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
					Iterator<Entry<IRTreeIndex, Type>> iterator = entries.iterator();
					while(iterator.hasNext()){
						Entry<IRTreeIndex, Type> entry = iterator.next();
						IRTreeIndex irTreeIndex = entry.getKey();
						IRTree irTree = irTreeIndex.getIrTree();
						if(irTree instanceof IRTreeMemory){
							IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
							Value value = irTreeMemory.getMemory();
							Value value2 = ((IRTreeTempOrVar)irTreeExp).getValue();
							if(value.equals(value2)){
								firstStmt = expressionStm;
							}
						}
					}
				}
			}
			irTreeStatementLst = irTreeStatementLst.getStatementList();
		}

		if(firstStmt instanceof IRTreeMove){
			IRTreeMove actualPostfixAssignment = (IRTreeMove)firstStmt;
			actualPostfixAssignment.setSrc(irTreeExp);
		}
		else if(firstStmt instanceof IRTreeExpressionStm){
			IRTreeExpressionStm irTreeExpressionStm = (IRTreeExpressionStm)firstStmt;
			IRTreeExp irTreeExp2 = irTreeExpressionStm.getExpressionTree();
			if(irTreeExp2 instanceof IRTreeCallExp){
				IRTreeCallExp callExp = (IRTreeCallExp)irTreeExp2;
				modifyCallExpr(callExp, irTreeExp);
			}
			else if(irTreeExp2 instanceof IRTreeAggregateData){
				modifyAggregateExpr((IRTreeAggregateData)irTreeExp2, irTreeExp);
			}
			else{
				// TODO
			}
		}
		// Here we put the postfix expression at the top
		if((firstStmt instanceof IRTreeMove) || (firstStmt instanceof IRTreeExpressionStm)){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)finalTree;
			// This is the Move expression 
			IRTreeStatementList moveIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(moveIrTreeStatementList, irTreeStatementList);
			// This is the Binary Expression
			IRTreeStatementList binExprIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(binExprIrTreeStatementList, irTreeStatementList);

			// Now add them at the top
			binExprIrTreeStatementList.setStatementList(irTreeStatementList);
			moveIrTreeStatementList.setStatementList(binExprIrTreeStatementList);

			return moveIrTreeStatementList;
		}

		return finalTree;
	}

	private void modifyCallExpr(IRTreeCallExp callExp, IRTreeExp irTreeExp) {
		List<IRTree> irTrees = callExp.getParamList();
		Iterator<IRTree> iterator = irTrees.iterator();
		while(iterator.hasNext()){
			IRTree irTree = iterator.next();
			if(irTree instanceof IRTreeStatementList){
				IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
				IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
				IRTreeStatement irTreeStatement = leafStatementList.getStatement();
				if(irTreeStatement instanceof IRTreeExpressionStm){
					IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTreeStatement;
					IRTreeExp irTreeExp2 = expressionStm.getExpressionTree();
					if(irTreeExp2.getExpType() == TreeNodeExpType.AGR_EXP){
						IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp2;
						modifyAggregateExpr(aggregateData, irTreeExp);
					}
					else{
						// TODO
					}
				}
				else{
					// TODO
				}
			}
			else if(irTree instanceof IRTreeMemory){
				int index = irTrees.indexOf(irTree);
				irTrees.set(index, irTreeExp);
				callExp.setParamList(irTrees);
			}
			else{
				// TODO
			}
		}
	}

	private void modifyAggregateExpr(IRTreeAggregateData aggregateData,
			IRTreeExp irTreeExp) {
		Value value2 = null;
		if(irTreeExp instanceof IRTreeTempOrVar){
			IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)irTreeExp;
			value2 = irTreeTempOrVar.getValue();
		}
		else if(irTreeExp instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)irTreeExp;
			value2 = irTreeMemory.getMemory();
		}

		IRTree irTree = aggregateData.getAggregateDataStruct();
		if(irTree instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
			Value value = irTreeMemory.getMemory();
			if(value.equals(value2)){
				aggregateData.setAggregateDataStruct(irTreeExp);
			}
		}
		else{
			// TODO
		}
		List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
		Iterator<Entry<IRTreeIndex, Type>> iterator = entries.iterator();
		while(iterator.hasNext()){
			IRTreeIndex irTreeIndex = iterator.next().getKey();
			IRTree irTree2 = irTreeIndex.getIrTree();
			if(irTree2 instanceof IRTreeMemory){
				IRTreeMemory irTreeMemory = (IRTreeMemory)irTree2;
				Value value = irTreeMemory.getMemory();
				if(value.equals(value2)){
					irTreeIndex.setIrTree(irTreeExp);
				}
			}
			else if(irTree2 instanceof IRTreeAggregateData){
				modifyAggregateExpr((IRTreeAggregateData)irTree2, irTreeExp);
			}
		}
	}


	public IRTree translateBinPointerExpr(int binaryOpExprType,
			IRTree pointerIRTree, IRTree indexIRTree) {

		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		boolean isNegative = false;

		BinaryOperatorID  binaryOperatorID = getAssemOperatorType(binaryOpExprType, false);

		if(binaryOperatorID == BinaryOperatorID.SUB){
			isNegative = true;
		}

		IRTreeIndex irTreeIndex = new IRTreeIndex(indexIRTree, isNegative);

		Type type = null;
		if(pointerIRTree instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)pointerIRTree;
			type = irTreeMemory.getMemory().getType();
			if(type.isPointerType()){
				PointerType pointerType = (PointerType)type;
				type = pointerType.getContainedType();

				if(type.isArrayType()){
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					CompilationContext compilationContext = type.getCompilationContext();
					createTwoZeroIndexes(compilationContext , pointerType, type , indexVsType);
					IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType );

					// We know there are 2 indexes
					type = aggregateData.getIndexVsType().get(1).getValue();

					indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					Entry<IRTreeIndex,Type> entry_indexVsType = getMapEntry(irTreeIndex, type);
					indexVsType.add(entry_indexVsType);

					PointerType pointerType2 = null;
					try {
						pointerType2 = Type.getPointerType(compilationContext, type, 0);
					} catch (TypeCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
					pointerIRTree = new IRTreeMemory(new Value(pointerType2), aggregateData);
					IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(pointerIRTree, indexVsType);

					irTreeStack.push(aggregateData);
					irTreeStack.push(aggregateData2);

					return translateSeqStatement(irTreeStack);
				}
			}
		}
		else if(pointerIRTree instanceof IRTreeAggregateData){
			IRTreeAggregateData aggregateData = (IRTreeAggregateData)pointerIRTree;
			List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
			int lastIndex = entries.size() - 1;
			Entry<IRTreeIndex, Type> entry = entries.get(lastIndex);
			type = entry.getValue();
		}
		else if(pointerIRTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)pointerIRTree;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafIrTreeStatement = leafIrTreeStatementList.getStatement();
			if(leafIrTreeStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafIrTreeStatement;
				IRTreeExp exp = expressionStm.getExpressionTree();
				if(exp.getExpType() == TreeNodeExpType.AGR_EXP){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)exp;
					List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
					int lastIndex = entries.size() - 1;
					Type type2 = entries.get(lastIndex).getValue();
					CompilationContext compilationContext = type2.getCompilationContext();


					PointerType pointerType = null;

					if(type2.isPointerType()){
						pointerType = (PointerType)type2;
					}
					else{
						try {
							pointerType = Type.getPointerType(compilationContext, type2, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
					}

					Value value = new Value(pointerType);
					List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
					Entry<IRTreeIndex, Type> entry = getMapEntry(irTreeIndex, pointerType);
					indexVsType.add(entry);
					IRTree aggregateDataStruct = new IRTreeMemory(value, aggregateData);
					IRTreeAggregateData aggregateData2 = new IRTreeAggregateData(aggregateDataStruct , indexVsType );
					irTreeStack.push(pointerIRTree);
					irTreeStack.push(aggregateData2);

					return translateSeqStatement(irTreeStack);
				}
			}
		}

		Entry<IRTreeIndex,Type> entry_indexVsType = getMapEntry(irTreeIndex, type);
		List<Entry<IRTreeIndex,Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
		indexVsType.add(entry_indexVsType);
		IRTreeAggregateData aggregateData = new IRTreeAggregateData(pointerIRTree, indexVsType);
		return aggregateData;
	}

	/**
	 * This function translates something like arr[0] or arr[var] and gives back the 
	 * corresponding IRTreeAggregateData, which ultimately will create the 'getElementPtr'
	 * instruction
	 * @param arrayRefTree
	 * @param indexTree
	 * @return IRTree
	 */
	public IRTree translateArrayReference(IRTree arrayRefTree, IRTree indexTree, CompilationContext compilationContext) {
		List<Entry<IRTreeIndex,Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(arrayRefTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)arrayRefTree;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafStatementList.getStatement();

			IRTree aggrStmtList = null;

			if(leafStatement instanceof IRTreeExpressionStm){

				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();

				if(irTreeExp instanceof IRTreeAggregateData){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp;
					IRTree irTree = aggregateData.getAggregateDataStruct();

					if(irTree instanceof IRTreeMemory){
						IRTreeMemory irTreeMemory = (IRTreeMemory)irTree;
						Value value = irTreeMemory.getMemory();
						Type type2 = value.getType();
						compilationContext = type2.getCompilationContext();

						if(type2.isPointerType()){
							PointerType pointerType = (PointerType)type2;
							Type containedType = pointerType.getContainedType();

							if(containedType.isArrayType()){
								ArrayType arrayType = (ArrayType)containedType;
								containedType = arrayType.getContainedType();
								PointerType pointerType2 = null;
								try {
									pointerType2 = Type.getPointerType(compilationContext, containedType, 0);
								} catch (TypeCreationException e) {
									e.printStackTrace();
									System.exit(-1);
								}
								Value newValue = new Value(pointerType2);
								IRTreeMemory irTreeMemory2 = new IRTreeMemory(newValue, aggregateData);
								aggrStmtList = translateArrayReference(irTreeMemory2, indexTree, compilationContext);
							}
							else{
								List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
								int index = entries.size() - 1;
								Type type = entries.get(index).getValue();

								if(type.isArrayType()){
									ArrayType arrayType = (ArrayType)type;
									PointerType pointerType2 = null;
									try {
										pointerType2 = Type.getPointerType(compilationContext, arrayType, 0);
									} catch (TypeCreationException e) {
										e.printStackTrace();
										System.exit(-1);
									}
									Value value2 = new Value(pointerType2);
									IRTreeMemory irTreeMemory2 = new IRTreeMemory(value2, aggregateData);
									aggrStmtList = translateArrayReference(irTreeMemory2, indexTree, compilationContext);
								}
								else{
									// Pointer to an Array
									PointerType pointerType2 = (PointerType)type;
									Value value2 = new Value(pointerType2);
									IRTreeMemory irTreeMemory2 = new IRTreeMemory(value2, aggregateData);
									aggrStmtList = translateArrayReference(irTreeMemory2, indexTree, compilationContext);
								}
							}
						}
					}
					else if(irTree instanceof IRTreeAggregateData){
						IRTreeAggregateData irTreeAggregateData = (IRTreeAggregateData)irTree;
						List<Entry<IRTreeIndex, Type>> entries = irTreeAggregateData.getIndexVsType();
						int index = entries.size() - 1;
						Type containedType = entries.get(index).getValue();

						if(containedType.isAggregateType()){
							if(containedType.isArrayType()){
								ArrayType arrayType = (ArrayType)containedType;
								Type containedType2 = arrayType.getContainedType();
								if(containedType2.isPointerType()){
									PointerType pointerType = null;
									try {
										pointerType = Type.getPointerType(compilationContext, containedType2, 0);
									} catch (TypeCreationException e) {
										e.printStackTrace();
										System.exit(-1);
									}
									boolean isNegative = false;

									if(indexTree instanceof IRTreeConst){
										IRTreeConst irTreeConst = (IRTreeConst)indexTree;
										Value value = irTreeConst.getExpressionValue();
										APInt apInt = irTreeConst.getApInt();
										String val = apInt.getVal();
										int constValue = Integer.parseInt(val);
										if(constValue < 0)
											isNegative = true;

										apInt.setNumBits(64);
										value.setType(Type.getInt64Type(compilationContext, true));
									}

									IRTreeIndex irTreeIndex = new IRTreeIndex(indexTree, isNegative );
									Entry<IRTreeIndex, Type> entry = getMapEntry(irTreeIndex, containedType2);
									indexVsType.add(entry);
									Value memory = new Value(pointerType);
									IRTreeMemory irTreeMemory = new IRTreeMemory(memory, irTreeExp);
									IRTree aggregateDataStruct = new IRTreeMemory(memory, irTreeMemory);
									aggrStmtList = new IRTreeAggregateData(aggregateDataStruct , indexVsType);

									irTreeStack.push(arrayRefTree);
									irTreeStack.push(aggrStmtList);
									return translateSeqStatement(irTreeStack);
								}
							}
							else if(containedType.isStructType()){
								// TODO
							}
						}

						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, containedType, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						createTwoZeroIndexes(compilationContext, pointerType, containedType, indexVsType);
						Value memory = new Value(pointerType);
						IRTree aggregateDataStruct = new IRTreeMemory(memory, irTreeExp);
						aggrStmtList = new IRTreeAggregateData(aggregateDataStruct , indexVsType);
					}
					// This is the case with Union
					else if(irTree instanceof IRTreeCast){
						List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
						Type indexType = null;
						int index = entries.size() - 1;
						Type type = entries.get(index).getValue();
						if(type.isAggregateType()){
							if(type.isArrayType()){
								ArrayType arrayType = (ArrayType)type;
								Type containedType = arrayType.getContainedType();
								indexType = containedType;
							}
							else if(type.isStructType()){
								// TODO 
							}
						}
						boolean isNegative = checkIfTheIndexIsNegativeOrNot(indexTree, compilationContext);
						IRTreeIndex irTreeIndex = new IRTreeIndex(indexTree, isNegative);
						Entry<IRTreeIndex, Type> entry = getMapEntry(irTreeIndex, indexType);
						entries.add(entry);
						aggregateData.setIndexVsType(entries);
						return aggregateData;
					}
				}
			}
			irTreeStack.push(arrayRefTree);
			irTreeStack.push(aggrStmtList);
			return translateSeqStatement(irTreeStack);
		}

		ConstantInt zeroConst = null;
		try {
			zeroConst = new ConstantInt(Type.getInt32Type(compilationContext, false), new APInt(32, "0", false));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		IRTreeConst zeroConstIRTree = new IRTreeConst(zeroConst);
		IRTreeIndex irTreeIndex1 = new IRTreeIndex(zeroConstIRTree, false);
		boolean isNegative = checkIfTheIndexIsNegativeOrNot(indexTree, compilationContext);
		IRTreeIndex irTreeIndex2 = null;

		if(indexTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)indexTree;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafStatementList.getStatement();
			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				irTreeIndex2 = new IRTreeIndex(irTreeExp, isNegative);
			}
			else if(leafStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
				IRTreeExp irTreeExp = irTreeMove.getSrc();
				irTreeIndex2 = new IRTreeIndex(irTreeExp, isNegative);
			}
			irTreeStack.push(indexTree);
		}
		else
			irTreeIndex2 = new IRTreeIndex(indexTree, isNegative); // TODO May not be always +ve

		IRTreeMemory arrayPtr = null;
		PointerType pointerType = null;

		if(arrayRefTree instanceof IRTreeMemory)
			arrayPtr = (IRTreeMemory)arrayRefTree;

		else if(arrayRefTree instanceof IRTreeAggregateData){
			// If the Array is inside a Structure
			IRTreeAggregateData aggregateData = (IRTreeAggregateData)arrayRefTree;

			List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
			int index = entries.size() - 1;
			Type type = entries.get(index).getValue();

			if(type.isArrayType()){
				PointerType pointerType1 = null;
				try {
					pointerType1 = Type.getPointerType(compilationContext, type, 0);
				} catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				Value value = new Value(pointerType1);
				arrayPtr = new IRTreeMemory(value, aggregateData);
			}
		}
		else if(arrayRefTree instanceof IRTreeCast){
			IRTreeCast irTreeCast = (IRTreeCast)arrayRefTree;
			IRTree destIrTree = irTreeCast.getDestTree();
			if(destIrTree instanceof IRTreeTempOrVar){
				IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)destIrTree;
				pointerType = (PointerType)irTreeTempOrVar.getValue().getType();
				Type containedType = pointerType.getContainedType();
				if(containedType.isPointerType())
					pointerType = (PointerType)containedType;
			}
		}

		if(arrayPtr != null)
			pointerType = (PointerType)arrayPtr.getMemory().getType();

		if(pointerType != null){
			Type containedType = pointerType.getContainedType();

			if(containedType.isArrayType()){
				ArrayType arrayType = (ArrayType)containedType;
				containedType = arrayType.getContainedType();

				Entry<IRTreeIndex,Type> entry_indexVsType1 = getMapEntry(irTreeIndex1, pointerType);
				Entry<IRTreeIndex,Type> entry_indexVsType2 =getMapEntry(irTreeIndex2, containedType);

				indexVsType.add(entry_indexVsType1);
				indexVsType.add(entry_indexVsType2);
			}
			// It is pointer to an Array
			else{
				IRTree irTree = irTreeIndex2.getIrTree();
				if(irTree instanceof IRTreeConst){
					IRTreeConst irTreeConst = (IRTreeConst)irTree;
					APInt apInt = irTreeConst.getApInt();
					if(apInt != null){
						apInt.setNumBits(64);
						Value value = irTreeConst.getExpressionValue();
						value.setType(Type.getInt64Type(compilationContext, true));
					}
				}
				Entry<IRTreeIndex,Type> entry_indexVsType = getMapEntry(irTreeIndex2, containedType);
				indexVsType.add(entry_indexVsType);

				PointerType pointerType2 = null;
				try {
					pointerType2 = Type.getPointerType(compilationContext, containedType, 0);
				} catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				Value memory = new Value(pointerType2);
				IRTreeMemory irTreeMemory = new IRTreeMemory(memory, (IRTreeExp)arrayRefTree);
				IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType);
				irTreeStack.push(aggregateData);

				return translateSeqStatement(irTreeStack);
			}
			IRTreeAggregateData aggregateData = new IRTreeAggregateData(arrayRefTree, indexVsType);
			irTreeStack.push(aggregateData);
		}
		return translateSeqStatement(irTreeStack);
	}

	private boolean checkIfTheIndexIsNegativeOrNot(IRTree indexTree, CompilationContext compilationContext) {
		boolean isNegative = false;
		if(indexTree instanceof IRTreeConst){
			IRTreeConst irTreeConst = (IRTreeConst)indexTree;
			APInt apInt = irTreeConst.getApInt();
			String val = null;
			if(apInt == null){
				APFloat apFloat = irTreeConst.getApFloat();
				val = apFloat.getStrRepresentation();
				float f = Float.parseFloat(val);
				if(f < 0)
					isNegative = true;
			}
			else{
				val = apInt.getVal();
				int constValue = Integer.parseInt(val);
				if(constValue < 0)
					isNegative = true;
				Value value = irTreeConst.getExpressionValue();
				if(apInt != null){
					apInt.setNumBits(64);
					value.setType(Type.getInt64Type(compilationContext, true));
				}
			}
		}
		return isNegative;
	}

	public Type getLLVMType(BasicType basicType, boolean isUnSigned, CompilationContext compilationContext){

		Type returnType = null;

		if(basicType instanceof CharTypeEntry){          // 8 bit integer
			returnType = Type.getInt8Type(compilationContext, !isUnSigned);
		}
		else if(basicType instanceof ShortTypeEntry){   // 16 bits
			returnType = Type.getInt16Type(compilationContext, !isUnSigned);
		}
		else if(basicType instanceof VoidTypeEntry){   // Void type
			returnType = Type.getVoidType(compilationContext);
		}
		if(basicType instanceof IntTypeEntry){         // 32 bits
			returnType = Type.getInt32Type(compilationContext, !isUnSigned);
		}
		if(basicType instanceof PointerTypeEntry){         // 32 bits
			PointerTypeEntry ptrEntry = (PointerTypeEntry) basicType;
			TypeEntryWithAttributes containedTeWithAttrs = ptrEntry.getBaseTypeEntry();
			BasicType basicType2 = containedTeWithAttrs.getBasicType();
			boolean isUnsigned = containedTeWithAttrs.isUnsigned();

			Type containedType = getLLVMType(basicType2, isUnsigned, compilationContext); 
			try {
				returnType = Type.getPointerType(compilationContext, containedType, 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		if(basicType instanceof LongTypeEntry){        // 64 bits
			returnType = Type.getInt64Type(compilationContext, !isUnSigned);
		}
		if(basicType instanceof HalfTypeEntry){        // 16 bits FP
			returnType = Type.getHalfType(compilationContext);
		} 
		if(basicType instanceof FloatTypeEntry){        // 32 bits FP
			returnType = Type.getFloatType(compilationContext);
		}
		if(basicType instanceof DoubleTypeEntry){        // 64 bits
			returnType = Type.getDoubleType(compilationContext);
		}
		if(basicType instanceof ArrayTypeEntry){        // 64 bits
			ArrayTypeEntry arrTypeEntry = (ArrayTypeEntry)basicType;
			TypeEntryWithAttributes typeEntryWithAttributes = arrTypeEntry.getBaseTypeEntry();
			BasicType actualBasicType = typeEntryWithAttributes.getBasicType();
			Type actualIRType = getLLVMType(actualBasicType, false, compilationContext);
			int dimension = 0;
			TypeEntryWithAttributes  dimensionObj = arrTypeEntry.getDimension();

			if(dimensionObj != null && dimensionObj.getLiteralValue() != null && dimensionObj.getLiteralValue().matches("\\d+")){
				String literalValue = dimensionObj.getLiteralValue();
				dimension = Integer.parseInt(literalValue);
			}
			else{
				// Create a pointer type and return it
				PointerType pointerType = null;
				try {
					pointerType = Type.getPointerType(compilationContext, actualIRType, 0);
				} catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				return pointerType;
			}

			try {
				returnType = Type.getArrayType(compilationContext, actualIRType, dimension);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		if(basicType instanceof StructOrUnionTypeEntry){        
			StructOrUnionTypeEntry structOrUnionTypeEntry = (StructOrUnionTypeEntry)basicType;
			String name = structOrUnionTypeEntry.getTag();
			List<Type> listOfType = new ArrayList<Type>();

			if(structOrUnionTypeEntry.isStruct()){

				Vector<String> memberNames = structOrUnionTypeEntry.getMemberNames();

				for(String memberName : memberNames){
					TypeEntryWithAttributes typeAttrs = structOrUnionTypeEntry.getMemberType(memberName);
					isUnSigned = typeAttrs.isUnsigned();
					BasicType basicType2 = typeAttrs.getBasicType();

					Type type = getLLVMType(basicType2, isUnSigned, compilationContext);
					listOfType.add(type);
				}

				try {
					returnType = Type.getStructType(compilationContext, false, "struct."+name, false, listOfType.toArray(new Type[listOfType.size()]));

				} catch (TypeCreationException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
			// Union Type
			else {
				// Sorting the typeVsWidth Map based on Width
				Vector<String> memberNames = structOrUnionTypeEntry.getMemberNames();

				Map<Type, Integer> typeVsAlignment = new LinkedHashMap<Type, Integer>();
				Map<Type, Integer> typeVsSize = new LinkedHashMap<Type, Integer>();

				ComparingBasedOnAlignmentOfType comparingBasedOnAlignmentOfType = new ComparingBasedOnAlignmentOfType(typeVsAlignment);
				ComparingBasedOnSizeOfType comparingBasedOnSizeOfType = new ComparingBasedOnSizeOfType(typeVsSize);

				TreeMap<Type, Integer> sortedMapBasedOnAlignment = new TreeMap<Type, Integer>(comparingBasedOnAlignmentOfType);
				TreeMap<Type, Integer> sortedMapBasedOnSize = new TreeMap<Type, Integer>(comparingBasedOnSizeOfType);

				List<StructType> listOfStructType = new ArrayList<StructType>();

				for(String memberName : memberNames){
					TypeEntryWithAttributes typeAttrs = structOrUnionTypeEntry.getMemberType(memberName);
					isUnSigned = typeAttrs.isUnsigned();
					BasicType basicType2 = typeAttrs.getBasicType();

					Type type = getLLVMType(basicType2, isUnSigned, compilationContext);

					if(type.isAggregateType()){
						if(type.isStructType()){
							StructType structType = (StructType)type;
							listOfStructType.add(structType);
						}
					}

					Integer size = LLVMUtility.getSizeForType(properties, type);
					Integer alignment = AllocaInst.getAlignmentForType(properties, type);
					typeVsAlignment.put(type, alignment);
					typeVsSize.put(type, size);
				}

				sortedMapBasedOnAlignment.putAll(typeVsAlignment);
				sortedMapBasedOnSize.putAll(typeVsSize);

				NavigableSet<Type> descendingTypeBasedOnAlignmentSet = sortedMapBasedOnAlignment.descendingKeySet();
				Type typeWithMaxAlignment = descendingTypeBasedOnAlignmentSet.first();
				int maxAlignment = sortedMapBasedOnAlignment.get(typeWithMaxAlignment);

				// Union contains StructType. Check whether 'typeWithMaxAlignment' is present in the Structure
				if(listOfStructType.size() != 0){
					for(StructType structType : listOfStructType){
						int elementSize = structType.getElementSize();
						for(int i = 0; i < elementSize; i++){
							Type type = structType.getTypeAtIndex(i);
							int alignment = AllocaInst.getAlignmentForType(properties, type);

							if(type == typeWithMaxAlignment)
								typeWithMaxAlignment = structType;

							// If the alignment of any type of member of Structure is more than the
							// alignment of the TypeWithMaxAlignment then the Structure will be the 
							// type with max alignment.
							else if(alignment > maxAlignment)
								typeWithMaxAlignment = structType;
						}
					}
				}

				NavigableSet<Type> descendingTypeBasedOnSizeSet = sortedMapBasedOnSize.descendingKeySet();
				Type typeWithMaxSize = descendingTypeBasedOnSizeSet.first();

				Iterator<Type> typeIterator = descendingTypeBasedOnSizeSet.iterator();

				while(typeIterator.hasNext()){
					Type type = typeIterator.next();
					if(type.isArrayType()){
						ArrayType arrayType = (ArrayType)type;
						Type primitiveType = arrayType.getActualContainedType();
						if(primitiveType.isInt8Type()){
							continue;
						}
					}
					typeWithMaxAlignment = type;
					break;
				}

				Integer maxSize = sortedMapBasedOnSize.get(typeWithMaxSize);
				Integer sizeOfTypeWithMaxAlignment = LLVMUtility.getSizeForType(properties, typeWithMaxAlignment);
				Integer alignmentOfTypeWithMaxAlignment = sortedMapBasedOnAlignment.get(typeWithMaxAlignment);
				ArrayType arrayType = null;

				if(typeWithMaxAlignment != typeWithMaxSize){
					int i = 1;
					while(true){
						Integer size = sizeOfTypeWithMaxAlignment + (alignmentOfTypeWithMaxAlignment * i);
						if(size >= maxSize)
							break;
						else
							i++;
					}
					Integer numElements = alignmentOfTypeWithMaxAlignment * i;

					try {
						arrayType = Type.getArrayType(compilationContext, Type.getInt8Type(compilationContext, false), numElements);
					} catch (TypeCreationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
				listOfType.add(typeWithMaxAlignment);

				if(arrayType != null)
					listOfType.add(arrayType);

				try{
					returnType = Type.getStructType(compilationContext, false, "union."+name, true, listOfType.toArray(new Type[listOfType.size()]));
				}
				catch(TypeCreationException tce){
					tce.printStackTrace();
					System.exit(-1);
				}
			}
		}
		else if(basicType instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry typeDefNameTypeEntry = (TypeDefNameTypeEntry)basicType;
			TypeEntryWithAttributes  typeEntryWithAttributes = typeDefNameTypeEntry.getActualBasicType();
			BasicType basicType2 = typeEntryWithAttributes.getBasicType();
			returnType = getLLVMType(basicType2, isUnSigned, compilationContext);
		}
		else if(basicType instanceof FunctionEntry){
			FunctionEntry functionEntry = (FunctionEntry)basicType;
			// Get the IRType for return type of the function
			TypeEntryWithAttributes returnAttributes = functionEntry.getReturnType();
			BasicType returnBasicType = returnAttributes.getBasicType();
			isUnSigned = returnAttributes.isUnsigned();
			Type retType = getLLVMType(returnBasicType, isUnSigned, compilationContext);
			boolean isVarArgs = functionEntry.isEndsWithEllipses();
			// Get the IRTypes of the function parameters
			Vector<Type> paramTypes = new Vector<Type>();
			Vector<VariableEntry> varEntryForFuncParameters = functionEntry.getFormals();
			if(varEntryForFuncParameters != null){
				Enumeration<VariableEntry> enumeration = varEntryForFuncParameters.elements();
				while(enumeration.hasMoreElements()){
					VariableEntry variableEntry = enumeration.nextElement();
					TypeEntryWithAttributes paramAttributes = variableEntry.getType();
					BasicType basicType2 = paramAttributes.getBasicType();
					isUnSigned = paramAttributes.isUnsigned();
					Type type = getLLVMType(basicType2, isUnSigned, compilationContext);
					paramTypes.addElement(type);
				}
			}
			
			try {
				returnType = Type.getFunctionType(compilationContext, retType, isVarArgs, paramTypes);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else if(basicType instanceof StringTypeEntry){
			try {
				returnType = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, true), 0);
			} catch (TypeCreationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		else if(basicType instanceof EnumSpecTypeEntry){
			returnType = Type.getInt32Type(compilationContext, true);
		}
		return returnType;
	}

	Entry<IRTreeIndex, Type> getMapEntry(IRTreeIndex irTreeIndex, Type type) {
		LinkedHashMap<IRTreeIndex, Type> indxVsTyp = new LinkedHashMap<IRTreeIndex, Type>();
		indxVsTyp.put(irTreeIndex, type);
		Set<Map.Entry<IRTreeIndex, Type>> set_indxVsTyp = indxVsTyp.entrySet();
		List<Map.Entry<IRTreeIndex, Type>> list_indxVsTyp = new ArrayList<Map.Entry<IRTreeIndex, Type>>(set_indxVsTyp);
		Map.Entry<IRTreeIndex, Type> entry_IndexVsType = list_indxVsTyp.get(0);
		return entry_IndexVsType;
	}


	public IRTree createIndexTreeForStructure(Integer index, CompilationContext compilationContext) {
		APInt val = new APInt(32, index.toString(), true);
		ConstantInt constInt = null;
		try {
			constInt = new ConstantInt(Type.getInt32Type(compilationContext, true), val);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst = new IRTreeConst(constInt);
		return irTreeConst;
	}

	public IRTree translateStructureReference(IRTree structureRefTree, IRTree indexTree, Type memberType, CompilationContext compilationContext) {

		Stack<IRTree> irTreeStack = new Stack<IRTree>();
		List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
		IRTreeConst irTreeConstZero = (IRTreeConst)createIndexTreeForStructure(0, compilationContext);
		IRTreeIndex irTreeIndex1 = new IRTreeIndex(irTreeConstZero, false);
		IRTreeIndex irTreeIndex2 = new IRTreeIndex(indexTree, false);
		Type type = null;
		IRTreeAggregateData irTreeAggregateData = null;

		if(structureRefTree instanceof IRTreeMemory){
			IRTreeMemory irTreeMemory = (IRTreeMemory)structureRefTree;
			Value value = irTreeMemory.getMemory();
			type = value.getType();

			if(type.isPointerType()){
				PointerType pointerType = (PointerType)type;
				Type containedType = pointerType.getContainedType();

				if(containedType.isPointerType()){
					type = containedType;
				}
			}

			Entry<IRTreeIndex, Type> entry1 = getMapEntry(irTreeIndex1, type);
			Entry<IRTreeIndex, Type> entry2 = getMapEntry(irTreeIndex2, memberType);

			indexVsType.add(entry1);
			indexVsType.add(entry2);

			irTreeAggregateData = new IRTreeAggregateData(structureRefTree, indexVsType );
		}
		else if(structureRefTree instanceof IRTreeAggregateData){
			irTreeAggregateData = (IRTreeAggregateData)structureRefTree;
			indexVsType = irTreeAggregateData.getIndexVsType();
			int index = indexVsType.size() -1;
			type = indexVsType.get(index).getValue();

			if(type.isPointerType()){
				irTreeStack.push(irTreeAggregateData);
				Entry<IRTreeIndex, Type> entry1 = getMapEntry(irTreeIndex1, type);
				Entry<IRTreeIndex, Type> entry2 = getMapEntry(irTreeIndex2, memberType);
				List<Entry<IRTreeIndex,Type>> indexVsType1 = new ArrayList<Entry<IRTreeIndex,Type>>();
				indexVsType1.add(entry1);
				indexVsType1.add(entry2);

				IRTreeAggregateData irTreeAggregateData1 = new IRTreeAggregateData(structureRefTree, indexVsType1 );
				irTreeStack.push(irTreeAggregateData1);
				return translateSeqStatement(irTreeStack);
			}

			Entry<IRTreeIndex, Type> entry2 = getMapEntry(irTreeIndex2, memberType);
			indexVsType.add(entry2);
		}
		else if(structureRefTree instanceof IRTreeStatementList){

			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)structureRefTree;
			IRTreeStatementList leafStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafStatementList.getStatement();
			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				if(irTreeExp.getExpType() == TreeNodeExpType.MEMORY){
					irTreeAggregateData = (IRTreeAggregateData)translateStructureReference(irTreeExp, indexTree, memberType, compilationContext);
				}
				else if(irTreeExp.getExpType() == TreeNodeExpType.AGR_EXP){
					IRTreeAggregateData aggregateData = (IRTreeAggregateData)irTreeExp;
					List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
					int index = entries.size() - 1;
					Type type2 = entries.get(index).getValue();
					if(type2.isStructType()){
						StructType structType = (StructType)type2;
						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, structType, 0);
						} catch (TypeCreationException e) {
							e.printStackTrace();
							System.exit(-1);
						}
						Value value = new Value(pointerType);
						IRTreeMemory irTreeMemory = new IRTreeMemory(value, aggregateData);
						IRTree irTree = translateStructureReference(irTreeMemory, indexTree, memberType, compilationContext);

						irTreeStack.push(irTreeStatementList);
						irTreeStack.push(irTree);

						return translateSeqStatement(irTreeStack);
					}
					else if(type2.isPointerType()){
						Value value = new Value(type2);
						IRTreeMemory irTreeMemory = new IRTreeMemory(value, aggregateData);

						IRTree irTree = translateStructureReference(irTreeMemory, indexTree, memberType, compilationContext);

						irTreeStack.push(irTreeStatementList);
						irTreeStack.push(irTree);

						return translateSeqStatement(irTreeStack);
					}
				}
			}
		}
		else if(structureRefTree instanceof IRTreeCast){
			IRTreeCast irTreeCast = (IRTreeCast)structureRefTree;
			Entry<IRTreeIndex, Type> entry1 = getMapEntry(irTreeIndex1, type);
			Entry<IRTreeIndex, Type> entry2 = getMapEntry(irTreeIndex2, memberType);

			indexVsType.add(entry1);
			indexVsType.add(entry2);

			irTreeAggregateData = new IRTreeAggregateData(irTreeCast, indexVsType );
		}
		return irTreeAggregateData;
	}


	public IRTree createFunctionSignature(String funcName, IRTree paramsAsmt, Type returnType, boolean endsWithEllipses, boolean isStatic) {

		List<Value> paramValues = new ArrayList<Value>();
		List<List<Integer>> paramAttributes = new ArrayList<List<Integer>>();

		if(paramsAsmt != null){
			IRTreeStatementList paramStatementList = (IRTreeStatementList)paramsAsmt;
			IRTreeStatement paramStatement = paramStatementList.getStatement();
			while(paramStatementList != null){
				if(paramStatement.getStatementType() == TreeStatementType.ARG_PASS){
					IRTreeArgPassStm argPassStm = (IRTreeArgPassStm)paramStatement;
					IRTreeTempOrVar irTreeTempOrVar = argPassStm.getOriginalValuePassed();
					Value value = irTreeTempOrVar.getValue();
					paramValues.add(value);
					List<Integer> paramAttrs = new ArrayList<Integer>();
					paramAttributes.add(paramAttrs);
				}
				paramStatementList = paramStatementList.getStatementList();

				if(paramStatementList != null)
					paramStatement = paramStatementList.getStatement();
			}
		}

		List<Integer> functionAttrs = new ArrayList<Integer>();
		functionAttrs.add(LLVMUtility.getParamAttributeAsIntegerValue(properties, "nounwind"));
		int wordSize = LLVMUtility.getWordSize(properties);

		if(wordSize == 64)
			// This function attribute is required only for ELF x86-64
			functionAttrs.add(LLVMUtility.getParamAttributeAsIntegerValue(properties, "uwtable"));

		List<Integer> returnAttributes = new ArrayList<Integer>();
		IRTreeFunction irTreeFunction = new IRTreeFunction(funcName, returnType, paramValues, paramAttributes, functionAttrs, returnAttributes, false, endsWithEllipses, isStatic);

		return irTreeFunction;
	}


	public IRTree createFuncDeclaration(String funcName, Type returnType,
			List<Value> paramTypes, List<List<Integer>> paramAttrs, boolean endsWithEllipses, boolean isStatic) {
		List<Integer> funcAttributeList = new ArrayList<Integer>();
		List<Integer> returnAttributeList = new ArrayList<Integer>();
		if(funcName.equals("malloc")){
			returnAttributeList.add(LLVMUtility.getParamAttributeAsIntegerValue(properties, "noalias"));
			funcAttributeList.add(LLVMUtility.getParamAttributeAsIntegerValue(properties, "nounwind"));
		}
		if(funcName.equals("free") || funcName.equals("rand"))
			funcAttributeList.add(LLVMUtility.getParamAttributeAsIntegerValue(properties, "nounwind"));

		IRTreeFunction functionDeclaration = new IRTreeFunction(funcName, returnType, paramTypes, paramAttrs, funcAttributeList, returnAttributeList, true, endsWithEllipses, isStatic);
		return functionDeclaration;
	}

	public boolean isFunctionAlreadyAddedToDeclrStack(IRTree functionDefination,
			List<IRTree> functionDeclr) {

		IRTreeFunction irTreeFunction = (IRTreeFunction)functionDefination;

		for(IRTree irTree : functionDeclr){
			IRTreeFunction irTreeFunction1 = (IRTreeFunction)irTree;
			String funcName1 = irTreeFunction1.getFuncName();
			String funcName2 = irTreeFunction.getFuncName();

			List<Value> paramValues2 = irTreeFunction1.getParameters();
			List<Value> paramValues = irTreeFunction.getParameters();

			Type returnType1 = irTreeFunction1.getReturnType();
			Type returnType2 = irTreeFunction.getReturnType();

			boolean isParamTypesSame = true;

			if((funcName1.equals(funcName2)) && (returnType1 == returnType2) && (paramValues2.size() == paramValues.size())){
				for(int i = 0; i < paramValues.size(); i++){
					Value value = paramValues.get(i);
					Type type = value.getType();
					if(type != paramValues.get(i).getType()){
						isParamTypesSame = false;
						break;
					}
				}
			}
			else{
				isParamTypesSame = false;
			}

			return isParamTypesSame;
		}
		return false;
	}

	/**
	 * If the last statement of the function body is not a return statement, then it manually puts that.
	 * Provided that the function does not return multiple times.
	 * If the function is returning multiple times, then it creates a separate Basic Block in which 
	 * it loads the return value and returns it back.
	 * @param returnValueTree
	 * @param bodyAsmt
	 * @return IRTree
	 */
	public IRTree createReturnStmt(IRTree returnValueTree, IRTree bodyAsmt, CompilationContext compilationContext) {

		IRTreeUnConditionalBranch unConditionalBranch = null;
		IRTreeStatementList irTreeStatementList = null;
		IRTreeStatementList leafStatementList = null;
		IRTreeStatement leafStatement = null;
		IRTreeLabel globalReturnLabelTree = null;
		Stack<IRTree> irTreeStack = new Stack<IRTree>();

		if(bodyAsmt != null){
			irTreeStatementList = (IRTreeStatementList)bodyAsmt;
			leafStatementList = getLeafStatementList(irTreeStatementList);
			leafStatement = leafStatementList.getStatement();


			if(!(leafStatement instanceof IRTreeReturn))
				islastStatementAReturnStmt = false;

			if(globalReturnLabel == null){
				// If the last statement of the function body is not a return statement, then manually put that.
				if(!(leafStatement instanceof IRTreeReturn)){
					IRTreeReturn irTreeReturn = createIRTreeReturnStatement(returnValueTree, compilationContext);
					IRTreeStatementList returnStmtList = new IRTreeStatementList(irTreeReturn, null);
					leafStatementList.setStatementList(returnStmtList);
				}
				return irTreeStatementList;
			}
			// If there are multiple return statements but the last statement is not a return statement the we
			// have to create a branch instruction to that last block where we create the return instruction
			else if(!(leafStatement instanceof IRTreeReturn) && !(leafStatement instanceof IRTreeLabel))
				unConditionalBranch = new IRTreeUnConditionalBranch(globalReturnLabel);

			// Instead of removing the label we should update it.
			if(leafStatement instanceof IRTreeLabel){
				IRTreeLabel irTreeLabel = (IRTreeLabel)leafStatement;
				Label label = irTreeLabel.getLabel();
				label.setName(globalReturnLabel.getName());
			}
			else
				globalReturnLabelTree = new IRTreeLabel(globalReturnLabel);

			irTreeStack.push(irTreeStatementList);
		}

		IRTreeReturn irTreeReturn = createIRTreeReturnStatement(returnValueTree, compilationContext);

		if(unConditionalBranch != null)
			irTreeStack.push(unConditionalBranch);

		if(globalReturnLabelTree != null)
			irTreeStack.push(globalReturnLabelTree);

		irTreeStack.push(irTreeReturn);
		IRTree irTree = translateSeqStatement(irTreeStack);
		return irTree;
	}

	private IRTreeReturn createIRTreeReturnStatement(IRTree returnValueTree, CompilationContext compilationContext) {
		IRTreeReturnAlloca irTreeReturnValue = (IRTreeReturnAlloca)returnValueTree;
		IRTreeMemory memory = irTreeReturnValue.getMemory();
		Value value = memory.getMemory();
		IRTreeReturn irTreeReturn = null;

		if(value != null){
			PointerType ptrType = (PointerType)value.getType();
			Type containedType  = ptrType.getContainedType();
			irTreeReturn = new IRTreeReturn(memory, containedType, containedType);
		}
		else{
			irTreeReturn = new IRTreeReturn(memory, Type.getVoidType(compilationContext), Type.getVoidType(compilationContext));
		}
		return irTreeReturn;
	}

	public void setCaseValueVsLabelMap(IRTree constExprTree, IRTreeLabel irTreeLabel) {
		caseValuesVsLabels.put(constExprTree, irTreeLabel);
	}


	public IRTree translateStringConstant(String str, CompilationContext compilationContext, Translator translatingMediator) {
		ConstantArray constantArray = null;
		constantArray = (ConstantArray)ConstantArray.get(str, true, compilationContext);
		IRTreeConst irTreeConst = new IRTreeConst(constantArray);
		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, constantArray.getType(), 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Value value = new Value(pointerType);
		if(count_for_string_constants == 0 || constantStrings.contains(str))
			value.setName(".str");
		else
			value.setName(".str" + count_for_string_constants);

		synchronized (translatingMediator) {
			if(!constantStrings.contains(str))
				count_for_string_constants++;
		}

		if(!constantStrings.add(str)){
			return constStrVsDeclr.get(str);
		}

		IRTreeMemory irTreeMemory = new IRTreeMemory(value);
		LinkageTypes linkageType = LinkageTypes.PrivateLinkage;
		IRTreeDeclaration irTreeDeclaration = new IRTreeDeclaration(irTreeMemory, irTreeConst, true, true, true, linkageType);
		constStrVsDeclr.put(str, irTreeDeclaration);
		return irTreeDeclaration;
	}


	public IRTree translateConstantArray(List<IRTreeConst> listOfIrTreeConst, ArrayTypeEntry arrayInit, CompilationContext compilationContext, String currentFuncName, String arrName, boolean isExternalDecl) {
		ConstantArray constantArray = null;
		List<Constant> listOfConstantValues = new ArrayList<Constant>();
		for(IRTreeConst irTreeConst : listOfIrTreeConst){
			Constant constant = (Constant)irTreeConst.getExpressionValue();
			listOfConstantValues.add(constant);
		}
		TypeEntryWithAttributes typeEntryWithAttributes = arrayInit.getBaseTypeEntry();
		boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
		BasicType basicType = typeEntryWithAttributes.getBasicType();
		Type containedType = getLLVMType(basicType, isUnSigned, compilationContext);
		ArrayType arrayType = null;
		try {
			arrayType = Type.getArrayType(compilationContext, containedType, listOfConstantValues.size());
			constantArray = (ConstantArray)ConstantArray.get(arrayType, listOfConstantValues);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst = new IRTreeConst(constantArray);

		return irTreeConst;
	}


	public List<IRTreeType> getListOfNestedStructTypeIfPossible(StructType structType, Set<StructType> listOfStructType) {
		List<IRTreeType> irTreeTypes = new ArrayList<IRTreeType>();

		Integer memberSize = structType.getElementSize();
		for(int i = 0; i < memberSize; i++){
			Type type = structType.getTypeAtIndex(i);
			if(type.isStructType()){
				StructType structType2 = (StructType)type;

				// This Structure has already been converted or will be converted to IRTreeType, no need to do it here
				if(!listOfStructType.add(structType2))
					continue;

				String name = structType2.getName();
				Value value = new Value(type);
				value.setName(name);
				IRTreeType irTreeType = new IRTreeType(value);
				irTreeTypes.add(irTreeType);
				List<IRTreeType> moreStructTypes = getListOfNestedStructTypeIfPossible(structType2, listOfStructType);
				irTreeTypes.addAll(moreStructTypes);
			}
		}

		return irTreeTypes;
	}

	public IRTreeStatementList createConstantStruct(List<IRTree> listOfMemberIRTree, StructOrUnionTypeEntry structOrUnionTypeEntry, CompilationContext compilationContext){
		IRTreeStatementList irTreeStatementList = null;
		// Create a ConstantStruct
		List<org.tamedragon.common.llvmir.types.Constant> listOfConstants = new ArrayList<org.tamedragon.common.llvmir.types.Constant>();

		for(IRTree irTree : listOfMemberIRTree){
			while(true && irTree != null){
				if(irTree instanceof IRTreeDeclaration){
					IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
					irTree = irTreeDeclaration.getInitializerExpression();
					if(irTree instanceof IRTreeConst){
						IRTreeConst irTreeConst = (IRTreeConst)irTree;
						Value value = irTreeConst.getExpressionValue();
						if(value instanceof ConstantArray){

							if(irTreeStatementList == null)
								irTreeStatementList = new IRTreeStatementList(irTreeDeclaration, null);
							else
								getLeafStatementList(irTreeStatementList).setStatementList(new IRTreeStatementList(irTreeDeclaration, null));

							ConstantArray constantArray = (ConstantArray)value;
							ConstantExpr constantExpr = createConstantExpressionForString(irTreeDeclaration, constantArray, compilationContext);
							if(constantExpr != null)
								listOfConstants.add(constantExpr);
						}
					}
					break;
				}
				else if(irTree instanceof IRTreeConst){
					IRTreeConst irTreeConst = (IRTreeConst)irTree;
					Value value = irTreeConst.getExpressionValue();
					if(value instanceof org.tamedragon.common.llvmir.types.Constant){
						listOfConstants.add((org.tamedragon.common.llvmir.types.Constant)value);
					}
					break;
				}
				else if(irTree instanceof IRTreeStatementList){
					IRTreeStatementList irTreeStatementList2 = (IRTreeStatementList)irTree;
					irTree = getLeafStatementList(irTreeStatementList2).getStatement();
					if(irTree instanceof IRTreeExpressionStm){
						IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)irTree;
						irTree = expressionStm.getExpressionTree();
					}
					else if(irTree instanceof IRTreeDeclaration){
						IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irTree;
						irTree = irTreeDeclaration.getInitializerExpression();
					}
				}
				else if(irTree instanceof IRTreeMemory){
					break;
				}
			}
		}

		// Get the IRTree Type for each struct members
		Type[] contained = new Type[structOrUnionTypeEntry.getNumMembers()];
		LinkedHashMap<String, TypeEntryWithAttributes> membersWithTypeEntryWithAttrs = structOrUnionTypeEntry.getMembers();
		Set<java.util.Map.Entry<String, TypeEntryWithAttributes>> entries = membersWithTypeEntryWithAttrs.entrySet();
		Iterator<java.util.Map.Entry<String, TypeEntryWithAttributes>> iterator = entries.iterator();
		int counter = 0;
		while(iterator.hasNext()){
			java.util.Map.Entry<String, TypeEntryWithAttributes> entry = iterator.next();
			TypeEntryWithAttributes typeEntryWithAttributes = entry.getValue();
			BasicType basicType = typeEntryWithAttributes.getBasicType();
			boolean isUnSigned = typeEntryWithAttributes.isUnsigned();
			contained[counter] = getLLVMType(basicType, isUnSigned, compilationContext);
			counter++;
		}

		StructType structType = (StructType) getLLVMType(structOrUnionTypeEntry, true, compilationContext);
		IRTreeType irTreeType = new IRTreeType(new Value(structType));

		if(irTreeStatementList != null)
			getLeafStatementList(irTreeStatementList).setStatementList(new IRTreeStatementList(irTreeType, null));
		else
			irTreeStatementList = new IRTreeStatementList(irTreeType, null);

		if(structType.isUnion() && listOfConstants.size() > 0){
			Type initialValueType = contained[0];
			Type unionType = structType.getContainedTypes().get(0);
			int initialValueTypeSize = LLVMUtility.getSizeForType(properties, initialValueType);
			int unionTypeSize = LLVMUtility.getSizeForType(properties, unionType);

			Constant initializer = listOfConstants.get(0);
			Type initializerType = initializer.getType();

			if(initializerType.isPrimitiveType()){
				if(initializerType.isFloatingPointType() && initialValueType.isIntegerType()){
					APFloat apFloat = ((ConstantFP)initializer).getAPFloat();
					ConstantInt constantInt = null;
					try {
						constantInt = ConstantInt.create((IntegerType) initialValueType, apFloat.getIntegerPart(), true);
						listOfConstants.remove(0);
						listOfConstants.add(0, constantInt);
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}
				else if(initializerType.isIntegerType() && initialValueType.isFloatingPointType()){
					ConstantInt constantInt = ((ConstantInt)initializer);
					ConstantFP constantFP = null;
					APFloat apFloat = new APFloat(APFloat.IEEEdouble, constantInt.getApInt().getVal());
					try {
						constantFP = new ConstantFP(Type.getDoubleType(compilationContext), apFloat );
						listOfConstants.remove(0);
						listOfConstants.add(0, constantFP);
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
			else if(initialValueType != initializerType){
				InstructionID instrID = null;

				if(initialValueType.isIntegerType() && initializerType.isPointerType())
					instrID = InstructionID.PTR_TO_INT_INST;

				CastInst castInst = null;
				try {
					castInst = CastInst.create(instrID , null, initialValueType, initializer, null);
				} catch (InstructionCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<Value> operands = new ArrayList<Value>();
				operands.add(castInst);
				ConstantExpr constantExpr = new ConstantExpr(castInst.getType(), operands , instrID);
				listOfConstants.remove(0);
				listOfConstants.add(0, constantExpr);
			}

			if(initialValueTypeSize < unionTypeSize){
				List<Type> listOfTypes = new ArrayList<Type>();
				// create a new NamedType
				ArrayType arrayType = createAnArrayTypeOfTypeI8(initialValueTypeSize, unionTypeSize, 1, compilationContext);
				listOfTypes.add(initialValueType);
				listOfTypes.add(arrayType);
				try {
					structType = Type.getStructType(compilationContext, false, null, true, listOfTypes.toArray(new Type[listOfTypes.size()]));

					if(intermediateNamedTypes.add(structType)){ // If its a new intermediate NamedType
						structType.setName("%" + nosOfIntermediateNamedType.toString());
						nosOfIntermediateNamedType++;
					}

				} catch (TypeCreationException e) {
					e.printStackTrace();
				}
			}
		}

		ConstantStruct constantStruct = (ConstantStruct) ConstantStruct.get(structType, listOfConstants);
		IRTreeConst irTreeConst = new IRTreeConst(constantStruct);
		IRTreeExpressionStm expressionStm = new IRTreeExpressionStm(irTreeConst);

		getLeafStatementList(irTreeStatementList).setStatementList(new IRTreeStatementList(expressionStm, null));

		return irTreeStatementList;
	}


	private ArrayType createAnArrayTypeOfTypeI8(int initialValueTypeSize,
			int unionTypeSize, int alignmentOfTypeWithMaxAlignment, CompilationContext compilationContext) {
		ArrayType arrayType = null;
		int i = 1;
		while(true){
			Integer size = initialValueTypeSize + (alignmentOfTypeWithMaxAlignment * i);
			if(size >= unionTypeSize)
				break;
			else
				i++;
		}
		Integer numElements = alignmentOfTypeWithMaxAlignment * i;

		try {
			arrayType = Type.getArrayType(compilationContext, Type.getInt8Type(compilationContext, false), numElements);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return arrayType;
	}


	public IRTree addPreviousIRTree(IRTree previousIRTree, IRTree newIRTree) {
		if(previousIRTree == null)
			return newIRTree;
		else if(previousIRTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)previousIRTree;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			leafIrTreeStatementList.setStatementList((IRTreeStatementList)newIRTree);
			return irTreeStatementList;
		}
		return null;
	}


	public void addReturnValueTreeToFunctionAssemStack(
			Stack<IRTree> functionAssemTreeStack, IRTree returnValueTree, String currentFunctionName) {
		// If there is a multiple Return statement
		if(globalReturnLabel != null || !islastStatementAReturnStmt || currentFunctionName.equals("main")){
			functionAssemTreeStack.push(returnValueTree);
		}
	}

	public IRTreeConditionalBranch createConditionalBrInstr(Label iterStartLabel, Label iterEndLabel, CompilationContext compilationContext, IRTree whileTestAssemType, Stack<IRTree> irTreeStack){
		IRTreeConditionalBranch conditionalBranch = null;

		IRTreeExp whileTestExp = null;

		if(whileTestAssemType instanceof IRTreeExp)
			whileTestExp = (IRTreeExp) whileTestAssemType;
		else if(whileTestAssemType instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)whileTestAssemType;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafIrTreeStatementList.getStatement();

			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				whileTestExp = expressionStm.getExpressionTree();
			}
			else if(leafStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
				whileTestExp = irTreeMove.getSrc();
			}

			if(whileTestExp.getExpType() == TreeNodeExpType.MEMORY)
				whileTestAssemType = removeStmtListFromIRTree(irTreeStatementList, leafIrTreeStatementList);
			irTreeStack.push(whileTestAssemType);
		}

		if(whileTestExp.getExpType() == TreeNodeExpType.CONDITIONAL_EXPR || whileTestExp.getExpType() == TreeNodeExpType.PHI_NODE){
			conditionalBranch = new IRTreeConditionalBranch(iterStartLabel, iterEndLabel, whileTestExp);
		}
		else if(whileTestExp.getExpType() == TreeNodeExpType.MEMORY){
			IRTreeMemory irTreeMemory = (IRTreeMemory)whileTestExp;
			Value value = irTreeMemory.getMemory();
			Type type = value.getType();
			conditionalBranch = createConditionalBrForType(type, iterStartLabel, iterEndLabel, irTreeMemory);
		}
		else if(whileTestExp.getExpType() == TreeNodeExpType.AGR_EXP){
			IRTreeAggregateData aggregateData = (IRTreeAggregateData)whileTestExp;
			List<Entry<IRTreeIndex, Type>> entries = aggregateData.getIndexVsType();
			Integer lastIndex = entries.size() - 1;
			Type type = entries.get(lastIndex).getValue();
			conditionalBranch = createConditionalBrForType(type, iterStartLabel, iterEndLabel, aggregateData);
		}
		else if(whileTestExp.getExpType() == TreeNodeExpType.CONST_EXP){
			IRTreeConst irTreeConst = (IRTreeConst)whileTestExp;
			Value value = irTreeConst.getExpressionValue();
			if(value instanceof ConstantInt){
				ConstantInt constantInt = (ConstantInt)value;
				APInt apInt = constantInt.getApInt();
				String val = apInt.getVal();
				Integer integer = Integer.parseInt(val);
				if(integer != 0){
					// True case
					try {
						whileTestExp = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "true", false)));
					} catch (InstantiationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
				else{
					// False case
					try {
						whileTestExp = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "false", false)));
					} catch (InstantiationException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
			conditionalBranch = new IRTreeConditionalBranch(iterStartLabel, iterEndLabel, whileTestExp);
		}
		else if(whileTestExp.getExpType() == TreeNodeExpType.BINARY_EXP){
			IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)whileTestExp;
			BinaryOperatorID binaryOperatorID = binaryExp.getBinaryOperatorID();
			if(binaryOperatorID == BinaryOperatorID.XOR)
				conditionalBranch  = new IRTreeConditionalBranch(iterStartLabel, iterEndLabel, whileTestExp);
			else
				conditionalBranch = createConditionalBrForType(Type.getInt32Type(compilationContext, true), iterStartLabel, iterEndLabel, whileTestExp);
		}
		else{
			// TODO
		}
		IRTreeStatementList conditionalBranchStmtList = new IRTreeStatementList(conditionalBranch, null);
		irTreeStack.push(conditionalBranchStmtList);
		return conditionalBranch;
	}

	public IRTree translateTernaryOp(IRTree lhs, IRTree rhs, IRTree condIRTree,
			CompilationContext compilationContext, Type type) {
		Stack<IRTree> seqAssemTypes = new Stack<IRTree>();

		IRTreeExp condExp = getIRTreeExprFromIRTree(condIRTree, seqAssemTypes);

		if(condExp.getExpType() == TreeNodeExpType.CONST_EXP){
			IRTreeConst irTreeConst = (IRTreeConst)condExp;
			APFloat apFloat = irTreeConst.getApFloat();
			APInt apInt = irTreeConst.getApInt();
			String val = null;

			if(apInt != null){
				val = apInt.getVal();
			}
			else{
				val = apFloat.getStrRepresentation();
			}

			if(val.equals("0") || val.equals("0.0"))
				return rhs;
			else
				return lhs;
		}
		if(condExp.getExpType() != TreeNodeExpType.CONDITIONAL_EXPR){
			//create constant zero
			APInt zero = new APInt(32, "0", true);
			ConstantInt zeroConst = null;
			try {
				zeroConst = new ConstantInt(Type.getInt32Type(compilationContext, true), zero);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			IRTreeConst zeroIRTree = new IRTreeConst(zeroConst);
			condExp = new IRTreeConditionalExpr(Predicate.ICMP_NE, condExp, zeroIRTree);
		}

		IRTreeExpressionStm conditionalExprStmt = new IRTreeExpressionStm(condExp);
		seqAssemTypes.push(conditionalExprStmt);
		
		Label trueLabel = new Label();
		Label falseLabel = new Label();
		Label label = new Label();
		
		IRTreeConditionalBranch conditionalBranch = null;
		conditionalBranch = new IRTreeConditionalBranch(trueLabel, falseLabel, condExp);
		seqAssemTypes.push(conditionalBranch);
		IRTreeLabel irTreeTrueLab = new IRTreeLabel(trueLabel);
		seqAssemTypes.push(irTreeTrueLab);
		IRTreeExp val1 = getIRTreeExprFromIRTree(lhs, seqAssemTypes);
		
		if(val1.getExpType() == TreeNodeExpType.MEMORY)
			seqAssemTypes.push(val1);
		
		IRTreeUnConditionalBranch unCondBrToPhiBlk = new IRTreeUnConditionalBranch(label);
		seqAssemTypes.push(unCondBrToPhiBlk);
		IRTreeLabel irTreeFalseLab = new IRTreeLabel(falseLabel);
		seqAssemTypes.push(irTreeFalseLab);
		IRTreeExp val2 = getIRTreeExprFromIRTree(rhs, seqAssemTypes);
		
		if(val2.getExpType() == TreeNodeExpType.MEMORY)
			seqAssemTypes.push(val2);
		
		unCondBrToPhiBlk = new IRTreeUnConditionalBranch(label);
		seqAssemTypes.push(unCondBrToPhiBlk);
		IRTreeLabel irTreeLabel = new IRTreeLabel(label);
		seqAssemTypes.push(irTreeLabel);
		IRTreePhiExpr phiExpr = null;
		Map<Label, IRTreeExp> labelVsValueMap = new LinkedHashMap<Label, IRTreeExp>();
		labelVsValueMap .put(trueLabel, val1);
		labelVsValueMap.put(falseLabel, val2);
		phiExpr = new IRTreePhiExpr(type, labelVsValueMap);
		IRTreeExpressionStm phiExprStmt = new IRTreeExpressionStm(phiExpr);
		seqAssemTypes.push(phiExprStmt);
		IRTree irTree = translateSeqStatement(seqAssemTypes);
		return irTree;
	}


	private IRTreeExp getIRTreeExprFromIRTree(IRTree irTree, Stack<IRTree> seqAssemTypes) {
		IRTreeExp exprTree = null;

		if(irTree instanceof IRTreeExp){
			//if(irTree instanceof IRTreeMemory && isUsedByPhiNode)
			if(!(irTree instanceof IRTreeMemory))
				seqAssemTypes.push(irTree);
			exprTree = (IRTreeExp) irTree;
		}
		else if(irTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafIrTreeStatementList.getStatement();
			IRTreeStatement irTreeStatement = irTreeStatementList.getStatement();
			if(leafStatement != irTreeStatement || !(leafStatement instanceof IRTreeExpressionStm))
				seqAssemTypes.push(irTree);

			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				exprTree = expressionStm.getExpressionTree();
			}
			else if(leafStatement instanceof IRTreeMove){
				IRTreeMove irTreeMove = (IRTreeMove)leafStatement;
				exprTree = irTreeMove.getSrc();
			}
		}
		return exprTree;
	}


	public IRTreeExp getExprFromIRTree(IRTree irInitExpr, Stack<IRTree> seqStmt, CompilationContext compilationContext, Value newValue, boolean isGlobal, boolean isConstant, Map<IRTreeExp, Boolean> isCompileTimeConstant) {
		IRTreeMemory memoryTree = new IRTreeMemory(newValue);
		IRTreeExp expTreeFromIRTree = null;

		while(true && irInitExpr != null){
			if(irInitExpr instanceof IRTreeExp){
				expTreeFromIRTree = (IRTreeExp) irInitExpr;
				break;
			}
			else if(irInitExpr instanceof IRTreeStatementList){
				// Get the expr tree from 'assemTypeTest'
				IRTreeStatementList irTreeLeafStmtList = getLeafStatementList((IRTreeStatementList)irInitExpr);
				IRTree leafStmt = irTreeLeafStmtList.getStatement();
				if(leafStmt instanceof IRTreeExpressionStm){
					IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStmt;
					expTreeFromIRTree = expressionStm.getExpressionTree();

					// For statements like char* bin = {"000","001"} etc, it creates a constant array for getElementPtr instruction so a list of IRTreeExprs which is compile time constant
					if(expTreeFromIRTree instanceof IRTreeExpList)
						isCompileTimeConstant.put(expTreeFromIRTree, true);
					// Remove the expr tree from 'assemTypeTest'
					irInitExpr = removeStmtListFromIRTree(irTreeLeafStmtList, (IRTreeStatementList)irInitExpr);
				}
				else if(leafStmt instanceof IRTreeMove){ //Probably postfix or Prefix
					IRTreeMove irTreeMove = (IRTreeMove) leafStmt;
					expTreeFromIRTree = irTreeMove.getSrc();
					if(expTreeFromIRTree instanceof IRTreeBinaryExp){
						IRTreeBinaryExp binaryExp = (IRTreeBinaryExp)expTreeFromIRTree;
						Value value = null;
						if(!postfixExprStack.empty()){
							postfixExprStack.pop();
							IRTreeExp leftExp = binaryExp.getLeft();
							if(leftExp.getExpType() == TreeNodeExpType.TEMP_OR_VAR_EXP){
								IRTreeTempOrVar irTreeTempOrVar = (IRTreeTempOrVar)leftExp;
								value = irTreeTempOrVar.getValue();
							}
							else if(leftExp.getExpType() == TreeNodeExpType.MEMORY){
								IRTreeMemory irTreeMemory = (IRTreeMemory)leftExp;
								value = irTreeMemory.getMemory();
							}
							expTreeFromIRTree = new IRTreeTempOrVar(value);
						}
						else{
							expTreeFromIRTree = binaryExp;
						}
					}
					if(expTreeFromIRTree instanceof IRTreeAggregateData){
						IRTreeAggregateData aggregateData = (IRTreeAggregateData)expTreeFromIRTree;
						expTreeFromIRTree = aggregateData;
					}
				}
				else if(leafStmt instanceof IRTreeDeclaration){
					seqStmt.push(irInitExpr);
					irInitExpr =  leafStmt;
					continue;
				}

				if(irInitExpr != null)
					seqStmt.push(irInitExpr);
				break;
			}
			else if(irInitExpr instanceof IRTreeDeclaration){
				IRTreeDeclaration irTreeDeclaration = (IRTreeDeclaration)irInitExpr;
				IRTreeMemory irTreeMemory = irTreeDeclaration.getMemoryTree();
				IRTreeExp irTreeExp = irTreeDeclaration.getInitializerExpression();
				if(irTreeExp instanceof IRTreeConst){
					IRTreeConst irTreeConst = (IRTreeConst)irTreeExp;
					Value value = irTreeConst.getExpressionValue();
					if(value instanceof ConstantArray){
						ConstantArray constantArray = (ConstantArray)value;
						boolean hasZeroInitializer = constantArray.hasZeroInitializer();
						List<Constant> constants = constantArray.getVal();
						boolean isLHSArrayType = false;
						if(newValue.getType().isPointerType()){
							PointerType pointerType = (PointerType)newValue.getType();
							Type containedType = pointerType.getContainedType();
							if(containedType.isArrayType()){
								isLHSArrayType = true;
							}
						}

						ArrayType arrType = (ArrayType)value.getType();
						Type containedType = arrType.getContainedType();
						PointerType pointerType = null;
						try {
							pointerType = Type.getPointerType(compilationContext, arrType, 0);
						} catch (TypeCreationException e1) {
							e1.printStackTrace();
							System.exit(-1);
						}
						List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
						createTwoZeroIndexes(compilationContext, pointerType, arrType, indexVsType);
						IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeMemory, indexVsType);

						if(!isLHSArrayType){
							expTreeFromIRTree = aggregateData;
							isCompileTimeConstant.put(expTreeFromIRTree, true);
						}
						else if(!hasZeroInitializer){
							// Call llvm.memcpy intrinsic function
							String funcName = "llvm.memcpy.p0i8.p0i8.i64";
							IRTreeCallExp callExp = createIntrinsicCallExpr(funcName, compilationContext, memoryTree, isGlobal, isConstant, containedType, aggregateData, irTreeDeclaration, constants.size(), seqStmt, hasZeroInitializer, arrType);
							return callExp;
						}
						else{
							// Call llvm.memset intrinsic function
							String funcName = "llvm.memset.p0i8.i64";
							IRTreeCallExp callExp = createIntrinsicCallExpr(funcName, compilationContext, memoryTree, isGlobal, isConstant, containedType, aggregateData, irTreeDeclaration, constants.size(), seqStmt, hasZeroInitializer, arrType);
							return callExp;
						}
						if(!listOfConstArrDeclrs.contains(irTreeDeclaration))
							seqStmt.push(irTreeDeclaration);
						listOfConstArrDeclrs.add(irTreeDeclaration);
					}
					else if(value instanceof ConstantStruct){
						ConstantStruct constantStruct = (ConstantStruct)value;
						String funcName = "llvm.memcpy.p0i8.p0i8.i64";
						IRTreeCallExp callExp = createIntrinsicCallExpr(funcName, compilationContext, memoryTree, isGlobal, isConstant, constantStruct.getType(), null, irTreeDeclaration, null, seqStmt, false, constantStruct.getType());
						return callExp;
					}
				}
				else
					isCompileTimeConstant.put(expTreeFromIRTree, false);
				break;
			}
		}


		if(isCompileTimeConstant != null && isCompileTimeConstant.isEmpty()){
			if(isGlobal && (expTreeFromIRTree instanceof IRTreeConst))
				isCompileTimeConstant.put(expTreeFromIRTree, true);
			else
				isCompileTimeConstant.put(expTreeFromIRTree, false);
		}
		return expTreeFromIRTree;
	}
	/**
	 * It creates intrinsic function call like "llvm.memcpy.p0i8.p0i8.i64" or "llvm.memset.p0i8.i64"
	 * @param funcName : Name of the function, as of now it is either "llvm.memcpy.p0i8.p0i8.i64" or "llvm.memset.p0i8.i64"
	 * @param compilationContext
	 * @param irTree   : It is the source.
	 * @param isGlobal
	 * @param isConstant
	 * @param containedType  : If the arrayType is null the number of bytes to be copied will calculated based on the size of containedType
	 * @param aggregateData  : It can be the source when the destination is an array whose containedType is i8 the we don't need to bitcast them, we just need the GetElementPtrInstr 
	 * @param irTreeDeclaration : It can be the source and some times used for the declaration of Global Value like Global Array
	 * @param nosOfBytes		 : number of bytes to be copied
	 * @param seqStmt		 : CallInstrs and any other statements would be added to the this list
	 * @param hasZeroInitializer : If the function name is "llvm.memset.p0i8.i64" then this has to be true
	 * @param arrayType		 : arrayType if not null would be used to calculate number of bytes to be copied
	 * @return
	 */
	private IRTreeCallExp createIntrinsicCallExpr(String funcName, CompilationContext compilationContext, IRTree irTree, boolean isGlobal, boolean isConstant, Type containedType, IRTree aggregateData, IRTreeDeclaration irTreeDeclaration, Integer nosOfBytes, Stack<IRTree> seqStmt, boolean hasZeroInitializer, Type arrayType) {

		Type returnType = Type.getVoidType(compilationContext);
		List<IRTree> paramList = new ArrayList<IRTree>();
		List<Value> formalParamValues = new ArrayList<Value>();
		List<List<Integer>> paramAttributes = new ArrayList<List<Integer>>();
		PointerType pointerType2 = null;
		try {
			pointerType2 = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, false), 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		formalParamValues.add(new Value(pointerType2));
		Integer noCaptureAttr = LLVMUtility.getParamAttributeAsIntegerValue(properties, "nocapture");
		List<Integer> paramAttrs = new ArrayList<Integer>();
		paramAttrs.add(noCaptureAttr);
		paramAttributes.add(paramAttrs);

		if(hasZeroInitializer){
			formalParamValues.add(new Value(Type.getInt8Type(compilationContext, true)));
			paramAttrs = new ArrayList<Integer>();
			paramAttributes.add(paramAttrs);
		}
		else{
			formalParamValues.add(new Value(pointerType2));
			paramAttrs = new ArrayList<Integer>();
			paramAttrs.add(noCaptureAttr);
			paramAttributes.add(paramAttrs);
		}

		formalParamValues.add(new Value(Type.getInt64Type(compilationContext, false)));
		paramAttrs = new ArrayList<Integer>();
		paramAttributes.add(paramAttrs);
		formalParamValues.add(new Value(Type.getInt32Type(compilationContext, false)));
		paramAttrs = new ArrayList<Integer>();
		paramAttributes.add(paramAttrs);
		formalParamValues.add(new Value(Type.getInt1Type(compilationContext, false)));
		paramAttrs = new ArrayList<Integer>();
		paramAttributes.add(paramAttrs);

		List<Type> formalParamTypes = getParamTypesFromParamValues(formalParamValues);

		LinkageTypes linkageType = LinkageTypes.PrivateLinkage;

		IRTreeDeclaration llvmMemCpyDeclr = null;

		if(irTree instanceof IRTreeMemory){
			llvmMemCpyDeclr = new IRTreeDeclaration((IRTreeMemory)irTree, null, isGlobal, isConstant, false, linkageType);
			paramList.add(llvmMemCpyDeclr);
		}
		else
			paramList.add(irTree);

		if(!hasZeroInitializer){
			if(containedType.isInt8Type())
				paramList.add(aggregateData);
			else 
				paramList.add(irTreeDeclaration);
		}

		int size = LLVMUtility.getSizeForType(properties, containedType);
		Integer nosOfBitsToBeCopied = 0;

		if(nosOfBytes != null)
			nosOfBitsToBeCopied = nosOfBytes * size;
		else
			nosOfBitsToBeCopied = size;

		APInt apInt = new APInt(64, nosOfBitsToBeCopied.toString(), false);
		ConstantInt constantInt = null;
		try {
			constantInt = new ConstantInt(Type.getInt64Type(compilationContext, false), apInt);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst2 = new IRTreeConst(constantInt);

		Integer alignment = null;

		if(hasZeroInitializer || arrayType == null)
			alignment = AllocaInst.getAlignmentForType(properties, containedType);
		else
			alignment = AllocaInst.getAlignmentForType(properties, arrayType);

		apInt = new APInt(32, alignment.toString(), false);
		try {
			constantInt = new ConstantInt(Type.getInt32Type(compilationContext, false), apInt);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		IRTreeConst irTreeConst3 = new IRTreeConst(constantInt);

		IRTreeConst irTreeConstFalse = null;
		try {
			irTreeConstFalse = new IRTreeConst(new ConstantInt(Type.getInt1Type(compilationContext, false), new APInt(1, "false", false)));
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if(hasZeroInitializer){
			apInt = new APInt(8, "0", true);
			constantInt = null;
			try {
				constantInt = new ConstantInt(Type.getInt8Type(compilationContext, true), apInt);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
			IRTreeConst irTreeConst1 = new IRTreeConst(constantInt);

			paramList.add(irTreeConst1);
		}

		paramList.add(irTreeConst2);
		paramList.add(irTreeConst3);
		paramList.add(irTreeConstFalse);


		IRTreeMemory funcMemory = createFunctionMemory(funcName, compilationContext, returnType, formalParamTypes);

		IRTreeCallExp irTreeCallExp = new IRTreeCallExp(returnType, paramList, funcMemory, formalParamTypes, false, true,0,0);

		Integer funcAttributeList = 32;
		List<Integer> listFuncAttrs = new ArrayList<Integer>();
		listFuncAttrs.add(funcAttributeList);
		List<Integer> listRetAttrs = new ArrayList<Integer>();

		// Before using we have to declare the function
		if(hasZeroInitializer){
			if(irTreeLLVMMemSetFunctionDeclr == null){
				irTreeLLVMMemSetFunctionDeclr = new IRTreeFunction(funcName, returnType, formalParamValues, paramAttributes, listFuncAttrs, listRetAttrs, true, false, false);
				seqStmt.push(irTreeLLVMMemSetFunctionDeclr);
			}
		}
		else{
			if(irTreeLLVMMemCpyFunctionDeclr == null){
				irTreeLLVMMemCpyFunctionDeclr = new IRTreeFunction(funcName, returnType, formalParamValues, paramAttributes, listFuncAttrs, listRetAttrs, true, false, false);
				seqStmt.push(irTreeLLVMMemCpyFunctionDeclr);
			}
		}

		if(irTreeDeclaration != null && irTreeDeclaration.getInitializerExpression() instanceof IRTreeConst){
			seqStmt.push(irTreeDeclaration);
			seqStmt.push(llvmMemCpyDeclr);
		}
		else if(hasZeroInitializer){// If it is a memset operation
			seqStmt.push(llvmMemCpyDeclr);
		}

		return irTreeCallExp;
	}


	private IRTreeMemory createFunctionMemory(String funcName,
			CompilationContext compilationContext, Type returnType,
			List<Type> formalParamTypes) {
		PointerType funcPtrType = null;
		FunctionType funcType = null;

		try{
			funcType = Type.getFunctionType(compilationContext, returnType, false, new Vector<Type>(formalParamTypes));
			funcPtrType = Type.getPointerType(compilationContext, funcType, 0);
		}
		catch(TypeCreationException e){
			e.printStackTrace();
			System.exit(-1);
		}

		Value funcValue = new Value(funcPtrType);
		funcValue.setName(funcName);
		IRTreeMemory funcMemory = new IRTreeMemory(funcValue);
		return funcMemory;
	}


	protected void resetTranslatorPropertiesForNewFunction(){
		valVsMemoryMap = new HashMap<IRTreeTempOrVar, IRTreeMemory>();
		caseValuesVsLabels = new LinkedHashMap<IRTree, IRTreeLabel>();
		postfixExprStack = new Stack<IRTreeExp>();
		translatingIndirectionStack = new Stack<IRTree>();
		globalReturnLabel = null;
		islastStatementAReturnStmt = true;
	}


	public IRTreeCallExp callIntrinsicLLVMStackRestoreOrStackSave(String funcName, Stack<IRTreeAlloca> variableLengthArrayAllocas, Stack<IRTree> treeElementStack, CompilationContext compilationContext) {

		List<Value> formalParamValues = new ArrayList<Value>();
		List<Type> formalParamTypes = new ArrayList<Type>();

		PointerType pointerType = null;
		try {
			pointerType = Type.getPointerType(compilationContext, Type.getInt8Type(compilationContext, true), 0);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if(funcName.equals("llvm.stackrestore")){
			formalParamTypes.add(pointerType);
			formalParamValues.add(new Value(pointerType));
		}

		List<List<Integer>> paramAttributes = new ArrayList<List<Integer>>();
		Type returnType = null;
		if(funcName.equals("llvm.stackrestore"))
			returnType = Type.getVoidType(compilationContext);
		else
			returnType = pointerType;

		List<IRTree> paramList = new ArrayList<IRTree>();

		if(funcName.equals("llvm.stackrestore"))
			paramList.add(variableLengthArrayAllocas.pop());

		IRTreeMemory funcMemory = createFunctionMemory(funcName, compilationContext, returnType, formalParamTypes);

		IRTreeCallExp irTreeCallExp = new IRTreeCallExp(returnType, paramList, funcMemory, formalParamTypes, false, false,0,0);
		treeElementStack.push(irTreeCallExp);
		Integer funcAttributeList = 32;
		List<Integer> listFuncAttrs = new ArrayList<Integer>();
		listFuncAttrs.add(funcAttributeList);
		List<Integer> listRetAttrs = new ArrayList<Integer>();

		if(funcName.equals("llvm.stackrestore")){
			if(irTreeLLVMStackRestoreFunctionDeclr == null){
				irTreeLLVMStackRestoreFunctionDeclr = new IRTreeFunction(funcName, returnType, formalParamValues, paramAttributes, listFuncAttrs, listRetAttrs, true, false, false);
				treeElementStack.push(irTreeLLVMStackRestoreFunctionDeclr);
			}
		}
		else{
			if(irTreeLLVMStackSaveFunctionDeclr == null){
				irTreeLLVMStackSaveFunctionDeclr = new IRTreeFunction(funcName, returnType, formalParamValues, paramAttributes, listFuncAttrs, listRetAttrs, true, false, false);
				treeElementStack.push(irTreeLLVMStackSaveFunctionDeclr);
			}
		}
		return irTreeCallExp;
	}

	protected void addIRTreeCastToIRTree(Translated retValue, Type irType) {

		if(irType == null)
			return;

		IRTree irTree = retValue.getIRTree();
		Value destValue = new Value(irType);
		IRTreeTempOrVar destIRTree = new IRTreeTempOrVar(destValue);
		IRTreeCast irTreeCast = null;

		if(irTree instanceof IRTreeStatementList){
			IRTreeStatementList irTreeStatementList = (IRTreeStatementList)irTree;
			IRTreeStatementList leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			IRTreeStatement leafStatement = leafIrTreeStatementList.getStatement();

			if(leafStatement instanceof IRTreeExpressionStm){
				IRTreeExpressionStm expressionStm = (IRTreeExpressionStm)leafStatement;
				IRTreeExp irTreeExp = expressionStm.getExpressionTree();
				irTreeCast = new IRTreeCast(irTreeExp, destIRTree);

				if(irTreeExp instanceof IRTreeMemory){
					irTreeStatementList = (IRTreeStatementList) removeStmtListFromIRTree(leafIrTreeStatementList, irTreeStatementList);
				}
			}

			IRTreeExpressionStm expressionStm = new IRTreeExpressionStm(irTreeCast);
			IRTreeStatementList explicitCastingIRTreeStmtList = new IRTreeStatementList(expressionStm, null);
			leafIrTreeStatementList = getLeafStatementList(irTreeStatementList);
			leafIrTreeStatementList.setStatementList(explicitCastingIRTreeStmtList);
			retValue.setIRTree(irTreeStatementList);
		}
		else if(irTree instanceof IRTreeExp){
			irTreeCast = new IRTreeCast(irTree, destIRTree);
			retValue.setIRTree(irTreeCast);
		}
	}


	public IRTreeAggregateData createIRTreeAggregateDataFromIRTreeDeclr(
			IRTreeDeclaration irTreeDeclaration, CompilationContext compilationContext) {
		List<Entry<IRTreeIndex, Type>> indexVsType = new ArrayList<Entry<IRTreeIndex,Type>>();
		createTwoZeroIndexes(compilationContext, irTreeDeclaration.getMemoryTree().getMemory().getType(), ((IRTreeConst)irTreeDeclaration.getInitializerExpression()).getExpressionValue().getType(), indexVsType);
		IRTreeAggregateData aggregateData = new IRTreeAggregateData(irTreeDeclaration.getMemoryTree(), indexVsType);
		return aggregateData;
	}


	protected boolean chkIfShiftCountIsMoreThanSizeOfType(BasicType leftBasicType,
			Translated rightExprAndType) {
		IRTreeConst irTreeConst = (IRTreeConst)rightExprAndType.getIRTree();
		int shiftCount = Integer.parseInt(irTreeConst.getApInt().getVal());
		if(leftBasicType instanceof IntTypeEntry && shiftCount >= 32)
			return true;
		else if(leftBasicType instanceof DoubleTypeEntry && shiftCount >= 64)
			return true;
		else if(leftBasicType instanceof ShortTypeEntry && shiftCount >= 16)
			return true;
		else if(leftBasicType instanceof FloatTypeEntry && shiftCount >= 32)
			return true;
		else if(leftBasicType instanceof LongTypeEntry && shiftCount >= 64)
			return true;
		else if(leftBasicType instanceof CharTypeEntry && shiftCount >= 8)
			return true;

		return false;
	}

	protected void initializeAllElementsToDefaultValuesAndAssignRestOfTheValues(CompilationContext compilationContext, Stack<IRTree> irTreeStack, String name, ArrayTypeEntry arrayInit, List<IRTreeConst> listOfIRTreeConstants) {
		Type arrayType = getLLVMType(arrayInit, true, compilationContext);
		Type containedType = ((ArrayType)arrayType).getContainedType();
		PointerType pointerType = null;
		try{
			pointerType = Type.getPointerType(compilationContext, arrayType, 0);
		}
		catch(TypeCreationException e){
			e.printStackTrace();
		}
		Value memoryVal = new Value(pointerType);
		memoryVal.setName(name);
		IRTreeMemory irTree = new IRTreeMemory(memoryVal);
		IRTreeCallExp callExp = createIntrinsicCallExpr("llvm.memset.p0i8.i64", compilationContext, irTree, false, false, arrayType, null, null, null, irTreeStack, true, arrayType);
		irTreeStack.push(callExp);



		List<IRTree> listOfMemberIRTree = new ArrayList<IRTree>();
		List<Type> listOfTypes = new ArrayList<Type>();

		for(IRTreeConst const1 : listOfIRTreeConstants){
			listOfMemberIRTree.add(const1);
			listOfTypes.add(containedType);
		}

		translateInitializationOfComplexTypes(listOfMemberIRTree, compilationContext, irTreeStack, listOfTypes, pointerType, irTree);
	}
	/**
	 * This will handle initialization of Complex Types where we don't initialize all the elements
	 * for e.g. int arr[20][2] = {{1,2},{3,4}}
	 * @param listOfMemberIRTree
	 * @param compilationContext
	 * @param irTreeStack
	 * @param listOfTypes
	 * @param pointerType
	 * @param intrmTree
	 */
	public void translateInitializationOfComplexTypes(List<IRTree> listOfMemberIRTree, CompilationContext compilationContext, Stack<IRTree> irTreeStack, List<Type> listOfTypes, PointerType pointerType, IRTree intrmTree) {
		List<Entry<IRTreeIndex, Type>> indexVsType = null;
		IRTreeAggregateData aggregateData = null;
		int indx  = 0;
		for(IRTree irTree : listOfMemberIRTree){
			indexVsType = new ArrayList<Map.Entry<IRTreeIndex,Type>>();
			Type typ = listOfTypes.get(indx);
			IRTreeIndex irTreeIndex1 = null;
			IRTreeIndex irTreeIndex2 = null;
			try {
				irTreeIndex1 = new IRTreeIndex(new IRTreeConst(ConstantInt.create(Type.getInt32Type(compilationContext, false), 0, false)), false);
				irTreeIndex2 = new IRTreeIndex(new IRTreeConst(ConstantInt.create(Type.getInt32Type(compilationContext, false), indx, false)), false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			Entry<IRTreeIndex, Type> entry_indexVsType1 = getMapEntry(irTreeIndex1 , pointerType);
			Entry<IRTreeIndex, Type> entry_indexVsType2 = getMapEntry(irTreeIndex2 , typ);
			indexVsType.add(entry_indexVsType1);
			indexVsType.add(entry_indexVsType2);
			aggregateData = new IRTreeAggregateData(intrmTree, indexVsType );

			if(irTree instanceof IRTreeConst){
				IRTreeConst const1 = (IRTreeConst)irTree;
				Constant constant = (Constant)const1.getExpressionValue();
				if(constant instanceof ConstantArray){
					ConstantArray constantArray = (ConstantArray)constant;
					List<IRTree> listOfConstIrTrees = new ArrayList<IRTree>();
					List<Type> listOfTyps = new ArrayList<Type>();
					List<Constant> listOfConsts = constantArray.getVal();
					for(Constant constant2 : listOfConsts){
						listOfConstIrTrees.add(new IRTreeConst(constant2));
						listOfTyps.add(constant2.getType());
					}
					PointerType ptrType = null;
					try{
						ptrType = Type.getPointerType(compilationContext, typ, 0);
					}
					catch(TypeCreationException e){
						e.printStackTrace();
					}
					translateInitializationOfComplexTypes(listOfConstIrTrees, compilationContext, irTreeStack, listOfTyps, ptrType, aggregateData);
					indx++;
					continue;
				}
			}

			IRTree irTree2 = translateAssignStm(aggregateData, irTree, -1, false, compilationContext);
			irTreeStack.push(irTree2);
			indx++;
		}
	}

	public ConstantExpr createConstantExpressionForString(IRTreeDeclaration irTreeDeclaration, ConstantArray constantArray, CompilationContext compilationContext) {
		List<Pair<Value, Type>> indexVsType = new ArrayList<Pair<Value,Type>>();
		ConstantInt first = null;
		Type second1 = null;
		ConstantExpr constantExpr = null;
		try {
			first = ConstantInt.create(Type.getInt32Type(compilationContext, true), 0, true);
			second1 = Type.getPointerType(compilationContext, constantArray.getType(), 0);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (TypeCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Pair<Value, Type> pair1 = new Pair<Value, Type>(first, second1);
		indexVsType.add(pair1);
		Type second2 = ((ArrayType)constantArray.getType()).getContainedType();
		Pair<Value, Type> pair2 = new Pair<Value, Type>(first, second2);
		indexVsType.add(pair2);
		GetElementPtrInst elementPtrInst = null;
		try {
			Value ptr = irTreeDeclaration.getMemoryTree().getMemory();
			PointerType ptrType = (PointerType) ptr.getType();
			Type pointeeType = ptrType.getContainedType();
			elementPtrInst = GetElementPtrInst.create(null, ptr, indexVsType, pointeeType.toString(), null);
			elementPtrInst.setIsInBounds(true);
			List<Value> operands = new ArrayList<Value>();
			operands.add(elementPtrInst);
			constantExpr = new ConstantExpr(elementPtrInst.getType(), operands , elementPtrInst.getInstructionID());
		} catch (InstructionCreationException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return constantExpr;
	}

	public IRTreeType getNamedTypeForEnum(BasicType basicType, Type type) {
		if(basicType instanceof TypeDefNameTypeEntry){
			TypeDefNameTypeEntry typeDefNameTypeEntry = (TypeDefNameTypeEntry)basicType;
			TypeEntryWithAttributes actualType = typeDefNameTypeEntry.getActualBasicType();
			return getNamedTypeForEnum(actualType.getBasicType(), type);
		}
		else if(basicType instanceof EnumSpecTypeEntry){
			EnumSpecTypeEntry enumSpecTypeEntry = (EnumSpecTypeEntry)basicType;
			String tagName = enumSpecTypeEntry.getTag();
			if(tagName == null)
				return null;
			String namedTypeName = "enum." + tagName;
			Value value = new Value(type);
			value.setName(namedTypeName);
			IRTreeType irTreeType = new IRTreeType(value);
			return irTreeType;
		}
		return null;
	}
}