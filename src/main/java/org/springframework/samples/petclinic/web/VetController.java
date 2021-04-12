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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;
	
	private final SpecialtyService specialtyService;

	@Autowired
	public VetController(VetService clinicService, SpecialtyService specialtyService) {
		this.vetService = clinicService;
		this.specialtyService = specialtyService;
	}
	
	@ModelAttribute("specialties")
	public Collection<Specialty> populatePetTypes() {
		return this.specialtyService.findSpecialties();
	}
	

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	
	@GetMapping("/vets/new")
	public String editNewVet(ModelMap model) {
		
		model.addAttribute("vet",new Vet());
		return "vets/createOrUpdateVetForm";
	}
	
	@PostMapping("/vets/new")
	public String saveNewVet(@Valid Vet vet, @RequestParam(value="specialties", required= false) Collection<Specialty> specialties, BindingResult binding,ModelMap model) {
		
		if(binding.hasErrors()) {		
			return "vets/createOrUpdateVetForm";
		}else {
			
				if (!(specialties==null)) {
					for(Specialty s : specialties){
						vet.addSpecialty(s);
					}
				}
            	this.vetService.save(vet);
            }
		
			
	
			System.out.println("Excepción lanzada------------------------------------------------------------------------------");
			this.vetService.save(vet);
			return "redirect:/" + "vets";
       
	}
	
	@GetMapping("vets/{id}/edit")
	public String editLogro(@PathVariable("id") int id, ModelMap model) {
		Vet vet = vetService.findById(id);
		Collection<Specialty> specialties = SpecialtyService.findSpecialties();
		Collection<Specialty> specialtiesVet = vet.getSpecialties();
		List<Specialty> especialidadesRestantes = new ArrayList<Specialty>();
		for(Specialty s: specialties) {
			if(!specialtiesVet.contains(s)){
				especialidadesRestantes.add(s);
			}
		}
		model.addAttribute("vet", vet);
		model.addAttribute("specialties",specialties);
		model.addAttribute("specialtiesNoSeleccionadas",especialidadesRestantes);
		return "vets/createOrUpdateVetForm";
	} 

	@PostMapping("vets/{id}/edit")
	public String editLogro(@PathVariable("id") int id, @RequestParam(value="specialties", required= false)Collection<Specialty> specialties, @Valid Vet modifiedVet, BindingResult binding,
			ModelMap model) {
		Vet vet = vetService.findById(id);
		if (binding.hasErrors()) {
			return "vets/createOrUpdateVetForm";
		} 
		BeanUtils.copyProperties(modifiedVet, vet, "id");
		if (!(specialties==null)) {
			for(Specialty s : specialties){
				vet.addSpecialty(s);
			}
			}
		vetService.save(vet);
		model.addAttribute("message", "vet updated succesfully!");
		return "redirect:/" +"vets";
		}

		
	
	
	@GetMapping(value = { "/vets/{vetId}/delete" })
    public String deleteVet(@PathVariable("vetId") int vetId) {
    	vetService.removeVet(vetId);
    	return "redirect:/vets";
    }

}
