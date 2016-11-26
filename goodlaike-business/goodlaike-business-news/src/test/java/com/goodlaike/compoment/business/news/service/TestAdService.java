package com.goodlaike.compoment.business.news.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlaike.business.news.model.Ad;
import com.goodlaike.business.news.service.AdService;
import com.goodlaike.compoment.business.news.BaseTest;

/**
 * Test AdService
 * 
 * @author Jail Hu
 */
public class TestAdService extends BaseTest {

    @Autowired
    private AdService adService;

    @Test
    public void getPagination() {
        System.out.println(adService.getPagination(1, 20, "首页头条"));
    }

    @Test
    public void insertAd() {
        Ad ad = new Ad();
        ad.setTitle("你好1");
        ad.setSubtitle("副标题");
        ad.setType("首页头条");
        System.out.println(adService.insertAd(ad));
    }

    @Test
    public void updateAd() {
        Assert.assertTrue(adService.updateAd(2, "不好", "", "不想写", "没有", 8));
    }

    @Test
    public void deleteAd() {
        Assert.assertTrue(adService.deleteAd(2, 8));
    }

    @Test
    public void getAllAd() {
        System.out.println(adService.getAllAd());
        System.out.println(adService.getAllAd());
        System.out.println(adService.getAllAd());
        System.out.println(adService.getAllAd());
    }

    @Test
    public void getAdGroup() {
        System.out.println(adService.getAdGroup());
    }
}
