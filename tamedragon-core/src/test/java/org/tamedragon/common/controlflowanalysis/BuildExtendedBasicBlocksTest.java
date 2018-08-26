package org.tamedragon.common.controlflowanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BuildExtendedBasicBlocksTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(BuildExtendedBasicBlocksTest.class);
	
	/*
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

		Semantic semanticAnalyzer = new Semantic(filename, compilationContext); 	    
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
				ValueName.resetTempCount();
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

	
	@Test
	public void runRandomTest() throws Exception{
		CompilationContext compilationContext = new CompilationContext();
		
		String path = "TestData/CSrc/Optimizations";
		String cSrcfilename = "ForWithIfElse2.c";

		List<IRTree> irTreeList = generateIRTree(path, cSrcfilename, compilationContext);
		IRTree body = irTreeList.get(0);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(compilationContext, cSrcfilename);
		llvmirGenerator.generateCodeForBasicBlock(null, (IRTreeStatementList)body);

		List<String> instrs = llvmirGenerator.emitInstructions();
		LOG.debug("Raw LLVM:");
		printInstructions(instrs);

		//CFG cfg = llvmirGenerator.getCFG();
		CFG cfg = llvmirGenerator.getModule().getFunctions().get(0).getCfg();

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(cfg);
		LOG.debug("AFTER MEM2REG: ");
		instrs = emitInstructions(cfg);
		printInstructions(instrs);

	
		// ADCE
		LOG.debug("CFG AFTER ADCE: ");
		ADCE adce = new ADCE();
		adce.execute(cfg);
		
		instrs = emitInstructions(cfg);
		printInstructions(instrs);
	}
*/
}
