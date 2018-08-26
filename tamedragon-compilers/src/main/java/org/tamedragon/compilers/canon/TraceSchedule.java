package org.tamedragon.compilers.canon;

import org.tamedragon.common.Label;
import org.tamedragon.assemblyabstractions.constructs.*;

public class TraceSchedule {

  public AssemStmList stms;
  BasicBlocks theBlocks;
  java.util.Dictionary table = new java.util.Hashtable();

  AssemStmList getLast(AssemStmList block) {
     AssemStmList l=block;
     while (l.getStmList().getStmList() !=null)  l=l.getStmList();
     return l;
  }

  void trace(AssemStmList l) {
   for(;;) {
     AssemLabel lab = (AssemLabel)l.getStm();
     table.remove(lab.getLabel());
     AssemStmList last = getLast(l);
     AssemStm s = last.getStmList().getStm();
     
    if (s instanceof AssemJump) {
	AssemJump j = (AssemJump)s;
	AssemStmList target = (AssemStmList)table.get(j.getLabel());
	if (target!=null) {
               last.setStmList(target);
	       l=target;
        }
	else {
	  last.getStmList().setStmList(getNext());
	  return;
        }
     }
	
     else if (s instanceof AssemCJump) {
	AssemCJump j = (AssemCJump)s;
        AssemStmList t = (AssemStmList)table.get(j.getIfTrueLabel());
        AssemStmList f = (AssemStmList)table.get(j.getIfFalseLabel());
        if (f!=null) {
	  last.getStmList().setStmList(f); 
	  l=f;
	}
        else if (t!=null) {
	  last.getStmList().setHead(new AssemCJump(AssemCJump.notRel(j.relop),
					j.getLeft(),j.getRight(),
					j.getIfFalseLabel(),j.getIfFalseLabel()));
	  last.getStmList().setStmList(t);
	  l=t;
        }
        else {
	  Label ff = new Label("");
	  last.getStmList().setHead(new AssemCJump(j.relop,j.getLeft(),j.getRight(),
					j.getIfTrueLabel(),ff));
	  last.getStmList().setStmList(new AssemStmList(new AssemLabel(ff),
		           new AssemStmList(new AssemJump(
		        		   new AssemName(j.getIfFalseLabel())),
					    getNext())));
	  return;
        }
     }
     else throw new Error("Bad basic block in TraceSchedule");
    }
  }

  AssemStmList getNext() {
    if (theBlocks.getBlocks() == null)
	   return new AssemStmList(new AssemLabel(theBlocks.getDone()), null);
      else {
	 AssemStmList s = theBlocks.getBlocks().getHead();
	 AssemLabel lab = (AssemLabel)s.getStm();
	 if (table.get(lab.getLabel()) != null) {
          trace(s);
	  return s;
         }
         else {
	   theBlocks.setBlocks(theBlocks.getBlocks().getTail());
           return getNext();
         }
      }
  }

  public TraceSchedule(BasicBlocks b) {
    theBlocks=b;
    for(AssemStmListList l = b.getBlocks(); l!=null; l=l.getTail())
       table.put(((AssemLabel)l.getHead().getStm()).getLabel(), l.getHead());
    
    stms=getNext();
    table=null;
  }     
}



