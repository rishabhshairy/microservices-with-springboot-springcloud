/**
 * 
 */
package com.shairy.microservices.v2.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shairy.microservices.v2.currencyexchangeservice.model.CurrencyExchange;
import com.shairy.microservices.v2.currencyexchangeservice.service.ExchangeService;

/**
 * @author rishabhshairy
 *
 */

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ExchangeService service;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange exchangeValues(@PathVariable String from, @PathVariable String to) {

		logger.info("Retrieve exchange values from {} to {}", from, to);
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to,
				service.getCurrencyExchange(from, to).getConversionMultiple());
		currencyExchange.setEnvironment(env.getProperty("local.server.port"));
		return currencyExchange;
	}

}
