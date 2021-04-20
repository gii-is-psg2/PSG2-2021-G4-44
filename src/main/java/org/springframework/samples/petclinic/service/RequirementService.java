package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Requirement;
import org.springframework.samples.petclinic.repository.RequirementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RequirementService {

	private RequirementRepository requirementRepository;


	@Autowired
	public RequirementService(RequirementRepository requirementRepository) {
		this.requirementRepository = requirementRepository;
	}		

	@Transactional(readOnly = true)	
	public Collection<Requirement> findAdoptions() throws DataAccessException {
		return requirementRepository.findAll();
	}	
	
	@Transactional(readOnly = true)	
	public Optional<Requirement> findRequirementById(int adoptionId) throws DataAccessException {
		return requirementRepository.findById(adoptionId);
	}	
	public void delete(Requirement requirement) {
		requirementRepository.deleteById(requirement.getId());
	}
	
	@Transactional(rollbackFor = ConstraintViolationException.class)
	public void saveRequirement(@Valid Requirement requirement)  {
		
		requirementRepository.save(requirement);
	}	
	
	@Transactional
	public void removeRequirement(Integer id) throws DataAccessException {
		requirementRepository.remove(id);
	}
}