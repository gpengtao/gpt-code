package com.gpengtao.leetcode.labuladong.linkedlist.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2023/9/1 09:51.
 */
public class ListNode {
	public int val;
	public ListNode next;

	ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		return "ListNode{" +
				"val=" + val +
				'}';
	}

	public static ListNode buildLinK(int... num) {
		List<ListNode> list = Arrays.stream(num)
				.mapToObj(ListNode::new)
				.collect(Collectors.toList());
		for (int i = 0; i < list.size() - 1; i++) {
			list.get(i).next = list.get(i + 1);
		}
		return list.get(0);
	}

	public static void printList(ListNode listNode) {
		if (listNode == null) {
			System.out.println("null");
			return;
		}
		List<Integer> list = new ArrayList<>();
		while (listNode != null) {
			list.add(listNode.val);
			listNode = listNode.next;
		}
		String str = list.stream()
				.map(Object::toString)
				.collect(Collectors.joining("->"));
		System.out.println(str);
	}
}
