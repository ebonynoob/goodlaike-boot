package com.goodlaike.business.core.support;

/**
 * 语言枚举类
 * @author jail
 */
public enum Localization {

    CN("cn", "中文");

    /**
     * 语言值
     */
    private String value;
    /**
     * 语言文本
     */
    private String text;

    private Localization(String value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 获得 "语言值",对应属性"value"
     * 
     * @return String
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 设置 "语言值",对应属性"value"
     *
     * @param value
     *            String
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获得 "语言文本",对应属性"text"
     * 
     * @return String
     */
    public String getText() {
        return this.text;
    }

    /**
     * 设置 "语言文本",对应属性"text"
     *
     * @param text
     *            String
     */
    public void setText(String text) {
        this.text = text;
    }
}
