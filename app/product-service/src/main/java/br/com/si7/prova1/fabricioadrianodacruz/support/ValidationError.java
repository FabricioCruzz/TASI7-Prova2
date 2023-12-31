package br.com.si7.prova1.fabricioadrianodacruz.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;

public class ValidationError extends StandardError {
	@Getter
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Date timestamp) {
		super(message, status, timestamp);
	}
	
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
}
