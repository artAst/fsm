package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsm.model.Passenger;

@Transactional
public interface PassengerDao extends JpaRepository<Passenger, Long> {

}
