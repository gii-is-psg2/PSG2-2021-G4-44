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
	private LocalDate fechaEntrada;
	
	@Column(name = "fecha_salida")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaSalida;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	public LocalDate getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}