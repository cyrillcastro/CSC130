 /**
 * Cyrill Castro
 * Project 3
 * CSC 130
 * 
 * AVL Tree implementation. Extends BinarySearchTree. Includes a balance method.  
 * 
 */

import java.lang.Math;

public class Avl<E extends Comparable<? super E>> extends BinarySearchTree<E> implements 
		DataCounter<E> {
	
	public Avl() {
		overallRoot = null;
        size = 0;
	}

    /**
     * Insert data into tree. Can just use super method.
     * AVL will then balance the tree by implementing the needed
     * rotation. 
     */
    public void incCount(E data) {
    	balance(data, overallRoot);
    }
    
    protected BSTNode balance(E data, BSTNode root) {
		BSTNode currentNode = overallRoot;
    	
    	if (root == null) {
    		root = new BSTNode(data);
    	} else if (data.compareTo(currentNode.data) < 0) {
    		currentNode.left = balance(data, currentNode.left);
    		if (height(currentNode.left) - height(currentNode.right) > 1) {				// Left subtree height is greater than right subtree height by more than 1
        		if (height(currentNode.left.left) >= height(currentNode.left.right)) 
        			currentNode = singleRightRotation(currentNode);
        		else 
            		currentNode = doubleLeftRightRotation(currentNode);
    		}
    	} else if (data.compareTo(currentNode.data) > 0) {
    		currentNode.right = balance(data, currentNode.right);
    		if (height(currentNode.right) - height(currentNode.left) > 1) {		// Right subtree heiht is greater than left subtree height by more than 1
	    		if (height(currentNode.right.right) >= height(currentNode.right.left))
	    			currentNode = singleLeftRotation(currentNode);
	    		else 
	    			currentNode = doubleRightLeftRotation(currentNode);
    	    }
    	} else 
    		currentNode.count++;											// No difference = match; increase the count
 
    	return currentNode;
    }
    
    //Case 1
    private BSTNode singleRightRotation(BSTNode k2) {
    	BSTNode k1 = k2.left;
    	k2.left = k1.right;
    	k1.right = k2;
    	k2.height = (1 + Math.max(height(k2.left), height(k2.right)));
    	k1.height = (1 + Math.max(height(k1.right), height(k2)));
		
		return k1;
    }
    
  //Case 4
  	private BSTNode singleLeftRotation(BSTNode k1) {
		BSTNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = 1 + Math.max(height(k1.left), height(k1.right));
		k2.height = 1 + Math.max(height(k2.right), height(k1));
		
		return k2;
	}
    
    //Case 2
  	private BSTNode doubleLeftRightRotation(BSTNode k3) {
  		k3.left = singleLeftRotation(k3.left);
  		return singleRightRotation(k3);
  	}
  	
  	//Case 3
  	private BSTNode doubleRightLeftRotation(BSTNode k1) {
		k1.right = singleRightRotation(k1.right);
		return singleLeftRotation(k1);
	}
  	
  	public boolean checkBalance()  {
		if (overallRoot == null)
			return true;
		else {
			int leftHeight = height(overallRoot.left);
			int rightHeight = height(overallRoot.right);
			return Math.abs(leftHeight - rightHeight) <= 1;
		}
	}


    /**
     * The number of unique data elements in the structure.
     * 
     * @return the number of unique data elements in the structure.
     */
    public int getSize() {
    	return super.getSize();
    }

    /**
     * Get an array of all of the data counts in the DataCounter structure. The
     * array should contain exactly one DataCount instance for each unique
     * element inserted into the structure. The elements do not need to be in
     * any particular order.
     * 
     * @return an array of the data counts.
     */
    public DataCount<E>[] getCounts() {
    	return super.getCounts();
    }
    
}