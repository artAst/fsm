package com.fsm.api.controller;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class FSMRestApiControllerTest {
	
	private static final String PASSENGER_LIST_RESOURCE = "/api/passenger";
	
	@Test
	public void getPassengerListShouldReturn() {
		
	}
}
