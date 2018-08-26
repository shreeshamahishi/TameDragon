package org.tamedragon.visualization.ide;

public class PropertiesPair {
	
	private String key = null, value = null;
	
	public PropertiesPair(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.value;
	}

}