package com.gpengtao.leetcode.main;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/card-flipping-game/">...</a>
 *
 * @author pengtao.geng on 2023/8/2 20:04.
 */
public class Q0822_CardFlippingGame_翻转卡片游戏 {

	/**
	 * 官方的解答
	 */
	public int flipgame(int[] fronts, int[] backs) {
		Set<Integer> sameNum = new HashSet<>();
		for (int i = 0; i < fronts.length; ++i) {
			if (fronts[i] == backs[i]) {
				sameNum.add(fronts[i]);
			}
		}
		int result = 0;
		for (int num : fronts) {
			if ((num < result || result == 0) && !sameNum.contains(num)) {
				result = num;
			}
		}
		for (int num : backs) {
			if ((num < result || result == 0) && !sameNum.contains(num)) {
				result = num;
			}
		}
		return result;
	}

	public int flipgame2(int[] fronts, int[] backs) {
		Set<Integer> sameNums = new HashSet<>();
		for (int i = 0; i < fronts.length; i++) {
			if (fronts[i] == backs[i]) {
				sameNums.add(fronts[i]);
			}
		}

		int result = 0;
		for (int i = 0; i < fronts.length; i++) {
			if (sameNums.contains(fronts[i])) {
				continue;
			}

			int frontNum = fronts[i];
			int backNum = backs[i];
			if ((frontNum < result || result == 0) && notHasNum(fronts, backNum)) {
				result = frontNum;
			}
		}
		return result;
	}

	private boolean notHasNum(int[] fronts, int num) {
		for (int front : fronts) {
			if (front == num) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Q0822_CardFlippingGame_翻转卡片游戏().flipgame(new int[]{1, 1}, new int[]{1, 2}));
		System.out.println(new Q0822_CardFlippingGame_翻转卡片游戏().flipgame(new int[]{1, 2, 4, 4, 7}, new int[]{1, 3, 4, 1, 3}));
	}
}
