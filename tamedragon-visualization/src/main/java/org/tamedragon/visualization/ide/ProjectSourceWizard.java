package org.tamedragon.visualization.ide;

import java.awt.Dimension;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProjectSourceWizard extends JDialog {

	private static final long serialVersionUID = -3194262961596298374L;
	private static final Dimension dimension = new Dimension(450, 150);
	private String wizard_Ind = null;
	// private String inputString = "";
	IDEMainFrame mainFrame = null;
	// private String actionString = null;
	private JLabel pNameLabel = null, wsLabel = null, cSourceLabel = null;
	private JTextField projectName = null, cSourceName = null,
			projectPath = null;
	// private JCheckBox defaultWSSet = null;
	private JButton jbtOK = null, jbtCancel = null, jbtBrowse = null;

	// JFileChooser fc = null;

	public ProjectSourceWizard(JFrame frame, boolean modal, String wizardInd) {
		// super(frame, "Select Workspace", modal);
		System.out.println("Wizaed Ind is " + wizardInd);
		mainFrame = (IDEMainFrame) frame;
		wizard_Ind = wizardInd;
		if (wizard_Ind.equalsIgnoreCase(IDESupport
				.getResource("new.project.ind"))) {
			setTitle("Project Wizard");
			initProjectWizardComponents();
		} else if(wizard_Ind.equalsIgnoreCase(IDESupport
				.getResource("new.c.source.ind"))) {
			setTitle("C Source Wizard");
			initCSourceWizardComponents();
		}
		pack();
		setLocationRelativeTo(frame);
		setSize(dimension);
		setVisible(true);
	}

	private void initProjectWizardComponents() {
		System.out.println("Creating project wizard!");
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		pNameLabel = new JLabel("Project Name:");
		wsLabel = new JLabel("Workspace:");
		projectName = new JTextField();
		projectPath = new JTextField();
		projectPath.setText(IDESupport.getResource("app.workspace"));
		projectPath.setEditable(false);
		jbtOK = new JButton("OK");
		jbtCancel = new JButton("Cancel");

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		// layout.linkSize(SwingConstants.HORIZONTAL, c3, c4);
		try {

			// The sequential group in turn contains two parallel groups.
			// One parallel group contains the labels, the other the text
			// fields.
			// Putting the labels in a parallel group along the horizontal axis
			// positions them at the same x location.
			//
			// Variable indentation is used to reinforce the level of grouping.

			hGroup.addGroup(layout.createParallelGroup()
					.addComponent(pNameLabel).addComponent(wsLabel));
			hGroup.addGroup(layout
					.createParallelGroup()
					.addComponent(projectName)
					.addComponent(projectPath)
					.addGroup(
							layout.createSequentialGroup().addComponent(jbtOK)
									.addComponent(jbtCancel)));

			layout.setHorizontalGroup(hGroup);

			// The sequential group contains two parallel groups that align
			// the contents along the baseline. The first parallel group
			// contains
			// the first label and text field, and the second parallel group
			// contains
			// the second label and text field. By using a sequential group
			// the labels and text fields are positioned vertically after one
			// another.
			vGroup.addGroup(layout
					.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(pNameLabel).addComponent(projectName));
			vGroup.addGroup(layout
					.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(wsLabel).addComponent(projectPath));
			vGroup.addGroup(layout
					.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbtOK).addComponent(jbtCancel));
			layout.setVerticalGroup(vGroup);

			layout.linkSize(SwingConstants.HORIZONTAL, jbtOK, jbtCancel);

		} catch (Exception ex) {
		}

		jbtOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtOKActionPerformed(evt);
			}
		});

		jbtCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCancelActionPerformed(evt);
			}
		});

	}

	private void initCSourceWizardComponents() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		cSourceLabel = new JLabel("C Source Name:");
		wsLabel = new JLabel("Project:");
		cSourceName = new JTextField();
		projectPath = new JTextField();
		projectPath.setText(IDESupport.getResource("app.workspace"));
		jbtOK = new JButton("OK");
		jbtCancel = new JButton("Cancel");
		jbtBrowse =  new JButton("Browse");
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(cSourceLabel).addComponent(wsLabel));
		hGroup.addGroup(layout
				.createParallelGroup()
				.addComponent(cSourceName)
				.addGroup(layout.createSequentialGroup().addComponent(projectPath)
						.addComponent(jbtBrowse))
				.addGroup(
						layout.createSequentialGroup().addComponent(jbtOK)
								.addComponent(jbtCancel)));

		layout.setHorizontalGroup(hGroup);
		
		vGroup.addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(cSourceLabel).addComponent(cSourceName));
		vGroup.addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(wsLabel).addComponent(projectPath).addComponent(jbtBrowse));
		vGroup.addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(jbtOK).addComponent(jbtCancel));
		layout.setVerticalGroup(vGroup);

		layout.linkSize(SwingConstants.HORIZONTAL, jbtOK, jbtCancel, jbtBrowse);
		
		jbtOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtOKActionPerformed(evt);
			}
		});		
		
		jbtCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCancelActionPerformed(evt);
			}
		});
		
		jbtBrowse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtBrowseActionPerformed(evt);
			}
		});

	}

	private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {

		if (wizard_Ind.equalsIgnoreCase(IDESupport
				.getResource("new.project.ind"))) {

			String projectNameStr = this.projectName.getText();

			if (projectNameStr == null || projectNameStr.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid Project Name", "",
						1);
				return;
			}

			try {

				File f = new File(IDESupport.getResource("app.workspace"),
						projectNameStr);
				if(f.exists()) {
					JOptionPane.showMessageDialog(null, "Project Already Exists. Choose a different name", "",	1); 
					return;	
				} else if(f.mkdir()) {
					File cProjFile = new File(f.getAbsoluteFile(), ".cproject");
					cProjFile.createNewFile();
					this.dispose();
					//mainFrame.repaint();
					mainFrame.refresh();
					return;
					//mainFrame.re
				} else {JOptionPane.showMessageDialog(null, "Invalid Project Name", "",	1); return;}
				
			} catch (Exception ioe) {

			}

		} else if (wizard_Ind.equalsIgnoreCase(IDESupport
				.getResource("new.c.source.ind"))) {
			
			String sourceNameStr = this.cSourceName.getText() + ".c";
			String projectPathStr = this.projectPath.getText();
			
			if(sourceNameStr == null || sourceNameStr.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "C Source Name can't be null or empty", "",
						1);	
				return;
			} else if (projectPathStr == null || projectPathStr.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Project Name Can't be null or empty", "",
						1);	
			} else if (!(new File(projectPathStr).exists())) {
				JOptionPane.showMessageDialog(null, "Invalid Project Path", "",
						1);	
			} else {
				try {
				    File f = new File(projectPathStr, sourceNameStr);
				    if (f.createNewFile()) {
				    	this.dispose();
				    	mainFrame.refresh();
				    	mainFrame.loadSelectedDocument(f);
				    	//mainFrame.repaint();
						//mainFrame.paintAll(getGraphics());
				    } else {
				    	JOptionPane.showMessageDialog(null, "Invalid source file name", "",	1); return;
				    }
				} catch(Exception ex) {}
				
			}

		} else {
		}

	}

	private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}
	
	private void jbtBrowseActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(ProjectSourceWizard.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			projectPath.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}

}
