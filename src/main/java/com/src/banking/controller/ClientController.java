package com.src.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.src.banking.entity.Account;
import com.src.banking.entity.Branch;
import com.src.banking.entity.Customer;
import com.src.banking.service.impl.ClientServiceImpl;

@RestController
@RequestMapping
public class ClientController {

	@Autowired
	ClientServiceImpl clientService;

	/**
	 * This URL will check in DB if the user is valid or not. If valid provides
	 * the userID back. If login is valid the client login screen will be
	 * displayed.
	 * 
	 * @param loginId
	 * @return
	 */
	@RequestMapping(value = "/clientLogin/{loginId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account login(@PathVariable(value = "loginId") int loginId) {
		System.out.println("loginId is  " + loginId);
		clientService.isClientAuthorized(loginId);
		return null;
	}

	@RequestMapping(value = "/clientHome/applyNewAccount/{enquiryId}/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer applyNewAccount(@PathVariable(value = "enquiryId") int enquiryId,
			@PathVariable(value = "email") String email,
			@RequestBody Branch branch) {
		System.out.println("enquiryId is  " + enquiryId);
		System.out.println("email is  " + email);
		Customer customer = clientService.applyNewAccount(enquiryId, email,branch);
		return customer;
	}

	@RequestMapping(value = "/viewAccountBalance/{clientId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		System.out.println("clientId is  " + clientId);
		Account account = clientService.viewAccountBalance(clientId);
		return account;
	}
	
	
	@RequestMapping(value = "/clientHome/{clientId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		System.out.println("clientId is  " + clientId);
		Account account = clientService.viewAccountBalance(clientId);
		return account;
	}
	
}
