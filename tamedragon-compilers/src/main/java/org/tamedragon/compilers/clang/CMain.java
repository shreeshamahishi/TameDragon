package org.tamedragon.compilers.clang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.tamedragon.assemblyabstractions.ActivationFrame;
import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.common.CFGLinearizationDFS;
//import org.tamedragon.common.ControlFlowGraph;
import org.tamedragon.common.EnvironmentConstants;
import org.tamedragon.common.InterferenceGraph;
import org.tamedragon.common.Label;
import org.tamedragon.common.RegisterAllocator;
import org.tamedragon.common.TargetDataType;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.Utilities;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.types.AbstractType;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Temp;
//import org.tamedragon.common.optimization.OptimizationContext;
//import org.tamedragon.common.optimization.SSAOptimizationUtils;
import org.tamedragon.common.utils.ConversionUtility;
import org.tamedragon.compilers.canon.Linearizer;
import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
import org.tamedragon.compilers.clang.semantics.CFunctionDef;
import org.tamedragon.compilers.clang.semantics.CTranslationUnit;
import org.tamedragon.compilers.clang.semantics.ClangTransUnit;
import org.tamedragon.compilers.clang.semantics.Environments;
import org.tamedragon.compilers.clang.semantics.Semantic;
import org.tamedragon.compilers.clang.semantics.TargetMachineFactory;
import org.tamedragon.compilers.mips.translate.AssemblySegments;

public class CMain {

	private static final Logger LOG = LoggerFactory.getLogger(CMain.class);
	
	public static void main(String argv[]) {
		
		
//		// INITIALIZE
//		
//		long startTime = System.currentTimeMillis();
//		
//		CMain cMain = new CMain();
//		
//		CLangUtils.populateSettings();
//		CompilerSettings compilerSettings = CompilerSettings.getInstance();
//		//compilerSettings.setInstanceProjectPath("CSrc/TranslationToTreeIR");
//		compilerSettings.setInstanceProjectPath("CSrc/Preprocessor");
//		ErrorHandler errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		DefinitionsMap defsMap = DefinitionsMap.getInstance();
//		defsMap.clearDefinitions();
//		Environments environments = Environments.getInstance();
//		environments.reset();		
//		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
//		includesPreProcessed.clear();
//		
//		//String inputFileName = "MultipleTypesTest2.c";
//		String inputFileName = "IncludeProcessTest5.c";
//		//String inputFileName = "ActivationFrameAssignmentTest3.c";
//		//String inputFileName = "ActivationFrameAssignmentTest1.c";
//		//String inputFileName = "ActivationFrameAssignmentTest4.c";
//		//String inputFileName = "SimpleFunctionTest1.c";
//		//String inputFileName = "IfWithReturn.c";
//	
//		try{
//			boolean isDebugMode = true;
//			
//			String filename = compilerSettings.getInstanceProjectPath() + File.separator  +inputFileName;
//
//			if(isDebugMode){
//				EnvironmentConstants.VALUES.put(EnvironmentConstants.MODE, EnvironmentConstants.DEBUG_MODE);
//			}
//
//			// Generate name of the output file
//			File inFile = new File(filename);
//			String folderStr = inFile.getParentFile().getAbsolutePath();
//			String inFileName = inFile.getName();
//			int periodPos  = inFileName.indexOf("."); 
//			String outFileNameInterim = folderStr + File.separator  // Name and path of the output file
//			+inFileName.substring(0, periodPos) + "_intrm.s";       // for intermediate result
//
//			String outFileName = folderStr + File.separator         // Name and path of the output file
//			+  inFileName.substring(0, periodPos) + ".s";           // for final code after register allocation
//
//			File outFile = new File(outFileNameInterim);
//			
//			// Identify the target architecture
//			TargetMachineFactory targetMachineFactory = new TargetMachineFactory();
//			String targetDesc = compilerSettings.getInstanceTarget();
//			TargetMachine targetArchitecture = targetMachineFactory.getTargetMachine(targetDesc);
//			
//			// Run the pre-processor			
//			PreprocessorMain ppMain = new PreprocessorMain(filename);			
//			InputStream sourceFileInputStream = ppMain.process(true); 
//
//			// Translate to abstract syntax tree
//			TranslationUnit translationUnit = CLangUtils.getTranslationByLLParsing(sourceFileInputStream);
//
//			// Pass through semantic analyzer and translate to assembly tree
//			//SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(filename, targetDesc); 
//			CompilationContext compilationContext = compilerSettings.getInstanceCompilationContext();
//			Semantic semantic = new Semantic(filename, compilationContext);
//			List<ClangTransUnit> translationUnits =  semantic.translateAbstractTree(translationUnit);    	  
//			errorHandler.displayResult();
//			if(errorHandler.getNumErrors() != 0) 
//				return;
//
//			// No errors in semantic analysis; continue with intermediate code generation
//			// for each function and generate intermediate code
//			Debug debug = new Debug();
//			Vector<String> finalCodeListing = new Vector<String>();    	  
//			boolean compiledSuccessfully = true;
//
//			Vector<String> commonData = new Vector<String>();
//
//			int numTransUnits = translationUnits.size();
//			int numFuncs = 0;
//			for(int i = 0; i < numTransUnits; i++){
//				CTranslationUnit ctu = (CTranslationUnit) translationUnits.get(i);
//				if(!(ctu instanceof CFunctionDef))
//					continue;
//
//				CFunctionDef functionDef = (CFunctionDef) ctu;
//				AssemType body = functionDef.getExternalDeclTree();
//				ActivationFrame activationFrame = functionDef.getActivationFrame();
//
//				String fname = functionDef.getName();			
//				Linearizer linearizer = cMain.processWithLinearizer(debug, body, fname);
//
//				// Generate intermediate code
//				CodeGenerator codeGenerator = new CodeGenerator();
//				codeGenerator.generateCode(linearizer.getTrace());
//				Vector<AssemblyInstruction> instList = codeGenerator.getInstructionsList();
//				boolean isLeafProcedure = codeGenerator.isLeafProcedure();
//				activationFrame.setIsLeafProcedure(isLeafProcedure);
//				
//				if(numFuncs == 0){
//					writeInstructionsToFile(outFile, instList, true);
//				}
//				else{
//					writeInstructionsToFile(outFile, instList, false);
//				}
//				
//				// Create a control flow graph from the instructions created above
//				// Optimization phase
//				ControlFlowGraph flowGraph = new ControlFlowGraph(instList, false);
//				
//				if(EnvironmentConstants.VALUES.get(EnvironmentConstants.MODE).equals(
//						EnvironmentConstants.DEBUG_MODE)) 
//					Utilities.generateXMLFromCFG(flowGraph, 
//							EnvironmentConstants.VALUES.get(EnvironmentConstants.DEBUG_OUTPUT_FOLDER),
//							functionDef.getName(), "cfg_xml_before_optimization.xml");        			  
//
//				OptimizationContext optimizationContext = new OptimizationContext(flowGraph, targetArchitecture.isLoadStore());
//				optimizationContext.setSSAMethod();
//				flowGraph = optimizationContext.getOptimizedFlowGraph();
//
//				CFGLinearizationDFS cfgLinearizationDFS = new CFGLinearizationDFS();
//				instList = (Vector<AssemblyInstruction>)
//						cfgLinearizationDFS.execute(flowGraph, flowGraph.getStartNode());
//
//				// Translate back from SSA form 
//				SSAOptimizationUtils.backTranslateFromSSA(flowGraph);
//				if(EnvironmentConstants.VALUES.get(EnvironmentConstants.MODE).equals(
//						EnvironmentConstants.DEBUG_MODE)) 
//					Utilities.generateXMLFromCFG(flowGraph, 
//							EnvironmentConstants.VALUES.get(EnvironmentConstants.DEBUG_OUTPUT_FOLDER),
//							flowGraph.getStartNode().getName(), "cfg_xml_after_back_translation_from_SSA.xml");  
//				
//				instList = ((Vector<AssemblyInstruction>)
//						cfgLinearizationDFS.execute(flowGraph, flowGraph.getStartNode()));
//				
//				// Modify control flow graph to add target specific instructions
//				// (example: function calls in MIPS will be handled by moving arguments to $a0, etc.
//				
//				instList = targetArchitecture.modify(instList, activationFrame, isLeafProcedure);
//				
//				//cMain.printInstructionList(instList);
//				
//				flowGraph = new ControlFlowGraph(instList, false);	       
//				if(EnvironmentConstants.VALUES.get(EnvironmentConstants.MODE).equals(
//						EnvironmentConstants.DEBUG_MODE)) 
//					Utilities.generateXMLFromCFG(flowGraph, 
//							EnvironmentConstants.VALUES.get(EnvironmentConstants.DEBUG_OUTPUT_FOLDER),
//							flowGraph.getStartNode().getName(), "cfg_xml_after_back_translation_from_SSA_and_target_changes.xml");  
//				
//				// Create a flow graph with one instruction in each node to facilitate 
//				// the construction of an interference graph
//				flowGraph = new ControlFlowGraph(instList, true);	       
//
//				// Create the interference graph
//				InterferenceGraph ig = new InterferenceGraph(flowGraph);
//				//LOG.debug("LIVENESS INFORMATION:");
//				//ig.showLiveInAndLiveOut();
//				//LOG.debug("INTERFERENCE GRAPH:");
//				//ig.showInterferenceGraph();
//				//ig.showMoveRelatedNodes();
//
//				
//				// Do register allocation
//				RegisterAllocator registerAllocator = new RegisterAllocator(ig, 
//						instList, targetArchitecture);
//				/*
//				HashMap<String, HashSet<Temp>> colorVsTemps = registerAllocator.getColorVsTemps();
//				
//				updateActivationFrameWithRegistersUsedInfo(activationFrame, colorVsTemps);
//				
//				Vector<AssemblyInstruction> functionBodyCode = registerAllocator.getOutputInstructionList();
//				//cMain.printInstructionList(functionBodyCode);
//				
//				//LOG.debug(activationFrame.toString());
//				
//				ControlFlowGraph cfgAfterSSAOpts = new ControlFlowGraph(functionBodyCode, false);
//				if(EnvironmentConstants.VALUES.get(EnvironmentConstants.MODE).equals(
//						EnvironmentConstants.DEBUG_MODE)) 
//					Utilities.generateXMLFromCFG(cfgAfterSSAOpts, 
//							EnvironmentConstants.VALUES.get(EnvironmentConstants.DEBUG_OUTPUT_FOLDER),
//							cfgAfterSSAOpts.getStartNode().getName(), "cfg_xml_after_register_allocation.xml");
//
//				// Create the final code for the function by building the pre-call, post-call, 
//				// prologue and epilogue code	        	
//				if(functionBodyCode != null){
//					
//					Vector<String> functionCode = targetArchitecture.createCodeStringForFunction(
//							activationFrame, functionBodyCode,
//							functionDef.isMainFunction());
//					
//					HashMap<Label, TargetDataType> labelVsTargetDataType = functionDef.getLabelVsString();
//					commonData.addAll(targetArchitecture.getGlobalDataDeclarations(labelVsTargetDataType));
//					
//					//finalCodeListing.addAll(0, functionCode);
//					finalCodeListing.addAll(functionCode);
//				}
//				else{
//					LOG.debug("Warning - Actual spilling not yet implemented");
//					compiledSuccessfully = false;
//					break;
//				}    		
//				
//				numFuncs++;
//				*/
//			}
//			
//			if(compiledSuccessfully){
//				// Add the directives if any
//				AssemblySegments assemblySegments = new AssemblySegments();
//				Vector<String> preTextDirectives =  assemblySegments.getPreTextDirectives();
//				Vector<String> postTextDirectives = assemblySegments.getPostTextDirectives();
//				Vector<String> dataDirectives = assemblySegments.getDataDirectives();
//				finalCodeListing.addAll(0, preTextDirectives);
//				finalCodeListing.addAll(postTextDirectives);
//				finalCodeListing.addAll(dataDirectives);
//				finalCodeListing.addAll(commonData);
//
//				outFile = new File(outFileName);
//				LOG.debug("Generating output assembly code to file: " + outFile.getAbsolutePath());
//				writeFinalInstructionStringsToFile(outFile, finalCodeListing);
//				
//				long endTime = System.currentTimeMillis();
//				int timeDiff = (int) (endTime - startTime);
//				int timeInSecs = (int) (timeDiff / 1000);
//				LOG.debug("Compilation successfully completed in " + timeInSecs + " seconds");
//			}
//		}
//		catch(Exception e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	private void printInstructionList(Vector<AssemblyInstruction> functionBodyCode){
		LOG.debug("INSTRUCTION LIST:");
		for(AssemblyInstruction ai: functionBodyCode){
			if(ai == null){
				LOG.debug("Null here");
			}
			else{
				LOG.debug(ai.toString());
			}
		}		
	}
	
	private Linearizer processWithLinearizer( AssemType body, String fname){
		Linearizer linearizer = new Linearizer(fname);		  
		linearizer.linearize(body);
		
		linearizer.getCanonized();

		linearizer.generateBasicBlocks();
		
		linearizer.generateTrace();

		return linearizer;
	}

	private static void writeInstructionsToFile(File outFile, Vector instList, boolean overWrite){
	
		try{
			
			// If there instructions already in this file, read them first
			String line = "";
			String finalStr = "";
			int numPrevLines = 0;
			if(!overWrite){
				BufferedReader bf = new BufferedReader(new FileReader(outFile));
				while((line = bf.readLine()) != null){
					finalStr += line + "\n";
					numPrevLines++;
				}
							
				bf.close();
			}
			
			FileWriter fileWriter = new FileWriter(outFile);
			int instNum = instList.size();
			String newInsStr = "";
			for(int i = 0; i < instNum; i++){
				AssemblyInstruction instr = (AssemblyInstruction) instList.elementAt(i);
				
				String linenum = "" + (i + numPrevLines) + ") "; 
				String str = "";
				if(instr != null)
					str = linenum + instr.toString();
				else
					str = linenum + "<NULL>\n";
				
				newInsStr += str;
				
			}
			
			finalStr += newInsStr;
			
			fileWriter.write(finalStr);
			
			fileWriter.flush();
			fileWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		int m = 0;
		m++;
		
	}

	private static void writeFinalInstructionStringsToFile(File outFile, Vector instList){
		if(instList == null){
			LOG.warn("Warning: Oppurtunity for Register allocation with re-write arose - not yet implemented");
			return;
		}

		try{
			FileWriter fileWriter = new FileWriter(outFile);
			int instNum = instList.size();
			for(int i = 0; i < instNum; i++)
			{
				String str = (String) instList.elementAt(i);
				fileWriter.write(str);
			}
			fileWriter.flush();
			fileWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	private static void updateActivationFrameWithRegistersUsedInfo(ActivationFrame activationFrame, 
					HashMap<String, HashSet<Temp>> colorVsTemps){
		
		Set<String> colors = colorVsTemps.keySet();
		Iterator<String> colorIterator = colors.iterator();
		while(colorIterator.hasNext()){
			
			String color = colorIterator.next();

			HashSet<Temp> tempList = colorVsTemps.get(color);
			Iterator<Temp> iter1 = tempList.iterator();
			
			Temp widestTemp = null;
			
			while(iter1.hasNext()){
				Temp tmp = iter1.next();
				if(widestTemp == null)
					widestTemp = tmp;
				
				if(tmp.isWiderThan(widestTemp))
					widestTemp = tmp;
				
			}

			AbstractType abstractTypeAssignedToRegister = ConversionUtility.getAbstractType(widestTemp);
			activationFrame.addRegistersUsed(color, abstractTypeAssignedToRegister);
		}
	}
	*/
	
}


