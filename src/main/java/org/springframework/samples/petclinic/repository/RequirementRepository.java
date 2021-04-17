package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Requirement;


public interface RequirementRepository extends CrudRepository<Requirement, Integer> {
	
	void deleteById(int id) throws DataAccessException;

	Optional<Requirement> findById(Integer id) throws DataAccessException;

	Collection<Requirement> findAll() throws DataAccessException;
	
	@Modifying
	@Query("DELETE FROM Requirement o WHERE o.id = :id")
	void remove(@Param("id") Integer id);
	
}