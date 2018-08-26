package org.tamedragon.visualization;

import java.awt.*;
import java.util.Vector;

import javax.swing.JFrame;

import org.tamedragon.common.*;
import org.tamedragon.visualization.algorithm.GraphUpdate;
import org.tamedragon.visualization.components.*;
import org.tamedragon.visualization.util.*;

public class GraphCanvas //extends Canvas implements GraphUpdate {
{

	/**
	 *	Event id for size change. Subclasses must post an event with this id
	 *	when their contents or windows are resized.
	 **/
	public static int RESIZE = 32450;

	/**
	 *	Event id for changing the label above the OffsetCanvas.
	 *	The arg field of the event must contain the string.
	 **/
	public static int LABEL = 32451;

	// Event indicating that the graph has changed.
	public static final int UPDATE = 38792;

	private static final int aaDivs_ = 4; // Number of pixel divisions for
	// anti-aliasing.
	static private Color[] aaShades_;

	private JFrame frame;

	private double width_, // drawable width
			height_; // drawable height

	private Dimension windowSize_ = new Dimension(900, 800); // window viewable size, -1 values
	// will force a change_size at start

	private DragFix dragFix_;

	private double offsetx_ = 0, offsety_ = 0;

	private double minx_, miny_, maxx_, maxy_;

	private double scale_ = 1.0;

	private DInterferenceGraph ig;

	private DomGraph domGraph;

	private DPoint offset_ = new DPoint(0, 0);

	//private Node newEdgeNode_;
	//private Node movingNode_;
	//private Node selectedNode_;
	private double movingZ_, movingX_, movingY_;
	private double selectedRatio_;
	private Point selectedEdge_;

	private final static int NONE_ = 0;
	private final static int CENTER_ = 1;
	private final static int CORNER_ = 2;
	private final static int BOTTOM_ = 3;
	private final static int LEFT_ = 4;

	private boolean scaleBounds_ = true;

	private Matrix44 viewTransform_, moveTransform_;
	private Matrix44 scaleMatrix_, shiftMatrix_;
	private Matrix44 rotxMatrix_, rotzMatrix_;

	private boolean xyPlane_ = true;

	private boolean _3d_ = false;
	private Image backImage_ = null;
	private Font font_;

	private int currentMouseAction_ = 0;

	private DPoint3 lastEdgePoint_ = null;
	private int pathLength_, pathArraySize_;
	private DPoint3[] pathArray_;

	private int multiSelectX_, multiSelectY_, multiSelectX2_, multiSelectY2_;

	private double moveX_, moveY_;

	public double hSpacing = 30, vSpacing = 40;

	private int quality = 1;

    private int qualityCB_ = 1;

//	public GraphCanvas(DInterferenceGraph ug) {
//		this.ig = ug;
//
//		setBackground(Color.white);
//		font_ = new Font("Helvetica", Font.PLAIN, 12);
//
//		computeBounds_forInterferenceGraph();
//
//		scaleMatrix_ = new Matrix44();
//
//		scaleMatrix_.matrix[0][0] = scaleMatrix_.matrix[1][1] = scaleMatrix_.matrix[2][2] = scaleMatrix_.matrix[3][3] = 1.0;
//
//		shiftMatrix_ = new Matrix44(scaleMatrix_);
//		rotxMatrix_ = new Matrix44(scaleMatrix_);
//		rotxMatrix_.matrix[1][1] = rotxMatrix_.matrix[2][2] = -1.0;
//		rotxMatrix_.matrix[2][1] = -(rotxMatrix_.matrix[1][2] = 0.0);
//		rotzMatrix_ = new Matrix44(scaleMatrix_);
//
//		updateViewTransform_();
//		dragFix_ = new DragFix(this);
//	}
//
//	public GraphCanvas(DomGraph domGraph) {
//		this.domGraph = domGraph;
//
//		setBackground(Color.white);
//		font_ = new Font("Helvetica", Font.PLAIN, 12);
//
//		computeBounds_forDominatorTree();
//
//		scaleMatrix_ = new Matrix44();
//
//		scaleMatrix_.matrix[0][0] = scaleMatrix_.matrix[1][1] = scaleMatrix_.matrix[2][2] = scaleMatrix_.matrix[3][3] = 1.0;
//
//		shiftMatrix_ = new Matrix44(scaleMatrix_);
//		rotxMatrix_ = new Matrix44(scaleMatrix_);
//		rotxMatrix_.matrix[1][1] = rotxMatrix_.matrix[2][2] = -1.0;
//		rotxMatrix_.matrix[2][1] = -(rotxMatrix_.matrix[1][2] = 0.0);
//		rotzMatrix_ = new Matrix44(scaleMatrix_);
//
//		updateViewTransform_();
//		dragFix_ = new DragFix(this);
//	}
//
//
//	// This will give the initial window size.
//	public Dimension preferredSize() {
//		return new Dimension(400, 400);
//	}
//
//	@Override
//	public void paint(Graphics graphics) {
//		graphics.dispose();
//		paintOver();
//	}
//
//	private void computeBounds_forInterferenceGraph() {
//		double oldminx = minx_;
//		double oldminy = miny_;
//
//		minx_ = miny_ = maxx_ = maxy_ = 0;
//
//		Vector<DNode> nodelist = ig.nodes();
//		
//		System.out.println("WAIT HERE: in Graph canvas NODELIST SIZE = " +  nodelist.size());
//
//		if (nodelist == null)
//			return;
//
//		DNode tmpnode = nodelist.get(0);
//
//		DPoint tmppoint;
//		Dimension tmpdim;
//
//		if (tmpnode != null) {
//			tmppoint = tmpnode.getPosition();
//			tmpdim = tmpnode.getBoundingBox();
//
//			minx_ = tmppoint.x - tmpdim.width / 2.0;
//			maxx_ = tmppoint.x + tmpdim.width / 2.0;
//			miny_ = tmppoint.y - tmpdim.height / 2.0;
//			maxy_ = tmppoint.y + tmpdim.height / 2.0;
//
//			for (int index = 1; index < nodelist.size(); index ++)
//			{
//				tmpnode = nodelist.get(index);
//				if (tmpnode != null)
//				{
//					tmppoint = tmpnode.getPosition();
//					tmpdim = tmpnode.getBoundingBox();
//
//					double w = tmpdim.width / 2.0;
//					double h = tmpdim.height / 2.0;
//
//					if (tmppoint.x - w < minx_)
//						minx_ = tmppoint.x - w;
//					if (tmppoint.x + w > maxx_)
//						maxx_ = tmppoint.x + w;
//					if (tmppoint.y - h < miny_)
//						miny_ = tmppoint.y - h;
//					if (tmppoint.y + h > maxy_)
//						maxy_ = tmppoint.y + h;
//				}
//
//			}
//		}
//
//		// 3D approach.
//		double maxdim = Math.abs(maxy_);
//		if (Math.abs(miny_) > maxdim)
//			maxdim = Math.abs(miny_);
//		if (Math.abs(minx_) > maxdim)
//			maxdim = Math.abs(minx_);
//		if (Math.abs(maxx_) > maxdim)
//			maxdim = Math.abs(maxx_);
//
//		maxx_ = maxy_ = maxdim;
//		minx_ = miny_ = -maxdim;
//		width_ = height_ = 2.0 * maxdim;
//	}
//
//	public void setScale(double new_scale) {
//		// Scale about the center - compute new offset to keep centered
//		setOffsets_(windowSize_.width / 2.0 - (new_scale / scale_)
//				* (windowSize_.width / 2.0 - offset_.x), windowSize_.height
//				/ 2.0 - (new_scale / scale_)
//				* (windowSize_.height / 2.0 - offset_.y));
//
//		scale_ = new_scale;
//
//		scaleMatrix_.matrix[0][0] = scaleMatrix_.matrix[1][1] = scaleMatrix_.matrix[2][2] = scale_;
//
//		updateViewTransform_();
//
//		getParent().postEvent(new Event((Object) this, RESIZE, (Object) this));
//
//		paintOver();
//	}
//	private void computeBounds_forDominatorTree() {
//		double oldminx = minx_;
//		double oldminy = miny_;
//
//		minx_ = miny_ = maxx_ = maxy_ = 0;
//
//		Vector<DomNode> nodelist = domGraph.getVertices() ;
//
//		if (nodelist == null)
//			return;
//
//		DomNode tmpnode = nodelist.get(0);
//
//		DPoint tmppoint;
//		Dimension tmpdim;
//
//		if (tmpnode != null) {
//			tmppoint = tmpnode.getPosition();
//			tmpdim = tmpnode.getBoundingBox();
//
//			minx_ = tmppoint.x - tmpdim.width / 2.0;
//			maxx_ = tmppoint.x + tmpdim.width / 2.0;
//			miny_ = tmppoint.y - tmpdim.height / 2.0;
//			maxy_ = tmppoint.y + tmpdim.height / 2.0;
//
//			for (int index = 1; index < nodelist.size(); index ++)
//			{
//				tmpnode = nodelist.get(index);
//				if (tmpnode != null)
//				{
//					tmppoint = tmpnode.getPosition();
//					tmpdim = tmpnode.getBoundingBox();
//
//					double w = tmpdim.width / 2.0;
//					double h = tmpdim.height / 2.0;
//
//					if (tmppoint.x - w < minx_)
//						minx_ = tmppoint.x - w;
//					if (tmppoint.x + w > maxx_)
//						maxx_ = tmppoint.x + w;
//					if (tmppoint.y - h < miny_)
//						miny_ = tmppoint.y - h;
//					if (tmppoint.y + h > maxy_)
//						maxy_ = tmppoint.y + h;
//				}
//
//			}
//		}
//
//		// 3D approach.
//		double maxdim = Math.abs(maxy_);
//		if (Math.abs(miny_) > maxdim)
//			maxdim = Math.abs(miny_);
//		if (Math.abs(minx_) > maxdim)
//			maxdim = Math.abs(minx_);
//		if (Math.abs(maxx_) > maxdim)
//			maxdim = Math.abs(maxx_);
//
//		maxx_ = maxy_ = maxdim;
//		minx_ = miny_ = -maxdim;
//		width_ = height_ = 2.0 * maxdim;
//	}
//
//
//	private void setOffsets_(double offx, double offy) {
//		offset_.x = offx;
//		offset_.y = offy;
//
//		shiftMatrix_.matrix[0][3] = offx;
//		shiftMatrix_.matrix[1][3] = offy;
//
//		updateViewTransform_();
//	}
//
//	private synchronized void updateViewTransform_() {
//		viewTransform_ = new Matrix44(shiftMatrix_);
//		viewTransform_.mult(scaleMatrix_);
//		viewTransform_.mult(rotxMatrix_);
//		viewTransform_.mult(rotzMatrix_);
//
//		moveTransform_ = new Matrix44(rotzMatrix_);
//		moveTransform_.matrix[1][0] = -moveTransform_.matrix[1][0];
//		moveTransform_.matrix[0][1] = -moveTransform_.matrix[0][1];
//
//		Matrix44 tmp_matrix = new Matrix44(rotxMatrix_);
//		tmp_matrix.matrix[2][1] = -tmp_matrix.matrix[2][1];
//		tmp_matrix.matrix[1][2] = -tmp_matrix.matrix[1][2];
//		moveTransform_.mult(tmp_matrix);
//
//		tmp_matrix.setTo(scaleMatrix_);
//		tmp_matrix.matrix[0][0] = 1.0 / tmp_matrix.matrix[0][0];
//		tmp_matrix.matrix[1][1] = 1.0 / tmp_matrix.matrix[1][1];
//		tmp_matrix.matrix[2][2] = 1.0 / tmp_matrix.matrix[2][2];
//		moveTransform_.mult(tmp_matrix);
//
//		tmp_matrix.setTo(shiftMatrix_);
//		tmp_matrix.matrix[0][3] = -tmp_matrix.matrix[0][3];
//		tmp_matrix.matrix[1][3] = -tmp_matrix.matrix[1][3];
//		moveTransform_.mult(tmp_matrix);
//
//		viewTransform_.scale = scale_;
//	}
//
//	public synchronized void paintOver() {
//		Dimension tmpdim = getSize();
//		if (tmpdim.width != windowSize_.width
//				|| tmpdim.height != windowSize_.height) {
//			// Maintain the center point.
//			if (windowSize_.width > 0) // not first time
//			{
//				setOffsets_(offset_.x + (tmpdim.width - windowSize_.width) / 2,
//						offset_.y + (tmpdim.height - windowSize_.height) / 2);
//			} else // initialize to centered
//			{
//				setOffsets_(.5 * (tmpdim.width - (minx_ + maxx_) * scale_),
//						.5 * (tmpdim.height - (miny_ + maxy_) * scale_));
//			}
//
//			windowSize_.width = tmpdim.width;
//			windowSize_.height = tmpdim.height;
//
//			// post an event indicating a size change
//			getParent().postEvent(
//					new Event((Object) this, RESIZE, (Object) this));
//
//			// Force recreation of back buffer.
//			backImage_ = null;
//		}
//
//		if (backImage_ == null)
//			backImage_ = createImage(windowSize_.width, windowSize_.height);
//
//		Graphics back_graphics = getBackGraphics_();
//		back_graphics.setColor(Color.white);
//		back_graphics.setPaintMode();
//		back_graphics.clearRect(0, 0, windowSize_.width, windowSize_.height);
//
//		back_graphics.setColor(Color.black);
//		drawAxes_(back_graphics);
//		drawObjects_(false, back_graphics, 1);
//		back_graphics.dispose();
//
//		Graphics graphics = getGraphics();
//		graphics.setPaintMode();
//		graphics.setColor(Color.black);
//		graphics.drawImage(backImage_, 0, 0, null);
//
//		// Draw selected objects directly to screen.
//		graphics.setFont(font_);
//		drawObjects_(true, graphics, 0);
//
//		graphics.dispose();
//	}
//
//	private synchronized void drawAxes_(Graphics graphics) {
//		String letter;
//		double lx, ly;
//		FontMetrics fm = graphics.getFontMetrics();
//
//		graphics.setColor(Color.black);
//
//		DPoint3 p2 = new DPoint3();
//		p2.move(25, 0, 0);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		graphics.drawLine(40, 40, 40 + (int) p2.x, 40 + (int) p2.y);
//
//		p2.move(32, 0, 0);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		letter = new String("X");
//		lx = p2.x - fm.stringWidth(letter) / 2.0;
//		ly = p2.y + fm.getAscent() / 2.0;
//		graphics.drawString(letter, 40 + (int) lx, 40 + (int) ly);
//
//		p2.move(0, 25, 0);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		graphics.drawLine(40, 40, 40 + (int) p2.x, 40 + (int) p2.y);
//
//		p2.move(0, 32, 0);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		letter = new String("Y");
//		lx = p2.x - fm.stringWidth(letter) / 2.0;
//		ly = p2.y + fm.getAscent() / 2.0;
//		graphics.drawString(letter, 40 + (int) lx, 40 + (int) ly);
//
//		p2.move(0, 0, 25);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		graphics.drawLine(40, 40, 40 + (int) p2.x, 40 + (int) p2.y);
//
//		p2.move(0, 0, 32);
//		p2.transform(rotzMatrix_);
//		p2.transform(rotxMatrix_);
//		letter = new String("Z");
//		lx = p2.x - fm.stringWidth(letter) / 2.0;
//		ly = p2.y + fm.getAscent() / 2.0;
//		graphics.drawString(letter, 40 + (int) lx, 40 + (int) ly);
//	}
//
//	// Get the graphics with the font set.
//	private Graphics getGraphicsInternal_() {
//		Graphics graphics = getGraphics();
//		graphics.setFont(font_);
//		return graphics;
//	}
//
//	private Graphics getBackGraphics_() {
//		Graphics graphics = backImage_.getGraphics();
//		graphics.setFont(font_);
//		return graphics;
//	}
//
//	// Draw selected or unselected objects.
//	public synchronized void drawObjects_(boolean selected, Graphics graphics,
//			int which_gr) {
//		if(ig!=null){
//			DNode tmpnode;
//
//			// Draw nodes.
//			Vector<DNode> nodelistVector  = ig.nodes();
//
//			if (nodelistVector == null)
//				return;
//
//			for (int index = 0; index < nodelistVector.size(); index ++)
//			{
//				tmpnode = nodelistVector.get(index);
//				tmpnode.draw(this, graphics, viewTransform_, quality);
//			}
//			// Draw edges.
//			int num = 0;
//			DEdge edge;
//			while (num < ig.getNumEdges()) {
//				edge = ig.getDEdge(num);
//				boolean arrow_only = false;
//				edge.draw(graphics, viewTransform_, xyPlane_, arrow_only, quality,
//						this, which_gr);
//				graphics.dispose();
//				if (which_gr == 0)
//					graphics = getGraphicsInternal_();
//				else
//					graphics = getBackGraphics_();
//				num++;
//			}
//		}
//		else if(domGraph!=null){
//			DomNode tmpnode;
//
//			// Draw nodes.
//			Vector<DomNode> nodelistVector  = domGraph.getVertices();
//
//			if (nodelistVector == null)
//				return;
//
//			for (int index = 0; index < nodelistVector.size(); index ++)
//			{
//				tmpnode = nodelistVector.get(index);
//				tmpnode.draw(this, graphics, viewTransform_, quality);
//			}
//			// Draw edges.
//			int num = 0;
//			DOMEdge edge;
//			while (num < domGraph.getNumEdges()) {
//				edge = domGraph.getEdge(num);
//				boolean arrow_only = false;
//				edge.draw(graphics, viewTransform_, xyPlane_, arrow_only, quality,
//						this, which_gr);
//				graphics.dispose();
//				if (which_gr == 0)
//					graphics = getGraphicsInternal_();
//				else
//					graphics = getBackGraphics_();
//				num++;
//		    }
//		}
//	}
//
//	public Dimension contentsSize() {
//		double w = width_ * scale_ + (double) windowSize_.width * 2.0;
//		double h = height_ * scale_ + (double) windowSize_.height * 2.0;
//
//		return new Dimension((int) w, (int) h);
//	}
//
//	public void setOffsets(double xoffset, double yoffset, boolean redraw) {
//		offsetx_ = xoffset / scale_;
//		offsety_ = yoffset / scale_;
//		computeDrawOffset_();
//		if (redraw)
//			paintOver();
//	}
//
//	public DPoint getOffset() {
//		DPoint val = new DPoint(0, 0);
//
//		val.x = -(offset_.x - (double) windowSize_.width) - minx_ * scale_;
//		val.y = -(offset_.y - (double) windowSize_.height) - miny_ * scale_;
//
//		return val;
//	}
//
//	private void computeDrawOffset_() {
//		setOffsets_((-offsetx_ - minx_) * scale_ + windowSize_.width,
//				(-offsety_ - miny_) * scale_ + windowSize_.height);
//	}
//
//	public void drawRotatedText(String string, double theta, int cx, int cy,
//			Graphics graphics_in, int which_gr) {
//		FontMetrics fm = graphics_in.getFontMetrics();
//		int label_w = fm.stringWidth(string);
//		int label_h = fm.getHeight();
//
//		double cos_theta = Math.cos(theta);
//		if (cos_theta < 0.0) {
//			theta += Math.PI;
//			cos_theta = -cos_theta;
//		}
//		double sin_theta = Math.sin(theta);
//		cx += -sin_theta * label_h / 2.0;
//		cy += -cos_theta * label_h / 2.0;
//
//		graphics_in.dispose();
//
//		Image tmp_image = createImage(label_w, label_h);
//		Graphics graphics = tmp_image.getGraphics();
//		graphics.setFont(font_);
//		graphics.setColor(new Color(0));
//		graphics.drawString(string, 0, fm.getAscent());
//		graphics.dispose();
//
//		if (true)
//			return;
//		// below not supported.
//		int[] pixels = null;
//		//			Node.getImagePixels(tmp_image, label_w, label_h);
//
//		if (pixels == null)
//			return;
//
//		int x, y;
//		int image_size = (int) Math.ceil(Math.sqrt((double) (label_w * label_w)
//				+ (double) (label_h * label_h))) + 2;
//
//		int[] result = new int[image_size * image_size];
//
//		rotImage_(theta, pixels, label_w, label_h, result, image_size);
//
//		cx -= image_size / 2;
//		cy -= image_size / 2;
//
//		if (which_gr == 0)
//			graphics_in = getGraphicsInternal_();
//		else
//			graphics_in = getBackGraphics_();
//		for (y = 0; y < image_size; y++) {
//			for (x = 0; x < image_size; x++) {
//				if (result[y * image_size + x] > 0) {
//					graphics_in
//							.setColor(aaShades_[result[y * image_size + x] / 2]);
//					graphics_in.drawLine(cx + x, cy + y, cx + x, cy + y);
//				}
//
//			}
//		}
//		graphics_in.dispose();
//	}
//
//	private void rotImage_(double theta, int[] pixels, int w, int h,
//			int[] result, int image_size) {
//		double dxX = Math.cos(theta);
//		double dyX = -Math.sin(theta);
//		double dxY = -dyX;
//		double dyY = dxX;
//		double dxIx = dxX / (double) aaDivs_;
//		double dyIx = dyX / (double) aaDivs_;
//		double dxIy = dxY / (double) aaDivs_;
//		double dyIy = dyY / (double) aaDivs_;
//		double hdxIx = dxIx / 2.0, hdyIx = dyIx / 2.0;
//		double hdxIy = dxIy / 2.0, hdyIy = dyIy / 2.0;
//
//		double xX = image_size / 2.0 - dxX * w / 2.0 - dxY * h / 2.0;
//		double yX = image_size / 2.0 - dyX * w / 2.0 - dyY * h / 2.0;
//
//		double xY, yY, Ix, Iy, xIx, yIx, xIy, yIy;
//		int x, y;
//		for (x = 0; x < w; x++) {
//			xY = xX;
//			yY = yX;
//			for (y = 0; y < h; y++) {
//				xIx = xY + hdxIx;
//				yIx = yY + hdyIx;
//				if ((pixels[y * w + x] & 0xFFFFFF) == 0) {
//					for (Ix = 0; Ix < aaDivs_; Ix++) {
//						xIy = xIx + hdxIy;
//						yIy = yIx + hdyIy;
//						for (Iy = 0; Iy < aaDivs_; Iy++) {
//							if (((int) yIy * image_size + (int) xIy > 0)
//									&& ((int) yIy * image_size + (int) xIy < image_size
//											* image_size))
//								result[(int) yIy * image_size + (int) xIy]++;
//
//							xIy += dxIy;
//							yIy += dyIy;
//						}
//						xIx += dxIx;
//						yIx += dyIx;
//					}
//				}
//				xY += dxY;
//				yY += dyY;
//			}
//			xX += dxX;
//			yX += dyX;
//		}
//	}
//
//	public void center_forInterferenceGraph() {
//		computeBounds_forInterferenceGraph();
//
//		setOffsets_(.5 * (windowSize_.width - (minx_ + maxx_) * scale_),
//				.5 * (windowSize_.height - (miny_ + maxy_) * scale_));
//		getParent().postEvent(new Event((Object) this, RESIZE, (Object) this));
//
//		paintOver();
//	}
//
//	public void update_forInterferenceGraph(boolean adjust_bounds) {
//		currentMouseAction_ = 0;
//
//		if (adjust_bounds) {
//			computeBounds_forInterferenceGraph();
//			getParent().postEvent(
//					new Event((Object) this, RESIZE, (Object) this));
////			center_forInterferenceGraph();
//		}
//		paintOver();
//		getParent().postEvent(new Event((Object) this, UPDATE, null));
//	}
//
//	public void center_forDominatorTree() {
//		computeBounds_forDominatorTree();
//
//		setOffsets_(.5 * (windowSize_.width - (minx_ + maxx_) * scale_),
//				.5 * (windowSize_.height - (miny_ + maxy_) * scale_));
//		getParent().postEvent(new Event((Object) this, RESIZE, (Object) this));
//
//		paintOver();
//	}
//
//	public void update_forDominatorTree(boolean adjust_bounds) {
//		currentMouseAction_ = 0;
//
//		if (adjust_bounds) {
//			computeBounds_forDominatorTree();
//			getParent().postEvent(
//					new Event((Object) this, RESIZE, (Object) this));
////			center_forDominatorTree();
//		}
//		paintOver();
//		getParent().postEvent(new Event((Object) this, UPDATE, null));
//	}
//
//	public double getHSpacing() {
//		return 1 / scale_ * hSpacing;
//	}
//
//	public double getVSpacing() {
//		return 1 / scale_ * vSpacing;
//	}
//
//
//	public void scale(double scaleval) {
//		setScale(scaleval);
//	}
//
//
//	public DRect windowRect() {
//		return new DRect(-offset_.x / scale_, -offset_.y / scale_,
//				windowSize_.width / scale_, windowSize_.height / scale_);
//	}
//
//	public boolean handleEvent(Event e) {
//		if (e.id == DragFix.QUEUED) {
//			super.handleEvent((Event) e.arg);
//			getParent().postEvent((Event) e.arg);
//			return true;
//		}
//		dragFix_.queueEvent(e);
//		return true;
//	}
//
//	public void update (boolean interferenceGraph, boolean adjust_bounds) {
//		currentMouseAction_ = 0;
//
//		if (adjust_bounds) {
//			computeBounds_forInterferenceGraph();
//			getParent().postEvent(
//					new Event((Object) this, RESIZE, (Object) this));
//
//			String string = "Label";
//			getParent().postEvent(new Event((Object) this, LABEL, string));
//
//		}
//		paintOver();
//		getParent().postEvent(new Event((Object) this, UPDATE, null));
//	}
//
//
//	 public void setViewAngles(double theta, double phi)
//     {
//        rotxMatrix_.matrix[1][1] = rotxMatrix_.matrix[2][2] = -Math.cos(-phi + Math.PI / 2.0);
//        rotxMatrix_.matrix[2][1] = -(rotxMatrix_.matrix[1][2] = -Math.sin(-phi + Math.PI / 2.0));
//
//        rotzMatrix_.matrix[0][0] = rotzMatrix_.matrix[1][1] = Math.cos(-theta);
//        rotzMatrix_.matrix[1][0] = -(rotzMatrix_.matrix[0][1] = -Math.sin(-theta));
//
//        updateViewTransform_();
//
//        xyPlane_ = (theta == 0.0 && phi == Math.PI / 2.0);
//
//        paintOver();
//     }
//
//     public void setWireframe(boolean wireframe)
//     {
//        if(wireframe)
//           quality = 0;
//        else
//           quality = qualityCB_;
//     }

}
