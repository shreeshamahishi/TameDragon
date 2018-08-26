package org.tamedragon.visualization.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.antlr.runtime.RecognitionException;

import org.tamedragon.common.Label;
import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.types.Function;

public class Test {

	private static Dimension DEFAULT_SIZE = new Dimension(1000, 700);
	static Function function;
	static File file = null;

	public static void main(String [] args) {
		final Test test = new Test();
		//		final Visualize abc = new Visualize();

		//		CFG cfg = Getcfg.getCFG();

		//			abc.show(cfg);

		//	 JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame = new JFrame("JTextArea Test");
		frame.setLayout(new BorderLayout());
		//	    frame.setLayout(new CardLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);

		frame.setSize(DEFAULT_SIZE );
		final JTextArea textArea = new JTextArea();

		JPanel jPanel = new JPanel(new FlowLayout());
		frame.add(jPanel);
		textArea.setAutoscrolls(true);
		//	    textArea2.setPreferredSize(new Dimension(500, 500));
		textArea.setEditable(true);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setLineWrap(true);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		jPanel.add(scrollPane);

		/**
		 * Add Button To upload file
		 */
		final JCheckBox checkBox = new JCheckBox("Or upload file");
		jPanel.add(checkBox);
		final JButton uploadFileButton = new JButton("Upload file");

		final JButton visualizeButton = new JButton("Visualize CFG");
		ActionListener uploadFileSelect = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox.isSelected())
				{
					uploadFileButton.setVisible(true);
					textArea.setVisible(false);
				}
				else
				{
					uploadFileButton.setVisible(false);
					textArea.setVisible(true);

				}
			}
		};;;
		checkBox.addActionListener(uploadFileSelect );

		final JFileChooser fileChooser = new JFileChooser();

		ActionListener uploadClick = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(uploadFileButton);

			}};
			uploadFileButton.addActionListener(uploadClick);
			ActionListener visualizeClick = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String textStr = textArea.getText();
					file = fileChooser.getSelectedFile();
					if(checkBox.isSelected()){
						if(file == null){
							JOptionPane.showMessageDialog(frame,"Please first Select the input file ");
							return;
						}
						else
							function = Visualize.getFunction(file);

					}
					else if(textStr != null){
						try {
							function = Visualize.getFunction(textStr);
						} catch (RecognitionException e1) {
							e1.printStackTrace();
						}
					}

					Visualize abc = new Visualize(frame, false);
					abc.show(function);
				}
			};;;
			visualizeButton.addActionListener(visualizeClick );
			jPanel.add(uploadFileButton);
			uploadFileButton.setVisible(false);
			jPanel.add(visualizeButton);
			frame.setVisible(true);

	}
}
