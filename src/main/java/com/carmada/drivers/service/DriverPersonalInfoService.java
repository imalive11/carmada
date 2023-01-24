package com.carmada.drivers.service;

import java.util.List;

import com.carmada.drivers.entity.DriverPersonalInfo;

public interface DriverPersonalInfoService {
	
	public List<DriverPersonalInfo> findAll();
	
	public DriverPersonalInfo findById(int id);

}
