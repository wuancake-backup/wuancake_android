package com.example.frostmaki.drawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        listView = (ListView) findViewById(R.id.list);

        final String[] arr_data = {"首页", "我的星球", "全部星球", "登录", "注册"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String TEXT=parent.getItemAtPosition(position).toString();
        Toast.makeText(MainActivity.this,"You Click:"+TEXT,Toast.LENGTH_SHORT).show();
        Fragment contentFragment =new Relative_content();
        Bundle args=new Bundle();
        args.putString("Text",TEXT);
        contentFragment.setArguments(args);

        FragmentManager fm=getFragmentManager();
        fm.beginTransaction().replace(R.id.main_layout,contentFragment).commit();

        drawerLayout.closeDrawer(listView);
    }
}