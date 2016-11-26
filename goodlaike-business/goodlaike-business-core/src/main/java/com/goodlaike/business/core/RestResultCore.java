package com.goodlaike.business.core;

import com.goodlaike.business.core.support.RestResult;

/**
 * Core 业务异常代码
 * 
 * @author jail
 */
public final class RestResultCore extends RestResult {

	private static final long serialVersionUID = -2535536072355074330L;

	/**
	 * 修改用户信息异常<br>
	 * 10001
	 */
	public final static RestResult USER_UPDATE_ERROR = RestResult.code(10001).message("修改用户信息异常");
}
