package com.gpengtao.leetcode.main;

import lombok.Data;

/**
 * @author pengtao.geng on 2023/7/11 20:00.
 */
public class Q0101_SymmetricTree {

	@Data
	static class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public boolean isSymmetric(TreeNode root) {
		return true;
	}

	public static void main(String[] args) {
		test1();
		test2();
	}

	/*
	树：
		1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	*/
	private static void test1() {
		TreeNode nodeLeft3 = new TreeNode(3, null, null);
		TreeNode nodeLeft4 = new TreeNode(4, null, null);

		TreeNode nodeRight3 = new TreeNode(3, null, null);
		TreeNode nodeRight4 = new TreeNode(4, null, null);

		TreeNode nodeLeft2 = new TreeNode(2, nodeLeft3, nodeLeft4);
		TreeNode nodeRight2 = new TreeNode(2, nodeRight4, nodeRight3);

		TreeNode root = new TreeNode(1, nodeLeft2, nodeRight2);

		boolean b = new Q0101_SymmetricTree().isSymmetric(root);
		System.out.println(b);
	}

	/*
	树：
		1
	   / \
	  2   2
	  \    \
	   3    3
	*/
	private static void test2() {
		TreeNode nodeLeft3 = new TreeNode(3, null, null);
		TreeNode nodeRight3 = new TreeNode(3, null, null);

		TreeNode nodeLeft2 = new TreeNode(2, null, nodeLeft3);
		TreeNode nodeRight2 = new TreeNode(2, null, nodeRight3);

		TreeNode root = new TreeNode(1, nodeLeft2, nodeRight2);

		boolean b = new Q0101_SymmetricTree().isSymmetric(root);
		System.out.println(b);
	}
}
