package com.inb.banking.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ADMIN")
@NamedQuery(name="Admin.findAll", query="SELECT c FROM Admin c")
public class Admin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;
	
	@Column(length=50)
	private String password;

	@Column(length=50)
	private String userName;
	
	public Admin() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}






	
	

	
	
	
	

	



	


