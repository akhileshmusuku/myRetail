package com.myretail.product.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class ProductInfoAPIDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoAPIDao.class);

	@Value("${productInfoURL}")
	private String productInfoURL;

	@Autowired
	private RestTemplate restTemplate;

	@CircuitBreaker(name = "getAPIResponse", fallbackMethod = "fallBackforAPIResponse")
	@Retry(name = "getAPIResponse")
	@Bulkhead(name = "getAPIResponse")
	public ResponseEntity<String> getAPIResponse(long id) {
		LOGGER.debug("calling URL :: {}", productInfoURL);

		ResponseEntity<String> response = restTemplate.getForEntity(productInfoURL, String.class);

		return response;
	}

	public ResponseEntity<String> fallBackforAPIResponse(long id, Throwable t) {
		LOGGER.error("Inside fallBackforAPIResponse, cause {}", t.toString());

		return new ResponseEntity<String>("Inside fallback method. unknown error occured while calling productname URL",
				HttpStatus.BAD_GATEWAY);

	}

}
