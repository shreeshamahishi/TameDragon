package org.tamedragon.visualization.ide;

/* DirectoryTree.java
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
 * the projects
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class DirectoryTree extends JPanel implements TreeExpansionListener, TreeSelectionListener, ActionListener {

	private static final long serialVersionUID = 6583176194168294503L;
	protected static Icon customOpenIcon = new ImageIcon("images/openFolder2.jpg");
	protected static Icon customClosedIcon = new ImageIcon("images/openFolder2.jpg");
	protected static Icon customLeafIcon = new ImageIcon("images/cSource.jpg");

	private JTree tree;
	protected TreeModel model;
	private DefaultMutableTreeNode root;
	private String defaultDir = null;
	private static boolean DEBUG = false;
	private IDEMainFrame mainFrame;
	private JPopupMenu rootMenu, projectMenu, fileMenu;
	private JMenuItem mi;
	private TreePath _clickedPath;

	public DirectoryTree(IDEMainFrame mainFrame, String defaultDirStr) {
		this.mainFrame = mainFrame;
		defaultDir = defaultDirStr;
		System.out.println("The default workspace is " + defaultDir);

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		File file = null;
		if(defaultDir != null){
			file = new File(defaultDir);
			if (!file.exists())
				file.mkdirs();

		}
		else{
			defaultDir = IDESupport.getResource("app.default.proj.ws");
			if(defaultDir == null){
				defaultDir = "C:\\Users\\shreesha\\CompilerVisionWorkSpace";
			}
			file = new File(defaultDir);
			
			if (!file.exists()) {
				
				file = null;
				file = new File(defaultDir);
				if (!file.exists())
					file.mkdirs();
			}

		}

		root = new DefaultMutableTreeNode(file.getName(), true);

		listAllFiles(defaultDir, root, true);

		setLayout(new BorderLayout());

		model = new DefaultTreeModel(root);

		//tree = new JTree(root);
		//tree = new IDENavigationTree(root);
		tree = new JTree(addNodes(null, file));

		if (root.getChildCount() == 0) {
			renderer.setOpenIcon(customOpenIcon);
			renderer.setClosedIcon(customClosedIcon);
			renderer.setLeafIcon(customOpenIcon);
		} else {
			renderer.setOpenIcon(customOpenIcon);
			renderer.setClosedIcon(customClosedIcon);
			renderer.setLeafIcon(customLeafIcon);
		}

		// System.out.println("The depth of the tree is ++++++ " + root.);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.setCellRenderer(renderer);

		// tree.setRootVisible(false);
		// Listen for when the selection changes.
		tree.addTreeSelectionListener(this);

		add(new JScrollPane((JTree) tree), "Center");

		rootMenu = new JPopupMenu();

		mi = new JMenuItem("New Project");
		mi.addActionListener(this);
		mi.setActionCommand("newProject");
		rootMenu.add(mi);

		//		mi = new JMenuItem("New C Source");
		//		mi.addActionListener(this);
		//		mi.setActionCommand("newCSource");
		//		rootMenu.add(mi);

		//		mi = new JMenuItem("New C Header");
		//		mi.addActionListener(this);
		//		mi.setActionCommand("newCHeader");
		//		rootMenu.add(mi);	
		//		rootMenu.setOpaque(true);
		//		rootMenu.setLightWeightPopupEnabled(true);

		projectMenu = new JPopupMenu();
		mi = new JMenuItem("New C Source");
		mi.addActionListener(this);
		mi.setActionCommand("newCSource");
		projectMenu.add(mi);

		mi = new JMenuItem("New C Header");
		mi.addActionListener(this);
		mi.setActionCommand("newCHeader");
		projectMenu.add(mi);

		mi = new JMenuItem("Delete");
		mi.addActionListener(this);
		mi.setActionCommand("delete");
		projectMenu.add(mi);

		fileMenu = new JPopupMenu();
		mi = new JMenuItem("Delete");
		mi.addActionListener(this);
		mi.setActionCommand("delete");
		fileMenu.add(mi);

		tree.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent ev) {
				if (ev.isPopupTrigger()) {

					int x = ev.getX();
					int y = ev.getY();
					TreePath path = tree.getPathForLocation(x, y);
					_clickedPath = path;

					DefaultMutableTreeNode clicked_node = (DefaultMutableTreeNode) path.getLastPathComponent();

					if(clicked_node.isRoot())
						rootMenu.show(tree, x, y);
					else if(clicked_node.isLeaf())
						fileMenu.show(tree, x, y);
					else if(!clicked_node.isRoot() && !clicked_node.isLeaf())
						projectMenu.show(tree, x, y);
					else
						;		    		

				}
			}
		});
		//addMouseListener(arg0)
	}



	public Dimension getPreferredSize() {
		return new Dimension(200, 120);
	}

	public static void listAllFiles(String directory,
			DefaultMutableTreeNode parent, Boolean recursive) {
		File[] children = new File(directory).listFiles(); // list all the files
		// in the directory
		if (children == null)
			children = new File("C:\temp").listFiles();
		for (int i = 0; i < children.length; i++) { // loop through each
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(
					children[i].getName());
			// only display the node if it isn't a folder, and if this is a
			// recursive call
			if (children[i].isDirectory() && recursive) {
				parent.add(node); // add as a child node
				listAllFiles(children[i].getPath(), node, recursive); // call
				// again
				// for
				// the
				// subdirectory
			} else if (!children[i].isDirectory()) { // otherwise, if it isn't a
				// directory
				System.out.println("File name is " + children[i].getName()
						+ " and parent is " + children[i].getParent());
				parent.add(node); // add it as a node and do nothing else
			}
		}

	}

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(
				dir.getName());
		if (curTop != null) { // should only be null at root
			curTop.add(curDir);
		}
		Vector<String> ol = new Vector<String>();
		String[] tmp = dir.list();
		for (int i = 0; i < tmp.length; i++)
			ol.addElement(tmp[i]);
		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
		File f;
		Vector<String> files = new Vector<String>();
		// Make two passes, one for Dirs and one for Files. This is #1.
		for (int i = 0; i < ol.size(); i++) {
			String thisObject = (String) ol.elementAt(i);
			String newPath;
			if (curPath.equals("."))
				newPath = thisObject;
			else
				newPath = curPath + File.separator + thisObject;

			f = new File(newPath);
			if (isValidProjectDirectory(f))
				addNodes(curDir, f);
			else if (f.isFile() && isValidProjectDirectory(f.getParentFile())) {
				// System.out.println("Parent is " + f.getParent());
				// System.out.println("Curr Path is " + curPath);
				files.addElement(thisObject);
			} else {}
		}
		// Pass two: for files.
		for (int fnum = 0; fnum < files.size(); fnum++) {
			System.out.println("Name is " + (String) files.elementAt(fnum));
			curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
		}
		return curDir;
	}

	public boolean isValidProjectDirectory(File file) {
		boolean isValid = false;

		if (file.isDirectory()) {
			String[] names = file.list();

			for (int i = 0; i < names.length; i++) {
				if (names[i].equalsIgnoreCase(".cproject")) {
					isValid = true;
					break;
				}
			}
		}

		return isValid;
	}



	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
		.getLastSelectedPathComponent();

		if (node == null)
			return;

		// Object nodeInfo = node.getUserObject();
		Object paths[] = node.getUserObjectPath();
		// String initialPathString = (String) paths[0];
		String initialPathString = defaultDir;

		if (!node.isRoot() && node.isLeaf()) {
			StringBuffer pathBuf = new StringBuffer(initialPathString)
			.append("/");
			// mainFrame.loadSelectedDocument(file);
			// System.out.println("File name is " + nodeInfo);
			for (int i = 0; i < paths.length; i++) {
				if (i == 0) {
					// do Nothing
				} else if (i > 0) {
					if (((String) paths[i]).lastIndexOf(".c") == -1)
						pathBuf.append(paths[i]).append("/");
					else
						pathBuf.append(paths[i]);
				} else {
				}
			}
			System.out.println("The file is " + pathBuf.toString());
			mainFrame.loadSelectedDocument(new File(pathBuf.toString()));
			if (DEBUG) {
				// System.out.print(file.getName() + ":  \n    ");
			}
		} else {
			// displayURL(helpURL);
		}
		if (DEBUG) {
			// System.out.println(nodeInfo.toString());
		}

		// TODO Auto-generated method stub
	}

	public static boolean isValidProject(File dir) {
		boolean isValidProject = false;

		return isValidProject;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		String action = ae.getActionCommand();
		new CustomWizardDialog(mainFrame, true, action, tree, _clickedPath, defaultDir);
	}

	DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	FileNode getFileNode(DefaultMutableTreeNode node) {

		if(node == null)
			return null;

		Object obj = node.getUserObject();

		if(obj instanceof IconData)
			obj = ((IconData)obj).getObject();

		if(obj instanceof FileNode)
			return (FileNode) obj;
		else
			return null;		    
	}

	public void treeExpanded(TreeExpansionEvent event) {

		final DefaultMutableTreeNode node = getTreeNode(event.getPath());
		final FileNode fnode = getFileNode(node);

		Thread runner = new Thread() {

			public void run() {

				if(fnode != null && fnode.expand(node)) {
					Runnable runnable = new Runnable() {
						public void run() {
							((DefaultTreeModel)model).reload(node);
						}
					};
					SwingUtilities.invokeLater(runnable);
				}
			}
		};
		runner.start();				
	}

	public void treeCollapsed(TreeExpansionEvent event) {

	}

	//	class PopupTrigger extends MouseAdapter {
	//
	//	    public void mouseReleased(MouseEvent e) {
	//	    	if (e.isPopupTrigger()) {
	//	    		
	//	    		int x = e.getX();
	//	    		int y = e.getY();
	//	    		
	//	    		TreePath path = tree.getPathForLocation(x, y);
	//	    		
	//	    		if (path != null) {
	//	    			if (tree.isExpanded(path))
	//	    				m_action.putValue(Action.NAME, "Collapse");
	//	    			else
	//	    				m_action.putValue(Action.NAME, "Expand");
	//	    			
	//	    			m_popup.show(tree, x, y);
	//	    			//m_clickedPath = path;
	//	    		}
	//	    	}
	//	    }
	//	}
}
