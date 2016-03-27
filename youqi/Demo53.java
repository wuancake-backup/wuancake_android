public class Demo53<T> {
	private T t;
	public void add(T t){
		this.t=t;
	}
	public T get(){
		return t;
	}
	public static void main(String[] args){
		Demo53<Integer> integerBox=new Demo53<Integer>();
		Demo53<String> stringBox=new Demo53<String>();
		integerBox.add(new Integer(10));
		stringBox.add(new String("Hello world"));
		System.out.printf("Integer Value:%d\n\n",integerBox.get());
		System.out.printf("String Value:%s\n",stringBox.get());
	}
}
