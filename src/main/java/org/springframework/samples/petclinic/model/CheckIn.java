package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Check in de una mascota.
 *
 * @author Victor
 */
@Entity
@Table(name = "chechkins")
public class CheckIn extends BaseEntity {

	@Column(name = "fecha_entrada")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dateE;
	
	@Column(name = "fecha_salida")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dateS;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	public LocalDate getDateEntrada() {
		return this.dateE;
	}

	public void setDateEntrada(LocalDate date) {
		this.dateE = date;
	}
	
	public LocalDate getDateSalida() {
		return this.dateS;
	}

	public void setDateSalida(LocalDate date) {
		this.dateS = date;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}