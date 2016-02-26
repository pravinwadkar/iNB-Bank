package com.inb.banking.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSCustomer;

@Repository
public class ClientDaoImpl implements ClientDao{

	@Autowired
	SessionFactory sessionFactory;

	public Customer getClientDetails(int id) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
		return customer;
	}

	
	public Customer getRegisteredCustomer(int id) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
		return customer;
	}
	
	public Customer registeredCustomerAccount(int id) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
		return customer;
	}
	
	public Account viewAccountBalance(int id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, id);
	}

	public Account viewAccountBalance(Account account) {
		return (Account) sessionFactory.getCurrentSession().save(account);
	}
	
	/**
	 * This will deal save new account opening request.
	 * @param customer
	 * @return
	 */
	public String unregisteredUser(String value) {
		String falseData = "{\"alreadyExists\" : \"false\"}";
		String trueData = "{\"alreadyExists\" : \"true\"}";
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		cr.add(Restrictions.ilike("email", value));
		List<Customer> list = cr.list();
		if(list != null && ! list.isEmpty()){
			return trueData;
		}
		return falseData;
	}
	
	public Customer unregisteredUser(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
		return customer ;
	}


	public Customer getValidateCustomer(int customerId, String userName, String password) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));
		Customer customer = (Customer)criteria.uniqueResult();
		return customer;
		
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllUnregisteredUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.like("applicationStatus", "Pending",MatchMode.ANYWHERE));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	public List<Customer> getAllRegisteredUsers() {
		//String applicationStatus = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.isNull("applicationStatus"));
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	public List<Customer> getAllRejectedUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.like("applicationStatus", "Rejected",MatchMode.ANYWHERE));
		return criteria.list();
	}
}
