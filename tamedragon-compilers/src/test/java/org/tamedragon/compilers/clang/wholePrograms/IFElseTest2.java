package org.tamedragon.compilers.clang.wholePrograms;
//package com.compilervision.compilers.clang.wholePrograms;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import org.junit.Before;
//import org.junit.Test;
//import org.tamedragon.compilers.clang.tests.TestInitializer;
//
//public class IFElseTest2 extends TestInitializer {
//
//	private String sourceFilePath;
//	private String asmFilePath;
//
//	@Before
//	public void setUp() {
//		sourceFilePath = "CSrc/TranslationToTreeIR/IFElseTest2.c";
//		// Compiling and generating the .s file
//		String[] strArr = new String[1];
//		strArr[0] = "IFElseTest2.c";
//		try {
//			ASMGenenator.main(strArr);
//		} catch (Exception e2) {
//			e2.printStackTrace();
//			throw new AssertionError();
//		}
//		// locating the .s file
//		asmFilePath = sourceFilePath.replaceFirst("c$", "s");
//		FileWriter fstream = null;
//		BufferedWriter out = null;
//		FileReader freader = null;
//		BufferedReader bufferedReader = null;
//		try {
//			// appending printf.s to .s file
//			fstream = new FileWriter(asmFilePath, true);
//			out = new BufferedWriter(fstream);
//			freader = new FileReader("resources" + File.separator + "MIPS"
//					+ File.separator + "printf.s");
//			bufferedReader = new BufferedReader(freader);
//			char[] chBuf = new char[1];
//			out.write("\n");
//			while (bufferedReader.read(chBuf) != -1) {
//				out.write(chBuf);
//				out.flush();
//				fstream.flush();
//			}
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} finally {
//			// Close the input and output stream
//			try {
//				if (bufferedReader != null)
//					bufferedReader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (freader != null)
//					freader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (fstream != null)
//					fstream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (out != null)
//					out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Test
//	public void test1() {
//		BufferedReader reader = null;
//		try {
//			// Running the .s in spim
//			Process p = Runtime.getRuntime().exec("spim -file " + asmFilePath);
//			p.waitFor();
//			reader = new BufferedReader(new InputStreamReader(p
//					.getInputStream()));
//			String line = reader.readLine();
//			while (line != null) {
//				System.out.println(line);
//				// Ignoring the first line of spim output
//				if (line.equals("Loaded: /usr/share/spim/exceptions.s")) {
//					line = reader.readLine();
//					continue;
//				}
//				// Comparing the generated output with the expected one
//				line = reader.readLine();
//			}
//
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} catch (InterruptedException e2) {
//			e2.printStackTrace();
//		} finally {
//			// Close the input and output stream
//			try {
//				if (reader != null)
//					reader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}