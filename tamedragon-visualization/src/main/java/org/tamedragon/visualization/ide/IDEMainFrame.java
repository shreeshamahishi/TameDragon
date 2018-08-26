package org.tamedragon.visualization.ide;

/* IDEMainFrame.java
 * 
 * @ 1.0
 * 
 * @ 2010/11/03
 * 
 * @ Sunil Tuppale
 * 
 * @ Copyright (c) 2008 - 2010 Skygraph Technologies Pvt. Ltd.
 * 
 * This is the main frame of the IDE that displays
 * the compilation visualization.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class IDEMainFrame extends JFrame {

	private static final long serialVersionUID = -2093504599241665823L;

	// property names to be read from the app_en.properties file
	private static final String FILEMENU_TITLE = "app.frame.menus.file.title",
			FILEMENU_NEW = "app.frame.menus.file.new",
			PROJECT_NEW = "app.frame.menu.new.project",
			NEW_PROJECT_IND = "new.project.ind",
			NEW_C_SOURCE_IND = "new.c.source.ind",
			C_SOURCE_NEW = "app.frame.menu.new.cSource",
			FILEMENU_SAVE = "app.frame.menus.file.save",
			FILEMENU_EXIT = "app.frame.menus.file.exit",

			EDITMENU_TITLE = "app.frame.menus.edit.title",
			EDITMENU_CUT = "app.frame.menus.edit.cut",
			EDITMENU_COPY = "app.frame.menus.edit.copy",
	        EDITMENU_PASTE = "app.frame.menus.edit.paste";

	// property names of toolbar images 
	private static final String TOOLBAR_NEW_ICON = "app.frame.toolbar.newIcon",
			TOOLBAR_OPEN_ICON = "app.frame.toolbar.openIcon",
			TOOLBAR_SAVE_ICON = "app.frame.toolbar.saveIcon",
			TOOLBAR_CUT_ICON = "app.frame.toolbar.cutIcon",
			TOOLBAR_COPY_ICON = "app.frame.toolbar.copyIcon",
			TOOLBAR_PASTE_ICON = "app.frame.toolbar.pasteIcon";
	
	// various properties of the IDE such as dimensions, navigation panes, etc
	static final int windowX = 100, windowY = 25, windowW = 850,
			windowH = 650;
	private static final Dimension navigationPaneSize = new Dimension(100, 0);

	private JTabbedPane navigationPane = null;
	private JTabbedPane editorAndVisualisationPane = null;

	private JSplitPane spVertical, spHorizontal;
	private JPanel navigationPanel = new JPanel(), rightPanel = new JPanel(),
			projectsPanel = new JPanel(), functionsPanel = new JPanel(),
			visualisationPanel = new JPanel();
	private JDesktopPane editorPanel;
	private JInternalFrame closeupPanel = new ConsoleFrame();
	private String defaultDir = null;
	
	private DirectoryTree directoryTree;
	private FunctionTree functionTree;

	private ArrayList<JTextPane> editors;
	private ArrayList<JInternalFrame> frames;
	
	private static final int OFFSET_INDEX = 0;
	
	static{
		try {
			Class.forName("com.compilervision.visualization.ide.IDESupport");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Main method that invokes the IDE frame using a Swing worker thread
	public static void main(String args[]) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					System.out.println("Error setting Motif LAF: " + e);
				}
				createAndShowGUI();
			}
		});
	}

	public void clearStatusArea() {
		// statusPanel.setText("");
	}

	// Initialising components of the IDE frame
	public IDEMainFrame() {
		editorPanel = new JDesktopPane();
		editorPanel.setBackground(visualisationPanel.getBackground());
		editors = new ArrayList<JTextPane>();
		editors.clear();
		frames = new ArrayList<JInternalFrame>();
		frames.clear();
		setDefaultDir();
		createNavigationPane();
		createEditorAndVisualizationPane();
		initializeNavigationPanel();
		initializeFunctionsPanel();
		populateRightPanel();
		populateContentPane();
	}
	
	private void setDefaultDir() {	
		if (IDESupport.getResource("workspace.set") != null && IDESupport.getResource("workspace.set").equalsIgnoreCase("yes")) {
		    this.defaultDir = IDESupport.getResource("app.workspace");
		} else {
			this.defaultDir = getDefaultDir();
		}
	}

	private void createNavigationPane() {
		navigationPane = new JTabbedPane();
		navigationPane.setTabPlacement(JTabbedPane.TOP);
		navigationPane.setPreferredSize(navigationPaneSize);
		makeNavigationTabs();
	}

	private void makeNavigationTabs() {
		navigationPane.addTab(IDESupport.getResource("app.projects.tab"),
				projectsPanel);
		navigationPane.addTab(IDESupport.getResource("app.functions.tab"),
				functionsPanel);
	}

	private void createEditorAndVisualizationPane() {
		editorAndVisualisationPane = new JTabbedPane();
		editorAndVisualisationPane.removeAll();
		editorAndVisualisationPane.setTabPlacement(JTabbedPane.TOP);
		editorAndVisualisationPane.setPreferredSize(navigationPaneSize);
		makeEdiorAndVisualisationTabs();
	}

	private void makeEdiorAndVisualisationTabs() {
		editorAndVisualisationPane.addTab(
				IDESupport.getResource("app.editor.tab"), editorPanel);
		editorAndVisualisationPane.addTab(
				IDESupport.getResource("app.visualisation.tab"),
				visualisationPanel);
	}

	private void initializeProjectsPanel() {
		projectsPanel.setLayout(new BorderLayout());

		projectsPanel
				.add(new JLabel(IDESupport.getResource("app.projects.tab")));
		directoryTree = new DirectoryTree(this, this.defaultDir);
		projectsPanel.add(directoryTree, "Center");
		
	}
	
	public void setDefaultDirFromWSDialog(String defaultDir) {
		this.defaultDir = defaultDir;
	}
	
	public String getDefaultDir() {
		return this.defaultDir;
	}

	private void initializeFunctionsPanel() {
		functionsPanel.setLayout(new BorderLayout());
		functionsPanel.add(new JLabel(IDESupport
				.getResource("app.functions.tab")));
		functionTree = new FunctionTree();
		functionsPanel.add(functionTree, "Center");
	}

	private void populateRightPanel() {
		closeupPanel.setVisible(true);
		spHorizontal = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				new JScrollPane(editorAndVisualisationPane), new JScrollPane(
						closeupPanel));
		rightPanel.setLayout(new BorderLayout());
		spVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				navigationPanel, rightPanel);

		spHorizontal.setDividerLocation(450); // 150
		rightPanel.add(spHorizontal, BorderLayout.CENTER);
		spVertical.setDividerLocation(200); // 200
	}

	private void initializeNavigationPanel() {

		navigationPanel.setLayout(new BorderLayout());
		navigationPanel.add(new JScrollPane(navigationPane),
				BorderLayout.CENTER);
	}

	private void populateContentPane() {
		spVertical.setOneTouchExpandable(true);
		
		Container contentPane = getContentPane();
		contentPane.add(spVertical, BorderLayout.CENTER);
		contentPane.add(makeToolbar(), BorderLayout.NORTH);
		setJMenuBar(makeMenubar());
	}

	private JComponent makeToolbar() {
		JToolBar toolbar = new JToolBar();
//		JButton newButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_NEW_ICON)));
		JButton newButton = new JButton("New");
		newButton.setToolTipText(IDESupport.getResource("main.tooltip.new.text"));
//		JButton openButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_OPEN_ICON)));
		JButton openButton = new JButton("Open");
		openButton.setToolTipText(IDESupport.getResource("main.tooltip.open.text"));
//		JButton saveButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_SAVE_ICON)));
		JButton saveButton = new JButton("Save");
		saveButton.setToolTipText(IDESupport.getResource("main.tooltip.save.text"));
//		JButton cutButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_CUT_ICON)));
		JButton cutButton = new JButton("Cut");
		cutButton.setToolTipText(IDESupport.getResource("main.tooltip.cut.text"));
//		JButton copyButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_COPY_ICON)));		
		JButton copyButton = new JButton("Copy");
		copyButton.setToolTipText(IDESupport.getResource("main.tooltip.copy.text"));
		
//		JButton pasteButton = new JButton(new ImageIcon(
//				IDESupport.getResource(TOOLBAR_PASTE_ICON)));
		JButton pasteButton = new JButton("Cut");
		pasteButton.setToolTipText(IDESupport.getResource("main.tooltip.paste.text"));
		
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewProjectORSourceFile(IDESupport.getResource(NEW_PROJECT_IND));
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDocument();
			}
		});
		
		cutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutText();
			}
		});
		
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyText();
			}
		});
		
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasteText();
			}
		});

		toolbar.add(newButton);
		toolbar.add(openButton);
		toolbar.add(saveButton);
		toolbar.addSeparator();
		toolbar.add(cutButton);
		toolbar.add(copyButton);
		toolbar.add(pasteButton);
		toolbar.addSeparator();
		
		openButton.addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser("./log");
			private boolean initialized = false;

			public void actionPerformed(ActionEvent e) {
				if (!initialized) {
					setFilter();
					initialized = true;
				}

				int state = chooser.showOpenDialog(null);

				if (state != JFileChooser.APPROVE_OPTION)
					return;

				System.out.println(chooser.getSelectedFile());
			}

			private void setFilter() {
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
					public boolean accept(File f) {
						return f.getName().endsWith("xml") || f.isDirectory();
					}

					public String getDescription() {
						return "XML files and directories only";
					}
				});
			}
		});
		return toolbar;
	}

	private JMenuBar makeMenubar() {
		JMenuBar menubar = new JMenuBar();
		menubar.add(makeFileMenu());
		menubar.add(makeEditMenu());
		menubar.add(makeViewMenu());
		return menubar;
	}

	private JMenu makeFileMenu() {
		JMenu fileMenu = new JMenu(IDESupport.getResource(FILEMENU_TITLE));
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenu newItem = new JMenu(IDESupport.getResource(FILEMENU_NEW));
		JMenuItem saveItem = new JMenuItem(
				IDESupport.getResource(FILEMENU_SAVE));
		saveItem.setMnemonic(KeyEvent.VK_S);

		JMenuItem exitItem = new JMenuItem(
				IDESupport.getResource(FILEMENU_EXIT));
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		JMenuItem newProjectItem = new JMenuItem(IDESupport.getResource(PROJECT_NEW));
		JMenuItem newCSourceItem = new JMenuItem(IDESupport.getResource(C_SOURCE_NEW));
		newItem.add(newProjectItem);
		newItem.add(newCSourceItem);

		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		newProjectItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				createNewProjectORSourceFile(IDESupport.getResource(NEW_PROJECT_IND));
			}
		});
		
		newCSourceItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				createNewProjectORSourceFile(IDESupport.getResource(NEW_C_SOURCE_IND));
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saveDocument();
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		return fileMenu;
	}

	private JMenu makeEditMenu() {
		JMenu editMenu = new JMenu(IDESupport.getResource(EDITMENU_TITLE));
		editMenu.setMnemonic(KeyEvent.VK_E);
		JMenuItem _copy = new JMenuItem(IDESupport.getResource(EDITMENU_COPY));
		_copy.setMnemonic(KeyEvent.VK_COPY);
		JMenuItem _cut = new JMenuItem(IDESupport.getResource(EDITMENU_CUT));
		_cut.setMnemonic(KeyEvent.VK_CUT);
		JMenuItem _paste = new JMenuItem(IDESupport.getResource(EDITMENU_PASTE));
		_paste.setMnemonic(KeyEvent.VK_PASTE);
		
		_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    copyText();	
			}
		});
		
		_cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    cutText();	
			}
		});
		
		_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    pasteText();	
			}
		});
		
		editMenu.add(_copy);
		editMenu.add(_cut);
		editMenu.add(_paste);
				
		return editMenu;
	}
	
	private JMenu makeViewMenu() {
		JMenu viewMenu = new JMenu("View");
		JMenuItem _consoleView = new JMenuItem("Console");
		JMenuItem _refresh = new JMenuItem("Refresh");
		JMenuItem _changeWS = new JMenuItem("Change Workspace");
		
		_consoleView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    closeupPanel.show();	
			}
		});
		
		_changeWS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				WorkSpaceDialog wsDialog = new WorkSpaceDialog(new IDEMainFrame(), true);
				wsDialog.getOwner().dispose();
				wsDialog.setVisible(true);
				wsDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
			}
		});
		
		viewMenu.add(_consoleView);
		viewMenu.add(_refresh);
		viewMenu.add(_changeWS);
		
		return viewMenu;		
	}

	public void loadSelectedDocument(File file) {
		JTextPane _resultArea = null;
		RTFEditorKit kit = null;
		String fileName = file.getParent() + "\\" + file.getName();
		//System.out.println("File Name is " + fileName);

		int tabIndex = editorAndVisualisationPane.indexOfTab(fileName);

		if (tabIndex == -1) {

			try {
				_resultArea = createTextPane();

				kit = new RTFEditorKit();
				_resultArea.setEditorKit(kit);

				JScrollPane scrollingArea = new JScrollPane(_resultArea);
				_resultArea.setBounds(scrollingArea.getBounds());
				//System.out.println("The absolute path is " + file.getAbsolutePath() + " " + );
				_resultArea.setName(file.getAbsolutePath());
				
				editorAndVisualisationPane.remove(editorPanel);
				
				editorAndVisualisationPane.addTab(fileName, scrollingArea);
				int i = editorAndVisualisationPane.indexOfComponent(scrollingArea);				

				StyledDocument doc = _resultArea.getStyledDocument();
				addStylesToDocument(doc);

				FileReader fin = new FileReader(file);
				BufferedReader br = new BufferedReader(fin);
				char buffer[] = new char[4096];
				int len;

				while ((len = br.read(buffer, 0, buffer.length)) != -1) {
					// Insert into pane
					doc.insertString(doc.getLength(),
							new String(buffer, 0, len), doc.getStyle("regular"));
				}

				validate();
				br.close();
				fin.close();
				editors.add(i-1, _resultArea);
				System.out.println("Just before adding " + file.getName());
				editorAndVisualisationPane.setTabComponentAt(i,
						new ButtonTabComponent(file.getName(),
								editorAndVisualisationPane, editors));
				editorAndVisualisationPane.setSelectedIndex(i);

			} catch (Exception ex) {
                ex.printStackTrace(System.out);
			}
		} else {
			editorAndVisualisationPane.setSelectedIndex(tabIndex);
		}

	}

	private void saveDocument() {
		// Determine the currently active pane

        JTextPane jedp = editors.get(editorAndVisualisationPane.getSelectedIndex() - 1);

		// Save file
		File myFile = new File(jedp.getName());
		//System.out.println("In Save the name is: " + myFile.getName());

		FileWriter writer = null;
		int _docLength = jedp.getDocument().getLength();
		String textString = null;
		try {
			writer = new FileWriter(myFile);
			textString = jedp.getDocument().getText(OFFSET_INDEX, _docLength);
			writer.write(textString, OFFSET_INDEX, _docLength);
		} catch (IOException ex) {
			System.out.println("IOException: ");
		} catch (BadLocationException ex) {
			System.out.println("Bad Location Exception: ");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException x) {
				}
			}
		}

	}
	
	private void copyText() {
		JTextPane jedp = editors.get(editorAndVisualisationPane
				.getSelectedIndex() - 1);

		jedp.copy();
	}
	
	private void cutText() {
		JTextPane jedp = editors.get(editorAndVisualisationPane
				.getSelectedIndex() - 1);

		jedp.cut();
	}
    
	private void pasteText() {
		JTextPane jedp = editors.get(editorAndVisualisationPane
				.getSelectedIndex() - 1);

		jedp.paste();
	}

	private JTextPane createTextPane() {
		JTextPane textPane = new JTextPane();
		StyledDocument doc = textPane.getStyledDocument();
		addStylesToDocument(doc);
		// the file reading code was originally here
		return textPane;
	}

	protected void addStylesToDocument(StyledDocument doc) {
		// Initialize some styles.
		Style def = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "SansSerif");

		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);

		s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);

		s = doc.addStyle("small", regular);
		StyleConstants.setFontSize(s, 10);

		s = doc.addStyle("large", regular);
		StyleConstants.setFontSize(s, 16);

		s = doc.addStyle("icon", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	}
	
	// Main method that displays the GUI after all the initializations.
	// If the workspace has been set by the user, then all the projects
	// are loaded into the JTree else the 'Select Workspace' dialog shows 
	// to allow the user to select a workspace
	private static void createAndShowGUI() {
		IDEMainFrame ideMainFrame = new IDEMainFrame();
		//if (IDESupport.getResource("workspace.set") != null && IDESupport.getResource("workspace.set").equalsIgnoreCase("yes")) {
			ideMainFrame.initializeProjectsPanel();
			IDESupport.launch(ideMainFrame,
					IDESupport.getResource("app.frame.title"), windowX,
					windowY, windowW, windowH);	
//		} else {
//			WorkSpaceDialog wsDialog = new WorkSpaceDialog(ideMainFrame, true);
//			wsDialog.setVisible(true);
//			wsDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		}
		ideMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
	}
	
	public void initiateProjectsPanelFromUserSelection() {
		initializeProjectsPanel();
	}
	
	public void createNewProjectORSourceFile(String wizardInd) {
		ProjectSourceWizard psWizard = new ProjectSourceWizard(this, true, wizardInd);
		psWizard.setVisible(true);
		psWizard.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void refresh() {
		//directoryTree.refresh();
		//initializeProjectsPanel();
	}
	
	public void closeDeletedDocument(File file) {		
		int tabIndex = editorAndVisualisationPane.indexOfTab(file.getAbsolutePath());
		if(tabIndex != -1) {
			editorAndVisualisationPane.remove(tabIndex);
		    editors.remove(tabIndex - 1);
		}		
	}
}
