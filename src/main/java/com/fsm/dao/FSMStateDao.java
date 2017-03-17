package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsm.model.FSMState;

@Transactional
public interface FSMStateDao extends JpaRepository<FSMState, Long>{

}
