package org.tamedragon.visualization;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.tamedragon.common.InterferenceGraph;
import org.tamedragon.visualization.algorithm.*;
import org.tamedragon.visualization.components.DInterferenceGraph;
import org.tamedragon.visualization.components.DomGraph;
import org.tamedragon.visualization.util.DPoint;

public class GraphWindow //extends JDialog implements ActionListener {
{

	private static final long serialVersionUID = 1L;
	private GraphCanvas graphCanvas_;
	private ScrolledPanel viewingPanel_;
	private DInterferenceGraph ug;
	private DomGraph domGraph;
	private boolean interferenceGraph = true;
	private ViewportScroller portScroller_;
	private CheckboxMenuItem cbmiDirected_;

	JButton applyAlg;
	private Panel controls_;
	private JLabel scaleLabel_;
	private double scale_;
	private String title;

//	public GraphWindow(InterferenceGraph ug) {
//		this.ug = new DInterferenceGraph(ug);
//		System.out.println("WAIT HERE: in GraphWindow construttor converted ig graph size: " + this.ug.nodes().size() );
//		setTitle(title = "Interference Graph");
//		buildWindow();
//	}
//
//	public GraphWindow(DomGraph domGraph) {
//		this.domGraph = domGraph;
//		setTitle(title = "Dominator Tree");
//		interferenceGraph = false;
//		buildWindow();
//	}
//
//	private void construct() {
//		// the graph viewing canvas
//		if (ug != null)
//			graphCanvas_ = new GraphCanvas(ug);
//		else if (domGraph != null)
//			graphCanvas_ = new GraphCanvas(domGraph);
//
//		// panel for controls
//
//		LPanel p = new LPanel();
//
//		portScroller_ = new ViewportScroller(90, 90, 500.0, 500.0, 400.0,
//				400.0, 0.0, 0.0);
//		portScroller_.setBackground(Color.lightGray);
//
//		p.constraints.insets.bottom = 0;
//		p.addLabel("Viewing Offset", 0, 0, 1.0, 1.0, 0, 0);
//		p.constraints.insets.top = p.constraints.insets.bottom = 0;
//		p.addComponent(portScroller_, 0, 0, 1.0, 1.0, 0, 0);
//		p.constraints.insets.top = p.constraints.insets.bottom = 0;
//		JButton centerButton = p.addButton("Center", 0, 0, 1.0, 1.0, 0, 0);
//		centerButton.addActionListener(this);
//		centerButton.setActionCommand("Center");
//
//		scaleLabel_ = p.addLineLabel("Scale: 1", 0);
//
//		LPanel sp = new LPanel();
//		sp.spacing = 0;
//		sp.constraints.insets.top = sp.constraints.insets.bottom = 0;
//		JButton scale1 = sp.addButton("Scale / 2", 1, -1, 1.0, 1.0, 0, 0);
//		scale1.addActionListener(this);
//		scale1.setActionCommand("Scale / 2");
//		JButton scale2 = sp.addButton("Scale = 1", 1, -1, 1.0, 1.0, 0, 0);
//		scale2.addActionListener(this);
//		scale2.setActionCommand("Scale = 1");
//
//		JButton scale3 = sp.addButton("Scale * 2", 0, -1, 1.0, 1.0, 0, 0);
//		scale3.addActionListener(this);
//		scale3.setActionCommand("Scale * 2");
//
//		sp.finish();
//
//		p.addComponent(sp, 0, 0, 1.0, 1.0, 0, 0);
//
//		p.constraints.insets.top = 0;
//		AngleControlPanel angc = new AngleControlPanel(180, 76);
//		p.addComponent(angc, 0, 0, 1.0, 1.0, 1, 0);
//
//		// super panel is there just to allow controls to be
//		// fixed to top instead of centered
//		Panel controls_superpanel = new Panel();
//
//		controls_superpanel.add("North", p);
//		p.finish();
//		add("West", controls_superpanel);
//		controls_ = controls_superpanel;
//		controls_.setVisible(true);
//
//		// the graph viewing panel (canvas and scrollbars)
//		viewingPanel_ = new ScrolledPanel(graphCanvas_);
//		validate();
//	}
//
//	void buildWindow() {
//		setLayout(new BorderLayout());
//		construct();
//		getContentPane().add(viewingPanel_);
//		//addToolbar();
//		//		getContentPane().add(addAlgorithmPanel(), BorderLayout.NORTH);
//		setPreferredSize(new Dimension(1024, 800));
//		setSize(getPreferredSize());
//		setBackground(Color.lightGray);
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setModal(true);
//		validate();
//		keepCenter();
//		pack();
//	}
//
//	public void applyAlgorithm(String algrithm) {
//
//		GraphAlgorithm alg = null;
//		if (interferenceGraph) {
//			if ((ug == null || ug.getNodeCount() < 1)) {
//				JOptionPane.showMessageDialog(this, "Graph is empty.", "Error",
//						JOptionPane.WARNING_MESSAGE);
//				return;
//			}
//			alg = new Spring();
//
//		} else {
//			if (domGraph == null || domGraph.getVertices().size() < 1) {
//				JOptionPane.showMessageDialog(this, "Graph is empty.", "Error",
//						JOptionPane.WARNING_MESSAGE);
//				return;
//			}
//			alg = new TreeAlgorithm('d');
//		}
//
//		if (alg != null) {
//
//			final GraphAlgorithm algorithm = alg;
//			SwingUtilities.invokeLater(new Runnable()
//			{
//				public void run() {
//					String msg = null;
//					setTitle(title + "  Applying Algorithm...");
//					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//					if (interferenceGraph)
//						msg = algorithm.compute(ug, graphCanvas_);
//					else
//						msg = algorithm.compute(domGraph, graphCanvas_);
//
//					setCursor(null);
//					if (msg != null) {
//						JOptionPane.showMessageDialog(null, "Message", msg,
//								JOptionPane.WARNING_MESSAGE);
//						return;
//					}
//
//					if (interferenceGraph)
//						graphCanvas_.update_forInterferenceGraph(true);
//					else
//						graphCanvas_.update_forDominatorTree(true);
//
//					setTitle(title);
//
//					actionPerformed(new ActionEvent(this, -1, "Scale = 1"));
//					actionPerformed(new ActionEvent(this, -1, "Center"));
//				}
//			});
//		}
//	}
//
//
//	private JPanel addAlgorithmPanel() {
//		JPanel algorithmPanel = new JPanel();
//		applyAlg = new JButton("Click to get better view");
//		applyAlg.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent event) {
//				applyAlgorithm("Spring");
//			}
//		});
//		algorithmPanel.add(applyAlg);
//		return algorithmPanel;
//	}
//
//	void keepCenter() {
//		final Dimension screenSize = Toolkit.getDefaultToolkit()
//				.getScreenSize();
//		final Dimension applicationSize = getSize();
//		final int x = Math.abs((screenSize.width - applicationSize.width) / 2);
//		final int y = Math
//				.abs((screenSize.height - applicationSize.height) / 2);
//		setLocation(x, y);
//	}
//
//	public boolean handleEvent(Event event) {
//		if (event.target instanceof ScrolledPanel) {
//			if (event.id == ScrolledPanel.RESIZE)
//			// RESIZE event from panel.
//			{
//				Dimension tmp_dim;
//
//				tmp_dim = viewingPanel_.getPortSize();
//				portScroller_.setPortSize(tmp_dim.width, tmp_dim.height);
//
//				tmp_dim = viewingPanel_.getContentSize();
//				portScroller_.setContentSize(tmp_dim.width, tmp_dim.height);
//
//				tmp_dim = viewingPanel_.getOffset();
//				portScroller_.setOffset(tmp_dim.width, tmp_dim.height);
//
//				return true;
//			} else if (event.id == ScrolledPanel.OFFSET) {
//				Dimension tmp_dim = viewingPanel_.getOffset();
//				portScroller_.setOffset(tmp_dim.width, tmp_dim.height);
//
//				return true;
//			}
//		} else if (event.target instanceof ViewportScroller) {
//			if (event.id == ViewportScroller.SCROLL) {
//				graphCanvas_.setWireframe(true);
//				viewingPanel_.scrollTo((int) portScroller_.getOffsetX(),
//						(int) portScroller_.getOffsetY());
//			}
//			if (event.id == ViewportScroller.DONE) {
//				graphCanvas_.setWireframe(false);
//				viewingPanel_.scrollTo((int) portScroller_.getOffsetX(),
//						(int) portScroller_.getOffsetY());
//			}
//		} else if (event.target instanceof AngleControlPanel) {
//			if (event.id == AngleControlPanel.ANGLE) {
//				DPoint angles = (DPoint) event.arg;
//
//				graphCanvas_.setWireframe(true);
//				graphCanvas_.setViewAngles(angles.x, angles.y);
//			}
//			if (event.id == AngleControlPanel.DONE) {
//				DPoint angles = (DPoint) event.arg;
//
//				graphCanvas_.setWireframe(false);
//				graphCanvas_.setViewAngles(angles.x, angles.y);
//			}
//		}
//		// quit from Window Manager menu
//		else if (event.id == Event.WINDOW_DESTROY) {
//			Destroy();
//			return false;
//		}
//
//		// call inherited handler
//		return super.handleEvent(event);
//	}
//
//	//Function is called on an exit menu choice or delete from the WM menu
//	private void Destroy() {
//		dispose();
//	}
//
//	public void actionPerformed(ActionEvent event) {
//		String what = event.getActionCommand();
//		if (((String) what).equals("Scale / 2")) {
//			scale_ /= 2.0;
//			graphCanvas_.setScale(scale_);
//
//			scaleLabel_.setText("Scale: " + scale_);
//		} else if (((String) what).equals("Scale * 2")) {
//			scale_ *= 2.0;
//			graphCanvas_.setScale(scale_);
//
//			scaleLabel_.setText("Scale: " + scale_);
//		} else if (((String) what).equals("Scale = 1")) {
//			scale_ = 1.0;
//			graphCanvas_.setScale(scale_);
//
//			scaleLabel_.setText("Scale: " + scale_);
//		} else if (((String) what).equals("Center")) {
//			Dimension port_dim = viewingPanel_.getPortSize();
//			Dimension cont_dim = viewingPanel_.getContentSize();
//
//			double x = (cont_dim.width - port_dim.width) / 2.0;
//			double y = (cont_dim.height - port_dim.height) / 2.0;
//
//			viewingPanel_.scrollTo((int) x, (int) y);
//			portScroller_.setOffset(x, y);
//		}
//	}
}
