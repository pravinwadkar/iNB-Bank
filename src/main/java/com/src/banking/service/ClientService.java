package com.src.banking.service;

import com.src.banking.entity.Account;
import com.src.banking.entity.Branch;
import com.src.banking.entity.Customer;


public interface ClientService {
	public boolean isClientAuthorized(int clientId);
	
	public Customer applyNewAccount(int enquiryId,String email,Branch branch);
	
	public Account viewAccountBalance(int clientId);
	// sender account will be returned
	public Account transferMoney(Account sender,Account reciever);
	// new account opening request
	public Customer unregisteredUser(Customer account);
}
