package org.tamedragon.compilers.canon;

import org.tamedragon.common.Label;
import org.tamedragon.assemblyabstractions.constructs.*;

import java.util.HashSet;
import java.util.Vector;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;

//import org.tamedragon.compiler.tiger.Debug;

public class Linearizer 
{
	private AssemStmList canonizedRepresentation;
	private Vector<AssemStmList> blocks;   // Basic blocks
	private LinkedHashMap<Label, AssemStmList> labelVsBasicBlocks;
	private AssemStmList tracedStms;
	private String levelName;

	private Label doneLabel;

	public Linearizer(String levelName)
	{
		this.levelName = levelName;
	}

	private AssemStmList linear(AssemSeq s, AssemStmList l) 
	{
		if(s.getLeftStm() == null)
		{
			return linear(s.getRightStm(), l);
		}
		else
			return linear(s.getLeftStm(),linear(s.getRightStm(),l));
	}

	private AssemStmList linear(AssemStm s, AssemStmList l)
	{
		if(s == null && l != null)
		{
			// Ignore statements that are nulls
			return linear(l.getStm(), l.getStmList());
		}
		else if (s instanceof AssemSeq) return linear((AssemSeq)s, l);
		else return new AssemStmList(s,l);
	}

	public void linearize(AssemStm s)
	{
		//return linear(s.canonize(), null);
		canonizedRepresentation = linear(s.canonize(), null);
	}

	public void linearize(AssemType assemType)
	{	
		if(assemType instanceof AssemStm)
			//canonizedRepresentation =  linearize((MipsAssemStm) assemType);
			linearize((AssemStm) assemType);
		else if(assemType instanceof AssemSeqExp)
		{
			AssemSeqExp seqExp = (AssemSeqExp) assemType;
			//canonizedRepresentation =  linearize(seqExp.translateToStatement());
			linearize(seqExp.translateToStatement());
		}

		//return canonizedRepresentation;
	}

	public AssemStmList getCanonized()
	{
		return canonizedRepresentation;
	}

	public void generateBasicBlocks()
	{		
		blocks = new Vector<AssemStmList>();		
		doneLabel = new Label("");   // Create a new "done" label

		AssemStmList currentBlock = null;
		AssemStmList prevStmList = null;
		AssemStm finalStm = null;

		for(AssemStmList mainList = canonizedRepresentation; mainList != null; 
		mainList = mainList.getStmList())
		{
			AssemStm stm = mainList.getStm();
			if(stm instanceof AssemLabel)
			{
				AssemLabel assemLbl = (AssemLabel) stm;
				if(!(finalStm instanceof AssemJump || finalStm instanceof AssemCJump))
				{
					// End the previous block with a jump to the label at the end of the current block
					if(currentBlock != null)
					{
						AssemJump newJump = new AssemJump(
								new AssemName(assemLbl.getLabel()));
						prevStmList.setStmList(new AssemStmList(newJump, null));
						blocks.addElement(currentBlock);
					}
				}
				// Start the new block
				prevStmList = new AssemStmList(stm, null);
				currentBlock = prevStmList;			
			}
			else if(stm instanceof AssemJump 
					|| stm instanceof AssemCJump)
			{
				if(prevStmList == null)
				{
					// Stick a label to the beginning of the block
					AssemLabel assemLabel = new AssemLabel(new Label(""));
					prevStmList = new AssemStmList(assemLabel, null);
					currentBlock = prevStmList;
				}
				prevStmList.setStmList(new AssemStmList(stm, null));

				//	End the current block
				blocks.addElement(currentBlock);
				currentBlock = null;
				prevStmList = null;
			}
			else 
			{
				AssemStmList newlist = new AssemStmList(stm, null);		
				if(currentBlock == null)
				{
					// This is the beginning; start a new block, create a new label 
					// based on the function name and stick it in the front
					AssemLabel assemLbl = new AssemLabel(new Label(levelName));
					currentBlock = new AssemStmList(assemLbl, newlist);
				}
				else
					prevStmList.setStmList(newlist);

				prevStmList = newlist;
			}
			finalStm = stm;   // Store this to use outside this loop
		}


		if(!(finalStm instanceof AssemLabel)) {
			AssemJump lastJump = new AssemJump(new AssemName(doneLabel));			
			prevStmList.setStmList(new AssemStmList(lastJump,null));
		}
		
		// Add the last block
		blocks.addElement(currentBlock);

	}

	public void generateTrace() {		
		labelVsBasicBlocks = new LinkedHashMap<Label, AssemStmList>();

		// Populate the linked hash map with labels against the corresponding basic block
		int numBlocks = blocks.size();
		for(int i = 0; i < numBlocks; i++) {
			AssemStmList stmList = (AssemStmList) blocks.elementAt(i);
			Label startLbl = ((AssemLabel)stmList.getStm()).getLabel();
			labelVsBasicBlocks.put(startLbl, stmList);
		}

		AssemStmList prevLastList = null;

		Set<Label> keys = labelVsBasicBlocks.keySet();
		Iterator<Label> iter = keys.iterator();

		while(iter.hasNext()) {
			Label lbl = iter.next();
			AssemStmList blk = labelVsBasicBlocks.get(lbl);				
			AssemStmList newLastList = trace(blk);

			if(prevLastList == null)  {
				// Entered loop for the first time
				tracedStms = blk;
				prevLastList = newLastList;					
			}
			else {
				prevLastList.setStmList(blk);
				prevLastList = newLastList;
			}

			labelVsBasicBlocks.remove(lbl);
			keys = labelVsBasicBlocks.keySet();
			iter = keys.iterator();
		}

		if(prevLastList != null) prevLastList.setStmList(
				new AssemStmList(new AssemLabel(doneLabel), null));

	}

	private AssemStmList trace(AssemStmList firstListInTrace) {	
		AssemStmList list = firstListInTrace;

		while(true) {
			AssemStmList lastStmList = getLast(list);
			if(lastStmList.getStmList() == null){
				AssemJump lastJump = new AssemJump(new AssemName(doneLabel));	
				AssemStmList stmListLastJump = new AssemStmList(lastJump,null);
				lastStmList.setStmList(stmListLastJump);
				return stmListLastJump;
			}
			
			AssemStm stm = lastStmList.getStmList().getStm();

			if(stm instanceof AssemJump) {
				AssemJump jmpStm = (AssemJump) stm;
				Label nextLbl = ((AssemName)jmpStm.getLabelExp()).getLabel();

				AssemStmList nextList = (AssemStmList) labelVsBasicBlocks.get(nextLbl);

				if(nextList == null) {
					if(nextLbl.equals(doneLabel))
						lastStmList.getStmList().setStmList(nextList);

					return lastStmList.getStmList();
				}

				lastStmList.setStmList(nextList);

				labelVsBasicBlocks.remove(nextLbl);
				list = nextList;			
			}
			if(stm instanceof AssemCJump) {
				AssemCJump cjumpStm = (AssemCJump) stm;
				Label falseLabel = cjumpStm.getIfFalseLabel();
				Label trueLabel = cjumpStm.getIfTrueLabel();

				AssemStmList falseLblNextList = (AssemStmList) labelVsBasicBlocks.get(falseLabel);
				AssemStmList trueLblNextList = (AssemStmList) labelVsBasicBlocks.get(trueLabel);

				if(falseLblNextList != null) {

					if(falseLabel.equals(doneLabel)){
						lastStmList.getStmList().setStmList(falseLblNextList);
						return lastStmList.getStmList();
					}

					// Block starting with false label exists; negate the condition and attach the false
					// path to the trace				
					labelVsBasicBlocks.remove(falseLabel);
					lastStmList.getStmList().setStmList(falseLblNextList);					
					list = falseLblNextList;
				}
				else if(trueLblNextList != null) {
					//	Only the block starting with true label exists
					AssemCJump newCJump = new AssemCJump(
							AssemCJump.notRel(cjumpStm.relop), cjumpStm.getLeft(),
							cjumpStm.getRight(), cjumpStm.getIfFalseLabel(), cjumpStm.getIfTrueLabel());
					labelVsBasicBlocks.remove(trueLabel);
					lastStmList.getStmList().setHead(newCJump);
					lastStmList.getStmList().setStmList(trueLblNextList);
					list = trueLblNextList;
				}
				else {
					// A CJump statement followed by niether its true nor its false label
					Label newFalseLabel = new Label("");
					lastStmList.getStmList().setHead(
							new AssemCJump(cjumpStm.relop,cjumpStm.getLeft(),
									cjumpStm.getRight(), cjumpStm.getIfTrueLabel(),
									newFalseLabel));

					AssemJump newJump = new AssemJump(new AssemName(cjumpStm.getIfFalseLabel()));
					AssemStmList newList = new 	AssemStmList(newJump, null);	
					lastStmList.getStmList().setStmList(
							new AssemStmList(new AssemLabel(newFalseLabel), newList));
					return newList;
				}
			}
		}
	}

	public AssemStmList getTrace() {
		return tracedStms;
	}

	public Vector getBasicBlocks() {
		return blocks;
	}

	private Map.Entry getNextBlock() {
		Set set = labelVsBasicBlocks.entrySet();
		Iterator iter = set.iterator();

		while(iter.hasNext()) {
			Map.Entry entr = (Map.Entry)iter.next();
			return entr;   // Return the first element in the map
		}
		return null;  // Return null if empty
	}

	private AssemStmList getLast(AssemStmList list) {
		AssemStmList tempList = list;
		//Get the stmlist that has the label stmlist as its stmlist
		if(tempList.getStmList() == null)
			return tempList;

		while(tempList.getStmList().getStmList() != null) tempList = tempList.getStmList(); 
			return tempList;
	}
}
