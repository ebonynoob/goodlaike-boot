package com.goodlaike.business.news.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * News 应用 启动加载类
 * 
 * @author Jail Hu
 */
public class AppConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected Properties mergeProperties() throws IOException {
        super.ignoreUnresolvablePlaceholders = true;
        Properties properties = super.mergeProperties();
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("news-config.properties");
            config.getKeys().forEachRemaining(key -> {
                properties.put(key, config.getProperty(String.valueOf(key)));
            });
        } catch (ConfigurationException e) {
            e.addSuppressed(new Throwable("新闻模块数据源加载失败，请检查"));
            e.printStackTrace();
        }
        return properties;
    }
}
