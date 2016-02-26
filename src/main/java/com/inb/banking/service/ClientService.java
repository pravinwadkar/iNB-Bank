package com.inb.banking.service;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSCustomer;


public interface ClientService {
	public WSCustomer isClientAuthorized(int clientId);
	
	public WSAccount viewAccountBalance(int clientId);
	// sender account will be returned
	public Account transferMoney(Account sender,Account reciever);
	// new account opening request
	public String unregisteredUser(String account);
	
	public Customer unregisteredUser(Customer account);

	public Customer registeredCustomerAccount(int clientId);
	
	public WSCustomer registeredCustomer(int clientId);
}
