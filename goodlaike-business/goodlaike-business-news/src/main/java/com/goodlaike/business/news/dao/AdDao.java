package com.goodlaike.business.news.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goodlaike.business.news.model.Ad;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * 广告dao
 * 
 * @author Jail Hu
 */
@Repository
@Lazy(value = true)
public class AdDao extends LocalRWDao<Ad> {

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
     */
    @Transactional
    public int insertAndReturnId(Ad ad) {
        super.insertAndReturnId(ad);
        int returnId = ad.getId();
        return returnId;
    }

    /**
     * 获得广告
     * 
     * @param id
     *            广告id
     * @return News
     * @author jail
     */
    public Ad findAd(int id) {
        return super.selectOne(id);
    }

    /**
     * 更新广告
     * 
     * @param ad
     *            广告实体 包含<br>
     *            id 主键ID<br>
     *            title 广告标题<br>
     *            subtitle 广告副标题<br>
     *            picPath 广告图片<br>
     *            link 广告链接<br>
     *            updateUserId 操作人ID
     * 
     * @return boolean
     * @author jail
     */
    public boolean update(Ad ad) {
        return super.update("update", ad);
    }

    /**
     * 获得所有的广告
     * 
     * @return List<Ad>
     * @author jail
     */
    public List<Ad> findAll() {
        return super.selectList("findAll");
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
    public boolean delete(int id, int userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userId", userId);
        return super.delete(map);
    }

    /**
     * 获得分页
     * @param pagination
     * @return
     * @author jail
     */
    public Pagination<Ad> getAdPagination(Pagination<Ad> pagination) {
        return super.getPagination(pagination);
    }
}
