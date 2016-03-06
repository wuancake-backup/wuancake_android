package com.god530.test20160227;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
	public static void main(String args[]) {
		//       // Instantiate a Date object
		//       Date date = new Date();
		//
		//       
		//       // display time and date using toString()
		//       System.out.println(date.toString());


		Date dNow = new Date( );
		SimpleDateFormat ft = 
				new SimpleDateFormat ("yy/MM/dd HH:mm:ss");

		System.out.println(ft.format(dNow));
	}
}
