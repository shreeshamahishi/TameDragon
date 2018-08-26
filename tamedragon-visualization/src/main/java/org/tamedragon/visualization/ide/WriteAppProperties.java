package org.tamedragon.visualization.ide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

public class WriteAppProperties {
	//private String key = null, value = null, str = null;
	private File file = null;
	private Vector<PropertiesPair> prop = null;

	public WriteAppProperties(File file, Vector <PropertiesPair> properties) {
		this.file = file;
		this.prop = properties;
		// WriteAppProperties();
	}

	public void editProperties() {
		try {
			FileInputStream in = new FileInputStream(file);
			Properties props = new Properties();
			props.load(in);

			for (PropertiesPair sProp : prop) {
				props.setProperty(sProp.getKey(), sProp.getValue());
			}
			props.store(new FileOutputStream(file), "Saving");
		} catch (Exception ex) {

		}
	}
}

