package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/">...</a>
 *
 * @author pengtao.geng on 2023/9/14 10:08.
 * @see Q05_21_MergeTwoSortedLists_合并两个有序链表
 */
public class Q10_剑指Offer25_合并两个排序的链表 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 相同
		return null;
	}

	public static void main(String[] args) {
		printList(new Q10_剑指Offer25_合并两个排序的链表().mergeTwoLists(buildLinK(1, 2, 4), buildLinK(1, 3, 4)));
	}
}
