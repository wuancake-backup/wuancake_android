package com.example.miaoyu.jsonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cityN;
    private Button search_weather;
    private queryTask query;
    private TextView tv_result;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityN=(EditText)findViewById(R.id.cityName);
        tv_result=(TextView)findViewById(R.id.result);


        search_weather=(Button)findViewById(R.id.searchWeather);
        search_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=cityN.getText().toString();
                if(city.length()<1){
                    Toast.makeText(MainActivity.this,"@string/toast_text",Toast.LENGTH_LONG).show();
                }
                else{
                    query=new queryTask(tv_result);
                    query.execute(city);
                    Log.d("true", "onClick: "+city);
                }
            }
        });

    }
}
