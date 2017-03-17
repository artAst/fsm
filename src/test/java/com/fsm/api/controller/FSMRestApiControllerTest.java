package com.fsm.api.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fsm.FsmApplication;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.PassengerService;
import com.fsm.service.VehicleService;

@RunWith(SpringRunner.class)
@WebMvcTest(FSMRestApiController.class)
@ContextConfiguration( classes = {FsmApplication.class} )
public class FSMRestApiControllerTest {
	
	private static final String PASSENGER_LIST_RESOURCE = "/api/passenger";
	private static final String VEHICLE_LIST_RESOURCE = "/api/vehicle";
	private static final String PASSENGER_RESOURCE = PASSENGER_LIST_RESOURCE + "/1";
	private static final String VEHICLE_RESOURCE = VEHICLE_LIST_RESOURCE + "/1";
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private PassengerService passengerService;
	
	@MockBean
	private VehicleService vehicleService;
	
	@Test
	public void getPassengerListShouldReturnOkStatus() throws Exception {
		given(this.passengerService.getAllPassengers()).willReturn(populatePassenger());
		this.mvc.perform(get(PASSENGER_LIST_RESOURCE))
						.andExpect(status().isOk());
	}
	
	@Test
	public void getVehicleListShouldReturnOkStatus() throws Exception {
		given(this.vehicleService.getAll()).willReturn(populateVehicle());
		this.mvc.perform(get(VEHICLE_LIST_RESOURCE))
						.andExpect(status().isOk());
	}
	
	@Test
	public void getPassengerShouldReturnOkStatus() throws Exception {
		given(this.passengerService.getPassengerById(1L)).willReturn(getTestPassenger());
		this.mvc.perform(get(PASSENGER_RESOURCE).param("id", "1"))
						.andExpect(status().isOk());
	}
	
	@Test
	public void getVehicleShouldReturnOkStatus() throws Exception {
		given(this.vehicleService.getVehicle(1L)).willReturn(getTestVehicle());
		this.mvc.perform(get(VEHICLE_RESOURCE).param("id", "1"))
						.andExpect(status().isOk());
	}
	
	private List<Passenger> populatePassenger() {
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengers.add(getTestPassenger());
		return passengers;
	}
	
	private Passenger getTestPassenger() {
		Passenger p = new Passenger();
		p.setCurrentMoney(20.00);
		p.setName("Foo 1");
		return p;
	}
	
	private List<Vehicle> populateVehicle() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(getTestVehicle());
		return vehicles;
	}
	
	private Vehicle getTestVehicle() {
		Vehicle v = new Vehicle();
		v.setEarnings(0.00);
		v.setTransportationCost(8.00);
		v.setTypeName("Jeepney");
		return v;
	}
}
