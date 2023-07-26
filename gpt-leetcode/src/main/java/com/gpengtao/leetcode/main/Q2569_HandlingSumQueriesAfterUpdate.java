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
		// 构造long类型的数组2，防止num2数值溢出
		long[] nums2222 = new long[nums2.length];
		for (int i = 0; i < nums2.length; i++) {
			nums2222[i] = nums2[i];
		}

		// 初始化结果数组
		int size = 0;
		for (int[] query : queries) {
			if (query[0] == 3) {
				size++;
			}
		}
		long[] result = new long[size];
		int current = 0;

		// 处理
		for (int[] query : queries) {
			// 操作1
			if (query[0] == 1) {
				for (int i = query[1]; i <= query[2]; i++) {
					if (nums1[i] == 1) {
						nums1[i] = 0;
					} else {
						nums1[i] = 1;
					}
				}
			}
			// 操作2
			else if (query[0] == 2) {
				for (int i = 0; i < nums2222.length; i++) {
					nums2222[i] = nums2222[i] + (long) nums1[i] * query[1];
				}
			}
			// 操作3
			else if (query[0] == 3) {
				long sum = 0;
				for (long num : nums2222) {
					sum += num;
				}
				result[current++] = sum;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		test(new int[]{1, 0, 1}, new int[]{0, 0, 0}, new int[][]{{1, 1, 1}, {2, 1, 0}, {3, 0, 0}});
		test(new int[]{1}, new int[]{5}, new int[][]{{2, 0, 0}, {3, 0, 0}});
	}

	private static void test(int[] num1, int[] num2, int[][] queries) {
		System.out.println(Arrays.toString(new Q2569_HandlingSumQueriesAfterUpdate().handleQuery(num1, num2, queries)));
	}
}
