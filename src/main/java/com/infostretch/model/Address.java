package com.infostretch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer id;

	@Column(name = "user_id")
	private String userId;

	private String firstname;

	private String lastname;

	@Column(name = "addressline")
	private String address;
	private String pincode;
	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "alternate_number")
	private String alternateContactNumber;

	public Address() {

	}
	
	

	public Address(Integer id, String userId, String firstname, String lastname, String address, String pincode,
			String mobileNumber, String alternateContactNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.pincode = pincode;
		this.mobileNumber = mobileNumber;
		this.alternateContactNumber = alternateContactNumber;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	

}
