package com.jones.izo809;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

class A {
	
	 static int count = 1;
	public   int scream() {
		return 2;
	}
	
	static {
		System.out.println(" From Static Block");
	}
}

public class ClassAndInterface  {
	
	public  int scream() throws Exception {
		assert(A.count > 1): "Assert is success";
		try {
			int a =1;
			if (a==1) {
				//throw new SQLException();
			}
			throw new IOException();

			
		} catch (Exception  e) {
			e = new FileNotFoundException();
			throw e;
			// TODO: handle exception
		}
	}
	public static void main(String[] args) throws Exception {
		Optional<String> baba = Optional.empty();
		System.out.println(" My Block");
		System.out.println(baba.orElse("bauahhaah" + new ClassAndInterface().scream()));
		System.out.println(baba.orElse("brrr" + new ClassAndInterface().scream()));


	}
}
