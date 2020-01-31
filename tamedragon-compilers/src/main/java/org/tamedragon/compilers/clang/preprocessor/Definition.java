package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.tamedragon.compilers.clang.ErrorHandler;
import org.tamedragon.compilers.clang.SourceLocation;

public class Definition extends Absyn implements PreprocessorDirective {

	public static final int ID_DEFINITION = 0;
	public static final int MACRO_DEFINITION = 1;
	public static final int UNDEFINITION = 2;

	private int definitionType;
	private String id; 
	private TokenSequence replacementTokenSequence;

	private String macro;
	private List<String> otherMacroList;

	private String sourceFilePath;

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public Definition(){
		otherMacroList = new ArrayList<String>();
	}

	public Definition(int lineNum, String macro, int defType){
		super();

		setLineNum(lineNum);
		if(defType == ID_DEFINITION || defType == UNDEFINITION)
			this.id = macro;
		else 			
			this.macro = macro;

		this.definitionType = defType;
	}

	public TokenSequence getReplacementTokenSequence() {
		return replacementTokenSequence;
	}

	public void setReplacementTokenSequence(TokenSequence replacementTokenSequence) {
		this.replacementTokenSequence = replacementTokenSequence;
	}

	public int getDefinitionType() {
		return definitionType;
	}

	public void setDefinitionType(int definitionType) {
		this.definitionType = definitionType;
	}

	public String getMacro() {
		return macro;
	}

	public void setMacro(String macro) {
		this.macro = macro;
	}	

	public List<String> getOtherMacroList() {
		return otherMacroList;
	}

	public void setOtherMacroList(List<String> otherMacroList) {
		this.otherMacroList = otherMacroList;
	}

	public void addToMacroList(String macro){
		if(otherMacroList == null)
			otherMacroList = new ArrayList<String>();
		otherMacroList.add(macro);
	}

	public int getPreprocessorUnitType(){
		return PreprocessorUnit.DEFINITION;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag){
		ErrorHandler errorHandler = ErrorHandler.getInstance();
		SourceLocation location = new SourceLocation(getLineNum(), getPos());

		StringBuffer sb = new StringBuffer();

		// Get the symbol map
		DefinitionsMap definitionsMap = DefinitionsMap.getInstance();

		// Perform any replacements
		if(definitionType == ID_DEFINITION){
			String actualSource = getReplacementText(id);

			if(replacementTokenSequence != null){	
				chkForMisPlacedDoubleHashPreProcessToken(replacementTokenSequence.toString(), errorHandler, location);
				List<String> tokenList = replacementTokenSequence.getSequence();
				String target = process(tokenList).toString();
				String existingValue = definitionsMap.getDefinition(actualSource);
				if((existingValue != null && !existingValue.equals(target))){

					errorHandler.addError(sourceFilePath, location, actualSource + ":", "", 
							ErrorHandler.E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL);
				}
				else{
					if(definitionsMap.isSelfDefintionOnInstance(actualSource) && !actualSource.equals(target)){
						errorHandler.addError(sourceFilePath, location, actualSource + ":", "", 
								ErrorHandler.E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL);
					}
				}

				if(!actualSource.equals(target))
					definitionsMap.setDefinition(actualSource, target);
				else
					definitionsMap.addToSelfDefinitionsOnInstance(actualSource);

			}
			else{
				// Defined as empty macro
				definitionsMap.setDefinition(actualSource, "");
			}
		}
		else if(definitionType == UNDEFINITION){
			String existingValue = getReplacementText(id);

			if(existingValue != null){
				definitionsMap.removeDefinition(id);
			}
		}
		else if(definitionType == MACRO_DEFINITION){
			String actaulSource = getReplacementText(macro);
			if(otherMacroList != null){
				actaulSource += "(";
				for(int i = 0; i < otherMacroList.size(); i++){
					String actualSrcForOtherMacro = getReplacementText(otherMacroList.get(i));
					actaulSource += (i < otherMacroList.size()-1) ? actualSrcForOtherMacro + ", " : actualSrcForOtherMacro;
				}
				actaulSource += ")";
			}
			if(replacementTokenSequence != null){
				String replacementList = replacementTokenSequence.toString();
				chkForMisPlacedDoubleHashPreProcessToken(replacementList, errorHandler, location);
				List<String> tokens = replacementTokenSequence.getSequence();
				String target = process(tokens).toString().trim();
				String existingValue = definitionsMap.getDefinition(actaulSource);

				if(existingValue != null && !existingValue.equals(target)){
					errorHandler.addError(sourceFilePath, location, actaulSource + ":", "", 
							ErrorHandler.E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL);
				}
				else if(definitionsMap.isSelfDefintionOnInstance(actaulSource) && !actaulSource.equals(target)){
					errorHandler.addError(sourceFilePath, location, actaulSource + ":", "", 
							ErrorHandler.E_PREPROCESSOR_REDIFINITION_NOT_IDENTICAL);
				}

				if(!actaulSource.equals(target))
					definitionsMap.setDefinition(actaulSource, target);
				else
					definitionsMap.addToSelfDefinitionsOnInstance(actaulSource);
			}
		}
		sb.append("\n");   // The replacement

		return sb;
	}

	public static StringBuffer process(List<String> tokens){

		// Replace any tokens with replacement strings from the definitions map
		int tokenCount = 0;
		Stack<String> functionMacroStack = new Stack<String>();
		for(String token: tokens){
			int stackCount = functionMacroStack.size();

			if(token.equals(")") && !functionMacroStack.empty()){
				String str = functionMacroStack.pop();
				String newstr = str + token;
				boolean isBalancedParenthesisExist = chkBalancedParenthesisIsPresentOrNot(newstr, '(', ')');
				if(isBalancedParenthesisExist)
					token = newstr;
				else
					functionMacroStack.push(str);
			}

			String replacementStr = Definition.getReplacementText(token);

			if(replacementStr != null && !replacementStr.equals(token)){
				if(replacementStr.length() == 0){
					functionMacroStack.push(token);
					tokens.set(tokenCount, " ");
				}
				else if(functionMacroStack.empty() || functionMacroStack.size() == stackCount -1){

					if(!functionMacroStack.empty()){
						String str = functionMacroStack.pop();
						str += replacementStr;
						functionMacroStack.push(str);
						tokens.set(tokenCount, " ");
					}
					else
						tokens.set(tokenCount, replacementStr);
				}
				else{
					tokens.set(tokenCount, " ");
					String str = functionMacroStack.pop();
					str += replacementStr;
					functionMacroStack.push(str);
				}
			}
			else if(!functionMacroStack.empty()){

				if(token.equals("=")){
					int newTokenCount = tokenCount;
					while(!functionMacroStack.empty()){
						String s = functionMacroStack.pop();
						tokens.set(--newTokenCount, s);
					}
				}
				else{
					tokens.set(tokenCount, " ");
					String str = functionMacroStack.pop();
					str += token;
					functionMacroStack.push(str);
				}
			}
			tokenCount++;
		}
		StringBuffer codeTextStrBuf = new StringBuffer();
		for(String token: tokens){
			codeTextStrBuf.append(token);
		}

		return codeTextStrBuf;
	}

	private static boolean chkBalancedParenthesisIsPresentOrNot(
			String originalValue, char rightChar, char leftChar) {
		boolean hasBalancedParenthesis = false;
		char chArr[] = originalValue.toCharArray();
		int rightParenthesisCount = 0, leftParenthesisCount = 0;

		for(char ch : chArr){
			if(ch == rightChar)
				rightParenthesisCount++;
			else if(ch == leftChar)
				leftParenthesisCount++;
		}

		if(rightParenthesisCount == leftParenthesisCount)
			hasBalancedParenthesis = true;

		return hasBalancedParenthesis;
	}

	private void chkForMisPlacedDoubleHashPreProcessToken(String replacementList, ErrorHandler errorHandler, SourceLocation location) {
		String trimedStr = replacementList.trim();
		if(trimedStr.length() >= 2){
			String s = trimedStr.substring(0, 2);
			if(s.equals("##"))
				errorHandler.addError(sourceFilePath, location, "", "", 
						ErrorHandler.E_DOUBLE_HASH_APPEARING_AT_THE_BEGINNING_OF_REPLACEMENT_LIST);

			s = trimedStr.substring(trimedStr.length()-2, trimedStr.length());
			if(s.equals("##"))
				errorHandler.addError(sourceFilePath, location, "", "", 
						ErrorHandler.E_DOUBLE_HASH_APPEARING_AT_THE_END_OF_REPLACEMENT_LIST);

		}
	}

	public static String getReplacementText(String originalValue){
		DefinitionsMap definitionsMap = DefinitionsMap.getInstance();

		String replacementText = null;
		String currValue = definitionsMap.getDefinition(originalValue);
		if(currValue == null){
			try{
				String tempOriginalValue = originalValue + "[\\s]*[(]([^,](,[^,]+)*)*[)]";
				Set<String> keys = definitionsMap.getKeys();
				for(String key : keys){
					if(key.matches(tempOriginalValue)){
						if(!originalValue.contains("("))
							return "";
					}
					String regexForFuncCall = "[\\w]+[\\s]*[(]([^,](,[^,]+)*)*[)]";
					if(key.matches(regexForFuncCall) && originalValue.matches(regexForFuncCall)){
						String id1 = key.substring(0, key.indexOf('(', 0)).trim();
						String id2 = originalValue.substring(0, originalValue.indexOf('(', 0)).trim();
						if(id1.equals(id2)){
							String parameters1 = key.substring(key.indexOf('(', 0) + 1, key.indexOf(')'));
							String parameters2 = originalValue.substring(originalValue.indexOf('(', 0) + 1, originalValue.lastIndexOf(')'));
							String param1Arr[] = parameters1.split(",");
							String param2Arr[] = parameters2.split(",");
							if(param1Arr.length == param2Arr.length){
								Map<String, String> map = new HashMap<String, String>();
								for(int i = 0; i < param1Arr.length; i++){
									map.put(param1Arr[i].trim(), param2Arr[i].trim());
								}
								Set<Entry<String, String>> entries = map.entrySet();
								List<Entry<String, String>> listOfEntries = new ArrayList<Entry<String,String>>(entries);
								Comparator<Entry<String, String>> comparator = new MyComparator();
								Collections.sort(listOfEntries, comparator);
								String targetStr = definitionsMap.getDefinition(key);
								for(int i = 0; i < listOfEntries.size(); i++){
									String desiredStrToBeReplaced = listOfEntries.get(i).getKey();
									String replacedStr = listOfEntries.get(i).getValue();
									targetStr = replaceStr(targetStr, desiredStrToBeReplaced, replacedStr);
								}
								targetStr = concatenateIfRequired(targetStr);
								return targetStr;
							}
						}
					}
				}
			}catch(PatternSyntaxException e){
				return originalValue;
			}
			return originalValue;
		}
		currValue = concatenateIfRequired(currValue);
		String tokens[] = currValue.split("\\s+");

		if(tokens.length == 0)
			return originalValue;

		for(String token : tokens){
			if(token.equals(originalValue)){
				return originalValue;
			}
			String s = getReplacementText(token);
			if(s != null)
				if(replacementText == null)
					replacementText = "";
			replacementText += s;
		}

		return replacementText;
	}

	private static String replaceStr(String targetStr, String desiredStrToBeReplaced, String replacedStr) {
		if(!targetStr.contains("##" + desiredStrToBeReplaced) && !targetStr.contains("## " + desiredStrToBeReplaced)){
			if(targetStr.contains("#" + desiredStrToBeReplaced) || targetStr.contains("# " + desiredStrToBeReplaced))
			{
				if(targetStr.contains("#" + desiredStrToBeReplaced))
					targetStr = targetStr.replaceAll("#" + desiredStrToBeReplaced, "\""+replacedStr+"\"");
				else
					targetStr = targetStr.replaceAll("# " + desiredStrToBeReplaced, "\""+replacedStr+"\"");
			}
			else
				targetStr = targetStr.replaceAll(desiredStrToBeReplaced, replacedStr);
		}
		else
			targetStr = targetStr.replaceAll(desiredStrToBeReplaced, replacedStr);
		return targetStr;
	}

	private static String concatenateIfRequired(String targetStr) {
		if(targetStr.contains("##") && targetStr.charAt(0) != '"' && targetStr.charAt(targetStr.length()-1) != '"')
		{
			targetStr = targetStr.replaceAll("[\\s]*##[\\s]*", "");
			targetStr = getReplacementText(targetStr);
		}
		return targetStr;
	}
}
/**
 * This class sorts the string based on its length
 * for e.g. if the list contains abc and abcd, then this comparator
 * will sort them into abcd and abc
 * @author ipsg
 *
 */
class MyComparator implements Comparator<Entry<String, String>> {

	@Override
	public int compare(Entry<String, String> o1, Entry<String, String> o2) {
		int returnVal = 0;
		String key1 = o1.getKey();
		String key2 = o2.getKey();

		returnVal = (key1.length() > key2.length()) ? -1 : (key1.length() == key2.length() ? key1.compareTo(key2) : 1);
		return returnVal;
	}

}
