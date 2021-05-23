package org.springframework.samples.petclinic.web;


import org.springframework.beans.NotReadablePropertyException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CauseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cause.class.isAssignableFrom(clazz);		
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cause cause = (Cause) target;
		String name = cause.getName();
		String descripcion = cause.getDescription();
		String org = cause.getOrganization();
		
		// name validation
		if (!StringUtils.hasLength(name)) {
			errors.rejectValue("name", "El nombre no puede estar vacío");
		}
		if (!StringUtils.hasLength(descripcion)) {
			errors.rejectValue("description", "La descripción no puede estar vacía");
		}
		if (!StringUtils.hasLength(org)) {
			errors.rejectValue("organization", "La organización no puede estar vacía");
		}
		if (cause.getBudgetTarget()==null) {
			errors.rejectValue("budgetTarget", "Tiene que haber una cantidad objetivo");
		}
		
	}
		
}


