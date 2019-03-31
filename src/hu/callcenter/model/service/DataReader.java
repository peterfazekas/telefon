package hu.callcenter.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.callcenter.model.domain.Call;
import hu.callcenter.model.domain.Time;

public class DataReader {

	private static final Time WORK_BEGIN = new Time(8, 0, 0);
	private static final Time WORK_END = new Time(12, 0, 0);
	
	private int id;
	private Time lastTalkEnd = WORK_BEGIN;
	
	public List<Call> getCalls(String fileName) {
		return parse(read(fileName));
	}
	
	private List<Call> parse(List<String> lines) {
		return lines.stream().map(this::createCall).collect(Collectors.toList());
	}
	
	private Call createCall(String line) {
		String[] items = line.split(" ");
		Time callBegin = TimeParser.parseTime(items[0], items[1], items[2]);
		Time callEnd = TimeParser.parseTime(items[3], items[4], items[5]);
		Time talkBegin = null;
		if (callBegin.isEarlier(WORK_END) && callEnd.isLaterOrEqual(WORK_BEGIN) && callEnd.isLater(lastTalkEnd)) {
			talkBegin = lastTalkEnd.isEarlier(callBegin) ? callBegin : lastTalkEnd;
			lastTalkEnd = callEnd; 
		}
		return new Call(++id, callBegin, talkBegin, callEnd);
	}
	
	private List<String> read(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
