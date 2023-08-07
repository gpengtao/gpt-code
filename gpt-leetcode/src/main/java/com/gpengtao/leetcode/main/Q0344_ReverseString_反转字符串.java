package com.gpengtao.leetcode.main;

/**
 * <a href="https://leetcode.cn/problems/reverse-string/">...</a>
 *
 * @author pengtao.geng on 2023/8/7 19:20.
 */
public class Q0344_ReverseString_反转字符串 {

	public void reverseString(char[] s) {
		char temp;
		for (int i = 0; i < s.length / 2; i++) {
			temp = s[i];
			s[i] = s[s.length - 1 - i];
			s[s.length - 1 - i] = temp;
		}
	}

	public static void main(String[] args) {
		char[] s1 = {'h', 'e', 'l', 'l', 'o'};
		new Q0344_ReverseString_反转字符串().reverseString(s1);
		System.out.println(s1);

		char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
		new Q0344_ReverseString_反转字符串().reverseString(s2);
		System.out.println(s2);
	}
}
