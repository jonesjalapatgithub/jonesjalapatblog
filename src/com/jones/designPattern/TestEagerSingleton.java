package com.jones.designPattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

 class EagerSingleton implements Serializable{

	private static EagerSingleton eagerSingleton = new EagerSingleton();
	
	private EagerSingleton() {
		//defend against reflection
//		if(eagerSingleton != null) {
//			throw new RuntimeException("Constructor cannot be called directly");
//		}
		System.out.println(" Creating EagerSingleton ....");
	}
	
	public static EagerSingleton getInstance() {
		return eagerSingleton;
	}
	
	//defend against serialization
//	private Object readResolve() throws ObjectStreamException {
//		System.out.println("return the created Instance ");
//		return eagerSingleton;
//	}

	
}

public class TestEagerSingleton {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ClassNotFoundException {
		EagerSingleton egs1 = EagerSingleton.getInstance();
		EagerSingleton egs2 = EagerSingleton.getInstance();
		System.out.println("EagerSingleton1 " + egs1.hashCode());
		System.out.println("EagerSingleton2 " + egs2.hashCode());
		
		// Break Singleton using Reflection
		createObjectUsingReflection();
		// Break Singleton using Serialization
		createObjectUsingSerialization(egs2);

		
	}

	private static void createObjectUsingSerialization(EagerSingleton egs2) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("eg3.ser")); 
		oos.writeObject(egs2);
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("eg3.ser")); 
		EagerSingleton egs3 = (EagerSingleton)ois.readObject();
		//Tid Bit : In serialization Objects are called without invoking Constructor
		System.out.println("EagerSingleton from Serialization " + egs3.hashCode());
	}

	private static void createObjectUsingReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class clazz = Class.forName("com.jonesjalapat.classloader.EagerSingleton");
		Constructor<EagerSingleton> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		EagerSingleton eagerSingleton = constructor.newInstance();
		System.out.println("EagerSingleton from Reflection " + eagerSingleton.hashCode());
	}
	
}
