package algorithms.search.treesearch;

public class AVLTree<AnyType extends Comparable<? super AnyType>> {
	public AVLNode<AnyType> root;
	private static final int IMBALANCE_LIMIT = 1;
	
	public AVLNode<AnyType> insert(AnyType element){
		return insert(element, root);
	}
	
	private AVLNode<AnyType> insert(AnyType element, AVLNode<AnyType> root){
		if(root == null) return new AVLNode<AnyType>(element);
		
		int compareResult = element.compareTo(root.element);
		if(compareResult < 0){
			root.left = insert(element, root.left);
		}else if(compareResult > 0){
			root.right = insert(element, root.right);
		}else{
			//duplicated
		}
		
		return balance(root);
	}
	
	private AVLNode<AnyType> remove(AnyType element, AVLNode<AnyType> root){
		if(root == null) return root;
		int compareResult = element.compareTo(root.element);
		if(compareResult < 0){
			root.left = remove(element, root.left);
		}else if(compareResult > 0){
			root.right = remove(element, root.right);
		}else if(root.left != null && root.right != null){
			root.element = findMin(root.right).element;
			root.right = remove(root.element, root.right);
		}
		
		return balance(root);
	}
	
	private AVLNode<AnyType> findMin(AVLNode<AnyType> root){
		while(root != null && root.left != null){
			root = root.left;
		}
		return root;
	}
	
	private AVLNode<AnyType> balance(AVLNode<AnyType> root){
		if(root == null) return root;
		
		if(height(root.left) - height(root.right) > IMBALANCE_LIMIT){
			if(height(root.left.left) - height(root.left.right) >= 0){
				rotateWithLeftChild(root);
			}else{
				doubleRotateWithLeftChild(root);
			}
		}else if(height(root.right) - height(root.left) > IMBALANCE_LIMIT){
			if(height(root.right.right) - height(root.right.left) >= 0){
				rotateWithRightChild(root);
			}else{
				doubleRotateWithRightChild(root);
			}
		}
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	private AVLNode<AnyType> rotateWithLeftChild(AVLNode<AnyType> root){
		AVLNode<AnyType> newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		return newRoot;
	}
	
	private AVLNode<AnyType> rotateWithRightChild(AVLNode<AnyType> root){
		AVLNode<AnyType> newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;
		
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		return newRoot;
	}
	
	private AVLNode<AnyType> doubleRotateWithLeftChild(AVLNode<AnyType> root){
		root.left = rotateWithRightChild(root.left);
		return rotateWithLeftChild(root);
	}
	
	private AVLNode<AnyType> doubleRotateWithRightChild(AVLNode<AnyType> root){
		root.right = rotateWithLeftChild(root.right);
		return rotateWithRightChild(root);
	}
	
	private int height(AVLNode<AnyType> root){
		return (root == null) ? -1 : root.height;
	}
}
