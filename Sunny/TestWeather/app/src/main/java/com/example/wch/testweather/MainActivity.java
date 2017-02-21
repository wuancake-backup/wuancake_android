package com.example.wch.testweather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchBn;
    private TextView weatherTV;
    private Button currentCityTV;
    private ImageView startImage;
    private ImageView endImage;

    private final String BROADCAST_ACTION = "vaint.wyt.broadcast";
    private MyBroadcastReceiver myBroadcastReceiver;
    /**获取设置的选项*/
    private Map<String,Boolean> setup;

    /**
     * 天气图标数量</BR>图标位于drawable中，属于本地加载。
     */
    private int imagesCount = 23;
    private int[] imagesID = new int[imagesCount];
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
        getImagesID();

        //获得全局域的Preference
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        getSetupInfo();
        
        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION);
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, filter);
    }

    /**获得设置选项的值 */
    private void getSetupInfo() {
        setup = new HashMap<String,Boolean>();
//        setup.put("threeDay",preferences.getBoolean("threeDay", true));
//        setup.put("cityInfo", preferences.getBoolean("cityInfo", true));
        setup.put("threeDay",true);
        setup.put("cityInfo", true);
    }

    private void loadView() {
        searchBn = (Button) findViewById(R.id.searchBn);
        currentCityTV = (Button) findViewById(R.id.currentCityTV);
        weatherTV = (TextView) findViewById(R.id.weatherTV);
        startImage = (ImageView) findViewById(R.id.startGraph);
        endImage = (ImageView) findViewById(R.id.endGraph);

        currentCityTV.setOnClickListener(this);
        searchBn.setOnClickListener(this);
    }

    /**
     * 获得天气图标ID</BR>R文件中对应的值
     */
    private void getImagesID() {
        Resources res = getResources();
        String imageName = "";
        for (int i = 0; i < imagesCount; i++) {
            imageName = "a_" + i;
            imagesID[i] = res.getIdentifier(imageName,"drawable",getPackageName());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.currentCityTV){ // 打开选择城市界面
            Intent intent = new Intent(MainActivity.this,ChooseCityActivity.class);
            this.startActivity(intent);
        }else if(v.getId() == R.id.searchBn){ // 获取天气信息
            String city = "北京";
            //开启Service服务，当再次启动时，不会执行onCreate，但仍旧执行onStart方法
            Intent intent = new Intent("WeatherService");
            intent.putExtra("city",city);
            this.startService(intent);
        }
    }

    //定义一个广播接收器，用于接收Service获得的天气信息
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] weatherInfo = intent.getStringArrayExtra("weather");
            if (weatherInfo == null) {
                Toast.makeText(MainActivity.this, "没有当前城市的天气信息", Toast.LENGTH_SHORT).show();
            } else if (weatherInfo.length == 1) {
                Toast.makeText(MainActivity.this, "连接超时，请检查网络", Toast.LENGTH_SHORT).show();
            } else {
                showWeather(weatherInfo);
            }
        }
    }

    /**
     * 根据设置选项显示天气信息
     */
    private void showWeather(String[] weatherInfo) {
        // 根据获得数据，选择对应天气图标
        // String[8]为天气趋势开始图片名称，String[9]为天气趋势结束图片名称。格式为：3.gif
        String index = weatherInfo[8].split("\\.")[0];
        int startImageID = Integer.parseInt(index);
        startImage.setImageDrawable(getResources().getDrawable(
                imagesID[startImageID]));
        index = weatherInfo[9].split("\\.")[0];
        int endImageID = Integer.parseInt(index);
        endImage.setImageDrawable(getResources().getDrawable(
                imagesID[endImageID]));

        // 以下为参照返回数据进行筛选，一一对应即可
        StringBuilder sb = new StringBuilder();

        sb.append("查询时间\t" + weatherInfo[4] + "\r\n\r\n");

        String[] temp = weatherInfo[10].split(";|；");// 一般要使用中文“；”。
        sb.append(temp[0] + " " + temp[1] + " " + temp[2] + "\r\n\r\n");

        sb.append("今天\t" + weatherInfo[5] + " " + weatherInfo[6].split(" ")[1]
                + " " + weatherInfo[7] + "\r\n\r\n");

        if(setup.get("threeDay")){//如果用户选择查看三天的天气
            sb.append("明天\t" + weatherInfo[13] + " " + weatherInfo[12] + " "
                    + weatherInfo[14] + "\r\n\r\n");
            sb.append("后天\t" + weatherInfo[18] + " " + weatherInfo[17] + " "
                    + weatherInfo[19] + "\r\n\r\n");
        }
        if(setup.get("cityInfo")){//如果用户选择查看城市介绍
            sb.append("城市介绍" + "\r\n   " + weatherInfo[22].replaceAll("。", "。\r\n   ") + "\r\n");
        }
        weatherTV.setText(sb.toString());
//        String strNew = "";
//        for(int i=0;i<weatherInfo.length;i++){
//            strNew += weatherInfo[i];
//        }
//        weatherTV.setText(strNew);
    }
}
