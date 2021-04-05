package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;

public interface SpecialtiesRepository extends Repository<Specialty, Integer> {
	
	Collection<Specialty> findAll() throws DataAccessException;
	
	Specialty findById(int id);

	void save(@Valid Specialty especialidad);

}
