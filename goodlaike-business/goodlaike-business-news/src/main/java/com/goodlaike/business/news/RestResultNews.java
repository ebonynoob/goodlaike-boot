package com.goodlaike.business.news;

import com.goodlaike.business.core.support.RestResult;

/**
 * 新闻业务异常类
 * 
 * @author jail
 */
public class RestResultNews extends RestResult {

	private static final long serialVersionUID = -1151873350624019905L;

	/**
	 * 删除新闻失败 <br>
	 * 30001
	 */
	public static final RestResult NEWS_DELETE_ERROR = RestResult.code(30001).message("删除新闻失败");

	/**
	 * 更新新闻失败 <br>
	 * 30002
	 */
	public static final RestResult NEWS_UPDATE_ERROR = RestResult.code(30002).message("更新新闻失败");
}
