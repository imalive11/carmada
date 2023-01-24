package com.carmada.drivers.service;

import java.util.List;

import com.carmada.drivers.entity.Driver;

public interface DriverService {

	public List<Driver> findAll();
	
	public Driver findById(int id);
	
	public void save(Driver driver);
	
	public void delete(int id);
}
