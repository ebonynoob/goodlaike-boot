package com.goodlaike.framework.dao.search;


/**
 * 抽象查询
 * 
 * @author Jail Hu
 */
public abstract class AbstractSearchFilter {

	/**
	 * 查询连接
	 */
	private SearchConnect connect;

	/**
	 * 获得 "查询连接",对应属性"connect"
	 * 
	 * @return SearchConnect
	 */
	public SearchConnect getConnect() {
		return connect;
	}

	/**
	 * 设置 "查询连接",对应属性"connect"
	 *
	 * @param connect
	 *            SearchConnect
	 */
	public void setConnect(SearchConnect connect) {
		this.connect = connect;
	}

	/**
	 * 获得查询类型
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日下午12:11:54
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日下午12:11:54
	 */
	public abstract SearchClass getSearchClass();

	/**
	 * 获得查询Sql语句
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日下午12:12:00
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日下午12:12:00
	 */
	public abstract String toSearchString();
	
	public AbstractSearchFilter(SearchConnect connect) {
		this.connect = connect;
	}
	
	
}
