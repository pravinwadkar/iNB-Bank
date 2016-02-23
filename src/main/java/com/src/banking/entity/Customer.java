package com.src.banking.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@Table(name="CUSTOMER")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(length=50)
	private String address;

	@Column(length=50)
	private String applicationstatus;

	@Column(length=50)
	private String authorizedimagename;

	@Column(length=50)
	private String authorizedimagetext;

	@Column(precision=10)
	private BigDecimal customerid;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String enqid;

	@Column(length=50)
	private String firstname;

	@Column(length=50)
	private String lastname;

	@Column(length=50)
	private String password;

	@Column(precision=10)
	private BigDecimal phone;

	@Column(length=50)
	private String username;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="customer")
	private Set<Account> accounts;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BRANCHID")
	private Branch branch;

	//bi-directional many-to-one association to CustDocument
	@OneToMany(mappedBy="customer")
	private Set<CustDocument> custDocuments;

	public Customer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApplicationstatus() {
		return this.applicationstatus;
	}

	public void setApplicationstatus(String applicationstatus) {
		this.applicationstatus = applicationstatus;
	}

	public String getAuthorizedimagename() {
		return this.authorizedimagename;
	}

	public void setAuthorizedimagename(String authorizedimagename) {
		this.authorizedimagename = authorizedimagename;
	}

	public String getAuthorizedimagetext() {
		return this.authorizedimagetext;
	}

	public void setAuthorizedimagetext(String authorizedimagetext) {
		this.authorizedimagetext = authorizedimagetext;
	}

	public BigDecimal getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(BigDecimal customerid) {
		this.customerid = customerid;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnqid() {
		return this.enqid;
	}

	public void setEnqid(String enqid) {
		this.enqid = enqid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPhone() {
		return this.phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setCustomer(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setCustomer(null);

		return account;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Set<CustDocument> getCustDocuments() {
		return this.custDocuments;
	}

	public void setCustDocuments(Set<CustDocument> custDocuments) {
		this.custDocuments = custDocuments;
	}

	public CustDocument addCustDocument(CustDocument custDocument) {
		getCustDocuments().add(custDocument);
		custDocument.setCustomer(this);

		return custDocument;
	}

	public CustDocument removeCustDocument(CustDocument custDocument) {
		getCustDocuments().remove(custDocument);
		custDocument.setCustomer(null);

		return custDocument;
	}

}