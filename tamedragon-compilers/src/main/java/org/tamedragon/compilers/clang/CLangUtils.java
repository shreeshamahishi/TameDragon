package org.tamedragon.compilers.clang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.tamedragon.compilers.clang.abssyntree.ClangLLLexer;
import org.tamedragon.compilers.clang.abssyntree.ClangLLParser;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;

/*
 * A group of common utility functions statically defined.
 */

public class CLangUtils {
	
	public static void populateSettings(){		
		Properties settings = new Properties();
		
		ClassLoader classLoader = new CLangUtils().getClass().getClassLoader();
		File propertiesRefFile = new File(classLoader.getResource("CompilerSettings.properties").getFile());
		
		try{
			settings.load(new FileReader(propertiesRefFile));
		}
		catch(Exception e){
			ErrorHandler errorHandler = ErrorHandler.getInstance();
			errorHandler.addError(null, null, ErrorHandler.WARNING, null, ErrorHandler.W_SETTINGS_NO_PROPERTIES_FOUND);
		}
		
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		
		// Set the mode (debug or release)
		String debugMode = settings.getProperty("debugMode");
		if(debugMode != null){
			compilerSettings.setInstanceReplaceTrigraphSequences(debugMode);
		}
		else{   // Default is true
			compilerSettings.setInstanceReplaceTrigraphSequences("Y");
		}	
		
		// Set the target		
		String targetDesc = settings.getProperty("target");
		if(targetDesc != null){
			compilerSettings.setInstanceTarget(targetDesc);
		}
		else{  // Use MIPS are default
			compilerSettings.setInstanceTarget("MIPS");
		}
		
		// Set the replace trigraph sequence flag
		String replacets = settings.getProperty("replaceTrigraphSequences");
		if(replacets != null){
			compilerSettings.setInstanceReplaceTrigraphSequences(replacets);
		}
		else{   // Default is true
			compilerSettings.setInstanceReplaceTrigraphSequences("Y");
		}	
		
		// Set the include path
		String includePathStr = settings.getProperty("includePath");
		if(includePathStr != null){
			compilerSettings.setInstanceIncludePath(includePathStr);
		}
		else{   // Default is current folder
			compilerSettings.setInstanceIncludePath(File.pathSeparator);
		}	
		
		// Set the project path
		String projectPathStr = settings.getProperty("projectPath");
		if(includePathStr != null){
			compilerSettings.setInstanceProjectPath(projectPathStr);
		}
		else{   // Default is current folder
			compilerSettings.setInstanceProjectPath(File.pathSeparator);
		}	
		
		// Set the project path
		String newLineStr = settings.getProperty("newLine");
		if(includePathStr != null){
			compilerSettings.setInstanceNewLine(newLineStr);
		}
		else{   // Default is current folder
			compilerSettings.setInstanceNewLine("\r\n");
		}	
	}
	
	public static TranslationUnit getTranslationByLLParsing(InputStream sourceInputStream){
		TranslationUnit tu = null;
		try
		{
			// Create an input character stream from standard in
			ANTLRInputStream input = new ANTLRInputStream(sourceInputStream);
			// Create an ExprLexer that feeds from that stream
			ClangLLLexer lexer = new ClangLLLexer(input);
			// Create a stream of tokens fed by the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Create a parser that feeds off the token stream
			ClangLLParser parser = new ClangLLParser(tokens);
			// Begin parsing at rule prog

			tu = parser.translation_unit();
			tu.setErrors(parser.getErrorMsgs());
		}
		catch(Exception e) {			
			e.printStackTrace();
		}
		finally{
			try{
				if(sourceInputStream != null){
					sourceInputStream.close();
				}
			}
			catch(Exception ne){
				ne.printStackTrace();
			}
		}

		return tu;
	}
	
	public static TranslationUnit getTranslationByLLParsing(String sourceFilePath){
		TranslationUnit tu = null;
		try
		{
			FileInputStream sourceFileStream = new FileInputStream(new File(sourceFilePath));
			tu = getTranslationByLLParsing(sourceFileStream);

		}
		catch(Exception e) {			
			e.printStackTrace();
		}
		
		return tu;
	}  
	
	
	public static StringBuffer getStringBufferFromInputStream(InputStream is){
		
		String text = "";
		try{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				text += line + "\n";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new StringBuffer(text);
		
	}
	
	
}
