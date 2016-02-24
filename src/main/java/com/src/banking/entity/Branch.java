package com.src.banking.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the BRANCH database table.
 * @author SudarshanReddy
 * 
 */
@Entity
@Table(name="branch")
public class Branch implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(name="address",length=50)
	private String address;

	@Column(name="branchName",length=50)
	private String branchname;

	@Column(name="contact",precision=10)
	private BigDecimal contact;

	@Column(name="ifscCode",length=50)
	private String ifscCode;

	//bi-directional many-to-one association to Branchmanager
	@OneToMany(mappedBy="branch",fetch=FetchType.LAZY)
	private Set<BranchManager> branchManagers;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="branch")
	private Set<Customer> customers;

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

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public BigDecimal getContact() {
		return contact;
	}

	public void setContact(BigDecimal contact) {
		this.contact = contact;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Set<BranchManager> getBranchManagers() {
		return branchManagers;
	}

	public void setBranchManagers(Set<BranchManager> branchManagers) {
		this.branchManagers = branchManagers;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
}