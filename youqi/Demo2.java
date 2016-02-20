class FreshJuice{
	enum FreshJuiceSize{SMALL,MEDIUM,BIG};
	FreshJuiceSize size;
}

public class Demo2 {
    public static void main(String args[]){
    	FreshJuice juice=new FreshJuice();
    	juice.size=FreshJuice.FreshJuiceSize.MEDIUM;
    	System.out.println("Size:"+juice.size);
    }
}