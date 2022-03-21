package com.shairy.microservices.v2.limitsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shairy.microservices.v2.limitsservice.bean.Limits;

@RestController
public class LimitsController {

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(1, 1000);
	}
}
