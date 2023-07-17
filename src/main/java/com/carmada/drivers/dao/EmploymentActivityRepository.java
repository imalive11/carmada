package com.carmada.drivers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.drivers.entity.Driver;
import com.carmada.drivers.entity.EmploymentActivity;

@Repository
public interface EmploymentActivityRepository extends JpaRepository<EmploymentActivity, Integer> {
	
	public List<Driver> findAllByOrderByIdDesc();

}
