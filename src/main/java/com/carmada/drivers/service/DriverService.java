package com.carmada.drivers.service;

import java.util.List;

import com.carmada.drivers.entity.Driver;

public interface DriverService {

	public List<Driver> findAll();
	
	public List<Driver> findAllActiveDrivers();
	
	public Driver findById(int id);
	
	public void save(Driver driver);
	
	public void delete(int id);

	public List<Driver> findByName(String name);
	
	public List<Driver> findByNameByStatus(String name);

	public void deactivate(int theId);
}
