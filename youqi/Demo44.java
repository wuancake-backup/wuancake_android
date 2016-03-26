public abstract class Demo44 {
    private String name;
    private String address;
    private int number;
    public Demo44(String name,String address,int number){
    	System.out.println("Constructing an Demo44");
    	this.name=name;
    	this.address=address;
    	this.number=number;
    }
    public double computePay(){
    	System.out.println("Inside Demo44 computePay");
    	return 0.0;
    }
    public void mailCheck(){
    	System.out.println("Mailing a check to "+this.name+" "+this.address);
    }
    public String toString(){
    	return name+" "+address+" "+number;
    }
    public String getName(){
    	return name;
    }
    public String getAddress(){
    	return address;
    }
    public void steAddress(String newAddress){
    	address=newAddress;
    }
    public int getNumber(){
    	return number;
    }
}
