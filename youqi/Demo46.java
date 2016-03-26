public class Demo46 {
    public static void main(String args[]){
    	Demo45 s=new Demo45("Mohd Mohtashim","Ambehta,UP",3,3600.00);
    	Demo44 e=new Demo45("John Ddams","Boston,MA",2,2400.00);
    	
    	System.out.println("Call mailCheck using Salary reference --");
    	s.mailCheck();
    	System.out.println("\nCall mailCheck using Salary reference--");
    	e.mailCheck();
    }
}
