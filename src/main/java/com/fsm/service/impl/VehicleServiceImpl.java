package com.fsm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.constants.CurrentState;
import com.fsm.dao.FSMStateDao;
import com.fsm.dao.PassengerDao;
import com.fsm.dao.VehicleDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.VehicleService;

@Service( "vehicleService" )
public class VehicleServiceImpl implements VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private FSMStateDao fsmStateDao;
	
	@Override
	public FSMState waitVehicle(Passenger passenger) {
		logger.debug("Current State changed to... WAITING VEHICLE");
		return vehicleSave(null, passenger, CurrentState.WAITING_VEHICLE);
	}
	
	@Override
	public FSMState waitVehiclePayment(Passenger passenger, Vehicle vehicle) {
		logger.debug("Current State changed to... WAITING TO PAY");
		return vehicleSave(vehicle, passenger, CurrentState.WAITING_TO_PAY);
	}

	@Override
	public FSMState boardVehicle(Passenger passenger) {
		logger.debug("Passenger boarding to vehicle without vehicle param...");
		Vehicle v = vehicleDao.findFirstEntry();
		return boardPassengerToVehicle(passenger, v);
	}
	
	@Override
	public FSMState boardVehicle(Vehicle vehicle, Passenger passenger) {
		logger.debug("Passenger boarding to vehicle with vehicle param...");
		Vehicle v = vehicleDao.findOne(vehicle.getId());
		return boardPassengerToVehicle(passenger, v);
	}
	
	private FSMState boardPassengerToVehicle(Passenger p, Vehicle v) {
		if(v != null) {
			logger.debug("Current State changed to... PASSENGER BOARDED");
			return vehicleSave(v, p, CurrentState.PASSENGER_BOARDED);
		}
		return null;
	}

	@Override
	public FSMState payVehicle(Vehicle vehicle, Passenger passenger) {
		// set state to 'wating for change'
		logger.debug("Current State changed to... WAITING FOR CHANGE");
		vehicleSave(vehicle, passenger, CurrentState.WAITING_FOR_CHANGE);
		
		if(vehicle.getTransportationCost() > passenger.getCurrentMoney()) {
			// Not enough Money
			// state waiting to stop vehicle
			logger.debug("Passenger not enough money. Current State changed to... WAITING TO STOP");
			vehicleSave(vehicle, passenger, CurrentState.WAITING_TO_STOP);
			// vehicle stop
			logger.debug("Current State changed to... GOT OFF. Passenger unloaded.");
			return vehicleSave(vehicle, passenger, CurrentState.GOT_OFF);
		}
		else {
			// pay transportation cost
			Double passengerMoney = passenger.getCurrentMoney() - vehicle.getTransportationCost();
			Double vehicleEarnings = vehicle.getEarnings() + vehicle.getTransportationCost();
			// check if vehicle earnings can perform change
			passenger.setCurrentMoney(passengerMoney);
			vehicle.setEarnings(vehicleEarnings);
			
			passengerDao.save(passenger);
			logger.debug("Current State changed to... RIDING VEHICLE");
			return vehicleSave(vehicle, passenger, CurrentState.RIDING_VEHICLE);
		}
	}
	
	@Override
	public FSMState stopVehicle(Vehicle vehicle, Passenger passenger) {
		// state waiting to stop vehicle
		logger.debug("Current State changed to... WAITING TO STOP");
		vehicleSave(vehicle, passenger, CurrentState.WAITING_TO_STOP);
		// vehicle stop
		logger.debug("Current State changed to... GOT OFF. Passenger unloaded.");
		return vehicleSave(vehicle, passenger, CurrentState.GOT_OFF);
	}
	
	private FSMState vehicleSave(Vehicle v, Passenger p, CurrentState s) {
		FSMState status = new FSMState();
		if(v != null) {
			logger.debug("Vehicle not null... saving vehicle and set to status");
			Vehicle vehicle = vehicleDao.save(v);
			status.setVehicle(vehicle);
		}
		status.setPassenger(p);
		status.setCurrentState(s);
		fsmStateDao.save(status);
		logger.debug("Current State saved...");
		return status;
	}

}
