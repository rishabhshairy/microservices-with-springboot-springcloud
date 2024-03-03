package com.shairy.mockito.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.shairy.mockito.basics.service.BusinessImpl;
import com.shairy.mockito.basics.service.DataService;

class BusinessImplTest {

	@Test
	void findGreatest_basic() {
		DataService dataService  = new DataServiceStub();
		BusinessImpl businessImpl = new BusinessImpl(dataService);
		int result = businessImpl.findGreatest();
		assertEquals(100, result);
	}

}
