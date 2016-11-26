package com.goodlaike.business.core.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 验证码
 * 
 * @author Jail Hu
 *
 */
@Alias("veriCode")
public class VeriCode implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private long id;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 验证码
	 */
	private String veriCode;
	/**
	 * 验证码类型， 0 ： 注册
	 */
	private byte type;
	/**
	 * 验证码状态 0：有效 1：已使用
	 */
	private byte status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * @return the 主键ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the 手机号码
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the 验证码
	 */
	public String getVeriCode() {
		return veriCode;
	}

	/**
	 * @param veriCode
	 *            the veriCode to set
	 */
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	/**
	 * @return the 验证码类型， 0 ： 注册
	 */
	public byte getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * @return the 验证码状态 0：有效 1：已使用
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * @return the 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VeriCode [id=" + id + ", mobilePhone=" + mobilePhone + ", veriCode=" + veriCode + ", type=" + type
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	/**
	 * 验证码是否过期
	 * @return
	 */
	public boolean isExpired() {
		return this.getCreateTime() == null ? true
				: (System.currentTimeMillis() - this.getCreateTime().getTime() > 30 * 60 * 1000);
	}

	/**
	 * 验证码类型
	 * 
	 * @author Jail Hu
	 *
	 */
	public static enum VeriCodeType {
		REGISTER((byte) 0);

		private byte index;

		private VeriCodeType() {
			// TODO Auto-generated constructor stub
		}

		private VeriCodeType(byte index) {
			this.setIndex(index);
		}

		public byte getIndex() {
			return index;
		}

		public void setIndex(byte index) {
			this.index = index;
		}
	}
}
