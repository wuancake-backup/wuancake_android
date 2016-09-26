package com.example.administrator.niweather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import JsonBean.Aqi;
import JsonBean.AqiCity;
import JsonBean.DailyForecast;
import JsonBean.HeWeather;
import JsonBean.Now;
import JsonBean.NowCond;
import JsonBean.Results;
import JsonBean.Update;
import JsonBean.dailyForecast.Cond;
import JsonBean.dailyForecast.Tmp;
import JsonBean.dailyForecast.Wind;
import Utils.HttpUtil;
import Utils.MapUtils;
import Utils.SpUtils;
import Utils.Week;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {
    @BindView(R.id.location_title)
    TextView locationTitle;
    @BindView(R.id.current_temp)
    TextView currentTemp;
    @BindView(R.id.now_weather)
    TextView now_weather;
    @BindView(R.id.now_weather_icon)
    ImageView nowWeatherIcon;
    @BindView(R.id.feel_temp)
    TextView feelTemp;
    @BindView(R.id.wet)
    TextView wet;
    @BindView(R.id.wind_dir)
    TextView windDir;
    @BindView(R.id.aqi)
    TextView aqi;
    @BindView(R.id.daily_forecast_grid)
    GridView dailyForecastGrid;
    @BindView(R.id.temp_line_day)
    MyTempLine tempLineDay;
    @BindView(R.id.temp_line_night)
    MyTempLine tempLineNight;
    @BindView(R.id.now_date)
    TextView nowDate;
    @BindView(R.id.forecast_viewpager)
    ViewPager forecastViewpager;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.frame_empty)
    FrameLayout frameEmpty;
    @BindView(R.id.now_linear)
    LinearLayout nowLinear;
    @BindView(R.id.weather_linear)
    LinearLayout weatherLinear;
    @BindView(R.id.temp_unit)
    TextView tempUnit;
    private String location;
    private String weatherID;
    private String nowWeatherAddress = null;
    /**
     * 获取的天气信息
     */
    private String result;
    private HeWeather weather;
    //实时天气信息
    private String nowWeather;
    private String weatherCode;
    private String fl;
    private String sc;
    private String dir;
    private String spd;
    private String pcpn = "0";
    private String wet1;
    private String nowTemp;
    private String mapAddress;

    //天气质量信息
    private String aqi1;
    private String qlty;
    //七天天气预报信息
    private List<DailyForecast> dailyForecastList;
    private int[] tDay;
    private int[] tNight;
    //当前星期
    private String nowWeek;
    private int weekCode;
    private Week week;
    private String loc;//更新日期
    //天气图片
    private Bitmap mBitmap;
    private Bitmap[] dayBitmapArry = new Bitmap[7];
    private Bitmap[] nightBitmapArry = new Bitmap[7];
    private List<String> dayMapCodeList;
    private List<String> nightMapCodeList;
    private AqiCity aqiCity;
    private Aqi aqi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        init();
        Handler jsonHandler = new Handler() {//进行多次解析，保证解析成功
            @Override
            public void handleMessage(Message msg) {

                if (result == null) {
                    getWeatherInfo();
                    sendEmptyMessageDelayed(0, 100);
                } else if (weatherCode == null) {
                    handleResult(result);
                    sendEmptyMessageDelayed(0, 100);
                } else if (mBitmap == null) {
                    getWeatherIcon(mapAddress);
                    sendEmptyMessageDelayed(0, 100);
                } else if (nightBitmapArry[6] == null && dayMapCodeList != null) {
                    getForecastIcon();
                    sendEmptyMessageDelayed(0, 500);
                } else {
                    initInfo();
                    initHeight();
                    if (dailyForecastList!=null){
                        dailyForecastGrid.setAdapter(new DailyAdapter());
                        forecastViewpager.setAdapter(new PagerAdapter());
                    }
                    tDay = new int[7];
                    tNight = new int[7];
                    tempLineDay.setT(tDay);
                    tempLineNight.setT(tNight);
                }

            }
        };
        jsonHandler.sendEmptyMessageDelayed(0, 100);
    }

    private void init() {
        initHeight();
        Date date = new Date();
        week = new Week();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        String nowHour = hourFormat.format(date);
        if (Integer.parseInt(nowHour) > 18) {
            weatherLinear.setBackgroundResource(R.mipmap.night);
        }else {
            weatherLinear.setBackgroundResource(R.mipmap.sun1);
        }
        nowWeek = dateFormat.format(date);
        weekCode = week.getWeekCode(nowWeek);
        initWeather();
        locationTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化高度
     */
    public void initHeight() {
        WindowManager windowManager = this.getWindowManager();
        int emptyHeight = windowManager.getDefaultDisplay().getHeight();
        nowLinear.measure(0, 0);
        locationTitle.measure(0, 0);
        int relativeHeight = nowLinear.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                frameEmpty.getLayoutParams();
        layoutParams.height = emptyHeight - relativeHeight - locationTitle.getMeasuredHeight() -
                feelTemp.getHeight();
        frameEmpty.setLayoutParams(layoutParams);
        scrollView.smoothScrollTo(0, 0);
    }

    /**
     * 初始化界面
     */
    private void initInfo() {
        currentTemp.setText(nowTemp);
        tempUnit.setVisibility(View.VISIBLE);
        now_weather.setText(nowWeather);
        feelTemp.setText("体感温度" + fl + "°" + "\n" + "湿度" + wet1 + "%");
        if (!pcpn.equals("0")) {
            wet.setVisibility(View.VISIBLE);
            wet.setText("降雨量" + pcpn + "mm");
        } else {
            wet.setVisibility(View.GONE);
        }
        windDir.setText(dir + "" + sc + "级" + "\n" + "风速" + spd + "Kmph");
        nowDate.setText(loc + " " + nowWeek + " " + "更新");
        if (aqi2!=null){
            aqi.setText(aqi1 + qlty);
            aqi.setVisibility(View.VISIBLE);
        }
        //处理图片信息
        getWeatherIcon(mapAddress);
        nowWeatherIcon.setImageBitmap(mBitmap);
    }

    /**
     * 初始化天气信息
     */
    private void initWeather() {
        weatherID = SpUtils.getString(this, "weatherID", null);
            location = SpUtils.getString(this, "location", null);
            if (weatherID == null) {
            Intent intent = getIntent();
            location = intent.getStringExtra("location");
            weatherID = intent.getStringExtra("weatherID");
        }
        locationTitle.setText(location);
        getWeatherInfo();

    }

    /**
     * 获取天气信息
     */
    public void getWeatherInfo() {
        nowWeatherAddress = "https://api.heweather.com/x3/weather?cityid=CN" + weatherID +
                "&key=76af5642581c4eac956561e5a68e4aa6";
        HttpUtil.sendHttpRequest(nowWeatherAddress, new HttpUtil.HttpCallBackListener() {
            @Override
            public void onSucceed(String resultString) {
                result = resultString.replaceFirst("HeWeather data service 3.0", "heWeather");
                handleResult(result);
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    /**
     * 获取七天天气预报图片
     */
    public void getForecastIcon() {
        if (dayMapCodeList!=null){
        for (int i = 0; i < 7; i++) {
            String dayMapAddress = "http://files.heweather.com/cond_icon/" + dayMapCodeList.get(i) + ".png";
            MapUtils.getBitmap(dayMapAddress, new MapUtils.HttpCallBackListener() {
                @Override
                public void onSucceed(Bitmap bitmap, int i) {
                    dayBitmapArry[i] = bitmap;
                }

                @Override
                public void onFail(Exception e) {

                }
            }, i);

            final String nightMapAddress = "http://files.heweather.com/cond_icon/" + nightMapCodeList.get(i) + ".png";
            MapUtils.getBitmap(nightMapAddress, new MapUtils.HttpCallBackListener() {
                @Override
                public void onSucceed(Bitmap bitmap, int i) {
                    nightBitmapArry[i] = bitmap;
                }

                @Override
                public void onFail(Exception e) {

                }
            }, i);
        }
        }
    }

    /**
     * 获取实时天气图片
     */
    public void getWeatherIcon(String address) {

        MapUtils.getBitmap(address, new MapUtils.HttpCallBackListener() {
            @Override
            public void onSucceed(Bitmap bitmap, int i) {
                mBitmap = bitmap;
            }

            @Override
            public void onFail(Exception e) {

            }
        }, 0);
    }

    /**
     * @param result 处理天气结果
     */
    public void handleResult(String result) {
        Gson gson = new Gson();
        weather = gson.fromJson(result, HeWeather.class);
        List<Results> resultsList = weather.getResult();
        Results results = resultsList.get(0);//解析结果
        Update update = results.getBasic().getUpdate();
        loc = update.getLoc();
        //实时天气信息
        Now now = results.getNow();
        NowCond cond = now.getCond();
        Wind wind = now.getWind();
        nowWeather = cond.getTxt();
        weatherCode = cond.getCode();
        fl = now.getFl();
        sc = wind.getSc();
        dir = wind.getDir();
        spd = wind.getSpd();
        wet1 = now.getHum();
        pcpn = now.getPcpn();
        nowTemp = now.getTmp();
        mapAddress = "http://files.heweather.com/cond_icon/" + weatherCode + ".png";
        //天气质量信息
        aqi2 = results.getAqi();
        if (aqi2!=null){
            aqiCity = aqi2.getCity();
            aqi1 = aqiCity.getAqi();
            qlty = aqiCity.getQlty();
        }
        //七天天气预报信息
        dailyForecastList = results.getDaily_forecast();
        if (dailyForecastList!=null){
            dayMapCodeList = new ArrayList<>();
            nightMapCodeList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                dayMapCodeList.add(dailyForecastList.get(i).getCond().getCode_d());
            }
            for (int i = 0; i < 7; i++) {
                nightMapCodeList.add(dailyForecastList.get(i).getCond().getCode_n());
            }
        }
    }

    /**
     * 七天天气预报Adapter
     */
    class DailyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dailyForecastList.size();
        }

        @Override
        public DailyForecast getItem(int position) {
            return dailyForecastList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(WeatherActivity.this, R.layout.gridview_item, null);
            TextView week_forecast = (TextView) view.findViewById(R.id.week_forecast);
            TextView forecast_date = (TextView) view.findViewById(R.id.forecast_date);
            TextView wind_forecast = (TextView) view.findViewById(R.id.wind_forecast);
            TextView wind_sp_forecast = (TextView) view.findViewById(R.id.wind_sp_forecast);
            ImageView day_weather_map = (ImageView) view.findViewById(R.id.day_weather_map);
            ImageView night_weather_map = (ImageView) view.findViewById(R.id.night_weather_map);
            DailyForecast dailyForecast = dailyForecastList.get(position);
            Cond cond = dailyForecast.getCond();//天气信息
            Tmp tmp = dailyForecast.getTmp();//温度信息
            Wind wind = dailyForecast.getWind();//风向信息
            //获取七天预报的图片并展示
            day_weather_map.setImageBitmap(dayBitmapArry[position]);
            night_weather_map.setImageBitmap(nightBitmapArry[position]);

            //日期信息
            if (position == 0) {
                week_forecast.setText("今天");
            } else if (position == 1) {
                week_forecast.setText("明天");
            } else {
                int forecastWeekCode = weekCode + position;
                if (forecastWeekCode > 6) {
                    //forecastWeekCode = forecastWeekCode - 7;
                    week_forecast.setText(week.setWeek(forecastWeekCode));
                } else {
                    week_forecast.setText(week.setWeek(forecastWeekCode));
                }

            }
            String date = dailyForecast.getDate();
            String date2 = date.substring(date.length() - 5, date.length());
            forecast_date.setText(date2.replaceFirst("-", "/"));
            //风向信息
            if (wind.getDir().equals("无持续风向")) {
                wind_forecast.setText("");
                wind_forecast.setVisibility(View.INVISIBLE);
            } else {
                wind_forecast.setText(wind.getDir());
            }
            wind_sp_forecast.setText(wind.getSc());
            //温度信息
            tDay[position] = Integer.parseInt(tmp.getMax());
            tNight[position] = Integer.parseInt(tmp.getMin());
            return view;
        }
    }

    class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(WeatherActivity.this, R.layout.viewpager_item, null);
            TextView pager_tv1 = (TextView) view.findViewById(R.id.pager_tv1);
            TextView pager_tv2 = (TextView) view.findViewById(R.id.pager_tv2);
            TextView pager_tv3 = (TextView) view.findViewById(R.id.pager_tv3);
            ImageView pager_ima1 = (ImageView) view.findViewById(R.id.pager_ima1);
            TextView pager_tv1_2 = (TextView) view.findViewById(R.id.pager_tv1_2);
            TextView pager_tv2_2 = (TextView) view.findViewById(R.id.pager_tv2_2);
            TextView pager_tv3_2 = (TextView) view.findViewById(R.id.pager_tv3_2);
            ImageView pager_ima2 = (ImageView) view.findViewById(R.id.pager_ima2);
            TextView pager_tv1_3 = (TextView) view.findViewById(R.id.pager_tv1_3);
            TextView pager_tv2_3 = (TextView) view.findViewById(R.id.pager_tv2_3);
            TextView pager_tv3_3 = (TextView) view.findViewById(R.id.pager_tv3_3);
            ImageView pager_ima3 = (ImageView) view.findViewById(R.id.pager_ima3);
            if (position == 0) {
                pager_tv1.setText("明天");
                pager_tv1_2.setText(week.setWeek(weekCode + 2));
                pager_tv1_3.setText(week.setWeek(weekCode + 3));
                pager_tv2.setText(dailyForecastList.get(1).getTmp().getMin() + "/" +
                        dailyForecastList.get(1).getTmp().getMax() + "°");
                pager_tv2_2.setText(dailyForecastList.get(2).getTmp().getMin() + "/" +
                        dailyForecastList.get(2).getTmp().getMax() + "°");
                pager_tv2_3.setText(dailyForecastList.get(3).getTmp().getMin() + "/" +
                        dailyForecastList.get(3).getTmp().getMax() + "°");
                if (dailyForecastList.get(1).getCond().getTxt_d().equals(
                        dailyForecastList.get(1).getCond().getTxt_n())) {
                    pager_tv3.setText(dailyForecastList.get(1).getCond().getTxt_d());
                } else {
                    pager_tv3.setText(dailyForecastList.get(1).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(1).getCond().getTxt_n());
                }
                if (dailyForecastList.get(2).getCond().getTxt_d().equals(
                        dailyForecastList.get(2).getCond().getTxt_n())) {
                    pager_tv3_2.setText(dailyForecastList.get(2).getCond().getTxt_d());
                } else {
                    pager_tv3_2.setText(dailyForecastList.get(2).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(2).getCond().getTxt_n());
                }
                if (dailyForecastList.get(3).getCond().getTxt_d().equals(
                        dailyForecastList.get(3).getCond().getTxt_n())) {
                    pager_tv3_3.setText(dailyForecastList.get(3).getCond().getTxt_d());
                } else {
                    pager_tv3_3.setText(dailyForecastList.get(3).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(3).getCond().getTxt_n());
                }
                pager_ima1.setImageBitmap(dayBitmapArry[1]);
                pager_ima2.setImageBitmap(dayBitmapArry[2]);
                pager_ima3.setImageBitmap(dayBitmapArry[3]);
            } else {
                pager_tv1.setText(week.setWeek(weekCode + 4));
                pager_tv1_2.setText(week.setWeek(weekCode + 5));
                pager_tv1_3.setText(week.setWeek(weekCode + 6));
                pager_tv2.setText(dailyForecastList.get(4).getTmp().getMin() + "/" +
                        dailyForecastList.get(4).getTmp().getMax() + "°");
                pager_tv2_2.setText(dailyForecastList.get(5).getTmp().getMin() + "/" +
                        dailyForecastList.get(5).getTmp().getMax() + "°");
                pager_tv2_3.setText(dailyForecastList.get(6).getTmp().getMin() + "/" +
                        dailyForecastList.get(6).getTmp().getMax() + "°");
                if (dailyForecastList.get(4).getCond().getTxt_d().equals(
                        dailyForecastList.get(4).getCond().getTxt_n())) {
                    pager_tv3.setText(dailyForecastList.get(4).getCond().getTxt_d());
                } else {
                    pager_tv3.setText(dailyForecastList.get(4).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(4).getCond().getTxt_n());
                }
                if (dailyForecastList.get(5).getCond().getTxt_d().equals(
                        dailyForecastList.get(5).getCond().getTxt_n())) {
                    pager_tv3_2.setText(dailyForecastList.get(5).getCond().getTxt_d());
                } else {
                    pager_tv3_2.setText(dailyForecastList.get(5).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(5).getCond().getTxt_n());
                }
                if (dailyForecastList.get(6).getCond().getTxt_d().equals(
                        dailyForecastList.get(6).getCond().getTxt_n())) {
                    pager_tv3_3.setText(dailyForecastList.get(6).getCond().getTxt_d());
                } else {
                    pager_tv3_3.setText(dailyForecastList.get(6).getCond().getTxt_d() + "转" +
                            dailyForecastList.get(6).getCond().getTxt_n());
                }
                pager_ima1.setImageBitmap(dayBitmapArry[4]);
                pager_ima2.setImageBitmap(dayBitmapArry[5]);
                pager_ima3.setImageBitmap(dayBitmapArry[6]);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
