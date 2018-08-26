package org.tamedragon.common;

import java.util.Vector;

public class TreeNode {

	protected String name;
	protected TreeNode parent;
	protected Vector<TreeNode> children;

	public TreeNode() {
		children = new Vector<TreeNode>();
	}

	public TreeNode(String name){
		this.name = name;
		children = new Vector<TreeNode>();
	}

	public Vector<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(Vector<TreeNode> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void addChild(TreeNode node){
		children.addElement(node);
		node.setParent(this);
	}
}
