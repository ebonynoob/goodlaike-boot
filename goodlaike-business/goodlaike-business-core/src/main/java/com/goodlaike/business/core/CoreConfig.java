package com.goodlaike.business.core;

import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.goodlaike.business.core.support.Constants;

/**
 * 核心配置
 * 
 * @author jail
 *
 * @date 2015年8月22日
 */
public class CoreConfig implements Serializable {
    private static final long serialVersionUID = 2231056346690323449L;

    private static CoreConfig config = new CoreConfig();

    /**
     * 应用程序编码
     */
    private String appCharset;

    static {
        try {
            PropertiesConfiguration properties = new PropertiesConfiguration("core-config.properties");
            config.appCharset = properties.getString("encoding", Constants.DEFAULT_CHARSET);
        } catch (ConfigurationException e) {
            config.appCharset = Constants.DEFAULT_CHARSET;
        }
    }

    private CoreConfig() {

    }

    /**
     * 获得 "应用程序编码"
     * 
     * @return String
     */
    public final static String getAppCharset() {
        return config.appCharset;
    }
}
