package com.carmada.drivers.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.carmada.incident.entity.Incident;
import com.carmada.payment.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="driver")
public class Driver {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotEmpty(message = "Missing First Name")
    @Size(min = 1, max = 30)
	@Column(name="first_name")
	private String firstName;
	
	@NotEmpty(message = "Missing Last Name")
    @Size(min = 1, max = 30)
	@Column(name="last_name")
	private String lastName;

	@NotNull(message = "Missing Date of Employment")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="employment_date" , columnDefinition = "DATE")
    private Date employmentDate;

	
	@OneToOne(cascade=CascadeType.ALL)
	@Valid
	@JoinColumn(name="driver_info_id")
	private DriverPersonalInfo driverPersonalInfo;

	@JsonIgnoreProperties("driver")
	@OneToMany(mappedBy="driver", 
			fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payment> payments;
	
	@JsonIgnoreProperties("driver")
	@OneToMany(mappedBy="driver", 
			fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Incident> incidents;
	
	public void add(Payment tempPayment) {

		if (this.payments == null) {
			this.payments = new ArrayList<>();
		}

		this.payments.add(tempPayment);

		tempPayment.setDriver(this);
	}
	
	public DriverPersonalInfo getDriverPersonalInfo() {
		return driverPersonalInfo;
	}

	public void setDriverPersonalInfo(DriverPersonalInfo driverPersonalInfo) {
		this.driverPersonalInfo = driverPersonalInfo;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Driver() {
	}

	public Driver(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}






}
