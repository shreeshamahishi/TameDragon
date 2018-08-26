package org.tamedragon.compilers.canon;

import org.tamedragon.common.Label;
import org.tamedragon.assemblyabstractions.constructs.*;

public class BasicBlocks 
{
  private AssemStmListList blocks;
  private Label done;

  private AssemStmListList lastBlock;
  private AssemStmList lastStm;

  private void addStm(AssemStm s) 
  {
	  AssemStmList newStmList = new AssemStmList(s,null);
	  lastStm.setStmList(newStmList);
	  lastStm = newStmList;
  }

  private void doStms(AssemStmList l) {
      if (l==null) 
	doStms(new AssemStmList(new AssemJump(new AssemName(done)), null));
      else if (l.getStm()instanceof AssemJump 
	      || l.getStm() instanceof AssemCJump) {
	addStm(l.getStm());
	mkBlocks(l.getStmList());
      } 
      else if (l.getStm() instanceof AssemLabel)
      {
    	  AssemLabel assemLbl = (AssemLabel) l.getStm();
    	  AssemName assemName = new AssemName(assemLbl.getLabel());
           doStms(new AssemStmList(new AssemJump(assemName), l));
      }
      else {
	addStm(l.getStm());
	doStms(l.getStmList());
      }
  }

  void mkBlocks(AssemStmList l) {
     if (l==null) return;
     else if (l.getStm() instanceof AssemLabel) {
	lastStm = new AssemStmList(l.getStm(),null);
        if (lastBlock==null)
        {
  	   lastBlock= blocks= new AssemStmListList(lastStm,null);
        }
        else
        {
        	AssemStmListList newBlock = new AssemStmListList(lastStm,null);
        	lastBlock.setTail(newBlock);
        	lastBlock = newBlock;
        }
        
	doStms(l.getStmList());
     }
     else mkBlocks(new AssemStmList(new AssemLabel(new Label("")), l));
  }
   

  public BasicBlocks(AssemStmList stms) {
    done = new Label("");
    mkBlocks(stms);
  }
  
  public AssemStmListList getBlocks()
  {
	  return blocks;
  }
  
  public void setBlocks(AssemStmListList ll)
  {
	  blocks = ll;
  }
  
  public Label getDone()
  {
	  return done;
  }
}

