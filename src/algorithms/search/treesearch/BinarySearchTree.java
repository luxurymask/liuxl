package algorithms.search.treesearch;

import java.util.Deque;
import java.util.LinkedList;

import Tree.BinaryNode;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public BinaryNode<AnyType> insert(AnyType element){
		return insert(element, root);
	}
	
	public BinaryNode<AnyType> remove(AnyType element){
		return remove(element, root);
	}
	
	public boolean contains(AnyType element){
		return contains(element, root);
	}
	
	public boolean isBinarySearchTree(){
		return isBinarySearchTree(root);
	}
	
	private boolean isBinarySearchTree(BinaryNode<AnyType> root){
		Deque<BinaryNode<AnyType>> stack = new LinkedList<BinaryNode<AnyType>>();
		BinaryNode<AnyType> current = root;
		BinaryNode<AnyType> pre = null;
		while(current != null || !stack.isEmpty()){
			while(current != null){
				stack.push(current);
				current = root.left;
			}
		
			if(!stack.isEmpty()){
				current = stack.pop();
				if(pre != null && pre.element.compareTo(current.element) >= 0) return false;
				pre = current;
				current = current.right;
			}
		}
		return true;
	}
	
	private boolean contains(AnyType element, BinaryNode<AnyType> root){
		if(root == null) return false;
		
		int compareResult = element.compareTo(root.element);
		if(compareResult < 0){
			return contains(element, root.left);
		}else if(compareResult > 0){
			return contains(element, root.right);
		}else{
			return true;
		}
	}
	
	private BinaryNode<AnyType> remove(AnyType element, BinaryNode<AnyType> root){
		if(root == null) return null;
		
		int compareResult = element.compareTo(root.element);
		if(compareResult < 0){
			root.left = remove(element, root.left);
		}else if(compareResult > 0){
			root.right = remove(element, root.right);
		}else if(root.left != null && root.right != null){
			root.element = findMin(root.right).element;
			root.right = remove(root.element, root.right);
		}else{
			root = (root.left != null) ? root.left : root.right;
		}
		
		return root;
	}
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root){
		while(root != null && root.left != null){
			root = root.left;
		}
		return root;
	}
	
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root){
		while(root != null && root.right != null){
			root = root.right;
		}
		return root;
	}
	
	private BinaryNode<AnyType> findMinRecursively(BinaryNode<AnyType> root){
		if(root == null || root.left == null) return root;
		return findMinRecursively(root.left);
	}
	
	private BinaryNode<AnyType> findMaxRecursively(BinaryNode<AnyType> root){
		if(root == null || root.right == null) return root;
		return findMaxRecursively(root.right);
	}
	
	private BinaryNode<AnyType> insert(AnyType element, BinaryNode<AnyType> root){
		if(root == null) return new BinaryNode<>(element);
		
		int compareResult = element.compareTo(root.element);
		if(compareResult < 0){
			root.left = insert(element, root.left);
		}else if(compareResult > 0){
			root.right = insert(element, root.right);
		}else{
			//Duplicated
		}
		
		return root;
	}
}
