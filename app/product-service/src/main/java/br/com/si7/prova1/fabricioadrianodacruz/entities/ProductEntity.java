package br.com.si7.prova1.fabricioadrianodacruz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String name;
	private String description;
	private float price;
	private String category;
	private boolean active;
}
