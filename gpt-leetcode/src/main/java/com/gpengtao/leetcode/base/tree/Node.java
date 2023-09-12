package com.gpengtao.leetcode.base.tree;

import lombok.Data;

/**
 * @author pengtao.geng on 2023/9/12 20:27.
 */
@Data
public class Node {
	private int value;
	private Node left;
	private Node right;

	public Node(int value) {
		this.value = value;
	}

	public Node(int value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
