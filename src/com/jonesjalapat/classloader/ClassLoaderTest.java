package com.jonesjalapat.classloader;

import java.lang.reflect.Method;


public class ClassLoaderTest {
	
    public static void main(String args[]) throws Exception {

        CustomClassLoader ccl = new CustomClassLoader(CustomClassLoader.class.getClassLoader());
        System.out.println("===== Inside Main ClassLoaderTest=====");
        Class<?> clazz = ccl.loadClass("com.jonesjalapat.classloader.Utility");        
        Object ob = clazz.getDeclaredConstructor().newInstance();
        Method md1 = clazz.getMethod("print");
        md1.invoke(ob);
        Method md2 = clazz.getMethod("main");
        md2.invoke(ob);
    }

}
