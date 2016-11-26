package com.goodlaike.business.core.service.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.model.VeriCode;
import com.goodlaike.business.core.model.VeriCode.VeriCodeType;
import com.goodlaike.business.core.service.vericode.VeriCodeService;
import com.goodlaike.framework.tools.utils.EmailUtil;
import com.goodlaike.framework.tools.utils.PhoneUtil;

/**
 * 
 * @author jail
 */
@Service
@Lazy(true)
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VeriCodeService veriCodeService;

	/**
	 * 用户登录登录并获得用户<br>
	 * 根据 userId 与密码登录
	 * 
	 * @param userId
	 *            用户ID
	 * @param password
	 *            密码明码
	 * @return User
	 * @author jail
	 */
	public User login(int userId, String password) {
		return userDao.findUser(userId, password);
	}

	/**
	 * 用户登录登录并获得用户<br>
	 * 根据手机号码与密码登录
	 * 
	 * @param userId
	 *            用户ID
	 * @param password
	 *            密码明码
	 * @return User
	 * @author jail
	 */
	public User login(String mobilePhone, String password) {
		return userDao.findUser(mobilePhone, password);
	}

	/**
	 * 根据用户ID与密码获得用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param password
	 *            密码明码
	 * @return User
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月30日下午9:49:21
	 * @updator jail
	 * @updateTime 2015年8月30日下午9:49:21
	 */
	public User getUser(int userId, String password) {
		return userDao.findUser(userId, password);
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户对象 用到属性如下 <br>
	 *            nickname 昵称<br>
	 *            mobilePhone 手机号码<br>
	 *            avator 头像地址 <br>
	 *            password 密码明码<br>
	 *            createUserId 创建人ID
	 * @return int
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月30日下午9:02:56
	 * @updator jail
	 * @updateTime 2015年8月30日下午9:02:56
	 */
	public int insertUserAndReturnId(User user) {
		Assert.hasText(user.getPassword(), "密码不能为空");
		Assert.isTrue(PhoneUtil.isMobile(user.getMobilePhone()), "手机号码非法");
		Assert.state(!userDao.isExist(user.getMobilePhone()), "手机号码重复");
		return userDao.insertUserAndReturnId(user);
	}

	/**
	 * 新增用户（使用验证码）
	 * 
	 * @param user
	 *            用户对象 用到属性如下 <br>
	 *            nickname 昵称<br>
	 *            mobilePhone 手机号码<br>
	 *            avator 头像地址 <br>
	 *            password 密码明码<br>
	 *            companyName 公司名 <br>
	 *            email 邮箱
	 * @param veriCode
	 *            手机验证码
	 * @param request
	 * @return
	 */
	public int insertUserAndReturnId(User user, String veriCode) {
		VeriCode vc = this.veriCodeService.findVeriCode(user.getMobilePhone(), VeriCodeType.REGISTER);
		if (vc == null || vc.isExpired()) {
			throw new RuntimeException("验证码不存在或已过期【30分钟】");
		} else {
			if (!this.veriCodeService.updateVeriCode(user.getMobilePhone(), VeriCodeType.REGISTER, veriCode)) {
				throw new RuntimeException("使用验证码失败，请联系管理员");
			}
			return this.insertUserAndReturnId(user);
		}
	}

	/**
	 * 根据用户ID获得用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return User
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月30日下午9:03:19
	 * @updator jail
	 * @updateTime 2015年8月30日下午9:03:19
	 */
	public User getUser(int userId) {
		return userDao.findUser(userId);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param oldPassword
	 *            原始密码
	 * @param newPassword
	 *            新密码
	 * @param operateUserId
	 *            操作人ID
	 * @return Boolean
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月30日下午10:02:36
	 * @updator jail
	 * @updateTime 2015年8月30日下午10:02:36
	 */
	public boolean changePassword(int userId, String oldPassword, String newPassword, int operateUserId) {
		Assert.state(this.getUser(userId, oldPassword) != null, "原始密码不符");
		return userDao.changePassword(userId, newPassword, operateUserId);
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param operateUserId
	 *            操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月30日下午9:39:40
	 * @updator jail
	 * @updateTime 2015年8月30日下午9:39:40
	 */
	public boolean deleteUser(int userId, int operateUserId) {
		return userDao.delete(userId, operateUserId);
	}

	/**
	 * 修改用户
	 * 
	 * @param nickname
	 *            用户昵称
	 * @param mobilePhone
	 *            手机号码
	 * @param avator
	 *            头像地址
	 * @param companyName
	 *            公司名
	 * @param email
	 *            邮箱
	 * @param userId
	 *            用户id
	 * @param operateUserId
	 *            操作人用户id
	 * @return boolean
	 * @author Jail Hu
	 */
	public boolean updateUser(String nickname, String mobilePhone, String avator, String companyName, String email,
			int userId, int operateUserId) {
		Assert.isTrue(userId > 0, "用户id非法");
		if (StringUtils.isNotBlank(mobilePhone)) {
			assertUpdateMobile(mobilePhone, userId);
		}
		if (StringUtils.isNotBlank(email)) {
			assertUpdateEmail(email, userId);
		}
		return this.userDao.update(nickname, mobilePhone, avator, companyName, email, userId, operateUserId);
	}

	/**
	 * 更新方法手机号码断言
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param userId
	 *            排除的工号
	 * @author jail
	 */
	private void assertUpdateMobile(String mobilePhone, int userId) {
		Assert.isTrue(PhoneUtil.isMobile(mobilePhone), "手机号码非法");
		Assert.state(!userDao.isExist(mobilePhone, userId), "手机号码重复");
	}

	/**
	 * 更新方法邮件断言
	 * 
	 * @param email
	 *            邮件
	 * @param userId
	 *            排除的工号
	 * @author jail
	 */
	private void assertUpdateEmail(String email, int userId) {
		if (StringUtils.isNotBlank(email)) {
			Assert.isTrue(EmailUtil.isEmail(email), "邮箱格式非法");
			Assert.state(!userDao.isEmailExist(email, userId), "邮箱重复");
		}
	}

	/**
	 * 检查手机号码是否已存在
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @return boolean
	 * @author jail
	 */
	public boolean isExist(String mobilePhone) {
		return userDao.isExist(mobilePhone);
	}

	/**
	 * 检查邮箱是否已存在
	 * 
	 * @param email
	 *            email
	 * @return boolean
	 * @author jail
	 */
	public boolean isEmailExist(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		Assert.isTrue(EmailUtil.isEmail(email), "邮箱地址非法");
		return userDao.isEmailExist(email);
	}
}
