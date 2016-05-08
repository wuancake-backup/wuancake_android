package com.example.frostmaki.splashdemand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import android.os.Handler;

/**
 * Created by Frostmaki on 2016/5/8.
 */
public class logoActivity extends Activity{
private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //挡住标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //挡住状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.logo);
        imageView=(ImageView)findViewById(R.id.logo_Image);
        //一个延时的子线程
         new Handler().postDelayed(new Runnable(){
             @Override
             public void run() {
                 Intent intent=new Intent(logoActivity.this,MainActivity.class);
                 startActivity(intent);
                 //活动结束
                 finish();
             }
         },3000);
    }
}

