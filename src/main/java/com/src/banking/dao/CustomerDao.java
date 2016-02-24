package com.src.banking.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.src.banking.entity.Customer;

public class CustomerDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Customer findCustomerByUsernameAndPassword(String username,String password) {
		Session session = null;
		Customer cust = null;
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.ilike("username", username));
		cr.add(Restrictions.ilike("password", username));
		cust = (Customer)cr.list().get(0);
		return cust;
	}

}
