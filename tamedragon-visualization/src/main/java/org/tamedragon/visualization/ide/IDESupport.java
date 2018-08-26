package org.tamedragon.visualization.ide;

/* IDESupport.java
 *
 * @ 1.0
 *
 * @ 2010/11/04
 *
 * @ Sunil Tuppale
 *
 * @ Copyright (c) 2008 - 2010 Skygraph Technologies Pvt. Ltd.
 *
 * This class acts as a facade to implement the facade pattern
 *
 */

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class IDESupport {

	//private static final Logger ideSupportlogger = Logger.getLogger(IDESupport.class);
	static private final String PREFS_BUNDLE_BASENAME = "resources.prefs";
	static private final String BUNDLE_BASENAME = "resources.ide", PREFERRED_LOCALE_KEY = "locale";

	static private final JPanel statusArea = new JPanel();
	static private final JLabel status = new JLabel();
	static private ResourceBundle preferences, resources;
	static private Locale locale;

	static {
		ClassLoader loader = IDESupport.class.getClassLoader();
		ResourceBundle.clearCache(loader);
		
		//PropertyConfigurator.configure("log4j.properties");
		try {
			preferences = ResourceBundle.getBundle(PREFS_BUNDLE_BASENAME);
			locale = new Locale(preferences.getString(PREFERRED_LOCALE_KEY));
		} catch (java.util.MissingResourceException ex) {
//			ideSupportlogger.error("ERROR: cannot find preferences properties file "
//					+ BUNDLE_BASENAME);
//			ideSupportlogger.error(IDESupport.getExceptionStackTraceAsString(ex));
		}

		try {
			resources = ResourceBundle.getBundle(BUNDLE_BASENAME, locale);

		} catch (java.util.MissingResourceException ex) {
//			ideSupportlogger.error("ERROR: cannot find properties file for "
//					+ BUNDLE_BASENAME);
//			ideSupportlogger.error(IDESupport.getExceptionStackTraceAsString(ex));
			ex.printStackTrace();
		}
		//ideSupportlogger.info("<===== Initialization Successful ======>");
	};

	// Disallow direct instantiation
	private IDESupport() {
	}

	// This method is called from the IDEMainFrame class to launch
	// the frame of the IDE with all the properties set here
	public static void launch(final JFrame f, String title, final int x,
			final int y, final int w, int h) {
		f.setTitle(title);
		f.setBounds(x, y, w, h);
		f.setVisible(true);
		f.setResizable(true);

		status.setHorizontalAlignment(JLabel.LEFT);

		statusArea.setBorder(BorderFactory.createEtchedBorder());
		statusArea.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		statusArea.add(status);

		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static Locale getLocale() {
		return locale;
	}

	public static JMenu addMenu(final JFrame f, String titleKey,
			String[] itemKeys) {

		JMenuBar mb = f.getJMenuBar();
		if (mb == null) {
			mb = new JMenuBar();
			f.setJMenuBar(mb);
		}

		JMenu menu = new JMenu(IDESupport.getResource(titleKey));

		for (int i = 0; i < itemKeys.length; ++i) {
			menu.add(new JMenuItem(IDESupport.getResource(itemKeys[i])));
		}
		mb.add(menu);
		return menu;
	}

	public static JPanel getStatusArea() {
		return statusArea;
	}

	public static void showStatus(String s) {
		status.setText(s);
	}

	// The main method that returns the properties associated with the IDE
	public static String getResource(String key) {
		return (resources == null) ? null : resources.getString(key);
	}

	public static String formatMessage(String patternKey, String[] params) {
		String pattern = IDESupport.getResource(patternKey);
		MessageFormat fmt = new MessageFormat(pattern);
		return fmt.format(params);
	}
	
	// A utility method that converts an exception stack trace into a String
	public static String getExceptionStackTraceAsString(java.lang.Exception ex) {
		
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
