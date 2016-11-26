package com.goodlaike.business.news.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻图片
 * 
 * @author Jail Hu
 */
public class NewsPicture implements Serializable {

	private static final long serialVersionUID = -405613242867524950L;
	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 新闻ID
	 */
	private int newsId;
	/**
	 * 图片地址，相对路径
	 */
	private String picPath;
	/**
	 * 图片排序 ， 数值越大越靠后
	 */
	private int sort;
	/**
	 * 是否主图
	 */
	private boolean main;
	/**
	 * 图片状态 0：正常 9：删除
	 */
	private byte status;
	/**
	 * 创建人id
	 */
	private int createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人id
	 */
	private int updateUserId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/** 
	 * 获得 "主键ID",对应属性"id"
	 *  
	 * @return int 
	 */
	public int getId() {
		return this.id;
	}
	/** 
	 * 设置 "主键ID",对应属性"id"
	 *
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** 
	 * 获得 "新闻ID",对应属性"newsId"
	 *  
	 * @return int 
	 */
	public int getNewsId() {
		return this.newsId;
	}
	/** 
	 * 设置 "新闻ID",对应属性"newsId"
	 *
	 * @param newsId int
	 */
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	/** 
	 * 获得 "图片地址，相对路径",对应属性"picPath"
	 *  
	 * @return String 
	 */
	public String getPicPath() {
		return this.picPath;
	}
	/** 
	 * 设置 "图片地址，相对路径",对应属性"picPath"
	 *
	 * @param picPath String
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	/** 
	 * 获得 "图片排序，数值越大越靠后",对应属性"sort"
	 *  
	 * @return int 
	 */
	public int getSort() {
		return this.sort;
	}
	/** 
	 * 设置 "图片排序，数值越大越靠后",对应属性"sort"
	 *
	 * @param sort int
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/** 
	 * 获得 "是否主图",对应属性"main"
	 *  
	 * @return boolean 
	*/
	public boolean isMain() {
		return this.main;
	}
	
	/** 
	 * 设置 "是否主图",对应属性"main"
	 *
	 * @param main boolean
	 */
	public void setMain(boolean main) {
		this.main = main;
	}
	
	/** 
	 * 获得 "图片状态0：正常9：删除",对应属性"status"
	 *  
	 * @return byte 
	 */
	public byte getStatus() {
		return this.status;
	}
	/** 
	 * 设置 "图片状态0：正常9：删除",对应属性"status"
	 *
	 * @param status byte
	 */
	public void setStatus(byte status) {
		this.status = status;
	}
	/** 
	 * 获得 "创建人id",对应属性"createUserId"
	 *  
	 * @return int 
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}
	/** 
	 * 设置 "创建人id",对应属性"createUserId"
	 *
	 * @param createUserId int
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	/** 
	 * 获得 "创建时间",对应属性"createTime"
	 *  
	 * @return Date 
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/** 
	 * 设置 "创建时间",对应属性"createTime"
	 *
	 * @param createTime Date
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 
	 * 获得 "更新人id",对应属性"updateUserId"
	 *  
	 * @return int 
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}
	/** 
	 * 设置 "更新人id",对应属性"updateUserId"
	 *
	 * @param updateUserId int
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}
	/** 
	 * 获得 "更新时间",对应属性"updateTime"
	 *  
	 * @return Date 
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}
	/** 
	 * 设置 "更新时间",对应属性"updateTime"
	 *
	 * @param updateTime Date
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsPicture [id=" + id + ", newsId=" + newsId + ", picPath=" + picPath + ", sort=" + sort + ", main="
		        + main + ", status=" + status + ", createUserId=" + createUserId + ", createTime=" + createTime
		        + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime + "]";
	}
}
