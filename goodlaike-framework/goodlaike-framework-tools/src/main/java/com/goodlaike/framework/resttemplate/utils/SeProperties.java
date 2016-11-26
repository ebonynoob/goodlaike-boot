package com.goodlaike.framework.resttemplate.utils;


import java.util.Properties;

import org.apache.commons.collections.MapUtils;


/**
 * SeProperties
 *
 * @author ：liuxing
 * @since ：2015-07-14 01:11
 */
public class SeProperties {

    private Properties props;

    @SuppressWarnings("unused")
    private SeProperties() {}

    public SeProperties(Properties props) {
        this.props = props;
    }

    public String getString(String key, String defaultValue) {
        return MapUtils.getString(props, key, defaultValue);
    }

    public Boolean getBoolean(String key) {
        return MapUtils.getBoolean(props, key);
    }

    public Integer getInt(String key) {
        return MapUtils.getInteger(props, key);
    }

    public Long getLong(String key) {
        return MapUtils.getLong(props, key);
    }

    public Short getShort(String key) {
        return MapUtils.getShort(props, key);
    }

    public Float getFloat(String key) {
        return MapUtils.getFloat(props, key);
    }

    public Double getDouble(String key) {
        return MapUtils.getDouble(props, key);
    }

    public Byte getByte(String key) {
        return MapUtils.getByte(props, key);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return MapUtils.getBooleanValue(props, key, defaultValue);
    }

    public int getIntValue(String key, int defaultValue) {
        return MapUtils.getIntValue(props, key, defaultValue);
    }

    public long getLongValue(String key, long defaultValue) {
        return MapUtils.getLongValue(props, key, defaultValue);
    }

    public short getShortValue(String key, short defaultValue) {
        return MapUtils.getShortValue(props, key, defaultValue);
    }

    public float getFloatValue(String key, float defaultValue) {
        return MapUtils.getFloatValue(props, key, defaultValue);
    }

    public double getDoubleValue(String key, double defaultValue) {
        return MapUtils.getDoubleValue(props, key, defaultValue);
    }

    public byte getByteValue(String key, byte defaultValue) {
        return MapUtils.getByteValue(props, key, defaultValue);
    }

}
