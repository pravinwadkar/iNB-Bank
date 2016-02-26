package com.inb.banking.dao;

import org.springframework.stereotype.Repository;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;

@Repository
public interface ClientDao {


	public Customer getClientDetails(int id) ;

	
	public Customer getRegisteredCustomer(int id) ;
	
	public Customer registeredCustomerAccount(int id) ;

	public Account viewAccountBalance(int id) ;

	public Account viewAccountBalance(Account account) ;
	
	/**
	 * This will deal save new account opening request.
	 * @param customer
	 * @return
	 */
	public String unregisteredUser(String value) ;
	
	public Customer unregisteredUser(Customer customer) ;
	
	public Customer  getValidateCustomer(int customerId,String userName,String password);
}
