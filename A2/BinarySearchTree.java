public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public BinaryNode<AnyType> getRoot() {
		return root;
	}
	
	public void setRoot(BinaryNode<AnyType> root) {
		this.root = root;
	}
	
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root) {
		if (root == null) 
			return null;
		else if (root.getLeft() == null)
			return root;
		else
			return findMin(root.getLeft());
	}
	
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root) {
		if (root != null) {
			while (root.getRight() != null) {
				root = root.getRight();
			}
		}
		return root;
	}
	
	public boolean contains(AnyType x, BinaryNode<AnyType> root) {
		if (root == null) 
			return false;
		
		int comp = x.compareTo(root.getData());
		
		if (comp < 0) 
			return contains(x, root.getLeft());
		else if (comp > 0) 
			return contains(x, root.getRight());
		else 
			return true; // Match
	}
	
	public void insert(AnyType x) {
		root = insert(x, root);
	}
		
	protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> root) {
		if (root == null)
			return new BinaryNode<>(x, null, null);
		
		int comp = x.compareTo(root.getData());
		
		// Find the insertion point.
		if (comp < 0) 
			root.setLeft(insert(x, root.getLeft()));
		else if (comp > 0)
			root.setRight(insert(x, root.getRight()));
		else {
			// Duplicate: do nothing.
		}
		
		return root;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	public void remove(AnyType x) {
		root = remove(x, root);
	}
	
	protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> root) {
		if (root == null)
			return root;
		
		int comp = x.compareTo(root.getData());
		
		if (comp < 0)
			root.setLeft(remove(x, root.getLeft()));
		
		else if (comp > 0)
			root.setRight(remove(x, root.getRight()));
		
		else if (root.getLeft() != null && root.getRight() != null) {
			root.setData(findMin(root.getRight()).getData());
			root.setRight(remove(root.getData(), root.getRight()));
		} 
		
		else {
			root = (root.getLeft() != null) ? root.getLeft() : root.getRight();
		}
		
		return root;
	}
	
	public int height() {
		if (root == null)
			return 0;

		return height(root);
	}
	
	protected int height(BinaryNode<AnyType> root) {
		if (root == null)
			return -1;
		else 
			return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
	}
	
}