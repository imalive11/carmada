package com.carmada.payment.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carmada.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	public Page<Payment> findAllByOrderByIdDesc(Pageable pageable);
	
	public Page<Payment> findAllByTravelDateBetweenOrderByIdDesc(Pageable pageable, Date startDate, Date endDate);
	
	public Page<Payment> findFirst56ByOrderByIdDesc(Pageable pageable);
	
	public Payment findFirstByOrderByTravelDateDesc();
	
	public Page<Payment> findByTravelDateAfter(Pageable pageable, Date dateToday);
	
	public List<Payment> findByRemarksContains(String remarks);
	
	@Query("SELECT p FROM Payment p WHERE p.remarks LIKE :remarks")
	List<Payment> searchByRemarksLike(@Param("remarks") String remarks);
	
	@Query("SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate")
	List<Payment> searchByPaymentDateLike(@Param("paymentDate") Date paymentDate);
	
	public Page<Payment> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(Pageable pageable, String firstName, String lastName);
}
