package com.shairy.rest.webservices.restfulwebservices.filteringExample;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringExController {

	@GetMapping("/filtering")
	public SampleFilterBean getBean() {
		return new SampleFilterBean("val1", "val2", "val3");
	}

	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue getDynamicFilterBean() {
		DynamicFilterBean bean = new DynamicFilterBean("val1", "val2", "val3");
		String className = DynamicFilterBean.class.getAnnotation(JsonFilter.class).value();
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filterValueDynamic(className, "field1", "field3"));

		return mapping;
	}

	private FilterProvider filterValueDynamic(String filterName, String... values) {
		Set<String> filterValues = new HashSet<>();
		for (String val : values) {
			filterValues.add(val);
		}
		SimpleBeanPropertyFilter filter = new SimpleBeanPropertyFilter.FilterExceptFilter(filterValues);
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
		return filters;
	}
}
