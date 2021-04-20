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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model entity representing an adoption
 *
 * @author David Brincau
 */
@Entity
@Table(name = "requirements")
public class Requirement extends BaseEntity {

	@Column(name = "text")
	private String text;

	@ManyToOne
	@JoinColumn(name = "new_owner_id")
	private Owner new_owner;

	@ManyToOne
	@JoinColumn(name="id_adoption")
	private Adoption adoption;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Owner getNew_owner() {
		return new_owner;
	}

	public void setNew_owner(Owner new_owner) {
		this.new_owner = new_owner;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public void setAdoption(Adoption adoption) {
		this.adoption = adoption;
	}
	
	

}
