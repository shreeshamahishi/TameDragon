package org.tamedragon.compilers.clang.preprocessor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.jgrapht.Graph;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.tamedragon.compilers.clang.CompilerSettings;
import org.tamedragon.compilers.clang.exceptions.DAGCreationException;
import org.tamedragon.compilers.clang.preprocessor.CPreprocessorLLLexer;
import org.tamedragon.compilers.clang.preprocessor.CPreprocessorLLParser;
import org.tamedragon.compilers.clang.preprocessor.DefinitionsMap;
import org.tamedragon.compilers.clang.preprocessor.PreprocessorSegments;

public class PreprocessorMain {
	
	private final static HashMap<String, String> TRIGRAPH_SEQUENCES = new HashMap<String, String>();
	private final static String TRIGRAPH_SEQUENCE_KEYS[] = 
		new String[]{"\\?\\?=", "\\?\\?/", "\\?\\?'",
		"\\?\\?\\(", "\\?\\?\\)", "\\?\\?\\!",
		"\\?\\?<", "\\?\\?>", "\\?\\?\\-"};
	static {		
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[0], "#");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[1], "\\");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[2], "^");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[3], "[");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[4], "]");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[5], "|");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[6], "{");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[7], "}");
		TRIGRAPH_SEQUENCES.put(TRIGRAPH_SEQUENCE_KEYS[8], "~");
	}

	private static final Logger LOG = LoggerFactory.getLogger(PreprocessorMain.class);
	private static final String CANNOT_READ_SRC = "Error reading into source input stream";	

	private String inputFilePath;
	
	private Graph<String, DefaultEdge> dependenciesDag = new DirectedAcyclicGraph<>(DefaultEdge.class);

	public InputStream process(boolean clearPreviousDefinitions) throws Exception{
		
		if(clearPreviousDefinitions) { 
			// Lets clear previous definitions and create a new dependencies DAG
			DefinitionsMap.getInstance().clearDefinitions(); 
			 dependenciesDag = new DirectedAcyclicGraph<>(DefaultEdge.class);
		}
		
		InputStream sourceInputStream = null;		
		sourceInputStream = replaceTrigraphSequencesAndSpliceLines(inputFilePath);		
		PreprocessorSegments ppSegments = getPreprocessorTranslationByLLParsing(sourceInputStream);

		StringBuffer sb = new StringBuffer();
		if(ppSegments != null){
			sb = ppSegments.process(inputFilePath, dependenciesDag, false);
		}

		try{
			sourceInputStream = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
		}
		catch(Exception e){
			assert false : CANNOT_READ_SRC;
			LOG.info(CANNOT_READ_SRC);
			System.exit(-1);
		}

		return sourceInputStream;
	} 

	public InputStream replaceTrigraphSequencesAndSpliceLines(String inputFilePath){

		StringBuffer inputStr = new StringBuffer();
		ByteArrayInputStream bais = null;
		
		// Check the replace trigraph sequence flag from the settings (default is true)
		boolean replaceTrigraphSequences = true;
		CompilerSettings compilerSettings = CompilerSettings.getInstance();
		String rts = compilerSettings.getInstanceReplaceTrigraphSequences();
		
		if(rts != null && (!("Y".equalsIgnoreCase(rts) || "YES".equalsIgnoreCase(rts)))){
			replaceTrigraphSequences = false;
		}
	
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(inputFilePath));

			String line = null;
			boolean linesSpliced = false;
			while((line = br.readLine()) != null){
				
				if(replaceTrigraphSequences){				
					for(String trigraphSequenceKey: TRIGRAPH_SEQUENCE_KEYS){
						String value = TRIGRAPH_SEQUENCES.get(trigraphSequenceKey);
						line = line.replaceAll(trigraphSequenceKey, value);
					}
				}

				// Trigraphs are replaced (if required and found); now remove back-slash and new line
				if(line.endsWith("\\")){
					line = line.substring(0, line.length() -1);
					inputStr.append(line);
					inputStr.replace(inputStr.length() -1, inputStr.length(), "");
				}					
				else{
					linesSpliced = true;
					inputStr.append(line + "\n");
				}
			}

			if(br != null)
				br.close();

			if(linesSpliced){
				/*Replacement rp = new Replacement();
				rp.setSourceLineNumber();
				rp.set*/
			}			

			bais = new ByteArrayInputStream(inputStr.toString().getBytes("utf-8")) ;

		}				
		catch(Exception e){
			e.printStackTrace();
		}

		return bais;
	}

	public PreprocessorSegments getPreprocessorTranslationByLLParsing(InputStream inputStream){
		PreprocessorSegments preprocessorSegments = null;
		try
		{
			ANTLRInputStream input = new ANTLRInputStream(inputStream);
			CPreprocessorLLLexer lexer = new CPreprocessorLLLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CPreprocessorLLParser parser = new CPreprocessorLLParser(tokens);

			preprocessorSegments = parser.translation_unit();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(inputStream != null){
					inputStream.close();
				}
			}
			catch(Exception ne){
				ne.printStackTrace();
			}
		}

		return preprocessorSegments;
	}
	
	public String getInputFilePath() {
		return inputFilePath;
	}	

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public PreprocessorMain(String inputFilePath){
		this.inputFilePath = inputFilePath;
	}

	public Graph<String, DefaultEdge> getDependenciesDag() {
		return dependenciesDag;
	}

	public void setDependenciesDag(Graph<String, DefaultEdge> dependenciesDag) {
		this.dependenciesDag = dependenciesDag;
	}
	
}
