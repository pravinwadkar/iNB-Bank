package com.inb.banking.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Branch;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.service.ClientService;

@RestController
@RequestMapping
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientServiceImpl;
	@Autowired
	private DozerBeanMapper mapper;

	/**
	 * This URL will check in DB if the user is valid or not. If valid provides
	 * the userID back. If login is valid the client login screen will be
	 * displayed.
	 * 
	 * @param loginId
	 * @return
	 */
	@RequestMapping(value = "/authorisation/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSCustomer login(@PathVariable(value = "clientId") int clientId) {
		// DONE
		WSCustomer wsCustomer =null; 
		Customer customer = clientServiceImpl.isClientAuthorized(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@RequestMapping(value = "/registeredcustomer/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSCustomer registeredCustomer(@PathVariable(value = "clientId") int clientId) {
		// DONE
		WSCustomer wsCustomer =null; 
		Customer customer = clientServiceImpl.registeredCustomer(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}
	
	/*@RequestMapping(value = "/clientHome/applyNewAccount/{enquiryId}/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer applyNewAccount(@PathVariable(value = "enquiryId") int enquiryId,
			@PathVariable(value = "email") String email,
			@RequestBody Branch branch) {
		System.out.println("enquiryId is  " + enquiryId);
		System.out.println("email is  " + email);
		Customer customer = clientServiceImpl.applyNewAccount(enquiryId, email,branch);
		return customer;
	}*/

	@RequestMapping(value = "/registeredcustomer/account/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSAccount viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		// DONE
		WSAccount wsAccount =null;
		Account account  = clientServiceImpl.viewAccountBalance(clientId);
		wsAccount = mapper.map(account, WSAccount.class);
		return wsAccount;
	}
	
	
	@RequestMapping(value = "/clientHome/transferMoney", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account transferMoney(@RequestBody Account sender,@RequestBody Account reciever) {
		System.out.println("sender.getBalance() is  " + sender.getBalance());
		Account account = clientServiceImpl.transferMoney(sender,reciever);
		return account;
	}
	// only account status - pending records will be retrieved 
	@RequestMapping(value = "/unregistereduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer unregistereduser(@RequestBody Customer customer) {
		return clientServiceImpl.unregisteredUser(customer);
	}	
	///unregistereduser?email=asdasdas@as
	@RequestMapping(value = "/unregistereduser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregistereduserVerifyEmail(@RequestParam String email ) {
		return clientServiceImpl.unregisteredUser(email);
	}	
	
}
