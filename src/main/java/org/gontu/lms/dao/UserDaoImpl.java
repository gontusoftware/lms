package org.gontu.lms.dao;

import java.util.List;

import org.gontu.lms.model.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl<T> implements UserDao<T> {

	private final Class<T> persistentClass = (Class<T>) User.class;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T findById(Long id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(persistentClass, id);
	}

	public void save(T entity) {
		// TODO Auto-generated method stub
		System.out.println((User)entity);
		getSession().merge(entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		getSession().merge(entity);
	}

	public void delete(long id) {
		// TODO Auto-generated method stub

		getSession().delete((T) getSession().get(persistentClass, id));
	}

	public List<T> getAll() {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(persistentClass);

		return (List<T>) criteria.list();
	}

	public boolean isExists(T entity) {
		if (getSession().getEntityName(entity) != null)
			return true;
		return false;
	}

	public void deleteAll() {

		final List<?> instances = getSession().createCriteria(User.class).list();
	    for (Object obj : instances) {
	    	getSession().delete(obj);
	    }
	}

}
