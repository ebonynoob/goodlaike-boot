package com.goodlaike.business.core.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.support.Constants;

/**
 * 登录相关的 helper 类
 * 
 * @author Jail Hu
 */
public final class LoginHelper {

    /**
     * 获得登录的用户
     * 
     * @param request
     * @return User
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月27日上午10:41:24
     * @updator Jail Hu
     * @updateTime 2015年8月27日上午10:41:24
     */
    public static final User getLoginUser(HttpServletRequest request) {
        Assert.notNull(request);
        return (User) request.getSession(true).getAttribute(Constants.LOGIN_SESSION_NAME);
    }

    /**
     * 登录用户写入session
     * 
     * @param request
     * @param user
     * @since 1.0.0
     * @author jail
     * @createTime 2015年9月3日下午7:40:15
     * @updator jail
     * @updateTime 2015年9月3日下午7:40:15
     */
    public static final void setLoginUser(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute(Constants.LOGIN_SESSION_NAME, user);
    }

    /**
     * 移除登录用户的session
     * 
     * @param request
     * @since 1.0.0
     * @author jail
     * @createTime 2015年9月3日下午7:45:44
     * @updator jail
     * @updateTime 2015年9月3日下午7:45:44
     */
    public static final void removeLoginUser(HttpServletRequest request) {
        request.getSession(true).removeAttribute(Constants.LOGIN_SESSION_NAME);
    }
}
