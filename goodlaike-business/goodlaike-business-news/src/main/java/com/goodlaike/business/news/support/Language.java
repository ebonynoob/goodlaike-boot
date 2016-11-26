package com.goodlaike.business.news.support;

/**
 * 数据语言
 * 
 * @author Jail Hu
 */
public enum Language {
	/**
	 * 简体中文
	 */
	CN,
	/**
	 * 英文
	 */
	EN;
	
	/**
	 * 获得表后缀
	 * @return String
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月21日下午6:03:59
	 * @updator Jail Hu
	 * @updateTime 2015年8月21日下午6:03:59
	 */
	public String toSuffix(){
		switch (this) {
		case CN:
			return "_cn";
		case EN:
			return "_en";
		default:
			return "_cn";
		}
	}
}
