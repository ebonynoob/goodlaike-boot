package com.goodlaike.compoment.business.news.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlaike.business.news.config.NewsConfig;
import com.goodlaike.compoment.business.news.BaseTest;

/**
 * Test NewsService
 * 
 * @author Jail Hu
 */
public class TestNewsConfig extends BaseTest {

	@Autowired
	private NewsConfig newsConfig;

	@Test
	public void test() {
		System.out.println(newsConfig.getNewsTypeList());
	}
}
