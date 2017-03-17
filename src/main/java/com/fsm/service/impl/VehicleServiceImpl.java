package com.fsm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.constants.CurrentState;
import com.fsm.dao.VehicleDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.FSMStateService;
import com.fsm.service.VehicleService;

@Service( "vehicleService" )
public class VehicleServiceImpl implements VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private FSMStateService fsmStateService;

	@Override
	public FSMState stopVehicle(Vehicle vehicle, Passenger passenger) {
		// state waiting to stop vehicle
		logger.debug("Current State changed to... WAITING TO STOP");
		fsmStateService.saveFSMState(vehicle, passenger, CurrentState.WAITING_TO_STOP);
		// vehicle stop
		logger.debug("Current State changed to... GOT OFF. Passenger unloaded.");
		return fsmStateService.saveFSMState(vehicle, passenger, CurrentState.GOT_OFF);
	}

	@Override
	public Vehicle getFirstVehicleEntry() {
		return vehicleDao.findFirstEntry();
	}

	@Override
	public Vehicle getVehicle(Long id) {
		return vehicleDao.findOne(id);
	}

	@Override
	public Vehicle update(Vehicle vehicle) {
		return vehicleDao.save(vehicle);
	}

	@Override
	public List<Vehicle> getAll() {
		return vehicleDao.findAll();
	}

}
