package algorithms.search.treesearch;

public class AVLNode<AnyType> {
	public AnyType element;
	public AVLNode<AnyType> left;
	public AVLNode<AnyType> right;
	public int height;
	
	public AVLNode(AnyType element){
		this.element = element;
		left = null;
		right = null;
		height = 0;
	}
}
