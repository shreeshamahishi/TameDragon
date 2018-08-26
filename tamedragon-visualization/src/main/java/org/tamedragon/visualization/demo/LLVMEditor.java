/**
 * 
 */
package org.tamedragon.visualization.demo;
//LLVMEditor.java
//An example showing several DefaultEditorKit features. This class is designed
//to be easily extended for additional functionality.
//
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.Hashtable;

public class LLVMEditor extends JFrame {

	private Action openAction = new OpenAction();
	private Action saveAction = new SaveAction();
	private Action undoAction = new UndoAction();
	//private Action redoAction = new redoAction();
	private Action visualizeAction = new VisualizeAction();

	private JTextComponent textComp;
	private Hashtable actionHash = new Hashtable();

	public static void main(String[] args) {
		LLVMEditor editor = new LLVMEditor();
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editor.setVisible(true);
	}

	// Create an editor.
	public LLVMEditor() {
		super("LLVM Editor");
		textComp = createTextComponent();
		makeActionsPretty();

		Container content = getContentPane();
		// content.add(textComp, BorderLayout.CENTER);
		content.add(createToolBar(), BorderLayout.NORTH);
		setJMenuBar(createMenuBar());
		setSize(700, 500);
		JScrollPane scrollPane = new JScrollPane(textComp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		content.add(scrollPane, BorderLayout.CENTER);
	}

	// Create the JTextComponent subclass.
	protected JTextComponent createTextComponent() {
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setAutoscrolls(true);
		//Added top,Bottom Left and Write Margine
		ta.setMargin(new Insets(10,30,100,10));
		return ta;
	}

	// Add icons and friendly names to actions we care about.
	protected void makeActionsPretty() {
		
		String rootPath = "icons";
		ClassLoader classLoader = getClass().getClassLoader();
		
		Action a;
		a = getOpenAction();
		String fullFilePath = rootPath + "/" + "document_open.gif";
		a.putValue(Action.SMALL_ICON, new ImageIcon(new File(classLoader.getResource(fullFilePath).getFile()).getAbsolutePath()));
		a.putValue(Action.NAME, "Open");

		fullFilePath = rootPath + "/" + "cut.gif";
		a = textComp.getActionMap().get(DefaultEditorKit.cutAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon(new File(classLoader.getResource(fullFilePath).getFile()).getAbsolutePath()));
		a.putValue(Action.NAME, "Cut");

		fullFilePath = rootPath + "/" + "copy.gif";
		a = textComp.getActionMap().get(DefaultEditorKit.copyAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon(new File(classLoader.getResource(fullFilePath).getFile()).getAbsolutePath()));
		a.putValue(Action.NAME, "Copy");

		fullFilePath = rootPath + "/" + "paste.gif";
		a = textComp.getActionMap().get(DefaultEditorKit.pasteAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon(new File(classLoader.getResource(fullFilePath).getFile()).getAbsolutePath()));
		a.putValue(Action.NAME, "Paste");

		fullFilePath = rootPath + "/" + "paste.gif";
		a = textComp.getActionMap().get(getUndoAction());
		a.putValue(Action.SMALL_ICON, new ImageIcon(new File(classLoader.getResource(fullFilePath).getFile()).getAbsolutePath()));
		a.putValue(Action.NAME, "Undo");

		InputMap im = textComp.getInputMap(JComponent.WHEN_FOCUSED);
		ActionMap am = textComp.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");


		a = textComp.getActionMap().get(DefaultEditorKit.selectAllAction);
		a.putValue(Action.NAME, "Select All");
	}
	
	// Create a simple JToolBar with some buttons.
	protected JToolBar createToolBar() {
		JToolBar bar = new JToolBar();

		// Add simple actions for opening & saving.
		bar.add(getOpenAction()).setText("");
		bar.add(getSaveAction()).setText("Save");
		bar.addSeparator();

		// Add cut/copy/paste buttons.
		bar.add(textComp.getActionMap().get(DefaultEditorKit.cutAction)).setText("");
		bar.add(textComp.getActionMap().get(
				DefaultEditorKit.copyAction)).setText("");
		bar.add(textComp.getActionMap().get(
				DefaultEditorKit.pasteAction)).setText("");
		bar.add(getVisualizeAction(true));
		return bar;
	}

	// Create a JMenuBar with file & edit menus.
	protected JMenuBar createMenuBar() {
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu visualize = new JMenu("Visualize");
		JMenu help = new JMenu("Help");
		//Add Menu To the Menubar
		menubar.add(file);
		menubar.add(edit);
		menubar.add(visualize);
		menubar.add(help);

		file.add(getOpenAction());
		file.add(getSaveAction());
		file.add(new ExitAction());

		edit.add(textComp.getActionMap().get(DefaultEditorKit.cutAction));
		edit.add(textComp.getActionMap().get(DefaultEditorKit.copyAction));
		edit.add(textComp.getActionMap().get(DefaultEditorKit.pasteAction));
		edit.add(textComp.getActionMap().get(DefaultEditorKit.selectAllAction));
		edit.add(getUndoAction());
		edit.add(textComp.getActionMap().get(DefaultEditorKit.copyAction));

		visualize.add(getVisualizeAction(true));
		return menubar;
	}

	// Subclass can override to use a different open action.
	protected Action getOpenAction() { return openAction; }

	// Subclass can override to use a different save action.
	protected Action getSaveAction() { return saveAction; }

	//Subclass can override to use a different save action.
	protected Action getUndoAction() {
		javax.swing.text.Document doc = textComp.getDocument();
		final UndoManager undoManager = new UndoManager();
		UndoableEditListener undobleEditListner = new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Add edit");
				undoManager.addEdit(e.getEdit());
			}
		};;;
		doc.addUndoableEditListener(undobleEditListner);
		return undoAction; }



	protected JTextComponent getTextComponent() { return textComp; }

	// ********** ACTION INNER CLASSES ********** //

	protected Action getVisualizeAction(boolean a) { 
		String fileString = null;
		//	if(a)
		fileString	= textComp.getText();
		visualizeAction = new VisualizeAction(textComp,this);
		return visualizeAction; }

	// A very simple exit action
	public class ExitAction extends AbstractAction {
		public ExitAction() { super("Exit"); }
		public void actionPerformed(ActionEvent ev) { System.exit(0); }
	}

	// An action that opens an existing file
	class OpenAction extends AbstractAction {
		public OpenAction() { 
			super("Open", new ImageIcon("icons/open.gif")); 
		}


		// Query user for a filename and attempt to open and read the file into the
		// text component.
		public void actionPerformed(ActionEvent ev) {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(LLVMEditor.this) !=
				JFileChooser.APPROVE_OPTION)
				return;
			File file = chooser.getSelectedFile();
			if (file == null)
				return;

			FileReader reader = null;
			try {
				reader = new FileReader(file);
				textComp.read(reader, null);
			}
			catch (IOException ex) {
				JOptionPane.showMessageDialog(LLVMEditor.this,
						"File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException x) {}
				}
			}
		}
	}

	// An action that saves the document to a file
	class SaveAction extends AbstractAction {
		public SaveAction() {
			super("Save", new ImageIcon("icons/save.gif"));
		}

		// Query user for a filename and attempt to open and write the text
		// component’s content to the file.
		public void actionPerformed(ActionEvent ev) {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showSaveDialog(LLVMEditor.this) !=
				JFileChooser.APPROVE_OPTION)
				return;
			File file = chooser.getSelectedFile();
			if (file == null)
				return;

			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
				textComp.write(writer);
			}
			catch (IOException ex) {
				JOptionPane.showMessageDialog(LLVMEditor.this,
						"File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException x) {}
				}
			}
		}
	}
}

