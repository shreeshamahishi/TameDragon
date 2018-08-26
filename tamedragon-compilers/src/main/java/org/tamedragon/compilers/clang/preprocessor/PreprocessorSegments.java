package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class PreprocessorSegments {
	
	private List<PreprocessorUnit> units;
	
	public PreprocessorSegments(){
		units = new ArrayList<PreprocessorUnit>();
	}
	
	public void addUnit(PreprocessorUnit pu){
		units.add(pu);		
	}

	public List<PreprocessorUnit> getUnits() {
		return units;
	}

	public void setUnits(List<PreprocessorUnit> units) {
		this.units = units;
	}
	
	public StringBuffer process(String sourceFilePath, boolean clearPreviousDefinitions){
		
		if(clearPreviousDefinitions)
			DefinitionsMap.getInstance().clearDefinitions();   // Lets clear previous definitions	
	
		StringBuffer sb = new StringBuffer();
		for(PreprocessorUnit pu: units){
			
			pu.setSourceFilePath(sourceFilePath);
			sb.append(pu.process());
		}
		return sb;
	}
}
