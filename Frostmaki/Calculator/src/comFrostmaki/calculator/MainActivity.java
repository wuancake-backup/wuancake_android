package comFrostmaki.calculator;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{

	TextView inputText;
	TextView OutputText;
	
	String inputStr;
	String inputStr2;
	String outputStr2;
	
	Button but_zero;
	Button but_one;
	Button but_two;
	Button but_three;
	Button but_four;
	Button but_five;
	Button but_six;
	Button but_seven;
	Button but_eight;
	Button but_nine;
	
	Button but_del;
	Button but_clear;
	
	Button but_add;//+
	Button but_minus;//-
	Button but_multiplied;//*
	Button but_divided;// /
	
	Button but_equal;
	
	Button but_point;
	
	  
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	inputText =(TextView)findViewById(R.id.textInput);
    	OutputText =(TextView)findViewById(R.id.textOnput);
    	
    	but_zero=(Button)findViewById(R.id.but_zero);
    	but_one=(Button)findViewById(R.id.but_one);
    	but_two=(Button)findViewById(R.id.but_two);
    	but_three=(Button)findViewById(R.id.but_three);
    	but_four=(Button)findViewById(R.id.but_four);
    	but_five=(Button)findViewById(R.id.but_five);
    	but_six=(Button)findViewById(R.id.but_six);
    	but_seven=(Button)findViewById(R.id.but_seven);
    	but_eight=(Button)findViewById(R.id.but_eight);
    	but_nine=(Button)findViewById(R.id.but_nine);
    	
    	but_del=(Button)findViewById(R.id.but_del);
    	but_clear=(Button)findViewById(R.id.but_clear);
    	
    	but_add=(Button)findViewById(R.id.but_add);              //+
    	but_minus=(Button)findViewById(R.id.but_minus);          //-
    	but_multiplied=(Button)findViewById(R.id.but_multiplied);//*
    	but_divided=(Button)findViewById(R.id.but_divided);      // /
    	
    	but_equal=(Button)findViewById(R.id.but_equal);
    	
    	but_point=(Button)findViewById(R.id.but_point);
    	

    	
    	//click事件
        but_one.setOnClickListener(this);
        but_two.setOnClickListener(this);
        but_three.setOnClickListener(this);
        but_four.setOnClickListener(this);
        but_five.setOnClickListener(this);
        but_six.setOnClickListener(this);
        but_seven.setOnClickListener(this);
        but_nine.setOnClickListener(this);
        but_eight.setOnClickListener(this);
        but_zero.setOnClickListener(this);
        but_point.setOnClickListener(this);
        
        but_del.setOnClickListener(this);
        but_clear.setOnClickListener(this);
        
        but_add.setOnClickListener(this);
        but_minus.setOnClickListener(this);
        but_multiplied.setOnClickListener(this);
        but_divided.setOnClickListener(this);
        
        but_equal.setOnClickListener(this);
        Log.d("cuowu", "button click");
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.but_one:
		case R.id.but_two:
		case R.id.but_three:
		case R.id.but_four:
		case R.id.but_five:
		case R.id.but_six:
		case R.id.but_seven:
		case R.id.but_eight:
		case R.id.but_nine:
		case R.id.but_zero:
		case R.id.but_point:
            inputText.setText(inputText.getText().toString()+((Button)v).getText().toString());
            break;
		case R.id.but_add:
		case R.id.but_minus:
		case R.id.but_multiplied:
		case R.id.but_divided:
			inputText.setText(inputText.getText().toString()+((Button)v).getText().toString());
			break;
		case R.id.but_del:
			inputText.setText(inputText.getText().toString().substring(0, inputText.getText().toString().length()-1));
			break;
		case R.id.but_clear:
			
			inputText.setText("");
			OutputText.setText("");
			
			Log.d("cuowu", "clear");
			break;
		case R.id.but_equal:
			inputStr=inputText.getText().toString();
			String result="";
			try{
				result=cal(inputStr);
				
			}catch(Exception e){
				System.out.println(e+"33333333");
				
			}finally{
				OutputText.setText(result);
			}
			break;
		
		}
	}
	
	
	
		
		
		
		
	
	//对转化后的数组进行逆波兰计算
	private static String cal(String inputStr) {
        String rpnExp ="";
        
        String[] afterTrans=translate(inputStr);
        
        if (afterTrans == null)
     	   return null;
        
        
    	Stack<Double> stackShuZi =new Stack<Double>();
    	Stack<String> stackSuan =new Stack<String>();
    	
         for (String input: afterTrans) {
              if ( isOperator(input)) {
                   
                   while (!stackSuan.empty()) {
                         if ( comparePrecedence(input,stackSuan.peek()) <= 0) {
                        	String ysf =stackSuan.pop();
                 			double jg=0.0;
                 			double val_1=stackShuZi.pop();
                 			double val_2=stackShuZi.pop();
                 			if(ysf.equals("+")) {
                 				jg=val_1+val_2;
                 					
                 			}else if(ysf.equals("-")) {
                 				jg=val_2-val_1;
                 				    
                 			}else if(ysf.equals("*")) {
                 				jg=val_1*val_2;
                 				   
                 			}else if(ysf.equals("/")) {
                 				jg=val_2/val_1;
                 				
                 			}
                 			stackShuZi.push(jg);
                            continue;
                        }
                        if( comparePrecedence(input,stackSuan.peek()) > 0){
                        	stackSuan.add(input);
                        	
                        }
                     break;
                  }
                  if(stackSuan.empty()){ 
                	  stackSuan.add(input);
                  }
             }
              else {
            	  try{
            		  System.out.println( input+"555555");
            		  Double val=Double.parseDouble(input);
            		  stackShuZi.add(val);
            		  
            	  }catch(Exception e) {
            		  System.out.println( e+"1111111");
            	  }
             }
        }
        
         while (!stackSuan.empty()) {
        	String ysf =stackSuan.pop();
  			double jg=0.0;
  			double val_1=stackShuZi.pop();
 			double val_2=stackShuZi.pop();
 			if(ysf.equals("+")) {
 				jg=val_1+val_2;
 					
 			}else if(ysf.equals("-")) {
 				jg=val_2-val_1;
 				    
 			}else if(ysf.equals("*")) {
 				jg=val_1*val_2;
 				   
 			}else if(ysf.equals("/")) {
 				jg=val_2/val_1;
 				
 			}
  			stackShuZi.push(jg);
        }
        try{
        	System.out.println(stackShuZi.size());
        	rpnExp=stackShuZi.peek().toString();
        }catch(Exception e){
        	System.out.println(e+"2222222");
        	
        }
        System.out.println(rpnExp+"4444444");
         return rpnExp;
         
  }
		
	//////////////////////////////////////////
	/////////////////////////////////////////
	///////////////////////////////////////////
	
		

	//将输入的算术表达式分割成数组
	public static String[] translate(String inputStr){
		
		String lastshuzi="";
		ArrayList<String>    strArr = new ArrayList<String>();
		
		
		for (char ch: inputStr.toCharArray()){
			String str = Character. toString(ch);
            if ( isOperator(str)) {      
               if(lastshuzi==""){
            	   strArr.add(str);
               }else{
            	   strArr.add(lastshuzi);
            	   strArr.add(str);
                
            	   lastshuzi = "";
               }
           }
            else {
                lastshuzi += ch;
           }
      }
		  // Add the last operand
        strArr.add(lastshuzi);
        
        String[] outputs = new String[strArr.size()];
         return strArr.toArray(outputs);
	}
	
	
	//设置运算符的优先级
	 private final static Map<String, Integer> OPERATORS = new HashMap<String, Integer>();
     
     static{
            OPERATORS.put( "+", 0);
            OPERATORS.put( "-", 0);
            OPERATORS.put( "*", 1);
            OPERATORS.put( "/", 1);
     }
     
     //根据运算符得到优先级
     private static boolean isOperator(String str) {
            return OPERATORS.containsKey(str);
     }
     
     //比较优先级，0为优先级相同，-1优先级低，1优先级高
     private static final int comparePrecedence(String op1, String op2) {
         
         return OPERATORS.get(op1) - OPERATORS.get(op2);
  }
		
}
	
    
    

   

