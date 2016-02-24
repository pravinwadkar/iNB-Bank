package com.src.banking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.src.banking.entity.Customer;

@Repository
public class CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/
	
	@Transactional()
	public Customer findCustomerByUsernameAndPassword(String username,String password) {
		Session session = null;
		Customer cust = null;
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.ilike("username", username));
		cr.add(Restrictions.ilike("password", password));
		List<Customer> results = cr.list();
		if(results != null && results.size() > 0){
		cust = (Customer)cr.list().get(0);
		}
		return cust;
	}

}
