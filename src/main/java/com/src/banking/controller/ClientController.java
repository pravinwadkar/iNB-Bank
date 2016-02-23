package com.src.banking.controller;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.src.banking.entity.Account;
import com.src.banking.entity.Client;
import com.src.banking.service.impl.ClientServiceImpl;

@RestController
@RequestMapping
public class ClientController {

	@Autowired
	ClientServiceImpl clientService;

	@RequestMapping(value = "/authorisation/login/{clientId}", method = RequestMethod.GET, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account login(/*@RequestBody Client client*/) {

		Account account = new Account();
		Client client = new Client();
		client.setClientId(123);
		client.setClientName("xoriant name ");
		//if (client.getClientId() != 0) {
			// validate and authorized client
			clientService.isValidClient(client);
			// get and return client account information

		//}

		return null;
	}
}
