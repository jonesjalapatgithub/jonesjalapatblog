package com.jones.izo809;

public class TryWithResourcesExample {
	
	public static void main(String[] args) {
		try (One one = new One(); Two two = new Two()) {
			System.out.println("Try");
			throw new RuntimeException();
		} catch (Exception exception) {
			System.out.println("Catch");
		} finally {
			System.out.println("finally");
		}
	}

}


class One implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println(" Close One");
	}

}

class Two implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println(" Close Two");
	}

}