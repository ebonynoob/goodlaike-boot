package com.goodlaike.business.news.dao;

import java.util.HashMap;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goodlaike.business.core.support.LanguageStore;
import com.goodlaike.business.news.model.News;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * 新闻dao
 * 
 * @author Jail Hu
 */
@Repository
@Lazy(value = true)
public class NewsDao extends LocalRWDao<News> {

	/**
	 * 添加新闻并返回主键
	 * 
	 * @param news
	 *            新闻实体 包含<br>
	 *            title 新闻标题<br>
	 *            subtitle 新闻副标题<br>
	 *            content 新闻内容<br>
	 *            link 新闻链接<br>
	 *            type 新闻类型，string <br>
	 *            createUserId 操作人ID
	 * @return int
	 */
    @Transactional(transactionManager = "transactionManagerNews")
	public int insertAndReturnId(News news) {
		super.insertAndReturnId(news);
		int returnId = news.getId();
		HashMap<String, Object> map = super.createLangMap();
		map.put("id", returnId);
		super.insert("insertOthers", map);
		return returnId;
	}

	/**
	 * 获得新闻
	 * 
	 * @param id
	 *            新闻id
	 * @param lang
	 *            语言版本
	 * @return News
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月23日下午9:57:40
	 * @updator jail
	 * @updateTime 2015年8月23日下午9:57:40
	 */
	public News findNews(int id, String lang) {
		HashMap<String, Object> map = super.createLangMap(lang);
		map.put("id", id);
		return super.selectOne(map);
	}

	/**
	 * 更新新闻
	 * 
	 * @param news
	 *            新闻实体 包含<br>
	 *            id 主键ID<br>
	 *            title 新闻标题<br>
	 *            subtitle 新闻副标题<br>
	 *            content 新闻内容<br>
	 *            link 新闻链接<br>
	 *            updateUserId 操作人ID
	 * 
	 * @param lang
	 *            语言版本
	 * @return boolean
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月26日下午11:25:33
	 * @updator jail
	 * @updateTime 2015年8月26日下午11:25:33
	 */
	public boolean update(News news, String lang) {
		HashMap<String, Object> map = super.createLangMap(lang);
		map.put("news", news);
		return super.update("update", map);
	}

	/**
	 * 删除新闻
	 * 
	 * @param id
	 *            主键ID
	 * @param userId
	 *            操作人ID
	 * @return boolean
	 * @since 1.0.0
	 * @author jail
	 * @createTime 2015年8月26日下午11:24:36
	 * @updator jail
	 * @updateTime 2015年8月26日下午11:24:36
	 */
	public boolean delete(int id, int userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("userId", userId);
		return super.delete(map);
	}
	
	/**
	 * 分页查询
	 * @param pagination 分页对象
	 * @param lang 语言版本
	 * @return Pagination<News>
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年8月29日下午12:23:11
	 * @updator Jail Hu
	 * @updateTime 2015年8月29日下午12:23:11
	 */
	public Pagination<News> getPagination(Pagination<News> pagination, String lang) {
		pagination.setTable("v_news" + "_" + LanguageStore.getLanguage(lang));
		return super.getPagination(pagination);
	}
}
