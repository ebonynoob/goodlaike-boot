package com.goodlaike.business.core.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.goodlaike.business.core.helper.LoginHelper;
import com.goodlaike.business.core.model.User;

/**
 * assert for goodlaike
 * 
 * @author Jail Hu
 */
public class AssertGoodlaike extends Assert {

	/**
	 * 登录验证 <br>
	 * 未登录 抛出 IllegalLoginException
	 * 
	 * @param request
	 * @param message
	 *            附带异常信息
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:51:11
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:51:11
	 */
	public static void needLogin(HttpServletRequest request, String message) {
		needLogin(LoginHelper.getLoginUser(request), message);
	}

	/**
	 * 登录验证 <br>
	 * 未登录 抛出 IllegalLoginException
	 * 
	 * @param request
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:52:09
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:52:09
	 */
	public static void needLogin(HttpServletRequest request) {
		needLogin(request, "must login first");
	}

	/**
	 * 登录验证 <br>
	 * 登录 抛出 IllegalLoginException
	 * 
	 * @param request
	 * @param message
	 *            附带异常信息
	 * @param request
	 * @param message
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:53:20
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:53:20
	 */
	public static void notLogin(HttpServletRequest request, String message) {
		notLogin(LoginHelper.getLoginUser(request), message);
	}

	/**
	 * 登录验证 <br>
	 * 登录 抛出 IllegalLoginException
	 * 
	 * @param request
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:52:09
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:52:09
	 */
	public static void notLogin(HttpServletRequest request) {
		notLogin(request, "must not login ");
	}

	/**
	 * 登录验证 <br>
	 * 未登录 抛出 IllegalLoginException
	 * 
	 * @param user
	 *            用户对象
	 * @param message
	 *            附带异常信息
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:51:11
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:51:11
	 */
	public static void needLogin(User user, String message) {
		if (user == null) {
			throw new IllegalLoginException(message);
		}
	}

	/**
	 * 登录验证 <br>
	 * 未登录 抛出 IllegalLoginException
	 * 
	 * @param user
	 *            登录用户
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:52:09
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:52:09
	 */
	public static void needLogin(User user) {
		needLogin(user, "must login first");
	}

	/**
	 * 登录验证 <br>
	 * 登录 抛出 IllegalLoginException
	 * 
	 * @param user
	 *            登录用户
	 * @param message
	 *            附带异常信息
	 * @param request
	 * @param message
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:53:20
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:53:20
	 */
	public static void notLogin(User user, String message) {
		if (user != null) {
			throw new IllegalLoginException(message);
		}
	}

	/**
	 * 登录验证 <br>
	 * 登录 抛出 IllegalLoginException
	 * 
	 * @param user
	 *            登录用户
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日上午10:52:09
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日上午10:52:09
	 */
	public static void notLogin(User user) {
		notLogin(user, "must not login ");
	}

}
