package com.goodlaike.business.core.service.vericode;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.aliyuncs.exceptions.ClientException;
import com.goodlaike.business.core.BaseTest;

public class VeriCodeServiceTest extends BaseTest {

  @Autowired
  private VeriCodeService veriCodeService;

  @Test
  @Rollback(false)
  public void testSendVeriCode() {
    try {
      veriCodeService.sendVeriCode("18621807761");
    } catch (ClientException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
