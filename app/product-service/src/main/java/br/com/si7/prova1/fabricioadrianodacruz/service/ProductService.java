package br.com.si7.prova1.fabricioadrianodacruz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.si7.prova1.fabricioadrianodacruz.dto.ProductDTO;
import br.com.si7.prova1.fabricioadrianodacruz.entities.ProductEntity;
import br.com.si7.prova1.fabricioadrianodacruz.repository.ProductRepository;
import br.com.si7.prova1.fabricioadrianodacruz.support.ObjectNotFoundException;
import br.com.si7.prova1.fabricioadrianodacruz.support.ProductException;

@Service
public class ProductService {

	private ProductRepository repo;

	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public List<ProductDTO> getAll() {
		return repo.findAll().stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}

	public ProductEntity findById(Long code) {
		Optional<ProductEntity> obj = repo.findById(code);
		ProductEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Product " + code + " not found"));
		return entity;
	}

	public void createProduct(ProductDTO product) {
		repo.save(toEntity(product));
	}

	public ProductEntity toEntity(ProductDTO productDTO) {
		return new ProductEntity(
				productDTO.getCode(), 
				productDTO.getName(), 
				productDTO.getDescription(),
				productDTO.getPrice(), 
				productDTO.getCategory(), 
				productDTO.isActive()
				);
	}

	public void updateProduct(ProductEntity product, Long code) {
		if (code == null || product == null || !code.equals(product.getCode()))
			throw new ProductException("Invalid product code!");

		ProductEntity existingObj = findById(code);
		updateData(existingObj, product);
		repo.save(existingObj);
	}

	private void updateData(ProductEntity existingObj, ProductEntity obj) {
		existingObj.setActive(obj.isActive());
	}
}
