package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity{
	
	@NotBlank
	private Double amount; //la cantidad que se dona
	
	private Date donationDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner client; //el cliente que hace la donación
	
	
	@ManyToOne
	@JoinColumn(name= "cause_id") ///Cause a la que pertenece la 
	private Cause cause;
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public Owner getClient() {
		return client;
	}

	public void setClient(Owner client) {
		this.client = client;
	}

	public Cause getCause() {
        return this.cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

}
