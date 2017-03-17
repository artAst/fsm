package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsm.model.Vehicle;

@Transactional
public interface VehicleDao extends JpaRepository<Vehicle, Long>{
	
	default Vehicle findFirstEntry() {
		return findOne(1L);
	}
}
