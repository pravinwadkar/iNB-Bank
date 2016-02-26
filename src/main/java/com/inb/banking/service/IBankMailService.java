package com.inb.banking.service;

public interface IBankMailService {
	public void sendMail(String from, String to, String subject, String msg);
}
