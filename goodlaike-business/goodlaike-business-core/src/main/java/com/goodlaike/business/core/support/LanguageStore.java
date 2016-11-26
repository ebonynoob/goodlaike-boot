package com.goodlaike.business.core.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.goodlaike.business.core.CoreConfig;

/**
 * 程序语言设置
 * 
 * @author jail
 *
 * @date 2015年8月22日
 */
public final class LanguageStore extends HashMap<String, String> {

    private static final long serialVersionUID = -6348659478999915501L;

    private static LanguageStore store;

    private List<String> languagesWithOutDefault = new ArrayList<>();

    /**
     * 单例,默认为添加 cn 语言
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private static synchronized LanguageStore getInstance() {
        if (store == null) {
            store = new LanguageStore();
            store.putIfAbsent(Constants.DEFAULT_LOCALIZATION.getValue(), Constants.DEFAULT_LOCALIZATION.getText());
            try {
                PropertiesConfiguration properties = new PropertiesConfiguration();
                properties.setEncoding(CoreConfig.getAppCharset());
                properties.load("core-config.properties");
                String localization = properties.getString("goodlaike.business.core.localization", "");
                HashMap<String, String> localizationMap = JSONObject.parseObject(localization, HashMap.class);
                store.putAll(localizationMap);
            } catch (ConfigurationException e) {
            }
            store.forEach((k, v) -> {
                if (!k.equals(Constants.DEFAULT_LOCALIZATION.getValue()))
                    store.languagesWithOutDefault.add(k);
            });
        }
        return store;
    }

    /**
     * 根据参数获得语言
     * 
     * @param lang
     *            语言参数
     * @return String
     * @since 1.0.0
     * @author jail
     * @createTime 2015年8月22日下午10:13:36
     * @updator jail
     * @updateTime 2015年8月22日下午10:13:36
     */
    public static String getLanguage(String lang) {
        return LanguageStore.getInstance().containsKey(lang) ? lang : Constants.DEFAULT_LOCALIZATION.getValue();
    }

    /**
     * 获得所有语言版本
     * 
     * @return
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月29日下午2:40:31
     * @updator Jail Hu
     * @updateTime 2015年8月29日下午2:40:31
     */
    public static HashMap<String, String> getLanguages() {
        return LanguageStore.getInstance();
    }

    /**
     * 获得排除默认语言之外的所有语言版本
     * 
     * @return List<String>
     * @since 1.0.0
     * @author Jail Hu
     * @createTime 2015年8月27日上午10:32:40
     * @updator Jail Hu
     * @updateTime 2015年8月27日上午10:32:40
     */
    public static List<String> getLanguagesWithOutDefault() {
        return LanguageStore.getInstance().languagesWithOutDefault;
    }
}
