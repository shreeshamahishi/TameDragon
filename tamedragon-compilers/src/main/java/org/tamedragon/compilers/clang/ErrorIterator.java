package org.tamedragon.compilers.clang;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.compilers.clang.exceptions.TokenOutOfBoundsException;

public class ErrorIterator{
	private String fileName;
	private Vector<SourceLocationAndMsg> srcLocationAndMsgs;

	private int currentIndex;
	private int totalErrs;
	
	public ErrorIterator(String fileName){
		srcLocationAndMsgs = new Vector<SourceLocationAndMsg>();
		currentIndex = 0;
				
		ErrorHandler errHandler = ErrorHandler.getInstance();
		LinkedHashMap<SourceLocation, String> errs = errHandler.getErrorsInFile(fileName);
		if(errs == null)
			return;
		
		totalErrs = errs.size();
		
		Set errEntries = errs.entrySet();
		Iterator errorsIter = errEntries.iterator();
		while(errorsIter.hasNext()){
			Map.Entry errEntry = (Map.Entry) errorsIter.next();
			SourceLocation srcLocation = (SourceLocation)errEntry.getKey();
			String msg = (String)errEntry.getValue();
			SourceLocationAndMsg srcLocationAndMsg = new SourceLocationAndMsg(srcLocation, msg);
			srcLocationAndMsgs.addElement(srcLocationAndMsg);
		}		
	}
	
	public boolean hasNext(){
		if(totalErrs == 0)
			return false;
		
		if(currentIndex == totalErrs)
			return false;
		
		return true;
		
	}
	
	public SourceLocationAndMsg next() throws TokenOutOfBoundsException {
		if(currentIndex == totalErrs){
			throw new TokenOutOfBoundsException(TokenOutOfBoundsException.ERROR_MSGS_OUT_OF_BOUNDS);
		}
		
		SourceLocationAndMsg srcLcAndMsg = srcLocationAndMsgs.elementAt(currentIndex);
		currentIndex++;
		return srcLcAndMsg;
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}