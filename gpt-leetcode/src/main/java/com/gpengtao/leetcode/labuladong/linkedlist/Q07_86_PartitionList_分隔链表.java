package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/partition-list/">...</a>
 *
 * @author pengtao.geng on 2023/9/9 15:55.
 */
public class Q07_86_PartitionList_分隔链表 {

	/**
	 * 解法，定义两个新链表，遍历head，比 x 大的追到链表1，否则追到链表2，然后链表1连上链表2即是结果
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode list1 = new ListNode(-1);
		ListNode list2 = new ListNode(-1);
		ListNode p1 = list1;
		ListNode p2 = list2;
		while (head != null) {
			if (head.val < x) {
				p1.next = head;
				p1 = p1.next;
			} else {
				p2.next = head;
				p2 = p2.next;
			}
			head = head.next;
		}
		p1.next = list2.next;
		p2.next = null;
		return list1.next;
	}

	public static void main(String[] args) {
		printList(new Q07_86_PartitionList_分隔链表().partition(buildLinK(2, 1), 2));
		printList(new Q07_86_PartitionList_分隔链表().partition(buildLinK(1, 4, 3, 2, 5, 2), 3));
		printList(new Q07_86_PartitionList_分隔链表().partition(buildLinK(4, 3, 2, 5, 2), 3));
		printList(new Q07_86_PartitionList_分隔链表().partition(buildLinK(1, 4, 3, 0, 5, 2), 2));
	}
}
