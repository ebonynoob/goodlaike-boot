package com.goodlaike.business.core.service.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;

import com.goodlaike.framework.dao.support.BaseRWDao;


/**
 * 本地化Dao
 * @author jail
 */
public abstract class LocalRWDao<T> extends BaseRWDao<T>{

    @Resource(name = "goodlaikeCoreSqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
