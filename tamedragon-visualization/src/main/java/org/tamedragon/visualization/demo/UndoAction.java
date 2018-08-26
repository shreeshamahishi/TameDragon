package org.tamedragon.visualization.demo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class UndoAction extends AbstractAction {
	 public UndoAction() { 
	   super("Undo", new ImageIcon("icons/open1.gif")); 
	 }
	 UndoManager undoManager = new UndoManager();

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
	        if (undoManager.canUndo()) {
	            undoManager.undo();
	        }
	    } catch (CannotUndoException exp) {
	        exp.printStackTrace();
	    }
	}}
