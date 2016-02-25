package com.inb.banking.rest.entity;

import java.math.BigDecimal;

public class WSAccount {

	private String id;

	private BigDecimal accountNumber;

	private String accountType;

	private BigDecimal balance;

	private BigDecimal interestRate;




	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public BigDecimal getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(BigDecimal accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public BigDecimal getBalance() {
		return balance;
	}



	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



	public BigDecimal getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}



}
