package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.compilers.clang.CLangUtils;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.ErrorIterator;
import org.tamedragon.compilers.clang.SourceLocation;
import org.tamedragon.compilers.clang.SourceLocationAndMsg;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class ConditionalTest10 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	private Properties properties;
	private PreprocessorMain ppMain;
	
	@Before
	public void setUp(){	
		properties = LLVMUtility.getDefaultProperties();
		Environments environments = Environments.getInstance();
		environments.reset();
		sourceFilePath ="CSrc/Preprocessor/ConditionalTest10.c"; 
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		String absolutePath = file.getAbsolutePath();
		
		ppMain = new PreprocessorMain(absolutePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(absolutePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);			
	}
	
	@Test
	public void test1() throws Exception {      		
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		List<PreprocessorUnit> units = preprocessorSegments.getUnits();
		assertTrue(units.size() == 5);
		assertTrue(units.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(units.get(1).getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		assertTrue(units.get(2).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(units.get(3).getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(units.get(4).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		String absolutePath = file.getAbsolutePath();
		
		StringBuffer sb = preprocessorSegments.process(absolutePath, ppMain.getDependenciesDag(), true);		
		String codeText = sb.toString();
		
		/*System.out.println("************ FINAL PROCESSED CODE: ");
		System.out.println(codeText);
		System.out.println("************ END FINAL PROCESSED CODE: ");
		*/
		assertTrue(!codeText.contains("#define")); // should not have any more directives
		assertTrue(!codeText.contains("#ifdef"));  // should not have any more directives
		assertTrue(!codeText.contains("#endif"));  // should not have any more directives
		assertTrue(!codeText.contains("#else"));   // should not have any more directives
		assertTrue(!codeText.contains("#elif"));   // should not have any more directives
		
		int numLinesInCode = getNumLinesInFile(absolutePath);
		int numLinesInProcessedCode = getNumLinesInString(codeText);
		
		System.out.println(codeText);
		
		assertTrue(numLinesInCode == numLinesInProcessedCode);
		
	}	
	
	@Test
	public void checkFullTranslation() throws Exception{
		// This code should not compile
		String targetDesc = settings.getProperty("target");
		ErrorHandler errorHandler = ErrorHandler.getInstance();
		errorHandler.reset();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		String absolutePath = file.getAbsolutePath();
		
		PreprocessorMain ppMain = new PreprocessorMain(absolutePath);			
		InputStream sourceFileInputStream = ppMain.process(true); 
		TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);
		CompilationContext compilationContext = new CompilationContext();
		Semantic semanticAnalyzer = new Semantic(properties, sourceFilePath, compilationContext); 	    
		semanticAnalyzer.translateAbstractTree(translationUnit);    	  
		
		assertTrue(errorHandler.getNumErrors() == 5);
				
		errorHandler.displayResult();
		
		int count = 1;	
		
		ErrorIterator iter = new ErrorIterator(sourceFilePath);
		try{
			while(iter.hasNext()){
				SourceLocationAndMsg srcLcAndMsg =  iter.next();
				SourceLocation srcLc = srcLcAndMsg.getSrcLocation();
				String msg = srcLcAndMsg.getMsg();
				
				int line = srcLc.getLineNum();
				
				if(count == 1){
					assertTrue(line == 26); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "i" + ErrorHandler.E_NOT_DEFINED));
				}
				if(count == 2){
					assertTrue(line == 26); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "j" + ErrorHandler.E_NOT_DEFINED));
				}
				if(count == 3){
					assertTrue(line == 27); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "j" + ErrorHandler.E_NOT_DEFINED));
				}
				if(count == 4){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "m" + ErrorHandler.E_NOT_DEFINED));
				}
				if(count == 5){
					assertTrue(line == 29); 
					assertTrue(msg.equals(ErrorHandler.ERROR + "k" + ErrorHandler.E_NOT_DEFINED));
				}
								
				count++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		assertTrue(count == 6);		
	}
}
