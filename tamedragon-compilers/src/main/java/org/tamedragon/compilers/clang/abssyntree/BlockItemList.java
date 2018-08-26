package org.tamedragon.compilers.clang.abssyntree;

public class BlockItemList extends Absyn implements NodeCollection {
	private BlockItem blockItem;
	private BlockItemList blockItemList;
	
	public BlockItemList(){}
	@Override
	public NodeCollection getNextInCollection() {
		return blockItemList;
	}

	@Override
	public int getNodeCollectionType() {
		return NodeCollection.BLOCK_ITEM_LIST;
	}

	@Override
	public void setDeclarationListNext(NodeCollection newList) {
		this.blockItemList = (BlockItemList)newList;
	}
	
	public BlockItem getBlockItem() {
		return blockItem;
	}
	public void setBlockItem(BlockItem blockItem) {
		this.blockItem = blockItem;
	}
	public BlockItemList getBlockItemList() {
		return blockItemList;
	}
	public void setBlockItemList(BlockItemList blockItemList) {
		this.blockItemList = blockItemList;
	}

}
