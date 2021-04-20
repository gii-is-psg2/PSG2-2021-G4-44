package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Adoption;


public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
	
	void deleteById(int id) throws DataAccessException;

	Optional<Adoption> findById(Integer id) throws DataAccessException;

	Collection<Adoption> findAll() throws DataAccessException;
	
	@Modifying
	@Query("DELETE FROM adoptions a WHERE a.id = :id")
	void remove(@Param("id") Integer id);
	
	

	@Query("SELECT * FROM adoptions a WHERE a.adopted = false")
	Collection<Adoption> findAllFalse() throws DataAccessException;
	
}