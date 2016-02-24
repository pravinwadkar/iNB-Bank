package com.src.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;

import com.src.banking.dao.ClientDao;
import com.src.banking.entity.Account;
import com.src.banking.entity.Branch;
import com.src.banking.entity.Customer;
import com.src.banking.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;
	@Autowired
	HibernateTransactionManager transactionManager;

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
}
