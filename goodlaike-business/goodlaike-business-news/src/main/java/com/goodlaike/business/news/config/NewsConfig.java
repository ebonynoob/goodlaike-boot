package com.goodlaike.business.news.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 新闻配置
 * 
 * @author jail
 */
@Configuration
@PropertySource("classpath:news-config.properties")
public class NewsConfig {

    @Autowired
    private Environment environment;

    /**
     * 获得配置的新闻类型列表
     * @return List<String>
     * @author jail
     */
    public List<String> getNewsTypeList() {
        return Arrays.asList(environment.getProperty("goodlaike.business.news.types").split(","));
    }
    
    /**
     * 是否合法的新闻类型
     * @param type
     * @return
     * @author jail
     */
    public boolean isLegalType(String type){
        return this.getNewsTypeList().contains(type);
    }
    
}
