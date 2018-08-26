package org.tamedragon.compilers.clang.tests.preprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.compilers.LLVMBaseTest;


public class TestingHeaderFiles extends LLVMBaseTest{
	private String projectPath = "CSrc/Preprocessor";
	private String includePath = "/usr/include";
	private List<String> headerFileNames = new ArrayList<String>();
	private String originalIncludePath = null;
	private Properties properties;

	@Before
	public void setUp(){
		properties = LLVMUtility.getDefaultProperties();
		emitter = LLVMIREmitter.getInstance();
		emitter.reset();
		originalIncludePath = LLVMUtility.getOriginalIncludePath(properties);
		//LLVMUtility.changeIncludePath(properties, includePath, "changing includePath to /usr/include");
		File file = new File(includePath);
		String[] fileNames = file.list();
		for(String filename : fileNames){
			if(filename.endsWith(".h"))
				headerFileNames.add(filename);
		}
	}

	@Ignore
	@Test
	public void testHeaderFiles(){
		FileWriter fileWriter = null;
		try{
			for(String headerFileName : headerFileNames){
				fileWriter = new FileWriter(projectPath + File.separator + "TestHeaderFile.c");
				fileWriter.write("#include<" + headerFileName + ">");
				fileWriter.close();
				getRawLLVRIRInstrs(projectPath, "TestHeaderFile.c");
				fileWriter = new FileWriter(projectPath + File.separator + "TestHeaderFile.c");
				fileWriter.write("");
				fileWriter.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
			assertTrue(false);
		}
		catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
		finally{
			try{
				if(fileWriter != null){
					fileWriter.close();
					fileWriter = null;
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@After
	public void tearDown(){
		//LLVMUtility.changeIncludePath(originalIncludePath, null);
	}
}
