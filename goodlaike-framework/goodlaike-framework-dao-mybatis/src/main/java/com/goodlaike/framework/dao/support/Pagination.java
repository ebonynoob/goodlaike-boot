package com.goodlaike.framework.dao.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.goodlaike.framework.dao.order.OrderFilter;
import com.goodlaike.framework.dao.search.AbstractSearchFilter;

/**
 * 分页类
 *
 * @author Jail Hu
 * @date 2014年11月12日
 */
public class Pagination<T> {
	/**
	 * 查询结果集
	 */
	private List<T> list;
	/**
	 * 查询 条件
	 */
	@JSONField(serialize = false)
	private String where;
	/**
	 * 排序 条件 ，无需输入 Order by
	 */
	@JSONField(serialize = false)
	private String order;

	/**
	 * 单独指定查询的表
	 */
	@JSONField(serialize = false)
	private String table;

	/**
	 * 单独指定输出的列
	 */
	@JSONField(serialize = false)
	private String columns;
	/**
	 * 页号 第一页为1
	 */
	private int pageNo = 1;
	/**
	 * 页尺寸
	 */
	private int pageSize = 1;

	/**
	 * 总结果数
	 */
	private int totalRecords;

	/**
	 * 获得 "查询的表",对应属性"table"
	 * 
	 * @return String
	 */
	public String getTable() {
		return table;
	}

	/**
	 * 设置 "查询的表",对应属性"table"
	 *
	 * @param table
	 *            String
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * 获得 "单独指定输出的列",对应属性"columns"
	 * 
	 * @return String
	 */
	public String getColumns() {
		return columns == null ? "*" : columns;
	}

	/**
	 * 设置 "单独指定输出的列",对应属性"columns"
	 *
	 * @param columns
	 *            String
	 */
	public void setColumns(String columns) {
		this.columns = columns;
	}

	/**
	 * 查询条件集合
	 */
	@JSONField(serialize = false)
	private List<AbstractSearchFilter> searchFilters;
	/**
	 * 排序条件集合
	 */
	@JSONField(serialize = false)
	private List<OrderFilter> orderFilters;

	/**
	 * 获得 list List<E>
	 *
	 * @return list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置 list
	 *
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 获得 where String
	 *
	 * @return where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * 设置 where
	 *
	 * @param where
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * 获得 order String
	 *
	 * @return order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置 order
	 *
	 * @param order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 获得 pageNo int
	 *
	 * @return pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置 pageNo
	 *
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获得 pageSize int
	 *
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置 pageSize
	 *
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得总记录数
	 *
	 * @return int
	 */
	public int getTotalRecords() {
		return this.totalRecords;
	}

	/**
	 * 设置总记录数
	 *
	 * @param totalRecords
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * 获得 总页面数
	 *
	 * @return int
	 */
	public int getTotalPages() {
		return this.totalRecords % this.pageSize == 0 ? this.totalRecords / this.pageSize
		        : (this.totalRecords / this.pageSize) + 1;
	}

	/**
	 * 获得 where / order 的 sql 补充语句
	 *
	 * @return String
	 */
	@JSONField(serialize = false)
	public String getExtensionSql() {
		return this.getWhereSql() + this.getOrderSql();
	}

	/**
	 * 获得 order 的sql补充语句
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月29日下午1:43:38
	 * @updator Jail Hu
	 * @updateTime 2015年3月29日下午1:43:38
	 */
	@JSONField(serialize = false)
	public String getOrderSql() {
		/*
		 * return (StringUtils.isBlank(this.getOrder()) ? "" : " order by " +
		 * this.getOrder());
		 */

		if (StringUtils.isBlank(this.getOrder()) && this.orderFilters == null) {
			return StringUtils.EMPTY;
		} else {
			StringBuilder sb = new StringBuilder(" order by ");
			boolean doNotNeedFirst = false;
			if (StringUtils.isNotBlank(this.getOrder())) {
				sb.append(this.getOrder());
				doNotNeedFirst = true;
			}
			if (this.orderFilters != null) {
				for (OrderFilter filter : this.orderFilters) {
					if (filter != null) {
						if (doNotNeedFirst) {
							sb.append(", ");
						}
						sb.append(filter.toOrderString());
					}
				}
			}
			return sb.toString();
		}
	}

	/**
	 * 获得 limit 补充 sql
	 *
	 * @return String
	 */
	@JSONField(serialize = false)
	public String getLimitSql() {
		return " limit " + ((this.getPageNo() < 1 ? 0 : this.getPageNo() - 1) * this.getPageSize()) + ","
		        + this.getPageSize();
	}

	/**
	 * 获得 where 的Sql语句，包含 where 关键词
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public String getWhereSql() {
		if (StringUtils.isBlank(this.getWhere()) && this.searchFilters == null) {
			return StringUtils.EMPTY;
		} else {
			StringBuilder sb = new StringBuilder(" where 1=1 ");
			if (StringUtils.isNotBlank(this.getWhere())) {
				sb.append("and " + this.getWhere());
			}
			if (this.searchFilters != null) {
				for (AbstractSearchFilter filter : this.searchFilters) {
					if (filter != null) {
						sb.append(filter.toSearchString());
					}
				}
			}
			return sb.toString();
		}
	}

	/**
	 * Sql Server 使用！ 获得 row_number 的Sql 语句，包含 order By
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public String getRowNumberSql() {
		return " row_number() over (" + this.getOrderSql() + ") as rowNumberId ";
	}

	/**
	 * Sql Server 使用！ 获得 分页的sql 语句，包含 where
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public String getPageSql() {
		return " where rowNumberId between " + ((this.getPageNo() < 1 ? 0 : this.getPageNo() - 1) * this.getPageSize() + 1)
		        + " and " + (this.getPageNo() < 1 ? 0 : this.getPageNo()) * this.getPageSize();
	}

	@Override
	public String toString() {
		return "Pagination [list=" + list + ", where=" + where + ", order=" + order + ", pageNo=" + pageNo
		        + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords + ", getTotalPages()=" + getTotalPages()
		        + ", getExtensionSql()=" + getExtensionSql() + ", getLimitSql()=" + getLimitSql() + "]";
	}

	public Pagination<T> addFilters(AbstractSearchFilter... filters) {
		if (filters.length > 0) {
			if (this.searchFilters == null) {
				this.searchFilters = new ArrayList<AbstractSearchFilter>();
			}
			if (filters.length > 1) {
				this.searchFilters.addAll(Arrays.asList(filters));
			} else {
				this.searchFilters.add(filters[0]);
			}
		}
		return this;
	}

	/**
	 * 添加排序过滤器
	 * 
	 * @param filters
	 * @return
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年4月2日下午4:01:52
	 * @updator Jail Hu
	 * @updateTime 2015年4月2日下午4:01:52
	 */
	public Pagination<T> addFilters(OrderFilter... filters) {
		if (filters.length > 0) {
			if (this.orderFilters == null) {
				this.orderFilters = new ArrayList<OrderFilter>();
			}
			if (filters.length > 1) {
				this.orderFilters.addAll(Arrays.asList(filters));
			} else {
				this.orderFilters.add(filters[0]);
			}
		}
		return this;
	}
}
