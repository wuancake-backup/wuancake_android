import java.util.Scanner;
public class prime_total {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		
		int[] prime=new int[200];
		int i=0;
		boolean isPrime =true;
			for(int j=2;;j++, isPrime=true){
				for(int k=2;k<j;k++){
					if(j%k==0){
						isPrime=false;
						break;
					}
				}
				if(isPrime==true){
					 prime[i]=j;
					 
					 i++;
				 }
				if(i==200) break;
			}
//			for(int l=0;l<200;l++){
//				System.out.println(prime[l]);	}
			int start = in.nextInt();
			int finish =in.nextInt();
			int sum=0;
			for( int m=start-1;m<=finish-1;m++){
				sum+=prime[m];
			}
			System.out.println(sum);
		}
	}

