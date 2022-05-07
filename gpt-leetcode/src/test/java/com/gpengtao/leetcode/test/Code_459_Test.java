package com.gpengtao.leetcode.test;

import Code_459_RepeatedSubstringPattern.Solution;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author pengtao.geng on 2020/1/31 4:10 下午
 */
public class Code_459_Test {

	@Test
	public void test_1() {
		boolean b = new Solution().repeatedSubstringPattern("");
		Assert.assertFalse(b);
	}

	@Test
	public void test_2() {
		boolean b = new Solution().repeatedSubstringPattern("a");
		Assert.assertFalse(b);
	}

	@Test
	public void test_3() {
		boolean b = new Solution().repeatedSubstringPattern("ab");
		Assert.assertFalse(b);
	}


	@Test
	public void test_4() {
		boolean b = new Solution().repeatedSubstringPattern("abab");
		Assert.assertTrue(b);
	}


	@Test
	public void test_5() {
		boolean b = new Solution().repeatedSubstringPattern("abcabcabc");
		Assert.assertTrue(b);
	}
}
