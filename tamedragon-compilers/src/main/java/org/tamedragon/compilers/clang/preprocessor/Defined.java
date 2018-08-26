package org.tamedragon.compilers.clang.preprocessor;

public class Defined extends Absyn {
	String id;
	boolean isNot;
	
	public Defined(String id, boolean isNot){
		this.id = id;
		this.isNot = isNot;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNot() {
		return isNot;
	}

	public void setNot(boolean isNot) {
		this.isNot = isNot;
	}
	
	public int evaluate(){
		if(id != null){
			// The check is for whether or not an ID has been defined
			DefinitionsMap defsMap = DefinitionsMap.getInstance();
			String defValue = defsMap.getDefinition(id);
			if(isNot && defValue == null)
				return 1;
			if(isNot && defValue != null)
				return 0;
			if(!isNot && defValue == null)
				return 0;
			if(!isNot && defValue != null)
				return 1;
		}
		
		return 0;
	}
}
