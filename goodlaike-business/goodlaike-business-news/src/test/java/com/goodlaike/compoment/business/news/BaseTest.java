package com.goodlaike.compoment.business.news;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础测试
 * 
 * @author Jail Hu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:news-applicationContext.xml" })
@Rollback
@Transactional
public class BaseTest {

}
