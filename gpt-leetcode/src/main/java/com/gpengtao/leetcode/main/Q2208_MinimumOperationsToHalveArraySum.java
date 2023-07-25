package com.gpengtao.leetcode.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 将数组和减半的最少操作次数
 * <a href="https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/">...</a>
 *
 * @author pengtao.geng on 2023/7/25 10:18
 */
public class Q2208_MinimumOperationsToHalveArraySum {

	/**
	 * pengtao.geng实现
	 */
	public int halveArray(int[] originNums) {
		// copy
		double[] nums = new double[originNums.length];
		for (int i = 0; i < originNums.length; i++) {
			nums[i] = originNums[i];
		}

		// 求和
		double sum = 0;
		for (double num : nums) {
			sum += num;
		}

		// 目标要减去的量
		double targetReduceNum = sum / 2;

		// 求次数
		double hadReduceNum = 0;
		int count = 0;
		while (hadReduceNum < targetReduceNum) {
			// 排序（正序）
			Arrays.sort(nums);

			double maxValue = nums[nums.length - 1];
			double newValue = maxValue / 2;

			System.out.println(Arrays.toString(Arrays.stream(nums).toArray()) + " 最大的数 " + maxValue);

			hadReduceNum += newValue;
			count++;
			nums[nums.length - 1] = newValue;
		}
		return count;
	}

	/**
	 * 官方实现，优先级队列
	 */
	public int halveArray2(int[] nums) {
		PriorityQueue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
		for (int num : nums) {
			queue.offer((double) num);
		}

		double sum = 0;
		for (int num : nums) {
			sum += num;
		}

		int result = 0;
		double sum2 = 0.0;
		while (sum2 < sum / 2) {
			Double maxValue = queue.poll();
			assert maxValue != null;
			sum2 += maxValue / 2;
			queue.offer(maxValue / 2);
			result++;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new Q2208_MinimumOperationsToHalveArraySum().halveArray(new int[]{5, 19, 8, 1}));
		System.out.println(new Q2208_MinimumOperationsToHalveArraySum().halveArray(new int[]{3, 8, 20}));
		int[] list = {32, 98, 23, 14, 67, 40, 26, 9, 96, 96, 91, 76, 4, 40, 42, 2, 31, 13, 16, 37, 62, 2, 27, 25, 100, 94, 14, 3, 48, 56, 64, 59, 33, 10, 74, 47, 73, 72, 89, 69, 15, 79, 22, 18, 53, 62, 20, 9, 76, 64};
		System.out.println(new Q2208_MinimumOperationsToHalveArraySum().halveArray(list));
		System.out.println(new Q2208_MinimumOperationsToHalveArraySum().halveArray2(list));
	}
}
