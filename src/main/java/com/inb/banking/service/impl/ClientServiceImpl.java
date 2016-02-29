package com.inb.banking.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.BranchDAO;
import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSBranchCustomer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.rest.entity.WSTransfer;
import com.inb.banking.service.ClientService;
import com.inb.banking.service.IBankMailService;
import com.inbbank.util.ApplicationStatus;
import com.inbbank.util.GenerateUUID;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;

	@Autowired
	BranchDAO branchDao;
	
	@Autowired
	IBankMailService iBankMailService;

	@Autowired
	private DozerBeanMapper mapper;

	String InvalidCustomer = "Invalid credentials";

	String transferStatus = "{\"Status\":\"Success\", \"Message\":\"Done Successfully\"}";

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomerAccount(int accountId) {
		return clientDao.registeredCustomerAccount(accountId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public WSCustomer registeredCustomer(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = clientDao.getRegisteredCustomer(clientId);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public WSCustomer isClientAuthorized(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = clientDao.getClientDetails(clientId);
		//customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public List<WSAccount> viewAccountBalance(int clientId) {
		WSAccount wsAccount = null;
		List<WSAccount> wsaccount = new ArrayList<WSAccount>();
		List<Account> account = clientDao.viewAccountBalance(clientId);
		for (Account account2 : account) {
			wsAccount = mapper.map(account2, WSAccount.class);
			wsaccount.add(wsAccount);
		}
		return wsaccount;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false,isolation=Isolation.READ_COMMITTED)
	public String transferMoney(WSTransfer wsTransfer) {

		BigDecimal balance = clientDao.getAccountBalance(Integer.parseInt(wsTransfer.getClientAccount()));
		if (balance.intValue() >= Integer.parseInt(wsTransfer.getAmount())) {
			clientDao.transfer(wsTransfer);
		} else // insufficient balance
		{

		}

		return transferStatus;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public String unregisteredUser(String customer) {
		return clientDao.unregisteredUser(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false,isolation=Isolation.READ_COMMITTED)
	public WSBranchCustomer unregisteredUser(WSBranchCustomer wsCustomer) {
		wsCustomer.setId(GenerateUUID.getRendomString());
		wsCustomer.setApplicationStatus("Pending");

		WSAccount wsAccount = wsCustomer.getAccount();
		Customer customer = mapper.map(wsCustomer, Customer.class);
		customer.setBranch(branchDao.getBranchDetailsByName(wsCustomer.getBranchPOJO().getBranchName()));
		customer.setCustomerId(GenerateUUID.getRandomNumberInRange());
		Account account = mapper.map(wsAccount, Account.class);
		account.setId(GenerateUUID.getRendomString());
		account.setCustomer(customer);
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(account);
		customer.setAccounts(accounts);
		clientDao.unregisteredUser(customer);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public WSCustomer getRegisteredCustomer(Customer customer) {

		WSCustomer wsCustomer = null;
		List<WSCustomer> list = new ArrayList<WSCustomer>();
		Customer customerData = clientDao.getValidateCustomer(customer.getUserName(), customer.getPassword());
		// customerData.setBranch(null);
		if (customerData != null) {
			wsCustomer = mapper.map(customerData, WSCustomer.class);
		} else {
			wsCustomer = new WSCustomer();
			wsCustomer.setException(InvalidCustomer);
		}
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public List<WSBranchCustomer> getAllUnregisteredUsers() {
		List<Customer> customers = clientDao.getAllUnregisteredUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
				for (WSAccount wsAccount : wsBranchCustomer.getAccounts())
					wsBranchCustomer.setAccount(wsAccount);
				wsBranchCustomers.add(wsBranchCustomer);
			}

		}
		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public List<WSBranchCustomer> getAllRegisteredUsers() {
		List<Customer> customers = clientDao.getAllRegisteredUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		for (Customer customer : customers) {
			WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
			wsBranchCustomer.setAccounthash(wsBranchCustomer.getAccounts());
			wsBranchCustomers.add(wsBranchCustomer);
		}

		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public List<WSBranchCustomer> getAllRejectedUsers() {
		List<Customer> customers = clientDao.getAllRejectedUsers();
		List<WSBranchCustomer> wsBranchCustomers = new ArrayList<WSBranchCustomer>(customers.size());
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				WSBranchCustomer wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
				for (WSAccount wsAccount : wsBranchCustomer.getAccounts())
					wsBranchCustomer.setAccount(wsAccount);
				wsBranchCustomers.add(wsBranchCustomer);
			}

		}
		return wsBranchCustomers;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false,isolation=Isolation.READ_COMMITTED)
	public String unregisteredUserVerifyReject(String clientId, String email) {
		return clientDao.unregisteredUserVerifyReject(clientId, email);
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly = false,isolation=Isolation.READ_COMMITTED )
	public boolean uploadDocument(CustDocument custDocument,String email) throws Exception {
		return clientDao.uploadDocument(custDocument,email);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true ,isolation=Isolation.READ_COMMITTED)
	public List<WSBranchCustomer> getCustomerDetailsById(String id) {
		Customer customer = clientDao.getCustomerDetailsById(id);
		List<WSBranchCustomer> wscustomerList = new ArrayList<WSBranchCustomer>();
		WSBranchCustomer wsBranchCustomer = new WSBranchCustomer();
		if (customer != null) {
			wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
			Set<WSAccount> wsAccounts = wsBranchCustomer.getAccounts();
			for (WSAccount wsAccount : wsAccounts)
				wsBranchCustomer.setAccount(wsAccount);
			wscustomerList.add(wsBranchCustomer);
			return wscustomerList;
		} else
			return null;

	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false ,isolation=Isolation.READ_COMMITTED)
	public void unregisteredUserVerification(String id) {
		Customer customer = clientDao.unregisteredUserVerification(id);
		BigDecimal accountNumber= null;
		String password = null;
		BigDecimal customerId = null;
		if (customer != null) {
			customer.setApplicationStatus(null);
			Set<Account> accounts = customer.getAccounts();
			Set<Account> accountsDetails = new HashSet(accounts.size());
			for(Account account : accounts){
				accountNumber =GenerateUUID.getRandomNumberInRange();
				account.setAccountNumber(accountNumber);
				account.setBalance(new BigDecimal(500));
				account.setInterestRate(new BigDecimal(0));
				
				accountsDetails.add(account);
			}
			customer.setAccounts(accountsDetails);
			//customer.setCustomerId(GenerateUUID.getRandomNumber());
			customerId = customer.getCustomerId();
			customer.setPassword(GenerateUUID.getRendomString());
			password = customer.getPassword();
			clientDao.updateCustomerInformation(customer);
			String userName = customer.getFirstName() + customer.getLastName();
			if(accountNumber !=null && (password != null && password != "") && customerId != null )
				iBankMailService.sendMail("info.inbbank@gmail.com",customer.getEmail(), "Wel Come to IBank",
					"Your Account SuccessFully Created and CustomerId:"+String.valueOf(customerId)+ "\n  UserName :"+ userName +"Account Number : "+String.valueOf(accountNumber)
					+"\n  Password :" + password );

		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false,isolation=Isolation.READ_COMMITTED)
	public void unregisteredUserRejection(String id) {
		Customer customer = clientDao.unregisteredUserVerification(id);
		if (customer != null) { 
		customer.setApplicationStatus(ApplicationStatus.REJECTED.getValue());
             clientDao.updateCustomerInformation(customer);
             iBankMailService.sendMail("info.inbbank@gmail.com", customer.getEmail(), "Wel Come to IBank",
 					"Your Application Rejected Due to Invalid Document Please Resubmit Valid Documents");
      }
		
	}

}
