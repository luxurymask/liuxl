package Tree;

public class AVLNode<AnyType> extends BinaryNode{
	AnyType element;
	AVLNode<AnyType> left;
	AVLNode<AnyType> right;
	int height;
	
	AVLNode(AnyType element){
		this(element, null, null);
	}
	
	AVLNode(AnyType element, AVLNode<AnyType> left, AVLNode<AnyType> right){
		super(element, left, right);
		height = 0;
	}
}
