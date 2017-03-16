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
import com.fsm.service.VehicleService;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class VehicleServiceImplTest {
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Test
	public void waitVehicleShouldReturnFSMState() {
		Passenger p = getPassenger();
		FSMState status = vehicleService.waitVehicle(p);
		assertNotNull("Status null", status);
		assertEquals(status.getCurrentState(), CurrentState.WAITING_VEHICLE);
	}
	
	@Test
	public void boardVehicleWithPassengerShouldReturnFSMState() {
		Passenger p = getPassenger();
		FSMState status = vehicleService.boardVehicle(p);
		assertNotNull("Vehicle null", status.getVehicle());
	}
	
	@Test
	public void waitVehiclePaymentShouldReturnFSMState() {
		Passenger p = getPassenger();
		FSMState status = vehicleService.boardVehicle(p);
		assertNotNull("Vehicle null", status.getVehicle());
		status = vehicleService.waitVehiclePayment(p, status.getVehicle());
		assertNotNull("Status null", status);
		assertEquals(status.getCurrentState(), CurrentState.WAITING_TO_PAY);
	}
	
	public void payVehicleShouldReturnFSMState() {
		Passenger passenger = getPassenger();
		FSMState status = vehicleService.boardVehicle(passenger);
		assertNotNull("Vehicle null", status.getVehicle());
		Vehicle vehicle = status.getVehicle();
		status = vehicleService.payVehicle(vehicle, passenger);
	}
	
	private Passenger getPassenger() {
		Passenger p = passengerDao.findOne(1L);
		assertNotNull("Passenger null", p);
		return p;
	}
}
