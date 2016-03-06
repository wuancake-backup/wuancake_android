import java.io.*;
public class Demo31 {
    public static void main(String args[]) throws IOException{
    	FileInputStream in=null;
    	FileOutputStream out=null;
    	try{
    		in=new FileInputStream("src/input.txt");
    		out=new FileOutputStream("src/output.txt");
    		int c;
    		while((c=in.read())!=-1){
    			out.write(c);
    		}
    	}finally{
    		if(in!=null){
    			in.close();
    		}
    		if(out!=null){
    			out.close();
    		}
    	}
    }
}
