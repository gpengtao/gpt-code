package com.gpengtao.leetcode;

import java.util.Arrays;

/**
 * @author pengtao.geng on 2019-06-27 01:56
 */
public class Q01TwoSum {

	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Q01TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9)));
		System.out.println(Arrays.toString(new Q01TwoSum().twoSum(new int[]{1, 2, 9, 5, 4}, 6)));
	}
}
