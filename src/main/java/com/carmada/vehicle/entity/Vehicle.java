package com.carmada.vehicle.entity;

import java.util.ArrayList;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.carmada.drivers.entity.Driver;
import com.carmada.payment.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotEmpty(message = "Missing Plate Number")
	@Column(name="plate_no")
	private String plateNumber;

	@NotNull(message = "Missing Year Model")
	@Column(name="year_model")
	private Integer yearModel;

	@NotEmpty(message = "Missing Brand")
	@Column(name="brand")
	private String brand;
	
	@JsonIgnoreProperties("vehicle")
	@OneToMany(mappedBy="vehicle", fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payment> payments;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="assigned_driver1")
	@JsonIgnore
	private Driver assignedDriver1;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="assigned_driver2")
	@JsonIgnore
	private Driver assignedDriver2;

	@Column(name="boundary_rate")
	private Integer boundaryRate;
	
	@Column(name="expiration_year")
	private Integer expirationYear;
	
	public void add(Payment tempPayment) {

		if (this.payments == null) {
			this.payments = new ArrayList<>();
		}

		this.payments.add(tempPayment);

		tempPayment.setVehicle(this);
	}

	public Driver getAssignedDriver1() {
		return assignedDriver1;
	}

	public void setAssignedDriver1(Driver assignedDriver1) {
		this.assignedDriver1 = assignedDriver1;
	}

	public Driver getAssignedDriver2() {
		return assignedDriver2;
	}

	public void setAssignedDriver2(Driver assignedDriver2) {
		this.assignedDriver2 = assignedDriver2;
	}

	public Integer getBoundaryRate() {
		return boundaryRate;
	}

	public void setBoundaryRate(Integer boundaryRate) {
		this.boundaryRate = boundaryRate;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	public Integer getYearModel() {
		return yearModel;
	}

	public void setYearModel(Integer yearModel) {
		this.yearModel = yearModel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Vehicle() {
	}

	public Vehicle(String plateNumber, Integer yearModel, String brand) {
		this.plateNumber = plateNumber;
		this.yearModel = yearModel;
		this.brand = brand;
	}

	




}
