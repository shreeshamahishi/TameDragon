package org.tamedragon.common.aliasanalysis;

import org.tamedragon.common.llvmir.types.User;

public abstract class CaptureTracker {
	
	/// This callback is used in conjunction with PointerMayBeCaptured. In
	/// addition to the interface here, you'll need to provide your own getters
	/// to see whether anything was captured.

	/// tooManyUses - The depth of traversal has breached a limit. There may be
	/// capturing instructions that will not be passed into captured().
		public abstract void tooManyUses();

	/// shouldExplore - This is the use of a value derived from the pointer.
	/// To prune the search (ie., assume that none of its users could possibly
	/// capture) return false. To search it, return true.
	///
	/// U.getUser() is always an Instruction.
		public abstract boolean shouldExplore(User user);

	/// captured - Information about the pointer was captured by the user of
	/// use U. Return true to stop the traversal or false to continue looking
	/// for more capturing instructions.
		public abstract  boolean captured(User user);

}
