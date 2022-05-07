package com.gpengtao.leetcode.test;

import Code_101_SymmetricTree.Solution;
import Code_101_SymmetricTree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author pengtao.geng on 2020/1/31 5:01 下午
 */
public class Code_101_Test {

	/*
	树：
		1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	*/
	@Test
	public void test_1() {
		TreeNode nodeLeft3 = new TreeNode(3, null, null);
		TreeNode nodeLeft4 = new TreeNode(4, null, null);

		TreeNode nodeRight3 = new TreeNode(3, null, null);
		TreeNode nodeRight4 = new TreeNode(4, null, null);

		TreeNode nodeLeft2 = new TreeNode(2, nodeLeft3, nodeLeft4);
		TreeNode nodeRight2 = new TreeNode(2, nodeRight4, nodeRight3);

		TreeNode root = new TreeNode(1, nodeLeft2, nodeRight2);

		boolean b = new Solution().isSymmetric(root);
		Assert.assertTrue(b);
	}

	/*
	树：
		1
	   / \
	  2   2
	  \    \
	   3    3
	*/
	@Test
	public void test_2(){
		TreeNode nodeLeft3 = new TreeNode(3, null, null);
		TreeNode nodeRight3 = new TreeNode(3, null, null);

		TreeNode nodeLeft2 = new TreeNode(2, null, nodeLeft3);
		TreeNode nodeRight2 = new TreeNode(2, null, nodeRight3);

		TreeNode root = new TreeNode(1, nodeLeft2, nodeRight2);

		boolean b = new Solution().isSymmetric(root);
		Assert.assertFalse(b);
	}
}
