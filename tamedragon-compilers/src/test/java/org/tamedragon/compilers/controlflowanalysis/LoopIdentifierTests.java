package org.tamedragon.compilers.controlflowanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

import org.jgraph.graph.DefaultEdge;
import org.junit.Before;
import org.junit.Test;

import org.tamedragon.common.TreeNode;
import org.tamedragon.common.controlflowanalysis.LoopIdentifier;
import org.tamedragon.common.controlflowanalysis.LoopInfo;
import org.tamedragon.common.controlflowanalysis.LoopNestingTree;
import org.tamedragon.common.controlflowanalysis.LoopNestingTreeNode;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallingConv;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.exceptions.TypeCreationException;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.LLVMBaseTest;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;

public class LoopIdentifierTests extends LLVMBaseTest{

	private CompilationContext compilationContext;
	private Function function;
	
	private CompilerSettings compilerSettings;
	private String projectPath = "CSrc/ControlFlowAnalysis/LoopIdentifierTests/";
	private String projectRootPath;
	

	
	@Before
	public void setUp(){
		super.setUp();
		compilationContext = new CompilationContext();
		
		PointerType ptrToFunctype = null;
		Vector<Type> paramTypes = new Vector<Type>();
		Type funcType = null;
		try {
			funcType = Type.getFunctionType(compilationContext, Type.getInt32Type(compilationContext, true),
						false, paramTypes );
			ptrToFunctype = Type.getPointerType(compilationContext, funcType, 0);
		} catch (TypeCreationException e) {fail("Function type could not be created");}
		
		Module module = new Module("sampleModule", compilationContext, null);
		CFG cfg = new CFG();
		function = Function.create(module, ptrToFunctype, null, CallingConv.C, "foo", cfg);
		
		properties = LLVMUtility.getDefaultProperties();
		
		CLangUtils.populateSettings();
		compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setProjectPath(projectPath);

		projectRootPath = compilerSettings.getProjectRoot();
	}
	
	@Test
	public void analyzeCodeWithNoLoop() throws Exception {
		String cSrcfilename =  "NoLoop.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 0);
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 0);
		
	}
	
	@Test
	public void analyzeCodeWithSimpleLoop() throws Exception {
		String cSrcfilename =  "SimpleLoop.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 1);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%10", "%2");
		assertNotNull(loopInfo);
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%10", "%2", "%6");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 1);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		assertEquals(lntNode1.getLoopInfo(),loopInfoList.get(0));

		List<LoopInfo> leafLoops = lnTree.getLeafLoops();
		assertTrue(leafLoops.size() == 1);
		assertTrue(leafLoops.contains(lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode1.getLoopInfo()));
		assertTrue(lnTree.getImmediateOuterLoop(lntNode1.getLoopInfo()) == null);
	}
	
	@Test
	public void analyzeCodeWithControlFlowInSimpleLoop() throws Exception {
		String cSrcfilename =  "ControlFlowInSimpleLoop.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 1);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%32", "%4");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%32", "%4", "%7", "%11", "%15", "%27", "%31");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 1);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		assertEquals(lntNode1.getLoopInfo(),loopInfoList.get(0));
		
		List<LoopInfo> leafLoops = lnTree.getLeafLoops();
		assertTrue(leafLoops.size() == 1);
		assertTrue(leafLoops.contains(lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode1.getLoopInfo()));
		assertTrue(lnTree.getImmediateOuterLoop(lntNode1.getLoopInfo()) == null);
		
	}
	
	@Test
	public void analyzeCodeWithTwoSimpleDisjointLoops() throws Exception {
		String cSrcfilename =  "TwoSimpleDisjointLoops.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 2);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%11", "%3");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%11", "%3", "%7");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%26", "%18");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%18", "%22", "%26");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 2);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		LoopNestingTreeNode lntNode2 = (LoopNestingTreeNode) childrenOfRoot.get(1);
		assertTrue(loopInfoList.contains(lntNode1.getLoopInfo()));
		assertTrue(loopInfoList.contains(lntNode2.getLoopInfo()));
		
		List<LoopInfo> leafLoops = lnTree.getLeafLoops();
		assertTrue(leafLoops.size() == 2);
		assertTrue(leafLoops.contains(lntNode1.getLoopInfo()));
		assertTrue(leafLoops.contains(lntNode2.getLoopInfo()));
		assertFalse(lnTree.nestsInside(lntNode2.getLoopInfo(), lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode2.getLoopInfo()));
		assertTrue(lnTree.getImmediateOuterLoop(lntNode2.getLoopInfo()) == null);
		assertTrue(lnTree.getImmediateOuterLoop(lntNode1.getLoopInfo()) == null);

	}
	
	@Test
	public void analyzeCodeWithLoopsWithBreaks() throws Exception {
		String cSrcfilename =  "LoopsWithBreaks.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);
		assertTrue(loopInfoList.size() == 1);	
		
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%23", "%4");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%23", "%4", "%8", "%22", "%18");
	}
	
	@Test
	public void analyzeCodeWithSimpleNesting() throws Exception {
		String cSrcfilename =  "SimpleNestedLoops.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 2);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%23", "%3");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%23", "%3", "%7", "%11", "%15", "%22", "%19");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%19", "%11");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%19", "%11", "%19");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 1);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		Vector<TreeNode> childrenOfNode1 = lntNode1.getChildren();
		assertTrue(childrenOfNode1.size() == 1);
		LoopNestingTreeNode lntNode2 = (LoopNestingTreeNode) childrenOfNode1.get(0);
		assertTrue(loopInfoList.contains(lntNode1.getLoopInfo()));
		assertTrue(loopInfoList.contains(lntNode2.getLoopInfo()));
		
		List<LoopInfo> leafLoops = lnTree.getLeafLoops();
		assertTrue(leafLoops.size() == 1);
		assertTrue(leafLoops.contains(lntNode2.getLoopInfo()));
		assertTrue(lnTree.nestsInside(lntNode2.getLoopInfo(), lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode1.getLoopInfo()));
		assertFalse(lnTree.isAnOutermostLoop(lntNode2.getLoopInfo()));
		assertEquals(lnTree.getImmediateOuterLoop(lntNode2.getLoopInfo()), lntNode1.getLoopInfo());
		assertTrue(lnTree.getImmediateOuterLoop(lntNode1.getLoopInfo()) == null);

	}
	
	@Test
	public void analyzeCodeWithMultipleNesting() throws Exception {
		String cSrcfilename =  "MultipleNestedLoops.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 3);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%33", "%2");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%33", "%2", "%6", "%10", "%13", "%17", "%21", "%25", "%28", "%32");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%28", "%10");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%28", "%10", "%13", "%17", "%21", "%25");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%25", "%17");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%25", "%17", "%21");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 1);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		Vector<TreeNode> childrenOfNode1 = lntNode1.getChildren();
		assertTrue(childrenOfNode1.size() == 1);
		LoopNestingTreeNode lntNode2 = (LoopNestingTreeNode) childrenOfNode1.get(0);
		Vector<TreeNode> childrenOfNode2 = lntNode2.getChildren();
		assertTrue(childrenOfNode2.size() == 1);
		LoopNestingTreeNode lntNode3 = (LoopNestingTreeNode) childrenOfNode2.get(0);
		assertTrue(loopInfoList.contains(lntNode1.getLoopInfo()));
		assertTrue(loopInfoList.contains(lntNode2.getLoopInfo()));
		assertTrue(loopInfoList.contains(lntNode3.getLoopInfo()));
		
		List<LoopInfo> leafLoops = lnTree.getLeafLoops();
		assertTrue(leafLoops.size() == 1);
		assertTrue(leafLoops.contains(lntNode3.getLoopInfo()));
		assertTrue(lnTree.nestsInside(lntNode3.getLoopInfo(), lntNode2.getLoopInfo()));
		assertTrue(lnTree.nestsInside(lntNode2.getLoopInfo(), lntNode1.getLoopInfo()));
		assertTrue(lnTree.isAnOutermostLoop(lntNode1.getLoopInfo()));
		assertFalse(lnTree.isAnOutermostLoop(lntNode2.getLoopInfo()));
		assertFalse(lnTree.isAnOutermostLoop(lntNode3.getLoopInfo()));
		assertEquals(lnTree.getImmediateOuterLoop(lntNode2.getLoopInfo()), lntNode1.getLoopInfo());
		assertEquals(lnTree.getImmediateOuterLoop(lntNode3.getLoopInfo()), lntNode2.getLoopInfo());
		assertTrue(lnTree.getImmediateOuterLoop(lntNode1.getLoopInfo()) == null);

	}
	
	@Test
	public void analyzeCodeWithComplexAndDisjointLoopNesting() throws Exception {
		String cSrcfilename =  "ComplexAndDisjointLoops.c";

		LoopIdentifier loopIdentifier = runLoopAnalyzer(cSrcfilename);

		List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);
		
		assertTrue(loopInfoList.size() == 5);
		LoopInfo loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%28", "%25");
		List<BasicBlock> nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%28", "%25");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%40", "%4");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%40", "%4", "%8", "%12", "%18", "%22", "%25", "%28",
										"%34", "%37", "%38", "%39");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%74", "%67");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%74", "%67", "%71");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%78", "%61");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%78", "%61", "%64", "%74", "%67", "%71", "%77");
		
		loopInfo = getLoopInfoWithBackEdge(loopInfoList, "%81", "%50");
		nodesInLoop = loopInfo.getNodesInLoop();
		verifyNodesInLoop(nodesInLoop, "%78", "%61", "%64", "%74", "%67", "%71", "%77", "%81",
									"%50", "%53", "%58", "%59", "%60");
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 2);
		LoopNestingTreeNode lntNode1 = (LoopNestingTreeNode) childrenOfRoot.get(0);
		LoopInfo loopInfo1 = lntNode1.getLoopInfo();
		LoopNestingTreeNode lntNode2 = (LoopNestingTreeNode) childrenOfRoot.get(1);
		LoopInfo loopInfo2 = lntNode2.getLoopInfo();
		
		LoopInfo outermost4 = null, outermost50 = null;
		if(emitter.getValidName((BasicBlock)loopInfo1.getBackEdgeTarget()).equals("%4")){
			outermost4 = loopInfo1;
			outermost50 = loopInfo2;
		}
		else if(emitter.getValidName((BasicBlock)loopInfo1.getBackEdgeTarget()).equals("%50")){
			outermost4 = loopInfo2;
			outermost50 = loopInfo1;
		}
		
		assertTrue(lnTree.isAnOutermostLoop(outermost4));
		assertTrue(lnTree.isAnOutermostLoop(outermost50));
		
		List<LoopInfo> leaves  = lnTree.getLeafLoops();
		for(LoopInfo leaf : leaves){
			if(emitter.getValidName((BasicBlock)leaf.getBackEdgeTarget()).equals("%25")){
				LoopInfo liNxtOuter = lnTree.getImmediateOuterLoop(leaf);
				assertTrue(liNxtOuter == outermost4);
				assertTrue(lnTree.nestsInside(leaf, liNxtOuter));
			}
			else if(emitter.getValidName((BasicBlock)leaf.getBackEdgeTarget()).equals("%67")){
				LoopInfo liNxtOuter1 = lnTree.getImmediateOuterLoop(leaf);
				assertTrue(emitter.getValidName((BasicBlock)liNxtOuter1.getBackEdgeTarget()).equals("%61"));
				assertTrue(lnTree.nestsInside(leaf, liNxtOuter1));
				LoopInfo liNxtOuter2 = lnTree.getImmediateOuterLoop(liNxtOuter1);
				assertTrue(liNxtOuter2 == outermost50);
				assertTrue(lnTree.nestsInside(liNxtOuter1, liNxtOuter2));
			}
			else{
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void analyzeCodeWithFunctionStartingWithALoop() throws Exception {
		String cSrcfilename =  "FunctionStartingWithALoop.c";

		Function function = getCFGAfterMemRegAndEmptyBBRemoval(cSrcfilename);
		LoopIdentifier loopIdentifier = new LoopIdentifier(function);

		/*List<LoopInfo> loopInfoList = loopIdentifier.getLoopInfoList();
		boolean isNonReducible = loopIdentifier.isGraphIsNonReducible();
		assertFalse(isNonReducible);

		assertTrue(loopInfoList.size() == 0);
		
		LoopNestingTree lnTree = loopIdentifier.getLoopNestingTree();
		LoopNestingTreeNode lnTreeRoot = lnTree.getLoopNestingTreeRoot();
		Vector<TreeNode> childrenOfRoot = lnTreeRoot.getChildren();
		assertTrue(childrenOfRoot.size() == 0);
		*/
	}
	
	private LoopInfo getLoopInfoWithBackEdge(List<LoopInfo> loopInfoList,
				String tail , String head){
		
		for(LoopInfo loopInfo : loopInfoList){
			String src = emitter.getValidName(loopInfo.getBackEdgeSource());
			String dest = emitter.getValidName(loopInfo.getBackEdgeTarget());
			if(src.equals(tail) && dest.equals(head))
				return loopInfo;
		}
		
		return null;
	}
	
	
	private void verifyNodesInLoop(List<BasicBlock> nodesInLoop, String ... nodeNames){
		assertTrue(nodesInLoop.size() == nodeNames.length);
		
		for(String referenceNodeName : nodeNames){
			boolean nodeNameExistsInLoop = false;
			for(BasicBlock node : nodesInLoop){
				String nodeNameInLoopFound = emitter.getValidName(node);
				
				if(referenceNodeName.equals(nodeNameInLoopFound)){
					nodeNameExistsInLoop = true;
					break;
				}
			}
			assertTrue(nodeNameExistsInLoop);
		}
		
	}
	
	private Function getCFGAfterMemRegAndEmptyBBRemoval(String cSrcfilename)
			throws Exception{
		
		getRawLLVRIRInstrs(projectRootPath + projectPath, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(0);
		
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);
		
		return function;
		
	}

	private LoopIdentifier runLoopAnalyzer(String cSrcfilename) throws Exception  {

		getRawLLVRIRInstrs(projectRootPath + projectPath, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(0);

		LoopIdentifier loopIdentifier = new LoopIdentifier(function);
		return loopIdentifier;

	}
}
