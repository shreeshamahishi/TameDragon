package org.tamedragon.visualization.ide;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonTabComponent extends JPanel {
	
	private static final long serialVersionUID = 826346143869792086L;
	private final JTabbedPane pane;
	private final JLabel label;
	private final JButton button = new TabButton();
	private ArrayList<JTextPane> editors = null;

	public ButtonTabComponent(String title, JTabbedPane pane, ArrayList<JTextPane> editors) {
		// unset default FlowLayout' gaps
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		if (pane == null) {
			throw new NullPointerException("TabbedPane is null");
		}
		this.pane = pane;
		this.editors = editors;
		setOpaque(false);
		label = new JLabel(title);

		add(label);
		// add more space between the label and the button
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		add(button);
		// add more space to the top of the component
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}

	private class TabButton extends JButton implements ActionListener {
		
		private static final long serialVersionUID = -7023605377198699929L;
		
		public TabButton() {
			int size = 17;
			setPreferredSize(new Dimension(size, size));
			setToolTipText("close this tab");
			// Make the button looks the same for all Laf's
			setUI(new BasicButtonUI());
			// Make it transparent
			setContentAreaFilled(false);
			// No need to be focusable
			setFocusable(false);
			setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);
			// Making nice rollover effect
			// we use the same listener for all buttons
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			// Close the proper tab by clicking the button
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			int i = pane.indexOfTabComponent(ButtonTabComponent.this);

			JTextPane jedp = editors.get(i - 1);

			// Save file
			File myFile = new File(jedp.getName());

			TimerTask task = new FileWatcher(myFile) {
				protected void onChange(File file) {
					
				}
			};

			if (i != -1) {
				pane.remove(i);
				editors.remove(i - 1);
			}
		}

		// we don't want to update UI for this button
		public void updateUI() {
		}

		// paint the cross
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Stroke stroke = g2.getStroke();
			// shift the image for pressed buttons
			if (!getModel().isPressed()) {
				g2.translate(-1, -1);
			}
			g2.setStroke(new BasicStroke(2));
			g.setColor(Color.BLACK);
			if (getModel().isRollover()) {
				g.setColor(Color.MAGENTA);
			}
			int delta = 6;
			g.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
					- delta - 1);
			g.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
					- delta - 1);
			// leave the graphics unchanged
			if (!getModel().isPressed()) {
				g.translate(1, 1);
			}
			g2.setStroke(stroke);
		}
	}

	private final static MouseListener buttonMouseListener = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(false);
			}
		}
	};

}
