package com.inb.banking.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSTransfer;

@Repository
public interface ClientDao {


	public Customer getClientDetails(int id) ;

	
	public Customer getRegisteredCustomer(int id) ;
	
	public Customer registeredCustomerAccount(int id) ;

	public List<Account> viewAccountBalance(int id) ;

	public Account viewAccountBalance(Account account) ;
	
	/**
	 * This will deal save new account opening request.
	 * @param customer
	 * @return
	 */
	public String unregisteredUser(String value) ;
	
	public Customer unregisteredUser(Customer customer) ;
	
	public Customer  getValidateCustomer(String userName,String password);
	
	public List<Customer> getAllUnregisteredUsers();


	public List<Customer> getAllRegisteredUsers();


	public List<Customer> getAllRejectedUsers();
	
	public String unregisteredUserVerifyReject(String clientId,String email);
	
	public boolean uploadDocument(CustDocument custDocument) throws Exception;


	public Customer getCustomerDetailsById(String id);
	
	public BigDecimal getAccountBalance(int accountNumber);


	public void transfer(WSTransfer wsTransfer);


}
