package com.goodlaike.business.news.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.goodlaike.business.news.config.AdConfig;
import com.goodlaike.business.news.dao.AdDao;
import com.goodlaike.business.news.model.Ad;
import com.goodlaike.framework.dao.order.OrderFilter;
import com.goodlaike.framework.dao.search.SearchFilter;
import com.goodlaike.framework.dao.search.SearchType;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * 广告 Service
 * 
 * @author jail
 *
 * @date 2015年8月18日
 */
@Service
@Lazy(value = true)
public class AdService {

    @Autowired
    private AdDao adDao;

    @Autowired
    private AdConfig adConfig;

    /**
     * 获得广告分页数据
     * 
     * @param pageNo
     *            页码
     * @param pageSize
     *            页尺寸
     * @param type
     *            广告类型
     * @return Pagination<Ad>
     * @author jail
     */
    public Pagination<Ad> getPagination(int pageNo, int pageSize, String type) {
        Pagination<Ad> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        if (StringUtils.isNotBlank(type)) {
            Assert.isTrue(adConfig.isLegalType(type), "类型不合法");
            pagination.addFilters(SearchFilter.and("type", SearchType.EQUAL, type));
        }
        pagination.addFilters(OrderFilter.desc("id"));
        return adDao.getAdPagination(pagination);
    }

    /**
     * 添加广告并返回主键
     * 
     * @param title
     *            广告标题
     * @param subtitle
     *            广告副标题
     * @param picPath
     *            广告图片
     * @param link
     *            广告链接
     * @param type
     *            广告类型，string
     * @param createUserId
     *            操作人ID
     * @return int
     * @author jail
     */
    public int insertAd(String title, String subtitle, String picPath, String link, String type, int userId) {
        Assert.isTrue(adConfig.isLegalType(type), "类型不合法");
        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setSubtitle(subtitle);
        ad.setPicPath(picPath);
        ad.setLink(link);
        ad.setCreateUserId(userId);
        return this.insertAd(ad);
    }

    /**
     * 添加广告并返回主键
     * 
     * @param news
     *            广告实体 包含<br>
     *            title 广告标题<br>
     *            subtitle 广告副标题<br>
     *            picPath 广告图片<br>
     *            link 广告链接<br>
     *            type 广告类型，string <br>
     *            createUserId 操作人ID
     * @return int
     * @author jail
     */
    public int insertAd(Ad ad) {
        Assert.hasText(ad.getTitle(), "标题必填");
        Assert.hasText(ad.getType(), "类型必填");
        Assert.isTrue(adConfig.isLegalType(ad.getType()), "类型不合法");
        int newsId = adDao.insertAndReturnId(ad);
        return newsId;
    }

    /**
     * 获得广告
     * 
     * @param id
     *            广告 id
     * @return Ad
     * @author jail
     */
    public Ad getAd(int id) {
        return adDao.findAd(id);
    }

    /**
     * 更新广告
     * 
     * @param id
     *            主键id
     * @param title
     *            标题
     * @param subtitle
     *            副标题
     * @param picPath
     *            广告图片
     * @param link
     *            链接
     * @param userId
     *            创建人ID
     * @return boolean
     * @author jail
     */
    public boolean updateAd(int id, String title, String subtitle, String picPath, String link, int userId) {
        Assert.isTrue(id > 0, "id不合法");
        Assert.hasText(title, "标题必填");
        Ad ad = new Ad();
        ad.setId(id);
        ad.setTitle(title);
        ad.setSubtitle(subtitle);
        ad.setPicPath(picPath);
        ad.setLink(link);
        ad.setUpdateUserId(userId);
        boolean result = adDao.update(ad);
        return result;
    }

    /**
     * 删除广告
     * 
     * @param id
     *            主键ID
     * @param userId
     *            操作人ID
     * @return boolean
     * @author jail
     */
    public boolean deleteAd(int id, int userId) {
        return adDao.delete(id, userId);
    }

    /**
     * 获得广告分组
     * 
     * @return Map<String, List<Ad>>
     * @author jail
     */
    public Map<String, List<Ad>> getAdGroup() {
        List<Ad> adList = this.getAllAd();
        return adList.stream().collect(Collectors.groupingBy(Ad::getType));
    }

    /**
     * 获得所有广告
     * 
     * @return List<Ad>
     * @author jail
     */
    public List<Ad> getAllAd() {
        return adDao.findAll();
    }

    /**
     * 根据类型获得所有广告
     * @param type 广告类型
     * @return List<Ad>
     * @author jail
     */
    public List<Ad> getAllAd(String type) {
        if (StringUtils.isNotBlank(type)) {
            Assert.isTrue(adConfig.isLegalType(type), "类型不合法");
            return this.getAdGroup().get(type);
        } else {
            return this.getAllAd();
        }
    }
}
