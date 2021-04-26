package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.sun.istack.NotNull;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity{
	
	
	@NotBlank
	@Column(name = "name")            //Nombre  (string)
	private String name;
	
	@NotBlank
	@Column(name = "description")	  //Descripción  (string)
	private String description;
	
	@Column(name="budget_achieved")			//budget_achieved (dinero acumulado)
	private Integer budgetAchieved;
	
	@NotNull
	@Column(name = "budget_target")   //budget target (numeric)
	@Min(0)							  //como mínino será 0
	private Double budgetTarget;
	
	
	@NotBlank
	@Column(name = "organization")	  //an active non profit organization (string) 
	private String organization;
	
	public Boolean isClosed() {
		if (this.getBudgetAchieved() >= this.getBudgetTarget()) {
			return true;
		}
	return false;
				
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cause")
	private Set<Donation> donations;
	
	protected Set<Donation> getDonationsInternal() {
	    if (this.donations == null) {
	       this.donations = new HashSet<>();
	    }
	    return this.donations;
	}

	public List<Donation> getDonations() {
	    List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
	    PropertyComparator.sort(sortedDonations, new MutableSortDefinition("date", false, false));
	    return Collections.unmodifiableList(sortedDonations);
	}

	public void addDonation(Donation donation) {
	    getDonationsInternal().add(donation);
	    donation.setCause(this);
	}
	
	public Double getBudgetAchieved() {
		Double res=0.0;
		for (Donation donation:getDonations()) {
			res+=donation.getAmount();
		}
		return res;
		
	}

}