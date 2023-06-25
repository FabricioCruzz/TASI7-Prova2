package br.com.si7.prova1.fabricioadrianodacruz.support;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {
	private String message;
	private Integer status;
	private Date date;
}
