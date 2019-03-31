package hu.callcenter.model.service;

import hu.callcenter.model.domain.Time;

public class TimeService {

	public static boolean isEarlier(Time time1, Time time2) {
		return toSecond(time1) < toSecond(time2);
	}
	
	public static boolean isLater(Time time1, Time time2) {
		return toSecond(time1) > toSecond(time2);
	}

	public static boolean isEarlierOrEqual(Time time1, Time time2) {
		return toSecond(time1) <= toSecond(time2);
	}
	
	public static boolean isLaterOrEqual(Time time1, Time time2) {
		return toSecond(time1) >= toSecond(time2);
	}

	public static int getDuration(Time time1, Time time2) {
		return Math.abs(toSecond(time1) - toSecond(time2));
	}
	
	public static int toSecond(Time time) {
		return mpbe(time.getHour(), time.getMinute(), time.getSecond());
	}

	public static int toSecond(int hour, int minute, int second) {
		return mpbe(hour, minute, second);
	}
	
	private static int mpbe(int o, int p, int mp) {
		return o * 3600 + p * 60 + mp;
	}
}
