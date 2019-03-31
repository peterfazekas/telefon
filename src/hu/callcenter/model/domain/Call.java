package hu.callcenter.model.domain;

public class Call {

	private final int id;
	private final Time callBegin;
	private final Time talkBegin;
	private final Time callEnd;
	
	public Call(int id, Time callBegin, Time talkBegin, Time callEnd) {
		this.id = id;
		this.callBegin = callBegin;
		this.talkBegin = talkBegin;
		this.callEnd = callEnd;
	}

	public int getId() {
		return id;
	}

	public Time getCallBegin() {
		return callBegin;
	}

	public Time getTalkBegin() {
		return talkBegin;
	}

	public Time getCallEnd() {
		return callEnd;
	}

	public int getCallHour() {
		return callBegin.getHour();
	}
	
	public Integer getDuration() {
		return callBegin.getDuration(callEnd);
	}
	
	public boolean isActiveCall(Time time) {
		return callBegin.isEarlierOrEqual(time) && callEnd.isLaterOrEqual(time);
	}
	
	public boolean isSuccess() {
		return talkBegin != null;
	}
	
	public int getWaitingTime() {
		return isSuccess() ? callBegin.getDuration(talkBegin) : 0;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + talkBegin + callEnd;
	}
	
	
}
