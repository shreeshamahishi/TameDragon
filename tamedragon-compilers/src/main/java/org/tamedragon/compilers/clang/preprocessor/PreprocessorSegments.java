package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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

	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag, boolean clearPreviousDefinitions)
			throws Exception{

		if(clearPreviousDefinitions) {
			// Lets clear previous definitions
			DefinitionsMap.getInstance().clearDefinitions();  	
		}

		StringBuffer sb = new StringBuffer();
		for(PreprocessorUnit pu: units){			
			sb.append(pu.process(sourceFilePath, dependenciesDag));
		}
		return sb;
	}

	
}
