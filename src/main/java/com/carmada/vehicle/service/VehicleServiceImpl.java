package com.carmada.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carmada.vehicle.dao.VehicleRepository;
import com.carmada.vehicle.entity.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	private VehicleRepository vehicleRepository;

	@Autowired
	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return vehicleRepository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional
	public Vehicle findById(int id) {
		
		Optional<Vehicle> result = this.vehicleRepository.findById(id);
		
		Vehicle vehicle = null;
		
		if(result.isPresent() == true) {
			vehicle = result.get();
		} else {
			throw new RuntimeException("Did not find Vehicle by id: " + vehicle);
		}
		return vehicle;
	}

	@Override
	@Transactional
	public void save(Vehicle vehicle) {
		this.vehicleRepository.save(vehicle);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.vehicleRepository.deleteById(id);

	}
	
	@Override
	@Transactional
	public List<Vehicle> findByPlate(String name) {
		List<Vehicle> vehicles = vehicleRepository.findByPlateNumberIgnoreCaseContains(name);
		
		return vehicles;
	}

}
