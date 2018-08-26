package org.tamedragon.compilers.clang.abssyntree;

public class Absyn {
	private int lineNum;
	private int pos;
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
}
