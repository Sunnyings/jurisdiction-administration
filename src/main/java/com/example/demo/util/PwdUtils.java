package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdUtils {
	 public static String genUserPwd(String password) {
		 String enPassword= new BCryptPasswordEncoder().encode(password);
	        return enPassword;
	    }
}
