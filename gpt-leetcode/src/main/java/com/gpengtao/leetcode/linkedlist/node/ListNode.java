package com.gpengtao.leetcode.linkedlist.node;

import java.util.Arrays;
import java.util.List;

;

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
                .toList();
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        return list.get(0);
    }
}
