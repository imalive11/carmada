package com.carmada.drivers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="driver_personal_info")
public class DriverPersonalInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message = "Missing Address")
	@Column(name="address")
	private String address;
	
	@NotNull(message = "Missing Birth Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="birth_date")
	private String birthDate;

	@NotEmpty(message = "Missing License ID")
	@Column(name="license_id")
	private String licenseId;
	
	@NotNull(message = "Missing Expiration Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="license_expiration_date")
	private String licenseExpirationDate;
	
	@NotEmpty(message = "Missing Facebook Account")
	@Column(name="fb_email_address")
	private String fbEmailAddress;
	
	@NotEmpty(message = "Missing Contact Number")
	@Max(value = 11, message = "Starts with 09")
	@Column(name="mobile_number")
	private String mobileNumber;
	

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getFbEmailAddress() {
		return fbEmailAddress;
	}

	public String getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	public void setLicenseExpirationDate(String licenseExpirationDate) {
		this.licenseExpirationDate = licenseExpirationDate;
	}

	public void setFbEmailAddress(String fbEmailAddress) {
		this.fbEmailAddress = fbEmailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public DriverPersonalInfo() {
	}

	public DriverPersonalInfo(String address, String licenseId) {
		super();
		this.address = address;
		this.licenseId = licenseId;
	}


	
	

}
