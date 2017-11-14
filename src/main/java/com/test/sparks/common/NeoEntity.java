package com.test.sparks.common;

import java.io.Serializable;

public class NeoEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1549230096642420881L;
	
	private String name;
	private String workingLanguage;
	
	public NeoEntity() { }

	public NeoEntity(String name, String workingLanguage) {
		this.name = name;
		this.workingLanguage = workingLanguage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkingLanguage() {
		return workingLanguage;
	}

	public void setWorkingLanguage(String workingLanguage) {
		this.workingLanguage = workingLanguage;
	}	
	
}
