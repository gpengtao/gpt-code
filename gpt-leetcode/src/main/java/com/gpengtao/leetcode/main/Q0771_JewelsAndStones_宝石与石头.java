package com.gpengtao.leetcode.main;

/**
 * <a href="https://leetcode.cn/problems/jewels-and-stones/">...</a>
 *
 * @author pengtao.geng on 2023/7/24 21:28.
 */
public class Q0771_JewelsAndStones_宝石与石头 {

	public int numJewelsInStones(String jewels, String stones) {
		int count = 0;
		for (char jewel : jewels.toCharArray()) {
			for (char stone : stones.toCharArray()) {
				if (jewel == stone) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(new Q0771_JewelsAndStones_宝石与石头().numJewelsInStones("aA", "aAAbbbb"));
		System.out.println(new Q0771_JewelsAndStones_宝石与石头().numJewelsInStones("z", "ZZ"));
	}
}
