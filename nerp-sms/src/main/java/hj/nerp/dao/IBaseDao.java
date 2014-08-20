package hj.nerp.dao;

import hj.nerp.dao.vo.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao{

	/**
	 * 保存（持久化）一个对象
	 * 
	 * @param object
	 *            要保存的对象
	 */
	public abstract void save(Object object);

	/**
	 * 更新一个对象
	 * 
	 * @param object
	 *            要修改的对象
	 */
	public abstract void update(Object object);

	/**
	 * 用语句更新记录
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            参数
	 */
	public abstract void updateByQuery(final String queryString,
			final Object[] parameters);

	/**
	 * 删除一个对象
	 * 
	 * @param object
	 *            要删除的对象
	 */
	public abstract void delete(Object object);

	/**
	 * 根据类型和对象id删除一个对象
	 * 
	 * @param clazz
	 *            类型
	 * @param id
	 *            对象id
	 */
	public abstract void delete(Class clazz, Serializable id);

	/**
	 * 根据类型删除全部对象
	 * 
	 * @param clazz
	 *            类型
	 * @return Integer
	 */
	public abstract Integer deleteAll(final Class clazz);

	/**
	 * 根据查询和参数删除全部对象
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            参数
	 * @return Integer
	 */
	public abstract Integer deleteByQuery(final String queryString,
			final Object[] parameters);

	/**
	 * 获得某个类型的全部对象列表
	 * 
	 * @param clazz
	 *            类型
	 * @return 对象集合
	 */
	public abstract List findAll(Class clazz);

	/**
	 * 根据类型和对象id载入一个对象
	 * 
	 * @param clazz
	 *            类型
	 * @param id
	 *            对象id
	 * @return 目标对象
	 */
	public abstract Object load(Class clazz, Serializable id);

	/**
	 * 根据类型和对象id从数据库取得一个对象
	 * 
	 * @param clazz
	 *            类
	 * @param id
	 *            对象id
	 * @return 目标对象
	 */
	public abstract Object get(Class clazz, Serializable id);

	/**
	 * 根据查询语句和查询参数从数据库取得一个对象
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            参数
	 * @return Object 单个对象
	 */
	public abstract Object get(final String queryString, final Object[] parameters);

	/**
	 * 命名查询
	 * 
	 * @param queryName
	 *            命名查询语句
	 * @return 对象列表
	 */
	public abstract List findByNamedQuery(final String queryName);

	/**
	 * 依据单个参数做命名查询
	 * 
	 * @param query
	 *            命名查询语句
	 * @param parameter
	 *            单个查询参数
	 * @return 对象列表
	 */
	public abstract List findByNamedQuery(final String queryString,
			final Object parameter);

	/**
	 * 依据参数数组做命名查询
	 * 
	 * @param query
	 *            命名查询语句
	 * @param parameters
	 *            查询参数数组
	 * @return 对象列表
	 */
	public abstract List findByNamedQuery(final String queryString,
			final Object[] parameters);

	/**
	 * 查询全部
	 * 
	 * @param query
	 *            查询语句
	 * @return 对象列表
	 */
	public abstract List find(final String queryString);

	/**
	 * 带参数查询全部
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            查询参数
	 * @return 对象列表
	 */
	public abstract List find(final String queryString, final Object[] parameters);

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            参数
	 * @param pageInfo
	 *            分页信息
	 * @return List 对象列表
	 */
	public abstract List findPageByQuery(final String queryString,
			final Object[] parameters, final PageInfo pageInfo);

}
