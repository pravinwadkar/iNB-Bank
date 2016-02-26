package com.inb.banking.rest.entity;

public class WSCustomerDocument {
	
	private String id;
	private byte[] imageaddress;
	private byte[] imageage;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getImageaddress() {
		return imageaddress;
	}
	public void setImageaddress(byte[] imageaddress) {
		this.imageaddress = imageaddress;
	}
	public byte[] getImageage() {
		return imageage;
	}
	public void setImageage(byte[] imageage) {
		this.imageage = imageage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
