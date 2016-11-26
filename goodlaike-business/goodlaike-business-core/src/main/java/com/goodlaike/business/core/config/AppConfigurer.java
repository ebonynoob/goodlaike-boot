package com.goodlaike.business.core.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Core 应用 启动加载类
 * 
 * @author Jail Hu
 */
public class AppConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected Properties mergeProperties() throws IOException {
        super.ignoreUnresolvablePlaceholders = true;
        Properties properties = super.mergeProperties();
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("core-config.properties");
            config.getKeys().forEachRemaining(key -> {
                properties.put(key, config.getProperty(String.valueOf(key)));
            });
        } catch (ConfigurationException e) {
            e.addSuppressed(new Throwable("核心模块数据源加载失败，请检查"));
            e.printStackTrace();
        }
        return properties;
    }
}
