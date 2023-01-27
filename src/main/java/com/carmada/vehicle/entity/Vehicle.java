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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.carmada.payment.entity.Payment;

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
	
	@OneToMany(mappedBy="vehicle", fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payment> payments;

	public void add(Payment tempPayment) {

		if (this.payments == null) {
			this.payments = new ArrayList<>();
		}

		this.payments.add(tempPayment);

		tempPayment.setVehicle(this);
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
