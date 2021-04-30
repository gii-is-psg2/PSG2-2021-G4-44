package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.CheckIn;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CheckInValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {

		CheckIn checkIn = (CheckIn) obj;
		LocalDate fechaEntrada = checkIn.getFechaEntrada();
		LocalDate fechaSalida = checkIn.getFechaSalida();

		for (CheckIn ci : checkIn.getPet().getCheckIns()) {
			if (fechaEntrada.isBefore(ci.getFechaSalida())) {
				errors.rejectValue("fechaEntrada", "No puede solicitar otra estancia para esta mascota",
						"No puede solicitar otra estancia para esta mascota");
			}
		}

		if (fechaEntrada == null) {
			errors.rejectValue("fechaEntrada", " No puede dejar el campo vacio", "No puede dejar el campo vacio");
		}

		if (fechaEntrada.isBefore(LocalDate.now())) {
			errors.rejectValue("fechaEntrada", " debe ser posterior a hoy", " debe ser posterior a hoy");
		}

		if (fechaSalida == null) {
			errors.rejectValue("fechaSalida", " No puede dejar el campo vacio", "No puede dejar el campo vacio");
		}

		if (fechaSalida.isBefore(fechaEntrada)) {
			errors.rejectValue("fechaSalida", " debe ser posterior a la fecha de entrada",
					" debe ser posterior a la fecha de entrada");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CheckIn.class.isAssignableFrom(clazz);
	}
}
