package com.shairy.microservices.v2.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shairy.microservices.v2.currencyconversionservice.config.CurrencyExchangeProxy;
import com.shairy.microservices.v2.currencyconversionservice.model.CurrencyConversion;

/**
 * 
 * @author rishabhshairy
 *
 */
@RestController
public class CurrencyConversionController {

	@Autowired
	CurrencyExchangeProxy proxy;

	@Autowired
	private Environment env;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getExchangedValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion = proxy.getExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getFrom(),
				currencyConversion.getTo(), quantity, currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + ":feign");
	}
}
