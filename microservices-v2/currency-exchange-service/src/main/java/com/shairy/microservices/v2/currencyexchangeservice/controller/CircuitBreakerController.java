package com.shairy.microservices.v2.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/sample-retry-api")
	@Retry(name = "sample-api", fallbackMethod = "fallback") // default name will retry 3 times
	public String sampleRetryApi() {
		logger.info("Called Retry Sample API");
		ResponseEntity<String> forEntity = new RestTemplate()
				.getForEntity("http://localhost:8089/some-dummy-api-tofail", String.class);
		return forEntity.getBody();
	}

	@GetMapping("/sample-circuit-api")
	@CircuitBreaker(name = "default", fallbackMethod = "fallback") // default name will retry 3 times
	public String sampleCircuitApi() {
		logger.info("Called CB Sample API");
		ResponseEntity<String> forEntity = new RestTemplate()
				.getForEntity("http://localhost:8089/some-dummy-api-tofail", String.class);
		return forEntity.getBody();
	}

	@GetMapping("/sample-ratelimit-api")
//	@RateLimiter(name = "default", fallbackMethod = "fallback")
	@RateLimiter(name = "default")
	public String sampleRateLimitApi() {
		logger.info("Called Rate Limit Sample API");
		/*
		 * ResponseEntity<String> forEntity = new RestTemplate()
		 * .getForEntity("http://localhost:8089/some-dummy-api-tofail", String.class);
		 */
		return "sample-ratelimit-api";
	}
	
	@GetMapping("/sample-bulkhead-api")
	@Bulkhead(name = "default")
	public String sampleBulkHeadApi() {
		logger.info("Called Bulk Head Sample API");
		/*
		 * ResponseEntity<String> forEntity = new RestTemplate()
		 * .getForEntity("http://localhost:8089/some-dummy-api-tofail", String.class);
		 */
		return "sample-BulkHead-api";
	}

	public String fallback(Exception ex) {
		return "Failed Response";
	}
}
