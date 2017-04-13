package Utils;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(AnyType element){
		return contains(element, root);
	}
	
	public BinaryNode<AnyType> findMin(){
		return findMin(root);
	}
	
	public BinaryNode<AnyType> findMax(){
		return findMax(root);
	}
	
	public void insert(AnyType element){
		root = insert(element, root);
	}
	
	public void remove(AnyType element){
		root = remove(element, root);
	}
	
	/**
	 * 删除元素。
	 * @param element 待删除元素。
	 * @param root 根节点。
	 * @return 删除元素后的树的根节点。
	 */
	private BinaryNode<AnyType> remove(AnyType element, BinaryNode<AnyType> root){
		if(root == null){
			return null;
		}
		
		int compareResult = element.compareTo(root.element);
		
		if(compareResult < 0){
			root.left = remove(element, root.left);
		}else if(compareResult > 0){
			root.right = remove(element, root.right);
		}else if(root.left != null && root.right != null){
			//待删节点有两个孩子。
			root.element = findMin(root.right).element;
			root.right = remove(root.element, root.right);
		}else{
			//待删节点有一个孩子或没有孩子。
			//Genius.
			root = (root.left != null) ? root.left : root.right;
		}
		return root;
	}
	
	/**
	 * 插入元素。
	 * @param element 待插入的元素。
	 * @param root 根节点。
	 * @return 根节点。
	 */
	private BinaryNode<AnyType> insert(AnyType element, BinaryNode<AnyType> root){
		if(root == null){
			return new BinaryNode(element);
		}
		
		int compareResult = element.compareTo(root.element);
		
		if(compareResult < 0){
			root.left = insert(element, root.left);
		}else if(compareResult > 0){
			root.right = insert(element, root.right);
		}else{
			//Duplicated.
		}
		return root;
	}
	
	/**
	 * 查找最小值递归实现。
	 * @param root 根节点。
	 * @return 最小值节点。
	 */
	private BinaryNode<AnyType> findMinRecursively(BinaryNode<AnyType> root){
		if(root == null){
			return null;
		}else if(root.left == null){
			return root;
		}
		return findMinRecursively(root.left);
	}
	
	/**
	 * 查找最小值非递归实现。
	 * @param root 根节点。
	 * @return 最小值节点。
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root){
		while(root != null && root.left != null){
			root = root.left;
		}
		return root;
	}
	
	/**
	 * 查找最大值递归实现。
	 * @param root 根节点。
	 * @return 最大值节点。
	 */
	private BinaryNode<AnyType> findMaxRecursively(BinaryNode<AnyType> root){
		if(root == null){
			return null;
		}else if(root.right == null){
			return root;
		}
		return findMaxRecursively(root.right);
	}
	
	/**
	 * 查找最大值非递归实现。
	 * @param root 根节点。
	 * @return 最大值节点。
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root){
		while(root != null && root.right != null){
			root = root.right;
		}
		return root;
	}
	
	/**
	 * 递归查找。
	 * @param element 待查元素。
	 * @param root 查找树根节点。
	 * @return 是否存在元素。
	 */
	private boolean contains(AnyType element, BinaryNode<AnyType> root){
		if(root == null){
			return false;
		}
		
		int compareResult = element.compareTo(root.element);
		
		if(compareResult < 0){
			return contains(element, root.left);
		}else if(compareResult > 0){
			return contains(element, root.right);
		}else{
			return true;
		}
	}
	
}
