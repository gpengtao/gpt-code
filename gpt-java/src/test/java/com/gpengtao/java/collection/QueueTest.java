package com.gpengtao.java.collection;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author pengtao.geng on 2022/5/6 10:31.
 */
public class QueueTest {

	@Test
	public void test_queue() {
		Deque<Integer> queue = new ArrayDeque<>(1);

		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue);

		System.out.println(queue.peek());
		System.out.println(queue);

		System.out.println(queue.poll());
		System.out.println(queue);

		queue.addFirst(0);
		queue.addLast(4);
		queue.add(5);
		System.out.println(queue);
	}
}
