package com.shairy.microservices.v2.apigatewayservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 
 * @author rishabhshairy
 *
 */
@Component
public class LoggingFilter implements GlobalFilter{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Request Path Received:: {}",exchange.getRequest().getURI());
		return chain.filter(exchange);
	}

}
