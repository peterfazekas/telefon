package hu.callcenter.model.domain;

import hu.callcenter.model.service.TimeService;

public class Time {

	private final int hour;
	private final int minute;
	private final int second;

	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}
	
	public int getDuration(Time otherTime) {
		return TimeService.getDuration(this, otherTime);
	}
	
	public boolean isEarlier(Time otherTime) {
		return TimeService.isEarlier(this, otherTime);
	}

	public boolean isEarlierOrEqual(Time otherTime) {
		return TimeService.isEarlierOrEqual(this, otherTime);
	}
	
	public boolean isLater(Time otherTime) {
		return TimeService.isLater(this, otherTime);
	}

	public boolean isLaterOrEqual(Time otherTime) {
		return TimeService.isLaterOrEqual(this, otherTime);
	}

	@Override
	public String toString() {
		return " " + hour + " " + minute + " " + second;
	}
	
	
}
