package com.gpengtao.leetcode.main;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/power-of-heroes/">...</a>
 *
 * @author pengtao.geng on 2023/8/1 22:35.
 */
public class Q2681_PowerOfHeroes_英雄的力量 {

	public int sumOfPower2(int[] nums) {
		Arrays.sort(nums);
		long endInclusive = (long) Math.pow(2, nums.length) - 1;
		long sum = 0;
		for (long num = 1; num <= endInclusive; num++) {
			long temp = num;
			int minIdx = -1;
			int maxIdx = -1;
			int currentIdx = 0;
			while (num > 0) {
				long remainder = num % 2;
				if (remainder > 0) {
					if (minIdx == -1) {
						minIdx = currentIdx;
					}
					maxIdx = currentIdx;
				}
//				System.out.println("余数 " + remainder);
				num = num / 2;
				currentIdx++;
			}
			int ret = nums[maxIdx] * nums[maxIdx] * nums[minIdx];
//			System.out.println("数值 " + temp + " min下标 " + minIdx + " max下标 " + maxIdx + " 计算得到 " + ret + "\n");
			sum += ret;
		}

		return (int) (sum % ((long) (Math.pow(10, 9)) + 7));
	}

	/**
	 * 官方解答
	 */
	public int sumOfPower(int[] nums) {
		Arrays.sort(nums);
		int[] dp = new int[nums.length];
		int[] preSum = new int[nums.length + 1];
		int res = 0, mod = 1000000007;
		for (int i = 0; i < nums.length; i++) {
			dp[i] = (nums[i] + preSum[i]) % mod;
			preSum[i + 1] = (preSum[i] + dp[i]) % mod;
			res = (int) ((res + (long) nums[i] * nums[i] % mod * dp[i]) % mod);
			if (res < 0) {
				res += mod;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{1, 2}));
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{2, 1, 4}));
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{1, 2, 4}));
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{1, 1, 1}));
		System.out.println(new Q2681_PowerOfHeroes_英雄的力量().sumOfPower(new int[]{658, 489, 777, 2418, 1893, 130, 2448, 178, 1128, 2149, 1059, 1495, 1166, 608, 2006, 713, 1906, 2108, 680, 1348, 860, 1620, 146, 2447, 1895, 1083, 1465, 2351, 1359, 1187, 906, 533, 1943, 1814, 1808, 2065, 1744, 254, 1988, 1889, 1206}));
	}
}
