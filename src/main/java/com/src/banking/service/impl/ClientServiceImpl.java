package com.src.banking.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Service;

import com.src.banking.dao.ClientDao;
import com.src.banking.entity.Client;
import com.src.banking.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientDao clientDao;
	@Autowired
	HibernateTransactionManager transactionManager;

//	@Override
	public boolean isValidClient(Client client) {
		System.out.println("in ClientServiceImpl");
		clientDao.getClientDetails(client.getClientId());
		return false;
	}

}
