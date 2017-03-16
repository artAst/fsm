package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.fsm.model.Passenger;

@Transactional
public interface PassengerDao extends CrudRepository<Passenger, Long> {

}
