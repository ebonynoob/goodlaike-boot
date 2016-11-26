package com.goodlaike.business.core.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.goodlaike.business.core.support.Constants;
import com.goodlaike.business.core.support.LanguageStore;

/**
 * 获得当前的本地化语言
 * 
 * @author Jail Hu
 */
public final class LanguageHelper {

    /**
     * 获得当前设置的本地化语言
     * 
     * @param request
     * @return String
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月21日下午7:00:01
     * @updator Jail Hu
     * @updateTime 2015年8月21日下午7:00:01
     */
    public static String getLocalization(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Constants.DEFAULT_LOCALIZATION.getValue();
        } else {
            for (Cookie cookie : cookies) {
                if (Constants.LOCALIZATION_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
            return Constants.DEFAULT_LOCALIZATION.getValue();
        }
    }

    /**
     * 获得当前设置的本地化语言
     * 
     * @param request
     * @param lang
     *            如果有语言参数则直接根据语言参数获得
     * @return String
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:20:44
     * @updator jail
     * @updateTime 2015年8月23日下午10:20:44
     */
    public static String getLocalization(HttpServletRequest request, String lang) {
        if (lang != null) {
            return LanguageStore.getLanguage(lang);
        } else {
            return getLocalization(request);
        }
    }
}
