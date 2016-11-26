package com.goodlaike.business.news.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 新闻实体
 * 
 * @author Jail Hu
 */
@Alias("news")
public class News implements Serializable {

    private static final long serialVersionUID = -3183096120638132183L;

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
    private String content;
    /**
     * 新闻类型 - String型
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
     * @param id
     *            int
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
     * @param title
     *            String
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
     * @param subtitle
     *            String
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获得 "内容",对应属性"content"
     * 
     * @return String
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 "内容",对应属性"content"
     *
     * @param content
     *            String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获得 "新闻类型-String型",对应属性"type"
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置 "新闻类型-String型",对应属性"type"
     *
     * @param type
     *            String
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
     * @param link
     *            String
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
     * @param createUserId
     *            int
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
     * @param createTime
     *            Date
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
     * @param updateUserId
     *            int
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
     * @param updateTime
     *            Date
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", content=" + content + ", type="
                + type + ", link=" + link + ", createUserId=" + createUserId + ", createTime=" + createTime
                + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime + "]";
    }
}
