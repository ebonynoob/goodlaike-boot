package com.goodlaike.business.core.service.vericode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.goodlaike.business.core.model.VeriCode;
import com.goodlaike.business.core.service.dao.LocalRWDao;

/**
 * 验证码Dao
 * 
 * @author jail
 */
@Repository
@Lazy(true)
class VeriCodeDao extends LocalRWDao<VeriCode> {

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
		Map<String, Object> map = new HashMap<>();
		map.put("mobilePhone", mobilePhone);
		map.put("type", type);
		map.put("veriCode", veriCode);
		return super.insert("insertVeriCode", map);
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
	public VeriCode findVeriCode(String mobilePhone, byte type) {
		Map<String, Object> map = new HashMap<>();
		map.put("mobilePhone", mobilePhone);
		map.put("type", type);
		return super.selectOne("findVeriCode", map);
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
	public boolean updateVeriCode(String mobilePhone, byte type, String veriCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("mobilePhone", mobilePhone);
		map.put("type", type);
		map.put("veriCode", veriCode);
		return super.update("updateVeriCode", map);
	}
}
