package com.gpengtao.leetcode.linkedlist;

import com.gpengtao.leetcode.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">...</a>
 *
 * @author pengtao.geng on 2023/9/7 20:45.
 */
public class Q06_23_MergeKSortedLists_合并K个升序链表 {

	/**
	 * 解法1，定义新的head，遍历链表数组的头，找到最小的index，追加到head上，index再后移，以此循环到没有最小index为止
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		ListNode head = new ListNode(-1);
		ListNode p = head;


		while (true) {
			int minNodeIndex = findMin(lists);
			if (minNodeIndex == -1) {
				break;
			}
			p.next = lists[minNodeIndex];
			p = p.next;
			lists[minNodeIndex] = lists[minNodeIndex].next;
		}
		return head.next;
	}

	private int findMin(ListNode[] lists) {
		int value = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null && lists[i].val < value) {
				value = lists[i].val;
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		// [[1,4,5],[1,3,4],[2,6]]
		printList(new Q06_23_MergeKSortedLists_合并K个升序链表().mergeKLists(new ListNode[]{buildLinK(1, 4, 5), buildLinK(1, 3, 4), buildLinK(2, 6)}));
	}

}
