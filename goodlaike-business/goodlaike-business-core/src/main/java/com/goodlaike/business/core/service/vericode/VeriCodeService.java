package com.goodlaike.business.core.service.vericode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.goodlaike.business.core.model.VeriCode;
import com.goodlaike.business.core.model.VeriCode.VeriCodeType;
import com.goodlaike.framework.tools.utils.aliyun.SmsUtil;
import com.goodlaike.framework.tools.utils.aliyun.SmsUtil.AliSmsType;

@Service
public class VeriCodeService {

	@Autowired
	private VeriCodeDao veriCodeDao;

	/**
	 * 发送注册验证码
	 * 
	 * @param mobilePhone
	 * @throws ClientException
	 */
	public void sendVeriCode(String mobilePhone) throws ClientException {
		// 验证码
		VeriCodeType type = VeriCodeType.REGISTER;
		VeriCode veriCode = this.findVeriCode(mobilePhone, type);
		if (veriCode == null || veriCode.isExpired()) {
			int code = SmsUtil.createVeriCode();
			if (this.insert(mobilePhone, type.getIndex(), String.valueOf(code))) {
				SmsUtil.sendSms(AliSmsType.REGISTER, mobilePhone, String.valueOf(code));
			}
		} else {
			SmsUtil.sendSms(AliSmsType.REGISTER, mobilePhone, veriCode.getVeriCode());
		}
	}

	/**
	 * 新增验证码
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param type
	 *            验证码类型
	 * @param veriCode
	 *            验证码
	 * @return boolean
	 */
	public boolean insert(String mobilePhone, byte type, String veriCode) {
		return this.veriCodeDao.insert(mobilePhone, type, veriCode);
	}

	/**
	 * 获得验证码
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param type
	 *            验证码类型
	 * @return VeriCode
	 */
	public VeriCode findVeriCode(String mobilePhone, VeriCodeType veriCodeType) {
		return this.veriCodeDao.findVeriCode(mobilePhone, veriCodeType.getIndex());
	}

	/**
	 * 使用验证码
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param type
	 *            验证码类型
	 * @param veriCode
	 *            验证码
	 * @return boolean
	 */
	public boolean updateVeriCode(String mobilePhone, VeriCodeType veriCodeType, String veriCode) {
		return this.veriCodeDao.updateVeriCode(mobilePhone, veriCodeType.getIndex(), veriCode);
	}
}
