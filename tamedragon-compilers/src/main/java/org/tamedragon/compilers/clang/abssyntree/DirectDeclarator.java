package org.tamedragon.compilers.clang.abssyntree;

public class DirectDeclarator extends Absyn{
	
	public static int ID = 1;
	public static int DECLR = 2;
	public static int ARRAY = 3;
	public static int EMPTY_ARRAY = 4;
	public static int FUNC = 5;
	public static int NO_ARG_FUNC = 6;
	public static int ID_LIST_FUNC = 7;
	
	private int type;
	
	private String id;
	private Declarator declarator;
	private DirectDeclarator directDeclarator;
	private RootExpr arraySizeExpr;  // has to be const
	private ParamTypeList paramTypeList;
	private IdentifierList identifierList;

	public String toString(){
		String str = "";
		
		if(declarator != null){
			str += declarator.toString();
			
			if(directDeclarator != null)
				str += directDeclarator.toString();
		}
		else{
			if(directDeclarator != null)
				str += directDeclarator.toString();
				
			if(type == ID)
				str = id + ": " + str;
			else if(type == DECLR)
				str += declarator.toString();
			else if(type == ARRAY) 
				str += " array[" + arraySizeExpr.toString() + "] of" ;
			else if(type == EMPTY_ARRAY)
				str += " array[] of ";
			else if(type == FUNC)
				// TODO Implement to replace some_args with correct values
				str += " function(" + "some_args"  + ") returning";			
			else if(type == NO_ARG_FUNC)
				str += " function() returning";
			else 
				// TODO Implement this (must be id_list_func)
				str += "";
		}
		
		
		return str;
	}
	
	public DirectDeclarator(){}
	
	public DirectDeclarator(String id, Declarator declarator,
			DirectDeclarator directDeclarator, RootExpr arraySizeExpr, 
			ParamTypeList paramTypeList, int type)
	{
		
		this.type = type;
		this.id = id;
		this.declarator = declarator;
		this.directDeclarator = directDeclarator;
		this.arraySizeExpr = arraySizeExpr;
		this.paramTypeList = paramTypeList;
	}

	public RootExpr getArraySizeExpr() {
		return arraySizeExpr;
	}

	public void setArraySizeExpr(RootExpr arraySizeExpr) {
		this.arraySizeExpr = arraySizeExpr;
	}

	public Declarator getDeclarator() {
		return declarator;
	}

	public void setDeclarator(Declarator declarator) {
		this.declarator = declarator;
	}

	public DirectDeclarator getDirectDeclarator() {
		return directDeclarator;
	}

	public void setDirectDeclarator(DirectDeclarator directDeclarator) {
		this.directDeclarator = directDeclarator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ParamTypeList getParamTypeList() {
		return paramTypeList;
	}

	public void setParamTypeList(ParamTypeList paramTypeList) {
		this.paramTypeList = paramTypeList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public IdentifierList getIdentifierList() {
		return identifierList;
	}

	public void setIdentifierList(IdentifierList identifierList) {
		this.identifierList = identifierList;
	}
}
