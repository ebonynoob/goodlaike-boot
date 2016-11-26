package com.goodlaike.framework.tools.utils;
/**
 * StringBuilder Helper类
 * @author Jail    E -Mail:86455@ dooioo.com
 */
public final class StringUtil {
	/**
	 * 去除String的首尾一个匹配字符(串)
	 * @param input String对象
	 * @param c 需要去除的尾部字符(串)
	 * @return 去除尾部指定字符(串)之后的String对象
	 */
	public static String trim(String input,String c){
		if(input.startsWith(c))
			input = input.substring(input.indexOf(c) + c.length());
		if(input.endsWith(c))
			input = input.substring(0, input.lastIndexOf(c));
		return input;
	}
	/**
	 * 去掉字符串右边的空格
	 *
	 * @param str 要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

}

