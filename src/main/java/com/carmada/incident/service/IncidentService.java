package com.carmada.incident.service;

import java.util.Date;
import java.util.List;

import com.carmada.incident.entity.Incident;

public interface IncidentService {

	public List<Incident> findAll();
	
	public Incident findById(int id);
	
	public void save(Incident incident);
	
	public void delete(int id);

	public List<Incident> findByDriver(String name);
	
	public List<Incident> findByDriverAndIncidentDate(String name, Date date);

	public List<Incident> findByIncidentDate(Date incidentDate);
	
	public List<Incident> findAllByDriverAndDate(int id, Date startDate, Date endDate);
	
	public List<Incident> findAllByVehicleAndDate(int id, Date startDate, Date endDate);
	
	public List<Incident> findByDriverId(int id);

	public List<Incident> findByVehicleId(int id);
}
