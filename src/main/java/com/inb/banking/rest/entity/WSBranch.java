package com.inb.banking.rest.entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author SudarshanReddy
 *
 */
@XmlRootElement
public class WSBranch {
	
	private String id;
	private String address;
	private String branchName;
	private BigDecimal contact;
	private String ifscCode;
	
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
}
