package org.tamedragon.common;

/**
 * This is a Utility class which can hold a pair of Objects and provide the getter and setter methods for it.
 * One of its use is in LLVM's GetElementPtr instruction for storing index verses value.
 *
 * @param <First>
 * @param <Second>
 */
public class Pair<First,Second> {
	
	private First first;
	private Second second;
	
	public Pair(First first , Second second){
		this.first = first;
		this.second = second;
	}
	
	public First getFirst() {
		return first;
	}

	public void setFirst(First first) {
		this.first = first;
	}

	public Second getSecond() {
		return second;
	}

	public void setSecond(Second second) {
		this.second = second;
	}
}
