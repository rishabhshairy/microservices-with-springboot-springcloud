package com.shairy.mockito.basics;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shairy.mockito.basics.service.BusinessImpl;
import com.shairy.mockito.basics.service.DataService;

@ExtendWith(MockitoExtension.class)
class BusinessImplMockTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private BusinessImpl businessImpl;

	@Test
	void findGreatest_basic() {

		// when
		when(dataServiceMock.getAllData()).thenReturn(new int[] { 100, 25, 5 });
		// then
		assertEquals(100, businessImpl.findGreatest());
	}

	@Test
	void findGreatest_SingleArray() {
		// when
		when(dataServiceMock.getAllData()).thenReturn(new int[] { 100 });
		// then
		assertEquals(100, businessImpl.findGreatest());
	}

	@Test
	void findGreatest_EmptyArray() {
		// when
		when(dataServiceMock.getAllData()).thenReturn(new int[] {});
		// then
		assertEquals(Integer.MIN_VALUE, businessImpl.findGreatest());
	}

}

/*
 * // this is one way of mocking data
 * 
 * @Test void findGreatest_basic() { DataService dataServiceMock =
 * mock(DataService.class); // when
 * when(dataServiceMock.getAllData()).thenReturn(new int[] {100, 25, 5});
 * 
 * BusinessImpl businessImpl = new BusinessImpl(dataServiceMock);
 * assertEquals(100, businessImpl.findGreatest()); }
 */
