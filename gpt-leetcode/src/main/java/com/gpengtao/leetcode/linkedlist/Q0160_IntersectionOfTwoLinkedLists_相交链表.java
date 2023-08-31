package com.gpengtao.leetcode.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

	/**
	 * 解法2，双指针遍历，a到终点后换到B去遍历，b到终点后换到A去遍历，如果a==b则找到了
	 */
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		boolean aFirst = true;
		boolean bFirst = true;
		while (a != null && b != null) {
			if (a == b) {
				return a;
			}
			a = a.next;
			b = b.next;
			if (a == null && aFirst) {
				a = headB;
				aFirst = false;
			}
			if (b == null && bFirst) {
				b = headA;
				bFirst = false;
			}
		}
		return null;
	}

	/**
	 * 解法2，精简了代码
	 */
	public ListNode getIntersectionNode2_1(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		while (a != b) {
			if (a == null) {
				a = headB;
			} else {
				a = a.next;
			}
			if (b == null) {
				b = headA;
			} else {
				b = b.next;
			}
		}
		return a;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}

		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					'}';
		}
	}

	/**
	 * 2 -> 6 -> 4    -> null -> 1 -> 5 -> null
	 * 1 -> 5 -> null -> 2    -> 6 -> 4 -> null
	 */
	public static void main(String[] args) {
		ListNode headA = buildLinK(2, 6, 4);
		ListNode headB = buildLinK(1, 5);
		ListNode ret = new Q0160_IntersectionOfTwoLinkedLists_相交链表().getIntersectionNode2_1(headA, headB);
		System.out.println(ret);
	}

	private static ListNode buildLinK(int... num) {
		List<ListNode> list = Arrays.stream(num)
				.mapToObj(ListNode::new)
				.toList();
		for (int i = 0; i < list.size() - 1; i++) {
			list.get(i).next = list.get(i + 1);
		}
		return list.get(0);
	}
}
