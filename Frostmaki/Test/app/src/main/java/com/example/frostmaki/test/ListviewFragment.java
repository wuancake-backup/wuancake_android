package com.example.frostmaki.test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Frostmaki on 2016/6/26.
 */
public class ListviewFragment extends Fragment {
    private ListView mlistView;
    private List<Map<String, Integer>> mSampleList;
    private Map<String, Integer> map;
    private ListViewAdapter listViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_list_view, container, false);
        initlist();
        mlistView=(ListView)view.findViewById(R.id.list_view);
        listViewAdapter=new ListViewAdapter(MyApplication.getContext(),mSampleList);
        mlistView.setAdapter(listViewAdapter);
        return view;
    }

    private void initlist(){
        mSampleList = new ArrayList<>();
        int[] icons = {
                R.drawable.icon_1,
                R.drawable.icon_2,
                R.drawable.icon_3};

        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};
        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put("icon", icons[i]);
            map.put("color", colors[i]);
            mSampleList.add(map);
        }
    }
}

