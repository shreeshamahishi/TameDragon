package org.tamedragon.compilers.clang.tests.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.compilers.clang.preprocessor.Conditional;
import org.tamedragon.compilers.clang.preprocessor.ConstExpr;
import org.tamedragon.compilers.clang.preprocessor.Definition;
import org.tamedragon.compilers.clang.preprocessor.Elif;
import org.tamedragon.compilers.clang.preprocessor.ElifPart;
import org.tamedragon.compilers.clang.preprocessor.ElsePart;
import org.tamedragon.compilers.clang.preprocessor.IfLine;
import org.tamedragon.compilers.clang.preprocessor.IncludeDirective;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorUnit;
import org.tamedragon.compilers.clang.preprocessor.ProgramCode;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.tests.TestInitializer;

//@Ignore
public class MultipleDirectivesTest1 extends TestInitializer {

	private String sourceFilePath;
	private PreprocessorSegments preprocessorSegments;
	
	
	@Before
	public void setUp(){		
		sourceFilePath ="CSrc/Preprocessor/MultipleDirectivesTest1.c"; 
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(sourceFilePath).getFile());
		sourceFilePath = file.getAbsolutePath();
		
		PreprocessorMain ppMain = new PreprocessorMain(sourceFilePath);
		InputStream is = ppMain.replaceTrigraphSequencesAndSpliceLines(sourceFilePath);
		
		preprocessorSegments = ppMain.getPreprocessorTranslationByLLParsing(is);
		assertNotNull(preprocessorSegments);			
	}
	
	@Test
	public void test1() {      
		
		Environments environments = Environments.getInstance();
		environments.reset();	
		
		List<PreprocessorUnit> preprocessorUnits = preprocessorSegments.getUnits();
				
		//assertTrue(preprocessorUnits.size() == 21);
		PreprocessorUnit pu1 = preprocessorUnits.get(0);
		PreprocessorUnit pu2 = preprocessorUnits.get(1);
		PreprocessorUnit pu3 = preprocessorUnits.get(2);
		PreprocessorUnit pu4 = preprocessorUnits.get(3);
		PreprocessorUnit pu5 = preprocessorUnits.get(4);
		PreprocessorUnit pu6 = preprocessorUnits.get(5);
		PreprocessorUnit pu7 = preprocessorUnits.get(6);
		PreprocessorUnit pu8 = preprocessorUnits.get(7);
		PreprocessorUnit pu9 = preprocessorUnits.get(8);
		PreprocessorUnit pu10 = preprocessorUnits.get(9);
		PreprocessorUnit pu11 = preprocessorUnits.get(10);
		PreprocessorUnit pu12 = preprocessorUnits.get(11);
		PreprocessorUnit pu13 = preprocessorUnits.get(12);
		PreprocessorUnit pu14 = preprocessorUnits.get(13);
		PreprocessorUnit pu15 = preprocessorUnits.get(14);
		PreprocessorUnit pu16 = preprocessorUnits.get(15);
		PreprocessorUnit pu17 = preprocessorUnits.get(16);
		PreprocessorUnit pu18 = preprocessorUnits.get(17);
		PreprocessorUnit pu19 = preprocessorUnits.get(18);
		PreprocessorUnit pu20 = preprocessorUnits.get(19);
		PreprocessorUnit pu21 = preprocessorUnits.get(20);
		PreprocessorUnit pu22 = preprocessorUnits.get(21);
		PreprocessorUnit pu23 = preprocessorUnits.get(22);
		PreprocessorUnit pu24 = preprocessorUnits.get(23);
		PreprocessorUnit pu25 = preprocessorUnits.get(24);
		PreprocessorUnit pu26 = preprocessorUnits.get(25);
		PreprocessorUnit pu27 = preprocessorUnits.get(26);
		PreprocessorUnit pu28 = preprocessorUnits.get(27);
		PreprocessorUnit pu29 = preprocessorUnits.get(28);
		PreprocessorUnit pu30 = preprocessorUnits.get(29);
		PreprocessorUnit pu31 = preprocessorUnits.get(30);
		PreprocessorUnit pu32 = preprocessorUnits.get(31);
		
		assertTrue(pu1.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu2.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("stdio.h".equals(pu2.toString()));
		assertTrue(pu3.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("cmath.h".equals(pu3.toString()));
		assertTrue(pu4.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("ctype.h".equals(pu4.toString()));
		
		assertTrue(pu5.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu6.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		Definition def1 = (Definition) pu6;
		assertTrue(def1.getDefinitionType() == Definition.ID_DEFINITION);
		assertTrue("MAX_COUNT".equals(def1.getId()));
		String rts1 = def1.getReplacementTokenSequence().toString();
		System.out.println("RTS1 = " + rts1);
		assertTrue("1000".equals(rts1));
		
		assertTrue(pu7.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu8.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("conio.h".equals(pu8.toString()));
		
		assertTrue(pu9.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu10.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		Definition def2 = (Definition)pu10;
		assertTrue("SYSTEM".equals(def2.getId()));
		String rts2 = def2.getReplacementTokenSequence().toString();
		assertTrue("MSDOS".equals(rts2));
		
		assertTrue(pu11.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu12.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		Definition def3 = (Definition)pu12;
		assertTrue("MIN_COUNT".equals(def3.getId()));
		String rts3 = def3.getReplacementTokenSequence().toString();
		assertTrue("300".equals(rts3));
		
		assertTrue(pu13.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu14.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("sample1.h".equals(pu14.toString()));
		
		assertTrue(pu15.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu16.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		Conditional conditional1 = (Conditional) pu16;
		assertTrue(conditional1.getConditionalType() == Conditional.IFDEF);
		IfLine ifDef1 = conditional1.getIfLine();
		assertTrue(ifDef1.getIfLineType() == IfLine.IFDEF);
		String id1 = ifDef1.getIdentifier();
		assertTrue("MAX_COUNT".equals(id1));
		ConstExpr constExpr1 = ifDef1.getConstExpr();
		assertNull(constExpr1);
		List<PreprocessorUnit> ifConditionPUs = conditional1.getPreprocessorUnits();
		assertTrue(ifConditionPUs.size() == 2);
		PreprocessorUnit ifCondPu1 = ifConditionPUs.get(0);
		assertTrue(ifCondPu1.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		PreprocessorUnit ifCondPu2 = ifConditionPUs.get(1);
		assertTrue(ifCondPu2.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		IncludeDirective ifCondPu2Inc1 = (IncludeDirective) ifCondPu2;
		assertTrue("floh.h".equals(ifCondPu2Inc1.getFileNameLib().getLibraryFileName()));
		List<ElifPart> elifParts1 = conditional1.getElifParts();
		assertTrue(elifParts1.size() == 2);
		ElifPart ep1 = elifParts1.get(0);
		Elif elif1 = ep1.getElif();
		//String elifConstExpr1 = elif1.getConstExpr().getTokenSequence().toString();
		//System.out.println(elifConstExpr1);
		//assertTrue("SYSTEM == SYSV ".equals(elifConstExpr1));
		List<PreprocessorUnit> ep1pus1 = ep1.getPreprocessorUnits();
		assertTrue(ep1pus1.size() == 2);
		assertTrue(ep1pus1.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		IncludeDirective inclDir1 = (IncludeDirective) ep1pus1.get(1);
		assertTrue(inclDir1.getIncludeType() == IncludeDirective.LIB);
		assertTrue("half.h".equals(inclDir1.getFileNameLib().toString()));
		ElifPart ep2 = elifParts1.get(1);
		List<PreprocessorUnit> ep2pus1 = ep2.getPreprocessorUnits();
		assertTrue(ep2pus1.size() == 2);
		assertTrue(ep2pus1.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		IncludeDirective inclDir2 = (IncludeDirective) ep2pus1.get(1);
		assertTrue(inclDir2.getIncludeType() == IncludeDirective.LIB);
		assertTrue("half1.h".equals(inclDir2.getFileNameLib().toString()));
		Elif elif2 = ep2.getElif();
		//assertTrue("SYSTEM == MSDOS".equals(elif2.getConstExpr().getTokenSequence().toString()));
		ElsePart elsePart1 = conditional1.getElsePart();
		List<PreprocessorUnit> elsePart1pus = elsePart1.getPreprocessorUnits();
		assertTrue(elsePart1pus.size() == 2);
		assertTrue(elsePart1pus.get(0).getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		ProgramCode elsePart1Pc = (ProgramCode) elsePart1pus.get(0);
		assertTrue(elsePart1pus.get(1).getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		Definition def4 = (Definition) elsePart1pus.get(1);
		assertTrue("FACTORIAL".equals(def4.getId()));
		String rts4 = def4.getReplacementTokenSequence().toString();
		assertTrue("f *f * f".equals(rts4));
		
		assertTrue(pu17.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		assertTrue(pu18.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		Conditional conditional2 = (Conditional) pu18;
		assertTrue(conditional2.getConditionalType() == Conditional.IFDEF);
		IfLine ifLine2 = conditional2.getIfLine();
		ConstExpr ce1 = ifLine2.getConstExpr();
		//System.out.println("ce1 ID = " + ce1.getIdentifier());
		//assertTrue("SYSTEM".equals(ce1.getIdentifier()));
		//System.out.println("ce1  type = " + ce1.getConstExprType());
		//assertTrue(ce1.getConstExprType() == ConstExpr.NOT_DEFINED);

		
		
		assertTrue(pu11.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu12.getPreprocessorUnitType() == PreprocessorUnit.DEFINITION);
		//assertTrue("tyranny.h".equals(pu12.toString()));
		assertTrue(pu13.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		//assertTrue("turn.h".equals(pu13.toString()));
		assertTrue(pu14.getPreprocessorUnitType() == PreprocessorUnit.INCLUDE_DIRECTIVE);
		assertTrue("sample1.h".equals(pu14.toString()));
		assertTrue(pu15.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu16.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu17.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		assertTrue(pu18.getPreprocessorUnitType() == PreprocessorUnit.CONDITIONAL);
		assertTrue(pu19.getPreprocessorUnitType() == PreprocessorUnit.PROGRAM_CODE);
		
		System.out.println("Program code:");		
		System.out.println(pu19.toString());
		
	}	
}
