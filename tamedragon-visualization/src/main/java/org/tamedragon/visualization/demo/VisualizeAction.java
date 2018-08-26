package org.tamedragon.visualization.demo;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.irdata.ValueData;
import org.tamedragon.common.llvmir.semanticanalysis.LLVMSemantic;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.llvmGrammarLexer;
import org.tamedragon.common.llvmir.utils.llvmGrammarParser;

public class VisualizeAction extends AbstractAction {
	private String fileString;
	private JTextComponent textComp;
	LLVMEditor llvmEditor;
	public VisualizeAction(JTextComponent textComp1,LLVMEditor llvmEditor) {
		super("Visualize CFG", new ImageIcon("icons/visualize.gif")); 
		textComp = textComp1;
		this.llvmEditor = llvmEditor;
	}

	public VisualizeAction() {
		super("Visualize CFG", new ImageIcon("icons/visualize.gif")); 
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(textComp == null)
			return;
		fileString = textComp.getText();

		if(fileString.isEmpty())
		{
			JOptionPane.showMessageDialog(textComp,"Please Enter the 'LLVM' or 'C' Input file ");
			return;
		}



		// CFG cfg = Getcfg.getCFG(fileString);

		Module module = getModule(fileString);

		Visualize abc = new Visualize(llvmEditor,true);
		//		textComp.setEnabled(false);
		//	    JDialog dialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);
		abc.show(module.getFunctions().get(0));
	}

	private Module getModule(String llvmSrc) {
		Module module = null;

		if (llvmSrc != null) {
			ANTLRStringStream strStream = new ANTLRStringStream(llvmSrc);
			llvmGrammarLexer lexer = new llvmGrammarLexer(strStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			llvmGrammarParser parser = new llvmGrammarParser(tokens);

			try {
				parser.llvm();
			} catch (RecognitionException e) {
				e.printStackTrace();
			}

			System.out.println("Parsed sucessfully: ");

			List<ValueData> irDataList = parser.getList();
			LLVMSemantic llvmSemantic = new LLVMSemantic("Temp LLVM: ", irDataList);
			module = llvmSemantic.semantic();
		}

		return module;

	}
}
