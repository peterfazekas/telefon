package hu.callcenter.model.service;

import hu.callcenter.model.domain.Time;

public class TimeParser {

	public static Time parseTime(String text) {
		String[] items = text.split(" ");
		return parseTime(items[0], items[1], items[2]);
	}
	
	public static Time parseTime(String hour, String minure, String second) {
		return new Time(parseInt(hour), parseInt(minure), parseInt(second));
	}
	
	private static int parseInt(String text) {
		return Integer.parseInt(text);
	}
}
