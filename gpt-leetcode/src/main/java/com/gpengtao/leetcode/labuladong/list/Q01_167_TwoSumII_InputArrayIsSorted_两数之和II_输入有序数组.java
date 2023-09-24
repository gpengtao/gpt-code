package com.gpengtao.leetcode.labuladong.list;

/**
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">...</a>
 *
 * @author pengtao.geng on 2023/9/21 20:55.
 */
public class Q01_167_TwoSumII_InputArrayIsSorted_两数之和II_输入有序数组 {

	/**
	 * 解法1，两层for循环，暴力求解
	 */
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			// 注意j从i+1开始
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == target) {
					result[0] = i + 1;
					result[1] = j + 1;
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * 解法2，双指针（leetcode评论区 omega 的杰作，碉堡了
	 */
	public int[] twoSum2(int[] numbers, int target) {
		for (int i = 0, j = numbers.length - 1; i < j; ) {
			int sum = numbers[i] + numbers[j];
			if (sum == target) {
				return new int[]{i + 1, j + 1};
			} else if (sum > target) {
				j--;
			} else {
				i++;
			}
		}
		return null;
	}
}