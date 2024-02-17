package com.shairy.microservices.v2.currencyexchangeservice.service;

import java.math.BigDecimal;

import com.shairy.microservices.v2.currencyexchangeservice.model.CurrencyExchange;

public interface ExchangeService {
	CurrencyExchange getCurrencyExchange(String from,String to);
}
