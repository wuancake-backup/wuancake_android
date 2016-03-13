public class Demo43{
    public static void main(String[] args){
    	Demo42 s=new Demo42("Mohd Mohtashim","Ambehta,UP",3,3600.00);
    	Demo41 e=new Demo42("John Adams","Boston,MA",2,2400.00);
    	System.out.println("Call mailCheck using Salary refrence --");
    	s.mailCheck();
    	System.out.println("\n Call mailCheck using Employee refrence--");
    	e.mailCheck();
    }
}
