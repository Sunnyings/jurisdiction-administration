package com.example.demo.util;

import java.security.MessageDigest;

public class MD5Util {
	 private static final String SALT = "liuyanzhao.com";
	    /**
	     * MD5加盐加密
	     *
	     * @param password
	     * @return
	     */
	    public static String encode(String password) {
	        password = password + SALT;
	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        char[] charArray = password.toCharArray();
	        byte[] byteArray = new byte[charArray.length];
	        for (int i = 0; i < charArray.length; i++)
	            byteArray[i] = (byte) charArray[i];
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
	    }
	    public static void main(String[] args) {
	        String hashPass = MD5Util.encode("admin");
	        System.out.println(MD5Util.encode("admin"));//08dc78d36d1512e5a81ef05b01a37860
	        System.out.println("62218882bf00f03db674e83b4caec2b5".equals(hashPass));//true
	    }
}
