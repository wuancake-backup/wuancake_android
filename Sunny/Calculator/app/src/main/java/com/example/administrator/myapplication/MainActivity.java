package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    StringBuilder sb = new StringBuilder();
    Calculator cal = new Calculator();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.sub).setOnClickListener(this);
        findViewById(R.id.mul).setOnClickListener(this);
        findViewById(R.id.div).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.lp).setOnClickListener(this);
        findViewById(R.id.rp).setOnClickListener(this);
        findViewById(R.id.eql).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);

//        Button btn = (Button) findViewById(R.id.lp);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText("123");
//            }
//        });
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

    @Override
    public void onClick(View view) {
        char c = 'x';
        String s = "0123456789.+-*/()";
        switch (view.getId()) {
            case R.id.btn0:
                c = '0';
                break;
            case R.id.btn1:
                c = '1';
                break;
            case R.id.btn2:
                c = '2';
                break;
            case R.id.btn3:
                c = '3';
                break;
            case R.id.btn4:
                c = '4';
                break;
            case R.id.btn5:
                c = '5';
                break;
            case R.id.btn6:
                c = '6';
                break;
            case R.id.btn7:
                c = '7';
                break;
            case R.id.btn8:
                c = '8';
                break;
            case R.id.btn9:
                c = '9';
                break;
            case R.id.div:
                c = '/';
                break;
            case R.id.add:
                c = '+';
                break;
            case R.id.sub:
                c = '-';
                break;
            case R.id.mul:
                c = '*';
                break;
            case R.id.dot:
                c = '.';
                break;
            case R.id.lp:
                c = '(';
                break;
            case R.id.rp:
                c = ')';
                break;
            case R.id.clear:
                cal.clearText();
                textView.setText("0");
                break;
            case R.id.del:
                if (textView.getText() != "0") {
                    if (cal.getText().length() == 0) {
                        cal.setText(textView.getText().toString());
                    }
                    cal.delTailChar();
                    if (cal.getText().length() != 0) {
                        textView.setText(cal.getText());
                    } else {
                        textView.setText("0");
                    }
                }
                break;
            case R.id.eql:
                if (cal.parseText()) {
                    textView.setText(cal.getText());
                    cal.clearText();
                } else {
                    Toast.makeText(this, "Input Error!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        if (s.indexOf(c) > 0) {
            cal.appendChar(c);
            textView.setText(cal.getText());
        }
    }
}
