package org.tamedragon.visualization;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.PanelUI;

public class DockingFrame extends JPanel {

	private static final long serialVersionUID = 8204020013663690491L;

	JSplitPane hsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane vsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

	double hsplitDividerPos = 0.0;
	double vsplitDividerPos = 1.0;

	JTabbedPane tabbedPane1 = new MyTabbedPane();
	JTabbedPane tabbedPane2 = new MyTabbedPane();
	JTabbedPane tabbedPane3 = new MyTabbedPane();

	protected MouseInputListener dockingListener;
	protected PropertyChangeListener propertyListener;

	protected ContainerListener panelContListener;
	protected FocusListener panelFocusListener;

	boolean isDragging = false;
	boolean maximize = false;
	Handler handler = new Handler();

	public static final String TOP = "top";
	public static final String RIGHT = "right";
	public static final String LEFT = "left";
	public static final String BOTTOM = "bottom";

	public DockingFrame(String name) {
		setName(name);
		setLayout(new BorderLayout());
		buildPane();
	}

	void buildPane() {
		vsplitPane.add(tabbedPane1, JSplitPane.LEFT);
		vsplitPane.add(tabbedPane2, JSplitPane.RIGHT);
		hsplitPane.add(vsplitPane, JSplitPane.TOP);
//		hsplitPane.add(tabbedPane3, JSplitPane.BOTTOM);
		hsplitPane.setDividerSize(3);
		vsplitPane.setDividerSize(3);
		add(hsplitPane);

		if (dockingListener != null) {
			tabbedPane1.addMouseMotionListener(dockingListener);
			tabbedPane1.addMouseListener(dockingListener);

			tabbedPane2.addMouseMotionListener(dockingListener);
			tabbedPane2.addMouseListener(dockingListener);

			tabbedPane3.addMouseMotionListener(dockingListener);
			tabbedPane3.addMouseListener(dockingListener);
		}

		if (panelContListener != null) {
			tabbedPane1.addContainerListener(panelContListener);
			tabbedPane2.addContainerListener(panelContListener);
			tabbedPane3.addContainerListener(panelContListener);
		}

		tabbedPane1.addFocusListener(panelFocusListener);
		tabbedPane2.addFocusListener(panelFocusListener);
		tabbedPane3.addFocusListener(panelFocusListener);

	}

	protected void installListeners() {
		dockingListener = createDockingListener();

		propertyListener = createPropertyListener(); // added in setFloating
		if (propertyListener != null) {
			addPropertyChangeListener(propertyListener);
		}

		panelContListener = createDockFrameContListener();
		panelFocusListener = createDockFrameFocusListener();
	}


	@Override
	public void setUI(PanelUI ui) {
		super.setUI(ui);
		installListeners();
	}

	public DockingFrame(LayoutManager layout) {
		super(layout);
	}

	public DockingFrame(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public DockingFrame(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	@Override
	protected void addImpl(Component comp, Object constraints, int index) {
		// TODO Auto-generated method stub
		super.addImpl(comp, constraints, index);
	}

	public void add(Component component, String title, Object constraints) {
		if (constraints == LEFT) {
			tabbedPane1.add(component, title);
		}
		else if (constraints == RIGHT)
		{
			tabbedPane2.add(component, title);
		}
		else
			tabbedPane3.add(component, title);
		updateDividerPosition();
	}


	@Override
	public void remove(Component comp) {

		int index = tabbedPane1.indexOfComponent(comp);
		if (index != -1)
		{
			tabbedPane1.remove(index);
		}

		index = tabbedPane2.indexOfComponent(comp);

		if (index != -1) {
			tabbedPane2.remove(index);
		}

		index = tabbedPane3.indexOfComponent(comp);
		if (index != -1) {
			tabbedPane3.remove(index);
		}
		super.remove(comp);
		updateDividerPosition();
	}

	protected void updateDividerPosition ()
	{
		if (isDragging || maximize)
			return;


		if (hsplitPane == null || vsplitPane == null)
			return;

		hsplitDividerPos = 1.0;

		if (tabbedPane1.getTabCount() == 0 && tabbedPane2.getTabCount() != 0)
			vsplitDividerPos = 0.0;

		else if (tabbedPane1.getTabCount() != 0 && tabbedPane2.getTabCount() == 0)
			vsplitDividerPos = 1.0;
		else
			vsplitDividerPos = 0.5;

		if (tabbedPane3.getTabCount() != 0)
			hsplitDividerPos = 0.7;

		if (tabbedPane1.getTabCount() == 0 && tabbedPane2.getTabCount() == 0) {
			hsplitDividerPos = 0.0;
		}

		setDividerLocations(hsplitDividerPos, vsplitDividerPos);
	}


	private void setDividerLocations (final double hsplitDividerPos, final double vsplitDividerPos)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			public void run() {
				if (hsplitDividerPos >= 0.0 && hsplitDividerPos <= 1.0)
					hsplitPane.setDividerLocation(hsplitDividerPos);
				if (vsplitDividerPos >= 0.0 && vsplitDividerPos <= 1.0)
					vsplitPane.setDividerLocation(vsplitDividerPos);
			}
		});
	}


	public Handler getHandler() {
		if (handler == null) {
			handler = new Handler();
		}
		return handler;
	}

	protected ContainerListener createDockFrameContListener() {
		return getHandler();
	}

	protected FocusListener createDockFrameFocusListener() {
		return getHandler();
	}

	protected PropertyChangeListener createPropertyListener() {
		return getHandler();
	}

	protected MouseInputListener createDockingListener() {
		return getHandler();
	}


	private class Handler implements ContainerListener, FocusListener,
	MouseInputListener, PropertyChangeListener {

		Component component;
		Point origin = null;


		public void componentAdded(ContainerEvent e) {
		}


		public void componentRemoved(ContainerEvent e) {
		}


		public void focusGained(FocusEvent e) {
			fireFocusEvent(((JTabbedPane)e.getSource()).getSelectedComponent(), e);
		}


		public void focusLost(FocusEvent e) {
			fireFocusEvent(((JTabbedPane)e.getSource()).getSelectedComponent(), e);
		}


		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2)
			{
				updateTabSize((JTabbedPane)e.getSource());
			}
		}


		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}


		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}


		public void mousePressed(MouseEvent e) {

		}


		public void mouseReleased(MouseEvent evt) {

			if (!isDragging)
			{
				return;
			}

			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			if (component == null) {
				return;
			}

			Point global = evt.getLocationOnScreen();

			if (!(component instanceof JTabbedPane)) {
				return;
			}
			else
			{
				if (((JTabbedPane) component).getTabCount() == 0)
					return;
			}

			Point dockingPosition = tabbedPane1.getLocationOnScreen();
			Point comparisonPoint = new Point(global.x
					- dockingPosition.x, global.y - dockingPosition.y);

			JTabbedPane pane = (JTabbedPane) component;
			String title = pane
			.getTitleAt(pane.getSelectedIndex());

			if (tabbedPane1.contains(comparisonPoint)) {
				add(pane.getSelectedComponent(), title, LEFT);
			}
			else
			{
				dockingPosition = tabbedPane2.getLocationOnScreen();
				comparisonPoint = new Point(global.x
					- dockingPosition.x, global.y - dockingPosition.y);

				if (tabbedPane2.contains(comparisonPoint)) {
					add(pane.getSelectedComponent(), title, RIGHT);
				}
				else
					if (tabbedPane3.isVisible()) {

					dockingPosition = tabbedPane3.getLocationOnScreen();
					comparisonPoint = new Point(global.x - dockingPosition.x,
							global.y - dockingPosition.y);

					if (tabbedPane3.contains(comparisonPoint)) {
						add(pane.getSelectedComponent(), title, BOTTOM);
					}
				}
			}
			origin = null;
			component = null;
			isDragging = false;
			updateDividerPosition();
		}


		public void mouseDragged(MouseEvent evt) {

			isDragging = true;
			if (origin == null) {
				origin = evt.getComponent().getLocationOnScreen();
			}

			component = evt.getComponent();
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			int x = evt.getLocationOnScreen().x;
			int y = evt.getLocationOnScreen().y;

			x = x - origin.x;
			y = y - origin.y;

			double hpos = hsplitDividerPos;
			double ypos = vsplitDividerPos;

			if (y < getHeight() - 30)
			{
				if (x < 30 || x > getWidth() - 30)
				{
					if (ypos == 0.0)
						ypos = 0.1;

					if (ypos == 1.0)
						ypos = 0.9;
				}
			}

			if (y < 30 || y > getHeight() - 30)
			{
				if (hpos == 0.0)
				{
					hpos = 0.1;
					ypos = 0.5;
				}
				if (hpos == 1.0)
					hpos = 0.9;
			}

			if (hpos != hsplitDividerPos || ypos != vsplitDividerPos)
				setDividerLocations(hpos, ypos);
		}


		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}


		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub

		}
	}

	protected void fireFocusEvent(Component component, FocusEvent event)
	{
		if (component == null)
			return;

		FocusListener [] fls = component.getFocusListeners();
		for(FocusListener fl :fls)
		{
			if (event.getID() == FocusEvent.FOCUS_GAINED)
				fl.focusGained(event);
			else
				fl.focusLost(event);
		}
	}



	@Override
	protected void processEvent(AWTEvent e) {
		super.processEvent(e);
	}

	protected void updateTabSize(Component component) {

		if (!maximize) {
			if (component.equals(tabbedPane1)) {
				hsplitDividerPos = 1.0;
				vsplitDividerPos = 1.0;
			} else if (component.equals(tabbedPane2)) {
				vsplitDividerPos = 0.0;
				hsplitDividerPos = 1.0;
			} else if (component.equals(tabbedPane3)) {
				hsplitDividerPos = 0.0;
			} else
				return;
			maximize = true;
			setDividerLocations(hsplitDividerPos, vsplitDividerPos);
		}
		else
		{
			maximize = false;
			updateDividerPosition();
		}
	}


	public void doLayout() {
		updateDividerPosition();
		super.doLayout();
	}


	protected void showLowerPanel(boolean show)
	{
		if (!show)
		{
			hsplitPane.remove(tabbedPane3);
		}
		else
			hsplitPane.add(tabbedPane3, JSplitPane.BOTTOM);
		updateDividerPosition();
	}


	class MyTabbedPane extends JTabbedPane
	{

		public MyTabbedPane() {
			super();
		}

		public MyTabbedPane(int tabPlacement, int tabLayoutPolicy) {
			super(tabPlacement, tabLayoutPolicy);
		}

		public MyTabbedPane(int tabPlacement) {
			super(tabPlacement);
		}

		@Override
		public void setSelectedComponent(Component c) {
			super.setSelectedComponent(c);
		}

		@Override
		public void setSelectedIndex(int index) {
			if (isDragging)
				return;
			super.setSelectedIndex(index);
			requestFocus();
		}

	}

}
