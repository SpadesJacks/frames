/**
 * 
 */
package com.hibernate.dao.impl;

import com.hibernate.dao.IUserDao;
import com.hibernate.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author phn
 */

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	// extends BaseDaoImpl<UserEntity, Integer>
	@Resource
	protected SessionFactory sessionFactory;


	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean isExist(UserEntity user) {
		Session session = this.getSession();
		String hql = "from UserEntity where username =? and password = ?";
		Query query = session.createQuery(hql);
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		if (query.list().size() >= 1) {
			return true;
		}
		return false;
	}

	public UserEntity findOne(Long id) {
		Session session = this.getSession();
		String hql = "from UserEntity where id = ?";
		Query query = session.createQuery(hql);
		query.setLong(0, id);
		if (query.list().size() <= 0) {
			return null;
		}
		return (UserEntity) query.list().get(0);
	}


}
