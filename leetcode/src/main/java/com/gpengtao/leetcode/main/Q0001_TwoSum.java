package com.gpengtao.leetcode.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengtao.geng on 2019-06-27 01:56
 */
public class Q0001_TwoSum {

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

	public int[] twoSum2(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[]{map.get(target - nums[i]), i};
			}
			map.put(nums[i], i);
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Q0001_TwoSum().twoSum2(new int[]{2, 7, 11, 15}, 9)));
		System.out.println(Arrays.toString(new Q0001_TwoSum().twoSum2(new int[]{5, 2, 9, 1, 4}, 6)));
	}
}
