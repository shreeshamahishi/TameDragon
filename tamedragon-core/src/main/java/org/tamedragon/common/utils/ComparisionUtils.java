package org.tamedragon.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ComparisionUtils {
	// TODO We need to do better than comparing emitted instructions.
	
	/**
	 * This function compares two LLVM IRs by doing a line by line checking.
	 * One of the IR will be in memory passed as a List of String, while other
	 * will be in a file (that too should be passed as a method parameter).
	 */
	
	public static boolean compare(List<String> instrsAfterOpt,
			String rootPath, String llvmOutFileName) {

		String llvmReference = rootPath + "/" + llvmOutFileName;

		ClassLoader classLoader = new ComparisionUtils().getClass().getClassLoader();
		File llvmReferenceFile = new File(classLoader.getResource(llvmReference).getFile());
		
		FileReader fileReader = null;
		BufferedReader br = null;

		try{
			fileReader = new FileReader(llvmReferenceFile);
			br = new BufferedReader(fileReader);
			String line = "";
			int count = 0;
			while(line != null){
				line = br.readLine();
				if(line == null){
					break;
				}

				line = line.trim();
				String instrInOptimizedAsm = instrsAfterOpt.get(count).trim();

				// Switch instruction comes as one string so we have to split it, by new line
				if(instrInOptimizedAsm.contains("switch")){
					String string = instrsAfterOpt.get(count).trim();
					String arr[] = string.split("\n");
					instrsAfterOpt.remove(count);
					instrsAfterOpt.addAll(count, Arrays.asList(arr));
					instrInOptimizedAsm = instrsAfterOpt.get(count).trim();
				}

				if(line.length() == 0 && instrInOptimizedAsm.length() == 0){
					count++;
					continue;
				}

				// Strip away comments
				String commentPrefix = "; preds";
				if(line.contains(commentPrefix)){
					if(instrInOptimizedAsm.contains(commentPrefix)){
						line = line.substring(0, line.indexOf(commentPrefix)).trim();
						instrInOptimizedAsm = 
							instrInOptimizedAsm.substring(0, instrInOptimizedAsm.indexOf(commentPrefix)).trim();
					}
					else{
						return false;
					}
				}

				System.out.println("Reference line read : " + line);
				System.out.println("Translated line     : " + instrInOptimizedAsm);

				String reversedPhiParameterOrder = reversePhiParameter(instrInOptimizedAsm);
				if(reversedPhiParameterOrder != null && !line.equals(instrInOptimizedAsm)){
					System.out.println("**** Reference line read              : " + line);
					System.out.println("**** Reversed Translated phi line     : " + reversedPhiParameterOrder);
					instrInOptimizedAsm = reversedPhiParameterOrder;
				}
				
				// Ignore alignment
				if(line.contains("align")){
					line = line.substring(0, line.indexOf(", align"));
				}
				if(instrInOptimizedAsm.contains("align")){
					instrInOptimizedAsm = instrInOptimizedAsm.substring(0, instrInOptimizedAsm.indexOf(", align"));
				}

				if(!line.equals(instrInOptimizedAsm)){
					return false;
				}
				//				assertTrue(line.equals(instrInOptimizedAsm) || 
				//						(reversedPhiParameterOrder != null && line.equals(reversedPhiParameterOrder)));
				count++;
			}

			if(count < instrsAfterOpt.size()){
				for(int i = count; i < instrsAfterOpt.size() ; i++){
					String instr = instrsAfterOpt.get(i).trim();
					return instr.isEmpty();
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

		finally{
			try{
				br.close();
				fileReader.close();
			}catch(Exception e){}
		}
		return true;
	}
	
	private static String reversePhiParameter(String instrInOptimizedAsm) {

		String reversedOrder = null;

		if(!instrInOptimizedAsm.contains(" phi "))
			return null;


		int currStartIndexOfParam = instrInOptimizedAsm.indexOf("[");

		reversedOrder = instrInOptimizedAsm.substring(0,  currStartIndexOfParam);

		Stack<String> paramStack = new Stack<String>();

		while(currStartIndexOfParam > 0){
			String param = instrInOptimizedAsm.substring(currStartIndexOfParam, 
					instrInOptimizedAsm.indexOf("]", currStartIndexOfParam) + 1);
			paramStack.push(param);
			currStartIndexOfParam = instrInOptimizedAsm.indexOf("[", currStartIndexOfParam +1);
		}

		while(!paramStack.isEmpty()){
			reversedOrder += paramStack.pop();
			if(!paramStack.isEmpty())
				reversedOrder += ", ";
		}

		return reversedOrder.trim();
	}
}
