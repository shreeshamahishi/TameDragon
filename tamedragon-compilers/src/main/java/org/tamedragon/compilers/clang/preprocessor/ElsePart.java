package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class ElsePart extends Absyn  {

	private List<PreprocessorUnit> preprocessorUnits;

	public ElsePart(){}
	
	public ElsePart(int lineNum){
		setLineNum(lineNum);
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
