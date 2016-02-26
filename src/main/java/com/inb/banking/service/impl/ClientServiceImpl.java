package com.inb.banking.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.service.ClientService;
import com.inbbank.util.GenerateUUID;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;
	@Autowired
	private DozerBeanMapper mapper;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomerAccount(int accountId) {
		return clientDao.registeredCustomerAccount(accountId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer registeredCustomer(int clientId) {
		WSCustomer wsCustomer =null; 
		Customer customer = clientDao.getRegisteredCustomer(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer isClientAuthorized(int clientId) {
		WSCustomer wsCustomer =null;
		Customer customer = clientDao.getClientDetails(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSAccount viewAccountBalance(int clientId) {
		WSAccount wsAccount =null;
		Account account = clientDao.viewAccountBalance(clientId);
		wsAccount = mapper.map(account, WSAccount.class);
		return wsAccount;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Account transferMoney(Account sender, Account reciever) {
		BigDecimal fundTransferBalance = sender.getBalance().subtract(new BigDecimal("50"));
		sender.setBalance(fundTransferBalance);
		return clientDao.viewAccountBalance(sender);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String unregisteredUser(String customer) {
		return clientDao.unregisteredUser(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Customer unregisteredUser(Customer account) {
		account.setId(GenerateUUID.getRendomString());
		return clientDao.unregisteredUser(account);
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer getRegisteredCustomer(Customer customer) {

		WSCustomer wsCustomer =null;
		List<WSCustomer> list = new ArrayList<WSCustomer>(); 
		Customer customerData = clientDao.getValidateCustomer(customer.getCustomerId().intValue(),customer.getUserName(),customer.getPassword());
		//customerData.setBranch(null);
		wsCustomer = mapper.map(customerData, WSCustomer.class);
		return wsCustomer;
	}

}
