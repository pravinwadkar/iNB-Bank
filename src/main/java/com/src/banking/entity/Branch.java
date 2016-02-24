package com.src.banking.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the BRANCH database table.
 * 
 */
@Entity
@Table(name="BRANCH")
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(length=50)
	private String address;

	@Column(length=50)
	private String branchname;

	@Column(precision=10)
	private BigDecimal contact;

	@Column(length=50)
	private String ifsccode;

	//bi-directional many-to-one association to Branchmanager
	@OneToMany(mappedBy="branch")
	private Set<Branchmanager> branchmanagers;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="branch")
	private Set<Customer> customers;

	public Branch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchname() {
		return this.branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public BigDecimal getContact() {
		return this.contact;
	}

	public void setContact(BigDecimal contact) {
		this.contact = contact;
	}

	public String getIfsccode() {
		return this.ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public Set<Branchmanager> getBranchmanagers() {
		return this.branchmanagers;
	}

	public void setBranchmanagers(Set<Branchmanager> branchmanagers) {
		this.branchmanagers = branchmanagers;
	}

	public Branchmanager addBranchmanager(Branchmanager branchmanager) {
		getBranchmanagers().add(branchmanager);
		branchmanager.setBranch(this);

		return branchmanager;
	}

	public Branchmanager removeBranchmanager(Branchmanager branchmanager) {
		getBranchmanagers().remove(branchmanager);
		branchmanager.setBranch(null);

		return branchmanager;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setBranch(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setBranch(null);

		return customer;
	}

}