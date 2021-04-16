package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {
	
	private static DonationRepository donationRepository;

	
	@Transactional(readOnly = true)
	public Optional<Donation> findDonationById(int id) throws DataAccessException {
		return donationRepository.findById(id);
	}
	
	public Collection<Donation> findByCauseId(int causeId) throws DataAccessException {
		return donationRepository.findByCauseId(causeId);
	}
	
	@Transactional(readOnly = true)	
	public static Collection<Donation> findSpecialties() throws DataAccessException {
		return donationRepository.findAll();
	}	
	
	@Transactional
	public static void saveDonation(Donation d) throws DataAccessException {
		donationRepository.save(d);
	}	
}
