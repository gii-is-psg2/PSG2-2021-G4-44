

package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class CauseController {
	
	private final CauseService causeService;
	private final DonationService donationService;
	
	@Autowired
	public CauseController(CauseService causeService, DonationService donationService) {
		this.causeService = causeService;
		this.donationService = donationService;
	}
	
	@GetMapping(value = { "/causes" })
	public String showVetList(Cause cause, BindingResult result, Map<String, Object> model) {
		Collection<Cause> results = this.causeService.findCauses();
		model.put("causes", results);
		return "causes/causesList";
	}

	

	@GetMapping()
	public String listCauses(ModelMap model) {
		Collection<Cause> causes = causeService.findCauses();
		model.addAttribute("causes",causes);
		return "causes/causesList";
	}
	
	@GetMapping(value = "/causes/{id}/donations/new")
    public String initCreationForm(@PathVariable("id") int id,ModelMap model) {
    	Cause causa = causeService.findById(id).get();
    	if (causa.isClosed()){
    		model.addAttribute("message","Ya se ha recaudado lo necesario para esta causa por lo que no son necesarias más donaciones");
    		return listCauses(model);
    	} 
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "/donations/createDonationForm";
    }

    @PostMapping(value = "/causes/{id}/donations/new")
    public String processCreationForm(@PathVariable("id") int id,@Valid Donation donation, BindingResult result,ModelMap model) {
        if (result.hasErrors()) {
            return "/donations/createDonationForm";
        }
        Cause cause = causeService.findById(id).get();
        if (donation.getAmount() > (cause.getBudgetTarget()-cause.getBudgetAchieved())) {
        	model.addAttribute("message","La cantidad que desea donar es mayor que el objetivo de la donación");
    		return "/donations/createDonationForm";
        }
        cause.setBudgetAchieved(cause.getBudgetAchieved()+donation.getAmount());
        causeService.saveCause(cause);
        donation.setClient(SecurityContextHolder.getContext().getAuthentication().getName());
        donation.setCause(cause);
        donation.setDate(LocalDate.now());
        donationService.saveDonation(donation);
        return "redirect:/causes/"; 
    }
    
    @GetMapping("/causes/{id}/donations")
   	public String showDonation(@PathVariable("id") int id, ModelMap model) {
   		List<Donation> donations = donationService.findDonationsByCause(id);
   		model.addAttribute("donations",donations);
   		return "/donations/showDonations";
   	}
    
    
	@GetMapping(value = { "/causes/{causeId}" })
	public String showCauseDetails(@PathVariable("causeId") int id, ModelMap model) {
		
		Cause cause = this.causeService.findCauseById(id);
		Collection<Donation> causeDonations = this.causeService.findDonations(id);
		model.put("donations", causeDonations);
		model.put("cause", cause);
		
		
		return "causes/causeDetails";
	}


}

