package org.tamedragon.visualization.ide;

import javax.swing.JDialog;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.event.ActionEvent;
import java.io.File;

public class CustomWizardDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1218485893087711253L;
	private IDEMainFrame mainFrame = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private boolean answer = false;
	private JLabel wsLabel = null;//new JLabel("Workspace:");
	private JTextField name = null;//new JTextField();
	private JTree tree = null;
	private TreePath path = null;
	private String actionNameString = null;
	private String defaultDir = null;
	
		
	public boolean getAnswer() {
		return answer;
	}

	public CustomWizardDialog(IDEMainFrame frame, boolean modal, String myMessage, JTree tree, TreePath path, String defaultDir) {
		super(frame, modal);
		this.mainFrame = frame;
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		okButton = new JButton("Yes");
		cancelButton = new JButton("Cancel");
		this.tree = tree;
		this.path = path;
		this.defaultDir = defaultDir;

	    name = new JTextField();
	    this.actionNameString = myMessage;
		if(myMessage.equalsIgnoreCase("newProject")) {
			setTitle("Project Wizard");
			wsLabel = new JLabel("Project Name:");
		} else if(myMessage.equalsIgnoreCase("newCSource")) {
			setTitle("C Source Wizard");
			wsLabel = new JLabel("C Source Name:");
		} else if(myMessage.equalsIgnoreCase("newCHeader")) {
			setTitle("C Header Wizard");
			wsLabel = new JLabel("C Header Name:");
		} else if(myMessage.equalsIgnoreCase("delete")) {
			setTitle("Delete File/Folder?");
			wsLabel = new JLabel("Are you sure you want to delete?");
		} else {}

		if(myMessage.equalsIgnoreCase("delete")) {
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(wsLabel)
					.addGroup(layout.createSequentialGroup()
                    		   .addComponent(okButton)
                    		   .addComponent(cancelButton))
					);
			layout.linkSize(SwingConstants.HORIZONTAL, okButton, cancelButton);
			
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addComponent(wsLabel)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				                    .addComponent(okButton)
				                    .addComponent(cancelButton))));
			
		} else {
			
			layout.setHorizontalGroup(layout.createSequentialGroup()
				    .addComponent(wsLabel)
				    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addComponent(name)
                    .addGroup(layout.createSequentialGroup()
                    		   .addComponent(okButton)
                    		   .addComponent(cancelButton))));
			
			layout.linkSize(SwingConstants.HORIZONTAL, okButton, cancelButton);
			
			layout.setVerticalGroup(layout.createSequentialGroup()
				    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    		.addComponent(wsLabel)
			                .addComponent(name))
				    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				                    .addComponent(okButton)
				                    .addComponent(cancelButton))));
		}
		
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okActionPerformed(evt);
			}
		});		
		
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}
		});

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("The source is " + e.getSource() + " " + e.getActionCommand());
		if (okButton == e.getSource()) {
			System.err.println("User chose ok.");
			answer = true;
			setVisible(false);
		} else if (cancelButton == e.getSource()) {
			System.err.println("User chose Cancel.");
			answer = false;
			this.dispose();
		}
	}
	
	private void okActionPerformed(java.awt.event.ActionEvent evt) {
		
		if(actionNameString.equalsIgnoreCase("newProject")) {
			
			String projectNameStr = this.name.getText();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
					

			if (projectNameStr == null || projectNameStr.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid Project Name", "",
						1);
				return;
			}
			
			try {
				
				DefaultMutableTreeNode dmtn, root, projFile;

				File f = new File(defaultDir,
						projectNameStr);
				if(f.exists()) {
					JOptionPane.showMessageDialog(null, "Project Already Exists. Choose a different name", "",	1); 
					return;	
				} else if(f.mkdir()) {
					root = (DefaultMutableTreeNode)node.getRoot();
					dmtn = new DefaultMutableTreeNode(f.getName());
					root.add(dmtn);
					File cProjFile = new File(f, ".cproject");
					cProjFile.createNewFile();
					projFile = new DefaultMutableTreeNode(cProjFile.getName());
					dmtn.add(projFile);
					
					this.dispose();
					((DefaultTreeModel )tree.getModel()).nodeStructureChanged(node);
					((DefaultTreeModel )tree.getModel()).reload(node);
					
					tree.repaint();
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Project Name", "",	1); 
					return;
				}
				
			} catch (Exception ioe) {

			}
		
		} else if (actionNameString.equalsIgnoreCase("delete")) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
			File f = null;
			String filePath = null;
			
			if(node.isLeaf()) {
    			filePath = defaultDir + "\\" + parent.toString();
    			f = new File(filePath, node.toString());
    			System.out.println("The path is ==== " + filePath);
    		} else {
    			f = new File(defaultDir,
					node.toString());
    		}
			if(f.isDirectory()) {
				boolean success = deleteDir(f);
				
				int nodeIndex = parent.getIndex(node);
				node.removeAllChildren();
				parent.remove(nodeIndex);
				((DefaultTreeModel )tree.getModel()).nodeStructureChanged(node); 
				
				if(success)
					System.out.println("Delete was successful!");
				else
					System.out.println("Problems during deletion");	
				
				((DefaultTreeModel )tree.getModel()).reload(parent);				
			} else {
				boolean success = f.delete();
				
				int nodeIndex = parent.getIndex(node);
				parent.remove(nodeIndex);
				((DefaultTreeModel )tree.getModel()).nodeStructureChanged(node); 
				
				if(success) {
					System.out.println("Delete was successful!");
					mainFrame.closeDeletedDocument(f);
				}
				else
					System.out.println("Problems during deletion");
			}
			
			this.dispose();
			tree.repaint();			
		} else if (actionNameString.equalsIgnoreCase("newCSource") || actionNameString.equalsIgnoreCase("newCHeader")) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
			String fileNameStr = null;
			if(actionNameString.equalsIgnoreCase("newCSource"))
				fileNameStr = this.name.getText() + ".c";
			else
				fileNameStr = this.name.getText() + ".h";
			
			if (fileNameStr == null || fileNameStr.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid File Name", "",
						1);
				return;
			}
			
			String projectPath = defaultDir + "\\" + node.toString();
			
			try {
				File f = new File(projectPath, fileNameStr);
				if(f.exists()) {
					JOptionPane.showMessageDialog(null, "File Already Exists. Choose a different name", "",	1); 
					return;	
				} else if (f.createNewFile()) {
					DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(f.getName());
					node.add(fileNode);
			    	this.dispose();
			    	((DefaultTreeModel )tree.getModel()).nodeStructureChanged(node);
			    	((DefaultTreeModel )tree.getModel()).reload(node);
			    	tree.repaint();
			    	mainFrame.loadSelectedDocument(f);			    	
			    } else {
			    	JOptionPane.showMessageDialog(null, "Invalid source file name", "",	1); return;
			    }
				
			} catch(Exception ex) {}		
			
		}  else {
			
		}		
		
	}
	
	private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}
	
	public boolean deleteDir(File dir) {
		File f = null;
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	        	f = new File(dir, children[i]);
	            boolean success = deleteDir(f);
	            mainFrame.closeDeletedDocument(f);
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    // The directory is now empty so delete it
	    return dir.delete();
	}

}
