package com.fsm.service.impl;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fsm.dao.PassengerDao;
import com.fsm.dao.VehicleDao;
import com.fsm.model.FSMState;
import com.fsm.model.Passenger;
import com.fsm.service.VehicleService;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class VehicleServiceImplTest {
	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Test
	public void testBoardVehicleWithPassenger() {
		Passenger p = passengerDao.findOne(1L);
		assertNotNull("Passenger null", p);
		FSMState status = vehicleService.boardVehicle(p);
		assertNotNull("Vehicle null", status.getVehicle());
	}
}
