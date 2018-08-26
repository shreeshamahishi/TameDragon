package org.tamedragon.compilers.clang.abssyntree;

public class TypeName extends Absyn {
	private SpecifierQualifierList specifierQualifierList;
	private AbstractDeclarator abstractDeclarator;
	
	public TypeName(){}
	
	public TypeName(SpecifierQualifierList specifierQualifierList,
			AbstractDeclarator abstractDeclarator)
	{
		this.specifierQualifierList =specifierQualifierList;
		this.abstractDeclarator = abstractDeclarator;
	}

	public AbstractDeclarator getAbstractDeclarator() {
		return abstractDeclarator;
	}

	public void setAbstractDeclarator(AbstractDeclarator abstractDeclarator) {
		this.abstractDeclarator = abstractDeclarator;
	}

	public SpecifierQualifierList getSpecifierQualifierList() {
		return specifierQualifierList;
	}

	public void setSpecifierQualifierList(
			SpecifierQualifierList specifierQualifierList) {
		this.specifierQualifierList = specifierQualifierList;
	}
	
	public String toString(){
		String str = "";
		
		if(specifierQualifierList != null){
			str += specifierQualifierList.toString();
		}
		
		if(abstractDeclarator != null){
			str += abstractDeclarator.toString(); // TODO Implement this toString in absrtract declarator
		}
		
		return str;
	}
	
}
