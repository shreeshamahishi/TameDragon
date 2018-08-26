
package org.tamedragon.common.aliasanalysis;

import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.User;
import org.tamedragon.common.llvmir.types.Value;


//===----------------------------------------------------------------------===//
//
// This file defines the default implementation of the Alias Analysis interface
// that simply returns "I don't know" for all queries.
//

/// NoAA - This class implements the -no-aa pass, which always returns "I
/// don't know" for alias queries.  NoAA is unlike other alias analysis
/// implementations, in that it does not chain to a previous analysis.  As
/// such it doesn't follow many of the rules that other alias analyses must.
//===----------------------------------------------------------------------===//


public class NoAliasAnalysis extends  AliasAnalysis {

	public NoAliasAnalysis(AliasAnalysis prev) {
		super(prev);
	}

	public AliasResult alias(StorageLocation locA,StorageLocation locB) {
		return AliasResult.MAY_ALIAS;
	}

	//    virtual ModRefBehavior getModRefBehavior(ImmutableCallSite CS) {
	//      return UnknownModRefBehavior;
	//    }

	@Override
	public ModRefBehavior getModRefBehavior(Function F) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pointsToConstantMemory(StorageLocation loc,
			boolean OrLocal) {
		return false;
	}

	//    @Override
	//    public ModRefResult getModRefInfo(ImmutableCallSite CS,
	//                                       const Location &Loc) {
	//      return ModRef;
	//    }

	//  @Override
	//  public ModRefResult getModRefInfo(ImmutableCallSite CS1,
	//                                       ImmutableCallSite CS2) {
	//      return ModRef;
	//    }

	@Override
	public void deleteValue(Value V) {}

	@Override
	public void copyValue(Value From, Value To) {}

	@Override
	public void addEscapingUse(User U) {}

}