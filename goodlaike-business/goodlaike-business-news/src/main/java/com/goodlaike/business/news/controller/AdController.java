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
import com.goodlaike.business.core.helper.LoginHelper;
import com.goodlaike.business.core.model.User;
import com.goodlaike.business.core.support.AssertGoodlaike;
import com.goodlaike.business.news.RestResultAd;
import com.goodlaike.business.news.config.AdConfig;
import com.goodlaike.business.news.model.Ad;
import com.goodlaike.business.news.service.AdService;

/**
 * 广告控制器
 * 
 * @author Jail Hu
 */
@RestController
@RequestMapping("/business/ad")
public class AdController extends BaseRestController {

    @Autowired
    private AdService adService;

    @Autowired
    private AdConfig adConfig;

    /**
     * 分页
     * 
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     * @author jail
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<?> getPagination(HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(adService.getPagination(pageNo, pageSize, type));
    }

    /**
     * 广告详情
     * 
     * @param request
     * @param id
     * @return
     * @author jail
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    protected ResponseEntity<?> getAd(HttpServletRequest request, @PathVariable int id) {
        Ad ad;
        return (ad = adService.getAd(id)) == null ? super.notFound(RestResultAd.NOTFOUND) : ResponseEntity.ok(ad);
    }

    /**
     * 添加广告
     * 
     * @param request
     * @param ad
     * @return
     * @author jail
     */
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> addAd(HttpServletRequest request, @ModelAttribute("ad") Ad ad) {
        Assert.hasText(ad.getTitle(), "标题必填");
        Assert.hasText(ad.getType(), "类型必填");
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        ad.setCreateUserId(user.getUserId());
        return ResponseEntity.ok(adService.insertAd(ad));
    }

    /**
     * 更新广告
     * 
     * @param request
     * @param id
     *            主键ID
     * @param title
     *            标题
     * @param subtitle
     *            副标题
     * @param picPath
     *            广告图片
     * @param link
     *            链接地址
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    protected ResponseEntity<?> updateAd(HttpServletRequest request, @PathVariable int id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "subtitle", required = false) String subtitle,
            @RequestParam(value = "picPath", required = false) String picPath,
            @RequestParam(value = "link", required = false) String link) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        return adService.updateAd(id, title, subtitle, picPath, link, user.getUserId()) ? ResponseEntity.ok().build()
                : super.serverError(RestResultAd.AD_UPDATE_ERROR);
    }

    /**
     * 删除广告
     * 
     * @param request
     * @param id
     *            主键ID
     * @return
     * @author Jail Hu
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    protected ResponseEntity<?> deleteAd(HttpServletRequest request, @PathVariable int id) {
        User user = LoginHelper.getLoginUser(request);
        AssertGoodlaike.needLogin(user);
        return adService.deleteAd(id, user.getUserId()) ? ResponseEntity.ok().build()
                : super.serverError(RestResultAd.AD_DELETE_ERROR);
    }

    /**
     * 获得广告类型列表
     * 
     * @return
     * @author jail
     */
    @RequestMapping(value = "types", method = RequestMethod.GET)
    protected ResponseEntity<?> getAdTypes() {
        return ResponseEntity.ok(adConfig.getAdTypeList());
    }

    /**
     * 根据类型获得广告列表
     * 
     * @param type
     * @return
     * @author jail
     */
    @RequestMapping(value = "type")
    protected ResponseEntity<?> getAdList(@RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(adService.getAllAd(type));
    }

}
