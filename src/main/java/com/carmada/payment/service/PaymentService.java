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
	
	public Page<Payment> findByDriverId(Pageable pageable, int id);
	
	public Page<Payment> findAllByIdAndTravelDateBetween(int id, Date startDate, Date endDate, Pageable pageable);
	
	public Page<Payment> findAllByDate(Pageable pageable, Date startDate, Date endDate);
	
	public Page<Payment> findFirst56(Pageable pageable);
	
	public List<Payment> searchByRemarksLike(String remarks);
	
	public Payment findById(int id);
	
	public void save(Payment payment);
	
	public void delete(int id);

	public Payment findLatestPayment();

	public Page<Payment> findLatestDayPayment(Pageable pageable);
}
