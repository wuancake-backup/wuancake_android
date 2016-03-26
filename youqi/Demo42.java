public class Demo42 extends Demo41{
	private double salary;
	public Demo42(String name,String address,int number,double salary){
		super(name,address,number);
		setSalary(salary);
	}
	public void mailCheck(){
		System.out.println("Within mailCheck of Salary class ");
		System.out.println("ailing check to "+getName()+" with salary "+salary);
	}
	public double getSalary(){
		return salary;
	}
	public void setSalary(double newSalary){
		if(newSalary>=0.0){
			salary=newSalary;
		}
	}
	public double computePay(){
		System.out.println("Computing salary pay fpr "+getName());
		return salary/52;
	}
}
