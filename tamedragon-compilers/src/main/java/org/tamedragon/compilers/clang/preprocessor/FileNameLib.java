package org.tamedragon.compilers.clang.preprocessor;

public class FileNameLib extends Absyn {
	
	private String libraryFileName;	

	public FileNameLib(){}
	
	public FileNameLib(int lineNum, String libFileName){
		setLineNum(lineNum);
		this.libraryFileName = libFileName;
	}

	public String getLibraryFileName() {
		return libraryFileName;
	}

	public void setConstExpr(String libFileName) {
		this.libraryFileName = libFileName;
	}	
	
	public void addToFileName(String token){
		libraryFileName += token;
	}
	
	public String toString(){
		return libraryFileName;
	}
}
