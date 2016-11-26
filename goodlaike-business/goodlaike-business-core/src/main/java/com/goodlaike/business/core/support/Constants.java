package com.goodlaike.business.core.support;

/**
 * 核心常量类
 * 
 * @author Jail Hu
 */
public final class Constants {

    /**
     * 本地化 session 名
     */
    public final static String LOCALIZATION_SESSION_NAME = "session_localization";
    /**
     * 本地化 cookie 名
     */
    public final static String LOCALIZATION_COOKIE_NAME = "lang_current";
    /**
     * 登录 session 名
     */
    public final static String LOGIN_SESSION_NAME = "session_login";
    /**
	 * 默认的本地化语言
	 */
	public final static Localization DEFAULT_LOCALIZATION = Localization.CN;

    /**
     * 默认的应用程序编码
     */
    public final static String DEFAULT_CHARSET = "utf-8";

    /**
     * 默认的分页尺寸
     */
    public final static int DEFAULT_PAGESIZE = 20;
}
