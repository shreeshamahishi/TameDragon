package org.tamedragon.visualization.algorithm;

import org.tamedragon.visualization.components.DomNode;

public class TreeAlgorithmData {


      public int level;
      public DomNode parent;
      public DomNode leftChild;
      public DomNode rightChild;
      public DomNode leftSibling;
      public DomNode rightSibling;
      public DomNode leftNeighbor;
      public DomNode rightNeighbor;
      public boolean isLeaf;
      public double modifier;
      public double prelim;
      public DomNode group;
   
      public TreeAlgorithmData()
      {
         level = -1;
         parent = leftChild = rightChild = leftSibling = rightSibling =
            leftNeighbor = rightNeighbor = null;
         isLeaf = false;
         modifier = prelim = 0;
         group = null;
      }
	   
	 
}
