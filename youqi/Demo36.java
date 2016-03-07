import java.io.*;
public class Demo36 extends Exception{
    private double amount;
    public Demo36(double amount){
    	this.amount=amount;
    }
    public double getAmount(){
    	return amount;
    }
}
