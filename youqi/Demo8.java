import java.io.*;
public class Demo8 {
    public String name;
    private double salary;
    public Demo8(String empName){
    	name=empName;
    }
    public void setSalary(double empSal){
    	salary=empSal;
    }
    public void printEmp(){
    	System.out.println("name:"+name);
    	System.out.println("salary:"+salary);
    }
    public static void main(String args[]){
    	Demo8 empOne=new Demo8("Ransika");
    	empOne.setSalary(1000);
    	empOne.printEmp();
    }
}
