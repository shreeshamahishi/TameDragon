package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import org.tamedragon.compilers.LLVMBaseTest;

public class IRTreeGenTestMacros extends LLVMBaseTest{
	
	private String projectPath = "CSrc/Preprocessor";
	
	@Test
	public void testSimpleMacroDefination(){
		try {
			getRawLLVRIRInstrs(projectPath, "SimpleMacro.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFunctionLikeMacro(){
		try {
			getRawLLVRIRInstrs(projectPath, "FunctionLikeMacro.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCondition(){
		try {
			getRawLLVRIRInstrs(projectPath, "ConditionalTest11.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCondition2(){
		try {
			getRawLLVRIRInstrs(projectPath, "ConditionalTest12.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDuplicateOfSideEffects(){
		try {
			getRawLLVRIRInstrs(projectPath, "Duplication_Of_Side_Effects.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCocatenation(){
		try {
			getRawLLVRIRInstrs(projectPath, "Concatenation.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCocatenation2(){
		try {
			getRawLLVRIRInstrs(projectPath, "Concatenation2.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFuncLikeMacro2(){
		try {
			getRawLLVRIRInstrs(projectPath, "FuncLikeMacro2.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testErrorCheck(){
		try {
			getRawLLVRIRInstrs(projectPath, "ErrorCheck.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testFeaturesDotH(){
		try { 
			getRawLLVRIRInstrs(projectPath, "TestFeaturesDotH.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testNestedIf(){
		try {
			getRawLLVRIRInstrs(projectPath, "ConditionalTest13.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMacroWithDoubleQuotes(){
		try {
			getRawLLVRIRInstrs(projectPath, "MacroWithinDoubleQuotes.c");
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
