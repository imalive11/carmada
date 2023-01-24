package com.carmada.drivers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.carmada.drivers.dao.DriverPersonalInfoRepository;
import com.carmada.drivers.entity.DriverPersonalInfo;

public class DriverPersonalInfoServiceImpl implements DriverPersonalInfoService {

	DriverPersonalInfoRepository driverPersonalInfoRepository;
	
	@Autowired
	public DriverPersonalInfoServiceImpl(DriverPersonalInfoRepository driverPersonalInfoRepository) {
		this.driverPersonalInfoRepository = driverPersonalInfoRepository;
	}

	@Override
	public List<DriverPersonalInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPersonalInfo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
