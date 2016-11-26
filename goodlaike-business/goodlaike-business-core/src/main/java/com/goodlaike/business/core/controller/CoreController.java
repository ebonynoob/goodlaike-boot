package com.goodlaike.business.core.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodlaike.business.core.helper.LanguageHelper;
import com.goodlaike.business.core.support.Constants;
import com.goodlaike.business.core.support.LanguageStore;

/**
 * 内核 拦截器
 * 
 * @author Jail Hu
 */
@RestController
@RequestMapping("/business/core")
public class CoreController extends BaseRestController {

    /**
     * 设置本地化
     * 
     * @param request
     * @param lang
     *            语言参数
     * @return
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月21日下午6:44:46
     * @updator Jail Hu
     * @updateTime 2015年8月21日下午6:44:46
     */
    @RequestMapping(value = "/localization", method = RequestMethod.PUT)
    protected ResponseEntity<?> setLocalization(HttpServletRequest request, @RequestParam(value = "lang") String lang,
            HttpServletResponse response) {
//        request.getSession(true).setAttribute(Constants.LOCALIZATION_SESSION_NAME, LanguageStore.getLanguage(lang));
        Cookie cookie = new Cookie(Constants.LOCALIZATION_COOKIE_NAME, LanguageStore.getLanguage(lang));
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    /**
     * 获得所有支持的本地化
     * 
     * @return
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月21日下午6:45:39
     * @updator Jail Hu
     * @updateTime 2015年8月21日下午6:45:39
     */
    @RequestMapping(value = "/localization", method = RequestMethod.GET)
    protected ResponseEntity<?> getLocalizationList() {
        return ResponseEntity.ok(LanguageStore.getLanguages());
    }

    /**
     * 获得当前选择的语言版本
     * 
     * @param request
     * @return
     * @author jail
     */
    @RequestMapping(value = "/localization/current", method = RequestMethod.GET)
    protected ResponseEntity<?> getCurrentLocalization(HttpServletRequest request) {
        return ResponseEntity.ok(LanguageHelper.getLocalization(request));
    }
}
