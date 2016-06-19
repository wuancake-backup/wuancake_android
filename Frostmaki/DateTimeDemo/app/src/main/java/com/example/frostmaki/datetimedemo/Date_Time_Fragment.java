package com.example.frostmaki.datetimedemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frostmaki on 2016/6/19.
 */
public class Date_Time_Fragment extends DialogFragment implements View.OnClickListener {
    public DatePicker datePicker;
    public TimePicker timePicker;

    public ViewPager mViewPager;
    public PagerAdapter mPageAdapter;
    public List<View> mViews=new ArrayList<View>();

    public TextView mdateText;
    public TextView mtimeText;

    public View view;

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initView();
        initEvents();

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        //datePicker=(DatePicker)view.findViewById(R.id.datepicker);
        //timePicker=(TimePicker)view.findViewById(R.id.timepicker);

        //datePicker.init(2016, 12, 31, null);

        //timePicker.setIs24HourView(true);

        builder.setView(view);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "OK clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog=builder.create();

        return dialog;
    }


    public void initView(){
        view=getActivity().getLayoutInflater().inflate(R.layout.date_time,null);
        mViewPager=(ViewPager)view.findViewById(R.id.main_content);
        mdateText=(TextView)view.findViewById(R.id.dateText);
        mtimeText=(TextView)view.findViewById(R.id.timeText);

        LayoutInflater mInflater = LayoutInflater.from(MyApplication.getContext());
        View dateView=mInflater.inflate(R.layout.datepicker, null);
        View timeView=mInflater.inflate(R.layout.timepicker,null);

        mViews.add(dateView);
        mViews.add(timeView);

        mPageAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        mViewPager.setAdapter(mPageAdapter);
    }

    public void initEvents(){
        mdateText.setOnClickListener(this);
        mtimeText.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dateText:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.timeText:
                mViewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}
