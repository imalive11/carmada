package com.carmada.incident.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carmada.incident.dao.IncidentRepository;
import com.carmada.incident.entity.Incident;

@Service
public class IncidentServiceImpl implements IncidentService {
	
	@Autowired
	private IncidentRepository incidentRepository;

	@Override
	@Transactional
	public List<Incident> findAll() {
		return incidentRepository.findAllByOrderById();
	}

	@Override
	@Transactional
	public Incident findById(int id) {
		
		Optional<Incident> result = this.incidentRepository.findById(id);
		
		Incident incident = null;
		
		if(result.isPresent() == true) {
			incident = result.get();
		} else {
			throw new RuntimeException("Did not find Vehicle by id: " + incident);
		}
		return incident;
	}

	@Override
	@Transactional
	public void save(Incident incident) {
		this.incidentRepository.save(incident);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.incidentRepository.deleteById(id);

	}

	@Override
	public List<Incident> findByDriver(String name) {
		// TODO Auto-generated method stub
		return this.incidentRepository.DriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContains(name, name);
	}

	@Override
	public List<Incident> findByDriverAndIncidentDate(String name, Date incidentDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndIncidentDate(name, name, incidentDate);
	}

	@Override
	public List<Incident> findByIncidentDate(Date incidentDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findByIncidentDate(incidentDate);
	}

	@Override
	public List<Incident> findAllByDriverAndDate(int driverId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findAllByDriverIdAndIncidentDateBetween(driverId, startDate, endDate);
	}

	@Override
	public List<Incident> findByDriverId(int id) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findByDriverId(id);
	}
	
	@Override
	public List<Incident> findByVehicleId(int vehicleId) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findByVehicleId(vehicleId);
	}

	@Override
	public List<Incident> findAllByVehicleAndDate(int vehicleId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findAllByVehicleIdAndIncidentDateBetween(vehicleId, startDate, endDate);
	}

	@Override
	public List<Incident> findAllByDriverNameAndDate(String name, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findByDriverFirstNameIgnoreCaseContainsOrDriverLastNameIgnoreCaseContainsAndIncidentDateBetween(name, name, startDate, endDate);
	}

	@Override
	public List<Incident> findByIncidentDateBetween(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return this.incidentRepository.findAllByIncidentDateBetween(startDate, endDate);
	}

	@Override
	public List<Incident> findIncidentsForCurrentMonth() {
		// TODO Auto-generated method stub
		return this.incidentRepository.findIncidentsForCurrentMonth();
	}

}
