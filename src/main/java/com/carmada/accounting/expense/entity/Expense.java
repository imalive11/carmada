package com.carmada.accounting.expense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.carmada.vehicle.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="expense")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="item")
	private String item;

	@JsonIgnoreProperties("vehicle")
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	@NotNull(message="Missing Vehicle")
	private Vehicle vehicle;
	
	@Column(name="description")
	private String description;
	
	@Column(name="requested_by")
	private String requestedBy;

	@Column(name="category")
	private String category;

	@Column(name="cost")
	private double cost;
	
	@Column(name="credit")
	private double credit;
	
	@Column(name="debit")
	private double debit;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="order_date")
    private Date orderDate;

	@Column(name="vendor")
	private String vendor;
	
	@Column(name="job_order_number")
	private Integer jobOrderNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public Integer getJobOrderNumber() {
		return jobOrderNumber;
	}

	public void setJobOrderNumber(Integer jobOrderNumber) {
		this.jobOrderNumber = jobOrderNumber;
	}

	
	public Expense(int id, String item, @NotNull(message = "Missing Vehicle") Vehicle vehicle, String description,
			String requestedBy, String category, double cost, double credit, double debit, Date orderDate,
			String vendor, Integer jobOrderNumber) {
		super();
		this.id = id;
		this.item = item;
		this.vehicle = vehicle;
		this.description = description;
		this.requestedBy = requestedBy;
		this.category = category;
		this.cost = cost;
		this.credit = credit;
		this.debit = debit;
		this.orderDate = orderDate;
		this.vendor = vendor;
		this.jobOrderNumber = jobOrderNumber;
	}

	public Expense() {
	}

	
	
}
