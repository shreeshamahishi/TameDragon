package org.tamedragon.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tamedragon.common.llvmir.irdata.ValueData;
import org.tamedragon.common.llvmir.semanticanalysis.LLVMSemantic;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.llvmir.utils.llvmGrammarLexer;
import org.tamedragon.common.llvmir.utils.llvmGrammarParser;

public class LLVMIRUtils {
	private Module module;
	protected LLVMIREmitter emitter;

	private static final Logger LOG = LoggerFactory.getLogger(LLVMIRUtils.class);
	
	public LLVMIRUtils() {
		emitter = LLVMIREmitter.getInstance();
		emitter.reset();		
	}
	
	public Module getModule() {
		return module;
	}

	public LLVMIREmitter getEmitter() {
		return emitter;
	}
	
	public List<String> getInstructionsList(String rootPath, String LLVMFileName){
		FileReader fr = null;
		BufferedReader br = null;
		List<String> instrs = null;
		try {
			String fullFilePath = rootPath + "/" + LLVMFileName;
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fullFilePath).getFile());
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			char[] chArr = new char[(int) file.length()];
			br.read(chArr);
			String str = new String(chArr);
			if (str != null) {
				ANTLRStringStream strStream = new ANTLRStringStream(str);
				llvmGrammarLexer lexer = new llvmGrammarLexer(strStream);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				llvmGrammarParser parser = new llvmGrammarParser(tokens);

				parser.llvm();

				System.out.println("Parsed sucessfully: " + file.getAbsolutePath());

				List<ValueData> irDataList = parser.getList();
				LLVMSemantic llvmSemantic = new LLVMSemantic(file.getName(), irDataList);
				module = llvmSemantic.semantic();
				emitter.reset();
				instrs = emitter.emitInstructions(module);
				printAsm(instrs);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		catch (RecognitionException re) {
			LOG.error(re.getMessage());
		}
		finally {
			if (fr != null) {
				try {
					fr.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} 
				catch (IOException e) {
					LOG.error(e.getMessage());
				}
			}
		}
		
		return instrs;
	}

	public void printAsm(List<String> instrs) {
		for(String instr : instrs){
			if(instr.equals("\n"))
				System.out.print(instr);
			else
				System.out.println(instr);
		}
	}
}