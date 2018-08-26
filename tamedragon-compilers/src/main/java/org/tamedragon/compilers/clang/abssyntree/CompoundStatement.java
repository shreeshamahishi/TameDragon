package org.tamedragon.compilers.clang.abssyntree;

public class CompoundStatement extends Statement {
	
	private BlockItemList blockItemList;
	
	public CompoundStatement(){}

	public BlockItemList getBlockItemList() {
		return blockItemList;
	}

	public void setBlockItemList(BlockItemList blockItemList) {
		this.blockItemList = blockItemList;
	}
}
