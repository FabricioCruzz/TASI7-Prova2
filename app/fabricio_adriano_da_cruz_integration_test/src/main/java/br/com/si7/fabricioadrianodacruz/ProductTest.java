package br.com.si7.fabricioadrianodacruz;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.si7.fabricioadrianodacruz.dto.ProductDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductTest {
	private ObjectMapper mapper = new ObjectMapper();
	private final String productURL = "http://localhost:8080/products";
	
	@Test
	public void testCreateProduct_withSuccess() {
		Long productCode = 100L;
		Response resp = createProductWithCode(productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testCreateProduct_ExistingProduct() {
		Long productCode = 600L;
		createProductWithCode(productCode);
		Response resp = createProductWithCode(productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void testCreateProduct_InvalidData() {
		Long productCode = 101L;
		Response resp = createProductWithoutName(productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void testGetProductByCode_withSuccess() throws JsonMappingException, JsonProcessingException {
		Long productCode = 110L;
		createProductWithCode(productCode);
		Response resp = RestAssured.get(productURL + "/" + productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_OK);
		String jsonBody = resp.getBody().asPrettyString();
		
		ProductDTO product = mapper.readValue(jsonBody, ProductDTO.class);
		assertEquals(productCode, product.getCode());
	}
	
	@Test
	public void testGetProductByCode_nonExistingProduct() {
		Long nonExistingProductCode = 999L;
		Response resp = RestAssured.get(productURL + "/" + nonExistingProductCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void testUpdateActive_withSuccess() throws JsonMappingException, JsonProcessingException {
		Long productCode = 800L;
		createProductWithCode(productCode);
		Response existingProduct = RestAssured.get(productURL + "/" + productCode);
		String jsonBody = existingProduct.getBody().asPrettyString();
		ProductDTO product = mapper.readValue(jsonBody, ProductDTO.class);
		product.setActive(false);
		
		Response resp = RestAssured.given().body(product).contentType(ContentType.JSON).put(productURL + "/" + productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
	}
	
	@Test
	public void testUpdateActive_nonExistingProduct() {
		Long productCode = 999L;
		ProductDTO nonExistingProduct = new ProductDTO(productCode, "prod" + productCode, "desc" + productCode, 0f, "cat" + productCode, true);
		Response resp = RestAssured.given().body(nonExistingProduct).contentType(ContentType.JSON).put(productURL + "/" + productCode);
		resp.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
	}

	private Response createProductWithCode(Long productCode) {
		ProductDTO product = new ProductDTO(productCode, "prod" + productCode, "desc" + productCode, 0f, "cat" + productCode, true);
		Response resp = RestAssured.given().body(product).contentType(ContentType.JSON).post(productURL);
		return resp;
	}
	
	private Response createProductWithoutName(Long productCode) {
		ProductDTO product = new ProductDTO(productCode, null, "desc01", 0f, "cat01", true);
		Response resp = RestAssured.given().body(product).contentType(ContentType.JSON).post(productURL);
		return resp;
	}
}
