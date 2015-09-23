package com.jhcm.rest.backend.validators;

import java.awt.TrayIcon.MessageType;
import java.util.List;

public class MessageDTO {
	private String message;
	private MessageType type;
	private List<PropertyValidation> validations;

	public List<PropertyValidation> getValidations() {
		return validations;
	}

	public void setValidations(List<PropertyValidation> validations) {
		this.validations = validations;
	}

	public MessageDTO() {
		super();
	}

	public MessageDTO(MessageType type, String message) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
}