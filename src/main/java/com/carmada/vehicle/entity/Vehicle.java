package com.carmada.vehicle.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.carmada.incident.entity.Incident;
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
	
	@NotEmpty(message = "Missing Franchise")
	@Column(name="franchise")
	private String franchise;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "travel_type")
    private TravelType travelType;

    public enum TravelType {
        DAILY,
        REGULAR
    }	
	
	public TravelType getTravelType() {
		return travelType;
	}

	public void setTravelType(TravelType travelType) {
		this.travelType = travelType;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	@JsonIgnoreProperties("vehicle")
	@OneToMany(mappedBy="vehicle", fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payment> payments;
	
	@JsonIgnoreProperties("vehicle")
	@OneToMany(mappedBy="vehicle", fetch= FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Incident> incidents;

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
	
	public void addPayment(Payment tempPayment) {

		if (this.payments == null) {
			this.payments = new ArrayList<>();
		}

		this.payments.add(tempPayment);

		tempPayment.setVehicle(this);
	}
	
	public void addIncident(Incident incident) {

		if (this.incidents == null) {
			this.incidents = new ArrayList<>();
		}

		this.incidents.add(incident);

		incident.setVehicle(this);
	}

	public String getFranchise() {
		return franchise;
	}

	public void setFranchise(String franchise) {
		this.franchise = franchise;
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

	public Vehicle(int id, @NotEmpty(message = "Missing Plate Number") String plateNumber,
			@NotNull(message = "Missing Year Model") Integer yearModel,
			@NotEmpty(message = "Missing Brand") String brand,
			@NotEmpty(message = "Missing Franchise") String franchise, List<Payment> payments, Driver assignedDriver1,
			Driver assignedDriver2, Integer boundaryRate, Integer expirationYear) {
		super();
		this.id = id;
		this.plateNumber = plateNumber;
		this.yearModel = yearModel;
		this.brand = brand;
		this.franchise = franchise;
		this.payments = payments;
		this.assignedDriver1 = assignedDriver1;
		this.assignedDriver2 = assignedDriver2;
		this.boundaryRate = boundaryRate;
		this.expirationYear = expirationYear;
	}





}
