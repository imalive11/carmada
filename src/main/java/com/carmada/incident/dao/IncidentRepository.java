package com.carmada.incident.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carmada.incident.entity.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
	
	public List<Incident> findAllByOrderById();
	
	public List<Incident> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndIncidentDate(String firstName, String lastName, Date incidentDate);
	
	public List<Incident> DriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(String firstName, String lastName);
	
	public List<Incident> findByIncidentDate(Date incidentDate);
	
	public List<Incident> findAllByDriverIdAndIncidentDateBetween(int id, Date startDate, Date endDate);
	
	public List<Incident> findAllByVehicleIdAndIncidentDateBetween(int id, Date startDate, Date endDate);
	
	public List<Incident> findAllByIncidentDateBetween(Date startDate, Date endDate);
	
	public List<Incident> findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndIncidentDateBetween(String firstName, String lastName, Date startDate, Date endDate);
	
	public List<Incident> findByDriverId(int id);
	
	public List<Incident> findByVehicleId(int id);
	
	@Query("SELECT i FROM Incident i WHERE MONTH(i.incidentDate) = MONTH(CURRENT_DATE()) AND YEAR(i.incidentDate) = YEAR(CURRENT_DATE())")
	public List<Incident> findIncidentsForCurrentMonth();
	
}
	