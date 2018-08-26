package org.tamedragon.compilers.clang.preprocessor;

import gnu.jel.CompilationException;
import gnu.jel.CompiledExpression;
import gnu.jel.Evaluator;
import gnu.jel.Library;

import java.util.ArrayList;
import java.util.List;

public class FuncCallExpr {
	String funcName;
	List<Object> parameters = new ArrayList<Object>();
	
	public FuncCallExpr(String funcName){
		this.funcName = funcName;
	}
	
	public void addParameters(Object obj){
		parameters.add(obj);
	}

	public String getFuncName() {
		return funcName;
	}

	public List<Object> getParameters() {
		return parameters;
	}
	
	public int evaluate(){
		String token = funcName + "(";
		String replacementText = null;
		for(int i = 0; i < parameters.size(); i++){
			Object obj = parameters.get(i);
			if(obj instanceof String)
				replacementText = Definition.getReplacementText((String)obj);
			else if(obj instanceof ConstExpr)
				replacementText = new Integer(((ConstExpr)obj).evaluate()).toString();
			
			token += (i < (parameters.size() - 1))? replacementText + "," : replacementText + ")";
		}
		replacementText = Definition.getReplacementText(token);
		int result = evaluteInfixExpr(replacementText);
		return result;
	}

	private int evaluteInfixExpr(String expr) {
		int resultantValue = 0;
		// Set up the library
		Class[] staticLib=new Class[1];
		try {
			staticLib[0]=Class.forName("java.lang.Math");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			// Can't be ;)) ...... in java ... ;)
		}
		Library lib=new Library(staticLib,null,null,null,null);
		try {
			lib.markStateDependent("random",null);
		} 
		catch(CompilationException e){
			e.printStackTrace();
		}
		// Compile
		CompiledExpression expr_c=null;
		try {
			expr_c=Evaluator.compile(expr,lib);
		} catch (CompilationException ce) {
			System.err.print("–––COMPILATION ERROR :");
			System.err.println(ce.getMessage());
			System.err.print("                       ");
			System.err.println(expr);
			int column=ce.getColumn(); // Column, where error was found
			for(int i=0;i<column+23-1;i++) System.err.print(' ');
			System.err.println('^');
		}
		if (expr_c !=null) {

			// Evaluate (Can do it now any number of times FAST !!!)
			Number result=null;
			try {
				result=(Number)expr_c.evaluate(null);
			} catch (Throwable e) {
				System.err.println("Exception emerged from JEL compiled"+
				" code (IT'S OK) :");
				System.err.print(e);
			}
			if (result==null) 
				resultantValue = 0;
			else
				resultantValue = Integer.parseInt(result.toString());
		}
		return resultantValue;
	}
}
