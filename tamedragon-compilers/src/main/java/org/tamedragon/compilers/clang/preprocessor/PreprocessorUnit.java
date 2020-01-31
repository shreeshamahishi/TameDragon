package org.tamedragon.compilers.clang.preprocessor;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface PreprocessorUnit {
	public static final int INCLUDE_DIRECTIVE = 0;
	public static final int PROGRAM_CODE = 1;
	public static final int DEFINITION = 2;
	public static final int LINE = 3;
	public static final int ERROR = 4;
	public static final int PRAGMA = 5;
	public static final int CONDITIONAL = 6;
	public static final int NULL_DIRECTIVE = 7;
	public static final int WARNING = 8;
	
	public int getPreprocessorUnitType();
	public StringBuffer process(String sourceFilePath, Graph<String, DefaultEdge> dependenciesDag) throws Exception;
}
