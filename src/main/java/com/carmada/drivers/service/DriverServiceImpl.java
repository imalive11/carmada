package com.carmada.drivers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carmada.drivers.dao.DriverRepository;
import com.carmada.drivers.entity.Driver;

@Service
public class DriverServiceImpl implements DriverService {
	
	private DriverRepository driverRepository;

	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}
	@Override
	@Transactional
	public List<Driver> findAll() {
		return driverRepository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional
	public List<Driver> findByNameByStatus(String name) {
		
		List<Driver> result = 
				this.driverRepository
					.findByStatusTrueAndFirstNameIgnoreCaseContainsOrLastNameIgnoreCaseContains(name, name);
		
		return result;
		
	}

	@Override
	@Transactional
	public void save(Driver driver) {
		this.driverRepository.save(driver);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.driverRepository.deleteById(id);

	}
	@Override
	public Driver findById(int id) {
		Optional<Driver> result = driverRepository.findById(id);
		
		Driver driver = null;
		if(result.isPresent() == true) {
			driver = result.get();
		} else {
			throw new RuntimeException("Did not find Driver by id: " + driver);
		}
		return driver;
	}
	@Override
	public void deactivate(int theId) {
		
		Optional<Driver> result = driverRepository.findById(theId);
		
		Driver driver = null;
		if(result.isPresent() == true) {
			driver = result.get();
			if (driver.isStatus()) {
				driver.setStatus(false);
			} else driver.setStatus(true);
			
			this.driverRepository.save(driver);
		} else {
			throw new RuntimeException("Did not find Driver by id: " + driver);
		}

	}
	@Override
	public List<Driver> findAllActiveDrivers() {
		
		return this.driverRepository.findByStatusTrue();
	}
	@Override
	public List<Driver> findByName(String name) {
		List<Driver> result = 
				this.driverRepository
					.findByFirstNameIgnoreCaseContainsOrLastNameIgnoreCaseContains(name, name);
		
		return result;
	}


}
