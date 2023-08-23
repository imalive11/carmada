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
		setCoding(vehicle);
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
	
	public void setCoding(Vehicle vehicle) {
		String plateNumber = vehicle.getPlateNumber();
		
		if (plateNumber != null) {
            // Use regex to match the last character of the input string
            int lastCharacter = Integer.parseInt(plateNumber.substring(plateNumber.length() - 1));

            // Check conditions and set property accordingly
            if (lastCharacter == 1 ||lastCharacter == 2) {
            	vehicle.setCoding(1);
            } else if (lastCharacter == 3 ||lastCharacter == 4) {
            	vehicle.setCoding(2);
            } else if (lastCharacter == 5 ||lastCharacter == 6) {
            	vehicle.setCoding(3);
            } else if (lastCharacter == 7 ||lastCharacter == 8) {
            	vehicle.setCoding(4);
            } else if (lastCharacter == 9 ||lastCharacter == 0) {
            	vehicle.setCoding(5);
            }  else {
            	throw new IllegalArgumentException("Invalid Plate Number: " + plateNumber);
            }
        } 

	}

}
