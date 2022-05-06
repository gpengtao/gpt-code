package com.gpengtao.leetcode.main;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author pengtao.geng on 2022/5/6 09:07.
 */
public class Q0933_NumberOfRecentCalls {

	public static class RecentCounter {
		/**
		 * 注意长度3001，题目的时间区间是左闭又闭
		 */
		private static final int LENGTH = 3001;
		private final int[] record = new int[LENGTH];
		private int current = 0;

		public RecentCounter() {
		}

		public int ping(int t) {
			record[current] = t;
			current++;
			if (current >= LENGTH) {
				current = 0;
			}

			int count = 0;
			for (int i = 0; i < LENGTH; i++) {
				int index = current - i - 1;
				if (index < 0) {
					index += LENGTH;
				}
				if (record[index] > 0 && t - record[index] < LENGTH) {
					count++;
				} else {
					break;
				}
			}
			return count;
		}
	}

	public static class RecentCounter2 {
		private final Queue<Integer> queue = new ArrayDeque<>();

		public RecentCounter2() {
		}

		public int ping(int t) {
			queue.offer(t);
			while (queue.peek() < t - 3000) {
				queue.poll();
			}
			return queue.size();
		}
	}


	public static void main(String[] args) {
		RecentCounter counter = new RecentCounter();
		for (int i = 1; i <= 3002; i++) {
			System.out.println(counter.ping(i));
		}
	}
}
