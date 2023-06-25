package br.com.si7.prova1.fabricioadrianodacruz.support;

public class ProductException extends RuntimeException {
	public ProductException(String message) {
        super(message);
    }
	
	public ProductException(String message, Exception e) {
		super(message, e);
	}
}
