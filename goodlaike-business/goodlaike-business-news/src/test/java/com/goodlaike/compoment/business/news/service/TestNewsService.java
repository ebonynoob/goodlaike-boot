package com.goodlaike.compoment.business.news.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goodlaike.business.news.model.News;
import com.goodlaike.business.news.service.NewsService;
import com.goodlaike.compoment.business.news.BaseTest;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * Test NewsService
 * 
 * @author Jail Hu
 */
public class TestNewsService extends BaseTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void test() {
        System.out.println("hello world");
    }

    @Test
    public void getPaginationCN() {
        Pagination<News> pagination = new Pagination<>();
        System.out.println(newsService.getPagination(pagination, "cn"));
    }

    @Test
    public void getPaginationEN() {
        Pagination<News> pagination = new Pagination<>();
        System.out.println(newsService.getPagination(pagination, "en"));
    }

    @Test
    public void insertNews() {
        News news = new News();
        news.setTitle("你好1");
        news.setSubtitle("副标题");
        news.setContent("新闻内容");
        news.setType("新闻");
        System.out.println(newsService.insertNews(news));
    }

    @Test
    public void insertNews2() {
        News news = new News();
        news.setTitle("你好2");
        news.setSubtitle("副标题");
        news.setContent("新闻内容");
        news.setType("新闻");
        String pics = "[{\"picPath\":\"111.jpg\",\"sort\":2}," + "{\"picPath\":\"222.jpg\",\"sort\":1},"
                + "{\"picPath\":\"333.jpg\",\"sort\":3}]";
        System.out.println(newsService.insertNews(news, pics));
    }

    @Test
    public void updateNews() {
        Assert.assertTrue(newsService.updateNews(11, "不好2", "", "不想写", "没有", 8, "cn"));
    }

    @Test
    public void updateNews2() {
        String pics = "[{\"picPath\":\"d4444.jpg\",\"sort\":444}," + "{\"picPath\":\"222.jpg\",\"sort\":2},"
                + "{\"picPath\":\"333.jpg\",\"sort\":3}," + "{\"picPath\":\"99999999.jpg\",\"sort\":9}]";
        Assert.assertTrue(newsService.updateNews(11, "也没有不好", "写一下辐条比", "不想写", "没有", 8, "cn", pics));
    }

    @Test
    public void deleteNews() {
        Assert.assertTrue(newsService.deleteNews(2, 8));
    }

    @Test
    public void getNews() {
        Assert.assertNotNull(newsService.getNews(2, "cn"));
        Assert.assertNotNull(newsService.getNews(3, "cn"));
        Assert.assertNotNull(newsService.getNews(2, "cn"));
    }
}
