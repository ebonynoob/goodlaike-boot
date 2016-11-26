package com.goodlaike.business.news.dao;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;

import com.goodlaike.business.core.support.LanguageStore;
import com.goodlaike.framework.dao.support.BaseRWDao;

/**
 * 本地读写Dao
 * 
 * @author jail
 *
 * @date 2015年8月18日
 */
public abstract class LocalRWDao<T> extends BaseRWDao<T> {
    @Resource(name = "goodlaikeSqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 创建带其他语言分区的map
     * 
     * @return HashMap<String, Object>
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月22日下午10:31:05
     * @updator jail
     * @updateTime 2015年8月22日下午10:31:05
     */
    protected HashMap<String, Object> createLangMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("langs", LanguageStore.getLanguagesWithOutDefault());
        return map;
    }
    
    /**
     * 创建带语言区分的map
     * @param lang
     * @return HashMap<String, Object>
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月23日下午9:54:14
     * @updator jail
     * @updateTime 2015年8月23日下午9:54:14
     */
    protected HashMap<String, Object> createLangMap(String lang) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("lang",LanguageStore.getLanguage(lang));
        return map;
    }
}
