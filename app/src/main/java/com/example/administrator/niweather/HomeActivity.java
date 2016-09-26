package com.example.administrator.niweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Utils.SpUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import db.City;
import db.County;
import db.Province;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.selected_province)
    TextView selectedProvince;
    @BindView(R.id.selected_city)
    TextView selectedCity;
    @BindView(R.id.selected_county)
    TextView selectedCounty;
    @BindView(R.id.select_province_list)
    ListView selectProvinceList;
    @BindView(R.id.select_city_list)
    ListView selectCityList;
    @BindView(R.id.select_county_list)
    ListView selectCountyList;
    @BindView(R.id.select_title)
    TextView selectTitle;
    /**
     * 省份、城市、县列表
     */
    private List<String> provinceIdList = new ArrayList<>();
    private List<String> cityIdList = new ArrayList<>();
    private List<String> countyIdList = new ArrayList<>();
    private List<String> weatherIdList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private String location;
    /**
     * 数据库地址
     */
    private String file;
    /**
     * 省、市、县ID、查询天气ID
     */
    private String weatherId;
    private String selectedProvinceId;
    private String selectedCityId;
    private String selectedCountyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        file = getFilesDir() + "\\databases\\" + "a1.db";
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        selectProvince();
    }

    /**
     * 选择省份
     */
    public void selectProvince() {
        Province province = new Province();
        dataList = province.getProvinceNameList(file);
        provinceIdList = province.getProvinceIdList(file);
        selectProvinceList.setAdapter(new SelectLocationAdapter());
        selectProvinceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProvinceId = provinceIdList.get(position);
                selectedProvince.setText(dataList.get(position));
                selectedProvince.setVisibility(View.VISIBLE);
                selectProvinceList.setVisibility(View.INVISIBLE);
                selectCity();
            }
        });
    }

    /**
     * 选择城市
     */
    public void selectCity() {
        selectTitle.setText(R.string.select_city);
        City city = new City();
        dataList = city.getCityNameList(file, selectedProvinceId);
        cityIdList = city.getCityIdList(file, selectedProvinceId);
        selectCityList.setAdapter(new SelectLocationAdapter());
        selectCityList.setVisibility(View.VISIBLE);
        selectCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCityId = cityIdList.get(position);
                selectedCity.setText(dataList.get(position));
                selectedCity.setVisibility(View.VISIBLE);
                selectCityList.setVisibility(View.INVISIBLE);
                selectCounty();
            }
        });
    }

    /**
     * 选择县区
     */
    public void selectCounty() {
        selectTitle.setText(R.string.select_county);
        County county = new County();
        dataList = county.getCountyNameList(file, selectedCityId);
        countyIdList = county.getCountyIdList(file, selectedCityId);
        weatherIdList = county.getWeatherIdList(file,selectedCityId);
        selectCountyList.setAdapter(new SelectLocationAdapter());
        selectCountyList.setVisibility(View.VISIBLE);
        selectCountyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCountyId = countyIdList.get(position);
                selectedCounty.setText(dataList.get(position));
                selectedCounty.setVisibility(View.VISIBLE);
                location = dataList.get(position);
                weatherId = weatherIdList.get(position);
                SpUtils.putString(HomeActivity.this, "weatherID",null);
                SpUtils.putString(HomeActivity.this, "location", null);
                enterWeather();
            }
        });
    }

    /**
     * 进入天气预报
     */
    private void enterWeather() {
        Intent intent = new Intent(HomeActivity.this, WeatherActivity.class);
        intent.putExtra("weatherID",weatherId);
        intent.putExtra("location",location);
        startActivity(intent);
        finish();
    }

    /**
     * 选择列表的适配器
     */
    class SelectLocationAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(HomeActivity.this, R.layout.location_listview, null);
            TextView location = (TextView) view.findViewById(R.id.location);
            location.setText(dataList.get(position));
            return view;
        }
    }

}
