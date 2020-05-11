package org.tamedragon.common.analysis;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;
import org.tamedragon.common.common.analysis.InstrInfoQuery;
import org.tamedragon.common.common.analysis.SimplifyInstruction;
import org.tamedragon.common.common.analysis.SimplifyQuery;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.math.ULong;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.utils.LLVMIRUtils;

public class SimplifyInstructionTests {

	private static final String ROOT_PATH = "Analysis";
	private static final int MAX_RECURSE = 3;
	
	@Test
	public void runSimplifyIns1() throws Exception {

		String cSrcfilename =  "ConstOps.ll";
		String llvmOutFileName = "ConstOpsOut.ll";

		InstrInfoQuery iiq = new InstrInfoQuery(); 
		SimplifyQuery sq = new SimplifyQuery(null, null, iiq);

		List<Instruction> instructions = getInstructions(cSrcfilename, llvmOutFileName);
		SimplifyInstruction si = new SimplifyInstruction();
		int insCount = 0;
		for(Instruction ins: instructions) {
			System.out.println("Simplifying instruction " + ins);
			Value value = si.simplifyInstruction(ins, sq, MAX_RECURSE);
			if(insCount == 0) {   // Check simplify add instruction
				assertTrue(value.toString().equals("i8 91"));
			}
			else if(insCount == 1) {  // Check simplify add instruction
				assertTrue(value.toString().equals("i8 -65"));
			}
			else if(insCount == 2) {  // Check simplify add instruction
				assertTrue(value.toString().equals("i8 9"));
			}
			else if(insCount == 2) {  // Check simplify add instruction
				assertTrue(value.toString().equals("i8 -13"));
			}
			insCount++;
			System.out.println("Result of simplifying = " + value);
		}
	}
	
	@Test
	public void runSimplifyIns2() throws Exception {

		String cSrcfilename =  "SimplifyInstructionSrc2.ll";
		String llvmOutFileName = "SimplifyInstructionOut2.ll";

		InstrInfoQuery iiq = new InstrInfoQuery(); 
		SimplifyQuery sq = new SimplifyQuery(null, null, iiq);

		List<Instruction> instructions = getInstructions(cSrcfilename, llvmOutFileName);
		SimplifyInstruction si = new SimplifyInstruction();
		for(Instruction ins: instructions) {
			System.out.println("Simplifying instruction " + ins);
			si.simplifyInstruction(ins, sq, MAX_RECURSE);
		}
	}

	private List<Instruction> getInstructions(String llvmSrcFileName, String llvmOutFileName) throws Exception  {

		List<Instruction> instructions = new ArrayList<>();

		LLVMIRUtils llvmirUtils = new LLVMIRUtils();
		llvmirUtils.getInstructionsList(ROOT_PATH, llvmSrcFileName);

		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();

		Function function = functions.get(0);
		for(int i = 0; i < function.getNumBasicBlocks(); i++) {
			BasicBlock bb = function.getBasicBlockAt(i);

			Iterator<Instruction> instrs = bb.getInstructions();
			while(instrs.hasNext()) {
				Instruction ins = instrs.next();
				instructions.add(ins);
			}
		}

		return instructions;
	}
}
