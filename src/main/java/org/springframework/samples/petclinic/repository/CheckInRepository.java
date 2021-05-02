package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.CheckIn;


public interface CheckInRepository extends CrudRepository<CheckIn, Integer> {
	
	void deleteById(int id) throws DataAccessException;

	Optional<CheckIn> findById(Integer id) throws DataAccessException;

	Collection<CheckIn> findAll() throws DataAccessException;
	
	@Modifying
	@Query("DELETE FROM CheckIn o WHERE o.id = :id")
	void remove(@Param("id") Integer id);
	
}