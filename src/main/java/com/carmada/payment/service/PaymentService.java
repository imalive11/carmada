package com.carmada.payment.service;

import java.text.ParseException;
import java.util.List;

import com.carmada.payment.entity.Payment;

public interface PaymentService {

	public List<Payment> findAll();
	
	
	public List<Payment> findByCurrentDate() throws ParseException;
	
	public Payment findById(int id);
	
	public void save(Payment payment);
	
	public void delete(int id);
}
