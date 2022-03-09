package com.shairy.rest.webservices.restfulwebservices.filteringExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringExController {
	
	@GetMapping("/filtering")
	public SampleFilterBean getBean() {
		return new SampleFilterBean("val1", "val2", "val3");
	}
}
