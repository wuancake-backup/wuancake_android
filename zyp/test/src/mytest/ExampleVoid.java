package mytest;

public class ExampleVoid {

	public static void main(String[] args) {
		mathodRankPoints(255.7);

	}
	
	public static void mathodRankPoints(double points){
		if(points >= 202.5){
		System.out.println("Rank:A1");	
		}
		else if (points >= 122.4){
			System.out.println("Rank :A2");
		}
		else{
			System.out.println("Rank:A3");
		}
	}

}
