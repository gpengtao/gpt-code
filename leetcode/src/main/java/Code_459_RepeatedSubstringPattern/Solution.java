package Code_459_RepeatedSubstringPattern;

/**
 * @author pengtao.geng on 2020/1/31 4:10 下午
 */
/*
https://leetcode-cn.com/problems/repeated-substring-pattern/
 */
public class Solution {

	public boolean repeatedSubstringPattern(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		char[] chars = s.toCharArray();
		int midLength = chars.length / 2;
		for (int i = 0; i < midLength; i++) {
			if (chars[i] != chars[i + midLength]) {
				return false;
			}
		}
		return true;
	}
}
