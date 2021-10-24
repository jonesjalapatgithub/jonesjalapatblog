package com.jonesjalapat.classloader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Test {
	
	int a = 10;
	String string1 = "abc";
	List<String> list1 = new ArrayList<>();

	
	public static void main(String[] args) {
		
		
		BigDecimal bigDecimal1 = BigDecimal.valueOf(151.7700);
		BigDecimal bigDecimal2 = BigDecimal.valueOf(151.77);
		
		if (bigDecimal1.doubleValue() == bigDecimal2.doubleValue()) {
			System.out.println(" bigDecimal1 ");
		}

		
		Map<String, String> map = new HashMap<>();
		Optional<String> a = Optional.ofNullable(map.get(null));
		
		
		int b = 0 ;
		Test testObject = new Test();
		String string2 = "abc";
		
		testObject.printList1(string2);

		List<Integer> list2 = new ArrayList<>();
		list2.add(testObject.a);
		list2.add(b);
		list2.stream().forEach(System.out::println);
	}

	private  void printList1(String string2) {
		list1.add(string2);
	    list1.add(string1);
		list1.stream().forEach(System.out::println);		
	}

}
