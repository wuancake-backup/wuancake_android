class Demo54 implements Runnable{
	private Thread t;
	private String threadName;
	Demo54(String name){
		threadName =name;
		System.out.println("Creating "+threadName);
	}
	public void run(){
		System.out.println("Running "+threadName);
		try{
			for(int i=4;i>0;i--){
				System.out.println("Thread: "+threadName+","+i);
				Thread.sleep(50);
			}
		}catch(InterruptedException e){
			System.out.println("Thread "+threadName + " interruputed.");
		}
		System.out.println("Thread "+threadName+" exiting.");
	}
	public void start(){
		System.out.println("Starting "+threadName);
		if(t==null){
			t=new Thread(this,threadName);
			t.start();
		}
	}
}
public class Demo55{
	public static void main(String args[]){
		Demo54 R1=new Demo54("Thread-1");
		R1.start();
		Demo54 R2=new Demo54("Thread-2");
		R2.start();
	}
}

