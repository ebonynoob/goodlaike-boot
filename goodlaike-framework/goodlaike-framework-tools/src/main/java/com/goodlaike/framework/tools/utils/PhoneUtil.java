package com.goodlaike.framework.tools.utils;

import java.util.regex.Pattern;

/**
 * 电话工具类
 * 
 * @author jail
 */
public final class PhoneUtil {

    /**
     * 是否手机号码
     * 
     * @param str
     * @return
     * @author jail
     */
    public static boolean isMobile(String str) {
        return Pattern.matches("1[3578]\\d{9}", str);
    }
}
