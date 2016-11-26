package com.goodlaike.business.news.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * @author jail
 */
@Alias("ad")
public class Ad implements Serializable {

    private static final long serialVersionUID = -5905808035199677225L;

    /**
     * 主键
     */
    private int id;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 内容
     */
    private String picPath;
    /**
     * 广告类型 - String型
     */
    private String type;
    /**
     * 链接地址
     */
    private String link;
    /**
     * 创建人账号
     */
    private int createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人账号
     */
    private int updateUserId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /** 
     * 获得 "主键",对应属性"id"
     *  
     * @return int 
    */
    public int getId() {
        return this.id;
    }
    
    /** 
     * 设置 "主键",对应属性"id"
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** 
     * 获得 "标题",对应属性"title"
     *  
     * @return String 
    */
    public String getTitle() {
        return this.title;
    }
    
    /** 
     * 设置 "标题",对应属性"title"
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /** 
     * 获得 "副标题",对应属性"subtitle"
     *  
     * @return String 
    */
    public String getSubtitle() {
        return this.subtitle;
    }
    
    /** 
     * 设置 "副标题",对应属性"subtitle"
     *
     * @param subtitle String
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    /** 
     * 获得 "内容",对应属性"picPath"
     *  
     * @return String 
    */
    public String getPicPath() {
        return this.picPath;
    }
    
    /** 
     * 设置 "内容",对应属性"picPath"
     *
     * @param picPath String
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    
    /** 
     * 获得 "广告类型-String型",对应属性"type"
     *  
     * @return String 
    */
    public String getType() {
        return this.type;
    }
    
    /** 
     * 设置 "广告类型-String型",对应属性"type"
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /** 
     * 获得 "链接地址",对应属性"link"
     *  
     * @return String 
    */
    public String getLink() {
        return this.link;
    }
    
    /** 
     * 设置 "链接地址",对应属性"link"
     *
     * @param link String
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /** 
     * 获得 "创建人账号",对应属性"createUserId"
     *  
     * @return int 
    */
    public int getCreateUserId() {
        return this.createUserId;
    }
    
    /** 
     * 设置 "创建人账号",对应属性"createUserId"
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
     * 获得 "更新人账号",对应属性"updateUserId"
     *  
     * @return int 
    */
    public int getUpdateUserId() {
        return this.updateUserId;
    }
    
    /** 
     * 设置 "更新人账号",对应属性"updateUserId"
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
        return "Ad [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", picPath=" + picPath + ", type="
                + type + ", link=" + link + ", createUserId=" + createUserId + ", createTime=" + createTime
                + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime + "]";
    }
}
