package practise;

import java.util.Date;

public class DateDemo2 {
	public static void main(String args[]){
		Date date = new Date();
		String str = String.format("Current Date/Time:%tc", date);
		System.out.println(str);
	}
}
