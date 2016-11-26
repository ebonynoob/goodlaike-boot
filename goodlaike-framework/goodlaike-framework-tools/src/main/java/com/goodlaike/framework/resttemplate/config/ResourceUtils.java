package com.goodlaike.framework.resttemplate.config;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.goodlaike.framework.resttemplate.utils.SeProperties;

/**
 * 配置加载工具
 *
 * @author ：liuxing
 * @since ：2015-07-14 00:39
 */
public class ResourceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtils.class);

    private static final String REST_CLIENT_CONFIG_NAME = "rest-template-config.properties";

    private static SeProperties REST_CLIENT_PROPERTIES;

    static {
        REST_CLIENT_PROPERTIES = getProperties(REST_CLIENT_CONFIG_NAME);
    }

    /**
     * 获取RestClient的配置
     * @return
     */
    public static SeProperties getRestConfig() {
        return REST_CLIENT_PROPERTIES;
    }

    /**
     * 获取运行环境的根目录
     * @return
     */
    public static String getClassRootPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return path.replaceAll("%20", " ");
    }

    /**
     * 加载配置文件
     * @param directory
     * @param name
     * @return
     */
    private static SeProperties getProperties(String directory, String name) {
        Assert.hasText(directory, "无效的文件路径");
        Assert.hasText(name, "无效的文件名");

        String filePath = directory + name;

        File file = new File(filePath);
        if (!file.exists()) {
            return getProperties(name);
        }

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(new File(filePath)));
            return new SeProperties(props);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从jar包加载配置文件
     * @param name
     * @return
     */
    private static SeProperties getProperties(String name) {
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            Properties properties = new Properties();
            properties.load(inputStream);
            return new SeProperties(properties);
        } catch (FileNotFoundException e) {
            LOGGER.error("properties file not found");
        } catch (IOException e) {
            LOGGER.error("properties file IO exception");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

}