package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.BinaryOperator;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.ICmpInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LLVMUtility;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.CmpInst.Predicate;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionCreationException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class does Local Common Subexpression Elimination
 *
 */
public class LocalCSE {
	private Integer currentGenerationNumber = 0;
	private Map<Value, Integer> availableLoads = new HashMap<Value, Integer>();
	private Map<Value,Value> mapOfPointerOprndVsValue = new HashMap<Value,Value>();
	private StoreInst lastStore = null;

	public void localCSE(BasicBlock basicBlock) throws InstructionCreationException{
		
		CFG cfg = basicBlock.getParent().getCfg();
		
		if(cfg.getPredecessors(basicBlock).size() == 0){
			currentGenerationNumber++;
		}
		
		Set<Instruction> setOfExprs = new HashSet<Instruction>();
		Instruction instructns;
		Instruction inst, ti;
		int i = 0;
		boolean found;
		Iterator<Instruction> instructions = basicBlock.getInstructions();
		
		List<Instruction> deadInstructions = new ArrayList<Instruction>();
		List<Value> commonSubExpressions = new ArrayList<Value>();
		
		BasicBlockLoop : while(instructions.hasNext()){
			inst = instructions.next();
			CompilationContext compilationContext = inst.getType().getCompilationContext();

			// Perform trivial DCE
			if(!(inst.getType() == Type.getVoidType(compilationContext)) && inst.getNumUses() == 0){
				deadInstructions.add(inst);
				continue;
			}

			found = false;
			if(inst instanceof StoreInst){
				StoreInst storeInst = (StoreInst)inst;
				currentGenerationNumber++;

				if(lastStore != null && lastStore.getPointerOperand() == storeInst.getPointerOperand()){
					commonSubExpressions.add(lastStore);
					lastStore.replaceAllUsesOfThisWith(null);
					lastStore = null;
					continue;
				}

				Value pointerOprnd = storeInst.getPointerOperand();
				availableLoads.put(pointerOprnd, currentGenerationNumber);
				mapOfPointerOprndVsValue.put(pointerOprnd, storeInst.getOperand(0));
				lastStore = storeInst;
				i++;
			}
			else if(inst instanceof LoadInst){
				LoadInst loadInst = (LoadInst)inst;
				Value pointerOprnd = loadInst.getOperand(0);

				if(availableLoads.containsKey(pointerOprnd)){
					Integer currentGen = availableLoads.get(pointerOprnd);
					if(currentGen == currentGenerationNumber){
						commonSubExpressions.add(loadInst);
						loadInst.replaceAllUsesOfThisWith(mapOfPointerOprndVsValue.get(pointerOprnd));
						continue ;
					}
				}
				availableLoads.put(pointerOprnd, currentGenerationNumber);
				mapOfPointerOprndVsValue.put(pointerOprnd, loadInst);
				lastStore = null;
				i++;
			}
			else if(inst instanceof BinaryOperator){
				BinaryOperator binaryOperator = (BinaryOperator)inst;
				Iterator<Instruction> iterator = setOfExprs.iterator();
				while(iterator.hasNext()){
					instructns = iterator.next();
					found = LLVMUtility.compareBinaryExpr(binaryOperator, instructns);
					if(found){
						ti = (BinaryOperator)instructns;
						commonSubExpressions.add(binaryOperator);
						binaryOperator.replaceAllUsesOfThisWith(ti);
					}
				}
				if(!found){
					// Insert new tuple
					setOfExprs.add(binaryOperator);
					i++;
				}
			}
			else if(inst instanceof GetElementPtrInst || inst instanceof ICmpInst){

				if(inst instanceof GetElementPtrInst){					
					GetElementPtrInst elementPtrInst = (GetElementPtrInst)inst;
					Value value = elementPtrInst.getPointerOperand();
					if(value instanceof UndefValue){
						value = UndefValue.createOrGet(elementPtrInst.getType());
						commonSubExpressions.add(elementPtrInst);
						elementPtrInst.replaceAllUsesOfThisWith(value);
						continue BasicBlockLoop;
					}
				}

				Iterator<Instruction> iterator = setOfExprs.iterator();
				while(iterator.hasNext()){
					instructns = iterator.next();
					if(instructns instanceof GetElementPtrInst || instructns instanceof ICmpInst){
						int aebNumOfOprnds = instructns.getNumOperands();
						int numOfOprnds2 = inst.getNumOperands();
						
						// Match current instruction's expression against those in AEB, including commutativity
						boolean flag = true;
						if((instructns instanceof GetElementPtrInst && inst instanceof ICmpInst)||
									(instructns instanceof ICmpInst && inst instanceof GetElementPtrInst)){
							continue;
						}

						while(aebNumOfOprnds == numOfOprnds2 && numOfOprnds2 != 0){
							Value aebOprnd = instructns.getOperand(aebNumOfOprnds - 1);
							Value oprnd = inst.getOperand(numOfOprnds2 - 1);
							LoadInst loadInst = null;

							if(oprnd instanceof LoadInst && aebOprnd instanceof LoadInst){
								loadInst = (LoadInst)oprnd;
								LoadInst loadInst2 = (LoadInst)aebOprnd; 
								oprnd = loadInst.getOperand(0);
								aebOprnd = loadInst2.getOperand(0);
							}

							if(!aebOprnd.equals(oprnd)){
								flag = false;
								break;
							}
							else{
								if(loadInst != null){
									basicBlock.removeInstructionFromBasicBlock(loadInst);
									i--;
								}
							}
							aebNumOfOprnds--;
							numOfOprnds2--;
						}

						if(inst instanceof ICmpInst && instructns instanceof ICmpInst){
							ICmpInst aebCmpInst = (ICmpInst)instructns;
							ICmpInst cmpInst = (ICmpInst)inst;
							Predicate aebPredicate = aebCmpInst.getPredicate();
							Predicate predicate = cmpInst.getPredicate();

							if(aebPredicate != predicate)
								flag = false;
						}

						if(flag){
							found = true;
							ti = (Instruction)instructns;
							commonSubExpressions.add(inst);
							inst.replaceAllUsesOfThisWith(ti);
						}
					}
				}
				if(!found){
					// insert new tuple
					setOfExprs.add(inst);
					i++;
				}
			}
			else if(inst instanceof CallInst){
				CallInst callInst = (CallInst)inst;
				int numOfAgrsOprnds = callInst.getNumArgOperands();
				for(int j = 0; j < numOfAgrsOprnds; j++){
					Value argValue = callInst.getArgOperand(j+1);
					if(argValue.getType().isPointerType()){
						if(availableLoads.containsKey(argValue))
							currentGenerationNumber++;
					}
				}
				i++;
			}
			else{
				i++;
			}
		}
		
		// Remove any dead instructions
		for(Instruction instr : deadInstructions){
			instr.getParent().removeInstruction(instr);
		}
		
		//  and any common sub-expressions
		for(Value value : commonSubExpressions){
			Instruction instr = (Instruction) value;
			instr.getParent().removeInstruction(instr);
		}		
	}
}
