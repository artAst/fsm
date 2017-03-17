package com.fsm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fsm.constants.CurrentState;
import com.fsm.dao.PassengerDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.PassengerService;
import com.fsm.service.VehicleService;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class VehicleServiceImplTest {
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private PassengerService passengerService;
	
	@Test
	public void stopVehicleShouldReturnFSMState_gotOff() {
		Passenger passenger = getPassenger();
		FSMState status = passengerService.boardVehicle(passenger);
		assertNotNull("Vehicle null", status.getVehicle());
		Vehicle vehicle = status.getVehicle();
		status = vehicleService.stopVehicle(vehicle, passenger);
		assertEquals(status.getCurrentState(), CurrentState.GOT_OFF);
	}
	
	private Passenger getPassenger() {
		Passenger p = passengerDao.findOne(1L);
		assertNotNull("Passenger null", p);
		return p;
	}
}
