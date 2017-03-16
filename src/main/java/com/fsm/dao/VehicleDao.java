package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.fsm.model.Vehicle;

@Transactional
public interface VehicleDao extends CrudRepository<Vehicle, Long>{
	
	default Vehicle findFirstEntry() {
		return findOne(1L);
	}
}
