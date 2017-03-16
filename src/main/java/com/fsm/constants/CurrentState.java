package com.fsm.constants;

/**
 * FSM current state definitions.
 */
public enum CurrentState {
	
	/**
     * The Passenger waiting for vehicle state.
     */
    WAITING_VEHICLE,
	
	/**
     * The Vehicle with Passenger boarded state.
     */
    PASSENGER_BOARDED,

    /**
     * The Email on read state.
     */
    WAITING_TO_PAY,
    
    /**
     * The Email on read state.
     */
    WAITING_FOR_CHANGE,
    
    /**
     * The Email on read state.
     */
    CHANGE_RESOLUTION,
    
    /**
     * The Email on read state.
     */
    RIDING_VEHICLE,
    
    /**
     * The Email on read state.
     */
    WAITING_TO_STOP,

    /**
     * The Email on archived state.
     */
    GOT_OFF
}
