package com.src.banking.service;

import org.springframework.stereotype.Repository;

import com.src.banking.entity.Client;


public interface ClientService {
	public boolean isValidClient(Client client);
}
