package Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<AnyType> {
	public BinaryNode<AnyType> root;
	
	class TreeIterator implements Iterator<BinaryNode<AnyType>>{
		final List<BinaryNode<AnyType>> nodeList = new ArrayList<BinaryNode<AnyType>>();
		BinaryNode<AnyType> current;
		BinaryNode<AnyType> next;
		int index;
		
		static final int PRE_ORDER = 0;
		static final int INFIX_ORDER = 1;
		static final int POST_ORDER = 2;
		static final int BREADTH_FIRST = 3;
		
		public TreeIterator(int traverseWay){
			switch(traverseWay){
			case PRE_ORDER:
				preOrderTraverseNonrecursively(root, nodeList);
				break;
			case INFIX_ORDER:
				infixOrderTraverseNonrecursively(root, nodeList);
				break;
			case POST_ORDER:
				postOrderTraverseRecursively(root, nodeList);
				break;
			case BREADTH_FIRST:
				breadthFirstTraverse(root, nodeList);
				break;
			}
			current = null;
			index = 0;
			next = nodeList.isEmpty() ? null : nodeList.get(index);
		}
		
		@Override
		public final boolean hasNext() {
			return next != null;
		}

		@Override
		public BinaryNode<AnyType> next() {
			current = nodeList.get(index++);
			if(index != nodeList.size()){
				next = nodeList.get(index);
			}else{
				next = null;
			}
			return current;
		}
		
	}
	
	/**
	 * 获取默认迭代器（先序）。
	 * @return
	 */
	public Iterator<BinaryNode<AnyType>> iterator(){
		return new TreeIterator(0);
	}
	
	/**
	 * 获得迭代器。
	 * @param traverseWay 迭代方式：[0]先序遍历，[1]中序遍历，[2]后续遍历，[3]深度优先遍历，[4]广度优先遍历。
	 * @return 迭代器对象。
	 */
	public Iterator<BinaryNode<AnyType>> iterator(int traverseWay){
		return new TreeIterator(traverseWay);
	}
	public BinaryTree(BinaryNode<AnyType> root){
		this.root = root;
	}
	
	public BinaryTree(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void destroy(){
		root = null;
	}
	
	/**
	 * 获得二叉树深度。
	 * @return
	 */
	public int getDepth(){
		return getDepth(root);
	}
	
	/**
	 * 递归获得二叉树深度。
	 * @param root
	 * @return
	 */
	public int getDepth(BinaryNode<AnyType> root){
		if(root == null){
			return 0;
		}
		int depthLeft = getDepth(root.left);
		int depthRight = getDepth(root.right);
		return Math.max(depthLeft, depthRight) + 1;
	}
	
	/**
	 * 非递归先序遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void preOrderTraverseNonrecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		Deque<BinaryNode<AnyType>> stack = new LinkedList<BinaryNode<AnyType>>();
		stack.push(root);
		BinaryNode<AnyType> current = null;
		while(!stack.isEmpty()){
			current = stack.pop();
			list.add(current);
			if(current.right != null) stack.push(current.right);
			if(current.left != null) stack.push(current.left);
		}
	}
	
	/**
	 * 非递归中序遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void infixOrderTraverseNonrecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		Deque<BinaryNode<AnyType>> stack = new LinkedList<BinaryNode<AnyType>>();
		BinaryNode<AnyType> current = root;
		while(current != null || !stack.isEmpty()){
			//走到最左边儿，路径入栈
			while(current != null){
				stack.push(current);
				current = current.left;
			}
			if(!stack.isEmpty()){
				//最左节点出栈
				current = stack.pop();
				//访问
				list.add(current);
				//往右走一步
				current = current.right;
			}
		}
	}
	
	/**
	 * 非递归后续遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void postOrderTraverseNonecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		Deque<BinaryNode<AnyType>> stack = new LinkedList<BinaryNode<AnyType>>();
		BinaryNode<AnyType> current = root;
		BinaryNode<AnyType> lastAccess = null;
		while(current != null || !stack.isEmpty()){
			while(current != null){
				stack.push(current);
				current = current.left;
			}
			//栈里取一个节点（一定是当前节点的父节点）。
			current = stack.pop();
			
			//只有当当前节点没有右子树或者右子树都被访问过了才能访问当前节点。
			while(current != null && (current.right == null || current.right == lastAccess)){
				list.add(current);//访问当前节点。
				lastAccess = current;//记录上一次访问的节点。
				//树根节点出栈后栈会为空，return。
				if(stack.isEmpty()){
					return;
				}
				current = stack.pop();//回溯到父节点准备check。
			}
			//经典的重新入栈（第一次循环不是）。
			stack.push(current);
			current = current.right;//往右走一步（然后后面会继续一直往左走）。
		}
	}
	
	/**
	 * 递归先序遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void preOrderTraverseRecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		list.add(root);
		if(root.left != null) preOrderTraverseRecursively(root.left, list);
		if(root.right != null) preOrderTraverseRecursively(root.right, list);
	}
	
	/**
	 * 递归中序遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void infixOrderTraverseRecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		if(root.left != null) infixOrderTraverseRecursively(root.left, list);
		list.add(root);
		if(root.right != null) infixOrderTraverseRecursively(root.right, list);
	}
	
	/**
	 * 递归后序遍历。
	 * @param root 根节点。
	 * @param list 持有节点对象的List。
	 */
	public void postOrderTraverseRecursively(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		if(root.left != null) postOrderTraverseRecursively(root.left, list);
		if(root.right != null) postOrderTraverseRecursively(root.right, list);
		list.add(root);
	}
	
	/**
	 * 队列实现层次遍历。
	 * @param root
	 * @param list
	 */
	public void breadthFirstTraverse(BinaryNode<AnyType> root, List<BinaryNode<AnyType>> list){
		Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryNode<AnyType> node = queue.poll();
			list.add(node);
			if(node.left != null) queue.add(node.left);
			if(node.right != null) queue.add(node.right);
		}
	}
	
	public static void main(String[] args){
		BinaryNode<Integer> left = new BinaryNode<Integer>(4);
		BinaryNode<Integer> right = new BinaryNode<Integer>(5);
		BinaryNode<Integer> root = new BinaryNode<Integer>(3, left, right);
		BinaryTree<Integer> tree = new BinaryTree<Integer>(root);
		Iterator<BinaryNode<Integer>> preIterator = tree.iterator();
		Iterator<BinaryNode<Integer>> infixIterator = tree.iterator(1);
		Iterator<BinaryNode<Integer>> postIterator = tree.iterator(2);
		Iterator<BinaryNode<Integer>> breadthIterator = tree.iterator(3);
		while(preIterator.hasNext()){
			System.out.println(preIterator.next().element);
		}
		while(infixIterator.hasNext()){
			System.out.println(infixIterator.next().element);
		}
		while(postIterator.hasNext()){
			System.out.println(postIterator.next().element);
		}
		System.out.println(tree.getDepth());
	}
}
