package com.goodlaike.framework.tools.utils.aliyun;

/**
 * 短信类型
 * 
 * @author Jail Hu
 *
 */
public interface ISmsType {

	/**
	 * 获得短信模本 code
	 */
	public String getTemplateCode();

	/**
	 * 获得参数列表
	 */
	public String[] getParameters();
}
