package com.carmada.payment.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.carmada.drivers.entity.Driver;
import com.carmada.vehicle.entity.Vehicle;

@Entity
@Table(name="payment", schema="carmada-database")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="amount_boundary")
	@Min(value=1, message = "Missing Boundary")
	private double amountBoundary;
	
	@Column(name="amount_short")
	@Min(value=0, message = "Missing Boundary")
	private double amountShort;
	
	@Column(name="amount_fund")
	@Min(value=0, message = "Missing Boundary")
	private double amountFund;
	
	@Column(name="amount_loanpayment")
	@Min(value=0, message = "Missing Boundary")
	private double amountLoanPayment;
	
	public double getAmountShort() {
		return amountShort;
	}

	public void setAmountShort(double amountShort) {
		this.amountShort = amountShort;
	}

	public double getAmountFund() {
		return amountFund;
	}

	public void setAmountFund(double amountFund) {
		this.amountFund = amountFund;
	}

	public double getAmountLoanPayment() {
		return amountLoanPayment;
	}

	public void setAmountLoanPayment(double amountLoanPayment) {
		this.amountLoanPayment = amountLoanPayment;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="payment_date")
	@NotNull(message = "Missing Payment Date")
    private Date paymentDate;
	
	@NotNull(message = "Missing Travel Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="travel_date")
    private Date travelDate;
	

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},
			fetch= FetchType.LAZY)
	@JoinColumn(name="driver_id")
	@NotNull(message="Missing Driver")
	private Driver driver;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},
			fetch= FetchType.LAZY)
	@JoinColumn(name="vehicle_unit_id")
	@NotNull(message="Missing Vehicle")
	private Vehicle vehicle;
	
	@Column(name="driver_name")
	private String driverName;

	public String getDriverName() {
		return driverName;
	}
	
	public void set() {

		if (this.driverName == null) {
			this.setDriverName(this.driver.getId() +": " + this.driver.getFirstName() + " " + this.driver.getLastName());
		}
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Payment(double amountBoundary) {
		this.amountBoundary = amountBoundary;
	}

	public Payment() {
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmountBoundary() {
		return amountBoundary;
	}

	public void setAmountBoundary(double amountBoundary) {
		this.amountBoundary = amountBoundary;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	

}
