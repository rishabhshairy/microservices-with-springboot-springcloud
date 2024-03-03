package com.shairy.mockito.basics.listtest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void simple_test() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
	}

	@Test
	void multiple_return_test() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		// assertEquals(1, listMock.size()); // this statement will fail bcoz returns
		// are subsequent and default return is 2
	}

	@Test
	void specific_params_return_test() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("testData");
		assertEquals("testData", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void generic_params_return_test() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("testData");
		assertEquals("testData", listMock.get(0));
		assertEquals("testData", listMock.get(1));
	}

}
