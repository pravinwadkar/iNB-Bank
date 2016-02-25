package com.inb.banking.entity;

import java.io.Serializable;
import javax.persistence.*;

//import model.Customer;

import java.math.BigDecimal;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@Table(name="ACCOUNT")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(precision=20)
	private BigDecimal accountnumber;

	@Column(length=50)
	private String accounttype;

	@Column(precision=10, scale=2)
	private BigDecimal balance;

	@Column(precision=10, scale=2)
	private BigDecimal interestrate;

	//bi-directional many-to-one association to Customer
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTID")
	private Customer customer;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(BigDecimal accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccounttype() {
		return this.accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getInterestrate() {
		return this.interestrate;
	}

	public void setInterestrate(BigDecimal interestrate) {
		this.interestrate = interestrate;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}