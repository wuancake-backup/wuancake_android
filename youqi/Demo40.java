class Animals{
	public void move(){
		System.out.println("Animals can move");
	}
}
class Dogs extends Animals{
	public void move(){
		super.move();
		System.out.println("Dogs can walk and run");
	}
}
public class Demo40 {
    public static void main(String args[]){
    	Animals b=new Dogs();
    	b.move();
    }
}
