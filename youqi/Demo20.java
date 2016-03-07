import java.util.*;
public class Demo20 {
    public static void main(String args[]){
    	try{
    		long start = System.currentTimeMillis();
    		System.out.println(new Date()+"\n");
    		Thread.sleep(5*60*10);
    		System.out.println(new Date()+"\n");
    		long end=System.currentTimeMillis();
    		long diff=end-start;
    		System.out.println("Different is:"+diff);
    	}catch(Exception e){
    		System.out.println("Got an exception!");
    	}
    }
}
