package com.inb.banking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.entity.Customer;

@Repository
public class CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional()
	public Customer findCustomerByUsernameAndPassword(String username,String password) {
		Session session = null;
		Customer cust = null;
		try {
			session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Customer.class);
			cr.add(Restrictions.eq("userName", username));
			cr.add(Restrictions.eq("password", password));
			List<Customer> results = cr.list();
			System.out.println(results);
			if(results != null && results.size() > 0){
			for (Customer customer : results) {
				cust = customer;
			}	}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return cust;
	}

}
