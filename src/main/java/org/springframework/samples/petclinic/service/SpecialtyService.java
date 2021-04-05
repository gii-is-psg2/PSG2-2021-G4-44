package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.SpecialtiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialtyService {

	private static SpecialtiesRepository specialtyRepository;

	@Autowired
	public SpecialtyService(SpecialtiesRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}
		
	@Transactional(readOnly = true)
	public Specialty findSpecialtyById(int id) throws DataAccessException {
		return specialtyRepository.findById(id);
	}
	
	@Transactional(readOnly = true)	
	public static Collection<Specialty> findSpecialties() throws DataAccessException {
		return specialtyRepository.findAll();
	}	
	
}
