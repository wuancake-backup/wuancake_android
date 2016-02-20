public class Demo4 {
    int tAge;
    public Demo4(String tName){
    	System.out.println("Name:"+tName);
    }
    public void setAge(int a){
    	tAge=a;
    }
    public int getAge(){
    	System.out.println("Age:"+tAge);
    	return tAge;
    }
    public static void main(String args[]){
    	Demo4 t1=new Demo4("Tommy");
    	t1.setAge(4);
    	t1.getAge();
    	System.out.println("Variable Value :" + t1.tAge);
    }
}
