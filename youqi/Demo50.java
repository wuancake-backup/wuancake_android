public class Demo50 implements Demo49{
    public void eat(){
    	System.out.println("Mammal eats");
    }
    public void travel(){
    	System.out.println("Mammal travels");
    }
    public int no0fLegs(){
    	return 0;
    }
    public static void main(String args[]){
    	Demo50 m=new Demo50();
    	m.eat();
    	m.travel();
    }
}