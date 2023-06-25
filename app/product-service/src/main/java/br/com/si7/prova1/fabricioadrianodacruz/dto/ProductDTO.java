package br.com.si7.prova1.fabricioadrianodacruz.dto;

import br.com.si7.prova1.fabricioadrianodacruz.entities.ProductEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Long code;
	private String name;
	private String description;
	private float price;
	private String category;
	private boolean active;

	public ProductDTO(ProductEntity product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.category = product.getCategory();
		this.active = product.isActive();
	}
}