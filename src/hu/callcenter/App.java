package hu.callcenter;

import java.util.Scanner;

import hu.callcenter.controller.CallCenter;
import hu.callcenter.model.service.Console;
import hu.callcenter.model.service.DataReader;
import hu.callcenter.model.service.DataWriter;

public class App {

	private final CallCenter callCenter;
	private final Console console;
	private final DataWriter dataWriter;

	public App() {
		console = new Console(new Scanner(System.in));
		DataReader data = new DataReader();
		callCenter = new CallCenter(data.getCalls("hivas.txt"));
		dataWriter = new DataWriter("sikeres.txt");
	}

	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		System.out.println("3. feladat: \r\n" + callCenter.getCallStatisticByHour());
		System.out.println("4. feladat: " + callCenter.getLongesCallDetail());
		String text = console.read("5. feladat: Adjon meg egy időpontot: ");
		System.out.println(callCenter.getActiveCallsDetail(text));
		System.out.println("6. feladat: " + callCenter.getLastSuccessCallDetail());
		dataWriter.print(callCenter.getSuccessCalls());
	}

}
