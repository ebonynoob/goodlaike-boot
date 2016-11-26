package com.goodlaike.framework.dao.order;

/**
 * 
 * @author Jail Hu
 */
public class OrderFilter {

	/**
	 * 排序字段
	 */
	private String key;
	/**
	 * 排序类型
	 */
	private OrderType orderType;

	/**
	 * 获得 "排序字段",对应属性"key"
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置 "排序字段",对应属性"key"
	 *
	 * @param key
	 *            String
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获得 "排序类型",对应属性"orderType"
	 * 
	 * @return OrderType
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * 设置 "排序类型",对应属性"orderType"
	 *
	 * @param orderType
	 *            OrderType
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderFilter [key=" + key + ", orderType=" + orderType + "]";
	}

	/**
	 * 默认构造函数
	 */
	public OrderFilter() {
		// TODO Auto-generated constructor stub
	}

	public OrderFilter(String key, OrderType orderType) {
		this.key = key;
		this.orderType = orderType;
	}

	/**
	 * 返回正序排序过滤器
	 * 
	 * @param key
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午3:55:10
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午3:55:10
	 */
	public static OrderFilter asc(String key) {
		return new OrderFilter(key, OrderType.ASC);
	}

	/**
	 * 返回倒序排序过滤器
	 * 
	 * @param key
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午3:54:56
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午3:54:56
	 */
	public static OrderFilter desc(String key) {
		return new OrderFilter(key, OrderType.DESC);
	}

	public String toOrderString() {
		return this.key + " " + this.orderType.toString();
	}
}
