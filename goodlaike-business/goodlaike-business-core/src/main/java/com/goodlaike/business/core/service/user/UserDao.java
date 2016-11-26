package com.goodlaike.business.core.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.service.dao.LocalRWDao;
import com.goodlaike.framework.tools.utils.RandomUtil;

/**
 * User Dao
 * 
 * @author jail
 */
@Repository
@Lazy(value = true)
class UserDao extends LocalRWDao<User> {

	/**
	 * 根据用户ID 获得用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return User
	 * @author jail
	 */
	public User findUser(int userId) {
		return super.selectOne(userId);
	}

	/**
	 * 根据用户ID与密码获得用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param password
	 *            密码明码
	 * @return User
	 * @author jail
	 */
	public User findUser(int userId, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		return super.selectOne("findByUserIdAndPassword", map);
	}

	/**
	 * 根据手机号码与密码获得用户
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param password
	 *            密码明码
	 * @return User
	 * @author jail
	 */
	public User findUser(String mobilePhone, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("mobilePhone", mobilePhone);
		map.put("password", password);
		return super.selectOne("findByMobilePhoneAndPassword", map);
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户对象 用到属性如下 <br>
	 *            nickname 昵称 <br>
	 *            mobilePhone 手机号码 <br>
	 *            avator 头像地址 <br>
	 *            companyName 公司名<br>
	 *            email 邮箱<br>
	 *            password 密码明码 <br>
	 *            createUserId 创建人ID
	 * @return int
	 * @author jail
	 */
	public int insertUserAndReturnId(User user) {
		Assert.hasText(user.getPassword(), "密码不能为空");
		user.setSalt(RandomUtil.getUUID());
		super.insertAndReturnId(user);
		return user.getUserId();
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param operateUserId
	 *            操作人ID
	 * @return boolean
	 * @author jail
	 */
	public boolean delete(int userId, int operateUserId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("operateUserId", operateUserId);
		return super.delete(map);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param password
	 *            密码明码
	 * @param operateUserId
	 *            操作人ID
	 * @return Boolean
	 * @author jail
	 */
	public boolean changePassword(int userId, String password, int operateUserId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("operateUserId", operateUserId);
		return super.update("changePassword", map);
	}

	/**
	 * 检查手机号码是否存在
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @return boolean
	 * @author Jail Hu
	 */
	public boolean isExist(String mobilePhone) {
		return this.isExist(mobilePhone, 0);
	}

	/**
	 * 检查手机号码是否存在
	 * 
	 * @param mobilePhone
	 *            手机号码
	 * @param userId
	 *            需要排除的用户id
	 * @return boolean
	 * @author Jail Hu
	 */
	public boolean isExist(String mobilePhone, int userId) {
		HashMap<String, Object> map = new HashMap<>(2);
		map.put("mobilePhone", mobilePhone);
		return (int) super.getSqlSession().selectOne("isMobilePhoneExist", map) > 0;
	}

	/**
	 * 修改用户对象
	 * 
	 * @param user
	 *            用户对象，需要用到的字段如下<br>
	 *            userId 用户id<br>
	 *            nickName 用户昵称<br>
	 *            mobilePhone 用户手机号码<br>
	 *            companyName 公司名<br>
	 *            email 邮箱<br>
	 *            updateUserId 修改人用户id<br>
	 *            avator 用户头像地址
	 * @return boolean
	 */
	public boolean update(String nickname, String mobilePhone, String avator, String companyName, String email,
			int userId, int operateUserId) {
		Map<String, Object> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("mobilePhone", mobilePhone);
		map.put("avator", avator);
		map.put("companyName", companyName);
		map.put("email", email);
		map.put("userId", userId);
		map.put("updateUserId", operateUserId);
		return super.update("update", map);
	}

	/**
	 * 检查邮箱是否存在
	 * 
	 * @param email
	 *            邮箱
	 * @return boolean
	 * @author Jail Hu
	 */
	public boolean isEmailExist(String email) {
		return this.isEmailExist(email, 0);
	}

	/**
	 * 检查邮箱是否存在
	 * 
	 * @param email
	 *            邮箱
	 * @param userId
	 *            需要排除的用户id
	 * @return boolean
	 * @author Jail Hu
	 */
	public boolean isEmailExist(String email, int userId) {
		HashMap<String, Object> map = new HashMap<>(2);
		map.put("email", email);
		return (int) super.getSqlSession().selectOne("isEmailExist", map) > 0;
	}
}
