package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class ElifPart extends Absyn {
	
	private Elif elif;
	private List<PreprocessorUnit> preprocessorUnits;
		
	public ElifPart(){}
	
	public ElifPart(int lineNum, Elif elif){
		setLineNum(lineNum);
		this.elif = elif;			
	}
	
	public Elif getElif() {
		return elif;
	}

	public void setElif(Elif elif) {
		this.elif = elif;
	}

	public List<PreprocessorUnit> getPreprocessorUnits() {
		return preprocessorUnits;
	}

	public void setPreprocessorUnits(List<PreprocessorUnit> preprocessorUnits) {
		this.preprocessorUnits = preprocessorUnits;
	}
	
	public void addPreprocessorUnit(PreprocessorUnit pu){
		if(preprocessorUnits == null){
			preprocessorUnits = new ArrayList<PreprocessorUnit>();
		}
		
		preprocessorUnits.add(pu);
	}
	
}
