package org.tamedragon.compilers.clang.tests.irtranslate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.DominatorTreeNode;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.controlflowanalysis.ExtendedBasicBlockBuilder;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.LLVMIRGenerator;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.semantics.ClangExternalDecl;
import org.tamedragon.compilers.clang.semantics.ClangFunctionDef;
import org.tamedragon.compilers.clang.semantics.ClangTransUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;

public class TestRunners {

	
	private Properties properties = LLVMUtility.getDefaultProperties();
	
	private List<IRTree> generateIRTree(String path, String filename, 
			CompilationContext compilationContext) throws Exception {
		CLangUtils.populateSettings();
		CompilerSettings compilerSettings = CompilerSettings.getInstance();

		compilerSettings.setInstanceProjectPath(path);
		ErrorHandler errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();
		DefinitionsMap defsMap = DefinitionsMap.getInstance();
		defsMap.clearDefinitions();
		Environments environments = Environments.getInstance();
		environments.reset();		

		boolean isDebugMode = true;

		if(isDebugMode){
			EnvironmentConstants.VALUES.put(EnvironmentConstants.MODE, EnvironmentConstants.DEBUG_MODE);
		}

		filename = path + "/" + filename;
		
		// Generate name of the output file
		File inFile = new File(filename);

		// Run the pre-processor			
		InputStream sourceFileInputStream = new FileInputStream(inFile);

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);

		// Pass through semantic analyzer and translate to assembly tree

		Semantic semanticAnalyzer = new Semantic(properties, filename, compilationContext); 	    
		List<ClangTransUnit> translationUnits =  semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		if(errorHandler.getNumErrors() != 0) 
			return null;

		// No errors in semantic analysis; continue with intermediate code generation
		// for each function and generate intermediate code

		int numTransUnits = translationUnits.size();
		List<IRTree> irTreeList = new ArrayList<IRTree>();
		
		for(int i = 0; i < numTransUnits; i++){
			ClangTransUnit ctu = (ClangTransUnit) translationUnits.get(i);
			if(!(ctu instanceof ClangFunctionDef) && !(ctu instanceof ClangExternalDecl))
				continue;

			IRTree body = null;

			if(ctu instanceof ClangFunctionDef){
				ClangFunctionDef functionDef = (ClangFunctionDef) ctu;
				body = functionDef.getExternalDecl();
				//llvmirGenerator.generateCodeForBasicBlock(null, (IRTreeStatementList)body);
				irTreeList.add(body);
			}
			else if(ctu instanceof ClangExternalDecl){
				ClangExternalDecl externalDecl = (ClangExternalDecl)ctu;
				body = externalDecl.getExternalDecl();
				//llvmirGenerator.generateCode((IRTreeStatementList)body);
			}
		}
		
		return irTreeList;
	}

	@Ignore
	@Test
	public void runRandomTest() throws Exception{
		CompilationContext compilationContext = new CompilationContext();
		
		String path = "CSrc/Optimizations";
		String cSrcfilename = "ForWithIfElse2.c";

		List<IRTree> irTreeList = generateIRTree(path, cSrcfilename, compilationContext);
		IRTree body = irTreeList.get(0);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(properties, compilationContext, cSrcfilename);
		llvmirGenerator.generateCode((IRTreeStatementList)body);

		List<String> instrs = null;
		
		Module module = llvmirGenerator.getModule();
		LLVMIREmitter llvmirEmitter = LLVMIREmitter.getInstance();
		llvmirEmitter.reset();
		instrs = llvmirEmitter.emitInstructions(module);
		
		System.out.println("Raw LLVM:");
		printDescriptions(instrs);

		Function function = llvmirGenerator.getModule().getFunctions().get(0);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		llvmirEmitter.reset();
		instrs = llvmirEmitter.emitInstructions(module);
		printDescriptions(instrs);

		/*System.out.println("CFG AFTER MEM2REG: ");
		cfg.toString();

		System.out.println("REVERSE OF ABOVE CFG: ");
		CFG revCFG = cfg.reverse();
		revCFG.toString();
		*/
		// ADCE
		System.out.println("CFG AFTER ADCE: ");
//		ADCE adce = new ADCE();
//		adce.execute(cfg);
		
		instrs = llvmirEmitter.emitInstructions(module);
		printDescriptions(instrs);

	}
	
	@Test
	public void runTestForExtendedBasicBlocks1() throws Exception{
		CompilationContext compilationContext = new CompilationContext();
		
		String path = "CSrc/ControlFlowAnalysis";
		String cSrcfilename = "ExtendedBasicBlocks1.c";
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		String folderPath = file.getAbsolutePath();

		List<IRTree> irTreeList = generateIRTree(folderPath, cSrcfilename, compilationContext);
		IRTree body = irTreeList.get(0);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(properties, compilationContext, cSrcfilename);
		llvmirGenerator.generateCode((IRTreeStatementList)body);

		List<String> instrs = null;
		
		Module module = llvmirGenerator.getModule();
		LLVMIREmitter llvmirEmitter = LLVMIREmitter.getInstance();
		llvmirEmitter.reset();
		instrs = llvmirEmitter.emitInstructions(module);
		
		System.out.println("Raw LLVM:");

		//CFG cfg = llvmirGenerator.getCFG();
		CFG cfg = llvmirGenerator.getModule().getFunctions().get(0).getCfg();
		llvmirEmitter.reset();
		instrs = llvmirEmitter.emitInstructions(module);
		printDescriptions(instrs);
		
		// Generate EBBs
		ExtendedBasicBlockBuilder ebbBuilder = new ExtendedBasicBlockBuilder(cfg);
		Map<BasicBlock, List<BasicBlock>> ebbs = ebbBuilder.build();
		printEbbs(ebbs);

	}
	
	@Test
	public void runTestForExtendedBasicBlocks2() throws Exception{
		CompilationContext compilationContext = new CompilationContext();
		
		String path = "CSrc/ControlFlowAnalysis";
		String cSrcfilename = "ExtendedBasicBlocks2.c";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		String folderPath = file.getAbsolutePath();
		
		List<IRTree> irTreeList = generateIRTree(folderPath, cSrcfilename, compilationContext);
		IRTree body = irTreeList.get(0);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(properties, compilationContext, cSrcfilename);
		llvmirGenerator.generateCode((IRTreeStatementList)body);

		System.out.println("Raw LLVM:");

		//CFG cfg = llvmirGenerator.getCFG();
		CFG cfg = llvmirGenerator.getModule().getFunctions().get(0).getCfg();
		List<String> instrs  = null;
		
		Module module = llvmirGenerator.getModule();
		LLVMIREmitter llvmirEmitter = LLVMIREmitter.getInstance();
		llvmirEmitter.reset();
		instrs = llvmirEmitter.emitInstructions(module);
		
		printDescriptions(instrs);
		
		// Generate EBBs
		ExtendedBasicBlockBuilder ebbBuilder = new ExtendedBasicBlockBuilder(cfg);
		Map<BasicBlock, List<BasicBlock>> ebbs = ebbBuilder.build();
		printEbbs(ebbs);

	}
	
	@Test
	public void runTestForSimpleDominatorTreeConsruction() throws Exception{
		CompilationContext compilationContext = new CompilationContext();
		
		String path = "CSrc/ControlFlowAnalysis";
		String cSrcfilename = "ExtendedBasicBlocks2.c";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		String folderPath = file.getAbsolutePath();
		
		List<IRTree> irTreeList = generateIRTree(folderPath, cSrcfilename, compilationContext);
		IRTree body = irTreeList.get(0);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(properties, compilationContext, cSrcfilename);
		llvmirGenerator.generateCode((IRTreeStatementList)body);

		//CFG cfg = llvmirGenerator.getCFG();
		Module module = llvmirGenerator.getModule();
		Function function = module.getFunctions().get(0);
		List<String> instrs  = null;
		
		LLVMIREmitter llvmirEmitter = LLVMIREmitter.getInstance();
		instrs = llvmirEmitter.emitInstructions(module);
		System.out.println("Raw LLVM:");
		printDescriptions(instrs);
		
		System.out.println("DOMINATOR TREE:");
		
		DominatorCalculationContext dominatorCalculationContext = 
			new DominatorCalculationContext(function);
		//dominatorCalculationContext.setLengauerTarjan();
		dominatorCalculationContext.setIterativeMethod();

		DominatorTree dominatorTree = dominatorCalculationContext.getDominatorTree();
		DominatorTreeNode dominatorTreeNode = dominatorTree.getDominatorTreeRoot();
		
		List<String> dominatorTreeDescriptions = llvmirEmitter.emitDominatorTree(dominatorTreeNode);
		
		/*
		DominatorCalculationContext dominatorCalculationContext = 
			new DominatorCalculationContext(cfg, cfg.getStartNode());
		dominatorCalculationContext.setLengauerTarjan();

		DominatorTree dominatorTree = dominatorCalculationContext.getDominatorTree();
		DominatorTreeNode dominatorTreeNode = dominatorTree.getDominatorTreeRoot();
		
		List<String> dominatorTreeDescriptions = llvmirEmitter.emitDominatorTree(dominatorTreeNode);
		*/
		printDescriptions(dominatorTreeDescriptions);
	}

	private void printDescriptions(List<String> stmtList) {
		for(String stmt : stmtList)
			System.out.println(stmt);
	}

	private void printEbbs(Map<BasicBlock, List<BasicBlock>> ebbs) {
		LLVMIREmitter llvmirEmitter = LLVMIREmitter.getInstance();
		
		Set<BasicBlock> roots = ebbs.keySet();
		Iterator<BasicBlock> rootsIterator = roots.iterator();
		while(rootsIterator.hasNext()){
			BasicBlock root = rootsIterator.next();
			System.out.print("EBB Root: " + llvmirEmitter.getValidName(root) + " and other elements are: ");
			
			List<BasicBlock> otherElementsInEbb = ebbs.get(root);
			for(BasicBlock elem: otherElementsInEbb){
				System.out.print(llvmirEmitter.getValidName(elem) + ", ");
			} 
			System.out.println("");
		}
	}
}
