package com.example.frostmaki.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;


public class ListViewAdapter extends BaseAdapter{
    private Context mcontext;
    List<Map<String, Integer>> mdata;

    public ListViewAdapter(Context context,List<Map<String, Integer>> data){
        mcontext=context;
        mdata=data;
    }

    @Override
    public int getCount() {
        return mdata.size();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.list_item, parent, false);
            viewHolder.imageViewIcon = (ImageView) convertView.findViewById(R.id.image_view_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageViewIcon.setImageResource(mdata.get(position).get("icon"));
        convertView.setBackgroundResource(mdata.get(position).get("color"));

        return convertView;
    }

    class ViewHolder {
        ImageView imageViewIcon;
    }
}
