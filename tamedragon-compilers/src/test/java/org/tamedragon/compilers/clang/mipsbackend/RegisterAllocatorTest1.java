package org.tamedragon.compilers.clang.mipsbackend;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

//import org.tamedragon.common.CFGLinearizationDFS;
//import org.tamedragon.common.ControlFlowGraph;
import org.tamedragon.common.InterferenceGraph;
import org.tamedragon.common.RegisterAllocator;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
//import org.tamedragon.common.xmlutils.XmlReader;
import org.tamedragon.compilers.clang.semantics.TargetMachineFactory;

public class RegisterAllocatorTest1 {
	
//	private ControlFlowGraph cfg;
//	private String sourceFilePatXml;
//	private RegisterAllocator registerAllocator;
//	private CFGLinearizationDFS cfgLinearizationDFS;
//	
//	@Before
//	public void setUp(){
//		XmlReader xmlReader = new XmlReader();
//		
//		sourceFilePatXml = "TestData\\MIPSBackend\\RegisterAllocator\\RegisterAllocatorTest1.xml";
//		
//		try{
//			TargetMachineFactory targetMachineFactory = new TargetMachineFactory();
//			String targetDesc = "MIPS";
//			TargetMachine targetArchitecture = targetMachineFactory.getTargetMachine(targetDesc);
//			
//			cfgLinearizationDFS = new CFGLinearizationDFS();
//			
//			Vector<ControlFlowGraph> cfgs = xmlReader.readXml(sourceFilePatXml);
//			cfg = cfgs.elementAt(0);
//			
//			// Linearize to a list of instructions
//			Vector<AssemblyInstruction> instList = (Vector<AssemblyInstruction>)
//					cfgLinearizationDFS.execute(cfg, cfg.getStartNode());
//			
//			// Create cfg with one ins per node for creation of interference graph
//			cfg = new ControlFlowGraph(instList, true);	       
//
//			// Create the interference graph
//			InterferenceGraph ig = new InterferenceGraph(cfg);
//			System.out.println("INTERFERENCE GRAPH:");
//			ig.showInterferenceGraph();
//			ig.showMoveRelatedNodes();
//
//			// Do register allocation
//			registerAllocator = new RegisterAllocator(ig, 
//					instList, targetArchitecture);
//			
//			
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			assertTrue(false);
//		}			
//	}
//	
//	@Test
//	public void test1() {      
//		Vector<AssemblyInstruction> functionBodyCode = registerAllocator.getOutputInstructionList();
//		assertNotNull(functionBodyCode);
//		printInstructionList(functionBodyCode);
//	}
//	
//	private void printInstructionList(Vector<AssemblyInstruction> functionBodyCode){
//		System.out.println("INSTRUCTION LIST:");
//		for(AssemblyInstruction ai: functionBodyCode){
//			if(ai == null){
//				System.out.println("Null here");
//			}
//			else{
//				System.out.print(ai.toString());
//			}
//		}		
//	}
}
