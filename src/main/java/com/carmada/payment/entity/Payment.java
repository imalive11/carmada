package com.carmada.payment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.carmada.drivers.entity.Driver;
import com.carmada.vehicle.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="payment", schema="carmada-database")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="amount_boundary")
	@Min(value=0, message = "Missing Boundary")
	private double amountBoundary;
	
	@Column(name="amount_short")
	private double amountShort;
	
	@Column(name="amount_fund")
	private double amountFund;
	
	@Column(name="amount_loanpayment")
	private double amountLoanPayment;
	
	@Column(name="amount_contribution")
	private double amountContribution;
	
	@Column(name="amount_rate_boundary")
	private Integer amountRateBoundary;

	@Column(name="rate_boundary")
	private String rateBoundary;
	
	@Column(name="remarks")
	private String remarks;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="payment_date")
	@NotNull(message = "Missing Payment Date")
    private Date paymentDate;
	
	@NotNull(message = "Missing Travel Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="travel_date")
    private Date travelDate;

	@JsonIgnoreProperties("driver")
	@ManyToOne
	@JoinColumn(name="driver_id")
	@NotNull(message="Missing Driver")
	private Driver driver;@JsonIgnore

	@JsonIgnoreProperties("vehicle")
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	@NotNull(message="Missing Vehicle")
	private Vehicle vehicle;@JsonIgnore
	
	public Integer getAmountRateBoundary() {
		return amountRateBoundary;
	}

	public void setAmountRateBoundary(Integer amountRateBoundary) {
		this.amountRateBoundary = amountRateBoundary;
	}

	public String getRateBoundary() {
		return rateBoundary;
	}

	public void setRateBoundary(String rateBoundary) {
		this.rateBoundary = rateBoundary;
	}

	public double getAmountContribution() {
		return amountContribution;
	}

	public void setAmountContribution(double amountContribution) {
		this.amountContribution = amountContribution;
	}

	@Column(name="driver_name")
	private String driverName;
	
	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="payment_description")
	private String paymentDescription;
	
	
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

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

	public String getDriverName() {
		return driverName;
	}
	
	public void setFullName() {
		this.setDriverName(this.driver.getId() +" : " + this.driver.getFirstName() + " " + this.driver.getLastName());
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
