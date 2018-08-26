package org.tamedragon.visualization;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class CustomJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean floatable;

	public CustomJPanel() {
		// TODO Auto-generated constructor stub
	}

	public CustomJPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public CustomJPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CustomJPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setUI(PanelUI ui) {
		if (ui instanceof BasicPanelUI)
		{
			ui = new CustomBasicPanelUI();
		}
		super.setUI(ui);
	}

	public void setFloatable(final boolean flotable)
	{
		this.floatable = flotable;
	}

	public boolean isFloatable()
	{
		return floatable;
	}
}
