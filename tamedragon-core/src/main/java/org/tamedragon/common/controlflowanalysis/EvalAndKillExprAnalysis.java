package org.tamedragon.common.controlflowanalysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Value;

public class EvalAndKillExprAnalysis {
	private Map<BasicBlock, List<Instruction>> evalList = new LinkedHashMap<BasicBlock, List<Instruction>>();
	private Map<BasicBlock, List<Instruction>> killList = new LinkedHashMap<BasicBlock, List<Instruction>>();

	public EvalAndKillExprAnalysis(Function function) {
		populateEvalList(function);
		populateKillList(function);
	}
	
	private void populateEvalList(Function function){
		Iterator<BasicBlock> basicBlockIterator = function.basicBlocksIterator();
		while(basicBlockIterator.hasNext()) {
			BasicBlock bb = basicBlockIterator.next();
			Iterator<Instruction> instrs = bb.getInstructions();
			List<Instruction> evalInstrs = new ArrayList<Instruction>();
			while(instrs.hasNext()){
				Instruction instr = instrs.next();
				if(instr instanceof BinaryOperator || instr instanceof ICmpInst || instr instanceof GetElementPtrInst){
					evalInstrs.add(instr);
				}
			}
			evalList.put(bb,evalInstrs);
		}
	}
	
	private void populateKillList(Function function){
		Iterator<BasicBlock> basicBlockIterator1 = function.basicBlocksIterator();
		while(basicBlockIterator1.hasNext()) {
			BasicBlock bb = basicBlockIterator1.next();
			Iterator<Instruction> instrs = bb.getInstructions();
			List<Instruction> killInstrs = new ArrayList<Instruction>();
			while(instrs.hasNext()){
				Instruction instr = instrs.next();
				if(instr instanceof BinaryOperator || instr instanceof ICmpInst || instr instanceof GetElementPtrInst){
					
					Iterator<BasicBlock> basicBlockIterator2 = function.basicBlocksIterator();
					while(basicBlockIterator2.hasNext()) {
						BasicBlock bb2 = basicBlockIterator2.next();
						if(bb2 == bb)
							continue;
						
						else{
							Iterator<Instruction> bb2Instrs = bb2.getInstructions();
							while(bb2Instrs.hasNext()){
								Instruction ins = bb2Instrs.next();
								if(ins instanceof BinaryOperator || ins instanceof ICmpInst || ins instanceof GetElementPtrInst){
									int numOfOprnds = ins.getNumOperands();
									while(numOfOprnds != 0){
										Value oprnd = ins.getOperand(numOfOprnds - 1);
										if(oprnd.equals(instr)){
											killInstrs.add(ins);
											break;
										}
										numOfOprnds--;
									}
								}
							}
						}
					}
				}
			}
			killList.put(bb, killInstrs);
		}
	}

	public Map<BasicBlock, List<Instruction>> getEvalList() {
		return evalList;
	}

	public Map<BasicBlock, List<Instruction>> getKillList() {
		return killList;
	}
}
