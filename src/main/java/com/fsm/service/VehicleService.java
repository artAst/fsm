package com.fsm.service;

import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;

public interface VehicleService {
	
	public FSMState waitVehicle(Passenger passenger);
	
	public FSMState waitVehiclePayment(Passenger passenger, Vehicle vehicle);
	
	public FSMState boardVehicle(Passenger passenger);
	
	public FSMState boardVehicle(Vehicle vehicle, Passenger passenger);
	
	public FSMState payVehicle(Vehicle vehicle, Passenger passenger);
	
	public FSMState stopVehicle(Vehicle vehicle, Passenger passenger);
}
