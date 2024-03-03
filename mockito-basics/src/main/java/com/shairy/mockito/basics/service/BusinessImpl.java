package com.shairy.mockito.basics.service;

/**
 * 
 * @author rishabhshairy
 *
 */
public class BusinessImpl {
	private DataService dataService;

	public BusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findGreatest() {
		int[] data = dataService.getAllData();
		int greatestValue = Integer.MIN_VALUE;
		for (int value : data) {
			if (value > greatestValue) {
				greatestValue = value;
			}
		}
		return greatestValue;
	}
}
