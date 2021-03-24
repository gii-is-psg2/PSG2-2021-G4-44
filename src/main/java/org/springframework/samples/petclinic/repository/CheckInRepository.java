package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.CheckIn;
import org.springframework.samples.petclinic.model.Visit;


public interface CheckInRepository extends Repository<Visit, Integer> {

	void save(CheckIn chechkIn) throws DataAccessException;
	
	void deleteById(int id) throws DataAccessException;

	Optional<CheckIn> findById(Integer id) throws DataAccessException;

	Collection<CheckIn> findAll() throws DataAccessException;
	

}