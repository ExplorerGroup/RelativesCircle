package com.mn.pp.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字工具类
 * 
 * @author LLM
 * @version 2015-08-18
 */
public class MathUtils {

	/**
	 * 是否是Integer字符串，最大支持9位数（不包含负号位）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIntegerString(String str) {
		if (str == null) {
			return false;
		}

		Pattern pattern = Pattern.compile("(-?[1-9]\\d{0,8})|0");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 是否是BigDecimal字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBigDecimalString(String str) {
		if (str == null) {
			return false;
		}

		Pattern pattern = Pattern.compile("([1-9]\\d*(\\.\\d+)?)|0|(-?0\\.\\d+)|(-[1-9]\\d*\\.\\d+)|(-[1-9]\\d*)");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 将字符串转为整数，转换失败则返回替代整数
	 * 
	 * @param str
	 *            字符串
	 * @param replaceInteger
	 *            替代整数
	 * @return
	 */
	public static Integer valueOf(String str, Integer replaceInteger) {
		try {
			return Integer.valueOf(str);
		} catch (Exception ex) {
			return replaceInteger;
		}
	}

	/**
	 * 将数字字符串转换成long型，转换失败则返回0
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static long toLong(String str) {
		return toLong(str, 0);
	}

	/**
	 * 将数字字符串转换成long型，转换失败则返回替代长整数
	 * 
	 * @param str
	 *            字符串
	 * @param replaceLong
	 *            替代长整数
	 * @return
	 */
	public static long toLong(String str, long replaceLong) {

		String[] strs = str.split("\\.");
		if (strs.length <= 0) {
			return replaceLong;
		}

		String val = strs[0];
		if (val.trim().isEmpty()) {
			return replaceLong;
		}

		return Long.valueOf(val);

	}

	/**
	 * 将数值转为整数，转换失败则返回替代整数
	 * 
	 * @param val
	 *            数值
	 * @param replaceInteger
	 *            替代整数
	 * @return
	 */
	public static Integer valueOf(int val, Integer replaceInteger) {
		try {
			return Integer.valueOf(val);
		} catch (Exception ex) {
			return replaceInteger;
		}
	}

	/**
	 * 将字符串转为BigDecimal，转换失败则返回替代BigDecimal
	 * 
	 * @param str
	 *            字符串
	 * @param replaceBigDecimal
	 *            替代BigDecimal
	 * @return
	 */
	public static BigDecimal valueOf(String str, BigDecimal replaceBigDecimal) {
		try {
			return new BigDecimal(str);
		} catch (Exception ex) {
			return replaceBigDecimal;
		}
	}

	/**
	 * 将数值转为BigDecimal，转换失败则返回替代BigDecimal
	 * 
	 * @param val
	 *            数值
	 * @param replaceBigDecimal
	 *            替代BigDecimal
	 * @return
	 */
	public static BigDecimal valueOf(int val, BigDecimal replaceBigDecimal) {
		try {
			return new BigDecimal(val);
		} catch (Exception ex) {
			return replaceBigDecimal;
		}
	}

	/**
	 * 将长整数转为BigDecimal，转换失败则返回替代BigDecimal
	 * 
	 * @param val
	 *            长整数
	 * @param replaceBigDecimal
	 *            替代BigDecimal
	 * @return
	 */
	public static BigDecimal valueOf(long val, BigDecimal replaceBigDecimal) {
		try {
			return BigDecimal.valueOf(val);
		} catch (Exception ex) {
			return replaceBigDecimal;
		}
	}

	/**
	 * 将双精度浮点型转为BigDecimal，转换失败则返回替代BigDecimal
	 * 
	 * @param val
	 *            双精度浮点型
	 * @param replaceBigDecimal
	 *            替代BigDecimal
	 * @return
	 */
	public static BigDecimal valueOf(double val, BigDecimal replaceBigDecimal) {
		try {
			return BigDecimal.valueOf(val);
		} catch (Exception ex) {
			return replaceBigDecimal;
		}
	}

	/**
	 * 四舍五入，失败时返回0
	 * 
	 * @param val
	 *            BigDecimal
	 * @param scale
	 *            小数点后保留几位
	 * @return
	 */
	public static BigDecimal round(BigDecimal val, int scale) {
		if (val == null) {
			new BigDecimal(0);
		}

		BigDecimal bd = null;

		switch (val.compareTo(BigDecimal.valueOf(0))) {
		case -1:
			bd = val.abs().divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(-1));
			break;
		case 0:
			bd = BigDecimal.valueOf(0);
			break;
		case 1:
			bd = val.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
			break;
		}

		return bd;
	}

	/**
	 * 格式化数字，失败时返回空字符串
	 * 
	 * @param val
	 *            整数
	 * @param pattern
	 *            格式，如：<br>
	 *            #.00<br>
	 *            ###,###.00<br>
	 *            ￥#.00<br>
	 *            ￥###,###.00
	 * @return
	 */
	public static String format(Integer val, String pattern) {
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(val);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 格式化数字，失败时返回空字符串
	 * 
	 * @param val
	 *            长整数
	 * @param pattern
	 *            格式，如：<br>
	 *            #.00<br>
	 *            ###,###.00<br>
	 *            ￥#.00<br>
	 *            ￥###,###.00
	 * @return
	 */
	public static String format(long val, String pattern) {
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(val);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 格式化数字，失败时返回空字符串
	 * 
	 * @param val
	 *            双精度浮点数
	 * @param pattern
	 *            格式，如：<br>
	 *            #.00<br>
	 *            ###,###.00<br>
	 *            ￥#.00<br>
	 *            ￥###,###.00
	 * @return
	 */
	public static String format(double val, String pattern) {
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(val);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 格式化数字，失败时返回空字符串
	 * 
	 * @param val
	 *            BigDecimal
	 * @param pattern
	 *            格式，如：<br>
	 *            #.00<br>
	 *            ###,###.00<br>
	 *            ￥#.00<br>
	 *            ￥###,###.00
	 * @return
	 */
	public static String format(BigDecimal val, String pattern) {
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(val);
		} catch (Exception ex) {
			return "";
		}
	}
}
