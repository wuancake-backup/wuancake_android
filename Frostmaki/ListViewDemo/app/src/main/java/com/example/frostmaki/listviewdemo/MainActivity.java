package com.example.frostmaki.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private  MyAdapter myAdapter;
    private ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAdapter=new MyAdapter(this);
        mlistview=(ListView)this.findViewById(R.id.listview);
        mlistview.setAdapter(myAdapter);
    }
}
