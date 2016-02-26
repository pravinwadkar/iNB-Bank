package com.inb.banking.dao.impl;

import java.math.BigDecimal;
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
import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inbbank.util.ApplicationStatus;
import com.inbbank.util.GenerateUUID;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	SessionFactory sessionFactory;

	public Customer getClientDetails(int id) {

		Customer cust = null;
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		cr.add(Restrictions.eq("customerId", BigDecimal.valueOf(id)));
		List<Customer> list = cr.list();
		if (list != null && !list.isEmpty()) {

			return list.get(0);
		}
		return cust;
	}

	public Customer getRegisteredCustomer(int id) {
		
		Customer cust = null;
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		cr.createAlias("branch", "branch");
		cr.add(Restrictions.eq("customerId", BigDecimal.valueOf(id)));
		List<Customer> list = cr.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return cust;
		
	}

	public Customer registeredCustomerAccount(int id) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
		return customer;
	}

	public List<Account> viewAccountBalance(int id) {

		Account cust = null;
		List<Account> accList = new ArrayList<Account>();
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Account.class);
		cr.createAlias("customer", "customer");
		cr.add(Restrictions.eq("customer.customerId", BigDecimal.valueOf(id)));
		accList = cr.list();

		
		return accList;
	}

	public Account viewAccountBalance(Account account) {
		return (Account) sessionFactory.getCurrentSession().save(account);
	}

	/**
	 * This will deal save new account opening request.
	 * 
	 * @param customer
	 * @return
	 */
	public String unregisteredUser(String value) {
		String falseData = "{\"alreadyExists\" : \"false\"}";
		String trueData = "{\"alreadyExists\" : \"true\"}";
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		cr.add(Restrictions.ilike("email", value));
		List<Customer> list = cr.list();
		if (list != null && !list.isEmpty()) {
			return trueData;
		}
		return falseData;
	}

	public Customer unregisteredUser(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
		return customer;
	}

	public Customer getValidateCustomer(String userName, String password) {
		Customer customer = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("customerId", BigDecimal.valueOf(Long.valueOf(userName))));
		criteria.add(Restrictions.eq("password", password));
		Object customerObj = criteria.uniqueResult();
		if (customerObj != null) {
			customer = (Customer) customerObj;
		}
		return customer;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllUnregisteredUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.isNotNull("applicationStatus"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllRegisteredUsers() {
		// String applicationStatus = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.isNull("applicationStatus"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllRejectedUsers() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.createAlias("branch", "branch", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("accounts", "accounts", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.like("applicationStatus", "Rejected", MatchMode.ANYWHERE));
		return criteria.list();
	}

	public String unregisteredUserVerifyReject(String id, String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("email", email));
		Customer customer = (Customer) criteria.uniqueResult();
		customer.setApplicationStatus(ApplicationStatus.REJECTED.getValue());
		sessionFactory.getCurrentSession().save(customer);
		return "{ \"Success\": \"Email sent\"}";
	}

	public boolean uploadDocument(CustDocument custDocument) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("email", custDocument.getEmail()));
		Customer customer = (Customer) criteria.uniqueResult();
		custDocument.setCustomer(customer);
		custDocument.setId(GenerateUUID.getRendomString());
		sessionFactory.getCurrentSession().save(custDocument);
		return true;
	}
}
