package com.jones.designPattern;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LazySingleton implements Cloneable{

	private static volatile LazySingleton lazySingleton;
	
	private LazySingleton() {
		System.out.println(" Creating EagerSingleton ....");
	}
	
	//Defend against multiple threads
	/*
	 * Reasons why we used Double checked pattern
	 * Synchronization is needed since multiple threads create problem without it.
	 * Synchronized methods are not good since there is a performance degradation of factor of 100 or higher,
	 * Henceforth a double checked pattern used to do synchronization only at objet creation.
	 * Double checked has a problem of partially constructed object, henceforth we use a volatile variable approach
	 * Direct ref to volatile variable gives a performance hit due to not using register level caches, hence 
	 * a local ref is returned which gives us atleast 40% performance boost.
	 * There are other approaches as-well like using varHandle of java 9, but for now this is enough.
	 */
	public static LazySingleton getInstance() {
		 LazySingleton lazySingletonRef = lazySingleton;
		 if(lazySingletonRef == null) {
			 synchronized(LazySingleton.class) {
				 lazySingletonRef = lazySingleton;
				 if (lazySingletonRef == null) {
					 lazySingletonRef = lazySingleton = new LazySingleton();
				 }
			 }
		 }
		 return lazySingletonRef;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	} 
	
	
}


public class TestLazySingleton {
	
	static void call() {
		LazySingleton lzs4 = LazySingleton.getInstance();
		System.out.println("LazySingleton from Multithread " + lzs4.hashCode());
	}
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		LazySingleton lzs1 = LazySingleton.getInstance();
		LazySingleton lzs2 = LazySingleton.getInstance();
		System.out.println("LazySingleton1 " + lzs1.hashCode());
		System.out.println("LazySingleton2 " + lzs2.hashCode());
		
		// Break Singleton using Cloning
		createObjectUsingCloning(lzs2);
		
		// Trying to break Singleton using 
		createObjectUsingMultipleThreads(lzs2);		
	}

	// Not broken since we use a double checked pattern
	private static void createObjectUsingMultipleThreads(LazySingleton lzs2) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(TestLazySingleton::call);
		service.submit(TestLazySingleton::call);
		service.shutdown();
	}

	private static void createObjectUsingCloning(LazySingleton lzs2) throws CloneNotSupportedException {
		LazySingleton lzs3 = (LazySingleton) lzs2.clone();
		System.out.println("LazySingleton from Reflection " + lzs3.hashCode());
	}

}
