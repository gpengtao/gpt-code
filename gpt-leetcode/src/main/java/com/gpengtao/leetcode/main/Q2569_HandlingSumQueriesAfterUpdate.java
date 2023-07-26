package com.gpengtao.leetcode.main;

import java.util.Arrays;

/**
 * 更新数组后处理求和查询
 * <p>
 * 给你两个下标从 0 开始的数组 nums1 和 nums2 ，和一个二维数组 queries 表示一些操作。总共有 3 种类型的操作：
 * <p>
 * 操作类型 1 为 queries[i] = [1, l, r] 。你需要将 nums1 从下标 l 到下标 r 的所有 0 反转成 1 或将 1 反转成 0 。l 和 r 下标都从 0 开始。
 * 操作类型 2 为 queries[i] = [2, p, 0] 。对于 0 <= i < n 中的所有下标，令 nums2[i] = nums2[i] + nums1[i] * p 。
 * 操作类型 3 为 queries[i] = [3, 0, 0] 。求 nums2 中所有元素的和。
 * 请你返回一个数组，包含所有第三种操作类型的答案。
 * <a href="https://leetcode.cn/problems/handling-sum-queries-after-update/description/">...</a>
 *
 * @author pengtao.geng on 2023/7/26 20:07.
 */
public class Q2569_HandlingSumQueriesAfterUpdate {

	public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
		int size = 0;
		for (int[] query : queries) {
			if (query[0] == 3) {
				size++;
			}
		}
		long[] result = new long[size];
		int current = 0;

		for (int[] query : queries) {
			if (query[0] == 1) {
				doOperate1(nums1, query[1], query[2]);
			}
			if (query[0] == 2) {
				doOperate2(nums1, nums2, query[1]);
			}
			if (query[0] == 3) {
				long sum = 0;
				for (int num : nums2) {
					sum += num;
				}
				result[current++] = sum;
			}
		}
		return result;
	}

	private void doOperate2(int[] nums1, int[] nums2, int p) {
		for (int i = 0; i < nums2.length; i++) {
			nums2[i] = nums2[i] + nums1[i] * p;
		}
	}

	private void doOperate1(int[] nums1, int left, int right) {
		for (int i = left; i <= right; i++) {
			if (nums1[i] == 1) {
				nums1[i] = 0;
			} else {
				nums1[i] = 1;
			}
		}
	}

	public static void main(String[] args) {
		test(new int[]{1, 0, 1}, new int[]{0, 0, 0}, new int[][]{{1, 1, 1}, {2, 1, 0}, {3, 0, 0}});
		test(new int[]{1}, new int[]{5}, new int[][]{{2, 0, 0}, {3, 0, 0}});
	}

	private static void test(int[] num1, int[] num2, int[][] queries) {
		System.out.println(Arrays.toString(new Q2569_HandlingSumQueriesAfterUpdate().handleQuery(num1, num2, queries)));
	}
}
