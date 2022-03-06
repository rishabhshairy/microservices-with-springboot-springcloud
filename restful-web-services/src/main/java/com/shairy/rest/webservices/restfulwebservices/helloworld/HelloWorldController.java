package com.shairy.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping("/hello-world/path-var/{name}")
	public HelloWorldBean helloWorldPathBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world %s", name));
	}

	@GetMapping("/hello-world-i18n")
	public String helloWorldInternationalized(
			//@RequestHeader(name = "Accept-Language", required = false) Locale locale
			) 
			{
		logger.debug(messageSource.toString());
		return messageSource.getMessage("good.morning.text", null, "Defualt Message", LocaleContextHolder.getLocale());
	}
}
