package com.example.sumeng.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button one,two,three,four,five,six,seven,eight,nine,zero,point;
    public Button div,mul,c,add,sub,del,equal;// /,x,c,+,-,删除
    public TextView firstLine,secondLine,thirdLine;
    public double result; //存储计算结果
    public double num1=0,num2=0;
    public boolean isEqual=false;
    public int op=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);
        six=(Button)findViewById(R.id.six);
        seven=(Button)findViewById(R.id.seven);
        eight=(Button)findViewById(R.id.eight);
        nine=(Button)findViewById(R.id.nine);
        zero=(Button)findViewById(R.id.zero);
        point=(Button)findViewById(R.id.point);
        c=(Button)findViewById(R.id.c);
        div=(Button)findViewById(R.id.div);
        add=(Button)findViewById(R.id.add);
        sub=(Button)findViewById(R.id.sub);
        del=(Button)findViewById(R.id.del);
        mul=(Button)findViewById(R.id.mul);
        equal=(Button)findViewById(R.id.equal);
        firstLine=(TextView)findViewById(R.id.firstLine);
        secondLine=(TextView)findViewById(R.id.secondLine);
        thirdLine=(TextView)findViewById(R.id.thirdLine);
        ClickListener cl=new ClickListener();
        one.setOnClickListener(cl);
        two.setOnClickListener(cl);
        three.setOnClickListener(cl);
        four.setOnClickListener(cl);
        five.setOnClickListener(cl);
        six.setOnClickListener(cl);
        seven.setOnClickListener(cl);
        eight.setOnClickListener(cl);
        nine.setOnClickListener(cl);
        zero.setOnClickListener(cl);
        point.setOnClickListener(cl);
        c.setOnClickListener(cl);
        add.setOnClickListener(cl);
        sub.setOnClickListener(cl);
        del.setOnClickListener(cl);
        mul.setOnClickListener(cl);
        equal.setOnClickListener(cl);




    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.c :
                    firstLine.setText("");
                    secondLine.setText("");
                    thirdLine.setText("0");
                    op=0;
                    isEqual=false;
                    num1=0;
                    num2=0;
                    break;
                case R.id.del:
                    if(secondLine.getText().toString().equals("")){
                       String myLine=firstLine.getText().toString();
                        try{
                            firstLine.setText(myLine.substring(0,myLine.length()-1));
                        }catch (Exception e){
                            firstLine.setText("");
                        }
                    }
                    else{
                        String myLine=secondLine.getText().toString();
                        try{
                            secondLine.setText(myLine.substring(0,myLine.length()-1));
                        }catch (Exception e){
                            secondLine.setText("");
                        }
                    }
                    break;
                case R.id.zero:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLine0 = firstLine.getText().toString();
                        myLine0 += "0";
                        firstLine.setText(myLine0);
                    }
                    else{
                        String myLine0s = secondLine.getText().toString();
                        myLine0s+="0";
                        secondLine.setText(myLine0s);
                    }
                    break;
                case R.id.one:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLine1=firstLine.getText().toString();
                        myLine1+="1";
                        firstLine.setText(myLine1);
                    }
                    else{
                        String myLine1s=secondLine.getText().toString();
                        myLine1s+="1";
                        secondLine.setText(myLine1s);
                    }
                    break;
                case R.id.two:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLine2=firstLine.getText().toString();
                        myLine2+="2";
                        firstLine.setText(myLine2);
                    }
                    else{
                        String myLine2s=secondLine.getText().toString();
                        myLine2s+="2";
                        secondLine.setText(myLine2s);
                    }
                    break;
                case R.id.three:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }
                    if(op==0) {
                        String myLine3=firstLine.getText().toString();
                        myLine3+="3";
                        firstLine.setText(myLine3);
                    }
                    else{
                        String myLine3s=secondLine.getText().toString();
                        myLine3s+="3";
                        secondLine.setText(myLine3s);
                    }
                    break;
                case R.id.four:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }
                    if(op==0) {
                        String myLine4=firstLine.getText().toString();
                        myLine4+="4";
                        firstLine.setText(myLine4);
                    }
                    else{
                        String myLine4s=secondLine.getText().toString();
                        myLine4s+="4";
                        secondLine.setText(myLine4s);
                    }
                    break;
                case R.id.five:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }
                    if(op==0) {
                        String myLine5=firstLine.getText().toString();
                        myLine5+="5";
                        firstLine.setText(myLine5);
                    }
                    else{
                        String myLine5s=secondLine.getText().toString();
                        myLine5s+="5";
                        secondLine.setText(myLine5s);
                    }
                    break;
                case R.id.six:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }
                    if(op==0) {
                        String myLine6=firstLine.getText().toString();
                        myLine6+="6";
                        firstLine.setText(myLine6);
                    }
                    else{
                        String myLine6s=secondLine.getText().toString();
                        myLine6s+="6";
                        secondLine.setText(myLine6s);
                    }
                    break;
                case R.id.seven:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLine7=firstLine.getText().toString();
                        myLine7+="7";
                        firstLine.setText(myLine7);
                    }
                    else{
                        String myLine7s=secondLine.getText().toString();
                        myLine7s+="7";
                        secondLine.setText(myLine7s);
                    }
                    break;
                case R.id.eight:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }
                    if(op==0) {
                        String myLine8=firstLine.getText().toString();
                        myLine8+="8";
                        firstLine.setText(myLine8);
                    }
                    else{
                        String myLine8s=secondLine.getText().toString();
                        myLine8s+="8";
                        secondLine.setText(myLine8s);
                    }
                    break;
                case R.id.nine:
                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLine9=firstLine.getText().toString();
                        myLine9+="9";
                        firstLine.setText(myLine9);
                    }
                    else{
                        String myLine9s=secondLine.getText().toString();
                        myLine9s+="9";
                        secondLine.setText(myLine9s);
                    }
                    break;
                case R.id.point:

                    if(isEqual){
                        firstLine.setText("");
                        secondLine.setText("");
                        thirdLine.setText("");
                    }

                    if(op==0) {
                        String myLinePoint=firstLine.getText().toString();
                        myLinePoint+=".";
                        firstLine.setText( myLinePoint);
                    }
                    else{
                        String myLinePoints=secondLine.getText().toString();
                        myLinePoints+=".";
                        secondLine.setText( myLinePoints);
                    }
                    break;
                case R.id.add:
                    if(op!=0)
                        return;
                    String firstAdd=firstLine.getText().toString();
                    String thirdAdd=thirdLine.getText().toString();
                    if(thirdAdd.equals("")||thirdAdd.equals("0")){
                        if(firstAdd.equals("")){
                            return;
                        }
                        num1=Double.valueOf(firstAdd);
                        secondLine.setText("+");
                        isEqual=false;
                        op=1;
                    }
                    else {
                        num1=Double.valueOf(thirdAdd);
                        firstLine.setText(thirdAdd);
                        secondLine.setText("+");
                        isEqual=false;
                        op=1;
                    }
                    break;
                case R.id.sub:
                    if(op!=0)
                        return;
                    String firstSub=firstLine.getText().toString();
                    String thirdSub=thirdLine.getText().toString();
                    if(thirdSub.equals("")||thirdSub.equals("0")){
                        if(firstSub.equals("")){
                            return;
                        }
                        num1=Double.valueOf(firstSub);
                        secondLine.setText("-");
                        isEqual=false;
                        op=2;
                    }
                    else {
                        num1=Double.valueOf(thirdSub);
                        firstLine.setText(thirdSub);
                        secondLine.setText("-");
                        isEqual=false;
                        op=2;
                    }
                    break;
                case R.id.mul:
                    if(op!=0)
                        return;
                    String firstMul=firstLine.getText().toString();
                    String thirdMul=thirdLine.getText().toString();
                    if(thirdMul.equals("")||thirdMul.equals("0")){
                        if(firstMul.equals("")){
                            return;
                        }
                        num1=Double.valueOf(firstMul);
                        secondLine.setText("X");
                        isEqual=false;
                        op=3;
                    }
                    else {
                        num1=Double.valueOf(thirdMul);
                        firstLine.setText(thirdMul);
                        secondLine.setText("X");
                        isEqual=false;
                        op=3;
                    }
                    break;
                case R.id.div:
                    if(op!=0)
                        return;
                    String firstDiv=firstLine.getText().toString();
                    String thirdDiv=thirdLine.getText().toString();
                    if(thirdDiv.equals("")||thirdDiv.equals("0")){
                        if(firstDiv.equals("")){
                            return;
                        }
                        num1=Double.valueOf(firstDiv);
                        secondLine.setText("/");
                        isEqual=false;
                        op=4;
                    }
                    else {
                        num1=Double.valueOf(thirdDiv);
                        firstLine.setText(thirdDiv);
                        secondLine.setText("/");
                        isEqual=false;
                        op=4;
                    }
                    break;
                case R.id.equal:
                    String second=secondLine.getText().toString();
                    if(second.equals(""))
                        return;
                    second=second.substring(1,second.length());
                    num2=Double.valueOf(second);
                    firstLine.setText("");
                    secondLine.setText("");
                    switch (op){
                        case 0:
                            result=Double.parseDouble(thirdLine.getText().toString());
                            break;
                        case 1:
                            result=num1+num2;
                            break;
                        case 2:
                            result=num1-num2;
                            break;
                        case 3:
                            result=num1*num2;
                            break;
                        case 4:
                            result=num1/num2;
                            break;
                    }
                    thirdLine.setText(String.valueOf(result));
                    isEqual=true;
                    break;
            }


        }
    }
}
