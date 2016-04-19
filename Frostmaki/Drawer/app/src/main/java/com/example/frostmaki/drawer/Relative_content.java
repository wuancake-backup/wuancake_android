package com.example.frostmaki.drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by miaoyu on 2016/4/19.
 */
public class Relative_content extends Fragment {
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.relative_layout,null);
        textView= (TextView) view.findViewById(R.id.relative_text);
        String text=getArguments().getString("Text");
        textView.setText(text);
        return view;
    }


}
