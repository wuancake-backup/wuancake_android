package com.example.frostmaki.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Frostmaki on 2016/4/28.
 */
public class MyAdapter extends BaseAdapter{
    private Context mcontext;
    private LayoutInflater inflater;
    //private ViewHolder viewHolder;

    public MyAdapter(Context context){
        this.mcontext=context;
        inflater=LayoutInflater.from(mcontext);
}

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int selectView=getItemViewType(position);

        if(convertView==null) {
            switch (selectView) {
                case 0:
                    convertView = inflater.inflate(R.layout.list_layout_0, parent, false);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.list_layout_1, parent, false);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.list_layout_2, parent, false);
                    break;

            }

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        int id =position % 3;
        switch (id){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}

