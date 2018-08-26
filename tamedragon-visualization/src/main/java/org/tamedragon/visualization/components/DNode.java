package org.tamedragon.visualization.components;

import java.awt.*;
import java.util.Vector;


//import org.tamedragon.common.Node;
import org.tamedragon.common.llvmir.types.Temp;
import org.tamedragon.visualization.util.DPoint;
import org.tamedragon.visualization.util.DPoint3;
import org.tamedragon.visualization.util.Matrix44;


/**
 * DNode is short form of Displayable Node component. Basically this component used to display the data
 * wrapped inside the component.
 * @author ramarao
 *
 */
public class DNode {

//	private Node node;
//	private Temp temp;
//
//	private double x_, y_, z_;
//	private double width, height;
//
//	public final static int BELOW = 0;
//	public final static int IN = 1;
//	public final static int CENTER = 2;
//	private int labelPosition_ = CENTER;
//	private int positionInGraph;
//
//
//	public DNode(Node node, Temp temp) {
//		this(node, temp, 0);
//	}
//
//	public DNode(Node node, Temp temp, int positionInGraph) {
//		this.node = node;
//		this.temp = temp;
//		x_ = y_ = z_ = 10.0;
//		width = height = 30.0;
//		this.positionInGraph = positionInGraph;
//	}
//
//	public int getPositionInGraph() {
//		return positionInGraph;
//	}
//
//	public String getName() {
//		return temp.toString();
//	}
//
//	public boolean hasNode(Node n, Vector<Node> nodeList) {
//		return node.hasNode(n, nodeList);
//	}
//
//	public void draw(Component component, Graphics graphics, Matrix44 transform, int quality) {
//		double scale = transform.scale;
//
//		// We scale the bounds here.
////		scaleBounds_(graphics, scale);
//
//		double w = width * scale;
//		double h = height * scale;
//
//		if (w < 1)
//			w = 1;
//		if (h < 1)
//			h = 1;
//
//		DPoint3 position = new DPoint3(
//				x_, y_, z_);
//		position.transform(transform);
//
//		int x = (int) position.x;
//		int y = (int) position.y;
//
//		graphics.setColor(Color.black);
//		graphics.drawOval((int) (x - w / 2), (int) (y - h / 2),
//				(int) w, (int) h);
//
//		// painting the label
//		if (getName().length() > 0) {
//			FontMetrics fm = graphics.getFontMetrics();
//
//			double widest = 0.0;
//			widest = fm.stringWidth(new String(getName()));
//
//			double lx, ly;
//
//			lx = x - widest / 2.0;
//
//			if (labelPosition_ != BELOW) {
//				ly = y + fm.getHeight() / 2.0;
//				ly -= fm.getDescent();
//			} else
//				ly = y + h / 2.0 + fm.getAscent() + 1.0;
//
//			graphics.drawString(new String(getName()), (int) lx, (int) ly);
//		}
//
//		graphics.setColor(Color.red);
//
//	}
//
//	private void scaleBounds_(Graphics graphics, double scale) {
//		if (labelPosition_ == IN) {
//			FontMetrics fm = graphics.getFontMetrics();
//
//			double w, h;
//			int i;
//
//			h = fm.getHeight();
//			w = fm.stringWidth(new String(getName()));
//
//			// if(shape_ == OVAL) {
//			w *= 1.42;
//			h *= 1.42;
//			// }
//
//			w += 6;
//			h += 6;
//
//			width = w / scale;
//			height = h / scale;
//		}
//	}
//
//	public void setPosition(double new_x, double new_y) {
//		x_ = new_x;
//		y_ = new_y;
//	}
//
//	public void setPosition(DPoint new_position) {
//		x_ = new_position.x;
//		y_ = new_position.y;
//	}
//
//	public void setPosition(double new_x, double new_y, double new_z) {
//		x_ = new_x;
//		y_ = new_y;
//		z_ = new_z;
//	}
//
//	public void setPosition(DPoint3 new_position) {
//		x_ = new_position.x;
//		y_ = new_position.y;
//		z_ = new_position.z;
//	}
//
//	public DPoint getPosition() {
//		DPoint position = new DPoint(
//				x_, y_);
//		return position;
//	}
//
//	public DPoint3 getPosition3() {
//		DPoint3 position;
//		position = new DPoint3(x_, y_, z_);
//		return position;
//	}
//
//	public Dimension getBoundingBox() {
//		return new Dimension((int) width, (int) height);
//	}
//
//	public DPoint3 intersectWithLineTo(DPoint3 to, boolean inplane, int quality) {
//		if (width == 0.0 || height == 0.0)
//			return new DPoint3(x_, y_, z_);
//
//		if (!inplane || z_ != to.z)
//		// For now, assume spherical.
//		{
//			double mindim = Math.min(width, height) / 2.0;
//			double dist = Math.sqrt((to.x - x_) * (to.x - x_) + (to.y - y_)
//					* (to.y - y_) + (to.z - z_) * (to.z - z_));
//			if (dist <= mindim)
//				return new DPoint3(x_, y_, z_);
//			double ratio = mindim / dist;
//			return new DPoint3(x_ + (to.x - x_) * ratio, y_ + (to.y - y_) * ratio,
//					z_ + (to.z - z_) * ratio);
//		}
//
//		double dy = to.y - y_;
//		double dx = to.x - x_;
//
//		// If point is inside the node, return the centerpoint.
//		if (dx * dx / (width * width) + dy * dy / (height * height) <= .25)
//			return new DPoint3(x_, y_, z_);
//
//		if (dx == 0) {
//			if (dy > 0)
//				return new DPoint3(x_, y_ + height / 2, z_);
//			else
//				return new DPoint3(x_, y_ - height / 2, z_);
//		}
//		double slope = dy / dx;
//
//		double x, y;
//
//		x = width
//				* height
//				/ (2 * Math.sqrt(height * height + slope * slope * width
//						* width));
//		if (to.x < x_)
//			x = -x;
//		y = slope * x;
//
//		return new DPoint3(x + x_, y + y_, z_);
//	}
//
//
//	public boolean hasChild(DNode child)
//	{
//		return node.hasChild(child.getNode());
//	}
//
//	 public void setBoundingBox(double new_width, double new_height, double new_depth)
//     {
//        width = new_width;
//        height = new_height;
//     }
//
//	 public Node getNode()
//	 {
//		 return node;
//	 }
}
