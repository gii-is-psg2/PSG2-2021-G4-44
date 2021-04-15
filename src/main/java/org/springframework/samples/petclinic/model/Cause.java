package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
	
	@NotNull
	@Column(name = "budged_target")   //budget target (numeric)
	@Min(0)							  //como mínino será 0
	private Double budgetTarget;
	
	@NotBlank
	@Column(name = "organization")	  //an active non profit organization (string) 
	private String organization;
	
	@NotNull
	@Column(name = "is_closed")
	private Boolean isClosed;

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

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	

}
