package com.gpengtao.leetcode.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/reorder-list/">...</a>
 *
 * @author pengtao.geng on 2023/7/31 10:11.
 */
public class Q0143_ReorderList_重排链表 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					'}';
		}
	}

	public void reorderList(ListNode head) {
		// 构造一个栈
		Stack<ListNode> stack = new Stack<>();
		ListNode cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}

		// 计算栈需要pop出来的数量，即数组的一半：4的一半2，5的一半2
		int count = stack.size() / 2;
		ListNode current = head;
		while (count-- > 0) {
			ListNode popped = stack.pop();
			ListNode originNext = current.next;

			popped.next = originNext;
			current.next = popped;
			current = originNext;
		}
		current.next = null;
	}

	public static void main(String[] args) {
		test1(new int[]{1, 2, 3, 4, 5});
		test1(new int[]{1, 2, 3, 4});
	}

	private static void test1(int[] nums) {
		ListNode head = buildList(nums);
		new Q0143_ReorderList_重排链表().reorderList(head);
		print(head);
	}

	private static ListNode buildList(int[] nums) {
		ListNode head = new ListNode(nums[0]);

		ListNode current = head;
		for (int i = 1; i < nums.length; i++) {
			current.next = new ListNode(nums[i]);
			current = current.next;
		}
		return head;
	}

	private static void print(ListNode head) {
		List<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		System.out.println(list);
	}
}
