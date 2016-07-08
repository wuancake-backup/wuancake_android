package com.example.frostmaki.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Frostmaki on 2016/6/26.
 */
public class RecycleViewFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private List<Map<String, Integer>> mSampleList;
    private Map<String, Integer> map;
    private RecycleAdapter rAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragement_recycler_view,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        initlist();

        rAdapter=new RecycleAdapter(MyApplication.getContext(),mSampleList);
        mRecyclerView.setAdapter(rAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyApplication.getContext(),
                LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    private void initlist() {
        mSampleList = new ArrayList<>();
        int[] icons = {
                R.drawable.icon_1,
                R.drawable.icon_2,
                R.drawable.icon_3};

        int[] colors = {
                R.color.eggplant,
                R.color.saffron,
                R.color.sienna};
        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put("icon", icons[i]);
            map.put("color", colors[i]);
            mSampleList.add(map);
        }
    }
}
