package com.test.sparks.common;

import java.io.Serializable;

public class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3577516612665702755L;
	
	@Override
	public String toString() {
		return "Person [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	private String email;
	private String firstName;
	private String lastName;
	
	public Person() { }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	
	
}
