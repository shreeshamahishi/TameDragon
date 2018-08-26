package org.tamedragon.compilers.canon;

import org.tamedragon.assemblyabstractions.constructs.*;
import org.tamedragon.common.llvmir.types.Temp;

class StmExpList 
{
	private AssemStm stm;
	private AssemExpList exps;

	public StmExpList(AssemStm s, AssemExpList e) 
	{
		stm = s; 
		exps = e;
	}
	
	public AssemStm getStm()
	{
		return stm;
	}
	
	public AssemExpList getExps()
	{
		return exps;
	}
}

public class Canonizer 
{
	private StmExpList nopNull = new StmExpList(new AssemExpStm(new AssemConst(0)),null);
	
	private boolean isNop(AssemStm a) 
	{
		return a instanceof AssemExpStm
          && ((AssemExpStm)a).getExp() instanceof AssemConst;
	}
	
	private AssemStm seq(AssemStm a, AssemStm b)
	{
		if (isNop(a)) return b;
		else if (isNop(b)) return a;
		else return new AssemSeq(a,b);
    }
	
	private boolean commute(AssemStm a, AssemExp b) 
	{
		return isNop(a)
        || b instanceof AssemName
        || b instanceof AssemConst;
	}
	
	private AssemStm do_stm(AssemSeq s) 
	{ 
		return seq(do_stm(s.getLeftStm()), do_stm(s.getRightStm()));
	}
	
	private AssemStm do_stm(AssemMove s) 
	{ 
		if (s.getDst() instanceof AssemTemp
	     && s.getSrc() instanceof AssemCallExp) 
		return reorder_stm(new MoveCall((AssemTemp)s.getDst(),
						(AssemCallExp)s.getSrc()));
		
		else if (s.getDst() instanceof AssemSeqExp)
			return do_stm(new AssemSeq(((AssemSeqExp)s.getDst()).getStm(),
					new AssemMove(((AssemSeqExp)s.getDst()).getExp(),
						  s.getSrc())));
	    else return reorder_stm(s);
	}
	
	private AssemStm do_stm(AssemExpStm s)
	{ 
		if (s.getExp() instanceof AssemCallExp)
	       return reorder_stm(new ExpCall((AssemCallExp)s.getExp()));
		else return reorder_stm(s);
	}
	
	private AssemStm do_stm(AssemStm s)
	{
	     if (s instanceof AssemSeq) return do_stm((AssemSeq)s);
	     else if (s instanceof AssemMove) return do_stm((AssemMove)s);
	     else if (s instanceof AssemExpStm) return do_stm((AssemExpStm)s);
	     else return reorder_stm(s);
    }
	
	private AssemStm reorder_stm(AssemStm s) 
	{
		if(s == null) return seq(s, null);
	     StmExpList x = reorder(s.children());
	     return seq(x.getStm(), s.build(x.getExps()));
    }
	
	private AssemSeqExp do_exp(AssemSeqExp e) 
	{
      AssemStm stms = do_stm(e.getStm());
      AssemSeqExp b = do_exp(e.getExp());
      return new AssemSeqExp(seq(stms,b.getStm()), b.getExp());
	}
	
	private AssemSeqExp do_exp (AssemExp e) 
	{
       if (e instanceof AssemSeqExp) return do_exp((AssemSeqExp)e);
       else return reorder_exp(e);
    }
	
	private AssemSeqExp reorder_exp (AssemExp e)
	{
		StmExpList x = reorder(e.children());
		return new AssemSeqExp(x.getStm(), e.build(x.getExps()));
	}
	
	private StmExpList reorder(AssemExpList exps) 
	{
		//TODO Verify wrt to the value properties in this function.
		if (exps == null) return nopNull;
		else 
		{
			AssemExp a = exps.getHead();
			if (a instanceof AssemCallExp) 
			{
				Temp t = new Temp();
				AssemExp e = new AssemSeqExp(new AssemMove(new 
						AssemTemp(t, a.getValueProperties()), a),
				    new AssemTemp(t, a.getValueProperties()));
				return reorder(new AssemExpList(e, exps.getTail()));
			} 
			else
			{
				AssemSeqExp aa = do_exp(a);
				StmExpList bb = reorder(exps.getTail());
				if (commute(bb.getStm(), aa.getExp()))
					return new StmExpList(seq(aa.getStm(),bb.getStm()), 
				    new AssemExpList(aa.getExp(),bb.getExps()));
				else 
				{
					Temp t = new Temp();
					return new StmExpList(seq(aa.getStm(),
							seq(new AssemMove(new AssemTemp(t, aa.getExp().getValueProperties()),aa.getExp()),
									bb.getStm())),
									
							new AssemExpList(new AssemTemp(t, bb.getExps().getHead().getValueProperties()), bb.getExps()));
				}
			}
		}
	}
	
	private AssemStmList linear(AssemSeq s, AssemStmList l) 
	{
		return linear(s.getLeftStm(),linear(s.getRightStm(),l));
	}
	
	private AssemStmList linear(AssemStm s, AssemStmList l)
	{
		if (s instanceof AssemSeq) return linear((AssemSeq)s, l);
		else return new AssemStmList(s,l);
	}
	
	public AssemStmList linearize(AssemStm s)
	{
		return linear(do_stm(s), null);
	}
	
	public AssemStmList linearize(AssemType assemType)
	{
		if(assemType instanceof AssemStm)
			return linearize((AssemStm) assemType);
		else if(assemType instanceof AssemSeqExp)
		{
			AssemSeqExp seqExp = (AssemSeqExp) assemType;
			return linearize(seqExp.translateToStatement());
		}
		return null;
	}
}
