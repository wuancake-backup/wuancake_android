package com.example.frostmaki.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private  MyAdapter myAdapter;
    private ListView mlistview;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new ArrayList<String>();
        for(int i=0;i<=20;i++){
            data.add("    数据:"+i);
        }
        myAdapter=new MyAdapter(this,data);
        mlistview=(ListView)this.findViewById(R.id.listview);
        mlistview.setAdapter(myAdapter);
    }
}
