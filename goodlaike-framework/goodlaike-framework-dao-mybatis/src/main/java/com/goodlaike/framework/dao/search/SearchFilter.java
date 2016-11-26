package com.goodlaike.framework.dao.search;

import java.util.Date;

import org.springframework.util.Assert;

import com.goodlaike.framework.dao.utils.TextUtil;

/**
 * 单搜索条件
 * 
 * @author Jail Hu
 */
public class SearchFilter extends AbstractSearchFilter {

	/**
	 * 查询关键字
	 */
	private String key;

	/**
	 * 关键字条件类型
	 */
	private SearchType searchType;

	/**
	 * 查询值
	 */
	private SearchValue[] searchValues;

	/**
	 * 获得 "查询关键字",对应属性"key"
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置 "查询关键字",对应属性"key"
	 *
	 * @param key
	 *            String
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获得 "查询类型",对应属性"searchType"
	 * 
	 * @return SearchType
	 */
	public SearchType getSearchType() {
		return searchType;
	}

	/**
	 * 设置 "查询类型",对应属性"searchType"
	 *
	 * @param searchType
	 *            SearchType
	 */
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	/**
	 * 获得 "查询值",对应属性"searchValue"
	 * 
	 * @return SearchValue
	 */
	public SearchValue[] getSearchValues() {
		return searchValues;
	}

	/**
	 * 设置 "查询值",对应属性"searchValues"
	 *
	 * @param searchValue
	 *            SearchValues
	 */
	public void setSearchValues(SearchValue[] searchValues) {
		this.searchValues = searchValues;
	}

	public SearchFilter(SearchConnect connect, String key,
			SearchType searchType, SearchValue... values) {
		super(connect);
		if (searchType == SearchType.BETWEEN) {
			Assert.state(values.length == 2,
					"the BETWEEN searchType must have two searchValues");
		}
		this.key = key;
		this.searchType = searchType;
		this.searchValues = values;
	}

	/**
	 * 获得一个 and 查询
	 * 
	 * @param key
	 * @param searchType
	 * @param values
	 *            参数均为 String
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午4:14:44
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午4:14:44
	 */
	public static SearchFilter and(String key, SearchType searchType,
			Object... values) {
		return makeFilter(SearchConnect.AND, key, searchType, values);
	}

	/**
	 * 获得一个 or 查询
	 * 
	 * @param key
	 * @param searchType
	 * @param values
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午4:29:18
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午4:29:18
	 */
	public static SearchFilter or(String key, SearchType searchType,
			Object... values) {
		return makeFilter(SearchConnect.OR, key, searchType, values);
	}

	/**
	 * 创造 SearchFilter
	 * 
	 * @param connent
	 * @param key
	 * @param searchType
	 * @param values
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午4:29:29
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午4:29:29
	 */
	public static SearchFilter makeFilter(SearchConnect connent, String key,
			SearchType searchType, Object... values) {
		SearchValue[] va = new SearchValue[values.length];
		int i = 0;
		for (Object v : values) {
			if (v instanceof Number) {
				va[i] = SearchValue.number(v);
			} else if (v instanceof SearchValue) {
				va[i] = (SearchValue) v;
			} else if (v instanceof Date){
				va[i] = SearchValue.date(v);
			} else {
				va[i] = SearchValue.varchar(v);
			}
			i++;
		}
		return new SearchFilter(connent, key, searchType, va);
	}

	/**
	 * 永远不成立条件
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日下午4:27:27
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日下午4:27:27
	 */
	public static SearchFilter alwaysWrong() {
		return makeFilter(SearchConnect.AND, "1", SearchType.NOTEQUAL, 1);
	}

	/**
	 * 永远成立条件
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午4:30:34
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午4:30:34
	 */
	public static SearchFilter alwaysRight() {
		return makeFilter(SearchConnect.OR, "1", SearchType.EQUAL, 1);
	}

	/*
	 * @see com.goodlaike.dao.support.AbstractSearchFilter#getSearchClass()
	 */
	@Override
	public SearchClass getSearchClass() {
		return SearchClass.NORMAL;
	}

	/*
	 * @see com.goodlaike.dao.support.AbstractSearchFilter#toSearchString()
	 */
	@Override
	public String toSearchString() {
		StringBuilder sb = new StringBuilder(super.getConnect().toString());
		sb.append(this.key);
		switch (this.searchType) {
		case LIKELEFT:
		case LIKEBOTH:
		case LIKERIGHT:
			sb.append(TextUtil.format(this.searchType.toString(),
					this.searchValues[0].toOriginalString()));
			break;
		case BETWEEN:
			sb.append(TextUtil.format(this.searchType.toString(),
					this.searchValues[0].toFixString(),
					this.searchValues[1].toFixString()));
			break;
		default:
			sb.append(TextUtil.format(this.searchType.toString(),
					this.searchValues[0].toFixString()));
			break;
		}
		return sb.toString();
	}
}
