package hj.nerp.dao.impl;

import hj.nerp.dao.IBaseDao;
import hj.nerp.dao.vo.PageInfo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {

	public void save(Object object) {
		getHibernateTemplate().save(object);
	}

	public void update(Object object) {
		getHibernateTemplate().update(object);
	}

	public void updateByQuery(final String queryString,
			final Object[] parameters) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	public void delete(Class clazz, Serializable id) {
		getHibernateTemplate().delete(load(clazz, id));
	}

	public Integer deleteAll(final Class clazz) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session.createQuery("delete "
								+ clazz.getName());
						return new Integer(query.executeUpdate());
					}
				});
	}

	public List findAll(Class clazz) {
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	public Object load(Class clazz, Serializable id) {
		return getHibernateTemplate().load(clazz, id);
	}

	public Object get(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public List findByNamedQuery(final String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	public List findByNamedQuery(final String queryName, final Object parameter) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameter);
	}

	public List findByNamedQuery(final String queryName,
			final Object[] parameters) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameters);
	}

	public List find(final String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	public List find(final String queryString, final Object[] parameters) {
		return getHibernateTemplate().find(queryString, parameters);
	}

	public List findPageByQuery(final String queryString,
			final Object[] parameters, final PageInfo pageInfo) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(queryString);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				ScrollableResults sr = query.scroll();
				sr.last();
				int totalCount = sr.getRowNumber();
				int startIndex = (pageInfo.getPageIndex() - 1)
						* pageInfo.getPageSize();
				query.setMaxResults(pageInfo.getPageSize());
				query.setFirstResult(startIndex);
				int totalRec = totalCount + 1;
				pageInfo.setTotalRec(totalRec);
				int totalPage = (totalRec % pageInfo.getPageSize() == 0) ? (totalRec / pageInfo.getPageSize())
						: (totalRec / pageInfo.getPageSize()) + 1;
				int[] pageNumbers = new int[totalPage];
				for (int i = 0; i < totalPage; i++) {
					pageNumbers[i] = (i + 1);
				}
				pageInfo.setPageNumbers(pageNumbers);
				pageInfo.setTotalPage(totalPage);
				pageInfo.setPageSize(pageInfo.getPageSize());
				pageInfo.setPageIndex(pageInfo.getPageIndex());
				pageInfo.setPrePage(pageInfo.getPageIndex() - 1);
				pageInfo.setNextPage(pageInfo.getPageIndex() + 1);
				return query.list();
			}
		});
	}

	public Integer deleteByQuery(final String queryString,
			final Object[] parameters) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Query query = session.createQuery(queryString);
						if (parameters != null) {
							for (int i = 0; i < parameters.length; i++) {
								query.setParameter(i, parameters[i]);
							}
						}
						return new Integer(query.executeUpdate());
					}
				});
	}

	public Object get(final String queryString, final Object[] parameters) {
		List list = getHibernateTemplate().find(queryString, parameters);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
