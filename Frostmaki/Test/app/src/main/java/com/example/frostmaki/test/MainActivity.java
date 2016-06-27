package com.example.frostmaki.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment listViewFragment;
    private Fragment recycleFragment;
    private TextView txt_listView;
    private TextView txt_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        setselect(1);
        txt_recycle.setBackgroundColor(getResources().getColor(R.color.eggplant));
    }
    public void initView(){

        txt_listView=(TextView)findViewById(R.id.tab_listView);
        txt_recycle=(TextView)findViewById(R.id.tab_recycler);
    }

    private void setselect(int i){
        FragmentManager fm =getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();

        hideFragment(transaction);

        switch (i){
            case 0:
                if(listViewFragment==null){
                    listViewFragment=new ListviewFragment();
                    transaction.add(R.id.frame,listViewFragment);
                }else{
                    transaction.show(listViewFragment);
                }
                break;
            case 1:
                if(recycleFragment==null){
                    recycleFragment=new RecycleViewFragment();
                    transaction.add(R.id.frame,recycleFragment);
                }else{
                    transaction.show(recycleFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(listViewFragment!=null){
            transaction.hide(listViewFragment);
        }
        if(recycleFragment!=null){
            transaction.hide(recycleFragment);
        }
    }

    private void initEvent(){
        txt_listView.setOnClickListener(this);
        txt_recycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_listView :
                Toast.makeText(this,"listview click",Toast.LENGTH_SHORT).show();
                txt_listView.setBackgroundColor(getResources().getColor(R.color.saffron));
                txt_recycle.setBackgroundColor(getResources().getColor(R.color.black));
                setselect(0);
                break;
            case R.id.tab_recycler:
                Toast.makeText(this,"recyclerview click",Toast.LENGTH_SHORT).show();
                txt_recycle.setBackgroundColor(getResources().getColor(R.color.eggplant));
                txt_listView.setBackgroundColor(getResources().getColor(R.color.black));
                setselect(1);
                break;
            default:
                break;
        }
    }
}