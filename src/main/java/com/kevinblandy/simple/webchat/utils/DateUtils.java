package com.kevinblandy.simple.webchat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by KevinBlandy on 2017/5/12 9:13
 */
public class DateUtils {
	/**
	 * 默认格式化
	 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 字符串形式返回当前时间
	 * @return
	 */
	public static String currentDateTime() {
		return LocalDateTime.now().format(DATE_TIME_FORMATTER);
	}
	
	/**
	 * 字符串形式返回当前时间,自定义格式化格式
	 * @param pattern
	 * @return
	 */
	public static String currentDateTime(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static Long now() {
		return Instant.now().toEpochMilli();
	}
	
	/**
	 * 时间格式化
	 * @param date
	 * @return
	 */
	public static Date formart(String date) {
		try {
			return DEFAULT_DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
