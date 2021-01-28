package com.myretail.clientapplication.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myretail.clientapplication.model.Product;

@RestController
public class ClientController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${eurekaendpoint}")
	private String eurekaendpoint;
	
	@GetMapping(value = "/products/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Product> getProduct(@PathVariable long id) {

		LOGGER.debug("request id {}", id);
		LOGGER.debug("url ::: {}", eurekaendpoint);

		ResponseEntity<Product> response= restTemplate.getForEntity(eurekaendpoint+id, Product.class);
		LOGGER.debug("response payload {}", response);
		return response;

	}
	
	@PutMapping(value = "/products/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product request, @PathVariable long id) throws URISyntaxException {
		
		LOGGER.debug("request payload {}", request);
		String endpoint = eurekaendpoint+id;
		
		 RequestEntity<Product> reqEntity = RequestEntity
				 .put(new URI(endpoint))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(request);
		
		ResponseEntity<Product> response= restTemplate.exchange(reqEntity, Product.class);
		LOGGER.debug("response payload {}", response);
		return response;
		
	}
}
