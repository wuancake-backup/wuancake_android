package test;

public class testfor {

	public static void main(String[] args) {
		int [] numbers = {10,20,30,40,50};
		for (int x :numbers){
			System.out.print(x);
			System.out.print(",");
		}
		System.out.print("\n");
		String []names ={"james","larry","tom","lacy"};
		for(String name:names){
			System.out.print(name);
			System.out.print(",");
		}

	}

}
