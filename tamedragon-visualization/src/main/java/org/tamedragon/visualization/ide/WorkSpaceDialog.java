package org.tamedragon.visualization.ide;

/* WorkSpaceDialog.java
 * 
 * @ 1.0
 * 
 * @ 2010/11/29
 * 
 * @ Sunil Tuppale
 * 
 * @ Copyright (c) 2008 - 2010 Skygraph Technologies Pvt. Ltd.
 * 
 * This is the dialog box that shows at startup of the application
 * if the workspace has not been set. At the start of the application,
 * this dialog appears and asks the user to select a workspace.
 * The mainframe then loads all the folders in that workspace
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WorkSpaceDialog extends JDialog {

	// These fields define the properties of the dialog box
	private static final long serialVersionUID = -3194262961596298374L;
	private static final Dimension dimension = new Dimension(450, 150);
	private String inputString = "";
	private IDEMainFrame mainFrame = null;
	private String actionString = null;
	private JLabel jLabel1 = null;
	private JTextField wsPath = null;
	private JCheckBox defaultWSSet = null;
	private JButton jbtBrowse = null, jbtOK = null, jbtCancel = null;
	private JFileChooser fc = null;

	public WorkSpaceDialog(JFrame frame, boolean modal) {
		mainFrame = (IDEMainFrame) frame;
		initComponents();
		pack();
		setLocationRelativeTo(frame);
		setSize(dimension);
		setVisible(true);
	}

	// Initializing the components in the dialog box and also using the Group
	// Layout to lay all the main controls. Also add listeners to the buttons
	private void initComponents() {
		System.out.println("The WS in WS Dialog is =====> "
				+ IDESupport.getResource("app.workspace"));
		setTitle(IDESupport.getResource("workspace.dialog.title"));
		setLayout(new BorderLayout());
		jLabel1 = new JLabel(IDESupport.getResource("workspace.label.text"));

		wsPath = new JTextField();
		wsPath.setText(IDESupport.getResource("app.workspace"));
		BoundedRangeModel model = wsPath.getHorizontalVisibility();
		int extent = model.getExtent();
		wsPath.setScrollOffset(extent);

		jbtBrowse = new JButton(
				IDESupport.getResource("workspace.browseButton.text"));
		defaultWSSet = new JCheckBox(
				IDESupport.getResource("workspace.checkBox.text"));
		jbtOK = new JButton(IDESupport.getResource("workspace.okButton.text"));
		jbtCancel = new JButton(
				IDESupport.getResource("workspace.cancelButton.text"));
		defaultWSSet.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		try {
			layout.setHorizontalGroup(layout
					.createSequentialGroup()
					.addComponent(jLabel1)
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.LEADING)
									.addComponent(wsPath)
									.addComponent(defaultWSSet)
									.addGroup(
											layout.createSequentialGroup()
													.addComponent(jbtOK)
													.addComponent(jbtCancel)))
					.addComponent(jbtBrowse));

			layout.setVerticalGroup(layout
					.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(jLabel1).addComponent(wsPath)
									.addComponent(jbtBrowse))
					.addComponent(defaultWSSet)
					.addGroup(
							layout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(jbtOK)
									.addComponent(jbtCancel)));
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
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

		jbtBrowse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtBrowseActionPerformed(evt);
			}
		});

	}

	// When the user clicks the OK button, check to see if the 'default set'
	// checkbox is clicked
	// If it is, then change the workspace.set property to yes in the properties
	// file and also
	// verify that the path chosen is a valid one. If the path is not valid,
	// show a message saying
	// invalid directory.

	private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {
		Vector<PropertiesPair> propVector = new Vector<PropertiesPair>();
		this.actionString = IDESupport.getResource("workspace.okButton.text");
		String wsDir = this.wsPath.getText();
		File dir = new File(wsDir);
		if (this.defaultWSSet.isSelected()) {
			if (dir.exists() || dir.isDirectory()) {
				propVector.add(new PropertiesPair("workspace.set", "yes"));
				propVector.add(new PropertiesPair("app.workspace", dir
						.getAbsolutePath()));
			} else {
				JOptionPane.showMessageDialog(this, IDESupport
						.getResource("workspace.invalidDirectory.Message"), "",
						1);
				wsPath.setFocusCycleRoot(true);
				return;
			}
		} else {
			if (dir.exists() || dir.isDirectory()) {
				propVector.add(new PropertiesPair("app.workspace", dir
						.getAbsolutePath()));
			} else {
				JOptionPane.showMessageDialog(this, IDESupport
						.getResource("workspace.invalidDirectory.Message"), "",
						1);
				wsPath.setFocusCycleRoot(true);
				return;
			}
		}

		// Set the workspace path from the user selection
		mainFrame.setDefaultDirFromWSDialog(dir.getPath());

		// Call the method to initiate the laying of the projects panel
		mainFrame.initiateProjectsPanelFromUserSelection();

		WriteAppProperties wrp = new WriteAppProperties(new File(
				IDESupport.getResource("properties.path")), propVector);
		wrp.editProperties();

		// launch the application with the path entered in this dialog box
		IDESupport.launch(mainFrame, IDESupport.getResource("app.frame.title"),
				IDEMainFrame.windowX, IDEMainFrame.windowY,
				IDEMainFrame.windowW, IDEMainFrame.windowH);

		this.dispose();
	}

	// Cancel action closes the dialog and the mainframe
	private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
		mainFrame.dispose();
	}

	// Browse action uses a file chooser
	private void jbtBrowseActionPerformed(java.awt.event.ActionEvent evt) {
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(WorkSpaceDialog.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			wsPath.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}

	public String getInputText() {
		return inputString;
	}

	public String getActionString() {
		return actionString;
	}
}
