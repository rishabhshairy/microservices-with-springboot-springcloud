/**
 * 
 */
package com.shairy.microservices.v2.currencyexchangeservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shairy.microservices.v2.currencyexchangeservice.model.CurrencyExchange;
import com.shairy.microservices.v2.currencyexchangeservice.repository.CurrencyExchangeRepository;

/**
 * @author rishabhshairy
 *
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private CurrencyExchangeRepository repository;

	@Override
	public CurrencyExchange getCurrencyExchange(String from, String to) {
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " + to);
		}
		return currencyExchange;
	}

}
