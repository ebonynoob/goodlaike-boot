package com.goodlaike.framework.dao.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.goodlaike.framework.dao.utils.ReflectUtil;

/**
 * 基础Dao类
 * 
 * @param <T>
 * @author Jail Hu
 */
abstract class BaseDao<T> extends SqlSessionDaoSupport {
	
	/**
	 * 用于简写namespace 的泛型类
	 */
	protected Class<T> entityClass;
	private static final String DAO_QUERY_FOR_PAGINATE = "queryForPaginate";
	private static final String DAO_COUNT_FOR_PAGINATE = "countForPaginate";
	private static final String DAO_COUNT = "count";
	private static final String DAO_FINDBYID = "findById";
	private static final String DAO_LIST = "list";
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		entityClass = ReflectUtil.getSuperClassGenricType(getClass(), 0);
	}

	/**
	 * 根据序列化参数获得对象，sqlId 默认 findById
	 * 
	 * @param id
	 *            序列化参数
	 * @return T
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月24日下午3:48:15
	 * @updator Jail Hu
	 * @updateTime 2015年3月24日下午3:48:15
	 */
	protected T selectOne(Serializable id) {
		return this.selectOne(DAO_FINDBYID, id);
	}

	/**
	 * 根据序列化参数获得对象
	 * 
	 * @param sqlId
	 *            执行的sqlId
	 * @param id
	 *            序列化参数
	 * @return T
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年7月3日上午10:17:54
	 * @updator Jail Hu
	 * @updateTime 2015年7月3日上午10:17:54
	 */
	protected T selectOne(String sqlId, Serializable id) {
		return this.getSqlSession().selectOne(this.sqlId(sqlId), id);
	}

	/**
	 * 根据对象获得记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            查询参数(对象)
	 * @return T
	 */
	protected T selectOne(String sqlId, Object obj) {
		return this.getSqlSession().selectOne(this.sqlId(sqlId), obj);
	}

	/**
	 * 获得记录列表，sqlId 默认 list
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return List<T>
	 */
	protected List<T> selectList() {
		return this.selectList(DAO_LIST);
	}

	/**
	 * 获得记录列表
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return List<T>
	 */
	protected List<T> selectList(String sqlId) {

		return this.getSqlSession().selectList(this.sqlId(sqlId));
	}

	/**
	 * 获得记录列表
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            查询参数(对象)
	 * @return List<T>
	 */
	protected List<T> selectList(String sqlId, Object obj) {

		return this.getSqlSession().selectList(this.sqlId(sqlId), obj);
	}

	/**
	 * 获得记录统计,sqlId 默认 count
	 * 
	 * @return int
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年7月3日上午10:22:24
	 * @updator Jail Hu
	 * @updateTime 2015年7月3日上午10:22:24
	 */
	protected int count() {
		return this.count(DAO_COUNT);
	}

	/**
	 * 获得记录统计
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return int
	 */
	protected int count(String sqlId) {

		return this.getSqlSession().selectOne(this.sqlId(sqlId));
	}

	/**
	 * 获得记录统计
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            查询参数（对象）
	 * @return int
	 */
	protected int count(String sqlId, Object obj) {
		return this.getSqlSession().selectOne(this.sqlId(sqlId), obj);
	}

	/**
	 * 获得 pagination 分页 <br>
	 * 分页 sqlId 默认为 queryForPaginate <br>
	 * 统计 sqlId 默认为 countForPaginate
	 *
	 * @param pagination
	 *            带返回类的查询条件
	 * @return Pagination
	 */
	protected <E> Pagination<E> getPagination(Pagination<E> pagination) {
		return this.getPagination(DAO_QUERY_FOR_PAGINATE, DAO_COUNT_FOR_PAGINATE, pagination);
	}

	/**
	 * 获得 pagination 分页，不包含返回统计数据 <br>
	 * 分页 sqlId 默认为 queryForPaginate
	 *
	 * @param pagination
	 *            带返回类的查询条件
	 * @return Pagination
	 */
	protected <E> Pagination<E> getPaginationWithoutCount(Pagination<E> pagination) {
		return this.getPagination(DAO_QUERY_FOR_PAGINATE, StringUtils.EMPTY, pagination);
	}

	/**
	 * 获得 pagination 分页，不包含返回统计数据
	 *
	 * @param listSql
	 *            分页 sqlId
	 * @param pagination
	 *            带返回类的查询条件
	 * @return Pagination
	 */
	protected <E> Pagination<E> getPaginationWithoutCount(String listSql, Pagination<E> pagination) {
		return this.getPagination(listSql, StringUtils.EMPTY, pagination);
	}

	/**
	 * 获得 pagination 分页
	 *
	 * @param listSql
	 *            分页 sqlId
	 * @param countSql
	 *            统计数据 sqlId
	 * @param pagination
	 *            带返回类的查询条件
	 * @return Pagination
	 */
	protected <E> Pagination<E> getPagination(String listSql, String countSql, Pagination<E> pagination) {
		Objects.requireNonNull(pagination, "pagination must not be null");
		Objects.requireNonNull(listSql, "the sql for list must not be null");
		int totalRecords = 0;
		if (StringUtils.isNotBlank(countSql)) {
			totalRecords = (int) this.getSqlSession().selectOne(this.sqlId(countSql), pagination);
			pagination.setTotalRecords(totalRecords);
			pagination.setList(totalRecords > 0 ? this.getSqlSession().selectList(this.sqlId(listSql), pagination)
			        : new ArrayList<E>());
		} else {
			pagination.setList(this.getSqlSession().selectList(this.sqlId(listSql), pagination));
		}

		return pagination;
	}

	/**
	 * 生生sqlId namespace.sqlId
	 *
	 * @param sqlId
	 * @return String
	 */
	protected String sqlId(String sqlId) {
		return sqlId.contains(".") ? sqlId : (entityClass.getSimpleName() + "." + sqlId);
	}
}
