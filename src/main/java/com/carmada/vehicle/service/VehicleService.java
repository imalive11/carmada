package com.carmada.vehicle.service;

import java.util.List;

import com.carmada.vehicle.entity.Vehicle;

public interface VehicleService {

	public List<Vehicle> findAll();
	
	public Vehicle findById(int id);
	
	public void save(Vehicle vehicle);
	
	public void delete(int id);

	public List<Vehicle> findByPlate(String name);


}
