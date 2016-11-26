package com.goodlaike.business.fileupload.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 上传模块配置文件加载
 * @author jail
 */

public class AppConfigurer extends PropertyPlaceholderConfigurer {    
    protected Properties mergeProperties() throws IOException {
        super.ignoreUnresolvablePlaceholders = true;
        Properties properties = super.mergeProperties();
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("fileupload-config.properties");
            config.getKeys().forEachRemaining(key -> {
                properties.put(key, config.getProperty(String.valueOf(key)));
            });
        } catch (ConfigurationException e) {
            e.addSuppressed(new Throwable("上传模块配置加载加载失败，请检查"));
            e.printStackTrace();
        }
        return properties;
    }

}
