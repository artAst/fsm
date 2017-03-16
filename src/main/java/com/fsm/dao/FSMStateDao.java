package com.fsm.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.fsm.model.FSMState;

@Transactional
public interface FSMStateDao extends CrudRepository<FSMState, Long>{

}
