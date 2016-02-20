import java.io.*;
public class Demo6 {
    public static void main(String args[]){
    	Demo5 empOne=new Demo5("James Smith");
    	Demo5 empTwo=new Demo5("Mary Anne");
    	
    	empOne.empAge(26);
    	empOne.empDesignation("Senior Software Engineer");
    	empOne.empSalary(1000);
    	empOne.printEmployee();
    	System.out.println("*********************************");
    	empTwo.empAge(21);
    	empTwo.empDesignation("Software Engineer");
    	empTwo.empSalary(500);
    	empTwo.printEmployee();
    }
}
