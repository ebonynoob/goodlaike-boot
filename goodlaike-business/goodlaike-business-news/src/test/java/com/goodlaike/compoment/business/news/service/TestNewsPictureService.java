package com.goodlaike.compoment.business.news.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlaike.business.news.model.NewsPicture;
import com.goodlaike.business.news.service.NewsPictureService;
import com.goodlaike.compoment.business.news.BaseTest;

/**
 * Test NewsPictureService
 * 
 * @author Jail Hu
 */
public class TestNewsPictureService extends BaseTest {

	@Autowired
	private NewsPictureService newsPictureService;

	@Test
	public void insertNewsPicture() {
		List<NewsPicture> pictureList = new ArrayList<NewsPicture>();
		NewsPicture np1 = new NewsPicture();
		np1.setNewsId(2);
		np1.setPicPath("/aaa/ggdd/ddd.gif");
		np1.setCreateUserId(8);
		np1.setSort(1);
		pictureList.add(np1);
		NewsPicture np2 = new NewsPicture();
		np2.setNewsId(2);
		np2.setPicPath("/adsfadsfads.gif");
		np2.setCreateUserId(8);
		np2.setSort(10);
		pictureList.add(np2);

		newsPictureService.insertPictures(pictureList);
		getNewsPictureList();
	}

	@Test
	public void getNewsPictureList() {
		System.out.println(newsPictureService.getNewsPictureList(2));
	}

	@Test
	public void deletePicture() {
		System.out.println(newsPictureService.deleteNewsPicture(2, 8));
	}

	@Test
	public void deletePicture2() {
		newsPictureService.deleteNewsPicture2(8, 8);
	}
}
