public class BinaryNode<AnyType> {
	private AnyType data;				// data in the node
	private int height; 				// height of this node 
	private BinaryNode<AnyType> left; 	// left child
	private BinaryNode<AnyType> right;  // right child
	
	// Constructors
	BinaryNode(AnyType theData) {
		this(theData, null, null);
	}
	
	BinaryNode(AnyType theData, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
		this.data = theData;
		this.left = lt;
		this.right = rt;
		this.height = 0;
	}

	public AnyType getData() {
		return data;
	}

	public void setData(AnyType theData) {
		this.data = theData;
	}

	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getHeight() {
		return height;
	}

	public BinaryNode<AnyType> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<AnyType> left) {
		this.left = left;
	}

	public BinaryNode<AnyType> getRight() {
		return right;
	}

	public void setRight(BinaryNode<AnyType> right) {
		this.right = right;
	}

}
