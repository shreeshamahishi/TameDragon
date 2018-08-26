package org.tamedragon.common.aliasanalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tamedragon.common.llvmir.instructions.CallInst;
import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.instructions.Instruction.InstructionID;
import org.tamedragon.common.llvmir.types.GlobalValue;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;

public class CaptureTrackingUtil {

	private static final int THRESHOLD = 20;

	public static boolean pointerMayBeCaptured(final Value value,
			boolean returnCaptures,
			boolean storeCaptures) throws Exception{
		if(value instanceof GlobalValue){
			throw new Exception("It doesn't make sense to ask whether a global is captured.");
		}

		// TODO: If StoreCaptures is not true, we could do Fancy analysis
		// to determine whether this store is not actually an escape point.
		// In that case, BasicAliasAnalysis should be updated as well to
		// take advantage of this.
		//(void)StoreCaptures;

		SimpleCaptureTracker simpleCaptureTracker = new SimpleCaptureTracker(returnCaptures);
		pointerMayBeCaptured(value, simpleCaptureTracker);
		return simpleCaptureTracker.isCaptured();
	}

	/// PointerMayBeCaptured - Visit the value and the values derived from it and
	/// find values which appear to be capturing the pointer value. This feeds
	/// results into and is controlled by the CaptureTracker object.
	public static void pointerMayBeCaptured(final Value V, CaptureTracker tracker) throws Exception {

		if(!V.getType().isPointerType()){
			throw new Exception("Capture is for pointers only!");
		}
		List<User> Worklist = new ArrayList<User>();
		Set<User> Visited = new HashSet<User>();

		int Count = 0;

		int numUses = V.getNumUses();

		for (int i = 0; i < numUses; i++) {
			// If there are lots of uses, conservatively say that the value
			// is captured to avoid taking too much compile time.
			if (Count++ >= THRESHOLD){
				tracker.tooManyUses();
				return;
			}

			User user = (User)V.getUserAt(i);
			if (!tracker.shouldExplore(user)) 
				continue;
			
			Visited.add(user);
			Worklist.add(user);
		}

		while (!Worklist.isEmpty()) {
			User user = Worklist.remove(0);
			
			Instruction I = (Instruction) user;
			
			InstructionID instrID = I.getInstructionID();
			switch (instrID) {
			case CALL_INST:
			case INVOKE_INST: {
				
				//TODO Handle Invoke instructions when Invoke is implemented
				CallInst callInst = (CallInst) I;
				
				// Not captured if the callee is readonly, doesn't return a copy through
				// its return value and doesn't unwind (a readonly function can leak bits
				// by throwing an exception or not depending on the input value).
				if (callInst.onlyReadsMemory() && callInst.doesNotThrow() && I.getType().isVoidType())
					break;

				// Not captured if only passed via 'nocapture' arguments.  Note that
				// calling a function pointer does not in itself cause the pointer to
				// be captured.  This is a subtle point considering that (for example)
				// the callee might return its own address.  It is analogous to saying
				// that loading a value from a pointer does not cause the pointer to be
				// captured, even though the loaded value might be the pointer itself
				// (think of self-referential objects).
				int numArgs = callInst.getNumArgOperands();
				for (int k = 0; k < numArgs; k++){
					Value argVal = callInst.getArgOperand(k);
					//TODO Implement "does not capture" and other attributes
					boolean doesNotCapture = callInst.paramHasAttr(0);
					if (argVal == V && !doesNotCapture)
						// The parameter is not marked 'nocapture' - captured.
						if (tracker.captured(user))
							return;
				}
				break;
			}
			case LOAD_INST:
				// Loading from a pointer does not cause it to be captured.
				break;
			case VA_ARG_INST:
				// "va-arg" from a pointer does not cause it to be captured.
				break;
			case STORE_INST:
				if (V == I.getOperand(0))
					// Stored the pointer - conservatively assume it may be captured.
					if (tracker.captured(user))
						return;
			// Storing to the pointee does not cause the pointer to be captured.
			break;
			case BIT_CAST_INST:
			case GET_ELEMENT_PTR:
			case PHI_NODE_INST:
			case SELECT_INST:
				// The original value is not captured via this if the new value isn't.
				for (int j = 0; j < numUses; j++) {
					User usr = (User)I.getUserAt(j);
					if (Visited.add(usr))
						if (tracker.shouldExplore(usr))
							Worklist.add(usr);
				}
			break;
			case ICMP_INST:
				// Don't count comparisons of a no-alias return value against null as
				// captures. This allows us to ignore comparisons of malloc results
				// with null, for example.
				//TODO Implement this after implementing stripPointerCasts() and 
				// ConstantPointerNull
//				if (isNoAliasCall(V.stripPointerCasts()))
//					if (ConstantPointerNull *CPN =
//						dyn_cast<ConstantPointerNull>(I->getOperand(1)))
//						if (CPN->getType()->getAddressSpace() == 0)
//							break;
			// Otherwise, be conservative. There are crazy ways to capture pointers
			// using comparisons.
			if (tracker.captured(user))
				return;
			break;
			default:
				// Something else - be conservative and say it is captured.
				if (tracker.captured(user))
					return;
				break;
			}
		}

		// All uses examined.
	}
}

