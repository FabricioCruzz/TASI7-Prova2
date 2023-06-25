package br.com.si7.prova1.fabricioadrianodacruz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.si7.prova1.fabricioadrianodacruz.dto.ProductDTO;
import br.com.si7.prova1.fabricioadrianodacruz.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long code){
		ProductDTO productDTO = new ProductDTO(service.findById(code));
		return ResponseEntity.ok(productDTO);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductDTO product) {
		service.createProduct(product);
	}
	
	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long code) {
		service.updateProduct(service.toEntity(productDTO), code);
	}
}
