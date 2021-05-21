package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdoptionService {

	private AdoptionRepository adoptionRepository;


	@Autowired
	public AdoptionService(AdoptionRepository adoptionRepository) {
		this.adoptionRepository = adoptionRepository;
	}		

	@Transactional(readOnly = true)	
	public Collection<Adoption> findAdoptions() throws DataAccessException {
		return adoptionRepository.findAll();
	}	
	
	@Transactional(readOnly = true)	
	public Optional<Adoption> findAdoptionById(int adoptionId) throws DataAccessException {
		return adoptionRepository.findById(adoptionId);
	}	
	public void delete(Adoption adoption) {
		adoptionRepository.deleteById(adoption.getId());
	}
	
	@Transactional(rollbackFor = ConstraintViolationException.class)
	public void saveAdoption(@Valid Adoption adoption)  {
		
		adoptionRepository.save(adoption);
	}	
	
	@Transactional
	public void removeAdoption(Integer id) throws DataAccessException {
		adoptionRepository.remove(id);
	}
	
	@Transactional
	public Collection<Adoption> findAllFalse() throws DataAccessException {
		return adoptionRepository.findAllFalse();
	}
	
	@Transactional
	public Collection<Adoption> findByOwner(Integer ownerId) throws DataAccessException {
		return adoptionRepository.findByOwner(ownerId);
	}
}