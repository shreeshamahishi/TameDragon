package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class StdioTest extends TestInitializer {

	private PreprocessorSegments preprocessorSegments;	

	@Before
	public void setUp(){		
		String includePath = "include";
		//String includePath = "";

		/*try{
			Properties settings = new Properties();
			settings.load(new FileReader(new File("CompilerSettings.properties")));
			includePath = settings.getProperty("includePath");
		}
		catch(Exception e){
			System.out.println("Warning: Could not locate library include paths, using default");
			assertTrue(false);
		}	*/	

		String includeFilePath = includePath + "/" + "stdio.h";		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(includeFilePath).getFile());
		String absolutePath = file.getAbsolutePath();

		// Run the pre-processor
		PreprocessorMain ppMain = new PreprocessorMain(absolutePath);	
		InputStream inStream = ppMain.replaceTrigraphSequencesAndSpliceLines(absolutePath);

		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(inStream);
		assertNotNull(preprocessorSegments);			
	}

	@Test
	public void test1() {    
		/*
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		StringBuffer sb = preprocessorSegments.process(sourceFilePath);		
		String codeText = sb.toString();
		assertTrue(!codeText.contains("#define"));                      // should not have any more directives
		
		printDefs();
		*/
	}	
}
