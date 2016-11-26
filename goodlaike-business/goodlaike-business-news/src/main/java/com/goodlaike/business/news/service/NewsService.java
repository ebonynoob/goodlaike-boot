package com.goodlaike.business.news.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.goodlaike.business.core.support.LanguageStore;
import com.goodlaike.business.news.dao.NewsDao;
import com.goodlaike.business.news.model.News;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * @author jail
 *
 * @date 2015年8月18日
 */
@Service
@Lazy(value = true)
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private NewsPictureService newsPictureService;

    /**
     * 获得新闻分页数据
     * 
     * @param pagination
     *            分页对象
     * @param lang
     *            语言版本
     * @return Pagination<News>
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:00:51
     * @updator jail
     * @updateTime 2015年8月23日下午10:00:51
     */
    public Pagination<News> getPagination(Pagination<News> pagination, String lang) {
        return newsDao.getPagination(pagination, lang);
    }

    /**
     * 添加新闻并返回主键
     * 
     * @param title
     *            新闻标题
     * @param subtitle
     *            新闻副标题
     * @param content
     *            新闻内容
     * @param link
     *            新闻链接
     * @param type
     *            新闻类型，string
     * @param createUserId
     *            操作人ID
     * @return int
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月22日下午10:32:49
     * @updator jail
     * @updateTime 2015年8月22日下午10:32:49
     */
    public int insertNews(String title, String subtitle, String content, String link, String type, int userId) {
        News news = new News();
        news.setTitle(title);
        news.setSubtitle(subtitle);
        news.setContent(content);
        news.setLink(link);
        news.setCreateUserId(userId);
        return this.insertNews(news, null);
    }

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
     * @param pics
     *            图片字段，json 字符串
     * @return int
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月22日下午10:32:49
     * @updator jail
     * @updateTime 2015年8月22日下午10:32:49
     */
    @Transactional(transactionManager = "transactionManagerNews")
    public int insertNews(News news, String pics) {
        Assert.hasText(news.getTitle(), "标题必填");
        Assert.hasText(news.getContent(), "内容必填");
        Assert.hasText(news.getType(), "类型必填");
        int newsId = newsDao.insertAndReturnId(news);
        newsPictureService.insertPictures(pics, newsId);
        return newsId;
    }

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
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月22日下午10:32:49
     * @updator jail
     * @updateTime 2015年8月22日下午10:32:49
     */
    public int insertNews(News news) {
        Assert.hasText(news.getTitle(), "标题必填");
        Assert.hasText(news.getContent(), "内容必填");
        Assert.hasText(news.getType(), "类型必填");
        int newsId = newsDao.insertAndReturnId(news);
        return newsId;
    }

    /**
     * 获得新闻
     * 
     * @param id
     *            新闻 id
     * @param lang
     *            语言版本
     * @return News
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:00:17
     * @updator jail
     * @updateTime 2015年8月23日下午10:00:17
     */
    public News getNews(int id, String lang) {
        return newsDao.findNews(id, LanguageStore.getLanguage(lang));
    }

    /**
     * 更新新闻
     * 
     * @param id
     *            主键id
     * @param title
     *            标题
     * @param subtitle
     *            副标题
     * @param content
     *            内容
     * @param link
     *            链接
     * @param userId
     *            创建人ID
     * @param lang
     *            语言版本
     * @return boolean
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月26日下午11:29:08
     * @updator jail
     * @updateTime 2015年8月26日下午11:29:08
     */
    public boolean updateNews(int id, String title, String subtitle, String content, String link, int userId,
            String lang) {
        Assert.isTrue(id > 0, "id不合法");
        Assert.hasText(title, "标题必填");
        Assert.hasText(content, "内容必填");
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setSubtitle(subtitle);
        news.setContent(content);
        news.setLink(link);
        news.setUpdateUserId(userId);
        boolean result = newsDao.update(news, LanguageStore.getLanguage(lang));
        return result;
    }

    /**
     * 更新新闻
     * 
     * @param id
     *            主键id
     * @param title
     *            标题
     * @param subtitle
     *            副标题
     * @param content
     *            内容
     * @param link
     *            链接
     * @param userId
     *            创建人ID
     * @param lang
     *            语言版本
     * @param pics
     *            图片字段，json 字符串
     * @return
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月26日下午11:29:08
     * @updator jail
     * @updateTime 2015年8月26日下午11:29:08
     */
    @Transactional(transactionManager = "transactionManagerNews")
    public boolean updateNews(int id, String title, String subtitle, String content, String link, int userId,
            String lang, String pics) {
        Assert.isTrue(id > 0, "id不合法");
        Assert.hasText(title, "标题必填");
        Assert.hasText(content, "内容必填");
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setSubtitle(subtitle);
        news.setContent(content);
        news.setLink(link);
        news.setUpdateUserId(userId);
        boolean result = newsDao.update(news, LanguageStore.getLanguage(lang));
        if (result) {
            // 如果没有图片信息则认为是删除动作
            if (StringUtils.isBlank(pics)) {
                newsPictureService.deleteNewsPicture2(id, userId);
            } else {
                newsPictureService.insertPictures(pics, id);
            }
        }
        return result;
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
     * @createTime 2015年8月26日下午11:20:27
     * @updator jail
     * @updateTime 2015年8月26日下午11:20:27
     */
    public boolean deleteNews(int id, int userId) {
        return newsDao.delete(id, userId);
    }
}
