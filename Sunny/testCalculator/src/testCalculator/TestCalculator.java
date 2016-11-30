package testCalculator;

public class TestCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("hello world!");
//		String calText = "2*(2*(3+4))*2";
//		String calText = "()";
		String calText = "123";
		Calculator cal = new Calculator(calText);
		
		if(cal.parseText()){
			System.out.println(cal.getText());
		}else{
			System.out.println("input error!");
		}
		
	}

}
