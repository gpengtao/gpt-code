package com.gpengtao.leetcode.base.tree;

/**
 * @author pengtao.geng on 2023/9/12 20:40.
 */
public class BinaryTree {

	private Node root;

	public void add(int val) {
		root = addRecursive(root, val);
	}

	private Node addRecursive(Node current, int val) {
		if (current == null) {
			return new Node(val);
		}
		if (val < current.getValue()) {
			Node left = addRecursive(current.getLeft(), val);
			current.setLeft(left);
		} else if (val > current.getValue()) {
			Node right = addRecursive(current.getRight(), val);
			current.setRight(right);
		} else {
			return current;
		}
		return current;
	}
}
