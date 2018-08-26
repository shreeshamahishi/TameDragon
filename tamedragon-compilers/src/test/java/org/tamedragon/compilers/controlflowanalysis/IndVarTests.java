package org.tamedragon.compilers.controlflowanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import org.tamedragon.common.controlflowanalysis.IndVarExpression;
import org.tamedragon.common.controlflowanalysis.InductionVariable;
import org.tamedragon.common.controlflowanalysis.InductionVariableAnalysis;
import org.tamedragon.common.llvmir.instructions.BinaryOperator.BinaryOperatorID;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.compilers.LLVMBaseTest;

public class IndVarTests extends LLVMBaseTest{

	private static final String ROOT_PATH = "CSrc/ControlFlowAnalysis/IndVarIdentificationTests";

	@Test
	public void analyzeSimpleForLoop() throws Exception {
		String cSrcfilename =  "SimpleForLoop.c";

		InductionVariableAnalysis indVarAnalysis = runIndVarAnalyzer(cSrcfilename);
		HashMap<Value, InductionVariable> valueAndIndVars = indVarAnalysis.getValueAndIndVars();

	}

	@Test
	public void analyzeLoopNewVarsInsideLoop() throws Exception {
		String cSrcfilename =  "LoopNewVarsInsideLoop.c";

		InductionVariableAnalysis indVarAnalysis = runIndVarAnalyzer(cSrcfilename);
		HashMap<Value, InductionVariable> valueAndIndVars = indVarAnalysis.getValueAndIndVars();
		printIndVars(valueAndIndVars);

		assertTrue(valueAndIndVars.size() == 7);

		HashMap<String ,String> valueNamesAndExprs = 
			createValueAndIndVarExpressionStrings(valueAndIndVars);

		String expectedVarNamesAndExprs[] = new String[]{"%index.0", "%index.0", 
				"%4", "3*%index.0", "%5", "3*%index.0+%j", 
				"%6", "3*%index.0+%j-101", "%7", "12*%index.0+4*%j-404",
				"%8",  "12*%index.0+4*%j-404+%addr", "%15", "%index.0+1"};
		checkForIndVars(valueNamesAndExprs, expectedVarNamesAndExprs);

		HashMap<String, List<String>> actualIndVarMapStrs = 
			createIndVarMapDescs(indVarAnalysis.getInductionVarMap());
		printIndVarMap(actualIndVarMapStrs);
		HashMap<String, List<String>> expectedMap = new HashMap<String, List<String>>();
		List<String> depIndVars1 = new ArrayList<String>();
		depIndVars1.add("3*%index.0"); depIndVars1.add("%index.0+1");
		depIndVars1.add("3*%index.0+%j"); depIndVars1.add("3*%index.0+%j-101");
		depIndVars1.add("12*%index.0+4*%j-404"); depIndVars1.add("12*%index.0+4*%j-404+%addr");

		expectedMap.put("%index.0", depIndVars1);

		printIndVarMap(expectedMap);

		checkIndVarMap(expectedMap, actualIndVarMapStrs);

		HashMap<String, List<String>> expectedValueAndFactor = new HashMap<String, List<String>>();
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> 
		expectedValueAndDiffs = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();

		List<String> expectedFactor1 = new ArrayList<String>();
		expectedFactor1.add("3"); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs1 = new HashMap<List<String>, BinaryOperatorID>();
		expectedValueAndFactor.put("%4", expectedFactor1);
		expectedValueAndDiffs.put("%4", expectedExprs1);

		List<String> expectedFactor2 = new ArrayList<String>(); 
		expectedFactor2.add("3");
		HashMap<List<String>, BinaryOperatorID> expectedExprs2 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs2 = new ArrayList<String>(); expectedOprs2.add("%j");
		expectedExprs2.put(expectedOprs2, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%5", expectedFactor2);
		expectedValueAndDiffs.put("%5", expectedExprs2);

		List<String> expectedFactor3 = new ArrayList<String>(); 
		expectedFactor3.add("3");
		HashMap<List<String>, BinaryOperatorID> expectedExprs3 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs3 = new ArrayList<String>(); expectedOprs3.add("%j");
		List<String> expectedOprs4 = new ArrayList<String>(); expectedOprs4.add("101");
		expectedExprs3.put(expectedOprs3, BinaryOperatorID.ADD);
		expectedExprs3.put(expectedOprs4, BinaryOperatorID.SUB);
		expectedValueAndFactor.put("%6", expectedFactor3);
		expectedValueAndDiffs.put("%6", expectedExprs3);

		List<String> expectedFactor4 = new ArrayList<String>(); 
		expectedFactor4.add("12");
		HashMap<List<String>, BinaryOperatorID> expectedExprs4 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs5 = new ArrayList<String>(); expectedOprs5.add("4");expectedOprs5.add("%j");
		List<String> expectedOprs6 = new ArrayList<String>(); expectedOprs6.add("404");
		expectedExprs4.put(expectedOprs5, BinaryOperatorID.ADD);
		expectedExprs4.put(expectedOprs6, BinaryOperatorID.SUB);
		expectedValueAndFactor.put("%7", expectedFactor4);
		expectedValueAndDiffs.put("%7", expectedExprs4);
		
		List<String> expectedFactor5 = new ArrayList<String>(); 
		expectedFactor5.add("12");
		HashMap<List<String>, BinaryOperatorID> expectedExprs5 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs7 = new ArrayList<String>(); expectedOprs7.add("4");expectedOprs7.add("%j");
		List<String> expectedOprs8 = new ArrayList<String>(); expectedOprs8.add("404");
		List<String> expectedOprs9 = new ArrayList<String>(); expectedOprs9.add("%addr");
		expectedExprs5.put(expectedOprs7, BinaryOperatorID.ADD);
		expectedExprs5.put(expectedOprs8, BinaryOperatorID.SUB);
		expectedExprs5.put(expectedOprs9, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%8", expectedFactor5);
		expectedValueAndDiffs.put("%8", expectedExprs5);
		
		List<String> expectedFactor6 = new ArrayList<String>(); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs6 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs10 = new ArrayList<String>(); expectedOprs10.add("1");
		expectedExprs6.put(expectedOprs10, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%15", expectedFactor6);
		expectedValueAndDiffs.put("%15", expectedExprs6);

		checkCanonicalForm(valueAndIndVars, expectedValueAndFactor, expectedValueAndDiffs);
	}

	@Test
	public void analyzeLoopVarsDeclaredOutsideLoop() throws Exception {
		String cSrcfilename =  "LoopVarsDeclaredOutsideLoop.c";

		InductionVariableAnalysis indVarAnalysis = runIndVarAnalyzer(cSrcfilename);
		HashMap<Value, InductionVariable> valueAndIndVars = indVarAnalysis.getValueAndIndVars();
		printIndVars(valueAndIndVars);

		assertTrue(valueAndIndVars.size() == 7);

		HashMap<String ,String> valueNamesAndExprs = 
			createValueAndIndVarExpressionStrings(valueAndIndVars);

		String expectedVarNamesAndExprs[] = new String[]{"%index.0", "%index.0", 
				"%4", "100*%index.0", "%5", "100*%index.0+%j", 
				"%6", "100*%index.0+%j-101", "%7", "400*%index.0+4*%j-404",
				"%8",  "400*%index.0+4*%j-404+%addr", "%15", "%index.0+1"};
		checkForIndVars(valueNamesAndExprs, expectedVarNamesAndExprs);

		HashMap<String, List<String>> actualIndVarMapStrs = 
			createIndVarMapDescs(indVarAnalysis.getInductionVarMap());
		printIndVarMap(actualIndVarMapStrs);
		HashMap<String, List<String>> expectedMap = new HashMap<String, List<String>>();
		List<String> depIndVars1 = new ArrayList<String>();
		depIndVars1.add("100*%index.0"); depIndVars1.add("%index.0+1");
		depIndVars1.add("100*%index.0+%j"); depIndVars1.add("100*%index.0+%j-101");
		depIndVars1.add("400*%index.0+4*%j-404"); depIndVars1.add("400*%index.0+4*%j-404+%addr");

		expectedMap.put("%index.0", depIndVars1);

		printIndVarMap(expectedMap);

		checkIndVarMap(expectedMap, actualIndVarMapStrs);
		
		HashMap<String, List<String>> expectedValueAndFactor = new HashMap<String, List<String>>();
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> 
		expectedValueAndDiffs = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();

		List<String> expectedFactor1 = new ArrayList<String>();
		expectedFactor1.add("100"); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs1 = new HashMap<List<String>, BinaryOperatorID>();
		expectedValueAndFactor.put("%4", expectedFactor1);
		expectedValueAndDiffs.put("%4", expectedExprs1);

		List<String> expectedFactor2 = new ArrayList<String>(); 
		expectedFactor2.add("100");
		HashMap<List<String>, BinaryOperatorID> expectedExprs2 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs2 = new ArrayList<String>(); expectedOprs2.add("%j");
		expectedExprs2.put(expectedOprs2, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%5", expectedFactor2);
		expectedValueAndDiffs.put("%5", expectedExprs2);

		List<String> expectedFactor3 = new ArrayList<String>(); 
		expectedFactor3.add("100");
		HashMap<List<String>, BinaryOperatorID> expectedExprs3 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs3 = new ArrayList<String>(); expectedOprs3.add("%j");
		List<String> expectedOprs4 = new ArrayList<String>(); expectedOprs4.add("101");
		expectedExprs3.put(expectedOprs3, BinaryOperatorID.ADD);
		expectedExprs3.put(expectedOprs4, BinaryOperatorID.SUB);
		expectedValueAndFactor.put("%6", expectedFactor3);
		expectedValueAndDiffs.put("%6", expectedExprs3);

		List<String> expectedFactor4 = new ArrayList<String>(); 
		expectedFactor4.add("400");
		HashMap<List<String>, BinaryOperatorID> expectedExprs4 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs5 = new ArrayList<String>(); expectedOprs5.add("4");expectedOprs5.add("%j");
		List<String> expectedOprs6 = new ArrayList<String>(); expectedOprs6.add("404");
		expectedExprs4.put(expectedOprs5, BinaryOperatorID.ADD);
		expectedExprs4.put(expectedOprs6, BinaryOperatorID.SUB);
		expectedValueAndFactor.put("%7", expectedFactor4);
		expectedValueAndDiffs.put("%7", expectedExprs4);
		
		List<String> expectedFactor5 = new ArrayList<String>(); 
		expectedFactor5.add("400");
		HashMap<List<String>, BinaryOperatorID> expectedExprs5 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs7 = new ArrayList<String>(); expectedOprs7.add("4");expectedOprs7.add("%j");
		List<String> expectedOprs8 = new ArrayList<String>(); expectedOprs8.add("404");
		List<String> expectedOprs9 = new ArrayList<String>(); expectedOprs9.add("%addr");
		expectedExprs5.put(expectedOprs7, BinaryOperatorID.ADD);
		expectedExprs5.put(expectedOprs8, BinaryOperatorID.SUB);
		expectedExprs5.put(expectedOprs9, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%8", expectedFactor5);
		expectedValueAndDiffs.put("%8", expectedExprs5);
		
		List<String> expectedFactor6 = new ArrayList<String>(); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs6 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs10 = new ArrayList<String>(); expectedOprs10.add("1");
		expectedExprs6.put(expectedOprs10, BinaryOperatorID.ADD);
		expectedValueAndFactor.put("%15", expectedFactor6);
		expectedValueAndDiffs.put("%15", expectedExprs6);

		checkCanonicalForm(valueAndIndVars, expectedValueAndFactor, expectedValueAndDiffs);
	}

	@Test
	public void analyzeLoopWithMultipleIndVars() throws Exception {
		String cSrcfilename =  "LoopsWithMultipleBasicIndVars.c";

		InductionVariableAnalysis indVarAnalysis = runIndVarAnalyzer(cSrcfilename);
		HashMap<Value, InductionVariable> valueAndIndVars = indVarAnalysis.getValueAndIndVars();

		String expectedVarNamesAndExprs[] = new String[]{"%i.0", "%i.0", 
				"%4", "%i.0*42", "%5", "%j-%i.0*42", 
				"%6", "30*%j-%i.0*1260", "%dincr.0", "%dincr.0",
				"%7",  "%dincr.0+1.200000e+01", "%dotherincr.0", "%dotherincr.0",
				"%8",  "%dotherincr.0+4.500000e+00", "%9", "%dincr.0*6.700000e+00+8.040000e+01",
				"%16", "%i.0+3"};
		assertTrue(valueAndIndVars.size() == 10);

		HashMap<String ,String> valueNamesAndExprs = 
			createValueAndIndVarExpressionStrings(valueAndIndVars);
		checkForIndVars(valueNamesAndExprs, expectedVarNamesAndExprs);

		HashMap<InductionVariable, List<InductionVariable>> basicAndDependentIndvars
		= indVarAnalysis.getInductionVarMap();
		HashMap<String ,List<String>> actualIndVarMapStrs = createIndVarMapDescs(basicAndDependentIndvars);
		HashMap<String, List<String>> expectedMap = new HashMap<String, List<String>>();
		List<String> depIndVars1 = new ArrayList<String>();
		depIndVars1.add("%i.0*42"); depIndVars1.add("%i.0+3"); depIndVars1.add("%j-%i.0*42"); 
		depIndVars1.add("30*%j-%i.0*1260");
		expectedMap.put("%i.0", depIndVars1);

		List<String> depIndVars2 = new ArrayList<String>();
		depIndVars2.add("%dincr.0+1.200000e+01"); depIndVars2.add("%dincr.0*6.700000e+00+8.040000e+01"); 
		expectedMap.put("%dincr.0", depIndVars2);

		List<String> depIndVars3 = new ArrayList<String>();
		depIndVars3.add("%dotherincr.0+4.500000e+00"); depIndVars3.add("%dotherincr.0+4.500000e+00"); 
		expectedMap.put("%dotherincr.0", depIndVars3);

		checkIndVarMap(expectedMap, actualIndVarMapStrs);
		
		HashMap<String, List<String>> expectedValueAndFactor1 = new HashMap<String, List<String>>();
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> 
		expectedValueAndDiffs1 = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();
		
		List<String> expectedFactor1 = new ArrayList<String>();
		expectedFactor1.add("42"); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs1 = new HashMap<List<String>, BinaryOperatorID>();
		expectedValueAndFactor1.put("%4", expectedFactor1);
		expectedValueAndDiffs1.put("%4", expectedExprs1);
		
		List<String> expectedFactor2 = new ArrayList<String>();
		expectedFactor2.add("-42"); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs2 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs1 = new ArrayList<String>(); expectedOprs1.add("%j");
		expectedExprs2.put(expectedOprs1, BinaryOperatorID.ADD);
		expectedValueAndFactor1.put("%5", expectedFactor2);
		expectedValueAndDiffs1.put("%5", expectedExprs2);

		List<String> expectedFactor3 = new ArrayList<String>();
		expectedFactor3.add("-1260"); 
		HashMap<List<String>, BinaryOperatorID> expectedExprs3 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs2 = new ArrayList<String>(); expectedOprs2.add("30");expectedOprs2.add("%j");
		expectedExprs3.put(expectedOprs2, BinaryOperatorID.ADD);
		expectedValueAndFactor1.put("%6", expectedFactor3);
		expectedValueAndDiffs1.put("%6", expectedExprs3);
		
		List<String> expectedFactorE = new ArrayList<String>();
		HashMap<List<String>, BinaryOperatorID> expectedExprsE = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprsE = new ArrayList<String>(); expectedOprsE.add("3");
		expectedExprsE.put(expectedOprsE, BinaryOperatorID.ADD);
		expectedValueAndFactor1.put("%16", expectedFactorE);
		expectedValueAndDiffs1.put("%16", expectedExprsE);
		
		
		checkCanonicalForm(valueAndIndVars, expectedValueAndFactor1, expectedValueAndDiffs1);
		
		HashMap<String, List<String>> expectedValueAndFactor2 = new HashMap<String, List<String>>();
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> 
		expectedValueAndDiffs2 = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();
		
		List<String> expectedFactor4 = new ArrayList<String>();
		HashMap<List<String>, BinaryOperatorID> expectedExprs4 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs3 = new ArrayList<String>(); expectedOprs3.add("1.200000e+01");
		expectedExprs4.put(expectedOprs3, BinaryOperatorID.FADD);
		expectedValueAndFactor2.put("%7", expectedFactor4);
		expectedValueAndDiffs2.put("%7", expectedExprs4);
		
		checkCanonicalForm(valueAndIndVars, expectedValueAndFactor2, expectedValueAndDiffs2);
		
		HashMap<String, List<String>> expectedValueAndFactor3 = new HashMap<String, List<String>>();
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> 
		expectedValueAndDiffs3 = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();
		
		List<String> expectedFactor5 = new ArrayList<String>();
		HashMap<List<String>, BinaryOperatorID> expectedExprs5 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs4 = new ArrayList<String>(); expectedOprs4.add("4.500000e+00");
		expectedExprs5.put(expectedOprs4, BinaryOperatorID.FADD);
		expectedValueAndFactor3.put("%8", expectedFactor5);
		expectedValueAndDiffs3.put("%8", expectedExprs5);
		
		List<String> expectedFactor6 = new ArrayList<String>();
		expectedFactor6.add("6.700000e+00");
		HashMap<List<String>, BinaryOperatorID> expectedExprs6 = new HashMap<List<String>, BinaryOperatorID>();
		List<String> expectedOprs5 = new ArrayList<String>(); expectedOprs5.add("8.040000e+01");
		expectedExprs6.put(expectedOprs5, BinaryOperatorID.FADD);
		expectedValueAndFactor3.put("%9", expectedFactor6);
		expectedValueAndDiffs3.put("%9", expectedExprs6);
		
		checkCanonicalForm(valueAndIndVars, expectedValueAndFactor3, expectedValueAndDiffs3);
		
	}

	@Test
	public void analyzeNestedLoopWithSingleIndVar() throws Exception {
		/*String cSrcfilename =  "NestedLoopsWithSingleBasicIndVars.c";

		InductionVariableAnalysis indVarAnalysis = runIndVarAnalyzer(cSrcfilename);
		HashMap<Value, InductionVariable> valueAndIndVars = indVarAnalysis.getValueAndIndVars();

		assertTrue(valueAndIndVars.size() == 7);

		HashMap<String ,String> valueNamesAndExprs = 
			createValueAndIndVarExpressionStrings(valueAndIndVars);

		String expectedVarNamesAndExprs[] = new String[]{"%index.0", "%index.0", 
				"%4", "100*%index.0", "%5", "100*%index.0+%j", 
				"%6", "100*%index.0+%j-101", "%7", "400*%index.0+4*%j-404",
				"%8",  "400*%index.0+4*%j-404+%addr", "%15", "%index.0+1"};
		checkForIndVars(valueNamesAndExprs, expectedVarNamesAndExprs);
		 */
	}


	// Utilities and specific test cases.

	private HashMap<String ,String> createValueAndIndVarExpressionStrings(
			HashMap<Value, InductionVariable> valueAndIndVars){
		HashMap<String ,String> valueNamesAndExprs = new HashMap<String, String>();
		Set<Value> values = valueAndIndVars.keySet();
		Iterator<Value> valueIterator = values.iterator();
		while(valueIterator.hasNext()){
			Value value = valueIterator.next();
			String valName = emitter.getValidName(value);
			InductionVariable indVar = valueAndIndVars.get(value);
			IndVarExpression expr = indVar.getExpression();
			valueNamesAndExprs.put(valName, expr.toString());

		}

		return valueNamesAndExprs;
	}

	private HashMap<String, List<String>> createActualFactorDesc(HashMap<Value, InductionVariable> 
					actualValAndIndVars){
		HashMap<String, List<String>> factorDesc = new HashMap<String, List<String>>();

		System.out.println("********** START CANONICAL FACTOR MAP");
		
		Set<Value> values = actualValAndIndVars.keySet();
		Iterator<Value> iter = values.iterator();
		while(iter.hasNext()){
			Value val = iter.next();
			String valName = emitter.getValidName(val);
			System.out.print("For value: " + valName + ": ");
			InductionVariable indVar = actualValAndIndVars.get(val);
			List<Value> valsInFactor = indVar.getFactorInCanonicalForm();
			List<String> fcDesc = new ArrayList<String>();
			for(Value valF : valsInFactor){
				String valFDesc = emitter.getValidName(valF);
				System.out.print(valFDesc +  ", ");
				fcDesc.add(valFDesc);
			}
			factorDesc.put(valName, fcDesc);
			System.out.println("");
		}

		System.out.println("********** END CANONICAL FACTOR MAP");
		return factorDesc;

	}

	private HashMap<String, HashMap<List<String>, BinaryOperatorID>> createActualDiffsDesc(HashMap<Value, InductionVariable> 
	actualValAndIndVars){
		HashMap<String, HashMap<List<String>, BinaryOperatorID>> diffDesc = 
			new HashMap<String, HashMap<List<String>, BinaryOperatorID>>();

		Set<Value> values = actualValAndIndVars.keySet();
		Iterator<Value> iter = values.iterator();
		while(iter.hasNext()){
			Value val = iter.next();
			String valName = emitter.getValidName(val);
			InductionVariable indVar = actualValAndIndVars.get(val);
			HashMap<List<Value>, BinaryOperatorID> valsInDiff = indVar.getDifferenceInCanonicalForm();
			
			HashMap<List<String>, BinaryOperatorID> diffDescForIndVar = 
				new HashMap<List<String>, BinaryOperatorID>();
	
			Set<List<Value>> subExprsSet = valsInDiff.keySet();
			Iterator<List<Value>> iter1 = subExprsSet.iterator();
			
			while(iter1.hasNext()){
				List<Value> subExpr = iter1.next();
				BinaryOperatorID binOpId = valsInDiff.get(subExpr);
				List<String> valNames = new ArrayList<String>();
				for(Value v : subExpr){
					valNames.add(emitter.getValidName(v));
				}
				
				diffDescForIndVar.put(valNames, binOpId);
				
			}
			
			diffDesc.put(valName, diffDescForIndVar);
		}

		return diffDesc;

	}

	private void checkForIndVars(HashMap<String, String> actualValAndIndVarNames,
			String... expectedValueNamesAndExprs) {

		List<String> allParams = Arrays.asList(expectedValueNamesAndExprs);

		int count = 0;
		for(String param : allParams){
			String expectedExpression = "";
			String valueName = "";
			if(count == 0 || count %2 == 0){
				valueName = param;
				expectedExpression = allParams.get(count + 1);
				String actualExpression = actualValAndIndVarNames.get(valueName);
				assertNotNull(actualExpression);
				assertEquals(expectedExpression, actualExpression);
			}
			count++;
		}
	}


	private void checkIndVarMap(HashMap<String, List<String>> expectedMap,
			HashMap<String, List<String>> actualIndVarMapStrs) {

		Set<String> expectedBasicIndVars = expectedMap.keySet();
		Set<String> actualBasicIndVars = actualIndVarMapStrs.keySet();

		assertTrue(expectedBasicIndVars.size() == actualBasicIndVars.size());

		Iterator<String> expectedBIDescs = expectedBasicIndVars.iterator();
		while(expectedBIDescs.hasNext()){
			String expBI = expectedBIDescs.next();
			List<String> expDIs = expectedMap.get(expBI);
			assertTrue(expDIs.size() > 0);
			List<String> actualDIs = actualIndVarMapStrs.get(expBI);
			assertTrue(actualDIs.size() > 0);

			assertTrue(expDIs.containsAll(actualDIs) && actualDIs.containsAll(expDIs));

		}
	}

	private void checkCanonicalForm(
			HashMap<Value, InductionVariable> valueAndIndVars,
			HashMap<String, List<String>> expectedValueAndFactor,
			HashMap<String, HashMap<List<String>, BinaryOperatorID>> expectedValueAndDiffs) {

		HashMap<String, List<String>> actualValueAndFactorDescs = createActualFactorDesc(valueAndIndVars);
		HashMap<String, HashMap<List<String>, BinaryOperatorID>>  actualValueAndDiffDescs = 
					createActualDiffsDesc(valueAndIndVars);
		
		// Check the factor first
		Set<String> expectedValDesc1 = expectedValueAndFactor.keySet();
		Iterator<String> iter = expectedValDesc1.iterator();
		while(iter.hasNext()){
			String valName = iter.next();
			if(valName.equals("%15")){
				System.out.println("Matching for valName = " + valName);
			}
			
			List<String> expectedValNmsInFactor = expectedValueAndFactor.get(valName);
			
			List<String> actualValNmsInFactor = actualValueAndFactorDescs.get(valName);
			assertNotNull(actualValNmsInFactor);
			assertTrue(expectedValNmsInFactor.size() == actualValNmsInFactor.size());
			assertTrue(expectedValNmsInFactor.containsAll(actualValNmsInFactor));
			
		}
		
		// Check the difference
		Set<String> expectedValDesc2 =  expectedValueAndDiffs.keySet();
		Iterator<String> iter2 = expectedValDesc2.iterator();
		while(iter2.hasNext()){
			String valName = iter2.next();
			HashMap<List<String>, BinaryOperatorID> expectedValNmsInDiff = 
				expectedValueAndDiffs.get(valName);
			
			HashMap<List<String>, BinaryOperatorID> actualValNmsInDiff = 
				actualValueAndDiffDescs.get(valName);
			
			Iterator<List<String>> iter3 = expectedValNmsInDiff.keySet().iterator();
			
			int matchCount = 0;
			outer : while(iter3.hasNext()){
				List<String> expValsInDiff = iter3.next();
				BinaryOperatorID binOpExpected = expectedValNmsInDiff.get(expValsInDiff);
				
				Iterator<List<String>> iter4 = actualValNmsInDiff.keySet().iterator();
				while(iter4.hasNext()){
					List<String> actualValsInDiff = iter4.next();
					if(expValsInDiff.containsAll(actualValsInDiff) 
							&& actualValsInDiff.containsAll(expValsInDiff)){
						BinaryOperatorID binOpActual = actualValNmsInDiff.get(actualValsInDiff);
						assertTrue(binOpActual == binOpExpected);
						matchCount++;
						continue outer;
					}
				}
			}
			assertTrue(matchCount == expectedValNmsInDiff.size());
		}
	}

	private HashMap<String, List<String>> createIndVarMapDescs(
			HashMap<InductionVariable, List<InductionVariable>> basicAndDependentIndvars) {
		HashMap<String, List<String>> basicAndDependIndVarStrs = new HashMap<String, List<String>>();

		Set<InductionVariable> basicIndVars = basicAndDependentIndvars.keySet();

		Iterator<InductionVariable> iter = basicIndVars.iterator();
		while(iter.hasNext()){
			InductionVariable bIndVar = iter.next();
			String biv = emitter.getValidName(bIndVar.getDefiningInstruction());
			List<String> divs = new ArrayList<String>();
			List<InductionVariable> dIndVars = basicAndDependentIndvars.get(bIndVar);
			for(InductionVariable div : dIndVars){
				divs.add(div.getExpression().toString());
			}
			basicAndDependIndVarStrs.put(biv, divs);
		}

		return basicAndDependIndVarStrs;
	}

	private InductionVariableAnalysis runIndVarAnalyzer(String cSrcfilename) throws Exception  {

		getRawLLVRIRInstrs(ROOT_PATH, cSrcfilename);

		Module module = getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(0);

		// Mem2reg
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		System.out.println("AFTER MEM2REG: ");
		emitter.reset();
		List<String> instrsAfterOpt = emitter.emitInstructions(function);
		printAsm(instrsAfterOpt);

		InductionVariableAnalysis indVarAnalysis = new InductionVariableAnalysis(function);
		return indVarAnalysis;
	}

	private void printIndVars(HashMap<Value, InductionVariable> valueAndIndVars) {

		System.out.println("*****************INDUCTION VARIABLES");

		System.out.println("Indvars(" + valueAndIndVars.size() + "):");
		Set<Value> valueSet = valueAndIndVars.keySet(); 
		Iterator<Value> iter = valueSet.iterator();

		while(iter.hasNext()){
			InductionVariable indVar = valueAndIndVars.get(iter.next());
			System.out.println(indVar.toString());
			System.out.println("	Expression: " + indVar.getExpression().toString());
			System.out.println("");
		}

		System.out.println("*****************END INDUCTION VARIABLES");
	}

	private void printIndVarMap(HashMap<String, List<String>> indVarMapStrs) {

		System.out.println("*****************INDUCTION VARIABLE MAP");
		System.out.println("Indvars(" + indVarMapStrs.size() + "):");
		Set<String> bIVs = indVarMapStrs.keySet(); 
		Iterator<String> iter = bIVs.iterator();

		while(iter.hasNext()){
			String biv = iter.next();
			List<String> divsForBi = indVarMapStrs.get(biv);
			System.out.println("Basic Induction variable: (" + divsForBi.size() + ") : ");
			for(String div : divsForBi){
				System.out.print(div + ", ");
			}
			System.out.println("");
		}

		System.out.println("*****************INDUCTION VARIABLE MAP");
	}


}
