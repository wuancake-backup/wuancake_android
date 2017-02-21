package com.example.wch.testweather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import com.example.wch.testweather.utils.WeatherUtils;

/**
 * Created by WCH on 2017/2/16.
 */

public class WeatherService extends Service {
    /**
     * 此处要与MainActivity一致
     */
    private final String BROADCAST_ACTION = "vaint.wyt.broadcast";
    private String city;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //由于service开启后，依旧占用主线程，所以这里必须开启一个线程来执行耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                city = intent.getStringExtra("city");
                String[] weather = null;
                try {
                    weather = getWeather();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent();
                i.putExtra("weather", weather);
                i.setAction(BROADCAST_ACTION);
                sendBroadcast(i);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private String[] getWeather() throws IOException, XmlPullParserException {
        return WeatherUtils.getWeather(city);
    }
}
