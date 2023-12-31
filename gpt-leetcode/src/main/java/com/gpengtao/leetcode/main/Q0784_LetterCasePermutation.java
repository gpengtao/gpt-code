package com.gpengtao.leetcode.main;

import java.util.List;

/**
 * 784. 字母大小写全排列
 *
 * <a href="https://leetcode-cn.com/problems/letter-case-permutation/">https://leetcode-cn.com/problems/letter-case-permutation/</a>
 *
 * @author pengtao.geng on 2019-07-21 18:42
 */
public class Q0784_LetterCasePermutation {

	public static List<String> letterCasePermutation(String str) {
		char[] chars = str.toCharArray();

		permutation(chars, 0, chars.length);
		return null;
	}

	private static void permutation(char[] chars, int start, int end) {
		if (start == end) {
			System.out.println(chars);
			return;
		}
		if (isLetter(chars[start])) {
			permutation(chars, start + 1, end);

			chars[start] = transfer(chars[start]);
			permutation(chars, start + 1, end);

			chars[start] = transfer(chars[start]);
		} else {
			permutation(chars, start + 1, end);
		}
	}

	private static char transfer(char aChar) {
		if (isUpLetter(aChar)) {
			return (char) (aChar + ('a' - 'A'));
		} else {
			return (char) (aChar - ('a' - 'A'));
		}
	}

	private static boolean isLetter(char aChar) {
		return isUpLetter(aChar) || isLowLetter(aChar);
	}

	private static boolean isUpLetter(char aChar) {
		return aChar >= 'A' && aChar <= 'Z';
	}

	private static boolean isLowLetter(char aChar) {
		return aChar >= 'a' && aChar <= 'z';
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");

		letterCasePermutation("1111a");
	}
}
