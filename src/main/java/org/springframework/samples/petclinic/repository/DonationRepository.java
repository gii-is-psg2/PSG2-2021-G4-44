package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.CheckIn;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.User;

public interface DonationRepository extends CrudRepository<Donation, Integer>{
	
	Collection<Donation> findAll() throws DataAccessException;
	
	Optional<Donation> findById(Integer id) throws DataAccessException;
	
	@Query("SELECT donation FROM Donation donation WHERE donation.cause =:causeid")//dada la id de una cause, te devuelve todas sus donaciones
	public Collection<Donation> findByCauseId(@Param("causeid") int causeId);
	
	void save(Donation donation) throws DataAccessException;

	

}
