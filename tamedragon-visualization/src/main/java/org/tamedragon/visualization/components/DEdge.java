package org.tamedragon.visualization.components;

import java.awt.Color;
import java.awt.Graphics;



import org.tamedragon.visualization.GraphCanvas;

//import org.tamedragon.common.CFEdge;
//import org.tamedragon.common.Node;
import org.tamedragon.visualization.util.DPoint3;
import org.tamedragon.visualization.util.Matrix44;

/**
 * DEdge is meant for Displayable Edge component. Which wraps Edge component.
 * @author ramarao
 *
 */
public class DEdge {
//	private CFEdge edge;
//	private DNode src;
//	private DNode dest;
//
//	private int lineStyle_;
//
//	public static Color styleColors[] = { Color.black, Color.blue, Color.green,
//			Color.orange };
//
//	public DEdge(CFEdge edge) {
//		this(new DNode(edge.getSrc(), null), new DNode(edge.getDestination(), null), edge);
//	}
//
//
//	public DEdge(DNode src, DNode dest, CFEdge edge)
//	{
//		this.src = src;
//		this.dest = dest;
//		this.edge = edge;
//		lineStyle_ = 0;
//	}
//
//
//	public Node getSrc()
//	{
//		return edge.getSrc();
//	}
//
//
//	public Node getDestination()
//	{
//		return edge.getDestination();
//	}
//
//
//	public DNode getDSrc()
//	{
//		return src;
//	}
//
//
//	public DNode getDDest()
//	{
//		return dest;
//	}
//
//
//	public boolean isDirected() {
//		return edge.isDirected();
//	}
//
//	public int getPositionInGraph() {
//		return edge.getPositionInGraph();
//	}
//
//	public void display() {
//		if (isDirected())
//			System.out.println("Src = " + edge.getSrc().getName() + " Dest = "
//					+ edge.getDestination().getName());
//		else
//			System.out.println("First Node = " + edge.getSrc().getName()
//					+ " Second Node = " + edge.getDestination().getName());
//	}
//
//	public void draw(Graphics graphics,
//			Matrix44 transform, boolean inplane,
//			boolean arrow_only, int quality,
//			GraphCanvas canvas, int which_gr) {
//		double scale = transform.scale;
//
//		graphics.setColor(styleColors[lineStyle_]);
//		DNode src = getDSrc();
//		DNode destination = getDDest();
//
//		DPoint3 p1to, p2to;
//		p1to = src.getPosition3();
//		p2to = destination.getPosition3();
//
//		DPoint3 p1 = destination.intersectWithLineTo(p1to, inplane, quality);
//		DPoint3 p2 = src.intersectWithLineTo(p2to, inplane, quality);
//
//		p1.transform(transform);
//		p2.transform(transform);
//
//		// Self edge with no intermediate points.
//		if (src == destination) {
//			p2.x = p1.x + 1;
//			p2.y = p1.y;
//		}
//
//		graphics.setColor(Color.black);
//		graphics.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
//		// Draw selection handles.
//		if (!arrow_only) {
//			graphics.setColor(Color.red);
//
//			graphics.drawRect((int) p1.x - 2, (int) p1.y - 2, 4, 4);
//
//			if (src != destination) // Not a self-edge.
//				graphics.drawRect((int) p2.x - 2, (int) p2.y - 2, 4, 4);
//
//			graphics.setColor(Color.white);
//
//			graphics.drawRect((int) p1.x - 1, (int) p1.y - 1, 2, 2);
//
//			if (src != destination) // Not a self-edge.
//				graphics.drawRect((int) p2.x - 1, (int) p2.y - 1, 2, 2);
//
//			graphics.setColor(Color.black);
//		}
//
///*		String label_ = "Edge";
//
//		// Draw label.
//		if (quality > 0 && label_ != null && label_.length() > 0) {
//			char[] label = label_.toCharArray();
//			DPoint3 to;
//
//			to = new DPoint3(p1to);
//			to.transform(transform);
//			if (p1.x == to.x && p1.y == to.y)
//				to.x++;
//
//			double center_x = (p1.x + to.x) / 2.0;
//			double center_y = (p1.y + to.y) / 2.0;
//
//			double theta = Math.atan2(-(to.y - p1.y), to.x - p1.x);
//			canvas.drawRotatedText(label_, theta, (int) center_x,
//					(int) center_y, graphics, which_gr);
//		}
//*/
//	}
}
