package org.tamedragon.compilers.clang.tests.irtranslate;

import org.tamedragon.assemblyabstractions.constructs.IRTree;
import org.tamedragon.assemblyabstractions.constructs.IRTreeArgPassStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeBinaryExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConditionalBranch;
import org.tamedragon.assemblyabstractions.constructs.IRTreeConst;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExp;
import org.tamedragon.assemblyabstractions.constructs.IRTreeExpressionStm;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMemory;
import org.tamedragon.assemblyabstractions.constructs.IRTreeMove;
import org.tamedragon.assemblyabstractions.constructs.IRTreeReturn;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatement;
import org.tamedragon.assemblyabstractions.constructs.IRTreeStatementList;
import org.tamedragon.assemblyabstractions.constructs.IRTreeTempOrVar;

public class Debug {
	/*
	private void indent(int d) {
      for(int i = 0; i <d ; i++) 
            System.out.print(' ');
	}
	
	private void printLn(String s) {
		System.out.println(s);
	}
	
	private void print(String s) {
		System.out.print(s);
	}
	
	public void printAssem(IRTree IRTree) {
		if(IRTree instanceof IRTreeStatement)
			print((IRTreeStatement)IRTree);
		else
			print((IRTreeExp)IRTree);
	}
	
	public void print(IRTreeStatement stm)  {
		printStm(stm, 0);
		print("\n");
	}
	
	public void print(IRTreeExp e) {printExp(e,0); print("\n");}
	
	private void printStm(IRTreeStatement s, int d)  {
				
		//if (s instanceof MipsIRTreeList) printStm((MipsIRTreeList)s, d);
	    if (s instanceof IRTreeMove) printStm((IRTreeMove)s, d);
	    else if (s instanceof IRTreeExpressionStm) printStm((IRTreeExpressionStm)s, d);
	    else if (s instanceof IRTreeReturn) printStm((IRTreeReturn)s, d);
	    else if (s instanceof IRTreeArgPassStm) printStm((IRTreeArgPassStm)s, d);
	    else if (s instanceof IRTreeStatementList) print((IRTreeStatementList)s,d);
    }
	
	private void printExp(IRTreeExp e, int d) {
       if (e instanceof IRTreeBinaryExp) printExp((IRTreeBinaryExp)e, d);
	   else if (e instanceof IRTreeMemory) printExp((IRTreeMemory)e, d);
	   else if (e instanceof IRTreeTempOrVar) printExp((IRTreeTempOrVar)e, d);
	   else if (e instanceof IRTreeConst) printExp((IRTreeConst)e, d);
	   else if (e instanceof IRTreeConditionalBranch) printExp((IRTreeConditionalBranch)e, d);
    }
	
	private void printStm(IRTreeReturn s, int d) {
		indent(d); print("RETURN "); print(s.getDescription());
	}
	
	private void printStm(IRTreeArgPassStm s, int d) {
		indent(d); print("PASS "); print(s.getDescription());
	}
	
	private void printExp(IRTreeConditionalBranch s, int d) {
		
	      indent(d); print("CJUMP("); 
	      switch(s.getPredicate()) {
	        case ICMP_EQ: print("EQ"); break;
	        case ICMP_NE: print("NE"); break;
	        case ICMP_SLT: print("LT"); break;
	        case ICMP_SGT: print("GT"); break;
	      }
	        printLn(","); printExp(s.getLeft(),d+4); printLn(",");
	        printExp(s.getRight(),d+4); printLn(",");
	       print(")");
	  }
	
	private void printStm(IRTreeMove moveStm, int indentSpace) {
		indent(indentSpace);
		printLn(moveStm.getDescription());
		printExp(moveStm.getDest(), indentSpace + 4);
		printLn(",");
		printExp(moveStm.getSrc(), indentSpace + 4);
		print(")");
	}
	
	private void printStm(IRTreeExpressionStm expStm, int identSpace) {
		indent(identSpace); printLn("EXP("); 
		printExp(expStm.getExpressionTree(),identSpace+4); print(")"); 
	}
	
	private void printExp(IRTreeBinaryExp e, int d) {
	    indent(d); print("MipsAssemBinOp("); 
	    	    
	    switch(e.getBinaryOperatorID()) {
			case ADD: print("PLUS"); break;
			case SUB: print("MINUS"); break;
			case MUL: print("MUL"); break;
			case SDIV: print("DIV"); break;
			
			default:
		         throw new Error("Print.printExp.MipsAssemBinOp");
	       }
	      printLn(",");
	      printExp(e.getLeft(),d+4); printLn(","); printExp(e.getRight(),d+4); print(")");
	   }

	private void printExp(AssemUnaryOpExp e, int d){
		indent(d); print("MipsAssemUnaryOp("); 
	    
	    switch(e.getOperator()) {
			case AssemUnaryOpExp.ONES_COMPLEMENT: print("ONES_COMPLEMENT"); break;
			case AssemUnaryOpExp.TWOS_COMPLEMENT: print("TWOS_COMPLEMENT"); break;
			
			default:
		         throw new Error("Print.printExp.MipsAssemBinOp");
	       }
	      printLn(",");
	      printExp(e.getExpr(),d+4); print(")");
	}

	
	private void printExp(IRTreeMemory e, int d) {
	     indent(d);
		printLn("MEM("); printExp(e,d+4); print(")");
	  }

	private void printExp(IRTreeTempOrVar e, int d) {
	     indent(d);
	     print(e.getDescription()); 
	     //print(e.getDescription());
	  }

	private void printExp(IRTreeConst e, int d) {
	     indent(d); print("CONST "); print("" + e.getDescription());
	  }

	public void print(IRTreeStatementList stms, int d) {
		System.out.println("STMLIST: ");
		for(IRTreeStatementList l = stms; l != null; l = l.getStatementList()){
			printStm(l.getStatement(), d);
		}
	}
	*/
}
