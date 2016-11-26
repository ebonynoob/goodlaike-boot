package com.goodlaike.business.news.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodlaike.business.core.controller.BaseRestController;
import com.goodlaike.business.core.helper.LanguageHelper;
import com.goodlaike.business.core.helper.LoginHelper;
import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.support.AssertGoodlaike;
import com.goodlaike.business.news.RestResultNews;
import com.goodlaike.business.news.config.NewsConfig;
import com.goodlaike.business.news.model.News;
import com.goodlaike.business.news.model.NewsPicture;
import com.goodlaike.business.news.service.NewsPictureService;
import com.goodlaike.business.news.service.NewsService;
import com.goodlaike.framework.dao.order.OrderFilter;
import com.goodlaike.framework.dao.search.SearchFilter;
import com.goodlaike.framework.dao.search.SearchType;
import com.goodlaike.framework.dao.support.Pagination;

/**
 * 新闻控制器
 * 
 * @author Jail Hu
 */
@RestController
@RequestMapping("/business/news")
public class NewsController extends BaseRestController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsConfig newsConfig;

    @Autowired
    private NewsPictureService newsPictureService;

    /**
     * 分页
     * 
     * @param request
     * @param pageNo
     * @param pageSize
     * @param lang
     * @return
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:13:41
     * @updator jail
     * @updateTime 2015年8月23日下午10:13:41
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<?> getPagination(HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "type") String type, @RequestParam(value = "lang", required = false) String lang) {
        Assert.isTrue(newsConfig.isLegalType(type), "类型不合法");
        Pagination<News> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.addFilters(SearchFilter.and("type", SearchType.EQUAL, type));
        pagination.addFilters(OrderFilter.desc("id"));
        return ResponseEntity.ok(newsService.getPagination(pagination, LanguageHelper.getLocalization(request, lang)));
    }

    /**
     * 新闻详情
     * 
     * @param request
     * @param id
     * @param lang
     * @return
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:21:23
     * @updator jail
     * @updateTime 2015年8月23日下午10:21:23
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    protected ResponseEntity<?> getNews(HttpServletRequest request, @PathVariable int id,
            @RequestParam(value = "lang", required = false) String lang) {
        News news;
        return (news = newsService.getNews(id, LanguageHelper.getLocalization(request, lang))) == null
                ? super.notFound(RestResultNews.NOTFOUND) : ResponseEntity.ok(news);
    }

    /**
     * 添加新闻
     * 
     * @param request
     * @param news
     * @return
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午10:40:50
     * @updator jail
     * @updateTime 2015年8月23日下午10:40:50
     */
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> addNews(HttpServletRequest request, @ModelAttribute("news") News news,
            @RequestParam(value = "pics", required = false) String pics) {
        Assert.hasText(news.getTitle(), "标题必填");
        Assert.hasText(news.getContent(), "内容必填");
        Assert.hasText(news.getType(), "类型必填");
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        news.setCreateUserId(user.getUserId());
        return ResponseEntity.ok(newsService.insertNews(news, pics));
    }

    /**
     * 更新新闻
     * 
     * @param request
     * @param id
     *            主键ID
     * @param title
     *            标题
     * @param subtitle
     *            副标题
     * @param content
     *            内容
     * @param link
     *            链接地址
     * @param lang
     *            语言版本
     * @param pics
     *            图片列表，如果图片未做修改也请将原先的图片地址全部传入，否则认为删除
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    protected ResponseEntity<?> updateNews(HttpServletRequest request, @PathVariable int id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "subtitle", required = false) String subtitle,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(value = "pics", required = false) String pics) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        return newsService.updateNews(id, title, subtitle, content, link, user.getUserId(),
                LanguageHelper.getLocalization(request, lang), pics) ? ResponseEntity.ok().build()
                        : super.serverError(RestResultNews.NEWS_UPDATE_ERROR);
    }

    /**
     * 删除新闻
     * 
     * @param request
     * @param id
     *            主键ID
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    protected ResponseEntity<?> deleteNews(HttpServletRequest request, @PathVariable int id) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        return newsService.deleteNews(id, user.getUserId()) ? ResponseEntity.ok().build()
                : super.serverError(RestResultNews.NEWS_DELETE_ERROR);
    }

    /**
     * 获得指定新闻的图片列表
     * 
     * @param newsId
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{newsId}/picture", method = RequestMethod.GET)
    protected ResponseEntity<?> getNewsPictureList(@PathVariable int newsId) {
        return ResponseEntity.ok(newsPictureService.getNewsPictureList(newsId));
    }

    /**
     * 单纯添加新闻图片
     * 
     * @param newsId
     * @param pics
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{newsId}/picture", method = RequestMethod.POST)
    protected ResponseEntity<?> addNewsPictureList(@PathVariable int newsId,
            @RequestParam(value = "pics") String pics) {
        newsPictureService.insertPictures(pics, newsId);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据 外键ID删除所有图片
     * 
     * @param request
     * @param newsId
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{newsId}/picture", method = RequestMethod.DELETE)
    protected ResponseEntity<?> deleteNewsPictureList(HttpServletRequest request, @PathVariable int newsId) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        newsPictureService.deleteNewsPicture2(newsId, user.getUserId());
        return ResponseEntity.ok().build();
    }

    /**
     * 根据主键ID与外键ID获得图片
     * 
     * @param request
     * @param newsId
     * @param id
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{newsId}/picture/{id}", method = RequestMethod.GET)
    protected ResponseEntity<?> getNewsPicture(HttpServletRequest request, @PathVariable int newsId,
            @PathVariable int id) {
        NewsPicture picture;
        return (picture = newsPictureService.getNewsPicture(newsId, id)) == null
                ? super.notFound(RestResultNews.NOTFOUND) : ResponseEntity.ok(picture);
    }

    /**
     * 根据主键ID与外键ID删除图片
     * 
     * @param request
     * @param newsId
     * @param id
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{newsId}/picture/{id}", method = RequestMethod.DELETE)
    protected ResponseEntity<?> deleteNewsPicture(HttpServletRequest request, @PathVariable int newsId,
            @PathVariable int id) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        newsPictureService.deleteNewsPicture(id, newsId, user.getUserId());
        return ResponseEntity.ok().build();
    }

    /**
     * 获得新闻类型列表
     * 
     * @return
     * @author jail
     */
    @RequestMapping(value = "types", method = RequestMethod.GET)
    protected ResponseEntity<?> getNewsTypes() {
        return ResponseEntity.ok(newsConfig.getNewsTypeList());
    }
}
