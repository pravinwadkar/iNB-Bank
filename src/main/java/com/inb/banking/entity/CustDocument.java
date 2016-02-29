package com.inb.banking.entity;


import java.io.Serializable;

import javax.persistence.*;




/**
 * The persistent class for the CUST_DOCUMENT database table.
 * 
 */
@Entity
@Table(name="CUST_DOCUMENT")
@NamedQuery(name="CustDocument.findAll", query="SELECT c FROM CustDocument c")
public class CustDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;
	
	@Lob
	private byte[] imageaddress;

	@Lob
	private byte[] imageage;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTID")
	private Customer customer;

	public CustDocument() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getImageaddress() {
		return this.imageaddress;
	}

	public void setImageaddress(byte[] imageaddress) {
		this.imageaddress = imageaddress;
	}

	public byte[] getImageage() {
		return this.imageage;
	}

	public void setImageage(byte[] imageage) {
		this.imageage = imageage;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}