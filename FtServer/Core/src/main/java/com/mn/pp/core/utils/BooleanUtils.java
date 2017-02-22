package com.mn.pp.core.utils;

/**
 * 布尔工具类
 * 
 * @author wzw
 * @version 2010-12-11
 */
public class BooleanUtils {

	/**
	 * 将对象转成布尔值
	 * 
	 * @param obj
	 * @return 当值（不区分大小写）为1,y,t,yes,on,true,enable时返回true,否则返回false
	 */
	public static boolean valueOf(Object obj) {
		if (obj == null) {
			return false;
		}

		String b = obj.toString().toLowerCase();
		if (b.equals("1") || b.equals("y") || b.equals("t") || b.equals("yes") || b.equals("on") || b.equals("true") || b.equals("enable")) {
			return true;
		}

		return false;
	}
}
