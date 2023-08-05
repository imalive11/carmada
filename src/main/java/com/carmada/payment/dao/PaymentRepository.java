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
	
	@Query("SELECT p FROM Payment p JOIN p.vehicle v WHERE p.travelDate BETWEEN :startDate AND :endDate ORDER BY v.franchise DESC, v.id ASC")
	public Page<Payment> findPaymentsAfterDateOrderByVehicleIdAndFranchiseAsc(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	public Page<Payment> findFirst56ByOrderByIdDesc(Pageable pageable);
	
	public Payment findFirstByOrderByTravelDateDesc();
	
	@Query("SELECT p FROM Payment p JOIN p.vehicle v WHERE p.travelDate > :dateToday ORDER BY v.franchise DESC, v.id ASC")
	public Page<Payment> findPaymentsAfterDateOrderByVehicleIdAndFranchiseAsc(@Param("dateToday") Date dateToday, Pageable pageable);
	
	public Page<Payment> findByPaymentDateAfter(Pageable pageable, Date dateToday);
	
	@Query("SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate AND p.travelDate < :travelDate")
	public List<Payment> findLatePayments(Date travelDate, Date paymentDate);
	
	public Page<Payment> findByRemarksContains(Pageable pageable, String remarks);
	
	@Query("SELECT p FROM Payment p WHERE p.remarks LIKE %:remarks% AND p.travelDate = :searchDate")
    Page<Payment> searchByRemarksLikeAndTravelDate(String remarks, Date searchDate, Pageable pageable);
	
	@Query("SELECT p FROM Payment p JOIN p.driver d WHERE p.remarks LIKE %:remarks% AND (d.firstName LIKE %:firstName% OR d.lastName LIKE %:lastName%)")
	Page<Payment> searchByRemarksLikeAndDriverName(@Param("remarks") String remarks, @Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);

	@Query("SELECT p FROM Payment p JOIN p.driver d WHERE p.remarks LIKE %:remarks% AND (d.firstName LIKE %:firstName% OR d.lastName LIKE %:lastName%) AND p.travelDate = :travelDate")
	public Page<Payment> searchByRemarksLikeAndDriverNameAndtravelDate(@Param("remarks") String remarks, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("travelDate") Date travelDate, Pageable pageable);

	
	@Query("SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate")
	public List<Payment> searchByPaymentDateLike(@Param("paymentDate") Date paymentDate);
	
	@Query("SELECT MIN(p.travelDate) FROM Payment p")
	public Date findFirstTravelDate();

    @Query("SELECT MAX(p.travelDate) FROM Payment p")
    public Date findLatestTravelDate();
    
    @Query("SELECT MAX(p.paymentDate) FROM Payment p")
    public Date findLatestPaymentDate();
    
    @Query("SELECT MIN(p.travelDate) FROM Payment p WHERE YEAR(p.travelDate) = :year")
    Date findFirstDateByYear(@Param("year") int year);

    @Query("SELECT MAX(p.travelDate) FROM Payment p WHERE YEAR(p.travelDate) = :year")
    Date findLatestDateByYear(@Param("year") int year);
	
	public Page<Payment> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(Pageable pageable, String firstName, String lastName);

	public Page<Payment> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndTravelDateBetween(Pageable pageable, String firstName, String lastName, Date startDate, Date endDate);
	
	public Page<Payment> findAllByDriverIdAndTravelDateBetween(Pageable pageable, int id, Date startDate, Date endDate);
	
	public Page<Payment> findByDriverId(Pageable pageable, int driverId);
	
	public Page<Payment> findByVehicleId(Pageable pageable, int vehicleId);
	
	public Page<Payment> findByVehiclePlateNumberIgnoreCaseContains(Pageable pageable, String plateNumber);
	
	public Page<Payment> findByVehiclePlateNumberIgnoreCaseContainsAndTravelDateBetween(Pageable pageable, String plateNumber, Date startDate, Date endDate);
	
	public Page<Payment> findAllByVehicleIdAndTravelDateBetween(Pageable pageable, int vehicleId, Date startDate, Date endDate);
}
