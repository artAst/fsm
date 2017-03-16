package com.fsm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fsm.constants.CurrentState;

@Entity
@Table(name = "fsm_state")
public class FSMState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne( targetEntity = Vehicle.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "vehicle_id" )
	private Vehicle vehicle;
	
	@OneToOne( targetEntity = Passenger.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "passenger_id" )
	private Passenger passenger;
	
	@Enumerated(EnumType.STRING)
	@Column( name = "current_state" )
	private CurrentState currentState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public CurrentState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}
}
