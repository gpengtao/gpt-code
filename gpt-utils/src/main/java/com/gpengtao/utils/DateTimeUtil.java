package com.gpengtao.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author pengtao.geng on 2022/9/1 19:28.
 */
public class DateTimeUtil {

	public static String secondHumanDesc(Integer second) {
		String desc = "";
		if (second / TimeUnit.HOURS.toSeconds(1) > 0) {
			desc += (second / TimeUnit.HOURS.toSeconds(1)) + "小时";
			second = (int) (second % TimeUnit.HOURS.toSeconds(1));
		}
		if (second / TimeUnit.MINUTES.toSeconds(1) > 0) {
			desc += (second / TimeUnit.MINUTES.toSeconds(1)) + "分";
			second = (int) (second % TimeUnit.MINUTES.toSeconds(1));
		}
		if (second != 0) {
			desc += second + "秒";
		}
		return desc;
	}
}
