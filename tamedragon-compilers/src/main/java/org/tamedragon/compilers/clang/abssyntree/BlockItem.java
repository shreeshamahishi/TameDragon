package org.tamedragon.compilers.clang.abssyntree;

public class BlockItem extends Absyn implements NodeItem {
	private Declaration declaration;
	private Statement statement;
	
	public BlockItem(){}
	
	@Override
	public int getNodeItemType() {
		return NodeItem.BLOCK_ITEM;
	}
	
	public Declaration getDeclaration() {
		return declaration;
	}
	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}

}
