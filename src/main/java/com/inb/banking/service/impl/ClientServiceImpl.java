package com.inb.banking.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;
import com.inb.banking.service.ClientService;
import com.inbbank.util.GenerateUUID;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomerAccount(int accountId) {
		return clientDao.registeredCustomerAccount(accountId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomer(int clientId) {
		return clientDao.getRegisteredCustomer(clientId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer isClientAuthorized(int clientId) {
		return clientDao.getClientDetails(clientId);
	}

	/*
	 * public Customer applyNewAccount(int enquiryId, String email,Branch
	 * branch) { return clientDao.applyNewAccount(enquiryId,email,branch); }
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Account viewAccountBalance(int clientId) {
		Account account = clientDao.viewAccountBalance(clientId);
		return account;
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

}
