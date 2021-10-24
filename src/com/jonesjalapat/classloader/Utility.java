package com.jonesjalapat.classloader;

import com.jonesjalapat.utils.Civic;

public class Utility extends Civic{

	public static void print() {
		System.out.println("Utility print method completed ");
		int a = 3;
		int c = a+ 5;
		int d = a + c;
		System.out.println("Utility print addition completed " + d);

		
	}
	
	public static void main() {
		System.out.println("Utility main method ivoked ");
		new Civic().model();
		System.out.println("=====Utility main method completed====");		
	}

}
