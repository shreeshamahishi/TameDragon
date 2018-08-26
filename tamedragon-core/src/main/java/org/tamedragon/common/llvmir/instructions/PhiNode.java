package org.tamedragon.common.llvmir.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.tamedragon.common.LatticeValue;
import org.tamedragon.common.Pair;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionDetailAccessException;
import org.tamedragon.common.llvmir.instructions.exceptions.InstructionUpdateException;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Constant;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Operand;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class is used to represent the magical mystical PHI 
 * node, that can not exist in nature, but can be synthesised in a computer
 * scientist's over active imagination.
 * @author ipsg
 *
 */
public class PhiNode extends Instruction {

	//private List<BasicBlock> predecessorBasicBlocks;
	private HashMap<Integer, Pair<Value, BasicBlock>> valueAndPredecessorBasicBlocks;
	private Value primaryValue;
	private int numPredecessors;

	protected PhiNode(Type type, String name, List<Value> operandList, 
			Value primaryValue, int numPredecessors, BasicBlock parent) { 
		super(InstructionID.PHI_NODE_INST, type, operandList, parent);
		setName(name);
		this.primaryValue = primaryValue;
		//predecessorBasicBlocks = new ArrayList<BasicBlock>();
		valueAndPredecessorBasicBlocks = new HashMap<Integer, Pair<Value, BasicBlock>>();
		this.numPredecessors = numPredecessors;
	}

	/**
	 * Constructor will have an empty list of operands. Operands will be added / removed later
	 * in update operations.
	 * @param type
	 * @param name
	 * @return
	 */
	public static PhiNode create(Type type, String name, int numPredecessors, Value primaryValue, BasicBlock parent) {
		List<Value> operandList = new ArrayList<Value>(numPredecessors);
		return new PhiNode(type, name, operandList, primaryValue, numPredecessors, parent);
	}

	/**
	 * Add an incoming value to the end of the PHI list.
	 */
	public void addIncoming(Value value, BasicBlock basicBlock) throws InstructionUpdateException {
		if(value == null)
			throw new InstructionUpdateException(InstructionUpdateException.PHI_VALUE_CANNOT_BE_NULL);

		if(getType() != value.getType()){
			throw new InstructionUpdateException(InstructionUpdateException.INCOMPATIBLE_OPERAND_FOR_PHI_NODE);
		}

		addOperand(value);
		//addBasicBlock(basicBlock);
		
		Pair<Value, BasicBlock> incomingPair = new Pair<Value, BasicBlock>(value, basicBlock);
		int index = getNumOperands() -1;
		
		valueAndPredecessorBasicBlocks.put(index, incomingPair);
		//incomingValueAndBasicBlock.put(basicBlock, value);
	}

//	public void addBasicBlock(BasicBlock basicBlock){
//		predecessorBasicBlocks.add(basicBlock);
//	}

	public void setPhiOperandAt(int index, Value value){
		int numOperands = getNumOperands();
		if(numOperands == 0){
			// No operands have not been set yet; do so now
			int count = 0;
			while(count < numPredecessors){
				addOperand(null);
				count++;
			}
			
			return;
		}
		
		// Update the operands
		setOperand(index, value);
		
		if(valueAndPredecessorBasicBlocks.containsKey(index)){
			BasicBlock basicBlock = valueAndPredecessorBasicBlocks.get(index).getSecond();
			valueAndPredecessorBasicBlocks.remove(index);
			Pair<Value, BasicBlock> incomingPair = new Pair<Value, BasicBlock>(value, basicBlock);
			valueAndPredecessorBasicBlocks.put(index, incomingPair);
		}
	}

	/**
	 * Returns the number of incoming edges
	 */
	public int getNumIncomingValues() {
		return getNumOperands(); 
	}

	public void setPrimaryValue(Value primaryValue) {
		this.primaryValue = primaryValue;
	}

	public Value getPrimaryValue() {
		return primaryValue;
	}

	public int getNumPredecessors() {
		return numPredecessors;
	}

	/**
	 * Returns incoming value number x
	 */
	public Value getIncomingValue(int index) throws InstructionDetailAccessException {
		if(index < 0 || index >= getNumIncomingValues())
			throw new InstructionDetailAccessException(
					InstructionDetailAccessException.INVALID_INDEX_FOR_PREDECESSOR);
		return getOperand(index);
	}

	/**
	 * Return incoming basic block number @p i.
	 */
	public BasicBlock getIncomingBlock(int index) throws InstructionDetailAccessException {
		if(index < 0 || index >= getNumIncomingValues())
			throw new InstructionDetailAccessException(
					InstructionDetailAccessException.INVALID_INDEX_FOR_PREDECESSOR);
		
		Pair<Value, BasicBlock> incomingPair = valueAndPredecessorBasicBlocks.get(index);
		
		return incomingPair.getSecond();
	}

	public void setIncomingValueAndBasicBlock(int index, Value value, 
			BasicBlock basicBlock) throws InstructionUpdateException {
		if(index < 0 || index >= getNumIncomingValues())
			throw new InstructionUpdateException(InstructionUpdateException.INVALID_INDEX_FOR_PREDECESSOR);

		if(value == null)
			throw new InstructionUpdateException(InstructionUpdateException.PHI_VALUE_CANNOT_BE_NULL);
		if(basicBlock == null)
			throw new InstructionUpdateException(InstructionUpdateException.BASIC_BLOCK_CANNOT_BE_NULL);

		if(getType() != value.getType()){
			//"All operands to PHI node must be the same type as the PHI node!"
			throw new InstructionUpdateException(InstructionUpdateException.INCOMPATIBLE_OPERAND_FOR_PHI_NODE);
		}

		setOperand(index, value);
		
		Pair<Value, BasicBlock> incomingPair = new Pair<Value, BasicBlock>(value, basicBlock);
		valueAndPredecessorBasicBlocks.put(index, incomingPair);
	}

	/**
	 * Returns incoming basic block corresponding to an operand of the PHI.
	 */
//	public BasicBlock getIncomingBlock(Value value) throws InstructionDetailAccessException{
//		int index = getIndexOf(value);
//		if(index < 0)
//			throw new InstructionDetailAccessException(InstructionDetailAccessException.PHI_NODE_DOES_NOT_HAVE_OPERAND);
//
//		return valueAndPredecessorBasicBlocks.get(value);
//		//return predecessorBasicBlocks.get(index);
//	}

	/**
	 * Remove an incoming value.  This is useful if a predecessor basic block is deleted.  
	 * The value removed is returned. 
	 */

//	public Value removeIncomingValue(int index) throws InstructionUpdateException {
//
//		if(index < 0 || index >= getNumIncomingValues())
//			throw new InstructionUpdateException(
//					InstructionUpdateException.INVALID_INDEX_FOR_PREDECESSOR);
//
//		return removeOperandAt(index);
//	}

	public Value removeIncomingValue(BasicBlock basicBlock) throws InstructionUpdateException {
		int index = getBasicBlockIndex(basicBlock);
		if (index < 0){
			// "Invalid basic block argument to remove!"
			throw new InstructionUpdateException(					
					new InstructionDetailAccessException(
							InstructionDetailAccessException.INVALID_INDEX_FOR_PREDECESSOR), 
							InstructionUpdateException.NO_SUCH_BASIC_BLOCK);
		}

		Value incomingValue = getIncomingValueForBasicBlock(basicBlock);
		valueAndPredecessorBasicBlocks.remove(incomingValue);

		boolean removed = removeOperand(incomingValue);
		if(!removed){
			return null;
		}
		incomingValue.removeUser(this);
		
		// If this is the incoming value is the only argument to the phi node,
		// fold this phi node
		if(getNumOperands() == 1){
			replaceAllUsesOfThisWith(getOperand(0));
			getParent().removeInstruction(this);
		}
		
		return incomingValue;
	}

	private Value getIncomingValueForBasicBlock(BasicBlock basicBlock){
	
		Iterator<Entry<Integer, Pair<Value, BasicBlock>>> iterator = valueAndPredecessorBasicBlocks.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Pair<Value, BasicBlock>> entry = iterator.next();
			Pair<Value, BasicBlock> incomingPair = entry.getValue();
			if(incomingPair.getSecond() == basicBlock){
				return incomingPair.getFirst();
			}
		}
		return null;
	}
	
	/**
	 * Returns the first index of the specified basic block in the value 
	 * list for this PHI.  Returns -1 if there is no such instance.
	 */
	public int getBasicBlockIndex( BasicBlock basicBlock) {
		
		Value incomingValue = getIncomingValueForBasicBlock(basicBlock);
		if(incomingValue == null){
			// TODO Log warning here
			return -1;
		}
		
		return getIndexOf(incomingValue);
	}

	public Value getIncomingValueForBlock( BasicBlock  basicBlock)  throws InstructionDetailAccessException{
		int index = getBasicBlockIndex(basicBlock);
		if(index < 0){
			// "Invalid basic block argument!");
			throw new InstructionDetailAccessException(					
					new InstructionDetailAccessException(
							InstructionDetailAccessException.INVALID_INDEX_FOR_PREDECESSOR),
							InstructionDetailAccessException.NO_SUCH_BASIC_BLOCK);
		}

		return getIncomingValue(index);
	}

	/**
	 * If the specified PHI node always merges together the  same value, 
	 * return the value, otherwise return null.
	 */
	public Value hasConstantValue() {
		// Exploit the fact that phi nodes always have at least one entry.

		if(getNumOperands() == 0)
			return null;
		try{
			Value constantValue = getIncomingValue(0);

			for (int i = 1, e = getNumIncomingValues(); i != e; ++i)
				if (getIncomingValue(i) != constantValue)
					return null; // Incoming values not all the same.

			return constantValue;
		}
		catch(InstructionDetailAccessException idae){ }

		return null;
	}

	@Override
	public String emit(){
		String description = "";
		String name = LLVMIREmitter.getInstance().getValidName(this);
		
		if(name == null)   
			name = "";
		
		description += name + " = phi ";
		description += getType() + " ";
		int numOps = getNumOperands();

		if(numOps == 0){
			description += "[]";
			return description;
		}

		for(int i = 0; i < numOps; i++){
			Value op = getOperand(i);
			String opName = LLVMIREmitter.getInstance().getValidName(op);
			//BasicBlock bb = predecessorBasicBlocks.get(i);
			BasicBlock bb = valueAndPredecessorBasicBlocks.get(i).getSecond();
			String bbName = LLVMIREmitter.getInstance().getValidName(bb);
			if(i == numOps -1)
				description += "[ " + opName + ", " + bbName + " ]";
			else
				description += "[ " + opName + ", " + bbName + " ], ";

		}

		return description;
	}

	@Override
	public boolean isCastInstruction() {
		return false;
	}

	@Override
	public boolean definesNewValue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isTerminatorInstruction() {
		return false;
	}

	@Override
	public boolean isCritical() {
		return false;
	}

	@Override
	public LatticeValue visitForSCCP(LatticeValue latticeValueBeforeModelling,
			HashSet<BasicBlock> unreachableNodes,
			HashMap<Value, LatticeValue> tempVsLatticeValue,
			Vector<Value> ssaWorkList,
			Vector<BasicBlock> cfgWorkList) {
		LatticeValue newVal = new LatticeValue();

		// If this PN returns a struct, just mark the result overdefined.
		if (getType().isStructType()){
			newVal.setType(LatticeValue.OVERDEFINED);
			return newVal;
		}

		if (latticeValueBeforeModelling.getType() == LatticeValue.OVERDEFINED)
			return latticeValueBeforeModelling;  // Quick exit

		// Super-extra-high-degree PHI nodes are unlikely to ever be marked constant,
		// and slow us down a lot.  Just mark them overdefined.
		if (getNumIncomingValues() > 64){
			newVal.setType(LatticeValue.OVERDEFINED);
			return newVal;
		}

		Map<Integer, Integer>  basicBlockPositions = null;
		try{
			basicBlockPositions = getBasicBlockPosition();
		}
		catch (InstructionDetailAccessException e) {
			// TODO Log error here
			System.exit(-1);
		}
		
		// Look at all of the executable operands of the PHI node.  If any of them
		// are overdefined, the PHI becomes overdefined as well.  If they are all
		// constant, and they agree with each other, the PHI becomes the identical
		// constant.  If they are constant and don't agree, the PHI is overdefined.
		// If there are no executable operands, the PHI remains undefined.
		Constant firstConst = null;
		BasicBlock pred = null;
		for (int i = 0, e = getNumIncomingValues(); i != e; ++i) {
			Value operand = null;
			try{
				operand = getIncomingValue(i);
				pred = getIncomingBlock(i);
			}
			catch(InstructionDetailAccessException idae){
				// TODO Log here
				idae.printStackTrace();
				System.exit(-1);
			}

			if(unreachableNodes.contains(pred)) { // This predecessor is not reachable (yet?)
				continue;
			}

			LatticeValue operandLatticeValue = tempVsLatticeValue.get(operand);

			if (operandLatticeValue.getType() == LatticeValue.UNDEFINED) 
				continue;  // Doesn't influence PHI node.

			if (operandLatticeValue.getType() == LatticeValue.OVERDEFINED){    
				// PHI node becomes overdefined!
				newVal.setType(LatticeValue.OVERDEFINED);
				return newVal;
			}

			BasicBlock parent = getParent();
			
			CFG cfg = parent.getParent().getCfg();
			
			
			int indexInPredecessorList = basicBlockPositions.get(i);
			List<BasicBlock> predecessorsOfBasicBlock = cfg.getPredecessors(getParent());
			if(unreachableNodes.contains(predecessorsOfBasicBlock.get(indexInPredecessorList))){
				// Predecessor at this index is unreachable, continue
				continue;
			}

			// Lattice value must be a constant

			if (firstConst == null) {   // Grab the first value.
				firstConst = operandLatticeValue.getConstantValue();
				continue;
			}

			// There is already a reachable operand.  If we conflict with it,
			// then the PHI node becomes overdefined.  If we agree with it, we
			// can continue on.

			// Check to see if there are two different constants merging, if so, the PHI
			// node is overdefined.
			Constant constVal = operandLatticeValue.getConstantValue();
			if (!constVal.equals(firstConst)){
				newVal.setType(LatticeValue.OVERDEFINED);
				return newVal;
			}
		}

		// If we exited the loop, this means that the PHI node only has constant
		// arguments that agree with each other(and OperandVal is the constant) or
		// OperandVal is null because there are no defined incoming arguments.  If
		// this is the case, the PHI remains undefined.
		//
		if (firstConst != null){
			newVal.setConstantValue(firstConst);  // Acquire operand value
			return newVal;   
		}
		else{
			// Not sure how to interpet this
			return latticeValueBeforeModelling;
		}
	}

	private Map<Integer, Integer> getBasicBlockPosition() throws InstructionDetailAccessException {
		
		Map<Integer, Integer>  basicBlockPositions = new HashMap<Integer, Integer>();
		CFG cfg = getParent().getParent().getCfg();
		List<BasicBlock> predecessorsOfBasicBlock = cfg.getPredecessors(getParent());
		
		
		for (int i = 0, e = getNumIncomingValues(); i != e; ++i) {
			int indexInPredecessorList = predecessorsOfBasicBlock.indexOf(getIncomingBlock(i));
			basicBlockPositions.put(i, indexInPredecessorList);
		}
		
		return basicBlockPositions;
	}

	@Override
	public boolean canBeHoistedOrSank() {
		return false;
	}

	@Override
	public Constant foldIfPossible() {
		// If all the operands are constants and the same, return that
		// constant
		int numOperands = getNumIncomingValues();
		Constant constVal = null;
		for(int i = 0; i < numOperands; i++){
			Value value = getOperand(i);
			if(!value.isAConstant()){
				return null;
			}

			if(constVal == null){
				constVal = (Constant) value;
			}
			else{
				Constant currentConst = (Constant) value;
				if(currentConst != constVal){
					return null;
				}
			}
		}

		return constVal;
	}
}
