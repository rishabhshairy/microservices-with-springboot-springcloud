package com.shairy.mockito.basics;

import com.shairy.mockito.basics.service.DataService;

public class DataServiceStub implements DataService {

	@Override
	public int[] getAllData() {
		return new int[] { 50, 100, 1 };
	}

}
