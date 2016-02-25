package com.inb.banking.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Branch;
import com.inb.banking.entity.Customer;

@Repository
public class ClientDao {

	@Autowired
	SessionFactory sessionFactory;

	public Customer getClientDetails(int clientId) {
		// select * from client where clientId = ? and clientName=""

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria("From Client");
		List<Customer> customerDetails = criteria.list();

		for (Customer client : customerDetails) {
			Customer cli = (Customer) client;
			System.out.println("cli.getClientId() :: " + cli.getId());
			System.out.println("cli.getAddress() :: " + cli.getAddress());
			System.out.println("cli.getAddress() :: " + cli.getApplicationstatus());
			System.out.println("cli.getAddress() :: " + cli.getAuthorizedimagename());
			System.out.println("cli.getAddress() :: " + cli.getEmail());
			System.out.println("cli.getAddress() :: " + cli.getEnqid());
			System.out.println("cli.getAddress() :: " + cli.getFirstname());
			System.out.println("cli.getAddress() :: " + cli.getLastname());
			System.out.println("cli.getAddress() :: " + cli.getUsername());
			System.out.println("cli.getAddress() :: " + cli.getAccounts());
			System.out.println("cli.getAddress() :: " + cli.getBranch());
			System.out.println("cli.getAddress() :: " + cli.getCustDocuments());
			System.out.println("cli.getAddress() :: " + cli.getCustomerid());
			System.out.println("cli.getAddress() :: " + cli.getDateofbirth());
			System.out.println("cli.getAddress() :: " + cli.getPhone());

		}
		return null;
	}

	/**
	 * 
	 * @param enquiryId
	 * @param email
	 * @return
	 */
	public Customer applyNewAccount(int enquiryId, String email, Branch branch) {
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
	}

	public Account viewAccountBalance(int clientId) {
		Session session = sessionFactory.openSession();
		/////////////////////////
		Account tempAccount = new Account();
		tempAccount.setBalance(new BigDecimal(20000));
		Transaction tx = session.beginTransaction();
		session.save(tempAccount);
		clientId = tempAccount.getId();
		/////////////////////////
		Account account = (Account) session.load(Account.class, clientId);
		System.out.println("account balance is : " + account.getBalance());
		tx.commit();
		return account;
	}

	public Account viewAccountBalance(Account account) {
		return (Account) sessionFactory.getCurrentSession().save(account);
	}
	
	/**
	 * This will deal save new account opening request.
	 * @param customer
	 * @return
	 */
	public Customer unregisteredUser(Customer customer) {
		////////////////////////
		sessionFactory.getCurrentSession().save(customer.getBranch());
		///////////////////////
		Integer customerId =(Integer) sessionFactory.getCurrentSession().save(customer);
		customer.setCustomerid(new BigDecimal(customerId));
		return customer;
	}
}
