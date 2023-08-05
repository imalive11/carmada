package com.carmada.payment.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carmada.payment.entity.Payment;

public interface PaymentService {

	public Page<Payment> findAll(Pageable pageable);
	
	public Page<Payment> findByCurrentDate(Pageable pageable) throws ParseException;
	
	public Page<Payment> findByDriver(Pageable pageable, String driverName);
	
	public Page<Payment> findByDriverAndTravelDate(Pageable pageable, String driverName, Date date);
	
	public Page<Payment> findByDriverId(Pageable pageable, int id);
	
	public Page<Payment> findAllByIdAndTravelDateBetween(int id, Date startDate, Date endDate, Pageable pageable);
	
	public Page<Payment> findAllByDate(Pageable pageable, Date startDate, Date endDate);
	
	public Page<Payment> findAllByVehicleIdAndDate(Pageable pageable, int vehicleId, Date startDate, Date endDate);
	
	public Page<Payment> searchByRemarksLike(String remarks, Pageable pageable);
	
	public Page<Payment> searchByRemarksLikeAndTravelDate(String remarks, Date startDate, Pageable pageable);
	
	public Page<Payment> searchByRemarksLikeAndDriverName(String remarks, String name, Pageable pageable);
	
	public Page<Payment> searchByRemarksLikeAndDriverNameAndtravelDate(String remarks, String firstName, String lastName, Date travelDate, Pageable pageable);
	
	public Payment findById(int id);
	
	public void save(Payment payment);
	
	public void delete(int id);

	public Payment findLatestPayment();

	public Page<Payment> findLatestDayTravelDate(Pageable pageable);
	
	public List<Payment> findLatePaymentDate(Date latePaymentDate);
	
	public Page<Payment> findByVehicleId(Pageable pageable, int id);
	
	public Date findFirstTravelDate();

    public Date findLatestTravelDate();
    
    public Date getFirstDateForYear(int year);

    public Date getLatestDateForYear(int year);

    public Date findLatestPaymentDate();

    public List<Payment> findLatestLatePaymentDate();

	Page<Payment> findAllByTravelDateOrderByVehicleId(Pageable pageable, Date startDate, Date endDate);
	
}
