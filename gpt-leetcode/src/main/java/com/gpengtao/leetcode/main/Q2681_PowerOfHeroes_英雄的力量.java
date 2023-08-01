package com.gpengtao.leetcode.main;

import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.cn/problems/power-of-heroes/">...</a>
 *
 * @author pengtao.geng on 2023/8/1 22:35.
 */
public class Q2681_PowerOfHeroes_英雄的力量 {

	public int sumOfPower(int[] nums) {
		long sum = IntStream.rangeClosed(1, (1 << nums.length) - 1)
				.mapToLong(num -> {
					long min = 0;
					long max = 0;
					int idx = 0;
					while (num > 0) {
						int i = num % 2;
						num = num / 2;
						if (i > 0) {
							if (nums[idx] > max) {
								max = nums[idx];
							}
							if (min == 0 || nums[idx] < min) {
								min = nums[idx];
							}
						}
						idx++;
					}
					return max * max * min;
				})
				.sum();
		return (int) (sum % ((long) (Math.pow(10, 9)) + 7));
	}

	public static void main(String[] args) {
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{1, 2}));
//		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{2, 1, 4}));
//		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{658, 489, 777, 2418, 1893, 130, 2448, 178, 1128, 2149, 1059, 1495, 1166, 608, 2006, 713, 1906, 2108, 680, 1348, 860, 1620, 146, 2447, 1895, 1083, 1465, 2351, 1359, 1187, 906, 533, 1943, 1814, 1808, 2065, 1744, 254, 1988, 1889, 1206}));
//		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{1, 1, 1}));
	}
}
