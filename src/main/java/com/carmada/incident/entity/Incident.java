package com.carmada.incident.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.carmada.drivers.entity.Driver;
import com.carmada.vehicle.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="incident")
public class Incident {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "incident_amount")
	private Integer incidentAmount;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "incident_type")
    private IncidentType incidentType;

    public enum IncidentType {
        DAMAGE,
        VIOLATION,
        CASH_ADVANCE,
        CHARGE_BOUNDARY
    }
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "incident_date")
	private Date incidentDate;
	
    @Column(name = "job_order_number")
	private Integer jobOrderNumber;
	
    @Column(name = "remarks")
	private String remarks;
	
    @JsonIgnoreProperties("driver")
	@ManyToOne
	@JoinColumn(name="driver_id")
	@NotNull(message="Missing Driver")
	private Driver driver;

	@JsonIgnoreProperties("vehicle")
	@NotNull(message="Missing Vehicle")
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIncidentAmount() {
		return incidentAmount;
	}

	public void setIncidentAmount(Integer incidentAmount) {
		this.incidentAmount = incidentAmount;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}


	public Date getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	public Integer getJobOrderNumber() {
		return jobOrderNumber;
	}

	public void setJobOrderNumber(Integer jobOrderNumber) {
		this.jobOrderNumber = jobOrderNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Incident() {
		super();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Incident(int id, Integer incidentAmount, IncidentType incidentType, Date date, Integer jobOrderNumber,
			String remarks, @NotNull(message = "Missing Driver") Driver driver, Vehicle vehicle) {
		super();
		this.id = id;
		this.incidentAmount = incidentAmount;
		this.incidentType = incidentType;
		this.incidentDate = date;
		this.jobOrderNumber = jobOrderNumber;
		this.remarks = remarks;
		this.driver = driver;
		this.vehicle = vehicle;
	}


}
