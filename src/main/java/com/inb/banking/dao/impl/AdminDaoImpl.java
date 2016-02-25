package com.inb.banking.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.AdminDao;
import com.inb.banking.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional()
	public Admin findAdminByUsernameAndPassword(String username,String password) {
		Session session = null;
		Admin admin1 = null;
		try {
			session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Admin.class);
			cr.add(Restrictions.eq("userName", username));
			cr.add(Restrictions.eq("password", password));
			List<Admin> results = cr.list();
			if(results != null && results.size() > 0){
			for (Admin admin : results) {
				admin1 = admin;
			}	}
		} catch (HibernateException e) {
			throw e;
		}finally {
			session.close();
		}
		
		return admin1;
	}
}
