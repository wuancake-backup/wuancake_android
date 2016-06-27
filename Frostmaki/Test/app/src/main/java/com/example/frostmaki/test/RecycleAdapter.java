package com.example.frostmaki.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

/**
 * Created by Frostmaki on 2016/6/26.
 */
public class RecycleAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<Map<String, Integer>> mdata;
    private Context mcontext;
    private LayoutInflater inflater;
    public RecycleAdapter(Context context,List<Map<String, Integer>> data){
        this.mcontext=context;
        this.mdata=data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setImageResource(mdata.get(position).get("icon"));
        holder.mRootView.setBackgroundResource(mdata.get(position).get("color"));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    View mRootView;
    public MyViewHolder(View itemView) {
        super(itemView);

        image=(ImageView)itemView.findViewById(R.id.image_view_icon);
        mRootView=itemView;
    }
}

