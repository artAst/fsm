package com.fsm.service;

import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;

public interface VehicleService {
	
	public Vehicle getFirstVehicleEntry();
	
	public Vehicle getVehicle(Long id);
	
	public Vehicle update(Vehicle vehicle);
	
	public FSMState stopVehicle(Vehicle vehicle, Passenger passenger);
}
