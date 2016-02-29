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
import org.springframework.web.multipart.MultipartFile;

import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.Status;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSBranchCustomer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.rest.entity.WSTransfer;
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
		return wSCustomer;
	}

	@RequestMapping(value = "/registeredcustomer/account/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSAccount> viewAccountBalance(@PathVariable(value = "clientId") int clientId) {
		// DONE
		return clientServiceImpl.viewAccountBalance(clientId);
	}

	@RequestMapping(value = "/registeredcustomer/transfer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String transferMoney(@RequestBody WSTransfer wsTransfer) {
		String account = clientServiceImpl.transferMoney(wsTransfer);
		return account;
	}

	// only account status - pending records will be retrieved
	@RequestMapping(value = "/unregistereduser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSBranchCustomer unregistereduser(@RequestBody WSBranchCustomer wscustomer) {
		return clientServiceImpl.unregisteredUser(wscustomer);
	}

	@RequestMapping(value = "/unregistereduser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregistereduserVerifyEmail(@RequestParam String email) {
		return clientServiceImpl.unregisteredUser(email);
	}

	/**
	 * For Branch Manager Viewing UnRegistered User Details
	 * 
	 * @return
	 */
	@RequestMapping(value = "/unregistereduser/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllUnregisteredUsers() {
		return clientServiceImpl.getAllUnregisteredUsers();
	}

	/**
	 * For Branch Manager Viewing Registered User Details
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registeredcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllRegisteredUsers() {
		return clientServiceImpl.getAllRegisteredUsers();
	}

	/**
	 * For Branch Manager Viewing Rejected User Details
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rejectededcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getAllRejectedUsers() {
		return clientServiceImpl.getAllRejectedUsers();
	}

	@RequestMapping(value = "/document", method = RequestMethod.POST)
	public @ResponseBody Status uploadDocument(@RequestParam("addressProof") MultipartFile addressProof,
			@RequestParam("ageProof") MultipartFile ageProof, @RequestParam("email") String email) {

		try {

			CustDocument custDocument = new CustDocument();
			custDocument.setImageaddress(addressProof.getBytes());
			custDocument.setImageage(ageProof.getBytes());
			clientServiceImpl.uploadDocument(custDocument, email);
			return new Status();

		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/unregistereduser/{email}/{customerId}/verify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregistereduserVerifyEmail(@PathVariable(value = "email") String email,
			@PathVariable(value = "customerId") String customerId) {
		iBankMailServiceImpl.sendMail("info.inbbank@gmail.com",
				"ashutosh.sharma@xoriant.com"/* customer.getEmail() */, "Wel Come to IBank", "See you after mail ");

		return "{\"Success\": \"Email sent\"}";
	}

	/**
	 * For Branch Manager Viewing Customer details in Verify purpose based on Id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/unregistereduser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchCustomer> getCustomerDetailsById(@PathVariable(value = "id") String id) {

		return clientServiceImpl.getCustomerDetailsById(id);
	}

	/**
	 * For Branch Manager Verify and Reject the Customer
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/unregistereduser/email/{id}/verify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregisteredUserVerification(@PathVariable(value = "id") String id) {
		clientServiceImpl.unregisteredUserVerification(id);
		return "{\"Success\": \"Email sent\"}";
	}

	/**
	 * For Branch Manager Verify and Reject the Customer
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/unregistereduser/email/{id}/reject", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String unregisteredUserRejection(@PathVariable(value = "id") String id) {
		clientServiceImpl.unregisteredUserRejection(id);
		return "{\"Success\": \"Email sent\"}";
	}

}
