package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity{
	
	@NotBlank
	private Double amount; //la cantidad que se dona
	
	@Column(name = "donation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate donationDate; //la idea es, cuando se creen, que se hagan con la fecha LocalDate.now()
	
	@NotEmpty
	private String client; //nombre del cliente que hace la donación, más asequible hacerlo con String que con User
	
	
	@ManyToOne
	@JoinColumn(name= "cause_id") ///Cause a la que pertenece la 
	private Cause cause;
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(LocalDate donationDate) {
		this.donationDate = donationDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Cause getCause() {
        return this.cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

}
