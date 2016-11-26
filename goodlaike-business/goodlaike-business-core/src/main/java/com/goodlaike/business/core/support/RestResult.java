package com.goodlaike.business.core.support;

import java.util.HashMap;

import com.goodlaike.framework.dao.utils.TextUtil;


/**
 * 异常结果
 * 
 * @author jail
 */
public class RestResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 8961366881224000523L;

	public static final RestResult UNAUTHORIZED = RestResult.code(401).message("未登录");

	public static final RestResult NOTFOUND = RestResult.code(404).message("资源不存在");

	private final static String MESSAGE = "message";
	private final static String CODE = "code";

	/**
	 * 构建 RestResult, 可用成对的 params 直接填充 result <br>
	 * 因为 restResult 为 <String,Object> 结构，所以 params 奇数位需要为 String
	 * 
	 * @param params
	 *            成对的填充值
	 * @return
	 * @author Jail Hu
	 */
	public static RestResult build(Object... params) {
		RestResult result = new RestResult();
		if (params.length % 2 == 0) {
			for (int i = 0; i < params.length; i = i + 2) {
				if (!(params[i] instanceof String)) {
					throw new RuntimeException("成对填充值奇数位必须为 String");
				} else {
					result.putIfAbsent((String) params[i], params[i + 1]);
				}
			}
		}
		return result;
	}

	/**
	 * 根据code创建 restResult
	 * 
	 * @param code
	 *            业务编码
	 * @return restResult
	 * @author Jail Hu
	 */
	public static RestResult code(int code) {
		RestResult result = new RestResult();
		result.putIfAbsent(RestResult.CODE, code);
		return result;
	}

	/**
	 * 填充message，单次，如果已存在则不填充
	 * 
	 * @param message
	 * @return
	 * @author Jail Hu
	 */
	public RestResult message(String message) {
		this.putIfAbsent(RestResult.MESSAGE, message);
		return this;
	}

	/**
	 * 获得 message
	 * 
	 * @return String
	 * @author Jail Hu
	 */
	public String getMessage() {
		return (String) this.get(RestResult.MESSAGE);
	}

	/**
	 * 获得 code
	 * 
	 * @return int
	 * @author Jail Hu
	 */
	public int getCode() {
		return (int) this.get(RestResult.CODE);
	}

	/**
	 * 不让外部实例化了
	 */
	protected RestResult() {
		super();
	}

	protected RestResult(int code, String message) {
		super();
		this.put(RestResult.CODE, code);
		this.put(RestResult.MESSAGE, message);
	}

	/**
	 * 对预设的message模板重新构建
	 * 
	 * @param params
	 *            对应 message 模板中的 {?}
	 * @return RestResult
	 * @author Jail Hu
	 */
	public RestResult rebuild(Object... params) {
		RestResult result = this.clone();
		result.replace(RestResult.MESSAGE, TextUtil.format((String) result.get(RestResult.MESSAGE), params));
		return result;
	}

	/*
	 * @see java.util.HashMap#clone()
	 */
	@Override
	public RestResult clone() {
		return (RestResult) super.clone();
	}
}
