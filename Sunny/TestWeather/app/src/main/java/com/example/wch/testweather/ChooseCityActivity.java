package com.example.wch.testweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by WCH on 2017/2/20.
 */

public class ChooseCityActivity extends Activity implements View.OnClickListener{
    final static int[] cityIDArray = {
            R.array.beijing_array,
            R.array.tianjin_array,
            R.array.shanghai_array,
            R.array.chongqing_array,
            R.array.xianggang_array,
            R.array.aomen_array,
            R.array.liaoning_array,
            R.array.heilongjiang_array,
            R.array.jilin_array,
            R.array.neimenggu_array,
            R.array.xinjiang_array,
            R.array.qinghai_array,
            R.array.xizang_array,
            R.array.gansu_array,
            R.array.ningxia_array,
            R.array.shan3xi_array,
            R.array.shan1xi_array,
            R.array.hebei_array,
            R.array.shandong_array,
            R.array.jiangsu_array,
            R.array.anhui_array,
            R.array.henan_array,
            R.array.hubei_array,
            R.array.sichuan_array,
            R.array.yunnan_array,
            R.array.guizhou_array,
            R.array.hunan_array,
            R.array.jiangxi_array,
            R.array.zhejiang_array,
            R.array.fujian_array,
            R.array.guangdong_array,
            R.array.guangxi_array,
            R.array.hainan_array,
            R.array.taiwan_array,
    };
    private Spinner provinceSpinner;
    private Spinner citySpinner;
    private Button saveBn;
    private Button cancelBn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecity);
        getCityArrayID();
        findView();
        spinner();
    }

    private void spinner() {
        // 初始化下拉列表
        // 创建适配器
		/*
		 * ArrayAdapter<CharSequence> createFromResource(Context context, int
		 * textArrayResId, int textViewResId)
		 *  参数:
		 *  context：下拉列表对象所在的界面对象
		 * textArrayResId：在res/values/arrays.xml定义的一组数据 textViewResId：下拉列表的显示风格
		 * 1)android.R.layout.simple_spinner_dropdown_item
		 * 2)android.R.layout.simple_spinner_item
		 */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.provinces,android.R.layout.simple_spinner_item);
        provinceSpinner.setAdapter(adapter);

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position != 0){//选择了省份
					/*这是一个小技巧
					*0x7f050000为R文件中省份数组对应的id值，只要加上position，即可获得对应选项（省份）的城市
					*如果不是用这个方法，可能就要用一大堆的判断语句来级联城市
					*/
                    int cityID = findCiytIDFromIndex(position);
//                    int cityID = R.array.provinces + position;
//                    getResources().getStringArray(R.array.anhui_array);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ChooseCityActivity.this, cityID, android.R.layout.simple_spinner_item);
                    citySpinner.setAdapter(adapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private int findCiytIDFromIndex(int position) {
        return cityIDArray[position-1];
    }

    private void findView() {
        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        saveBn = (Button) findViewById(R.id.saveBn);
        cancelBn = (Button) findViewById(R.id.cancelBn);

        saveBn.setOnClickListener(this);
        cancelBn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveBn) {// 保存选择城市，并且返回上一界面，并发送选择的城市
            if(citySpinner.getSelectedItem()!=null){
                String city = citySpinner.getSelectedItem().toString();
//                SQLiteDao sqliteDao = new SQLiteDao(this);
//                sqliteDao.insert(city);
                Intent intent = new Intent();
                intent.putExtra("selectedCity",city);
                setResult(0,intent);
                ChooseCityActivity.this.finish();
            }else{//如果没有选择城市
                Toast.makeText(getApplicationContext(), R.string.choosecity, Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.cancelBn) {// 返回上一界面，相当于点击返回键
            ChooseCityActivity.this.finish();
        }
    }

    public Object getCityArrayID() {
        getResources().getStringArray(R.array.anhui_array);
        return null;
    }
}
