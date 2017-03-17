package com.fsm.service;

import java.util.List;

import com.fsm.constants.CurrentState;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;

public interface FSMStateService {
	
	public FSMState saveFSMState(Vehicle v, Passenger p, CurrentState s);
	
	public List<FSMState> getAll();
	
	public FSMState getFSMStateById(Long id);
}
