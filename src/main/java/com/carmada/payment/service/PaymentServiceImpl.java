package com.carmada.payment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carmada.payment.dao.PaymentRepository;
import com.carmada.payment.entity.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	@Override
	@Transactional
	public List<Payment> findAll() {
		return paymentRepository.findAllByOrderByIdDesc();
	}
	
	@Override
	@Transactional
	public List<Payment> findByCurrentDate() throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now().minusDays(1);  
		System.out.println();  
		
		Date dateToday = new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(now));
		return paymentRepository.findByTravelDateAfter(dateToday);
	}

	@Override
	@Transactional
	public Payment findById(int id) {
		
		Optional<Payment> result = this.paymentRepository.findById(id);
		
		Payment payment = null;
		
		if(result.isPresent() == true) {
			payment = result.get();
		} else {
			throw new RuntimeException("Did not find Payment by id: " + payment);
		}
		return payment;
	}

	@Override
	@Transactional
	public void save(Payment payment) {
		this.paymentRepository.save(payment);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.paymentRepository.deleteById(id);

	}
	@Override
	public List<Payment> findByDriver(String driverName) {
		List<Payment> result = 
				this.paymentRepository
					.findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(driverName, driverName);
		
		return result;
	}


}
