package com.example.miaoyu.jsonapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by miaoyu on 2016/4/14.
 */
public class queryTask extends AsyncTask<String,String,String> {
    private TextView tv;

    String dataUrl="http://web.juhe.cn:8080/environment/air/pm";
    String key="7ab3a6f6cae1b6ee9dd3e2e4ced51246";

    public queryTask(TextView tv){

        this.tv=tv;
    }

    @Override
    protected String doInBackground(String...parms) {
        //ArrayList<NameValuePair> datalist=new ArrayList<>();
        String cityN=parms[0];

        BufferedReader reader;
        try {
            String request=dataUrl+"?city="+ URLEncoder.encode(cityN,"UTF-8")+"&key="+URLEncoder.encode(key,"UTF-8");
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            connection.setUseCaches(false);



            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            int responseCode=connection.getResponseCode();
            if(responseCode==200) {
                String inputline = null;
                String resultData = null;

                while ((inputline = reader.readLine()) != null) {
                    resultData = inputline + "\n";
                }

                return resultData;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String resultData) {

        try {
            JSONObject jsonObject = new JSONObject(resultData);

            int resultcode=jsonObject.getInt("resultcode");

            if (resultcode==200){
                JSONArray OutputArray =jsonObject.getJSONArray("result");
                JSONObject resultObject =OutputArray.getJSONObject(0);
                String outputS="城市名称："+resultObject.getString("city")+"\n"+
                        "PM2.5指数："+resultObject.getString("PM2.5")+"\n"+
                        "空气质量指数："+resultObject.getString("AQI")+"\n"+
                        "空气质量："+resultObject.getString("quality")+"\n"+
                        "PM10："+resultObject.getString("PM10")+"\n"+
                        "一氧化碳："+resultObject.getString("CO")+"\n"+
                        "二氧化碳："+resultObject.getString("NO2")+"\n"+
                        "臭氧："+resultObject.getString("O3")+"\n"+
                        "二氧化硫："+resultObject.getString("SO2")+"\n"+
                        "更新时间："+resultObject.getString("time");
                tv.setText(outputS);
                Log.d("true", "onPostExecute: "+resultcode);
            }else{
                String reason = jsonObject.getString("error_code");
                tv.setText(reason);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
