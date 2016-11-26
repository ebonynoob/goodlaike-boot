package com.goodlaike.framework.tools.utils;

import java.util.UUID;

/**
 * 随机数 工具类
 * 
 * @author jail
 */
public final class RandomUtil {

    /**
     * 获得32位随机 uuid
     * 
     * @return String
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月30日下午8:58:29
     * @updator jail
     * @updateTime 2015年8月30日下午8:58:29
     */
    public final static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
