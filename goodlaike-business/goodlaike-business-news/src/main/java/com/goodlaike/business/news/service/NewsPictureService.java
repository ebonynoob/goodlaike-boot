package com.goodlaike.business.news.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.goodlaike.business.news.dao.NewsPictureDao;
import com.goodlaike.business.news.model.NewsPicture;

/**
 * 新闻图片 service
 * 
 * @author jail
 *
 * @date 2015年8月18日
 */
@Service
@Lazy(value = true)
public class NewsPictureService {

	@Autowired
	private NewsPictureDao newsPictureDao;

	/**
	 * 批量插入新闻图片<br>
	 * 会先删除不匹配的图片再做批量插入
	 * 
	 * @param pictureList
	 *            图片列表 需要用到的属性<br>
	 *            newsId 新闻ID<br>
	 *            picPath 图片地址，相对路径<br>
	 *            sort 图片排序，越大越靠后<br>
	 *            createUserId 创建人ID
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午3:24:17
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:24:17
	 */
	public void insertPictures(List<NewsPicture> pictureList) {
		newsPictureDao.batchInsertNewsPicture(pictureList);
	}

	/**
	 * 批量插入新闻图片
	 * 
	 * @param pics
	 *            图片集合 json 字符串,包含字段<br>
	 *            picPath 图片地址，相对路径<br>
	 *            sort 图片排序，越大越靠后<br>
	 * @param newsId
	 *            新闻ID
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月28日下午5:54:57
	 * @updator Jail Hu
	 * @updateTime 2015年8月28日下午5:54:57
	 */
	public void insertPictures(String pics, int newsId) {
		if (StringUtils.isBlank(pics)) {
			return;
		}
		Assert.isTrue(newsId > 0, "新闻id不合法");
		List<NewsPicture> pictureList = JSONObject.parseArray(pics, NewsPicture.class);
		pictureList.forEach(pic -> pic.setNewsId(newsId));
		newsPictureDao.batchInsertNewsPicture(pictureList);
	}

	/**
	 * 根据新闻ID获得图片列表
	 * 
	 * @param newsId
	 *            新闻ID
	 * @return List<NewsPicture>
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午3:23:12
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:23:12
	 */
	public List<NewsPicture> getNewsPictureList(int newsId) {
		return newsPictureDao.findNewsPictureList(newsId);
	}

	/**
	 * 删除单条新闻图片
	 * 
	 * @param id
	 *            主键ID
	 * @param userId
	 *            操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午3:21:30
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:21:30
	 */
	public boolean deleteNewsPicture(int id, int userId) {
		return newsPictureDao.delete(id, userId);
	}

	/**
	 * 根据主键ID与外键ID删除图片
	 * 
	 * @param id
	 *            主键ID
	 * @param newsId
	 *            外键ID
	 * @param userId
	 *            操作人ID
	 * @return Boolean
	 * 
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:31:46
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:31:46
	 */
	public boolean deleteNewsPicture(int id, int newsId, int userId) {
		return newsPictureDao.delete(id, newsId, userId);
	}

	/**
	 * 根据外键ID删除所有图片
	 * 
	 * @param newsId
	 *            外键ID
	 * @param userId
	 *            操作人ID
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日上午10:31:40
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日上午10:31:40
	 */
	public void deleteNewsPicture2(int newsId, int userId) {
		newsPictureDao.delete2(newsId, userId);
	}

	/**
	 * 根据主键获得图片
	 * 
	 * @param id
	 *            主键ID
	 * @return NewsPicture
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:26:20
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:26:20
	 */
	public NewsPicture getNewsPicture(int id) {
		return newsPictureDao.findNewsPicture(id);
	}

	/**
	 * 根据主键ID与外键ID获得图片
	 * 
	 * @param newsId
	 *            外键ID
	 * @param id
	 *            主键ID
	 * @return NewsPicture
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:27:08
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:27:08
	 */
	public NewsPicture getNewsPicture(int newsId, int id) {
		return newsPictureDao.findNewsPicture(newsId, id);
	}
}
