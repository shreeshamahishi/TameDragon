/*
	File: AngleControlPanel.java
	9/3/96  Larry Barowski
 */

package org.tamedragon.visualization;

import java.awt.Button;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.tamedragon.visualization.util.DPoint;

/**
 * An AngleControl, along with a label and buttons for XY plane, YZ plane, XZ
 * plane.
 * </p>
 * Here is the <a href="../gui/AngleControlPanel.java">source</a>.
 *
 * @author Larry Barowski
 */

class AngleControlPanel extends LPanel implements ActionListener {
	public static int ANGLE = 38793;
	public static int DONE = 38794;

	private AngleControl angle_;

	public AngleControlPanel(int width, int height) {
		super();

		constraints.insets.top = constraints.insets.bottom = 0;
		addLabel("Viewing Angles", 0, 0, 1.0, 0.0, 0, 0);
		constraints.insets.top = constraints.insets.bottom = 0;
		angle_ = new AngleControl(width, height);
		addComponent(angle_, 0, 0, 1.0, 1.0, 3, 0);
		constraints.insets.top = constraints.insets.bottom = 0;
		addLabel("Plane:", 1, -1, 0.0, 0.0, 0, 0);
		JButton xy = addButton("XY", 1, 0, 1.0, 0.0, 0, 0);
		xy.addActionListener(this);
		JButton xz = addButton("XZ", 1, 0, 1.0, 0.0, 0, 0);
		xz.addActionListener(this);
		JButton yz = addButton("YZ", 0, 0, 1.0, 0.0, 0, 0);
		yz.addActionListener(this);

		finish();
	}

	public boolean handleEvent(Event event) {
		if (event.target instanceof AngleControl) {
			if (event.id == AngleControl.ANGLE) {
				DPoint angles = (DPoint) event.arg;
				postEvent(new Event((Object) this, ANGLE, new DPoint(angles.x,
						angles.y)));
			} else if (event.id == AngleControl.DONE) {
				DPoint angles = (DPoint) event.arg;
				postEvent(new Event((Object) this, DONE, new DPoint(angles.x,
						angles.y)));
			}
		}
		return super.handleEvent(event);
	}

	public boolean action(Event event, Object what) {
		if (event.target instanceof Button) {
			if (((String) what).equals("XY")) {
				angle_.setAngles(0.0, Math.PI / 2.0);
				postEvent(new Event((Object) this, DONE, new DPoint(0.0,
						Math.PI / 2.0)));
			} else if (((String) what).equals("XZ")) {
				angle_.setAngles(0.0, 0.0);
				postEvent(new Event((Object) this, DONE, new DPoint(0.0, 0.0)));
			} else if (((String) what).equals("YZ")) {
				angle_.setAngles(Math.PI / 2.0, 0.0);
				postEvent(new Event((Object) this, DONE, new DPoint(
						Math.PI / 2.0, 0.0)));
			}
		}
		return true;
	}


	public void actionPerformed(ActionEvent e) {
		String what = e.getActionCommand();
		if (((String) what).equals("XY")) {
			angle_.setAngles(0.0, Math.PI / 2.0);
			postEvent(new Event((Object) this, DONE, new DPoint(0.0,
					Math.PI / 2.0)));
		} else if (((String) what).equals("XZ")) {
			angle_.setAngles(0.0, 0.0);
			postEvent(new Event((Object) this, DONE, new DPoint(0.0, 0.0)));
		} else if (((String) what).equals("YZ")) {
			angle_.setAngles(Math.PI / 2.0, 0.0);
			postEvent(new Event((Object) this, DONE, new DPoint(Math.PI / 2.0,
					0.0)));
		}
	}
}
