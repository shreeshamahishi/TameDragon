package org.tamedragon.visualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.LookAndFeel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicPanelUI;

public class CustomBasicPanelUI extends BasicPanelUI implements SwingConstants {

	protected CustomJPanel panel;
	private boolean floating;
	private int floatingX;
	private int floatingY;
	private RootPaneContainer floatingWindow;
	protected DragWindow dragWindow;
	private Container dockingSource;
	protected int focusedCompIndex = -1;

	protected Color dockingColor = null;
	protected Color floatingColor = null;
	protected Color dockingBorderColor = null;
	protected Color floatingBorderColor = null;

	protected MouseInputListener dockingListener;
	protected PropertyChangeListener propertyListener;

	protected ContainerListener panelContListener;
	protected FocusListener panelFocusListener;
	private Handler handler;

	protected String constraintBeforeFloating = null;

	// Rollover button implementation.
	private static String IS_ROLLOVER = "CustomJPanel.isRollover";
	private boolean rolloverBorders = false;

	private HashMap borderTable = new HashMap();
	private Hashtable rolloverTable = new Hashtable();

	private static String FOCUSED_COMP_INDEX = "CustomJPanel.focusedCompIndex";

	// Shared UI object
    private static PanelUI panelUI;

	public static ComponentUI createUI(JComponent c) {
		if(panelUI == null) {
            panelUI =  new CustomBasicPanelUI();
	}
        return panelUI;
	}

	public void installUI(JComponent c) {
		panel = (CustomJPanel) c;

		// Set defaults
		installDefaults();
		installComponents();
		installListeners();

		// Initialize instance vars
		floating = false;
		floatingX = floatingY = 0;
		floatingWindow = null;

		LookAndFeel.installProperty(c, "opaque", Boolean.TRUE);

		if (c.getClientProperty(FOCUSED_COMP_INDEX) != null) {
			focusedCompIndex = ((Integer) (c
					.getClientProperty(FOCUSED_COMP_INDEX))).intValue();
		}
	}

	public void uninstallUI(JComponent c) {

		// Clear defaults
		uninstallDefaults();
		uninstallComponents();
		uninstallListeners();

		// Clear instance vars
		if (isFloating() == true)
			setFloating(false, null);

		floatingWindow = null;
		dragWindow = null;
		dockingSource = null;

		c.putClientProperty(FOCUSED_COMP_INDEX, new Integer(focusedCompIndex));
	}

	protected void installDefaults() {
		LookAndFeel.installBorder(panel, "Panel.border");
		LookAndFeel.installColorsAndFont(panel, "Panel.background",
				"Panel.foreground", "Panel.font");
		// Toolbar specific defaults
		if (dockingColor == null || dockingColor instanceof UIResource)
			dockingColor = UIManager.getColor("ToolBar.dockingBackground");
		if (floatingColor == null || floatingColor instanceof UIResource)
			floatingColor = UIManager.getColor("ToolBar.floatingBackground");
		if (dockingBorderColor == null
				|| dockingBorderColor instanceof UIResource)
			dockingBorderColor = UIManager
					.getColor("ToolBar.dockingForeground");
		if (floatingBorderColor == null
				|| floatingBorderColor instanceof UIResource)
			floatingBorderColor = UIManager
					.getColor("ToolBar.floatingForeground");

		// ToolBar rollover button borders
		Object rolloverProp = panel.getClientProperty(IS_ROLLOVER);
		if (rolloverProp == null) {
			rolloverProp = UIManager.get("Panel.isRollover");
		}
		if (rolloverProp != null) {
			rolloverBorders = ((Boolean) rolloverProp).booleanValue();
		}

		setRolloverBorders(isRolloverBorders());
	}

	protected void uninstallDefaults() {
		LookAndFeel.uninstallBorder(panel);
		dockingColor = null;
		floatingColor = null;
		dockingBorderColor = null;
		floatingBorderColor = null;

		installNormalBorders(panel);
	}

	protected void installComponents() {
	}

	protected void uninstallComponents() {
	}

	protected void installListeners() {
		dockingListener = createDockingListener();

		if (dockingListener != null) {
			panel.addMouseMotionListener(dockingListener);
			panel.addMouseListener(dockingListener);
		}

		propertyListener = createPropertyListener(); // added in setFloating
		if (propertyListener != null) {
			panel.addPropertyChangeListener(propertyListener);
		}

		panelContListener = createToolBarContListener();
		if (panelContListener != null) {
			panel.addContainerListener(panelContListener);
		}

		panelFocusListener = createToolBarFocusListener();

		if (panelFocusListener != null) {
			// Put focus listener on all components in toolbar
			Component[] components = panel.getComponents();

			for (int i = 0; i < components.length; ++i) {
				components[i].addFocusListener(panelFocusListener);
			}
		}
	}

	protected void uninstallListeners() {
		if (dockingListener != null) {
			panel.removeMouseMotionListener(dockingListener);
			panel.removeMouseListener(dockingListener);

			dockingListener = null;
		}

		if (propertyListener != null) {
			panel.removePropertyChangeListener(propertyListener);
			propertyListener = null; // removed in setFloating
		}

		if (panelContListener != null) {
			panel.removeContainerListener(panelContListener);
			panelContListener = null;
		}

		if (panelFocusListener != null) {
			// Remove focus listener from all components in toolbar
			Component[] components = panel.getComponents();

			for (int i = 0; i < components.length; ++i) {
				components[i].removeFocusListener(panelFocusListener);
			}

			panelFocusListener = null;
		}
		handler = null;
	}

	/**
	 * Creates a window which contains the toolbar after it has been
	 * dragged out from its container
	 * @return a <code>RootPaneContainer</code> object, containing the toolbar.
	 * @since 1.4
	 */
	protected RootPaneContainer createFloatingWindow(CustomJPanel panel) {
		class PanelDialog extends JDialog {
			public PanelDialog(Frame owner, String title, boolean modal) {
				super(owner, title, modal);
			}

			public PanelDialog(Dialog owner, String title, boolean modal) {
				super(owner, title, modal);
			}
		}

		JDialog dialog;
		Window window = SwingUtilities.getWindowAncestor(panel);
		if (window instanceof Frame) {
			dialog = new PanelDialog((Frame) window, panel.getName(), false);
		} else if (window instanceof Dialog) {
			dialog = new PanelDialog((Dialog) window, panel.getName(), false);
		} else {
			dialog = new PanelDialog((Frame) null, panel.getName(), false);
		}

		dialog.getRootPane().setName("Panel.FloatingWindow");
		dialog.setTitle(panel.getName());
		WindowListener wl = createFrameListener();
		dialog.addWindowListener(wl);
		return dialog;
	}

	protected DragWindow createDragWindow(CustomJPanel jpanel) {
		Window frame = null;
		if (panel != null) {
			Container p;
			for (p = panel.getParent(); p != null && !(p instanceof Window); p = p
					.getParent())
				;
			if (p != null && p instanceof Window)
				frame = (Window) p;
		}
		if (floatingWindow == null) {
			floatingWindow = createFloatingWindow(panel);
		}
		if (floatingWindow instanceof Window)
			frame = (Window) floatingWindow;
		DragWindow dragWindow = new DragWindow(frame);
		return dragWindow;
	}

	/**
	 * Sets the flag for enabling rollover borders on the toolbar and it will
	 * also install the apropriate border depending on the state of the flag.
	 *
	 * @param rollover if true, rollover borders are installed.
	 *	      Otherwise non-rollover borders are installed
	 * @see #isRolloverBorders
	 * @since 1.4
	 */
	public void setRolloverBorders(boolean rollover) {
		rolloverBorders = rollover;

		if (rolloverBorders) {
			installRolloverBorders(panel);
		} else {
			installNonRolloverBorders(panel);
		}
	}

	/**
	 * Installs rollover borders on all the child components of the JComponent.
	 * <p>
	 * This is a convenience method to call <code>setBorderToRollover</code>
	 * for each child component.
	 *
	 * @param c container which holds the child components (usally a JToolBar)
	 * @see #setBorderToRollover
	 * @since 1.4
	 */
	protected void installRolloverBorders(JComponent c) {
		// Put rollover borders on buttons
		Component[] components = c.getComponents();

		for (int i = 0; i < components.length; ++i) {
			if (components[i] instanceof JComponent) {
				((JComponent) components[i]).updateUI();
				setBorderToRollover(components[i]);
			}
		}
	}

	/**
	 * Installs non-rollover borders on all the child components of the JComponent.
	 * A non-rollover border is the border that is installed on the child component
	 * while it is in the toolbar.
	 * <p>
	 * This is a convenience method to call <code>setBorderToNonRollover</code>
	 * for each child component.
	 *
	 * @param c container which holds the child components (usally a JToolBar)
	 * @see #setBorderToNonRollover
	 * @since 1.4
	 */
	protected void installNonRolloverBorders(JComponent c) {
		// Put non-rollover borders on buttons. These borders reduce the margin.
		Component[] components = c.getComponents();

		for (int i = 0; i < components.length; ++i) {
			if (components[i] instanceof JComponent) {
				((JComponent) components[i]).updateUI();
				setBorderToNonRollover(components[i]);
			}
		}
	}

	/**
	 * Installs normal borders on all the child components of the JComponent.
	 * A normal border is the original border that was installed on the child
	 * component before it was added to the toolbar.
	 * <p>
	 * This is a convenience method to call <code>setBorderNormal</code>
	 * for each child component.
	 *
	 * @param c container which holds the child components (usally a JToolBar)
	 * @see #setBorderToNonRollover
	 * @since 1.4
	 */
	protected void installNormalBorders(JComponent c) {
		// Put back the normal borders on buttons
		Component[] components = c.getComponents();

		for (int i = 0; i < components.length; ++i) {
			setBorderToNormal(components[i]);
		}
	}

	/**
	 * Sets the border of the component to have a rollover border which
	 * was created by <code>createRolloverBorder</code>.
	 *
	 * @param c component which will have a rollover border installed
	 * @see #createRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToRollover(Component c) {
		if (c instanceof AbstractButton) {
			AbstractButton b = (AbstractButton) c;

			Border border = (Border) borderTable.get(b);
			if (border == null || border instanceof UIResource) {
				borderTable.put(b, b.getBorder());
			}

			rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE
					: Boolean.FALSE);
			b.setRolloverEnabled(true);
		}
	}

	/**
	 * Sets the border of the component to have a non-rollover border which
	 * was created by <code>createNonRolloverBorder</code>.
	 *
	 * @param c component which will have a non-rollover border installed
	 * @see #createNonRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToNonRollover(Component c) {
		if (c instanceof AbstractButton) {
			AbstractButton b = (AbstractButton) c;

			Border border = (Border) borderTable.get(b);
			if (border == null || border instanceof UIResource) {
				borderTable.put(b, b.getBorder());
			}

			rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE
					: Boolean.FALSE);
			b.setRolloverEnabled(false);
		}
	}

	/**
	 * Sets the border of the component to have a normal border.
	 * A normal border is the original border that was installed on the child
	 * component before it was added to the toolbar.
	 *
	 * @param c component which will have a normal border re-installed
	 * @see #createNonRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToNormal(Component c) {
		if (c instanceof AbstractButton) {
			AbstractButton b = (AbstractButton) c;

			Border border = (Border) borderTable.remove(b);
			b.setBorder(border);

			Boolean value = (Boolean) rolloverTable.remove(b);
			if (value != null) {
				b.setRolloverEnabled(value.booleanValue());
			}
		}
	}

	protected class DragWindow extends Window {
		private static final long serialVersionUID = 1L;
		Color borderColor = Color.gray;
		Point offset; // offset of the mouse cursor inside the DragWindow

		DragWindow(Window w) {
			super(w);
		}

		public Point getOffset() {
			return offset;
		}

		public void setOffset(Point p) {
			this.offset = p;
		}

		public void setBorderColor(Color c) {
			if (this.borderColor == c)
				return;
			this.borderColor = c;
			repaint();
		}

		public Color getBorderColor() {
			return this.borderColor;
		}

		public void paint(Graphics g) {
			paintDragWindow(g);
			// Paint the children
			super.paint(g);
		}

		public Insets getInsets() {
			return new Insets(1, 1, 1, 1);
		}
	}

	/**
	 * Paints the contents of the window used for dragging.
	 *
	 * @param g Graphics to paint to.
	 * @throws NullPointerException is <code>g</code> is null
	 * @since 1.5
	 */
	protected void paintDragWindow(Graphics g) {
		g.setColor(dragWindow.getBackground());
		int w = dragWindow.getWidth();
		int h = dragWindow.getHeight();
		g.fillRect(0, 0, w, h);
		g.setColor(dragWindow.getBorderColor());
		g.drawRect(0, 0, w - 1, h - 1);
	}

	private class Handler implements ContainerListener, FocusListener,
			MouseInputListener, PropertyChangeListener {

		//
		// ContainerListener
		//
		public void componentAdded(ContainerEvent evt) {
			Component c = evt.getChild();

			if (panelFocusListener != null) {
				c.addFocusListener(panelFocusListener);
			}
		}

		public void componentRemoved(ContainerEvent evt) {
			Component c = evt.getChild();

			if (panelFocusListener != null) {
				c.removeFocusListener(panelFocusListener);
			}
		}

		//
		// FocusListener
		//
		public void focusGained(FocusEvent evt) {
			Component c = evt.getComponent();
		}

		public void focusLost(FocusEvent evt) {
		}

		//
		// MouseInputListener (DockingListener)
		//
		JPanel panel;
		boolean isDragging = false;
		Point origin = null;

		public void mousePressed(MouseEvent evt) {
			if (!panel.isEnabled()) {
				return;
			}
			isDragging = false;
		}

		public void mouseReleased(MouseEvent evt) {
			if (!panel.isEnabled()) {
				return;
			}
			if (isDragging == true) {
				Point position = evt.getPoint();
				if (origin == null)
					origin = evt.getComponent().getLocationOnScreen();
				floatAt(position, origin);
			}
			origin = null;
			isDragging = false;
		}

		public void mouseDragged(MouseEvent evt) {
			if (!panel.isEnabled()) {
				return;
			}
			isDragging = true;
			Point position = evt.getPoint();
			if (origin == null) {
				origin = evt.getComponent().getLocationOnScreen();
			}
			dragTo(position, origin);
		}

		public void mouseClicked(MouseEvent evt) {
		}

		public void mouseEntered(MouseEvent evt) {
		}

		public void mouseExited(MouseEvent evt) {
		}

		public void mouseMoved(MouseEvent evt) {
		}

		//
		// PropertyChangeListener
		//
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			if (propertyName == "lookAndFeel") {
				panel.updateUI();
			} else if (propertyName == IS_ROLLOVER) {
				installNormalBorders(panel);
				setRolloverBorders(((Boolean) evt.getNewValue()).booleanValue());
			}
		}
	}

	protected void floatAt(Point position, Point origin) {
		if (panel.isFloatable() == true) {
			try {
				Point offset = dragWindow.getOffset();
				if (offset == null) {
					offset = position;
					dragWindow.setOffset(offset);
				}
				Point global = new Point(origin.x + position.x, origin.y
						+ position.y);
				setFloatingLocation(global.x - offset.x, global.y - offset.y);
				if (dockingSource != null) {
					Point dockingPosition = dockingSource.getLocationOnScreen();
					Point comparisonPoint = new Point(global.x
							- dockingPosition.x, global.y - dockingPosition.y);
					if (canDock(dockingSource, comparisonPoint)) {
						setFloating(false, comparisonPoint);
					} else {
						setFloating(true, null);
					}
				} else {
					setFloating(true, null);
				}
				dragWindow.setOffset(null);
			} catch (IllegalComponentStateException e) {
			}
		}
	}

	public void setFloatingLocation(int x, int y) {
		floatingX = x;
		floatingY = y;
	}

	public boolean isFloating() {
		return floating;
	}

	public void setFloating(boolean b, Point p) {
		if (panel.isFloatable() == true) {
			boolean visible = false;
			Window ancestor = SwingUtilities.getWindowAncestor(panel);
			if (ancestor != null) {
				visible = ancestor.isVisible();
			}
			if (dragWindow != null)
			{
				dragWindow.setVisible(false);
			}
			this.floating = b;
			if (floatingWindow == null) {
				floatingWindow = createFloatingWindow(panel);
			}
			if (b == true) {
				if (dockingSource == null) {
					dockingSource = panel.getParent();
					dockingSource.remove(panel);
				}
				constraintBeforeFloating = calculateConstraint();
				if (propertyListener != null)
					UIManager.addPropertyChangeListener(propertyListener);
				floatingWindow.getContentPane().add(panel, BorderLayout.CENTER);
				if (floatingWindow instanceof Window) {
					((Window) floatingWindow).setPreferredSize(new Dimension(300, 200));
					((Window) floatingWindow).setLocation(floatingX, floatingY);
					if (visible) {
						((Window) floatingWindow).setVisible(true);
					} else {
						ancestor.addWindowListener(new WindowAdapter() {
							public void windowOpened(WindowEvent e) {
								((Window) floatingWindow).setVisible(true);
							}
						});
					}
				}
			} else {
				if (floatingWindow == null)
					floatingWindow = createFloatingWindow(panel);
				if (floatingWindow instanceof Window)
					((Window) floatingWindow).setVisible(false);
				floatingWindow.getContentPane().remove(panel);
				String constraint = getDockingConstraint(dockingSource, p);
				if (constraint == null) {
					constraint = BorderLayout.NORTH;
				}
				if (dockingSource == null)
					dockingSource = panel.getParent();
				if (propertyListener != null)
					UIManager.removePropertyChangeListener(propertyListener);
				dockingSource.add(constraint, panel);
			}
			dockingSource.invalidate();
			Container dockingSourceParent = dockingSource.getParent();
			if (dockingSourceParent != null)
				dockingSourceParent.validate();
			dockingSource.repaint();
		}
	}

	/**
	 * Gets the color displayed when over a docking area
	 */
	public Color getDockingColor() {
		return dockingColor;
	}

	/**
	 * Sets the color displayed when over a docking area
	 */
	public void setDockingColor(Color c) {
		this.dockingColor = c;
	}

	/**
	 * Gets the color displayed when over a floating area
	 */
	public Color getFloatingColor() {
		return floatingColor;
	}

	/**
	 * Sets the color displayed when over a floating area
	 */
	public void setFloatingColor(Color c) {
		this.floatingColor = c;
	}

	private boolean isBlocked(Component comp, Object constraint) {
		if (comp instanceof Container) {
			Container cont = (Container) comp;
			LayoutManager lm = cont.getLayout();
			if (lm instanceof BorderLayout) {
				BorderLayout blm = (BorderLayout) lm;
				Component c = blm.getLayoutComponent(cont, constraint);
				return (c != null && c != panel);
			}
		}
		return false;
	}

	public boolean canDock(Component c, Point p) {
		return (p != null && getDockingConstraint(c, p) != null);
	}

	private String calculateConstraint() {
		String constraint = null;
		LayoutManager lm = dockingSource.getLayout();
		if (lm instanceof BorderLayout) {
			constraint = (String) ((BorderLayout) lm).getConstraints(panel);
		}
		else if (dockingSource instanceof JSplitPane)
		{
			if (((JSplitPane) dockingSource).getLeftComponent() == panel) {
				constraint = JSplitPane.LEFT;
			}
			else
				constraint = JSplitPane.RIGHT;
		}
		return (constraint != null) ? constraint : constraintBeforeFloating;
	}

	private String getDockingConstraint(Component c, Point p) {
		if (c instanceof JSplitPane)
		{
				return JSplitPane.RIGHT;
		}
		return constraintBeforeFloating;
	}

	protected void dragTo(Point position, Point origin) {
		if (panel.isFloatable() == true) {
			try {
				if (dragWindow == null)
					dragWindow = createDragWindow(panel);
				Point offset = dragWindow.getOffset();
				if (offset == null) {
					Dimension size = panel.getPreferredSize();
					offset = new Point(size.width / 2, size.height / 2);
					dragWindow.setOffset(offset);
				}
				Point global = new Point(origin.x + position.x, origin.y
						+ position.y);
				Point dragPoint = new Point(global.x - offset.x, global.y
						- offset.y);
				if (dockingSource == null)
					dockingSource = panel.getParent();
				constraintBeforeFloating = calculateConstraint();
				Point dockingPosition = dockingSource.getLocationOnScreen();
				Point comparisonPoint = new Point(global.x - dockingPosition.x,
						global.y - dockingPosition.y);
				if (canDock(dockingSource, comparisonPoint)) {
					dragWindow.setBackground(getDockingColor());
					String constraint = getDockingConstraint(dockingSource,
							comparisonPoint);
					dragWindow.setBorderColor(dockingBorderColor);
				} else {
					dragWindow.setBackground(getFloatingColor());
					dragWindow.setBorderColor(floatingBorderColor);
				}

				dragWindow.setLocation(dragPoint.x, dragPoint.y);
				if (dragWindow.isVisible() == false) {
					Dimension size = panel.getPreferredSize();
					dragWindow.setSize(size.width, size.height);
					dragWindow.setVisible(true);
				}
			} catch (IllegalComponentStateException e) {
			}
		}
	}

	/**
	 * Returns a flag to determine whether rollover button borders
	 * are enabled.
	 *
	 * @return true if rollover borders are enabled; false otherwise
	 * @see #setRolloverBorders
	 * @since 1.4
	 */
	public boolean isRolloverBorders() {
		return rolloverBorders;
	}

	public CustomBasicPanelUI() {
	}

	private Handler getHandler() {
		if (handler == null) {
			handler = new Handler();
		}
		return handler;
	}

	protected ContainerListener createToolBarContListener() {
		return getHandler();
	}

	protected FocusListener createToolBarFocusListener() {
		return getHandler();
	}

	protected PropertyChangeListener createPropertyListener() {
		return getHandler();
	}

	protected MouseInputListener createDockingListener() {
		getHandler().panel = panel;
		return getHandler();
	}

	protected WindowListener createFrameListener() {
		return new FrameListener();
	}

	protected class FrameListener extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			if (panel.isFloatable() == true) {
				if (dragWindow != null)
					dragWindow.setVisible(false);
				floating = false;
				if (floatingWindow == null)
					floatingWindow = createFloatingWindow(panel);
				if (floatingWindow instanceof Window)
					((Window) floatingWindow).setVisible(false);
				floatingWindow.getContentPane().remove(panel);
				String constraint = constraintBeforeFloating;
				if (dockingSource == null)
					dockingSource = panel.getParent();
				if (propertyListener != null)
					UIManager.removePropertyChangeListener(propertyListener);
				dockingSource.add(panel, constraint);
				dockingSource.invalidate();
				Container dockingSourceParent = dockingSource.getParent();
				if (dockingSourceParent != null)
					dockingSourceParent.validate();
				dockingSource.repaint();
			}
		}

	}

	protected class PanelContListener implements ContainerListener {
		// NOTE: This class exists only for backward compatability. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		public void componentAdded(ContainerEvent e) {
			getHandler().componentAdded(e);
		}

		public void componentRemoved(ContainerEvent e) {
			getHandler().componentRemoved(e);
		}

	}

	protected class PanelFocusListener implements FocusListener {
		// NOTE: This class exists only for backward compatability. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		public void focusGained(FocusEvent e) {
			getHandler().focusGained(e);
		}

		public void focusLost(FocusEvent e) {
			getHandler().focusLost(e);
		}
	}

	protected class PropertyListener implements PropertyChangeListener {
		// NOTE: This class exists only for backward compatability. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		public void propertyChange(PropertyChangeEvent e) {
			getHandler().propertyChange(e);
		}
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of BasicToolBarUI.
	 */
	public class DockingListener implements MouseInputListener {
		// NOTE: This class exists only for backward compatability. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		protected CustomJPanel jpanel;
		protected boolean isDragging = false;
		protected Point origin = null;

		public DockingListener(CustomJPanel t) {
			this.jpanel = t;
			getHandler().panel = t;
		}

		public void mouseClicked(MouseEvent e) {
			getHandler().mouseClicked(e);
		}

		public void mousePressed(MouseEvent e) {
			getHandler().panel = jpanel;
			getHandler().mousePressed(e);
			isDragging = getHandler().isDragging;
		}

		public void mouseReleased(MouseEvent e) {
			getHandler().panel = jpanel;
			getHandler().isDragging = isDragging;
			getHandler().origin = origin;
			getHandler().mouseReleased(e);
			isDragging = getHandler().isDragging;
			origin = getHandler().origin;
		}

		public void mouseEntered(MouseEvent e) {
			getHandler().mouseEntered(e);
		}

		public void mouseExited(MouseEvent e) {
			getHandler().mouseExited(e);
		}

		public void mouseDragged(MouseEvent e) {
			getHandler().panel = jpanel;
			getHandler().origin = origin;
			getHandler().mouseDragged(e);
			isDragging = getHandler().isDragging;
			origin = getHandler().origin;
		}

		public void mouseMoved(MouseEvent e) {
			getHandler().mouseMoved(e);
		}
	}

	/**
	 * A border which is like a Margin border but it will only honor the margin
	 * if the margin has been explicitly set by the developer.
	 *
	 * Note: This is identical to the package private class
	 * MetalBorders.RolloverMarginBorder and should probably be consolidated.
	 */
	static class RolloverMarginBorder extends EmptyBorder {

		public RolloverMarginBorder() {
			super(3, 3, 3, 3); // hardcoded margin for JLF requirements.
		}

		public Insets getBorderInsets(Component c) {
			return getBorderInsets(c, new Insets(0, 0, 0, 0));
		}

		public Insets getBorderInsets(Component c, Insets insets) {
			Insets margin = null;

			if (c instanceof AbstractButton) {
				margin = ((AbstractButton) c).getMargin();
			}
			if (margin == null || margin instanceof UIResource) {
				// default margin so replace
				insets.left = left;
				insets.top = top;
				insets.right = right;
				insets.bottom = bottom;
			} else {
				// Margin which has been explicitly set by the user.
				insets.left = margin.left;
				insets.top = margin.top;
				insets.right = margin.right;
				insets.bottom = margin.bottom;
			}
			return insets;
		}
	}
}
