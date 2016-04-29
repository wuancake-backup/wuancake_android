package com.example.frostmaki.weatherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TextView textView;
    private QueryAsyncTask queryAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.city_query);
        editText=(EditText)findViewById(R.id.city_name);
        textView=(TextView)findViewById(R.id.output_text);

        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName_string =editText.getText().toString();
                if(cityName_string==""){
                    Toast.makeText(MainActivity.this,"你还没有输入城市名称",Toast.LENGTH_SHORT).show();
                }else{
                    queryAsyncTask=new QueryAsyncTask(MainActivity.this,textView);
                    queryAsyncTask.execute(cityName_string);
                }
            }
        });
    }
}
