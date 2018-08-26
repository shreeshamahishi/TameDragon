package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class Conditional extends Absyn implements PreprocessorDirective {

	public static final int IFDEF = 0;
	public static final int IFNDEF = 1;

	private int conditionalType;
	private IfLine ifLine;
	private List<PreprocessorUnit> preprocessorUnits;	
	private List<ElifPart> elifParts; 
	private ElsePart elsePart;
	private int endConditionalLineNum;   // This is the line number of the #endif
	
	private String sourceFilePath;
	
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public Conditional(){
		elifParts = new ArrayList<ElifPart>();
	}

	public Conditional(int lineNum, IfLine ifLine){
		setLineNum(lineNum);
		this.ifLine = ifLine;
	}

	public int getConditionalType() {
		return conditionalType;
	}

	public void setConditionalType(int conditionalType) {
		this.conditionalType = conditionalType;
	}

	public IfLine getIfLine() {
		return ifLine;
	}

	public void setIfLine(IfLine ifLine) {
		this.ifLine = ifLine;
	}

	public List<PreprocessorUnit> getPreprocessorUnits() {
		return preprocessorUnits;
	}

	public void setPreprocessorUnits(List<PreprocessorUnit> preprocessorUnits) {
		this.preprocessorUnits = preprocessorUnits;
	}

	public List<ElifPart> getElifParts() {
		return elifParts;
	}

	public void setElifParts(List<ElifPart> elifParts) {
		this.elifParts = elifParts;
	}

	public ElsePart getElsePart() {
		return elsePart;
	}

	public void setElsePart(ElsePart elsePart) {
		this.elsePart = elsePart;
	}

	public void addPreprocessorUnit(PreprocessorUnit pu){
		if(preprocessorUnits == null){
			preprocessorUnits = new ArrayList<PreprocessorUnit>();
		}

		preprocessorUnits.add(pu);

	}

	public void addElifPart(ElifPart elifPart){
		if(elifParts == null){
			elifParts = new ArrayList<ElifPart>();
		}
		elifParts.add(elifPart);
	}

	public int getPreprocessorUnitType(){
		return PreprocessorUnit.CONDITIONAL;
	}

	public StringBuffer process(){
		
		boolean ifConditionsIsTrue = ifLine.isTrue();
		if(ifConditionsIsTrue) {
			return processIfDirectiveIsTrue();
		}

		// The #if condition is not true; check elifs if applicable	
		if(elifParts != null && elifParts.size() > 0){	
			int i = getIndexOfElifEvaluatingToTrue();

			if(i >= 0){
				// We have an elif that evaluates to true; lets process that and 
				// discard the rest
				ElifPart elifPartThatEvaluatesToTrue = elifParts.get(i);
				return processElifDirectiveThatEvaluatesToTrue(i, elifPartThatEvaluatesToTrue);
			}
			else{
				// We have elif parts, but none of them evaluated to true; lets process the else part
				// if it exists
				return processElseDirective();
			}
		}

		// Neither the #if nor the #elifs (if any) evaluated to true, so lets process the #else if it exists
		return processElseDirective(); 

	}

	/**
	 * Process the # if directive since it evaluated to true
	 * @return
	 */

	public StringBuffer processIfDirectiveIsTrue(){
		// The "#if" condition is true, lets include those lines in the "true" path
		StringBuffer sb = new StringBuffer();
		sb.append("\n");    // For the #if line

		for(PreprocessorUnit pu: preprocessorUnits){
			StringBuffer sbOfPu = pu.process();
			sb.append(sbOfPu);
		}		

		// ... and discard everything else until the last #elsif #endif line (we fill them in with new line characters)
		if(elifParts != null && elifParts.size() > 0){
			ElifPart firstelifPart = elifParts.get(0);
			int lineNumOfFirstElif = firstelifPart.getLineNum();

			int numReplacementsRequired = endConditionalLineNum - (lineNumOfFirstElif -1);
			for(int count = 0; count < numReplacementsRequired; count++){
				sb.append("\n");
			}				
		}		
		else{
			if(elsePart != null){   // There is a else part, lets remove it
				int numReplacements = endConditionalLineNum - (elsePart.getLineNum() -1) ;
				for(int count = 0; count < numReplacements; count++){
					sb.append("\n");
				}					
			}
			else{
				// No else part, but we need a newline to replace #endif
				sb.append("\n");
			}
		}

		return sb;   // Nothing to process after this
	}

	/**
	 * Returns the index of the FIRST ElifPart that evaluates to true; -1 otherwise. Assumes
	 * that there are elif parts (list is not zero) 
	 * @return
	 */
	public int getIndexOfElifEvaluatingToTrue(){

		int requiredIndex = -1;

		int numElifParts = elifParts.size();

		for(int i = 0; i < numElifParts; i++){
			ElifPart elifPart = elifParts.get(i);
			Elif elif = elifPart.getElif();
			if(elif.isTrue()){
				requiredIndex = i;
				break;
			}
		}

		return requiredIndex;
	}

	/**
	 * Process the the elif part that evaluated to true; removes the # if directive until the preceding elif part
	 * and from the next elif part to the end of the conditional
	 * @param elifPartThatEvaluatesToTrue
	 * @return
	 */
	public StringBuffer processElifDirectiveThatEvaluatesToTrue(int index, ElifPart elifPartThatEvaluatesToTrue){

		StringBuffer sb = new StringBuffer();

		int lineNumOfElifPartThatEvaluatesToTrue = elifPartThatEvaluatesToTrue.getLineNum();
		int numReplacementsRequired = lineNumOfElifPartThatEvaluatesToTrue - (getLineNum() -1); 
		for(int count = 0; count < numReplacementsRequired; count++){
			sb.append("\n");
		}

		// Include the preprocessor units (after processing them) 
		List<PreprocessorUnit> preprocessorUnits = elifPartThatEvaluatesToTrue.getPreprocessorUnits();
		for(PreprocessorUnit pu: preprocessorUnits){
			StringBuffer sbOfPu = pu.process();
			sb.append(sbOfPu);
		}

		// If this is the last elif, remove the end directive and return.
		if(index == elifParts.size() -1){  // This is the last elif
			if(elsePart != null){
				int elsePartStart = elsePart.getLineNum();
				numReplacementsRequired = endConditionalLineNum - (elsePartStart -1);
			}
			else{
				// No else part, remove the #endif
				numReplacementsRequired = 1;				
			}
		}
		else{
			// Not the last elif; remove from the end of this #elifpart to the end of the conditional
			ElifPart nextElifPart = elifParts.get(index +1);
			numReplacementsRequired = endConditionalLineNum - (nextElifPart.getLineNum() -1);			
		}
		
		for(int count = 0; count < numReplacementsRequired; count++){
			sb.append("\n");
		}

		return sb;
	}

	/**
	 * Process the else directive since none of the preceding conditions were evaluated to true;
	 * however if there is no else part, the entire conditional is replaced.
	 * @return
	 */
	public StringBuffer processElseDirective(){
		StringBuffer sb = new StringBuffer();

		if(elsePart != null){
			int lineNumOfElsePart = elsePart.getLineNum();

			int numReplacementsRequired = lineNumOfElsePart - (getLineNum() - 1);
			for(int count = 0; count < numReplacementsRequired; count++){
				sb.append("\n");
			}

			// Include the preprocessor units (after processing them) 
			List<PreprocessorUnit> preprocessorUnits = elsePart.getPreprocessorUnits();
			for(PreprocessorUnit pu: preprocessorUnits){
				StringBuffer sbOfPu = pu.process();
				sb.append(sbOfPu);
			}

			sb.append("\n");  // For #endif		

			return sb;
		}

		// None of the conditions held true, lets replace the entire conditional with new lines
		int numReplacements = endConditionalLineNum - (getLineNum() -1);
		for(int count = 0; count < numReplacements; count++){
			sb.append("\n");
		}

		return sb;		
	}

	public int getEndConditionalLineNum() {
		return endConditionalLineNum;
	}

	public void setEndConditionalLineNum(int endConditionalLineNum) {
		this.endConditionalLineNum = endConditionalLineNum;
	}	
}
