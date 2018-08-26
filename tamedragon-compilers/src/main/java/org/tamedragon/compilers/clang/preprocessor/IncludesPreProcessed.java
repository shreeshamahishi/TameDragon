package org.tamedragon.compilers.clang.preprocessor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class IncludesPreProcessed {
	
	private static HashMap<String, HashMap<String, List<InputStream>>> includeFilesVsPreprocessedSingleton;
	
	private IncludesPreProcessed(){}
	
	public  static HashMap<String, HashMap<String, List<InputStream>>>  getInstance(){
		if(includeFilesVsPreprocessedSingleton == null){
			includeFilesVsPreprocessedSingleton = new HashMap<String, HashMap<String, List<InputStream>>> ();
		}
		
		return includeFilesVsPreprocessedSingleton;
	}
	
	public static void reset(){
		if(includeFilesVsPreprocessedSingleton != null){
			includeFilesVsPreprocessedSingleton.clear();
		}
	}
	
}
