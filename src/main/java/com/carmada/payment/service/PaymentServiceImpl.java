package com.carmada.payment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Payment> findAll(Pageable pageable) {
		return paymentRepository.findAllByOrderByIdDesc(pageable);
	}
	
	@Override
	@Transactional
	public Page<Payment> findByCurrentDate(Pageable pageable) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now().minusDays(1);  
		System.out.println();  
		
		Date dateToday = new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(now));
		return paymentRepository.findPaymentsAfterDateOrderByVehicleIdAndFranchiseAsc(dateToday, pageable);
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
	public Page<Payment> findByDriver(Pageable pageable, String driverName) {
		Page<Payment> result = 
				this.paymentRepository
					.findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(pageable, driverName, driverName);
		
		return result;
	}
	
	@Override
	@Transactional
	public Payment findLatestPayment() {
		return paymentRepository.findFirstByOrderByTravelDateDesc();
	}
	
	@Override
	public Page<Payment> findAllByDate(Pageable pageable, Date startDate, Date endDate) {

		return paymentRepository.findAllByTravelDateBetweenOrderByIdDesc(pageable, startDate, endDate);
	}
	
	@Override
	public Page<Payment> findAllByTravelDateOrderByVehicleId(Pageable pageable, Date startDate, Date endDate) {

		return paymentRepository.findPaymentsAfterDateOrderByVehicleIdAndFranchiseAsc(startDate, endDate, pageable);
	}
	
	@Override
	@Transactional
	public Page<Payment> findLatestDayTravelDate(Pageable pageable) {
		Date latestTravelDate = this.findLatestTravelDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(latestTravelDate);

		// Subtract one day from the Calendar object
		cal.add(Calendar.DAY_OF_MONTH, -1);

		// Get the new date from the Calendar object
		Date yesterday = cal.getTime();

		return paymentRepository.findPaymentsAfterDateOrderByVehicleIdAndFranchiseAsc(yesterday, pageable);
	}
	
	@Override
	public Page<Payment> findByDriverId(Pageable pageable, int id) {
		return paymentRepository.findByDriverId(pageable, id);
	}
	
	@Override
	public Page<Payment> findAllByIdAndTravelDateBetween(int id, Date startDate, Date endDate, Pageable pageable) {
		// TODO Auto-generated method stub
		return paymentRepository.findAllByDriverIdAndTravelDateBetween(pageable, id, startDate, endDate);
	}
	@Override
	public Page<Payment> findByDriverAndTravelDate(Pageable pageable, String driverName, Date date) {
		// TODO Auto-generated method stub
		return this.paymentRepository
				.findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndTravelDateBetween(pageable, driverName, driverName, date, date);
	}
	@Override
	public Page<Payment> findAllByVehicleIdAndDate(Pageable pageable, int vehicleId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return this.paymentRepository
				.findAllByVehicleIdAndTravelDateBetween(pageable, vehicleId, startDate, endDate);
	}
	
	@Override
	public Page<Payment> findByVehicleId(Pageable pageable, int id) {
		return paymentRepository.findByVehicleId(pageable, id);
	}
	
	@Override
	public Date findFirstTravelDate() {
		// TODO Auto-generated method stub
		return paymentRepository.findFirstTravelDate();
	}
	
	@Override
	public Date findLatestTravelDate() {
		// TODO Auto-generated method stub
		return paymentRepository.findLatestTravelDate();
	}
	
	@Override
	public Date findLatestPaymentDate() {
		// TODO Auto-generated method stub
		return paymentRepository.findLatestPaymentDate();
	}
	
	@Override
	public Date getFirstDateForYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date getLatestDateForYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Payment> findLatePaymentDate(Date travelDate) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(travelDate);

		// Subtract one day from the Calendar object
		cal.add(Calendar.DAY_OF_MONTH, 1);

		// Get the new date from the Calendar object
		Date paymentDate = cal.getTime();

		return paymentRepository.findLatePayments(travelDate, paymentDate);
	}
	
	@Override
	public List<Payment> findLatestLatePaymentDate() {
		
		Date latestTravelDate = this.findLatestTravelDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(latestTravelDate);

		// Subtract one day from the Calendar object
		cal.add(Calendar.DAY_OF_MONTH, 1);

		// Get the new date from the Calendar object
		Date paymentDate = cal.getTime();

		return paymentRepository.findLatePayments(latestTravelDate, paymentDate);
	}
	
	@Override
	public Page<Payment> searchByRemarksLike(String remarks, Pageable pageable) {
		Page<Payment> result = 
				this.paymentRepository
					.findByRemarksContains(pageable, remarks);
		
		return result;
	}
	
	@Override
	public Page<Payment> searchByRemarksLikeAndTravelDate(String remarks, Date startDate, Pageable pageable) {
		// TODO Auto-generated method stub
		return paymentRepository.searchByRemarksLikeAndTravelDate(remarks, startDate, pageable);
	}
	
	@Override
	public Page<Payment> searchByRemarksLikeAndDriverName(String remarks, String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return paymentRepository.searchByRemarksLikeAndDriverName(remarks, name, name, pageable);
	}
	
	@Override
	public Page<Payment> searchByRemarksLikeAndDriverNameAndtravelDate(String remarks, String firstName,
			String lastName, Date travelDate, Pageable pageable) {
		// TODO Auto-generated method stub
		return paymentRepository.searchByRemarksLikeAndDriverNameAndtravelDate(remarks, firstName, lastName, travelDate, pageable);
	}


}
