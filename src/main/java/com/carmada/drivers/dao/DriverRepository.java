package com.carmada.drivers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.drivers.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	
	public List<Driver> findAllByOrderByLastNameAsc();

	public List<Driver> findByFirstNameIgnoreCaseContainsOrLastNameIgnoreCaseContains(String firstName, String lastName);
}
