package com.goodlaike.business.fileupload;

import com.goodlaike.business.core.support.RestResult;

/**
 * 上传业务异常类
 * 
 * @author jail
 */
public class RestResultFileupload extends RestResult {

	private static final long serialVersionUID = 9124381156895602336L;

	/**
	 * 保存图片异常<br>
	 * 20005
	 */
	public static final RestResult FILE_SAVE_ERROR = RestResult.code(20005).message("保存图片异常");
}
