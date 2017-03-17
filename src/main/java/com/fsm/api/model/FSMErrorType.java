package com.fsm.api.model;

public class FSMErrorType {
	
	private String errorMessage;
	
	public FSMErrorType(String errorMsg) {
		this.errorMessage = errorMsg;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
