package org.tamedragon.common.controlflowanalysis;

import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.types.Value;

/**
 * This class represents a canonical pointer that keeps getting incremented in a loop.
 * For example, consider the following C code where a pointer is incremented in a loop:
 * 
 * int *ptr;
 * ......
 * ......
 * while(count < MAX){
 * 	ptr += k;    
 * }
 * 
 * The corresponding LLVM intermediate representation after mem2reg will look something like this:
 * 
 * ; <label>:1              		; preds = %20, %0
 * %ptr.0 = phi i32* [ %6, %20 ], [ %arr, %0 ]
 * ........
 * ........
 * ; <label>:3                       ; preds = %1
 * ........
 * %4 = getelementptr inbounds i32* %ptr.0, i32 2
 *
 * where %4 represents the increment and the phi function represents the initial value
 * or the incremented value.
 * 
 * This canonical form is denoted by this class. It has the phi node, the GEP instruction
 * and the (constant )value it is incremented by
 *
 */
public class IncrementingPointerTuple{

	enum IncrementingPointerType {
		PHI_GEP ,  
		STORE_LOAD_GEP_STORE,
		;
	} 

	private IncrementingPointerType incrPtrType;

	// The phi node and the GEP pair when the type is PHI_GEP
	private PhiNode phiNode;
	private GetElementPtrInst gep;

	// The store, load, gep and store tuple when the type is STORE_LOAD_GEP_STORE
	private StoreInst initializingStore;
	private LoadInst preIncrementLoad;
	private StoreInst postIncrementStore;

	// The address being incremented and the value used to increment the address.
	private Value addressBeingIncremented;
	private Value incrementedBy;

	private IncrementingPointerTuple(GetElementPtrInst gep, Value addressBeingIncremented, Value incrementedBy){
		this.gep = gep;
		this.addressBeingIncremented = addressBeingIncremented;
		this.incrementedBy = incrementedBy;
	}

	public IncrementingPointerTuple(PhiNode phiNode, GetElementPtrInst gep, Value addressBeingIncremented, 
			Value incrementedBy){
		this(gep, addressBeingIncremented, incrementedBy);
		incrPtrType = IncrementingPointerType.PHI_GEP;

		this.phiNode = phiNode;

	}

	public IncrementingPointerTuple(StoreInst initializingStore, LoadInst preIncrementLoad,
			GetElementPtrInst gep, StoreInst postIncrementStore,
			Value addressBeingIncremented, 
			Value incrementedBy){
		this(gep, addressBeingIncremented, incrementedBy);
		incrPtrType = IncrementingPointerType.STORE_LOAD_GEP_STORE;

		this.initializingStore = initializingStore;
		this.preIncrementLoad = preIncrementLoad;
		this.postIncrementStore = postIncrementStore;
	}

	public PhiNode getPhiNode() {
		return phiNode;
	}

	public GetElementPtrInst getGep() {
		return gep;
	}

	public Value getIncrementedBy() {
		return incrementedBy;
	}

	public Value getAddressBeingIncremented() {
		return addressBeingIncremented;
	}

	public IncrementingPointerType getIncrPtrType() {
		return incrPtrType;
	}

	public StoreInst getInitializingStore() {
		return initializingStore;
	}

	public LoadInst getPreIncrementLoad() {
		return preIncrementLoad;
	}

	public StoreInst getPostIncrementStore() {
		return postIncrementStore;
	}
}