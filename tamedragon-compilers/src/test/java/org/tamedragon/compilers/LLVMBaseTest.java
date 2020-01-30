package org.tamedragon.compilers;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.llvmir.instructions.LLVMIRGenerator;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.semantics.ClangExternalDecl;
import org.tamedragon.compilers.clang.semantics.ClangFunctionDef;
import org.tamedragon.compilers.clang.semantics.ClangTransUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;

public class LLVMBaseTest {
	Module module;
	protected LLVMIREmitter emitter;
	protected Properties properties;
	
	@Before
	public void setUp(){
		emitter = LLVMIREmitter.getInstance();
		properties = LLVMUtility.getDefaultProperties();
		emitter.reset();
	}
	
	public Module getModule() {
		return module;
	}

	// TODO Replace this later with the LLMVIR compiler to get the CFG. This entire
	// should be moved to Core.
	protected List<String> getRawLLVRIRInstrs(String rootPath, String srcFileName) throws Exception {
		CompilationContext compilationContext = new CompilationContext();
		List<IRTree> irTreeList = generateIRTreeFromCSource(properties, rootPath, srcFileName, compilationContext);

		LLVMIRGenerator llvmirGenerator = new LLVMIRGenerator(properties, compilationContext, srcFileName);
		for(IRTree body : irTreeList)
			llvmirGenerator.generateCode((IRTreeStatementList)body);
		
		llvmirGenerator.removeUnUsedStaticOrExternGlobalVariables();
		llvmirGenerator.removeAnyUnUsedStaticFunctionOrFunctionDeclrs();

		module = llvmirGenerator.getModule();

		System.out.println("RAW LLVM IR: ");
		
		List<String> instrs = emitter.emitInstructions(module);
		printAsm(instrs);
		return instrs;
	}

	protected void printAsm(List<String> instrs) {
		for(String instr : instrs){
			if(instr.equals("\n"))
				System.out.print(instr);
			else
				System.out.println(instr);
		}
	}
	
	private static List<IRTree> generateIRTreeFromCSource(Properties properties, String path, String filename, 
			CompilationContext compilationContext) throws Exception {
		//CLangUtils.populateSettings();
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		compilerSettings.setCompilationContext(compilationContext);
		//compilerSettings.setInstanceProjectPath(path);
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

		String fullFilePath = path + filename;

		// Run the pre-processor			
		PreprocessorMain ppMain = new PreprocessorMain(fullFilePath);
		InputStream is = ppMain.process(true); 

		// Translate to abstract syntax tree
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(is);

		// Pass through semantic analyzer and translate to assembly tree
		Semantic semanticAnalyzer = new Semantic(properties, fullFilePath, compilationContext); 	    
		List<ClangTransUnit> translationUnits =  semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		errorHandler.displayResult();
		if(errorHandler.getNumErrors() != 0) 
			Assert.fail("Compilation Errors");

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
				irTreeList.add(body);
			}
			else if(ctu instanceof ClangExternalDecl){
				ClangExternalDecl externalDecl = (ClangExternalDecl)ctu;
				body = externalDecl.getExternalDecl();
				irTreeList.add(body);
			}
		}

		return irTreeList;
	}
}