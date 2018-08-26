package org.tamedragon.compilers.clang.abssyntree;

public class ParamTypeList extends Absyn {

	private ParamList paramList;
	private boolean hasEllipses;
	
	public ParamTypeList(){}
	
	public ParamTypeList(ParamList paramList){
		this.paramList = paramList;
	}

	public boolean isHasEllipses() {
		return hasEllipses;
	}

	public void setHasEllipses(boolean hasEllipses) {
		this.hasEllipses = hasEllipses;
	}



	public ParamList getParamList() {
		return paramList;
	}



	public void setParamList(ParamList paramList) {
		this.paramList = paramList;
	}
}
