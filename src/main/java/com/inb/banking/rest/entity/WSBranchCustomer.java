package com.inb.banking.rest.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


public class WSBranchCustomer {

	private String id;

	private String address;

	private String applicationStatus;

	private String authorizedImageName;

	private String authorizedImageText;

	private BigDecimal customerId;

	private Date dateOfBirth;

	private String email;

	private BigDecimal enqId;

	private String firstName;

	private String lastName;

	private String password;

	private BigDecimal phone;

	private String userName;

	private WSBranch branch;
	private Set<WSAccount> accounthash;
	private Set<WSAccount> accounts;
	private WSAccount account;
	private WSBranch branchPOJO;

	public WSBranch getBranchPOJO() {
		return branchPOJO;
	}

	public void setBranchPOJO(WSBranch branchPOJO) {
		this.branchPOJO = branchPOJO;
	}

	public WSAccount getAccount() {
		return account;
	}

	public void setAccount(WSAccount account) {
		this.account = account;
	}

	public Set<WSAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<WSAccount> accounts) {
		this.accounts = accounts;
	}

	public Set<WSAccount> getAccounthash() {
		return accounthash;
	}

	public void setAccounthash(Set<WSAccount> accounthash) {
		this.accounthash = accounthash;
	}

	public WSBranch getBranch() {
		return branch;
	}

	public void setBranch(WSBranch branch) {
		this.branch = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getAuthorizedImageName() {
		return authorizedImageName;
	}

	public void setAuthorizedImageName(String authorizedImageName) {
		this.authorizedImageName = authorizedImageName;
	}

	public String getAuthorizedImageText() {
		return authorizedImageText;
	}

	public void setAuthorizedImageText(String authorizedImageText) {
		this.authorizedImageText = authorizedImageText;
	}

	public BigDecimal getCustomerId() {
		return customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getEnqId() {
		return enqId;
	}

	public void setEnqId(BigDecimal enqId) {
		this.enqId = enqId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPhone() {
		return phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
