package com.inb.banking.rest.entity;

public class WSTransfer {
	
	private String clientAccount;
	
	private String recevierAccount;
	
	private String amount;

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getRecevierAccount() {
		return recevierAccount;
	}

	public void setRecevierAccount(String recevierAccount) {
		this.recevierAccount = recevierAccount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
