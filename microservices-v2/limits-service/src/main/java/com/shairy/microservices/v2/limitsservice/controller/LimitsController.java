package com.shairy.microservices.v2.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shairy.microservices.v2.limitsservice.bean.Limits;
import com.shairy.microservices.v2.limitsservice.configuration.LimitServiceConfiguration;

@RestController
public class LimitsController {
	
	@Autowired
	private LimitServiceConfiguration config;
	
	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(config.getMinimum(), config.getMaximum());
	}
}
