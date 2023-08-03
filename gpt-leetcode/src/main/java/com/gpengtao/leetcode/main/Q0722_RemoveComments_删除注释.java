package com.gpengtao.leetcode.main;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/remove-comments/description/">...</a>
 *
 * @author pengtao.geng on 2023/8/3 22:36.
 */
public class Q0722_RemoveComments_删除注释 {

	/**
	 * 力扣官方题解
	 */
	public List<String> removeComments(String[] source) {
		List<String> res = new ArrayList<>();

		boolean inBlock = false;
		StringBuilder newLine = new StringBuilder();
		for (String line : source) {
			for (int i = 0; i < line.length(); i++) {
				if (inBlock) {
					if (i + 1 < line.length() && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
						inBlock = false;
						i++;
					}
				} else {
					if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
						inBlock = true;
						i++;
					} else if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
						break;
					} else {
						newLine.append(line.charAt(i));
					}
				}
			}
			if (!inBlock && newLine.length() > 0) {
				res.add(newLine.toString());
				newLine.setLength(0);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new Q0722_RemoveComments_删除注释().removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}));
	}
}
