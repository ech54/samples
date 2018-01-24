package org.ech.samples.netflix;

import java.time.LocalDate;

/**
 * @author Emilien Charton
 * @date 24 janv. 2018
 */
public class UserDetails {
	
	private Long id;
	private String lastName;
	private String firstName;
	private LocalDate birthDate;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	} 
	
	
	
}
