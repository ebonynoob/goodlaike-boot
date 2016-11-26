package com.goodlaike.framework.dao.search;

/**
 * 查询连接条件
 * 
 * @author Jail Hu
 */
public enum SearchConnect {
	/**
	 * and查询
	 */
	AND,
	/**
	 * or 查询
	 */
	OR;

	public String toString() {
		return this.name().toLowerCase() + " ";
	}
}
