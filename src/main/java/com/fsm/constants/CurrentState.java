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
     * The Vehicle waiting to pay state.
     */
    WAITING_TO_PAY,
    
    /**
     * The Passenger waiting for change state.
     */
    WAITING_FOR_CHANGE,
    
    /**
     * The change resolution state.
     */
    CHANGE_RESOLUTION,
    
    /**
     * The Passenger riding vehicle state.
     */
    RIDING_VEHICLE,
    
    /**
     * The Vehicle waiting to stop state.
     */
    WAITING_TO_STOP,

    /**
     * The Passenger got off state.
     */
    GOT_OFF
}
