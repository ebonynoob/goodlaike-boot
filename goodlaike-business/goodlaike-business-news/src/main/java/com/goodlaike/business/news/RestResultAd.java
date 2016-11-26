package com.goodlaike.business.news;

import com.goodlaike.business.core.support.RestResult;

/**
 * 广告业务异常类
 * 
 * @author jail
 */
public class RestResultAd extends RestResult {

    private static final long serialVersionUID = -6967843242292818486L;

    /**
     * 删除广告失败 <br>
     * 40001
     */
    public static final RestResult AD_DELETE_ERROR = RestResult.code(40001).message("删除广告失败");

    /**
     * 更新广告失败 <br>
     * 40002
     */
    public static final RestResult AD_UPDATE_ERROR = RestResult.code(40002).message("更新广告失败");
}
