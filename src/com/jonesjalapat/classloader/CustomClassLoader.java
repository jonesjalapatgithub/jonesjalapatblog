package com.jonesjalapat.classloader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
	
	public CustomClassLoader(ClassLoader classLoader) {
		super(classLoader);
	}
	
	/*
	 * 
	 * We are going to load Class from .class file
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
	 private Class<?> getClass(String name) throws ClassNotFoundException {
	        String file = name.replace('.', File.separatorChar) + ".class";
	        byte[] byteArr = null;
	        try {
	            // This loads the byte code data from the file
	        	byteArr = loadClassFileData(file);
	            // defineClass is inherited from the ClassLoader class
	            // that converts byte array into a Class. defineClass is Final
	            // so we cannot override it
	            Class<?> c = defineClass(name, byteArr, 0, byteArr.length);
	            resolveClass(c);
	            return c;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	    @Override
	    public Class<?> loadClass(String name) throws ClassNotFoundException {
	    	Class<?> clazz = null;
            System.out.println("Started Loading class using "  +  " name: " + name);
	        if (name.startsWith("com.jonesjalapat.classloader")) {
	            clazz =  getClass(name);
	            System.out.println("Loaded class using " + clazz.getClassLoader() +  " name: " + name);
	            return clazz;
	        } else {
	            clazz = super.loadClass(name);
	            System.out.println("Loaded class using " + clazz.getClassLoader() +  " name: " + name);
		        return clazz;
	        }
	    }
	    
	    private byte[] loadClassFileData(String name) throws IOException {
	        InputStream stream = getClass().getClassLoader().getResourceAsStream(
	                name);
	        int size = stream.available();
	        byte buff[] = new byte[size];
	        DataInputStream in = new DataInputStream(stream);
	        in.readFully(buff);
	        in.close();
	        return buff;
	    }

}
