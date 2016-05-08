import java.util.Scanner;
public class pronounce_integer {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		int integer = in.nextInt();
		int ab_value=Math.abs(integer);
		int ge = 0 , shi = 0, bai = 0 ,qian = 0 ,wan = 0;
		String GE="",SHI="",BAI="",QIAN="",WAN="";
		String outprint;
		wan = ab_value/10000;
		if(ab_value>9999)
			qian = ab_value%10000/1000;
		else
			qian = ab_value/1000;
		if(ab_value>999)
			bai = ab_value%1000/100;
		else
			bai = ab_value/100;
		if(ab_value>99)
			shi = ab_value%100/10;
		else
			shi = ab_value/10;
		ge = ab_value%10;

//		if(integer>=0)
//			System.out.println(wan+" "+qian+" "+bai+" "+shi+" "+ge);
//		else System.out.println("fu " + wan+" "+qian+" "+bai+" "+shi+" "+ge);
		switch (ge){
		case 0 :GE = "ling";break;
		case 1 :GE = "yi";break;
		case 2 :GE = "er";break;
		case 3 :GE = "san";break;
		case 4 :GE = "si";break;
		case 5 :GE = "wu";break;
		case 6 :GE = "liu";break;
		case 7 :GE = "qi";break;
		case 8 :GE = "ba";break;
		case 9 :GE = "jiu";break;
		}
		switch (shi){
		case 0 :SHI = "ling";break;
		case 1 :SHI = "yi";break;
		case 2 :SHI = "er";break;
		case 3 :SHI = "san";break;
		case 4 :SHI = "si";break;
		case 5 :SHI = "wu";break;
		case 6 :SHI = "liu";break;
		case 7 :SHI = "qi";break;
		case 8 :SHI = "ba";break;
		case 9 :SHI = "jiu";break;
		}switch (bai){
		case 0 :BAI = "ling";break;
		case 1 :BAI = "yi";break;
		case 2 :BAI = "er";break;
		case 3 :BAI = "san";break;
		case 4 :BAI = "si";break;
		case 5 :BAI = "wu";break;
		case 6 :BAI = "liu";break;
		case 7 :BAI = "qi";break;
		case 8 :BAI = "ba";break;
		case 9 :BAI = "jiu";break;
		}switch (qian){
		case 0 :QIAN = "ling";break;
		case 1 :QIAN = "yi";break;
		case 2 :QIAN = "er";break;
		case 3 :QIAN = "san";break;
		case 4 :QIAN = "si";break;
		case 5 :QIAN = "wu";break;
		case 6 :QIAN = "liu";break;
		case 7 :QIAN = "qi";break;
		case 8 :QIAN = "ba";break;
		case 9 :QIAN = "jiu";break;
		}switch (wan){
		case 0 :WAN = "ling";break;
		case 1 :WAN = "yi";break;
		case 2 :WAN = "er";break;
		case 3 :WAN = "san";break;
		case 4 :WAN = "si";break;
		case 5 :WAN = "wu";break;
		case 6 :WAN = "liu";break;
		case 7 :WAN = "qi";break;
		case 8 :WAN = "ba";break;
		case 9 :WAN = "jiu";break;
		}
		if(wan==0){
			if(qian==0){
				if(bai==0){
					if(shi==0)
						outprint = GE;
					else outprint = SHI +" " + GE;
				}
				else outprint = BAI +" " + SHI +" " + GE ;
			}
			else outprint = QIAN +" " + BAI +" " + SHI +" " + GE ;
		}
		else outprint =  WAN +" " + QIAN +" " + BAI +" " + SHI +" " + GE ;
		if (integer < 0)
			outprint = "fu " + outprint ;
		System.out.println(outprint);
	}
	

}
