package com.goodlaike.business.news.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.goodlaike.business.news.model.NewsPicture;

/**
 * 新闻图片dao
 * 
 * @author Jail Hu
 */
@Repository
@Lazy(value = true)
public class NewsPictureDao extends LocalRWDao<NewsPicture> {

	/**
	 * 批量插入新闻图片
	 * 
	 * @param pictureList
	 *            图片 List 需要用到的属性<br>
	 *            newsId 新闻ID<br>
	 *            picPath 图片地址，相对路径<br>
	 *            sort 图片排序，越大越靠后<br>
	 *            createUserId 创建人ID
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午3:19:53
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:19:53
	 */
    @Transactional(transactionManager = "transactionManagerNews")
	public void batchInsertNewsPicture(List<NewsPicture> pictureList) {
		if (CollectionUtils.isEmpty(pictureList)) {
			return;
		}
		this.updateNoneMatch(pictureList);
		super.insert("batchInsert", pictureList);
	}

	/**
	 * 根据新闻ID获得新闻图片列表
	 * 
	 * @param newsId
	 *            新闻ID
	 * @return List<NewsPicture>
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午3:16:31
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:16:31
	 */
	public List<NewsPicture> findNewsPictureList(int newsId) {
		return super.selectList("findByNewsId", newsId);
	}

	/**
	 * 删除所有不匹配满足的图片
	 * 
	 * @param pictureList
	 *            图片 List 需要用到的属性<br>
	 *            newsId 新闻ID<br>
	 *            picPath 图片地址，相对路径<br>
	 *            sort 图片排序，越大越靠后<br>
	 *            createUserId 创建人ID
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月27日下午6:11:35
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午6:11:35
	 */
	public void updateNoneMatch(List<NewsPicture> pictureList) {
		if (CollectionUtils.isEmpty(pictureList)) {
			return;
		}
		int userId = pictureList.get(0).getCreateUserId();
		int newsId = pictureList.get(0).getNewsId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("newsId", newsId);
		map.put("list", pictureList);
		super.update("updateNoneMatch", map);
	}

	/**
	 * 删除新闻图片
	 * 
	 * @param id
	 *            主键ID
	 * @param userId
	 *            操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月27日下午3:15:11
	 * @updator Jail Hu
	 * @updateTime 2015年8月27日下午3:15:11
	 */
	public boolean delete(int id, int userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("userId", userId);
		return super.delete(map);
	}

	/**
	 * 根据主键ID与外键ID删除图片
	 * @param id 主键ID
	 * @param newsId 外键ID
	 * @param userId 操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:30:33
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:30:33
	 */
	public boolean delete(int id, int newsId, int userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("newsId", newsId);
		map.put("userId", userId);
		return super.delete("deleteByIdAndNewsId",map);
	}

	/**
	 * 根据外键ID删除所有图片
	 * 
	 * @param newsId
	 *            外键ID
	 * @param userId
	 *            操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日上午10:30:29
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日上午10:30:29
	 */
	public void delete2(int newsId, int userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("newsId", newsId);
		map.put("userId", userId);
		super.delete("deleteByNewsId", map);
	}

	/**
	 * 根据ID获得图片
	 * 
	 * @param id
	 *            主键ID
	 * @return NewsPicture
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:18:55
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:18:55
	 */
	public NewsPicture findNewsPicture(int id) {
		return super.selectOne(id);
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
	 * @createTime 2015年8月29日下午12:25:28
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:25:28
	 */
	public NewsPicture findNewsPicture(int newsId, int id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("newsId", newsId);
		return super.selectOne("findByIdAndNewsId", map);
	}

}
