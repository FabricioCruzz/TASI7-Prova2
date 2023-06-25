package br.com.si7.fabricioadrianodacruz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long code;
	private String name;
	private String description;
	private float price;
	private String category;
	private boolean active;
}
