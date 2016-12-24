package com.goodlaike.business.core.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlaike.business.core.BaseTest;
import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.service.user.UserService;

/**
 * 
 * Test userService
 * 
 * @author jail
 */
public class TestUserService extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertUserAndReturnId() {
        User user = new User();
        user.setNickname("胡杰");
        user.setAvator("/photos/hello.gif");
        user.setPassword("123456");
        user.setMobilePhone("18621807762");
        user.setCreateUserId(8);
        user.setCompanyName("中华人民共和国");
        user.setEmail("jlclove@126.com");
        Assert.assertTrue(userService.insertUserAndReturnId(user) > 0);
    }

    @Test
    public void login() {
        Assert.assertNotNull(userService.login(8, "123456"));
    }

    @Test
    public void login2() {
        Assert.assertNotNull(userService.login("18621807761", "123456"));
    }
    
    @Test
    public void getUser() {
        Assert.assertNotNull(userService.getUser(8));
    }

    @Test
    public void deleteUser() {
        Assert.assertNotNull(userService.deleteUser(8, 8));
    }

    @Test
    public void changePassword() {
        Assert.assertNotNull(userService.changePassword(8, "123456", "654321", 8));
    }
}
