package com.inb.banking.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;

@Repository
public class ClientDaoImpl {

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
	/**
	 * 
	 * @param enquiryId
	 * @param email
	 * @return
	 */
	/*public Customer applyNewAccount(int enquiryId, String email, Branch branch) {
		if (enquiryId != 0 && email != null) {
			Session session = sessionFactory.openSession();
			Customer customer = new Customer();
			customer.setBranch(branch);
			customer.setEnqid(new BigDecimal(enquiryId));
			customer.setEmail("email address ");
			// Transaction transaction = session.beginTransaction();
			// as the branch doesn't exist till now
			session.save(branch);
			///////////////////////
			// session.save(customer);
			int customerId = customer.getId();
			System.out.println(" customerId is : " + customerId);
			// transaction.commit();
			// session.close();
			sessionFactory.getCurrentSession().save(customer);
			return customer;
		}
		return null;
	}*/

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
}
