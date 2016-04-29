package com.example.frostmaki.weatherdemo;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Frostmaki on 2016/4/29.
 */
public class QueryAsyncTask extends AsyncTask<String,String,String>{
    private Context context;
    private TextView textView;
    private final String KEY="27989b7032a9794af2a666d79e1174dd";

    public QueryAsyncTask(Context context,TextView textView){
        this.context=context;
        this.textView=textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String cityName=params[0];
        Log.d("调试", "doInBackground: "+cityName);

        try {
            String httpurl="http://apis.baidu.com/heweather/weather/free"+"?city="+ URLEncoder.encode(cityName,"UTF-8");
            URL url = new URL(httpurl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestProperty("apikey", KEY);

            conn.connect();
            Log.d("调试", "doInBackground: " +conn);

            BufferedReader bos=new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line=null;
            StringBuilder ResultData=new StringBuilder();
            while ((line=bos.readLine())!=null){
                ResultData.append(line+"\n");
            }
            bos.close();
            return ResultData.toString();
        }catch(Exception e){
            Log.e("错误", "doInBackground: "+e );
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        String output=null;
        String statusString=null;
        String line_citycnty=null;
        String line_tmp=null;
        String line_loctime=null;
        String line_comf=null;
        String line_weather=null;


        String forecast_1 =null;
        String forecast_2=null;
        Log.e("输出", "222222");
        try {
            JSONObject jsonobject_root = new JSONObject(s);//根元素
            Log.e("输出","33333");
            JSONArray jsonarray =jsonobject_root.getJSONArray("HeWeather data service 3.0");//根元素的值得array
            Log.e("输出","44444");
            JSONObject jsonobject_element =jsonarray.getJSONObject(0);//根元素的值得array的object
            Log.e("输出","55555");
            statusString=jsonobject_element.getString("status");
            if (statusString.equals("ok")){
                Log.e("输出","66666");
                JSONObject basic=jsonobject_element.getJSONObject("basic");
                line_citycnty="城市："+basic.getString("city")+"    国家："+basic.getString("cnty");
                line_loctime="数据更新当地时间："+basic.getJSONObject("update").getString("loc");

                JSONObject weather=jsonobject_element.getJSONObject("now").getJSONObject("cond");
                line_weather="天气状况:"+weather.getString("txt");

                JSONObject hourly_forecast=jsonobject_element.getJSONArray("hourly_forecast").getJSONObject(0);
                line_tmp="温度："+hourly_forecast.getInt("tmp");

                JSONObject suggestion_comf=jsonobject_element.getJSONObject("suggestion").getJSONObject("comf");
                line_comf="舒适度"+suggestion_comf.getString("txt");



                JSONObject daily_forecast_1 =jsonobject_element.getJSONArray("daily_forecast").getJSONObject(0);
                forecast_1=daily_forecast_1.getString("date")+"    "
                        +"天气："+daily_forecast_1.getJSONObject("cond").getString("txt_d")+"    "
                        +"气温："+daily_forecast_1.getJSONObject("tmp").getString("min")+"~"+daily_forecast_1.getJSONObject("tmp").getString("max");

                JSONObject daily_forecast_2 =jsonobject_element.getJSONArray("daily_forecast").getJSONObject(1);
                forecast_2=daily_forecast_2.getString("date")+"    "
                        +"天气："+daily_forecast_2.getJSONObject("cond").getString("txt_d")+"    "
                        +"气温："+daily_forecast_2.getJSONObject("tmp").getString("min")+"~"+daily_forecast_2.getJSONObject("tmp").getString("max");

                output=line_citycnty+"\n"+line_loctime+"\n"+line_weather+"\n"+line_tmp+"\n"+line_comf
                        +"\n"+"\n"+"\n"+"\n"+"\n"+"\n"
                        +"明后两天天气状况预测："+"\n"
                        +forecast_1+"\n"+"\n"+forecast_2;
                Log.e("完整代码", s);
            }else {
                Log.e("输出", "7777");
                output = statusString;

            }
            Log.e("输出", "111111");
        }catch (Exception e){
            Log.e("异常", "onPostExecute: "+e );
        }
        textView.setText(output);
    }
}
