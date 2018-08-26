package org.tamedragon.common.llvmir.semanticanalysis;

/**
 * Represents a location in the file containing the source code
 *
 */
public class SourceLocation implements Comparable {
	private int lineNum;
	private int pos;
	
	public SourceLocation(){}
	
	public SourceLocation(int lineNum, int pos){
		this.lineNum = lineNum; this.pos = pos;
	}
	
	public SourceLocation(int lineNum){
		this.lineNum = lineNum; this.pos = 0;
	}
	
	public int getLineNum() {
		return lineNum;
	}
	
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public String toString(){
		return "(" + lineNum + ")";
	}
	
	public boolean equals(Object obj){
		return false;  // No two source locations are same
		
	}
	
	public SourceLocation getClone(){
		SourceLocation srcLocation = new SourceLocation();
		srcLocation.setLineNum(lineNum);
		srcLocation.setPos(pos);
		return srcLocation;
	}
	
	public int compareTo(Object object){
		return lineNum - ((SourceLocation)object).getLineNum();
	}
}
