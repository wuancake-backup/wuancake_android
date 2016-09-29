package testCalculator;

public class TestCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("hello world!");
		String calText = "2*(2*(3+4))*2";
		Calculator cal = new Calculator(calText);
		cal.parseText();
		System.out.println(cal.getText());
	}

}
