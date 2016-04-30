package com.example.frostmaki.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> datalist;
    //private ViewHolder viewHolder;

    public MyAdapter(Context context,ArrayList<String> datalist){
        inflater=LayoutInflater.from(context);
        this.datalist=datalist;
}

    @Override
    public int getCount() {
        return 20;
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

        ViewHolder0 viewHolder0;
        ViewHolder1 viewHolder1;
        ViewHolder2 viewHolder2;


            switch (selectView) {
                case 0:
                    if(convertView==null) {
                        viewHolder0 = new ViewHolder0();
                        convertView = inflater.inflate(R.layout.list_layout_0, parent, false);
                        viewHolder0.viewHolder_textView_0 = (TextView) convertView.findViewById(R.id.text_0);
                        viewHolder0.viewHolder_textView_0_1 = (TextView) convertView.findViewById(R.id.text_0_1);
                        viewHolder0.viewHolder_imageView_0=(ImageView)convertView.findViewById(R.id.imag_0);

                        convertView.setTag(viewHolder0);
                    }else {
                        viewHolder0 = (ViewHolder0) convertView.getTag();
                    }
                    viewHolder0.viewHolder_textView_0.setText("text_0 载入成功");
                    viewHolder0.viewHolder_textView_0_1.setText("内容:"+datalist.get(position));
                    viewHolder0.viewHolder_imageView_0.setImageResource(R.drawable.ic_launcher);
                    break;
                case 1:
                    if(convertView==null) {
                        viewHolder1 = new ViewHolder1();
                        convertView = inflater.inflate(R.layout.list_layout_1, parent, false);
                        viewHolder1.viewHolder_textView_1 = (TextView) convertView.findViewById(R.id.text_1);
                        viewHolder1.viewHolder_textView_1_1 = (TextView) convertView.findViewById(R.id.text_1_1);
                        viewHolder1.viewHolder_imageView_1_0=(ImageView)convertView.findViewById(R.id.imag_1_0);
                        viewHolder1.viewHolder_imageView_1_1=(ImageView)convertView.findViewById(R.id.imag_1_1);

                        convertView.setTag(viewHolder1);
                    }else{
                        viewHolder1 = (ViewHolder1) convertView.getTag();
                    }
                    viewHolder1.viewHolder_textView_1.setText("text_1 载入成功");
                    viewHolder1.viewHolder_textView_1_1.setText("内容:"+datalist.get(position));
                    viewHolder1.viewHolder_imageView_1_0.setImageResource(R.drawable.ic_launcher);
                    viewHolder1.viewHolder_imageView_1_1.setImageResource(R.drawable.ic_launcher);
                    break;
                case 2:
                    if(convertView==null) {
                        viewHolder2 = new ViewHolder2();
                        convertView = inflater.inflate(R.layout.list_layout_2, parent, false);
                        viewHolder2.viewHolder_textView_2 = (TextView) convertView.findViewById(R.id.text_2);
                        viewHolder2.viewHolder_textView_2_1 = (TextView) convertView.findViewById(R.id.text_2_1);
                        viewHolder2.viewHolder_imageView_2_0=(ImageView)convertView.findViewById(R.id.imag_2_0);
                        viewHolder2.viewHolder_imageView_2_1=(ImageView)convertView.findViewById(R.id.imag_2_1);
                        viewHolder2.viewHolder_imageView_2_2=(ImageView)convertView.findViewById(R.id.imag_2_2);

                        convertView.setTag(viewHolder2);
                    }else{
                        viewHolder2 = (ViewHolder2) convertView.getTag();
                    }
                    viewHolder2.viewHolder_textView_2.setText("text_2 载入成功");
                    viewHolder2.viewHolder_textView_2_1.setText("内容:"+datalist.get(position));
                    viewHolder2.viewHolder_imageView_2_0.setImageResource(R.drawable.ic_launcher);
                    viewHolder2.viewHolder_imageView_2_1.setImageResource(R.drawable.ic_launcher);
                    viewHolder2.viewHolder_imageView_2_2.setImageResource(R.drawable.ic_launcher);
                    break;

            }


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        int id =position % 3;
            if(id==0){
                return 0;
            }else if(id==1){
                return 1;
            }else if(id==2){
                return 2;
            }else{
                return 0;
            }
        }



    @Override
    public int getViewTypeCount() {
        return 3;
    }


    class ViewHolder0{
        TextView viewHolder_textView_0;
        TextView viewHolder_textView_0_1;
        ImageView viewHolder_imageView_0;
    }
    class ViewHolder1{
        TextView viewHolder_textView_1;
        TextView viewHolder_textView_1_1;
        ImageView viewHolder_imageView_1_0;
        ImageView viewHolder_imageView_1_1;
    }
    class ViewHolder2{
        TextView viewHolder_textView_2;
        TextView viewHolder_textView_2_1;
        ImageView viewHolder_imageView_2_0;
        ImageView viewHolder_imageView_2_1;
        ImageView viewHolder_imageView_2_2;
    }
}

