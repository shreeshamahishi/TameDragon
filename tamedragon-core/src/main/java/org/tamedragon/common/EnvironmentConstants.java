package org.tamedragon.common;

import java.util.HashMap;

public class EnvironmentConstants {

	// Keys
	public static String DEBUG_OUTPUT_FOLDER = "DEBUG_OUTPUT_FOLDER"; // Output folder for debugging
	public static String MODE = "MODE";                // Debug or release?
	public static String SOURCE_LANGUAGE = "SOURCE_LANGUAGE";     // C or Tiger or C++, etc
	public static String TARGET_LANGUAGE = "TARGET_LANGUAGE";     // MIPS, ARM, etc
	
	// Values
	public static String DEBUG_MODE = "debug";     // Debug
	public static String RELEASE_MODE = "release"; // Release
	public static String SRC_CLANG = "C";          // Source language is C
	public static String SRC_CPLUS = "C++";        // Source language is C+
	public static String SRC_TIGER = "Tiger";      // Source language is Tiger
	public static String TARGET_MIPS = "mips";     // Target language is MIPS
	public static String TARGET_ARM = "arm";       // Target language is ARM
	public static String TARGET_X86= "x86";        // Target language is X86
	
	public static final String TAB = "\t";
	public static final String NEWLINE = "\r\n";
	
	public static HashMap<String, String> VALUES;
	
	// Initialize with some default values
	static {
		VALUES = new HashMap<String, String>();
		VALUES.put(DEBUG_OUTPUT_FOLDER, System.getProperty("java.io.tmpdir"));
		VALUES.put(MODE, DEBUG_MODE);
		VALUES.put(SOURCE_LANGUAGE, SRC_TIGER);
		VALUES.put(TARGET_LANGUAGE, TARGET_MIPS);
	}
}
