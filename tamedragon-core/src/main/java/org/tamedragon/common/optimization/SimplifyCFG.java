package org.tamedragon.common.optimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.tamedragon.common.DominatorTree;
import org.tamedragon.common.DominatorTreeNode;
import org.tamedragon.common.GeneralUtilities;
import org.tamedragon.common.TreeNode;
import org.tamedragon.common.controlflowanalysis.DominatorCalculationContext;
import org.tamedragon.common.llvmir.instructions.AllocaInst;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.instructions.CastInst;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.CompilationContext;
import org.tamedragon.common.llvmir.types.PointerType;
import org.tamedragon.common.llvmir.types.Type;
import org.tamedragon.common.llvmir.types.UndefValue;
import org.tamedragon.common.llvmir.types.Value;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;

/**
 * This class represents an optimization pass that identifies allocation instructions
 * and promotes them if possible. 
 *
 */
public class SimplifyCFG {

	private int numSingleStore;    // Number of alloca's promoted with a single store
	private int numLocalPromoted;   // Number of alloca's promoted within one block

	private int numDeadAllocas;
	private DominatorTree dominatorTree;

	private LLVMIREmitter emitter;

	public Transformations execute(CFG cfg){

		emitter = LLVMIREmitter.getInstance();

		

		return null;
	}

}