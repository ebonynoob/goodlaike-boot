package com.goodlaike.framework.tools.utils;

import java.util.regex.Pattern;

/**
 * 邮件 工具类
 * @author jail
 */
public final class EmailUtil {

    /**
     * 检查是否邮箱
     * @param str
     * @return 
     * @author jail
     */
    public static boolean isEmail(String str){
        return Pattern.matches("\\w+@\\w+\\.\\w+", str);
    }
}
