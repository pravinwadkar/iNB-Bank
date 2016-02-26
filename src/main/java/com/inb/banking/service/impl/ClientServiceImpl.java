package com.inb.banking.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.ClientDao;
import com.inb.banking.entity.Account;
import com.inb.banking.entity.CustDocument;
import com.inb.banking.entity.Customer;
import com.inb.banking.rest.entity.WSAccount;
import com.inb.banking.rest.entity.WSBranchCustomer;
import com.inb.banking.rest.entity.WSCustomer;
import com.inb.banking.rest.entity.WSTransfer;
import com.inb.banking.service.ClientService;
import com.inbbank.util.GenerateUUID;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;
	@Autowired
	private DozerBeanMapper mapper;

	String InvalidCustomer = "Invalid credentials";
	
	String transferStatus = "{\"Status\":\"Success\", \"Message\":\"Done Successfully\"}";

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Customer registeredCustomerAccount(int accountId) {
		return clientDao.registeredCustomerAccount(accountId);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer registeredCustomer(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = clientDao.getRegisteredCustomer(clientId);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSCustomer isClientAuthorized(int clientId) {
		WSCustomer wsCustomer = null;
		Customer customer = clientDao.getClientDetails(clientId);
		customer.setBranch(null);
		wsCustomer = mapper.map(customer, WSCustomer.class);
		return wsCustomer;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String transferMoney(WSTransfer wsTransfer) {

		BigDecimal balance = clientDao.getAccountBalance(Integer.parseInt(wsTransfer.getClientAccount()));
		if (balance.intValue() >= Integer.parseInt(wsTransfer.getAmount())) {
			clientDao.transfer(wsTransfer);			
		} else // insufficient balance
		{
			
		}

		return transferStatus;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String unregisteredUser(String customer) {
		return clientDao.unregisteredUser(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Customer unregisteredUser(Customer account) {
		account.setId(GenerateUUID.getRendomString());
		return clientDao.unregisteredUser(account);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
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

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
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

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String unregisteredUserVerifyReject(String clientId, String email) {
		return clientDao.unregisteredUserVerifyReject(clientId, email);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean uploadDocument(CustDocument custDocument) throws Exception {
		return clientDao.uploadDocument(custDocument);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public WSBranchCustomer getCustomerDetailsById(String id) {
		Customer customer = clientDao.getCustomerDetailsById(id);
		WSBranchCustomer wsBranchCustomer = new WSBranchCustomer();
		if (customer != null) {
			wsBranchCustomer = mapper.map(customer, WSBranchCustomer.class);
			Set<WSAccount> wsAccounts = wsBranchCustomer.getAccounts();
			for (WSAccount wsAccount : wsAccounts)
				wsBranchCustomer.setAccount(wsAccount);
			return wsBranchCustomer;
		} else
			return null;

	}
}
