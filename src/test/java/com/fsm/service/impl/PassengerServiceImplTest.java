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
public class PassengerServiceImplTest {
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Test
	public void waitVehicleShouldReturnFSMState_waitingVehicle() {
		Passenger p = getPassenger();
		FSMState status = passengerService.waitVehicle(p);
		assertNotNull("Status null", status);
		assertEquals(status.getCurrentState(), CurrentState.WAITING_VEHICLE);
	}
	
	@Test
	public void boardVehicleWithPassengerShouldReturnFSMState_passengerBoarded() {
		Passenger p = getPassenger();
		FSMState status = passengerService.boardVehicle(p);
		assertNotNull("Vehicle null", status.getVehicle());
		assertEquals(status.getCurrentState(), CurrentState.PASSENGER_BOARDED);
	}
	
	@Test
	public void waitVehiclePaymentShouldReturnFSMState_waitingToPay() {
		Passenger p = getPassenger();
		FSMState status = passengerService.boardVehicle(p);
		assertNotNull("Vehicle null", status.getVehicle());
		status = passengerService.waitVehiclePayment(p, status.getVehicle());
		assertNotNull("Status null", status);
		assertEquals(status.getCurrentState(), CurrentState.WAITING_TO_PAY);
	}
	
	@Test
	public void payVehicleShouldReturnFSMState_ridingVehicle() {
		Passenger passenger = getPassenger();
		FSMState status = passengerService.boardVehicle(passenger);
		assertNotNull("Vehicle null", status.getVehicle());
		Vehicle vehicle = status.getVehicle();
		status = passengerService.payVehicle(vehicle, passenger);
		assertEquals(status.getCurrentState(), CurrentState.RIDING_VEHICLE);
	}
	
	@Test
	public void clickedCoinOnRoofShouldReturnFSMState_gotOff() {
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
