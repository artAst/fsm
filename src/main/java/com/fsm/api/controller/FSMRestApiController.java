package com.fsm.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsm.api.model.FSMErrorType;
import com.fsm.model.Passenger;
import com.fsm.model.Vehicle;
import com.fsm.service.PassengerService;
import com.fsm.service.VehicleService;

@RestController
@RequestMapping("/api")
public class FSMRestApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(FSMRestApiController.class);
	
	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping( value = "/passenger", method = RequestMethod.GET )
	public ResponseEntity<List<Passenger>> listAllPassengers() {
		logger.info("listAllPassengers: service get all passengers");
		List<Passenger> passengers = passengerService.getAllPassengers();
		if(passengers.isEmpty()) {
			logger.info("passenger list empty...");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.OK);
	}
	
	@RequestMapping( value = "/vehicle", method = RequestMethod.GET )
	public ResponseEntity<List<Vehicle>> listAllVehicle() {
		logger.info("listAllVehicle: service get all vehicle");
		List<Vehicle> vehicles = vehicleService.getAll();
		if(vehicles.isEmpty()) {
			logger.info("vehicle list empty...");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	@RequestMapping( value = "/passenger/{id}", method = RequestMethod.GET )
	public ResponseEntity<?> getPassenger(@RequestParam( name = "id" ) long id) {
		logger.info("getPassenger: fetching passenger with id {}", id);
		Passenger passenger = passengerService.getPassengerById(id);
		if(passenger == null) {
			logger.info("passenger with id {} not found...", id);
			return new ResponseEntity<FSMErrorType>(new FSMErrorType("passenger with id "+ id
					+" not found..."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
	}
	
	@RequestMapping( value = "/vehicle/{id}", method = RequestMethod.GET )
	public ResponseEntity<?> getVehicle(@RequestParam( name = "id" ) long id) {
		logger.info("getVehicle: fetching vehicle with id {}", id);
		Vehicle v = vehicleService.getVehicle(id);
		if(v == null) {
			logger.info("vehicle with id {} not found...", id);
			return new ResponseEntity<FSMErrorType>(new FSMErrorType("vehicle with id "+ id
					+" not found..."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(v, HttpStatus.OK);
	}
}
