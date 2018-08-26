package org.tamedragon.common.llvmir.cfrontend.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CFrontendUtils {
	public static boolean isStdFunc(String funcName){
		if(funcName.equals("printf") || funcName.equals("scanf") 
				|| funcName.equals("exit") || funcName.equals("rand") 
				|| funcName.equals("rand_int") || funcName.equals("clearerr") 
				|| funcName.equals("fclose") || funcName.equals("fflush") 
				|| funcName.equals("fgetc") || funcName.equals("fgetpos") 
				|| funcName.equals("fgets") || funcName.equals("fopen") 
				|| funcName.equals("fprintf") || funcName.equals("fputc") 
				|| funcName.equals("fputs") || funcName.equals("fread") 
				|| funcName.equals("freopen") || funcName.equals("fscanf") 
				|| funcName.equals("fseek") || funcName.equals("fsetpos") 
				|| funcName.equals("ftell") || funcName.equals("fwrite") 
				|| funcName.equals("gets"))
			return true;
		else
			return false;
	}
	
	public static List<String> getListOfFormatSpecifiersFromString(String str){
		List<String> formatSpecifiers = new ArrayList<String>();
		Map<Integer,String> indexVsFormatSpecifier = new TreeMap<Integer, String>();
		Integer lastIndex = 0;
		String formatSpecifierArr[] = {"%c","%s","%d","%f","%lf"};
		for(String formatSpecifier : formatSpecifierArr){
			while(lastIndex != -1){

			       lastIndex = str.indexOf(formatSpecifier,lastIndex);

			       if( lastIndex != -1){
			    	   indexVsFormatSpecifier.put(lastIndex, formatSpecifier);
			    	   lastIndex++;
			       }
			}
			lastIndex = 0;
		}
		Set<Map.Entry<Integer, String>> entrySet = indexVsFormatSpecifier.entrySet();
		Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer, String> entry = iterator.next();
			formatSpecifiers.add(entry.getValue());
		}
		return formatSpecifiers;
	}
}
