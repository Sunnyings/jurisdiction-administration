package com.example.demo.util;

import java.util.Calendar;

public class GenerateCode {
	
	public static Long id_template = Calendar.getInstance().getTime().getTime() * 100000;

    public static Long genIdByIncrease(){
        id_template ++;
        return id_template;
    }
}
