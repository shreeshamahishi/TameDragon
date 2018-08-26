package org.tamedragon.common.llvmir.semanticanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class LLVMErrorHandler {

	public static final String ERROR = "Error: ";
	public static final String E_NO_DECLARATION = "Error: Undefined Value .";
	public static final String E_CANNOT_BE_NULL = "Error: value cannot be null.";
	public static final String E_ALREADY_DEFINED = "Error: Value already defined.";
	public static final String E_DEFINED_WITH_TYPE = "Error: defined with type";

	private HashMap<String, LinkedHashMap<SourceLocation, String>> fileNameVsErrors;

	private static LLVMErrorHandler singletonInstance;

	private LLVMErrorHandler(){
		fileNameVsErrors = new HashMap<String, LinkedHashMap<SourceLocation, String>>();
	}

	public static LLVMErrorHandler getInstance() {
		if(singletonInstance == null)
			singletonInstance = new LLVMErrorHandler();
		return singletonInstance;
	}

	public void reset(){
		fileNameVsErrors.clear();
	}

	public void addError(String fileName, SourceLocation location, String preMsg, String additionalInfo, 
			String mainMsg) {

		String finalMsg = getFinalMsg(preMsg, mainMsg, additionalInfo);

		LinkedHashMap<SourceLocation, String> entriesInErrorColl = singletonInstance.fileNameVsErrors.get(fileName);

		if(entriesInErrorColl == null)
			entriesInErrorColl = new LinkedHashMap<SourceLocation, String>();

		SourceLocation newLocation = location;
		if(entriesInErrorColl.containsKey(location)){			
			newLocation = location.getClone();

		}

		entriesInErrorColl.put(newLocation, finalMsg);
		singletonInstance.fileNameVsErrors.put(fileName, entriesInErrorColl);
	}

	private String getFinalMsg(String preMsg, String mainMsg, String additionalInfo){
		String finalMsg = "";
		String startFlag = LLVMErrorHandler.ERROR;

		if(preMsg != null && !preMsg.equals("")) { 
			// There is a string to attach before the main message		
			mainMsg = mainMsg.substring(LLVMErrorHandler.ERROR.length(), mainMsg.length());
			finalMsg = startFlag + preMsg + " " + mainMsg;
		}
		else{
			finalMsg = mainMsg;
		}

		if(additionalInfo != null && !additionalInfo.equals(""))   
			// There is an additional info message too
			finalMsg += additionalInfo;

		return finalMsg;
	}

	public LinkedHashMap<SourceLocation, String> getErrorsInFile(String fileName){
		sortErrors(fileName);		
		return singletonInstance.fileNameVsErrors.get(fileName);
	}

	public void sortErrors(String fileName){

		LinkedHashMap<SourceLocation, String> errsInFile = singletonInstance.fileNameVsErrors.get(fileName);
		if(errsInFile == null)
			return;

		Set<SourceLocation> keys = errsInFile.keySet();
		ArrayList<SourceLocation> arrayListSrcLocks = new ArrayList<SourceLocation>();
		Iterator<SourceLocation> iter = keys.iterator();
		while(iter.hasNext()){
			arrayListSrcLocks.add(iter.next());
		}

		Collections.sort(arrayListSrcLocks);
		LinkedHashMap<SourceLocation, String> temp = new LinkedHashMap<SourceLocation, String>();

		for(SourceLocation srcLocation: arrayListSrcLocks ){
			String msg = errsInFile.get(srcLocation);
			temp.put(srcLocation, msg);
		}

		singletonInstance.fileNameVsErrors.put(fileName, temp);  // Update
	}

	public int getNumErrors() {
		int numErrors = 0;
		if(singletonInstance.fileNameVsErrors.size() == 0)
			return numErrors;

		// Determine number of errors
		Set<Entry<String, LinkedHashMap<SourceLocation, String>>> entries = singletonInstance.fileNameVsErrors.entrySet();
		Iterator<Entry<String, LinkedHashMap<SourceLocation, String>>> iter = entries.iterator();
		while(iter.hasNext()) {
			Entry<String, LinkedHashMap<SourceLocation, String>>  entry =  iter.next();
			LinkedHashMap<SourceLocation, String> errorsInFile = entry.getValue();

			numErrors += errorsInFile.size();
		}
		return numErrors;
	}

	public Vector<String> getErrorOrWarningMessages(String fileName, SourceLocation location){

		Vector<String> errMsgs = new Vector<String>();

		LinkedHashMap<SourceLocation, String> errorsInfile = singletonInstance.fileNameVsErrors.get(fileName);
		if(errorsInfile == null)
			return errMsgs;

		Set<Entry<SourceLocation, String>>  errEntries = errorsInfile.entrySet();
		Iterator<Entry<SourceLocation, String>> errorsIter = errEntries.iterator();
		while(errorsIter.hasNext()){
			Entry<SourceLocation, String> errEntry =  errorsIter.next();
			SourceLocation srcLocation = (SourceLocation)errEntry.getKey();
			if(srcLocation.getLineNum() == location.getLineNum() &&
					srcLocation.getPos() == location.getPos()){
				String existingMsg = (String)errEntry.getValue();
				errMsgs.addElement(existingMsg);
			}
		}

		return errMsgs;
	}

	public boolean errorOrWarningAlreadyExists(String fileName, SourceLocation location, 
			short errOrWarning, String msg){
		Vector<String> existingMsgs = getErrorOrWarningMessages(fileName, location);
		return existingMsgs.contains(msg);
	}

	public void displayResult() {
		Set<Entry<String, LinkedHashMap<SourceLocation, String>>> entries = singletonInstance.fileNameVsErrors.entrySet();
		Iterator<Entry<String, LinkedHashMap<SourceLocation, String>>> iter = entries.iterator();
		while(iter.hasNext()) {
			Entry<String, LinkedHashMap<SourceLocation, String>> entry = iter.next();
			String fileName = (String)entry.getKey();
			System.out.println(fileName + ": ");

			sortErrors(fileName);

			LinkedHashMap<SourceLocation, String> errorsInFile= (LinkedHashMap<SourceLocation, String>) entry.getValue();
			Set<Entry<SourceLocation, String>> errEntries = errorsInFile.entrySet();
			Iterator<Entry<SourceLocation, String>> errorsIter = errEntries.iterator();
			while(errorsIter.hasNext()){
				Entry<SourceLocation, String> errEntry =  errorsIter.next();
				SourceLocation srcLocation = (SourceLocation)errEntry.getKey();
				String msg = (String)errEntry.getValue();

				msg = srcLocation.getLineNum() + ", " + srcLocation.getPos() + ": " + msg;
				System.out.println(msg);
			}				
		}

		// Print result summary
		displayResultSummary();			
	}

	private void displayResultSummary(){
		int numErrs = getNumErrors();
		if(numErrs == 0) {
			System.out.println("Compiled successfully.");
		}
		else{
			System.out.println("Compilation failed: " + numErrs + " error(s).");
		}
	}
}