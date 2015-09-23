package com.jhcm.rest.backend.validators;

public class PropertyValidation {
	private String property;
	private String message;

	public PropertyValidation(String property, String message) {
		super();
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
