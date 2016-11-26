package com.goodlaike.framework.dao.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 搜索条件集合
 * 
 * @author Jail Hu
 */
public class SearchFilterSet extends AbstractSearchFilter {

	private List<SearchFilter> filterList;

	/**
	 * @param connect
	 */
	public SearchFilterSet(SearchConnect connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	public SearchFilterSet addFilters(SearchFilter... filters) {
		if (this.filterList == null) {
			this.filterList = new ArrayList<SearchFilter>();
		}
		this.filterList.addAll(Arrays.asList(filters));
		return this;
	}

	public static SearchFilterSet and(SearchFilter... filterList) {
		return new SearchFilterSet(SearchConnect.AND).addFilters(filterList);
	}

	/*
	 * @see com.goodlaike.dao.support.AbstractSearchFilter#getSearchClass()
	 */
	@Override
	public SearchClass getSearchClass() {
		return SearchClass.SET;
	}

	/*
	 * @see com.goodlaike.dao.support.AbstractSearchFilter#toSearchString()
	 */
	@Override
	public String toSearchString() {
		if (filterList == null) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder(super.getConnect().toString()
					+ " (");
			int i = 0;
			for (SearchFilter filter : this.filterList) {
				if (i == 0) {
					String s = filter.toSearchString();
					if (s.startsWith(SearchConnect.AND.toString())) {
						sb.append(s.replaceFirst(SearchConnect.AND.toString(),
								""));
					} else if (s.startsWith(SearchConnect.OR.toString())) {
						sb.append(s.replaceFirst(SearchConnect.OR.toString(),
								""));
					} else {
						sb.append(s);
					}
				} else {
					sb.append(filter.toSearchString());
				}
				i++;
			}
			sb.append(") ");
			return sb.toString();
		}
	}
}
