package com.gpengtao.leetcode.labuladong.list;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">...</a>
 *
 * @author pengtao.geng on 2023/9/24 10:59.
 */
public class Q02_26_RemoveDuplicatesFromSortedArray_删除有序数组中的重复项 {

	/**
	 * 解法1，评论区 xgbj 的杰作
	 */
	public int removeDuplicates(int[] nums) {
		int index = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				nums[index++] = nums[i];
			}
		}
		return index;
	}
}
