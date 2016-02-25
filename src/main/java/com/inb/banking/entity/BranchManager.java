package com.inb.banking.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BRANCHMANAGER database table.
 * @author SudarshanReddy
 * 
 */
@Entity
@Table(name="branchmanager")
public class BranchManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id",unique=true, nullable=false, length=255)
	private String id;

	@Column(name="address",length=255)
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="dateOfBirth")
	private Date dateOfBirth;

	@Column(name="email",length=255)
	private String email;
	
	@Column(name="firstName",length=255)
	private String firstName;
	
	@Column(name="lastName",length=255)
	private String lastName;
	
	@Column(name="phone",length=255)
	private Long phone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchID", nullable = false)
	private Branch branch;
	
	@Column(name="userName",length=255)
	private String userName;
	
	@Column(name="password",length=255)
	private String password;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}