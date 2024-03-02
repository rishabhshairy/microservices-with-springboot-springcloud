/**
 * 
 */
package com.shairy.microservices.v2.apigatewayservice.config;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rishabhshairy
 *
 */
@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder routeBuilder) {
		/**
		 * Below is basic routefunction for example
		 */
		/*
		 * return routeBuilder.routes().route(p -> p.path("/get").filters(f -> f
		 * .addRequestHeader("MyHeader",
		 * "testHeadValue").addRequestParameter("MyParameter", "testParamValue"))
		 * .uri("http://httpbin.org:80")).build();
		 */

		return routeBuilder.routes()
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion-new/**") 
								.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion/${segment}"))
								.uri("lb://currency-conversion"))
				.build();
		
		// third route is example of how we can rewrite paths
	}
}
