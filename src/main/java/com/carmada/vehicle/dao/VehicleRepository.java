package com.carmada.vehicle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.vehicle.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	public List<Vehicle> findAllByOrderByIdAsc();
}
