package org.tamedragon.visualization.ide;

/* FunctionTree.java
 *
 * @ 1.0
 *
 * @ 2010/12/03
 *
 * @ Sunil Tuppale
 *
 * @ Copyright (c) 2008 - 2010 Skygraph Technologies Pvt. Ltd.
 *
 * This is the JTree Panel that contains a list of
 * the C files
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FunctionTree extends JPanel {

	private static final long serialVersionUID = 852362039310955677L;
	JTree tree;
	DefaultMutableTreeNode root;
	String defaultDir = null;

	public FunctionTree() {
		defaultDir = IDESupport.getResource("app.workspace");
		root = new DefaultMutableTreeNode(defaultDir, true);
		//getList(root, new File(defaultDir));
		listAllFiles(defaultDir, root, true);
		setLayout(new BorderLayout());
		tree = new JTree(root);
		tree.setRootVisible(true);
		add(new JScrollPane((JTree) tree), "Center");
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 120);
	}

	public static void listAllFiles(String directory, DefaultMutableTreeNode parent, Boolean recursive) {
		File [] children = null;
		if(directory != null){
			children = new File(directory).listFiles(); // list all the files in the directory
		}
		if (children == null)
			children = new File("D:\\shreesha\\work\\CompilerVision\\LLVMTests").listFiles();
		for (int i = 0; i < children.length; i++) { // loop through each
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(children[i].getName());
			// only display the node if it isn't a folder, and if this is a recursive call
			if (children[i].isDirectory() && recursive) {
				//parent.add(node); // add as a child node
				listAllFiles(children[i].getPath(), node, recursive); // call again for the subdirectory
			} //else if (!children[i].isDirectory()){ // otherwise, if it isn't a directory
			else {
				if (children[i].getName().endsWith(".bc")) {
				    System.out.println("The fileName is " + children[i].getName());
				    parent.add(node); // add it as a node and do nothing else
				}
			}
		}

	}
}

