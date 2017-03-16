package com.fsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.constants.CurrentState;
import com.fsm.dao.FSMStateDao;
import com.fsm.dao.VehicleDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.VehicleService;

@Service( "vehicleService" )
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private FSMStateDao fsmStateDao;

	@Override
	public FSMState boardVehicle(Passenger passenger) {
		Vehicle v = vehicleDao.findFirstEntry();
		return boardPassengerToVehicle(passenger, v);
	}
	
	@Override
	public FSMState boardVehicle(Vehicle vehicle, Passenger passenger) {
		Vehicle v = vehicleDao.findOne(vehicle.getId());
		return boardPassengerToVehicle(passenger, v);
	}
	
	private FSMState boardPassengerToVehicle(Passenger p, Vehicle v) {
		if(v != null) {
			return vehicleSave(v, p, CurrentState.PASSENGER_BOARDED);
		}
		return null;
	}

	@Override
	public void payVehicle(Vehicle vehicle, Passenger passenger) {
		// TODO Auto-generated method stub
		if(vehicle.getTransportationCost() > passenger.getCurrentMoney()) {
			// Not enough Money
		}
		else {
			// pay transportation cost
			Double passengerMoney = passenger.getCurrentMoney() - vehicle.getTransportationCost();
			Double vehicleEarnings = vehicle.getEarnings() + vehicle.getTransportationCost();
			// check if vehicle earnings can perform change
			passenger.setCurrentMoney(passengerMoney);
			vehicle.setEarnings(vehicleEarnings);
			
			vehicleSave(vehicle, passenger, CurrentState.WAITING_TO_PAY);
		}
	}

	@Override
	public void changeResolution() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}
	
	private FSMState vehicleSave(Vehicle v, Passenger p, CurrentState s) {
		Vehicle vehicle = vehicleDao.save(v);
		FSMState status = new FSMState();
		status.setPassenger(p);
		status.setVehicle(vehicle);
		status.setCurrentState(s);
		fsmStateDao.save(status);
		return status;
	}
}
