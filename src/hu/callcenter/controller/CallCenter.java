package hu.callcenter.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import hu.callcenter.model.domain.Call;
import hu.callcenter.model.domain.Time;
import hu.callcenter.model.service.TimeParser;

public class CallCenter {

	private final List<Call> calls;

	public CallCenter(List<Call> calls) {
		this.calls = calls;
	}
	
	public String getCallStatisticByHour() {
		return getCallMapByHour().entrySet().stream()
				.map(i -> i.getKey() + " óra " + i.getValue() + " hívás")
				.collect(Collectors.joining("\r\n"));
	}
	
	private Map<Integer, Long> getCallMapByHour() {
		return calls.stream()
				.collect(Collectors.groupingBy(Call::getCallHour, () -> new TreeMap<>(), Collectors.counting()));
	}
	
	
	public String getLongesCallDetail() {
		Call longestCall = getLongestCall();
		return String.format("A leghosszabb ideig vonalban lévő hívó a %d. sorban szerepel, a hívás hossza: %d másodperc.", 
				longestCall.getId(), longestCall.getDuration());
	}
	
	private Call getLongestCall() {
		return calls.stream()
				.sorted(Comparator.comparingInt(Call::getDuration).reversed())
				.findFirst()
				.get();
	}
	

	public String getActiveCallsDetail(String text) {
		Time time = TimeParser.parseTime(text);
		List<Call> activeCalls = getActiveCalls(time);
		String answer;
		if (activeCalls.size() > 0) {
			answer = String.format("A várakozók száma: %d, a beszélő a %d. hívó.", 
					activeCalls.size() - 1, activeCalls.get(0).getId());
		} else {
			answer = "Nem volt beszélő.";
		}
		return answer;
	}
	
	private List<Call> getActiveCalls(Time time) {
		return calls.stream()
				.filter(i -> i.isActiveCall(time))
				.collect(Collectors.toList());
	}
	
	
	public String getLastSuccessCallDetail() {
		Call lastCall = getLastSuccessCall();
		return String.format("Az utolsó telefonáló adatai a(z) %d. sorban vannak, %d másodpercig várt.", 
				lastCall.getId(), lastCall.getWaitingTime());
	}
	

	private Call getLastSuccessCall() {
		return calls.stream()
				.filter(Call::isSuccess)
				.sorted(Comparator.comparingInt(Call::getId).reversed())
				.findFirst()
				.get();
	}
	
	public List<String> getSuccessCalls() {
		return calls.stream()
				.filter(Call::isSuccess)
				.map(Call::toString)
				.collect(Collectors.toList());
	}
}
