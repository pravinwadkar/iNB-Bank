package com.src.banking.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BRANCHMANAGER database table.
 * 
 */
@Entity
@Table(name="BRANCHMANAGER")
@NamedQuery(name="Branchmanager.findAll", query="SELECT b FROM Branchmanager b")
public class Branchmanager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String id;

	@Column(length=50)
	private String address;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String firstname;

	@Column(length=50)
	private String lastname;

	@Column(length=50)
	private String password;

	@Column(precision=10)
	private BigDecimal phone;

	@Column(length=50)
	private String username;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BRANCHID")
	private Branch branch;

	public Branchmanager() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPhone() {
		return this.phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}