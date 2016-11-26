package com.goodlaike.tools.utils;

import org.junit.Test;

import com.goodlaike.framework.tools.utils.EmailUtil;

/**
 * EmailUtil test
 * @author jail
 */
public class TestEmailUtil {

    @Test
    public void isEmail(){
        System.out.println(EmailUtil.isEmail("sdf@sdf.net1"));
    }
}
