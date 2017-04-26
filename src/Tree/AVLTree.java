package Tree;

public class AVLTree<AnyType extends Comparable<? super AnyType>> extends BinarySearchTree<AnyType>{
	public AVLNode<AnyType> root;
	private static final int IMBALANCE_LIMIT = 1;
	
	private int height(AVLNode<AnyType> node){
		return node == null ? -1 : node.height;
	}
	
	private AVLNode<AnyType> remove(AVLNode<AnyType> root, AnyType element){
		if(root == null){
			return null;
		}
		
		int compareResult = element.compareTo(root.element);
		
		if(compareResult < 0){
			root.left = remove(root.left, element);
		}else if(compareResult > 0){
			root.right = remove(root.right, element);
		}else if(root.left != null && root.right != null){
			root.element = findMin(root.right).element;
			root.right = remove(root.right, root.element);
		}else{
			root = (root.left == null) ? root.right : root.left;
		}
		
		return balance(root);
	}
	
	private AVLNode<AnyType> insert(AVLNode<AnyType> root, AnyType element){
		if(root == null){
			return new AVLNode<AnyType>(element);
		}
		
		int compareResult = element.compareTo(root.element);
		
		if(compareResult < 0){
			root.left = insert(root.left, element);
		}else if(compareResult > 0){
			root.right = insert(root.right, element);
		}else{
			//Duplicated.
		}
		
		return balance(root);
	}
	
	private AVLNode<AnyType> balance(AVLNode<AnyType> root){
		if(root == null) return root;
		
		if(height(root.left) - height(root.right) > IMBALANCE_LIMIT){
			//删除节点后调整时以及root.left.left是叶子节点时会产生相等的情形。
			if(root.left.left.height >= root.left.right.height){
				root = singleRotate(root, 0);
			}else{
				root = doubleRotate(root, 0);
			}
		}else if(height(root.right) - height(root.left) > IMBALANCE_LIMIT){
			//删除节点后调整时以及root.left.left是叶子节点时会产生相等的情形。
			if(root.right.right.height >= root.right.left.height){
				root = singleRotate(root, 1);
			}else{
				root = doubleRotate(root, 1);
			}
		}
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	/**
	 * 单旋转。
	 * @param node1 原子树根节点。
	 * @param withWhat 旋转利用的子节点的方向。[0]：用左侧节点旋转，[1]：用右侧节点旋转。
	 * @return 新平衡子树根节点。
	 */
	private AVLNode<AnyType> singleRotate(AVLNode<AnyType> node1, int withWhat){
		AVLNode<AnyType> node2 = null;
		if(withWhat == 0){
			node2 = node1.left;
			node1.left = node2.right;
			node2.right = node1;
		}else if(withWhat == 1){
			node2 = node1.right;
			node1.right = node2.left;
			node2.left = node1;
		}
		node1.height = Math.max(height(node1.left), height(node1.right)) + 1;
		node2.height = Math.max(height(node2.left), height(node2.right)) + 1;
		return node2;
	}
	
	/**
	 * 双旋转。
	 * @param node1 原子树根节点。
	 * @param withWhat 旋转利用的子节点的方向。[0]：用左侧节点旋转，[1]：用右侧节点旋转。
	 * @return 新平衡子树根节点。
	 */
	private AVLNode<AnyType> doubleRotate(AVLNode<AnyType> node1, int withWhat){
		if(withWhat == 0){
			node1.left = singleRotate(node1.left, 1);
			node1 = singleRotate(node1, 0);
		}else if(withWhat == 1){
			node1.right = singleRotate(node1.right, 0);
			node1 = singleRotate(node1, 1);
		}
		
		return node1;
	}
}
