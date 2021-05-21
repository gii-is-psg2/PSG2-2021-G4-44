	package org.springframework.samples.petclinic.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Requirement;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RequirementService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdoptionController {

	private static final String VIEWS_ADOPTION_CREATE_FORM = "adoptions/adoptionsForm";
	
	@Autowired
	AdoptionService adoptionService;
	@Autowired
	UserService userService;
	@Autowired
	OwnerService ownerService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	PetService petService;

	
	
	@GetMapping(value = { "/adoptions"})
	public String showAdoptionList(Map<String, Object> model) {
		List<Adoption> adoptions = this.adoptionService.findAllFalse().stream().collect(Collectors.toList());
		
		User usuario = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Owner owner = ownerService.findOwnerByUser(usuario);
		
		List<Adoption> misAdoptions = (List<Adoption>) this.adoptionService.findByOwner(owner.getId());
		
		model.put("adoptions", adoptions);
		model.put("misAdoptions", misAdoptions);
		
		return "adoptions/adoptionsList";
	}
	
	@GetMapping(value = { "/adoptions/details/{adoptionId}" })
	public String showAdoptionDetails(ModelMap model, @PathVariable("adoptionId") int adoptionId) {
		if(adoptionService.findAdoptionById(adoptionId).get().getOwner().getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
			model.put("me", true);
		}else {model.put("me", false);}
		
		Adoption adoption = this.adoptionService.findAdoptionById(adoptionId).get();
		model.put("adoption", adoption);

		Collection<Requirement> requirements = this.requirementService
				.findRequirementByAdoptionId(adoptionService.findAdoptionById(adoptionId).get());
		model.put("requirements", requirements);

		return "adoptions/adoptionDetails";

	}
	
	@GetMapping(value = "/adoptions/new")
	public String initCreationForm(ModelMap model) {
		User usuario = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Owner owner = ownerService.findOwnerByUser(usuario);
		List<Pet> lsMascota = owner.getPets();
		model.put("lsMascota", lsMascota);
		
		

		
		Adoption adoption= new Adoption();
		model.put("adoption", adoption);
		return VIEWS_ADOPTION_CREATE_FORM;
	}
	
	@PostMapping(value = "/adoptions/new")
	public String processCreationForm(@Valid Adoption adoption,BindingResult result, ModelMap model) throws IOException {		
		if(result.hasErrors()) {
			model.clear();
			model.put("adoption", adoption);
			
			return VIEWS_ADOPTION_CREATE_FORM;
		}else {
			
			User usuario = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
			Owner owner = ownerService.findOwnerByUser(usuario);
			adoption.setOwner(owner);
			
			Pet pet = petService.findPetById(adoption.getPet().getId());
			adoption.setPet(pet);
			
			List<Requirement> lsRequirement = new ArrayList<Requirement>();
			adoption.setRequirement(lsRequirement);

			adoption.setAdoptado(false);
			this.adoptionService.saveAdoption(adoption);
			return "redirect:/adoptions";
		}
	}
	
	@GetMapping(value = { "/adoptions/details/{adoptionId}/delete" })
    public String deleteAdoption(@PathVariable("adoptionId") int adoptionId) {
    	this.adoptionService.removeAdoption(adoptionId);
    	return "redirect:/adoptions";
    }
	@PostMapping(value = "adoptions/details/{adoptionId}/requirement/new")
	public String procesCreationForm( @PathVariable("adoptionId") int adoptionId, @RequestParam("text") String text , ModelMap model) {
		Requirement requirement = new Requirement();
		User usuario = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Owner owner = ownerService.findOwnerByUser(usuario);
		
		requirement.setText(text);
		requirement.setAdoption(adoptionService.findAdoptionById(adoptionId).get());
		
		requirement.setNew_owner(owner);
		
		this.requirementService.saveRequirement(requirement);
		return "redirect:/adoptions";
	}
	
	@GetMapping(value = { "adoptions/details/{adoptionId}/requirement/{requirementId}/acept" })
    public String aceptRequirement(@PathVariable("requirementId") int requirementId, @PathVariable("adoptionId") int adoptionId) {
		
		
		Owner newOwner = requirementService.findRequirementById(requirementId).get().getNew_owner();
		Pet pet = adoptionService.findAdoptionById(adoptionId).get().getPet();
    	this.petService.changeOwner(newOwner, pet);
    	Adoption adoption = adoptionService.findAdoptionById(adoptionId).get();
    	adoptionService.delete(adoption);
    	
    	return "redirect:/adoptions";
    }


}
