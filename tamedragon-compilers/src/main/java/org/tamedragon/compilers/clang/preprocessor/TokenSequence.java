package org.tamedragon.compilers.clang.preprocessor;

import java.util.ArrayList;
import java.util.List;

public class TokenSequence extends Absyn {
	
	private List<String> sequence;
	
	public TokenSequence(){
		sequence = new ArrayList<String>();
	}
	
	public void addToken(String token){
		sequence.add(token);
	}

	public List<String> getSequence() {
		return sequence;
	}

	public void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
	
	public void replaceToken(int index, String replacement){
		sequence.set(index, replacement);
	}

	public String toString(){
		String ret = "";
		for(String token : sequence){
			ret += token;
		}
		return ret;
	}
}
