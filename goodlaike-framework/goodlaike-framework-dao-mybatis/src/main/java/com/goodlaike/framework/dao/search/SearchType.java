package com.goodlaike.framework.dao.search;

/**
 * 搜索比较类型
 * 
 * @author Jail Hu
 */
public enum SearchType {
	/**
	 * 等于
	 */
	EQUAL,
	/**
	 * 不等于
	 */
	NOTEQUAL,
	/**
	 * 大于
	 */
	GREATERTHAN,
	/**
	 * 小于
	 */
	LESSTHAN,
	/**
	 * 大于等于
	 */
	GREATERTHANOREQUAL,
	/**
	 * 小于等于
	 */
	LESSTHANOREQUAL,
	/**
	 * 包含
	 */
	BETWEEN,
	/**
	 * 前后%的like查询
	 */
	LIKEBOTH,
	/**
	 * 前%的like查询
	 */
	LIKELEFT,
	/**
	 * 后%的like查询
	 */
	LIKERIGHT;

	public String toString() {
		switch (this) {
		case EQUAL:
			return " ={0} ";
		case NOTEQUAL:
			return " !={0} ";
		case GREATERTHAN:
			return " >{0} ";
		case LESSTHAN:
			return " <{0} ";
		case GREATERTHANOREQUAL:
			return " >={0} ";
		case LESSTHANOREQUAL:
			return " <={0} ";
		case LIKEBOTH:
			return " like '%{0}%' ";
		case LIKELEFT:
			return " like '%{0}' ";
		case LIKERIGHT:
			return " like '{0}%' ";
		case BETWEEN:
			return " between {0} and {1} ";
		}
		return "";
	}
}
