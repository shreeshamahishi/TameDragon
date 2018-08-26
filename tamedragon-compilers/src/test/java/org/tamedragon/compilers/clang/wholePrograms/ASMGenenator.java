package org.tamedragon.compilers.clang.wholePrograms;
//package com.compilervision.compilers.clang.wholePrograms;
//
//import static org.junit.Assert.assertTrue;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Vector;
//
//import org.tamedragon.assemblyabstractions.constructs.AssemType;
//import org.tamedragon.common.CFGLinearizationDFS;
//import org.tamedragon.common.ControlFlowGraph;
//import org.tamedragon.common.EnvironmentConstants;
//import org.tamedragon.common.InterferenceGraph;
//import org.tamedragon.common.RegisterAllocator;
//import org.tamedragon.common.TargetMachine;
//import org.tamedragon.common.Utilities;
//import org.tamedragon.common.exceptions.InvalidIRException;
//import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
//import org.tamedragon.common.optimization.OptimizationContext;
//import org.tamedragon.common.optimization.SSAOptimizationUtils;
//import org.tamedragon.compilers.canon.Linearizer;
//import org.tamedragon.compilers.clang.CLangUtils;
//import org.tamedragon.compilers.clang.CodeGenerator;
//import org.tamedragon.compilers.clang.CompilerSettings;
//import org.tamedragon.compilers.clang.Debug;
//import org.tamedragon.compilers.clang.ErrorHandler;
//import org.tamedragon.compilers.clang.abssyntree.TranslationUnit;
//import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
//import org.tamedragon.compilers.clang.preprocessor.IncludesPreProcessed;
//import org.tamedragon.compilers.clang.preprocessor.PreprocessorMain;
//import org.tamedragon.compilers.clang.semantics.CFunctionDef;
//import org.tamedragon.compilers.clang.semantics.CTranslationUnit;
//import org.tamedragon.compilers.clang.semantics.Environments;
//import org.tamedragon.compilers.clang.semantics.SemanticAnalyzer;
//import org.tamedragon.compilers.clang.semantics.TargetMachineFactory;
//import org.tamedragon.compilers.mips.translate.AssemblySegments;
//
//public class ASMGenenator {
//	public static void main(String[] args) throws Exception {
//		ASMGenenator asmGenerator = new ASMGenenator();
//		
//		CLangUtils.populateSettings();
//		CompilerSettings compilerSettings = CompilerSettings.getInstance();
//		compilerSettings.setInstanceProjectPath("CSrc/TranslationToTreeIR");
//		ErrorHandler errorHandler = ErrorHandler.getInstance();
//		errorHandler.reset();
//		DefinitionsMap defsMap = DefinitionsMap.getInstance();
//		defsMap.clearDefinitions();
//		Environments environments = Environments.getInstance();
//		environments.reset();		
//		HashMap<String, HashMap<String, List<InputStream>>> includesPreProcessed = IncludesPreProcessed.getInstance();
//		includesPreProcessed.clear();
//		
//		String inputFileName = args[0];
//	
//			boolean isDebugMode = true;
//			
//			String filename = compilerSettings.getInstanceProjectPath() 
//			+ File.separator  +inputFileName;
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
//			SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(filename, targetDesc); 	    
//			List<CTranslationUnit> translationUnits =  semanticAnalyzer.translateAbstractTree(translationUnit);    	  
//			errorHandler.displayResult();
//			if(errorHandler.getNumErrors() != 0) 
//				return;
//
//			// No errors in semantic analysis; continue with intermediate code generation
//			// for each function and generate intermediate code
//			Debug debug = new Debug();
//			//Vector intermediateCodeListing = new Vector();
//			Vector<String> finalCodeListing = new Vector<String>();    	  
//			boolean compiledSuccessfully = true;
//
//			HashMap<String, String> fNameVsNumRegsUsed = new HashMap<String, String>();
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
//				
//				AssemType funcTree = functionDef.getExternalDeclTree();
//				assertTrue(funcTree != null);
//				
//				debug.printAssem(funcTree);
//
//				String fname = functionDef.getName();
//			
//				Linearizer linearizer = asmGenerator.processWithLinearizer(debug, body, fname);
//
//				// Generate intermediate code
//				CodeGenerator codeGenerator = new CodeGenerator();
//				codeGenerator.generateCode(linearizer.getTrace());
//				Vector<AssemblyInstruction> instList = codeGenerator.getInstructionsList();
//				
//				if(numFuncs == 0){
//					writeInstructionsToFile(outFile, instList, true);
//				}
//				else{
//					writeInstructionsToFile(outFile, instList, false);
//
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
//				OptimizationContext optimizationContext = new OptimizationContext(flowGraph, 
//						targetArchitecture.isLoadStore());
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
//				// Create a flow graph with one instruction in each node to facilitate 
//				// the construction of an interference graph
//				instList = ((Vector<AssemblyInstruction>)
//						cfgLinearizationDFS.execute(flowGraph, flowGraph.getStartNode()));
//				flowGraph = new ControlFlowGraph(instList, true);	       
//
//				// Create the interference graph
//				InterferenceGraph ig = new InterferenceGraph(flowGraph);
//				System.out.println("LIVENESS INFORMATION:");
//				ig.showLiveInAndLiveOut();
//				System.out.println("INTERFERENCE GRAPH:");
//				ig.showInterferenceGraph();
//				ig.showMoveRelatedNodes();
//
//				// Do register allocation
//				RegisterAllocator registerAllocator = new RegisterAllocator(ig, 
//						instList, targetArchitecture);
//
//				Vector<AssemblyInstruction> functionBodyCode = registerAllocator.getOutputInstructionList();
//				asmGenerator.printInstructionList(functionBodyCode);
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
//					int numRegistersUsed = registerAllocator.getNumRegistersUsed();
//					fNameVsNumRegsUsed.put(fname, "" + numRegistersUsed);
//					Vector<String> functionCode = targetArchitecture.createCodeStringForFunction(
//							functionDef.getActivationFrame(),
//							functionBodyCode, functionDef.isMainFunction());
//					
//					commonData.addAll(functionDef.dataDeclarations());
//
//					finalCodeListing.addAll(0, functionCode);
//				}
//				else{
//					System.out.println("Warning - Actual spilling not yet implemented");
//					compiledSuccessfully = false;
//					break;
//				}    		
//				
//				numFuncs++;
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
//				System.out.println("Generating output assembly code to file: " + outFile.getAbsolutePath());
//				writeFinalInstructionStringsToFile(outFile, finalCodeListing);  
//				System.out.println("Compilation successfully completed.");
//			}
//
//	}
//	
//	private void printInstructionList(Vector<AssemblyInstruction> functionBodyCode){
//		System.out.println("INSTRUCTION LIST:");
//		for(AssemblyInstruction ai: functionBodyCode){
//			if(ai == null){
//				System.out.println("Null here");
//			}
//			else{
//				System.out.println(ai.toString());
//			}
//		}		
//	}
//	
//	private Linearizer processWithLinearizer(Debug debug, AssemType body, String fname){
//		Linearizer linearizer = new Linearizer(fname);		  
//		linearizer.linearize(body);
//		
//		System.out.println("AFTER CANONIZING WITH LINEARIZER:");
//		debug.print(linearizer.getCanonized());
//
//		linearizer.generateBasicBlocks();
//		System.out.println("BASIC BLOCKS WITH LIST GENERATED FROM LINEARIZER:");
//		debug.print(linearizer.getBasicBlocks());
//		
//		linearizer.generateTrace();
//		System.out.println("TRACE SCHEDULE WITH LINEARIZER:");
//		debug.print(linearizer.getTrace());
//
//		return linearizer;
//	}
//
//	private static void writeInstructionsToFile(File outFile, Vector instList, boolean overWrite) throws IOException{
//	
//			// If there instructions already in this file, read them first
//			String line = "";
//			String finalStr = "";
//			int numPrevLines = 0;
//			if(!overWrite){
//				BufferedReader bf = new BufferedReader(new FileReader(outFile));
//				while((line = bf.readLine()) != null){
//					finalStr += line + "\n";
//					numPrevLines++;
//				}
//							
//				bf.close();
//			}
//			
//			FileWriter fileWriter = new FileWriter(outFile);
//			int instNum = instList.size();
//			String newInsStr = "";
//			for(int i = 0; i < instNum; i++){
//				AssemblyInstruction instr = (AssemblyInstruction) instList.elementAt(i);
//				
//				String linenum = "" + (i + numPrevLines) + ") "; 
//				String str = "";
//				if(instr != null)
//					str = linenum + instr.toString();
//				else
//					str = linenum + "<NULL>\n";
//				
//				newInsStr += str;
//				
//			}
//			
//			finalStr += newInsStr;
//			
//			fileWriter.write(finalStr);
//			
//			fileWriter.flush();
//			fileWriter.close();
//		
//			int m = 0;
//			m++;
//		
//	}
//
//	private static void writeFinalInstructionStringsToFile(File outFile, Vector instList) throws IOException{
//		if(instList == null){
//			System.out.println("Warning: Oppurtunity for Register allocation with re-write arose - not yet implemented");
//			return;
//		}
//
//			FileWriter fileWriter = new FileWriter(outFile);
//			int instNum = instList.size();
//			for(int i = 0; i < instNum; i++)
//			{
//				String str = (String) instList.elementAt(i);
//				fileWriter.write(str);
//			}
//			fileWriter.flush();
//			fileWriter.close();
//	}
//}
