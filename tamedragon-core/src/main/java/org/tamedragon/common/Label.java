package org.tamedragon.common;

/**
 * This class keeps track of Label names which is used to keep track of basic blocks.
 */
public class Label  {
	private String name;

	private static int count = 1;

	public String toString(){
		return name;
	}

	public Label(String n) {
		name = n;
	}

	public Label() {
		this("L" + count++);
	}

	public static Label getCurrentLabel(){
		return new Label("L"+ count);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void resetCount(){
		count = 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Label))
			return false;
		Label label = (Label)obj;
		String name1 = getName();
		String name2 = label.getName();
		if(name1.equals(name2))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
