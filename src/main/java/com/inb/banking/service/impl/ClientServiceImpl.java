package com.inb.banking.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.Branch;
import com.inb.banking.entity.Customer;
import com.inb.banking.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;

	public boolean isClientAuthorized(int clientId) {
		System.out.println("in ClientServiceImpl");
		// if it is not first time login we need to provide the image and text questions?
		clientDao.getClientDetails(clientId);
		return false;
	}

	public Customer applyNewAccount(int enquiryId, String email,Branch branch) {
		return clientDao.applyNewAccount(enquiryId,email,branch);
	}
	
	public Account viewAccountBalance(int clientId){
		return clientDao.viewAccountBalance(clientId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public Account transferMoney(Account sender, Account reciever) {
		BigDecimal fundTransferBalance = sender.getBalance().subtract(new BigDecimal("50"))  ;
		sender.setBalance(fundTransferBalance);
		return clientDao.viewAccountBalance(sender);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public Customer unregisteredUser(Customer customer){
		return clientDao.unregisteredUser(customer);
	}
}
