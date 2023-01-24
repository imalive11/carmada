package com.carmada.drivers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.drivers.entity.DriverPersonalInfo;

@Repository
public interface DriverPersonalInfoRepository extends JpaRepository<DriverPersonalInfo, Integer> {

}
