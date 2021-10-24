package com.jones.designPattern;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum TestEnumSingleton {
	INSTANCE;
	
	private Set<String> availableSeats;
	
	private TestEnumSingleton() {
		availableSeats = new HashSet<String>();
		availableSeats.add("1");
		availableSeats.add("2");
		availableSeats.add("3");
	}
	
	public boolean bookSeat(String seat) {
		return availableSeats.remove(seat);
	}
	
	static void call() {
		TestEnumSingleton es1 = TestEnumSingleton.INSTANCE;
		System.out.println("EnumSingleton from Multithread " + es1.hashCode());
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(TestEnumSingleton::call);
		service.submit(TestEnumSingleton::call);
		TestEnumSingleton es2 = TestEnumSingleton.INSTANCE;
		System.out.println("EnumSingleton " + es2.hashCode() + " booked success :" + es2.bookSeat("1"));
	}
}


