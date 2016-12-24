package com.goodlaike.business.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.exceptions.ClientException;
import com.goodlaike.business.core.RestResultCore;
import com.goodlaike.business.core.helper.LoginHelper;
import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.service.user.UserService;
import com.goodlaike.business.core.service.vericode.VeriCodeService;
import com.goodlaike.business.core.support.AssertGoodlaike;
import com.goodlaike.business.core.support.RestResult;
import com.goodlaike.framework.tools.utils.PhoneUtil;

/**
 * 用户 拦截器
 * 
 * @author jail
 */
@RestController
@RequestMapping("/business/user")
public class UserController extends BaseRestController {

  @Autowired
  private UserService userService;

  @Autowired
  private VeriCodeService veriCodeService;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 新增用户
   * 
   * @param user 用户对象 用到属性如下 <br>
   *        nickname 昵称<br>
   *        mobilePhone 手机号码<br>
   *        avator 头像地址 <br>
   *        password 密码明码<br>
   *        companyName 公司名 <br>
   *        email 邮箱
   * @param request
   * @return ResponseEntity
   * @author jail
   */
  @RequestMapping(value = "register", method = RequestMethod.POST)
  protected ResponseEntity<?> register(@ModelAttribute User user, HttpServletRequest request) {
    try {
      int userId = userService.insertUserAndReturnId(user);
      LoginHelper.setLoginUser(request, this.userService.getUser(userId));
      return ResponseEntity.ok(userId);
    } catch (RuntimeException e) {
      throw e;
    }
  }

  /**
   * 新增用户（使用验证码）
   * 
   * @param user 用户对象 用到属性如下 <br>
   *        nickname 昵称<br>
   *        mobilePhone 手机号码<br>
   *        avator 头像地址 <br>
   *        password 密码明码<br>
   *        companyName 公司名 <br>
   *        email 邮箱
   * @param veriCode 手机验证码
   * @param request
   * @return
   */
  @RequestMapping(value = "registerWithVC", method = RequestMethod.POST)
  protected ResponseEntity<?> registerWithVC(@ModelAttribute User user, @RequestParam(value = "veriCode") String veriCode,
      HttpServletRequest request) {
    try {
      int userCode = userService.insertUserAndReturnId(user, veriCode);
      LoginHelper.setLoginUser(request, this.userService.getUser(userCode));
      return ResponseEntity.ok(userCode);
    } catch (RuntimeException e) {
      throw e;
    }
  }

  /**
   * 根据id获得用户
   * 
   * @param userId 用户id
   * @return ResponseEntity
   * @author jail
   */
  @RequestMapping(value = "{userId}", method = RequestMethod.GET)
  protected ResponseEntity<?> getUser(@PathVariable int userId) {
    User user;
    return (user = userService.getUser(userId)) == null ? super.notFound(RestResult.NOTFOUND) : ResponseEntity.ok(user);
  }

  /**
   * 修改 自己 的用户信息
   * 
   * @param nickname 用户昵称
   * @param mobilePhone 手机号码
   * @param avator 用户头像
   * @param companyName 公司名
   * @param email 邮箱
   * @param request
   * @return
   * @author Jail Hu
   */
  @RequestMapping(value = "changeMyInfo", method = RequestMethod.PUT)
  protected ResponseEntity<?> updateMyUserInfo(@RequestParam(value = "nickname", required = false) String nickname,
      @RequestParam(value = "mobilePhone", required = false) String mobilePhone, @RequestParam(value = "avator", required = false) String avator,
      @RequestParam(value = "companyName", required = false) String companyName, @RequestParam(value = "email", required = false) String email,
      HttpServletRequest request) {
    User user = LoginHelper.getLoginUser(request);
    AssertGoodlaike.needLogin(user);
    if (userService.updateUser(nickname, mobilePhone, avator, companyName, email, user.getUserId(), user.getUserId())) {
      // 更新 session
      LoginHelper.setLoginUser(request, this.userService.getUser(user.getUserId()));
      return ResponseEntity.ok().build();
    } else {
      return super.serverError(RestResultCore.USER_UPDATE_ERROR);
    }
  }

  /**
   * 修改 用户息息
   * 
   * @param userId 被修改的用户ID
   * @param nickname 用户昵称
   * @param mobilePhone 手机号码
   * @param companyName 公司名
   * @param email 邮箱
   * @param avator 用户头像
   * @param request
   * @return
   * @author Jail Hu
   */
  @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
  protected ResponseEntity<?> updateUser(@PathVariable int userId, @RequestParam(value = "nickname", required = false) String nickname,
      @RequestParam(value = "mobilePhone", required = false) String mobilePhone, @RequestParam(value = "avator", required = false) String avator,
      @RequestParam(value = "companyName", required = false) String companyName, @RequestParam(value = "email", required = false) String email,
      HttpServletRequest request) {
    Assert.isTrue(userId > 0, "用户ID非法");
    User user = LoginHelper.getLoginUser(request);
    AssertGoodlaike.needLogin(user);
    if (userService.updateUser(nickname, mobilePhone, avator, companyName, email, userId, user.getUserId())) {
      if (userId == user.getUserId()) {
        // 更新 session
        LoginHelper.setLoginUser(request, this.userService.getUser(user.getUserId()));
      }
      return ResponseEntity.ok().build();
    } else {
      return super.serverError(RestResultCore.USER_UPDATE_ERROR);
    }
  }

  /**
   * 获得登录的用户 ，需要登录
   * 
   * @param request
   * @return
   * @author jail
   */
  @RequestMapping(value = "session", method = RequestMethod.GET)
  protected ResponseEntity<?> getLoginUser(HttpServletRequest request) {
    User user = LoginHelper.getLoginUser(request);
    AssertGoodlaike.needLogin(user);
    return ResponseEntity.ok(user);
  }

  /**
   * 修改用户密码
   * 
   * @param userId 用户ID
   * @param request
   * @param oldPassword 原始密码
   * @param newPassword 新密码
   * @author jail
   */
  @RequestMapping(value = "{userId}/changePassword", method = RequestMethod.PUT)
  protected ResponseEntity<?> changePassword(@PathVariable int userId, HttpServletRequest request,
      @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
    User user = LoginHelper.getLoginUser(request);
    AssertGoodlaike.needLogin(user);
    userService.changePassword(userId, oldPassword, newPassword, user.getUserId());
    return ResponseEntity.ok().build();
  }

  /**
   * 根据ID删除用户
   * 
   * @param userId 用户ID
   * @param request
   * @return ResponseEntity
   * @author jail
   */
  @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
  protected ResponseEntity<?> deleteUser(@PathVariable int userId, HttpServletRequest request) {
    User user = LoginHelper.getLoginUser(request);
    AssertGoodlaike.needLogin(user);
    userService.deleteUser(userId, user.getUserId());
    return ResponseEntity.ok().build();
  }

  /**
   * 根据userId与密码登录
   * 
   * @param userId 用户id
   * @param password 密码明码
   * @return ResponseEntity
   * @author jail
   */
  @RequestMapping(value = "login2", method = RequestMethod.POST)
  protected ResponseEntity<?> login(@RequestParam(value = "userId") int userId, @RequestParam(value = "password") String password,
      HttpServletRequest request) {
    User user = userService.login(userId, password);
    if (user == null) {
      return super.notFound(RestResult.NOTFOUND);
    } else {
      LoginHelper.setLoginUser(request, user);
      return ResponseEntity.ok(user);
    }
  }

  /**
   * 根据手机号码与密码登录
   * 
   * @param mobilePhone 手机号码
   * @param password 密码
   * @param loginType 登录类型
   * @param request
   * @return
   * @author Jail Hu
   */
  @RequestMapping(value = "login", method = RequestMethod.POST)
  protected ResponseEntity<?> login(@RequestParam(value = "mobilePhone", required = true) String mobilePhone,
      @RequestParam(value = "password") String password, @RequestParam(value = "loginType", required = false) String loginType,
      HttpServletRequest request) {
    Assert.isTrue(PhoneUtil.isMobile(mobilePhone), "手机号码非法");
    User user = userService.login(mobilePhone, password);
    if (user == null) {
      return super.notFound(RestResult.NOTFOUND);
    } else {
      LoginHelper.setLoginUser(request, user);
      return ResponseEntity.ok(user);
    }
  }

  /**
   * 退出登录
   * 
   * @param request
   * @return
   * @author jail
   */
  @RequestMapping(value = "logout", method = RequestMethod.POST)
  protected ResponseEntity<?> logout(HttpServletRequest request) {
    LoginHelper.removeLoginUser(request);
    return ResponseEntity.ok().build();
  }

  /**
   * 检查手机号码是否已存在
   * 
   * @param mobilePhone 手机号码
   * @return ResponseEntity<Boolean>
   * @author jail
   */
  @RequestMapping(value = "checkMobilePhoneExist", method = RequestMethod.GET)
  protected ResponseEntity<Boolean> checkMobilePhoneExist(@RequestParam(value = "mobilePhone") String mobilePhone) {
    return ResponseEntity.ok(userService.isExist(mobilePhone));
  }

  /**
   * 检查邮箱是否已存在
   * 
   * @param email 邮箱
   * @return ResponseEntity<Boolean>
   * @author jail
   */
  @RequestMapping(value = "checkEmailExist", method = RequestMethod.GET)
  protected ResponseEntity<Boolean> checkEmailExist(@RequestParam(value = "email") String email) {
    return ResponseEntity.ok(userService.isEmailExist(email));
  }

  /**
   * 发送验证码
   * 
   * @param mobilePhone
   * @return
   */
  @RequestMapping(value = "sendVeriCode", method = RequestMethod.POST)
  protected ResponseEntity<?> sendVeriCode(@RequestParam(value = "mobilePhone") String mobilePhone) {
    try {
      veriCodeService.sendVeriCode(mobilePhone);
      return ResponseEntity.ok().build();
    } catch (ClientException e) {
      logger.error("===>> 手机号码【{}】验证码发送异常，ex：【{}】", mobilePhone, e.getMessage());
      return ResponseEntity.status(500).body("验证码发送异常，请联系管理员；");
    }
  }
}
