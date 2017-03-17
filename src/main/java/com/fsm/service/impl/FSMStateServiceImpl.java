package com.fsm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.constants.CurrentState;
import com.fsm.dao.FSMStateDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.FSMStateService;

@Service( "fsmStateService" )
public class FSMStateServiceImpl implements FSMStateService {
	
	private static final Logger logger = LoggerFactory.getLogger(FSMStateServiceImpl.class);
	
	@Autowired
	private FSMStateDao fsmStateDao;

	@Override
	public FSMState saveFSMState(Vehicle v, Passenger p, CurrentState s) {
		FSMState status = new FSMState();
		status.setPassenger(p);
		status.setVehicle(v);
		status.setCurrentState(s);
		fsmStateDao.save(status);
		logger.debug("Current State saved...");
		return status;
	}

	@Override
	public List<FSMState> getAll() {
		return fsmStateDao.findAll();
	}

	@Override
	public FSMState getFSMStateById(Long id) {
		return fsmStateDao.findOne(id);
	}

}
