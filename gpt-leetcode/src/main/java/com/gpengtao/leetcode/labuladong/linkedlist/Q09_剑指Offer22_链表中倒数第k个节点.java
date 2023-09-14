package com.gpengtao.leetcode.labuladong.linkedlist;

import com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode;

import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.buildLinK;
import static com.gpengtao.leetcode.labuladong.linkedlist.node.ListNode.printList;

/**
 * <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/">...</a>
 *
 * @author pengtao.geng on 2023/9/14 09:42.
 */
public class Q09_剑指Offer22_链表中倒数第k个节点 {

	/**
	 * 解法，双指针
	 */
	public ListNode getKthFromEnd(ListNode head, int k) {
		ListNode p2 = head;
		for (int i = 0; i < k; i++) {
			p2 = p2.next;
		}

		ListNode p1 = head;
		while (p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	public static void main(String[] args) {
		printList(new Q09_剑指Offer22_链表中倒数第k个节点().getKthFromEnd(buildLinK(1, 2, 3, 4, 5, 6), 3));
		printList(new Q09_剑指Offer22_链表中倒数第k个节点().getKthFromEnd(buildLinK(1, 2, 3, 4, 5, 6), 1));
		printList(new Q09_剑指Offer22_链表中倒数第k个节点().getKthFromEnd(buildLinK(1, 2, 3, 4, 5, 6), 6));
		printList(new Q09_剑指Offer22_链表中倒数第k个节点().getKthFromEnd(buildLinK(1), 1));
	}
}
