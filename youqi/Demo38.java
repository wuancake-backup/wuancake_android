public class Demo38 {
	public static void main(String args[]){
		Demo37 c=new Demo37(101);
	    System.out.println("Depositing $500...");
	    c.deposit(500.00);
	    try{
	    	System.out.println("\nWithdrawing $100...");
	    	c.withdraw(100.00);
	    	System.out.println("\nWithdrawing $600...");
	    	c.withdraw(600.00);
	    }catch(Demo36 e){
	    	System.out.println("Sorry,but you are short $"+e.getAmount());
	    	e.printStackTrace();
	    }
	}
}
