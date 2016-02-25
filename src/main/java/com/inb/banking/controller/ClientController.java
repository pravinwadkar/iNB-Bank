package com.inb.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Branch;
import com.inb.banking.entity.Customer;
import com.inb.banking.service.ClientService;

@RestController
@RequestMapping
public class ClientController {

	@Autowired
	ClientService clientServiceImpl;

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
		clientServiceImpl.isClientAuthorized(loginId);
		return null;
	}

	@RequestMapping(value = "/clientHome/applyNewAccount/{enquiryId}/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer applyNewAccount(@PathVariable(value = "enquiryId") int enquiryId,
			@PathVariable(value = "email") String email,
			@RequestBody Branch branch) {
		System.out.println("enquiryId is  " + enquiryId);
		System.out.println("email is  " + email);
		Customer customer = clientServiceImpl.applyNewAccount(enquiryId, email,branch);
		return customer;
	}

	@RequestMapping(value = "/viewAccountBalance/{clientId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		System.out.println("clientId is  " + clientId);
		Account account = clientServiceImpl.viewAccountBalance(clientId);
		return account;
	}
	
	
	@RequestMapping(value = "/clientHome/transferMoney", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account transferMoney(@RequestBody Account sender,@RequestBody Account reciever) {
		System.out.println("sender.getBalance() is  " + sender.getBalance());
		Account account = clientServiceImpl.transferMoney(sender,reciever);
		return account;
	}
	
	@RequestMapping(value = "/unregistereduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer unregistereduser(@RequestBody Customer customer) {
		return clientServiceImpl.unregisteredUser(customer);
	}	
	
}
