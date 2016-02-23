package com.src.banking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.src.banking.entity.Client;

@Repository
public class ClientDao {
	

	public Client getClientDetails(int clientId) {
		// select * from client where clientId = ? and clientName=""
		System.out.println("in ClientDao");
		return null;
	}

}
