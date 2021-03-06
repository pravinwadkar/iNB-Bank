package com.inb.banking.service;

import java.util.List;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSBranchCustomer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.rest.entity.WSTransfer;


public interface ClientService {
	public WSCustomer isClientAuthorized(int clientId);
	
	public List<WSAccount> viewAccountBalance(int clientId);
	// sender account will be returned
	public String transferMoney(WSTransfer wsTransfer);
	// new account opening request
	public String unregisteredUser(String account);
	
	public WSBranchCustomer unregisteredUser(WSBranchCustomer account);

	public Customer registeredCustomerAccount(int clientId);
	
	public WSCustomer registeredCustomer(int clientId);
	
	public WSCustomer getRegisteredCustomer(Customer account);
	
	public List<WSBranchCustomer> getAllUnregisteredUsers();

	public List<WSBranchCustomer> getAllRegisteredUsers();

	public List<WSBranchCustomer> getAllRejectedUsers();
	
	public String unregisteredUserVerifyReject(String clientId,String email);
	
	public boolean uploadDocument(CustDocument custDocument,String email) throws Exception ;
	
	
	public List<WSBranchCustomer> getCustomerDetailsById(String id);
	
	public void unregisteredUserVerification(String id);

	public void unregisteredUserRejection(String id);
	

}
