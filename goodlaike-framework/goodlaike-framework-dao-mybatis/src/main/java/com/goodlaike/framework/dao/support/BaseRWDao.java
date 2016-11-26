package com.goodlaike.framework.dao.support;

import java.io.Serializable;
import java.util.List;

/**
 * 读写的BaseDao
 * 
 * @param <T>
 * @author Jail Hu
 */
public abstract class BaseRWDao<T> extends BaseDao<T> {
    
	private static final String DAO_INSERT = "insert";
	private static final String DAO_UPDATE = "update";
	private static final String DAO_DELETE = "delete";
	private static final String DAO_BATCHINSERT = "batchInsert";
	private static final String DAO_INSERTANDRETURNID = "insertAndReturnId";

	/**
	 * 插入记录，sqlId 默认为 insert
	 * 
	 * @param t
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月24日下午2:43:58
	 * @updator Jail Hu
	 * @updateTime 2015年3月24日下午2:43:58
	 */
	protected boolean insert(T t) {
		return super.getSqlSession().insert(this.sqlId(DAO_INSERT), t) > 0;
	}

	/**
	 * 插入记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return boolean
	 */
	protected boolean insert(String sqlId) {
		return super.getSqlSession().insert(this.sqlId(sqlId)) > 0;
	}

	/**
	 * 插入记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 * @return boolean
	 */
	protected boolean insert(String sqlId, Object obj) {
		return super.getSqlSession().insert(this.sqlId(sqlId), obj) > 0;
	}

	/**
	 * 根据对象插入记录，并返回主键id，sqlId 默认为 insertAndReturnId
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            对象名称 mysql xml 返回主键写法 <selectKey
	 *            resultType="java.lang.Integer" order="AFTER" keyProperty="id"
	 *            > SELECT LAST_INSERT_ID() AS ID </selectKey>
	 * @return int 影响行数
	 */
	protected int insertAndReturnId(T obj) {
		return this.insertAndReturnId(DAO_INSERTANDRETURNID, obj);
	}

	/**
	 * 根据对象插入记录，并返回主键id
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            对象名称 mysql xml 返回主键写法 <selectKey
	 *            resultType="java.lang.Integer" order="AFTER" keyProperty="id"
	 *            > SELECT LAST_INSERT_ID() AS ID </selectKey>
	 * @return int
	 */
	protected int insertAndReturnId(String sqlId, Object obj) {
		return super.getSqlSession().insert(this.sqlId(sqlId), obj);
	}

	/**
	 * 批量插入记录，sqlId 默认为 batchInsert <br>
	 * 请到 xml 中使用 《foreach collection="list" item="item" index="index"
	 * separator="," 》 《/foreach》实现
	 * 
	 * @param List
	 *            <T> 对象List
	 * @return boolean
	 */
	protected boolean batchInsert(List<T> list) {
		return super.getSqlSession().insert(this.sqlId(DAO_BATCHINSERT), list) > 0;
	}

	/**
	 * 根据对象删除记录，sqlId 默认为 delete
	 * 
	 * @param t
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月24日下午2:46:01
	 * @updator Jail Hu
	 * @updateTime 2015年3月24日下午2:46:01
	 */
	protected boolean delete(T t) {
		return super.getSqlSession().delete(this.sqlId(DAO_DELETE), t) > 0;
	}

	/**
	 * 根据序列化参数删除记录，sqlId 默认为 delete
	 * 
	 * @param id
	 *            序列化参数
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月24日下午3:57:33
	 * @updator Jail Hu
	 * @updateTime 2015年3月24日下午3:57:33
	 */
	protected boolean delete(Serializable id) {
		return super.getSqlSession().delete(this.sqlId(DAO_DELETE), id) > 0;
	}

	/**
	 * 删除记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return boolean
	 */
	protected boolean delete(String sqlId) {
		return super.getSqlSession().delete(this.sqlId(sqlId)) > 0;
	}

	/**
	 * 根据对象删除记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            查询参数（对象）
	 * @return boolean
	 */
	protected boolean delete(String sqlId, Object obj) {
		return super.getSqlSession().delete(this.sqlId(sqlId), obj) > 0;
	}

	/**
	 * 更新记录， sqlId 默认为 update
	 * 
	 * @param t
	 * @return boolean
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月24日下午2:44:40
	 * @updator Jail Hu
	 * @updateTime 2015年3月24日下午2:44:40
	 */
	protected boolean update(T t) {
		return super.getSqlSession().update(this.sqlId(DAO_UPDATE), t) > 0;
	}

	/**
	 * 执行指定sqlId更新记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @return boolean
	 */
	protected boolean update(String sqlId) {
		return super.getSqlSession().update(this.sqlId(sqlId)) > 0;
	}

	/**
	 * 根据对象更新记录
	 *
	 * @param sqlId
	 *            xml的id,如果sqlId包含命名空间则直接用sqlId，否则增加 T 为 命名空间,比如 A.B 则不变，不然会调整为
	 *            A.B
	 * @param obj
	 *            查询参数（对象）
	 * @return boolean
	 */
	protected boolean update(String sqlId, Object obj) {
		return super.getSqlSession().update(this.sqlId(sqlId), obj) > 0;
	}
}
