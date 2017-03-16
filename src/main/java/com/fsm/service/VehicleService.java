package com.fsm.service;

import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;

public interface VehicleService {
	
	public FSMState boardVehicle(Passenger passenger);
	
	public FSMState boardVehicle(Vehicle vehicle, Passenger passenger);
	
	public void payVehicle(Vehicle vehicle, Passenger passenger);
	
	public void changeResolution();
	
	public void stopVehicle(Vehicle vehicle);
}
