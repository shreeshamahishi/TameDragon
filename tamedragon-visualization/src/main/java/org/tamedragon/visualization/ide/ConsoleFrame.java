package org.tamedragon.visualization.ide;

/* ConsoleFrame.java
 *
 * @ 1.0
 *
 * @ 2010/11/17
 *
 * @ Sunil Tuppale
 *
 * @ Copyright (c) 2008 - 2010 Skygraph Technologies Pvt. Ltd.
 *
 * This is the main frame of the IDE that displays
 * the compilation visualization.
 */

import javax.swing.JInternalFrame;

public class ConsoleFrame extends JInternalFrame {
	
	private static final long serialVersionUID = 2318410799858473712L;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;

	public ConsoleFrame() {
		super("Console", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		// ...Create the GUI and put it in the window...

		// ...Then set the window size or call pack...
		setSize(300, 300);
		
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
	}
}
