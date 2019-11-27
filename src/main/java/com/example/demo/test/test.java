package com.example.demo.test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.nio.CharBuffer;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.tools.doclets.internal.toolkit.resources.doclets;



public class test {
//	 @Test
//	    public void encoder() {
//	        String password = "123456";
//	        String enPassword= new BCryptPasswordEncoder().encode(password);
//	        System.out.println(enPassword);
//	    }
	
	public static void main(String[] args) {
		MyTherde myTherde=new MyTherde();
		Thread thread=new Thread(myTherde);
		Thread thread2=new Thread(myTherde);
		thread.start();
		thread2.start();
		
	}
}


class D{
	private D() {
		//throw new AssertionError();//断言异常
	}
	
	private static D d=null;
	public static D getD() {
		if (d==null) {
			d=new D();
		}
		return d;
	}
}

class MyTherde implements Runnable{
	@Override
	public void run() {
		System.out.println(D.getD());
	}
}