/* * 
 * Cyrill Castro
 * CSC 130, Section 02
 * Professor A. Baynes & Professor J Chidella
 * 
 * Project 2: AVL and Binary Search Trees
 * */

import java.util.Random;

public class TreeDriver {

	public static void main(String[] args) throws Exception {
		System.out.println("Cyrill Castro");
		System.out.println("CSC 130, Section 2");
		System.out.println("Prof A. Baynes & Prof J. Chidella\n");
		System.out.println("Project 2: AVL Trees and Binary Search Trees\n\n");
		
		System.out.println("Part 1: Inserting and Deleting Nodes");
		
		Random rand = new Random();
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
				
		//Print the BST with height 5
		while (bst.height() < 5) {
			bst.insert(rand.nextInt(89) + 10);
			new TreePrinter(bst).print("\n***Binary Search Tree: After New Insertion***\n");
		}
		
		//Repeatedly delete the root of the tree until it's empty.
		while (!bst.isEmpty()) {
			bst.remove(bst.getRoot().getData());
			new TreePrinter(bst).print("\n***Binary Search Tree: After Deleting Root***\n");
		}
		
		System.out.println();
		
		/* *
		 * Create an AVL tree node by node. Generate 35 unique random integers 10-99 to
		 * insert into the tree. Print the tree after each insertion to verify that you
		 * are keeping it balanced. Each time you do a rebalancing, print a message indicate
		 * which rotation operation and which node. 
		 * */
		AVLTree<Integer> avl = new AVLTree<>();
		
		for (int i = 0; i < 35; i++) {
			avl.insert(rand.nextInt(89) + 10);
			
			if (!avl.checkBalance()) {
				avl.getBalance(avl.getRoot());
			}
			
			new TreePrinter(avl).print("***AVL Tree: After Insertion & Balancing***\n\n");
		}
		
		while (!avl.isEmpty()) {
			avl.remove(avl.getRoot().getData());
			
			if (!avl.checkBalance()) {
				avl.getBalance(avl.getRoot());
			}
			
			new TreePrinter(avl).print("***AVL Tree: After Deletion & Balancing***\n\n");
		}
		
		
		System.out.println("\n\nPart 2: Timing AVL and BST: Insertion\n");
		
		Random r = new Random();
		int[] randomInts = new int[100000];
		for (int i = 0; i < 100000; i++) {
			int random = r.nextInt();
			randomInts[i] = random;
		}
		
		long bstartTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			bst.insert(randomInts[i]);

		}
		long bendTime = System.nanoTime();
		
		long astartTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			bst.insert(randomInts[i]);

		}
		long aendTime = System.nanoTime();
		
		System.out.println("BST insertion time for same 100000 numbers: " + (bendTime - bstartTime) + " ns");
		System.out.println("AVL insertion time for same 100000 numbers: " + (aendTime - astartTime) + " ns");
		
		
	}
}
