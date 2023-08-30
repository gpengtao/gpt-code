package com.gpengtao.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">...</a>
 *
 * @author pengtao.geng on 2023/8/30 21:10.
 */
public class Q0160_IntersectionOfTwoLinkedLists_相交链表 {

	/**
	 * 解法1，用Set保存链表A，然后遍历B的节点看是否在Set中
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Set<ListNode> hadTraversed = new HashSet<>();
		while (headA != null) {
			hadTraversed.add(headA);
			headA = headA.next;
		}
		while (headB != null) {
			if (hadTraversed.contains(headB)) {
				return headB;
			}
			headB = headB.next;
		}
		return null;
	}

	private class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
