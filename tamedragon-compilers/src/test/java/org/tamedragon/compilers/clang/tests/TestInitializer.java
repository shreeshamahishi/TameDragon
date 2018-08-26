package org.tamedragon.compilers.clang.tests;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

import org.tamedragon.assemblyabstractions.constructs.AssemStm;
import org.tamedragon.assemblyabstractions.constructs.AssemStmList;
import org.tamedragon.compilers.clang.abssyntree.ClangLLLexer;
import org.tamedragon.compilers.clang.abssyntree.ClangLLParser;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.CPreprocessorLLLexer;
import org.tamedragon.compilers.clang.preprocessor.CPreprocessorLLParser;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.semantics.Environments;

public class TestInitializer {
	
	protected Properties settings;
	
	public TestInitializer(){
		
		try{
			settings = new Properties();
			settings.load(new FileReader(new File("CompilerSettings.properties")));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getNumLinesInFile(String filePath){
		String text = "";
		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				text += line + "\n";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return getNumLinesInString(text);
	}
	
	public int getNumLinesInString(String text){
		
		int numLines = 0;
		
		LineNumberReader ln = null;
		
		try{
			ln = new LineNumberReader(new CharArrayReader(text.toCharArray()));
			while(ln.readLine() != null){
				numLines++;
			}
			
			/*//String regExForNewLine = "(\\r)?\\n";
			String regExForNewLine = "\\n";
			String[] lines = text.split(regExForNewLine);
			
			return lines.length;
			*/
		}
		catch(Exception e){
			
		}
		try{
		if(ln != null)
			ln.close();
		}
		catch(Exception e) {}
		
		return numLines;
		
	}
	
	// For debugging after processing
	public void printDefs(){
		DefinitionsMap dm = DefinitionsMap.getInstance();
		Set<String> keys = dm.getKeys();
		Iterator<String> iter = keys.iterator();
		while(iter.hasNext()){
			String k = iter.next();
			System.out.println("Value for key " + k + " is " + dm.getDefinitionFromInstance(k));
		}		
	}
	
	public Vector<AssemStm> getFlatListOfStatements(AssemStmList stmList){
		
		Vector<AssemStm> flatList = new Vector<AssemStm>();
		
		AssemStm stm = stmList.getStm();
		flatList.add(stm);
		AssemStmList nextStmList = stmList.getStmList();
		if(nextStmList != null){
			flatList.addAll(getFlatListOfStatements(nextStmList));
		}
		
		return flatList;
		
	}
}
