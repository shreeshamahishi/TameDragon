package org.tamedragon.visualization.algorithm;

import java.util.Vector;

import org.tamedragon.visualization.components.*;
import org.tamedragon.visualization.util.DPoint;

public class TreeAlgorithm implements GraphAlgorithm {
	private char rootOrient_;
	private DPoint rootPos_;
	private DomGraph graph_;
	private int depth_;
	private DomNode prevNodeAtLevel_[];
	private double levelHeight_[];
	private double levelPosition_[];

	private double levelSeparation_;
	private double subtreeSeparation_;
	private double siblingSeparation_;

	// orientation d --> down
	// u ---> up, r ---> right
	// l ---> left
	public TreeAlgorithm(char orientation) {
		rootOrient_ = orientation;
	}

	public String compute(DomGraph graph, GraphUpdate update) {
		DomNode root = graph.getStartNode();
		DomNode tmpnode;

		if (root == null)
			return (String) "You need to select a root node.";

		levelSeparation_ = update.getVSpacing();
		subtreeSeparation_ = siblingSeparation_ = update.getHSpacing();

		graph_ = graph;

		rootPos_ = root.getPosition();

		// Construct the Node data fields.
		for (DomNode tempnode : graph_.getVertices())
			tempnode.data = new TreeAlgorithmData();

		// Initialize the data fields.
		depth_ = 0;
		initializeData_(root, 0);

		// Build and initialize array for connecting neighboring non-sibling nodes.
		prevNodeAtLevel_ = new DomNode[depth_ + 1];
		int i;
		for (i = 0; i < depth_ + 1; i++)
			prevNodeAtLevel_[i] = null;

		setInitialPositions_(root);
		levelHeight_ = new double[depth_ + 1];
		for (i = 0; i < depth_ + 1; i++)
			levelHeight_[i] = 0.0;
		setLevelHeight_(root);
		levelPosition_ = new double[depth_ + 1];
		levelPosition_[0] = 0;
		for (i = 1; i < depth_ + 1; i++)
			levelPosition_[i] = levelPosition_[i - 1]
					+ (levelHeight_[i - 1] + levelHeight_[i]) / 2.0;
		setFinalPositions_(root, 0.0);

		// Shift root to original position.
		double xoffs = rootPos_.x - root.getPosition().x;
		double yoffs = rootPos_.y - root.getPosition().y;
		for (tmpnode = graph.getStartNode(); tmpnode != null; tmpnode = graph
				.nextNode(tmpnode))
			if (((TreeAlgorithmData) (tmpnode.data)).level != -1) {
				DPoint pos = tmpnode.getPosition();
				tmpnode.setPosition(pos.x + xoffs, pos.y + yoffs);
			}

		// Create group nodes;
		// createGroups_(root);
		// groupNodes_(root);

		return null;
	}

	private void initializeData_(DomNode node, int level) {
		((TreeAlgorithmData) (node.data)).level = level;

		if (level > depth_)
			depth_ = level;

		DomNode children[] = new DomNode[node.getSuccessors().size()];
		//   DomNode child;
		int num_children = 0;

		// Put all the unvisited children in the list.

		Vector<DomNode> childNodes = node.getSuccessors();

		for (DomNode child : childNodes) {

			if (((TreeAlgorithmData) graph_.getNode(child).data).level == -1)
				children[num_children++] = graph_.getNode(child);
		}

		if (num_children == 0) {
			((TreeAlgorithmData) (node.data)).isLeaf = true;
			return;
		}

		// Sort the children by "left" coordinate;
		int i, j;
		DomNode tmpnode;
		for (i = 0; i < num_children - 1; i++)
			for (j = i + 1; j < num_children; j++)
				if (((rootOrient_ == 'd' || rootOrient_ == 'u') && children[j]
						.getPosition().x < children[i].getPosition().x)
						|| ((rootOrient_ == 'l' || rootOrient_ == 'r') && children[j]
								.getPosition().y < children[i].getPosition().y)) {
					tmpnode = children[i];
					children[i] = children[j];
					children[j] = tmpnode;
				}

		// Initialize links.
		((TreeAlgorithmData) (node.data)).leftChild = children[0];
		((TreeAlgorithmData) (node.data)).rightChild = children[num_children - 1];
		for (i = 0; i < num_children; i++)
			((TreeAlgorithmData) (children[i].data)).parent = node;
		for (i = 0; i < num_children - 1; i++)
			((TreeAlgorithmData) (children[i].data)).rightSibling = children[i + 1];
		for (i = 1; i < num_children; i++)
			((TreeAlgorithmData) (children[i].data)).leftSibling = children[i - 1];
		for (i = 0; i < num_children; i++)
			((TreeAlgorithmData) (children[i].data)).level = level + 1;

		// Initialize data for the children.
		for (i = 0; i < num_children; i++)
			initializeData_(children[i], level + 1);
	}

	private void setInitialPositions_(DomNode node) {
		TreeAlgorithmData data = (TreeAlgorithmData) (node.data);

		// Initialize neighbor links.
		data.leftNeighbor = prevNodeAtLevel_[data.level];
		if (data.leftNeighbor != null)
			((TreeAlgorithmData) (data.leftNeighbor.data)).rightNeighbor = node;
		prevNodeAtLevel_[data.level] = node;

		DomNode tmpnode;
		if (data.leftSibling != null) {
			if (rootOrient_ == 'd' || rootOrient_ == 'u')
				data.prelim = ((TreeAlgorithmData) (data.leftSibling.data)).prelim
						+ siblingSeparation_
						+ (node.getBoundingBox().width + data.leftSibling
								.getBoundingBox().width) / 2.0;
			else
				data.prelim = ((TreeAlgorithmData) (data.leftSibling.data)).prelim
						+ siblingSeparation_
						+ (node.getBoundingBox().height + data.leftSibling
								.getBoundingBox().height) / 2.0;
		}

		if (!data.isLeaf)
		// Bottom-up, left-to-right recurse.
		{
			for (tmpnode = data.leftChild; tmpnode != null; tmpnode = ((TreeAlgorithmData) (tmpnode.data)).rightSibling)
				setInitialPositions_(tmpnode);

			double mid_point = (((TreeAlgorithmData) (data.leftChild.data)).prelim + ((TreeAlgorithmData) (data.rightChild.data)).prelim) / 2.0;

			if (data.leftSibling != null) {
				data.modifier = data.prelim - mid_point;
				evenOut(node);
			} else
				data.prelim = mid_point;
		}

	}

	/**
	 * Called when two subtree are moved apart.  It evens out the position
	 * of all the other subtrees between them.
	 **/
	private void evenOut(DomNode node) {
		TreeAlgorithmData data = (TreeAlgorithmData) (node.data);

		DomNode left_most = data.leftChild;
		DomNode neighbor = ((TreeAlgorithmData) left_most.data).leftNeighbor;
		int compare_depth = 1;
		while (left_most != null && neighbor != null) {
			double left_mod_sum = 0.0;
			double right_mod_sum = 0.0;

			DomNode ancestor_leftmost = left_most;
			DomNode ancestor_neighbor = neighbor;

			int i;
			for (i = 0; i < compare_depth; i++) {
				ancestor_leftmost = ((TreeAlgorithmData) ancestor_leftmost.data).parent;
				ancestor_neighbor = ((TreeAlgorithmData) ancestor_neighbor.data).parent;

				right_mod_sum += ((TreeAlgorithmData) ancestor_leftmost.data).modifier;
				left_mod_sum += ((TreeAlgorithmData) ancestor_neighbor.data).modifier;
			}

			double move_distance;
			if (rootOrient_ == 'd' || rootOrient_ == 'u')
				move_distance = ((TreeAlgorithmData) neighbor.data).prelim
						+ left_mod_sum
						+ subtreeSeparation_
						+ (left_most.getBoundingBox().width + neighbor
								.getBoundingBox().width) / 2.0
						- ((TreeAlgorithmData) left_most.data).prelim
						- right_mod_sum;
			else
				move_distance = ((TreeAlgorithmData) neighbor.data).prelim
						+ left_mod_sum
						+ subtreeSeparation_
						+ (left_most.getBoundingBox().height + neighbor
								.getBoundingBox().height) / 2.0
						- ((TreeAlgorithmData) left_most.data).prelim
						- right_mod_sum;

			if (move_distance > 0.0) {
				DomNode tmpnode;
				int left_sibling;
				for (tmpnode = node, left_sibling = 0; tmpnode != null
						&& tmpnode != ancestor_neighbor; tmpnode = ((TreeAlgorithmData) tmpnode.data).leftSibling)
					left_sibling++;

				if (tmpnode != null) {
					double slide_value = move_distance / (double) left_sibling;
					for (tmpnode = node; tmpnode != ancestor_neighbor; tmpnode = ((TreeAlgorithmData) tmpnode.data).leftSibling) {
						((TreeAlgorithmData) tmpnode.data).prelim += move_distance;
						((TreeAlgorithmData) tmpnode.data).modifier += move_distance;
						move_distance -= slide_value;
					}
				} else
					return;
			}

			compare_depth++;
			if (((TreeAlgorithmData) left_most.data).leftChild == null)
				left_most = leftMost_(node, 0, compare_depth);
			else
				left_most = ((TreeAlgorithmData) left_most.data).leftChild;
			if (left_most != null)
				neighbor = ((TreeAlgorithmData) left_most.data).leftNeighbor;
		}
	}

	private DomNode leftMost_(DomNode node, int level, int depth) {
		if (level >= depth)
			return node;
		if (((TreeAlgorithmData) node.data).leftChild == null)
			return null;

		DomNode left_most, right_most;
		right_most = ((TreeAlgorithmData) node.data).leftChild;
		left_most = leftMost_(right_most, level + 1, depth);
		while (left_most == null
				&& ((TreeAlgorithmData) right_most.data).rightSibling != null) {
			right_most = ((TreeAlgorithmData) right_most.data).rightSibling;
			left_most = leftMost_(right_most, level + 1, depth);
		}
		return left_most;
	}

	private void setFinalPositions_(DomNode node, double mod_sum) {
		double x = 0.0, y = 0.0;
		DomNode tmpnode;
		TreeAlgorithmData data = (TreeAlgorithmData) node.data;

		x = data.prelim + mod_sum;
		y = -data.level * levelSeparation_ - levelPosition_[data.level];

		if (data.leftChild != null)
			setFinalPositions_(data.leftChild, mod_sum + data.modifier);

		if (data.rightSibling != null)
			setFinalPositions_(data.rightSibling, mod_sum);

		if (rootOrient_ == 'd')
			node.setPosition(x, y);
		else if (rootOrient_ == 'u')
			node.setPosition(x, -y);
		else if (rootOrient_ == 'l')
			node.setPosition(y, x);
		else if (rootOrient_ == 'r')
			node.setPosition(-y, x);
	}

	private void setLevelHeight_(DomNode node) {
		TreeAlgorithmData data = (TreeAlgorithmData) node.data;

		if (rootOrient_ == 'd' || rootOrient_ == 'u') {
			if (node.getBoundingBox().height > levelHeight_[data.level])
				levelHeight_[data.level] = node.getBoundingBox().height;
		} else {
			if (node.getBoundingBox().width > levelHeight_[data.level])
				levelHeight_[data.level] = node.getBoundingBox().width;
		}

		if (data.leftChild != null)
			setLevelHeight_(data.leftChild);
		if (data.rightSibling != null)
			setLevelHeight_(data.rightSibling);
	}

	public String compute(DInterferenceGraph graph, GraphUpdate update) {
		// TODO Auto-generated method stub
		return null;
	}

	/*  private void createGroups_(DomNode node)
	  {
	     TreeAlgorithmData data = (TreeAlgorithmData)node.data;

	     if(data.leftChild != null)
	        createGroups_(data.leftChild);
	     if(data.rightSibling != null)
	        createGroups_(data.rightSibling);

	     if(data.leftChild == null)
	        return;

	     int groupnode_id = graph_.insertNode();
	     DomNode groupnode = graph_.nodeFromIndex(groupnode_id);
	     groupnode.setGroup();

	     data.group = groupnode;
	  }



	  private void groupNodes_(DomNode node)
	  {
	     TreeAlgorithmData data = (TreeAlgorithmData)node.data;

	     if(data.leftChild != null)
	        groupNodes_(data.leftChild);
	     if(data.rightSibling != null)
	        groupNodes_(data.rightSibling);

	     if(data.leftChild == null)
	        return;

	     DomNode child, tmpnode, groupnode;
	     TreeAlgorithmData child_data;
	     groupnode = data.group;
	     // Put all tree children in the group.
	     for(child = data.leftChild; child != null; child = child_data.rightSibling)
	     {
	        child_data = (TreeAlgorithmData)child.data;
	        tmpnode = child;
	        if(child_data.group != null)
	           tmpnode = child_data.group;
	        graph_.setNodeGroup(tmpnode, groupnode);
	     }

	    // Put the node itself in the group.
	     graph_.setNodeGroup(node, groupnode);
	  }
	 */
}
