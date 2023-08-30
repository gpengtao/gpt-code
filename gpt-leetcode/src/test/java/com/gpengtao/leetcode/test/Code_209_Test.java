package com.gpengtao.leetcode.test;

import com.gpengtao.leetcode.code.Code_209_MinimumSizeSubarraySum.Solution;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author pengtao.geng on 2020/1/31 3:50 下午
 */
public class Code_209_Test {

	@Test
	public void test_1() {
		// 普通case
		int[] array = new int[]{2, 3, 1, 2, 4, 3};
		int s = 7;

		int[] minArray = new Solution().minSubArrayLen(array, s);
		System.out.println(Arrays.toString(minArray));
	}

	@Test
	public void test_2() {
		// 空数组
		int[] array = new int[]{};
		int s = 7;

		int[] minArray = new Solution().minSubArrayLen(array, s);
		System.out.println(Arrays.toString(minArray));
	}

	@Test
	public void test_3() {
		// 全部之和也不满足
		int[] array = new int[]{2, 3, 1, 2, 4, 3};
		int s = 70;

		int[] minArray = new Solution().minSubArrayLen(array, s);
		System.out.println(Arrays.toString(minArray));
	}

	@Test
	public void test_4() {
		// 题目中没说这个情况，应该抱有疑问
		int[] array = new int[]{2, 3, 1, 2, 4, 3};
		int s = 0;

		int[] minArray = new Solution().minSubArrayLen(array, s);
		System.out.println(Arrays.toString(minArray));
	}
}
