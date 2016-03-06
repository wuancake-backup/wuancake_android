import java.io.*;
public class Demo37 {
    private double balance;
    private int number;
    public Demo37(int number){
    	this.number=number;
    }
    public void deposit(double amount){
    	balance+=amount;
    }
    public void withdraw(double amount) throws Demo36{
    	if(amount<=balance){
    		balance-=amount;
    	}
    	else{
    		double needs=amount-balance;
    		throw new Demo36(needs);
    	}
    }
    public double getBalance(){
    	return balance;
    }
    public int getNumber(){
    	return number;
    }
}
