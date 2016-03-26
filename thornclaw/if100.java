import java.util.Scanner;
public class if100 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int a=in.nextInt();
		int b=in.nextInt();
		int c=in.nextInt();
		if(a==b&&b==c){
			System.out.println("equal");
		}
		else{
			if(a+b+c>1000){
				System.out.println("sum>100");
			}
		else{
			System.out.println("sum<1000");
		}
		}
		in.close();
	
	}

}
