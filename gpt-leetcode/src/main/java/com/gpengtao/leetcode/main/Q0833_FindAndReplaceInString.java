package com.gpengtao.leetcode.main;

/**
 * <a href="https://leetcode.cn/problems/find-and-replace-in-string/description/">...</a>
 *
 * @author pengtao.geng on 2023/8/15 20:05.
 */
public class Q0833_FindAndReplaceInString {

	/**
	 * 当前实现有问题，需要先对 indices 排序
	 */
	public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
		StringBuilder result = new StringBuilder();
		int index = 0;
		for (int i = 0; i < s.length(); ) {
			if (index < indices.length && i == indices[index]) {
				String source = sources[index];
				if (isMatch(s, i, source)) {
					result.append(targets[index]);
					i += i + source.length();
				} else {
					result.append(s.charAt(i));
					i++;
				}
				index++;
			} else {
				result.append(s.charAt(i));
				i++;
			}
		}
		return result.toString();
	}

	private static boolean isMatch(String s, int start, String source) {
		for (int x = start, y = 0; y < source.length(); x++, y++) {
			if (s.charAt(x) != source.charAt(y)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Q0833_FindAndReplaceInString().findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"}));
//		System.out.println(new Q().findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));

	}
}
