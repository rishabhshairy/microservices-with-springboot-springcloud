package com.shairy.rest.webservices.restfulwebservices.apiVersionExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	/**
	 * URI Versioning
	 * 
	 * @return
	 */

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Jack");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Jack", "Holmes"));
	}

	/***
	 * Param Versioning
	 */

	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Jack");
	}

	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Jack", "Holmes"));
	}

	/***
	 * Header versioning
	 */

	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Jack");
	}

	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Jack", "Holmes"));
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.rshairy+json")
	public PersonV1 producesV1() {
		return new PersonV1("Jack");
	}

	@GetMapping(value = "person/produces", produces = "application/vnd.rshairy+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Jack", "Holmes"));
	}

}
