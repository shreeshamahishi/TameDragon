package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.tests.TestInitializer;

public class ConstantExprTest1 extends TestInitializer {
	
	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/ConstExprTest1.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		sourceFilePath = file.getAbsolutePath();
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);		
	}
	
	@Test
	public void test() {   
		
        List<PreprocessorUnit> preprocessorUnits = preprocessorSegments.getUnits();
		
		System.out.println("Size = " + preprocessorUnits.size());
		
		PreprocessorUnit pu0 = preprocessorUnits.get(0);
		PreprocessorUnit pu1 = preprocessorUnits.get(1);
		PreprocessorUnit pu2 = preprocessorUnits.get(2);
		PreprocessorUnit pu3 = preprocessorUnits.get(3);
		PreprocessorUnit pu4 = preprocessorUnits.get(4);
		PreprocessorUnit pu5 = preprocessorUnits.get(5);
		PreprocessorUnit pu6 = preprocessorUnits.get(6);
		PreprocessorUnit pu7 = preprocessorUnits.get(7);
		PreprocessorUnit pu8 = preprocessorUnits.get(8);
		PreprocessorUnit pu9 = preprocessorUnits.get(9);
		PreprocessorUnit pu10 = preprocessorUnits.get(10);
		PreprocessorUnit pu11 = preprocessorUnits.get(11);
		PreprocessorUnit pu12 = preprocessorUnits.get(12);
		PreprocessorUnit pu13 = preprocessorUnits.get(13);
		PreprocessorUnit pu14 = preprocessorUnits.get(14);
		PreprocessorUnit pu15 = preprocessorUnits.get(15);
		PreprocessorUnit pu16 = preprocessorUnits.get(16);
		PreprocessorUnit pu17 = preprocessorUnits.get(17);
		PreprocessorUnit pu18 = preprocessorUnits.get(18);
		PreprocessorUnit pu19 = preprocessorUnits.get(19);
		PreprocessorUnit pu20 = preprocessorUnits.get(20);
		PreprocessorUnit pu21 = preprocessorUnits.get(21);
		PreprocessorUnit pu22 = preprocessorUnits.get(22);
		PreprocessorUnit pu23 = preprocessorUnits.get(23);
		PreprocessorUnit pu24 = preprocessorUnits.get(24);
		PreprocessorUnit pu25 = preprocessorUnits.get(25);
		PreprocessorUnit pu26 = preprocessorUnits.get(26);
		PreprocessorUnit pu27 = preprocessorUnits.get(27);
		PreprocessorUnit pu28 = preprocessorUnits.get(28);
		PreprocessorUnit pu29 = preprocessorUnits.get(29);
		PreprocessorUnit pu30 = preprocessorUnits.get(30);
		PreprocessorUnit pu31 = preprocessorUnits.get(31);
		PreprocessorUnit pu32 = preprocessorUnits.get(32);
		//PreprocessorUnit pu16 = preprocessorUnits.get(16);
		
//		
//		System.out.println("The pu0 value is " + pu0.getPreprocessorUnitType());
//		System.out.println("The pu1 value is " + pu1.getPreprocessorUnitType());
//		System.out.println("The pu2 value is " + pu2.getPreprocessorUnitType());
//		System.out.println("The pu3 value is " + pu3.getPreprocessorUnitType());		
//		System.out.println("The pu4 value is " + pu4.getPreprocessorUnitType());
//		System.out.println("The pu5 value is " + pu5.getPreprocessorUnitType());
//		System.out.println("The pu6 value is " + pu6.getPreprocessorUnitType());
//		System.out.println("The pu7 value is " + pu7.getPreprocessorUnitType());
//		System.out.println("The pu8 value is " + pu8.getPreprocessorUnitType());
//		System.out.println("The pu9 value is " + pu9.getPreprocessorUnitType());
//		System.out.println("The pu10 value is " + pu10.getPreprocessorUnitType());
//		System.out.println("The pu11 value is " + pu11.getPreprocessorUnitType());
//		System.out.println("The pu12 value is " + pu12.getPreprocessorUnitType());
//		System.out.println("The pu13 value is " + pu13.getPreprocessorUnitType());
//		System.out.println("The pu14 value is " + pu14.getPreprocessorUnitType());
//		System.out.println("The pu15 value is " + pu15.getPreprocessorUnitType());
//		System.out.println("The pu16 value is " + pu16.getPreprocessorUnitType());
//		//pu1.getPreprocessorUnitType().
//		System.out.println("The pu17 value is " + pu17.getPreprocessorUnitType());
//		System.out.println("The pu18 value is " + pu18.getPreprocessorUnitType());
//		System.out.println("The pu19 value is " + pu19.getPreprocessorUnitType());
//		System.out.println("The pu20 value is " + pu20.getPreprocessorUnitType());
//		System.out.println("The pu21 value is " + pu21.getPreprocessorUnitType());
//		System.out.println("The pu22 value is " + pu22.getPreprocessorUnitType());
//		System.out.println("The pu23 value is " + pu23.getPreprocessorUnitType());
//		System.out.println("The pu24 value is " + pu24.getPreprocessorUnitType());
//		System.out.println("The pu25 value is " + pu25.getPreprocessorUnitType());
//		System.out.println("The pu26 value is " + pu26.getPreprocessorUnitType());
//		System.out.println("The pu27 value is " + pu27.getPreprocessorUnitType());
//		System.out.println("The pu28 value is " + pu28.getPreprocessorUnitType());
//		System.out.println("The pu29 value is " + pu29.getPreprocessorUnitType());
//		System.out.println("The pu30 value is " + pu30.getPreprocessorUnitType());
//		System.out.println("The pu31 value is " + pu31.getPreprocessorUnitType());
//		System.out.println("The pu32 value is " + pu32.getPreprocessorUnitType());
		
		assertTrue(pu0.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		assertTrue(pu1.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		assertTrue(pu2.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu3.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue(pu4.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu5.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu6.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu7.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu8.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu9.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu10.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu11.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu12.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu13.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu14.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu15.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu16.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu17.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu18.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu19.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu20.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu21.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu22.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu23.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu24.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu25.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu26.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu27.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu28.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu29.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu30.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu31.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu32.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
	
	}

}
