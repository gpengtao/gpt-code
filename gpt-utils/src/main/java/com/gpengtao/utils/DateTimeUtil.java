package com.gpengtao.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author pengtao.geng on 2022/9/1 19:28.
 */
public class DateTimeUtil {

	/**
	 * 日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期时间格式化形式 "yyyy-MM-dd HH:mm:ss"
	 */
	public static final FastDateFormat DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = FastDateFormat.getInstance(yyyy_MM_dd_HH_mm_ss);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 日期时间转换为"yyyy-MM-dd HH:mm:ss"字符串
	 */
	public static String formatToDateTime(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return DATE_FORMAT_yyyy_MM_dd_HH_mm_ss.format(date);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
