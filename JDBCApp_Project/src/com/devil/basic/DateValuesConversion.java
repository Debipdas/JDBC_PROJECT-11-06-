package com.devil.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValuesConversion {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

	String s1="21-11-1990";
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	java.util.Date ud1=sdf.parse(s1);
	System.out.println("String date value ="+s1);
	System.out.println("util date= "+ud1);
			
	}

}
