/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.CheckIn;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.CheckInService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class CheckInController {

	private static final String VIEWS_CHECKIN_CREATE_OR_UPDATE_FORM = "checkIn/checkIns";
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	UserService userService;

	@Autowired
	CheckInService checkInService;

	@GetMapping(value = "/checkIn/new")
	public String initCreationForm(ModelMap model) {
		Collection<CheckIn> lsCIs = checkInService.findCheckIns();
		model.addAttribute("lsCIs", lsCIs);
		
		User usuario = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		
		Owner owner = ownerService.findOwnerByUser(usuario);
		List<Pet> lsMascota = owner.getPets();
		model.addAttribute("lsMascota", lsMascota);
		
		CheckIn checkin = new CheckIn();
		model.addAttribute("checkIn", checkin);
		
		return VIEWS_CHECKIN_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/checkIn/new")
	public String processCreationForm(@Valid CheckIn checkin,BindingResult result, ModelMap model) throws IOException {
		if(result.hasErrors()) {
			model.clear();
			model.addAttribute("checkIn", checkin);
			
			return VIEWS_CHECKIN_CREATE_OR_UPDATE_FORM;
		}else {
			//System.out.println(check.getId() + " " + check.getPet() + " " + check.getFechaEntrada() + " " + check.getFechaSalida());
			model.put("checkIn", checkin);
			checkInService.saveCheckIn(checkin);
			return "/welcome";
		}
	}
}
