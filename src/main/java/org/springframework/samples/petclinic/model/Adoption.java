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
 * 
 */
package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model entity representing an adoption
 *
 * @author David Brincau
 */
@Entity
@Table(name = "adoptions")
public class Adoption extends BaseEntity {

	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "adoptado")
	private Boolean adoptado;

	@OneToOne
	@JoinColumn(name = "pet")
	private Pet pet;

	@OneToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@OneToMany(mappedBy="adoption", cascade = CascadeType.REMOVE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<Requirement> requirement;
	
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getAdoptado() {
		return adoptado;
	}

	public void setAdoptado(Boolean adoptado) {
		this.adoptado = adoptado;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Requirement> getRequirement() {
		return requirement;
	}

	public void setRequirement(List<Requirement> requirement) {
		this.requirement = requirement;
	}



}
