package com.goodlaike.business.news.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻扩展类
 * @author Jail Hu
 */
@Alias("vNews")
public class VNews extends News{

	private static final long serialVersionUID = 8843751912414220863L;

	/**
	 * 新闻主图
	 */
	private String mainPicPath;

	/** 
	 * 获得 "新闻主图",对应属性"mainPicPath"
	 *  
	 * @return String 
	*/
	public String getMainPicPath() {
		return this.mainPicPath;
	}
	

	/** 
	 * 设置 "新闻主图",对应属性"mainPicPath"
	 *
	 * @param mainPicPath String
	 */
	public void setMainPicPath(String mainPicPath) {
		this.mainPicPath = mainPicPath;
	}


	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VNews [mainPicPath=" + mainPicPath + "]";
	}
	
}
