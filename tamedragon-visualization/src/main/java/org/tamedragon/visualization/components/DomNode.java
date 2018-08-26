package org.tamedragon.visualization.components;

import java.awt.*;
import java.util.Vector;

import org.tamedragon.visualization.algorithm.TreeAlgorithmData;
import org.tamedragon.visualization.util.DPoint;
import org.tamedragon.visualization.util.DPoint3;
import org.tamedragon.visualization.util.Matrix44;

public class DomNode {

	private String name;

	private DomNode predecessor;
	private Vector<DomNode> successors = new Vector<DomNode>();
	private int positionInGraph;
	private DOMEdge inComingEdge = null;
	private Vector<DOMEdge> outgoingEdges = new Vector<DOMEdge>();
	int position = 0;

	private double x_, y_, z_;
	private double width, height;

	public final static int BELOW = 0;
	public final static int IN = 1;
	public final static int CENTER = 2;
	private int labelPosition_ = CENTER;

	public TreeAlgorithmData data;

	protected boolean haveId_;

	public DomNode() {
		successors = new Vector<DomNode>();
		//inComingEdges = new Edge();
		//inComingEdge = new DOMEdge();
		outgoingEdges = new Vector<DOMEdge>();

		x_ = y_ = z_ = 10.0;
		width = height = 30.0;
	}

	public DomNode(String name) {
		this.name = name;
		successors = new Vector<DomNode>();
		//inComingEdges = new Edge();
		//inComingEdge = new DOMEdge();
		outgoingEdges = new Vector<DOMEdge>();

		x_ = y_ = z_ = 10.0;
		width = height = 30.0;
	}

	public void addSuccessesor(DomNode dest) {
		successors.addElement(dest);
	}

	public DOMEdge getInComingEdge() {
		return inComingEdge;
	}


	public void setPredecessor(DomNode predecessor) {
		this.predecessor = predecessor;
	}

	public void addOutgoingEdge(DOMEdge edge) {
		outgoingEdges.addElement(edge);
	}

	public Vector<DomNode> getSuccessors() {
		return successors;
	}

	public DomNode getPredecessor() {
		return predecessor;
	}

	public int getNumOutgoingEdges() {
		return successors.size();
	}

	public int getPositionInGraph() {
		return positionInGraph;
	}

	public void setPositionInGraph(int positionInGraph) {
		this.positionInGraph = positionInGraph;
	}

	public void setInComingEdge(DOMEdge inComingEdges) {
		this.inComingEdge = inComingEdges;
	}

	public Vector<DOMEdge> getOutgoingEdges() {
		return outgoingEdges;
	}

	public void setOutgoingEdges(Vector<DOMEdge> outgoingEdges) {
		this.outgoingEdges = outgoingEdges;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * This function returns true if the DomNode passed in the first parameter
	 * exists in the list of DomNodes passed in the second parameter
	 */
	public boolean hasDomNode(DomNode n, Vector<DomNode> DomNodeList) {
		int numSuccs = DomNodeList.size();
		for (int i = 0; i < numSuccs; i++) {
			DomNode DomNodeFound = DomNodeList.elementAt(i);
			if (DomNodeFound == n)
				return true;
		}
		return false;
	}


	/*
	 * This function returns true if the DomNode has an edge to the passed DomNode as
	 * parameter
	 */
	/*public boolean goesTo(DomNode n) {
		return hasDomNode(n, successors);
	}
*/
	public Object clone() throws java.lang.CloneNotSupportedException {
		DomNode copy;
		copy = (DomNode) super.clone();
		return copy;
	}


	public boolean hasChild(DomNode child)
	{
		return successors.indexOf(child) != -1 || (predecessor !=  child ) ;
	}

	public boolean equals(DomNode node){
		return this.name.equals(node.getName());
	}
	public void draw(Component component, Graphics graphics, Matrix44 transform, int quality) {
		double scale = transform.scale;

		// We scale the bounds here.
		scaleBounds_(graphics, scale);

		double w = width * scale;
		double h = height * scale;

		if (w < 1)
			w = 1;
		if (h < 1)
			h = 1;

		DPoint3 position = new DPoint3(
				x_, y_, z_);
		position.transform(transform);

		int x = (int) position.x;
		int y = (int) position.y;

		graphics.setColor(Color.black);
		graphics.drawOval((int) (x - w / 2), (int) (y - h / 2),
				(int) w, (int) h);

		// painting the label
		if (getName().length() > 0) {
			FontMetrics fm = graphics.getFontMetrics();

			double widest = 0.0;
			widest = fm.stringWidth(new String(getName()));

			double lx, ly;

			lx = x - widest / 2.0;

			if (labelPosition_ != BELOW) {
				ly = y + fm.getHeight() / 2.0;
				ly -= fm.getDescent();
			} else
				ly = y + h / 2.0 + fm.getAscent() + 1.0;

			graphics.drawString(new String(getName()), (int) lx, (int) ly);
		}
	}

	private void scaleBounds_(Graphics graphics, double scale) {
		if (labelPosition_ == IN) {
			FontMetrics fm = graphics.getFontMetrics();

			double w, h;
			int i;

			h = fm.getHeight();
			w = fm.stringWidth(new String(getName()));

			// if(shape_ == OVAL) {
			w *= 1.42;
			h *= 1.42;
			// }

			w += 6;
			h += 6;

			width = w / scale;
			height = h / scale;
		}
	}

	public void setPosition(double new_x, double new_y) {
		x_ = new_x;
		y_ = new_y;
	}

	public void setPosition(DPoint new_position) {
		x_ = new_position.x;
		y_ = new_position.y;
	}

	public void setPosition(double new_x, double new_y, double new_z) {
		x_ = new_x;
		y_ = new_y;
		z_ = new_z;
	}

	public void setPosition(DPoint3 new_position) {
		x_ = new_position.x;
		y_ = new_position.y;
		z_ = new_position.z;
	}

	public DPoint getPosition() {
		DPoint position = new DPoint(
				x_, y_);
		return position;
	}

	public DPoint3 getPosition3() {
		DPoint3 position;
		position = new DPoint3(x_, y_, z_);
		return position;
	}

	public Dimension getBoundingBox() {
		return new Dimension((int) width, (int) height);
	}

	public DPoint3 intersectWithLineTo(DPoint3 to, boolean inplane, int quality) {
		if (width == 0.0 || height == 0.0)
			return new DPoint3(x_, y_, z_);

		if (!inplane || z_ != to.z)
		// For now, assume spherical.
		{
			double mindim = Math.min(width, height) / 2.0;
			double dist = Math.sqrt((to.x - x_) * (to.x - x_) + (to.y - y_)
					* (to.y - y_) + (to.z - z_) * (to.z - z_));
			if (dist <= mindim)
				return new DPoint3(x_, y_, z_);
			double ratio = mindim / dist;
			return new DPoint3(x_ + (to.x - x_) * ratio, y_ + (to.y - y_) * ratio,
					z_ + (to.z - z_) * ratio);
		}

		double dy = to.y - y_;
		double dx = to.x - x_;

		// If point is inside the node, return the centerpoint.
		if (dx * dx / (width * width) + dy * dy / (height * height) <= .25)
			return new DPoint3(x_, y_, z_);

		if (dx == 0) {
			if (dy > 0)
				return new DPoint3(x_, y_ + height / 2, z_);
			else
				return new DPoint3(x_, y_ - height / 2, z_);
		}
		double slope = dy / dx;

		double x, y;

		x = width
				* height
				/ (2 * Math.sqrt(height * height + slope * slope * width
						* width));
		if (to.x < x_)
			x = -x;
		y = slope * x;

		return new DPoint3(x + x_, y + y_, z_);
	}

	 public void setBoundingBox(double new_width, double new_height, double new_depth)
     {
        width = new_width;
        height = new_height;
     }

  /*   public DomNode firstChild()
     {
    	 try{
    		 return successors.get(position);
    	 }
    	 catch(Exception ex){
    		 return null;
    	 }

     }

  *//**
  *  Returns the index of the next child, -1 if there is none.
  **//*
     public DomNode nextChild()
     {
    	 try{
    		 return successors.get(++position);
    	 }
    	 catch(Exception ex){
    		 return null;
    	 }
     }*/

	 public boolean equals(Object obj){
			DomNode node = (DomNode)obj;
			return this.name.equals(node.name);
		}

public void setSuccessors(Vector<DomNode> successors) {
	this.successors = successors;
}


}
