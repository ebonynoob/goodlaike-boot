package com.goodlaike.framework.dao.search;

import java.text.SimpleDateFormat;

import org.springframework.util.Assert;

/**
 * 查询的值
 * 
 * @author Jail Hu
 */
public class SearchValue {

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

	/**
	 * 查询值
	 */
	private Object value;
	/**
	 * 查询值类型
	 */
	private SearchValueType valueType;

	/**
	 * 获得 "查询值",对应属性"value"
	 * 
	 * @return Object
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置 "查询值",对应属性"value"
	 *
	 * @param value
	 *            Object
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 获得 "查询值类型",对应属性"valueType"
	 * 
	 * @return SearchValueType
	 */
	public SearchValueType getValueType() {
		return valueType;
	}

	/**
	 * 设置 "查询值类型",对应属性"valueType"
	 *
	 * @param valueType
	 *            SearchValueType
	 */
	public void setValueType(SearchValueType valueType) {
		this.valueType = valueType;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchValue [value=" + value + ", valueType=" + valueType + "]";
	}

	public SearchValue(SearchValueType valueType, Object value) {
		this.valueType = valueType;
		this.value = value;
	}

	/**
	 * 字符串型的查询值
	 * 
	 * @param value
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日上午11:11:05
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日上午11:11:05
	 */
	public static SearchValue varchar(Object value) {
		return new SearchValue(SearchValueType.VARCHAR, value);
	}

	/**
	 * 数值型的查询值
	 * 
	 * @param value
	 * @return SearchValue
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日上午11:11:43
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日上午11:11:43
	 */
	public static SearchValue number(Object value) {
		return new SearchValue(SearchValueType.NUMBER, value);
	}

	/**
	 * 日期型的查询值
	 * 
	 * @param value
	 * @return SearchValue
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年7月3日下午4:47:38
	 * @updator Jail Hu
	 * @updateTime 2015年7月3日下午4:47:38
	 */
	public static SearchValue date(Object value) {
		return new SearchValue(SearchValueType.DATE, value);
	}

	public String toFixString() {
		switch (this.valueType) {
		case NUMBER:
			return String.valueOf(this.value);
		case DATE:
			return "'" + dateFormatter.format(this.value) + "'";
		default:
			return "'" + String.valueOf(this.value) + "'";
		}
	}

	/**
	 * 用于like查询的原始String
	 * 
	 * @return String
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年7月3日下午5:06:48
	 * @updator Jail Hu
	 * @updateTime 2015年7月3日下午5:06:48
	 */
	public String toOriginalString() {
		Assert.state(this.valueType != SearchValueType.DATE, "日期型 不支持like 查询");
		return String.valueOf(this.value);
	}

}
