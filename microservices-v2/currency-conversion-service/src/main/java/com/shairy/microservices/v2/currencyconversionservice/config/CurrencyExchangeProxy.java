package com.shairy.microservices.v2.currencyconversionservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shairy.microservices.v2.currencyconversionservice.model.CurrencyConversion;

//@FeignClient(name = "currency-exchange", url = "localhost:8000") // use this for static debug purpose
@FeignClient(name = "currency-exchange") // this is used for load balancing
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);
}
