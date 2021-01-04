import java.lang.Math;

public class AVLTree<AnyType extends Comparable<AnyType>> extends BinarySearchTree<AnyType> {
	
	public AVLTree() {
		super();
	}
	
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	public void insert(AnyType data) {
		super.setRoot(insert(data, super.getRoot()));
	}
	
	@Override
	protected BinaryNode<AnyType> insert(AnyType data, BinaryNode<AnyType> root) {
		return balance(super.insert(data, root));
	}
	
	public void remove(AnyType data) {
		super.setRoot(remove(data, super.getRoot()));
	}
	
	protected BinaryNode<AnyType> remove(BinaryNode<AnyType> root, AnyType x) {
		return balance(super.remove(x, root));
	}
	
	public BinaryNode<AnyType> getBalance(BinaryNode<AnyType> root) {
		return balance(root);
	}
	
	protected BinaryNode<AnyType> balance(BinaryNode<AnyType> root) {
		if (height(root.getLeft()) - height(root.getRight()) > 1) {
			if (height(root.getLeft().getLeft()) >= height(root.getLeft().getRight())) {
				root = singleRightRotation(root);
				System.out.println("\nSingle Right Rotation: " + root.getData() + "\n");
			} else {
				root = doubleLeftRightRotation(root);
				System.out.println("\nDouble Left-Right Rotation: " + root.getData() + "\n");

			}
		} else if (height(root.getRight()) - height(root.getLeft()) > 1) {
			if (height(root.getRight().getRight()) >= height(root.getRight().getLeft())) {
				root = singleLeftRotation(root); 
				System.out.println("\nSingle Left Rotation: " + root.getData() + "\n");
			} else {
				root = doubleRightLeftRotation(root);
				System.out.println("\nDouble Left-Right Rotation: " + root.getData() + "\n");
			}
		}
		
		return root;
	}


	//Case 1
	private BinaryNode<AnyType> singleRightRotation (BinaryNode<AnyType> k2) {
		BinaryNode<AnyType> k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(1 + Math.max(height(k2.getLeft()), height(k2.getRight())));
		k1.setHeight(1 + Math.max(height(k1.getLeft()), k2.getHeight()));
		
		return k1;
	}
	
	//Case 4
	private BinaryNode<AnyType> singleLeftRotation(BinaryNode<AnyType> k1) {
		BinaryNode<AnyType> k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(1 + Math.max(height(k1.getLeft()), height(k1.getRight())));
		k2.setHeight(1 + Math.max(height(k2.getRight()), k1.getHeight()));
		
		return k2;
	}
	
	//Case 2
	private BinaryNode<AnyType> doubleLeftRightRotation(BinaryNode<AnyType> k3) {
		k3.setLeft(singleLeftRotation(k3.getLeft()));
		return singleRightRotation(k3);
	}
	
	//Case 3
	private BinaryNode<AnyType> doubleRightLeftRotation(BinaryNode<AnyType> k1) {
		k1.setRight(singleRightRotation(k1.getRight()));
		return singleLeftRotation(k1);
	}
	
	
	public boolean checkBalance()  {
		if (super.getRoot() == null)
			return true;
		else {
			int leftHeight = height(super.getRoot().getLeft());
			int rightHeight = height(super.getRoot().getRight());
			return Math.abs(leftHeight - rightHeight) <= 1;
		}
	}

}