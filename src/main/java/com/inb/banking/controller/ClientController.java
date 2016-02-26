package com.inb.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.Account;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSBranchCustomer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.service.ClientService;
import com.inb.banking.service.IBankMailService;

@RestController
@RequestMapping
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientServiceImpl;

	@Autowired
	IBankMailService iBankMailServiceImpl;
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
		return clientServiceImpl.isClientAuthorized(clientId);
	}

	@RequestMapping(value = "/registeredcustomer/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSCustomer registeredCustomer(@PathVariable(value = "clientId") int clientId) {
		// DONE
		return clientServiceImpl.registeredCustomer(clientId);
	}
	
	@RequestMapping(value = "/registeredcustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSCustomer getRegisteredCustomer(@RequestBody Customer customer) {
		// DONE
		WSCustomer wSCustomer = clientServiceImpl.getRegisteredCustomer(customer);
		if (customer.getEmail() != null) {
			iBankMailServiceImpl.sendMail("info.inbbank@gmail.com", "pravin.wadkar@Xoriant.Com"/*customer.getEmail()*/, "Wel Come to IBank",
					"See you after mail ");
		}
		return wSCustomer;
	}
	
	@RequestMapping(value = "/registeredcustomer/account/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSAccount viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		// DONE
		return clientServiceImpl.viewAccountBalance(clientId);
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

	@RequestMapping(value = "/unregistereduser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregistereduserVerifyEmail(@RequestParam String email ) {
		return clientServiceImpl.unregisteredUser(email);
	}	
	
	/**
	 * For Branch Manager Viewing UnRegistered User Details
	 * @return
	 */
	@RequestMapping(value = "/unregistereduser/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllUnregisteredUsers(){
		return clientServiceImpl.getAllUnregisteredUsers();
	}
	
	/**
	 * For Branch Manager Viewing Registered User Details
	 * @return
	 */
	@RequestMapping(value = "/registeredcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllRegisteredUsers(){
		return clientServiceImpl.getAllRegisteredUsers();
	}
	
	/**
	 * For Branch Manager Viewing Rejected User Details
	 * @return
	 */
	@RequestMapping(value = "/rejectededcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllRejectedUsers(){
		return clientServiceImpl.getAllRejectedUsers();
	}
	
}
