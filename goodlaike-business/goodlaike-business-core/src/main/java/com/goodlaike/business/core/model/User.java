package com.goodlaike.business.core.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 用户类
 * 
 * @author jail
 */
@Alias("user")
public class User implements Serializable {

	private static final long serialVersionUID = 3172237270677192477L;

	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String avator;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 用户状态 0： 正常 9：删除
	 */
	private byte status;
	/**
	 * 公司名
	 */
	private String companyName;
	/**
	 * email
	 */
	private String email;
	/**
	 * 密码盐
	 */
	private String salt;
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
	 * 会员类型
	 */
	private String type;

	/**
	 * 获得 "用户ID",对应属性"userId"
	 * 
	 * @return int
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * 设置 "用户ID",对应属性"userId"
	 *
	 * @param userId
	 *            int
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** 
	 * 获得 "密码",对应属性"password"
	 *  
	 * @return String 
	*/
	public String getPassword() {
		return this.password;
	}
	

	/** 
	 * 设置 "密码",对应属性"password"
	 *
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	/** 
	 * 获得 "昵称",对应属性"nickname"
	 *  
	 * @return String 
	*/
	public String getNickname() {
		return this.nickname;
	}
	

	/** 
	 * 设置 "昵称",对应属性"nickname"
	 *
	 * @param nickname String
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	

	/** 
	 * 获得 "头像",对应属性"avator"
	 *  
	 * @return String 
	*/
	public String getAvator() {
		return this.avator;
	}
	

	/** 
	 * 设置 "头像",对应属性"avator"
	 *
	 * @param avator String
	 */
	public void setAvator(String avator) {
		this.avator = avator;
	}
	

	/** 
	 * 获得 "手机号码",对应属性"mobilePhone"
	 *  
	 * @return String 
	*/
	public String getMobilePhone() {
		return this.mobilePhone;
	}
	

	/** 
	 * 设置 "手机号码",对应属性"mobilePhone"
	 *
	 * @param mobilePhone String
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	

	/** 
	 * 获得 "用户状态0：正常9：删除",对应属性"status"
	 *  
	 * @return byte 
	*/
	public byte getStatus() {
		return this.status;
	}
	

	/** 
	 * 设置 "用户状态0：正常9：删除",对应属性"status"
	 *
	 * @param status byte
	 */
	public void setStatus(byte status) {
		this.status = status;
	}
	

	/** 
     * 获得 "公司名",对应属性"companyName"
     *  
     * @return String 
    */
    public String getCompanyName() {
        return this.companyName;
    }
    

    /** 
     * 设置 "公司名",对应属性"companyName"
     *
     * @param companyName String
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    

    /** 
     * 获得 "email",对应属性"email"
     *  
     * @return String 
    */
    public String getEmail() {
        return this.email;
    }
    

    /** 
     * 设置 "email",对应属性"email"
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }
    

    /** 
     * 获得 "密码盐",对应属性"salt"
     *  
     * @return String 
    */
    public String getSalt() {
        return this.salt;
    }
    

    /** 
     * 设置 "密码盐",对应属性"salt"
     *
     * @param salt String
     */
    public void setSalt(String salt) {
        this.salt = salt;
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

    /** 
     * 获得 "会员类型",对应属性"type"
     *  
     * @return String 
    */
    public String getType() {
        return this.type;
    }
    

    /** 
     * 设置 "会员类型",对应属性"type"
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /* 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", nickname=" + nickname + ", avator=" + avator
                + ", mobilePhone=" + mobilePhone + ", status=" + status + ", companyName=" + companyName + ", email="
                + email + ", salt=" + salt + ", createUserId=" + createUserId + ", createTime=" + createTime
                + ", updateUserId=" + updateUserId + ", updateTime=" + updateTime + ", type=" + type + "]";
    }
}
