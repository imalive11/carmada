package com.carmada.payment.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	public List<Payment> findAllByOrderByIdDesc();
	
	public List<Payment> findByTravelDateAfter(Date dateToday);
	
	public List<Payment> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(String firstName, String lastName);
}
