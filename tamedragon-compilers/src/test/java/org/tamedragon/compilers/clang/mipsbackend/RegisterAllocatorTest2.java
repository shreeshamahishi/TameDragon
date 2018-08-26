package org.tamedragon.compilers.clang.mipsbackend;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import org.tamedragon.assemblyabstractions.ActivationFrame;
//import org.tamedragon.common.ControlFlowGraph;
import org.tamedragon.common.InterferenceGraph;
import org.tamedragon.common.Label;
import org.tamedragon.common.RegisterAllocator;
import org.tamedragon.common.TargetMachine;
import org.tamedragon.common.llvmir.instructions.AssemblyInstruction;
import org.tamedragon.common.llvmir.instructions.Call;
import org.tamedragon.common.llvmir.instructions.LabelInstr;
import org.tamedragon.common.llvmir.instructions.Operation;
import org.tamedragon.common.llvmir.types.Numeric;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.compilers.clang.semantics.ActivationFrameFactory;
import org.tamedragon.compilers.clang.semantics.TargetMachineFactory;

public class RegisterAllocatorTest2 {

//	private ControlFlowGraph cfg;
//	private RegisterAllocator registerAllocator;
//
//	@Before
//	public void setUp(){
//		try{
//			
//			TargetMachineFactory targetMachineFactory = new TargetMachineFactory();
//			TargetMachine targetArchitecture = targetMachineFactory.getTargetMachine("MIPS");
//			
//			ActivationFrameFactory activationFrameFactory = new ActivationFrameFactory();
//			ActivationFrame activationFrame = activationFrameFactory.getActivationFrame("MIPS");
//			activationFrame.setSourceName("main");
//			activationFrame.setLabel(new Label());	
//			
//			
//			Vector<AssemblyInstruction> instList = createSampleInstList();
//			instList = targetArchitecture.modify(instList, activationFrame, true);
//			printInstructionList(instList);
//
//			cfg = new ControlFlowGraph(instList, true);	       
//
//			// Create the interference graph
//			InterferenceGraph ig = new InterferenceGraph(cfg);
//			System.out.println("LIVENESS INFORMATION:");
//			ig.showLiveInAndLiveOut();
//			System.out.println("INTERFERENCE GRAPH:");
//			ig.showInterferenceGraph();
//			ig.showMoveRelatedNodes();
//			
//			// Allocate registers
//			registerAllocator = new RegisterAllocator(ig, 
//					instList, targetArchitecture);
//
//			Vector<AssemblyInstruction> functionBodyCode = registerAllocator.getOutputInstructionList();
//			printInstructionList(functionBodyCode);
//			
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void test1() {      
//		
//	}
//
//	private Vector<AssemblyInstruction> createSampleInstList(){
//		Vector<AssemblyInstruction> instList = new Vector<AssemblyInstruction>();
//		Temp kTemp = createNewTemp();
//		Temp jTemp = createNewTemp();
//		Temp gTemp = createNewTemp();
//		Temp hTemp = createNewTemp();
//		Temp fTemp = createNewTemp();
//		Temp eTemp = createNewTemp();
//		Temp mTemp = createNewTemp();
//		Temp bTemp = createNewTemp();
//		Temp cTemp = createNewTemp();
//		Temp dTemp = createNewTemp();
//		Temp formatTemp1 = createNewTemp();
//		Temp formatTemp2 = createNewTemp();
//		Temp formatTemp3 = createNewTemp();
//
//		LabelInstr mainLbl = new LabelInstr("main");
//
//		AssemblyInstruction ai1 = getAssemblyIns1(gTemp, jTemp);
//		AssemblyInstruction ai2 = getAssemblyIns2(hTemp, kTemp);
//		AssemblyInstruction ai3 = getAssemblyIns3(fTemp, gTemp, hTemp);
//		AssemblyInstruction ai4 = getAssemblyIns4(eTemp, jTemp);
//		AssemblyInstruction ai5 = getAssemblyIns5(mTemp, jTemp);
//		AssemblyInstruction ai6 = getAssemblyIns6(bTemp, fTemp);
//		AssemblyInstruction ai7 = getAssemblyIns7(cTemp, eTemp);
//		AssemblyInstruction ai8 = getAssemblyIns8(dTemp, cTemp);
//		AssemblyInstruction ai9 = getAssemblyIns9(kTemp, mTemp);
//		AssemblyInstruction ai10 = getAssemblyIns10(jTemp, bTemp);
//
//		AssemblyInstruction laAi1 = getAssemblyLa1(formatTemp1);
//		AssemblyInstruction callAi1 = getAssemblyInsCall(formatTemp1, dTemp);
//		AssemblyInstruction laAi2 = getAssemblyLa2(formatTemp2);
//		AssemblyInstruction callAi2 = getAssemblyInsCall(formatTemp2, kTemp);
//		AssemblyInstruction laAi3 = getAssemblyLa3(formatTemp3);
//		AssemblyInstruction callAi3 = getAssemblyInsCall(formatTemp3, jTemp);
//
//		LabelInstr endLbl = new LabelInstr("end");  
//
//		instList.add(mainLbl);
//		instList.add(ai1);
//		instList.add(ai2);
//		instList.add(ai3);
//		instList.add(ai4);
//		instList.add(ai5);
//		instList.add(ai6);
//		instList.add(ai7);
//		instList.add(ai8);
//		instList.add(ai9);
//		instList.add(ai10);
//		instList.add(laAi1);
//		instList.add(callAi1);
//		instList.add(laAi2);
//		instList.add(callAi2);
//		instList.add(laAi3);
//		instList.add(callAi3);
//		instList.add(endLbl);
//
//		return instList;
//	}
//
//	private Temp createNewTemp(){
//		Temp temp = new Temp();
//		temp.setInteger(true);
//		temp.setIntegerSize(32);
//		return temp;
//	}
//
//	
//	private AssemblyInstruction  getAssemblyIns1(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, new Numeric(Numeric.INTEGER_TYPE, "12"), null); 		
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns2(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.MINUS, new Numeric(Numeric.INTEGER_TYPE, "1"), null);
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns3(Temp destTemp, Temp srcTemp1, Temp srcTemp2){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp1);
//		srcList.add(srcTemp2);
//
//		Operation opr = new Operation(destList, srcList, Operation.MUL, null, null);
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns4(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, new Numeric(Numeric.INTEGER_TYPE, "8"), null); 		
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns5(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, new Numeric(Numeric.INTEGER_TYPE, "16"), null); 		
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns6(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.LOAD_FROM_TEMP_OFFSET_EXP_MEM, new Numeric(Numeric.INTEGER_TYPE, "0"), null);	
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns7(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.ADD, new Numeric(Numeric.INTEGER_TYPE, "8"), null);
//		return opr;	
//	}
//
//	private AssemblyInstruction  getAssemblyIns8(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP, null, null);	
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns9(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.ADD, new Numeric(Numeric.INTEGER_TYPE, "4"), null);
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyIns10(Temp destTemp, Temp srcTemp){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(destTemp);
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(srcTemp);
//
//		Operation opr = new Operation(destList, srcList, Operation.MOVE_TEMP_TO_TEMP, null, null);	
//		return opr;		
//	}
//
//	private AssemblyInstruction  getAssemblyInsCall(Temp formatStr, Temp srcTemp){
//
//		Vector<Operand> srcList = new Vector<Operand>();
//		srcList.add(formatStr);
//		srcList.add(srcTemp);
//
//		Call call = new Call("printf", null, srcList);		
//		return call;		
//	}
//
//	private AssemblyInstruction getAssemblyLa1(Temp formatTemp1){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(formatTemp1);
//		return new Operation(destList, null, Operation.LOAD_ADDRESS, null, 
//				"L1");
//	}
//	
//	private AssemblyInstruction getAssemblyLa2(Temp formatTemp2){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(formatTemp2);
//		return new Operation(destList, null, Operation.LOAD_ADDRESS, null, 
//				"L2");
//	}
//	
//	private AssemblyInstruction getAssemblyLa3(Temp formatTemp3){
//		Vector<Temp> destList = new Vector<Temp>();
//		destList.add(formatTemp3);
//		return new Operation(destList, null, Operation.LOAD_ADDRESS, null, 
//				"L3");
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
