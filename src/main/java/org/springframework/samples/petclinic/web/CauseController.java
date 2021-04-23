package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
    
    
	@GetMapping(value = { "/causes/{causeId}" })
	public String showCauseDetails(@PathVariable("causeId") int id, ModelMap model) {
		
		Cause cause = this.causeService.findCauseById(id);
		Collection<Donation> causeDonations = this.causeService.findDonations(id);
		model.put("donations", causeDonations);
		model.put("cause", cause);
		
		return "causes/causeDetails";
	}

}
