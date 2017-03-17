package com.fsm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.constants.CurrentState;
import com.fsm.dao.PassengerDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.FSMStateService;
import com.fsm.service.PassengerService;
import com.fsm.service.VehicleService;

@Service( "passengerService" )
public class PassengerServiceImpl implements PassengerService {
	
	private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private FSMStateService fsmStateService;
	
	@Autowired
	private VehicleService vehicleService;

	@Override
	public FSMState waitVehicle(Passenger passenger) {
		logger.debug("Current State changed to... WAITING VEHICLE");
		return fsmStateService.saveFSMState(null, passenger, CurrentState.WAITING_VEHICLE);
	}

	@Override
	public FSMState waitVehiclePayment(Passenger passenger, Vehicle vehicle) {
		logger.debug("Current State changed to... WAITING TO PAY");
		return fsmStateService.saveFSMState(vehicle, passenger, CurrentState.WAITING_TO_PAY);
	}
	
	@Override
	public FSMState boardVehicle(Passenger passenger) {
		logger.debug("Passenger boarding to vehicle without vehicle param...");
		Vehicle v = vehicleService.getFirstVehicleEntry();
		if(v != null) {
			logger.debug("Current State changed to... PASSENGER BOARDED");
			return fsmStateService.saveFSMState(v, passenger, CurrentState.PASSENGER_BOARDED);
		}
		return null;
	}
	
	@Override
	public FSMState boardVehicle(Vehicle vehicle, Passenger passenger) {
		logger.debug("Passenger boarding to vehicle with vehicle param...");
		Vehicle v = vehicleService.getVehicle(vehicle.getId());
		if(v != null) {
			logger.debug("Current State changed to... PASSENGER BOARDED");
			return fsmStateService.saveFSMState(v, passenger, CurrentState.PASSENGER_BOARDED);
		}
		return null;
	}
	
	@Override
	public FSMState payVehicle(Vehicle vehicle, Passenger passenger) {
		// set state to 'wating for change'
		logger.debug("Current State changed to... WAITING FOR CHANGE");
		fsmStateService.saveFSMState(vehicle, passenger, CurrentState.WAITING_FOR_CHANGE);
		
		if(vehicle.getTransportationCost() > passenger.getCurrentMoney()) {
			// Not enough Money
			// state waiting to stop vehicle
			logger.debug("Passenger not enough money. Current State changed to... WAITING TO STOP");
			fsmStateService.saveFSMState(vehicle, passenger, CurrentState.WAITING_TO_STOP);
			// vehicle stop
			logger.debug("Current State changed to... GOT OFF. Passenger unloaded.");
			return fsmStateService.saveFSMState(vehicle, passenger, CurrentState.GOT_OFF);
		}
		else {
			// pay transportation cost
			Double passengerMoney = passenger.getCurrentMoney() - vehicle.getTransportationCost();
			Double vehicleEarnings = vehicle.getEarnings() + vehicle.getTransportationCost();
			// deduct transpo cost to passenger
			passenger.setCurrentMoney(passengerMoney);
			// add transpo cost to vehicle
			vehicle.setEarnings(vehicleEarnings);
			vehicleService.update(vehicle);
			
			passengerDao.save(passenger);
			logger.debug("Current State changed to... RIDING VEHICLE");
			return fsmStateService.saveFSMState(vehicle, passenger, CurrentState.RIDING_VEHICLE);
		}
	}

	@Override
	public void clickCoinOnRoof() {
		// TODO Auto-generated method stub
		
	}

}
