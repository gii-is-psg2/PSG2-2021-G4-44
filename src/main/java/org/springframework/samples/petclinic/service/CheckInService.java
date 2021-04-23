package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.CheckIn;
import org.springframework.samples.petclinic.repository.CheckInRepository;
import org.springframework.samples.petclinic.service.exceptions.TwoPetsCheckInsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckInService {

	private CheckInRepository checkInRepository;

	@Autowired
	public CheckInService(CheckInRepository checkInRepository) {
		this.checkInRepository = checkInRepository;
	}

	@Transactional(readOnly = true)
	public Collection<CheckIn> findCheckIns() throws DataAccessException {
		return checkInRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<CheckIn> findCheckInById(int checkInId) throws DataAccessException {
		return checkInRepository.findById(checkInId);
	}

	public void delete(CheckIn checkIn) {
		checkInRepository.deleteById(checkIn.getId());
	}

	@Transactional(readOnly = true)
	public List<CheckIn> findPetsCheckIns() throws DataAccessException {
		return checkInRepository.findAll().stream().filter(x -> x.getFechaSalida().isAfter(LocalDate.now()))
				.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = ConstraintViolationException.class)
	public void saveCheckIn(@Valid CheckIn checkIn) throws TwoPetsCheckInsException {
		List<CheckIn> lista = findPetsCheckIns();
		for (int i = 0; lista.size() > i; i++) {
			if (lista.get(i).getPet().equals(checkIn.getPet())) {
				throw new TwoPetsCheckInsException();
			}
		}
		checkInRepository.save(checkIn);
	}

	@Transactional
	public void removeCheckIn(Integer id) throws DataAccessException {
		checkInRepository.remove(id);
	}
}