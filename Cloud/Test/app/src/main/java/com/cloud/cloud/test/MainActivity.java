package com.cloud.cloud.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<Person>mPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        try {
            mPersons=PersonService.getPerson(this);
            mListView.setAdapter(new PersonAdapter());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    class PersonAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mPersons.size();
        }

        @Override
        public Object getItem(int position) {
            return mPersons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person person=mPersons.get(position);
            View itemView;
            LayoutInflater inflater=MainActivity.this.getLayoutInflater();
            if(convertView==null)
            {
                Log.i("hyrz","调用了"+position+"次");
                itemView = View.inflate(MainActivity.this, R.layout.item, null);
            }
            else
            {
                itemView=convertView;
            }
            TextView tvID = (TextView) itemView.findViewById(R.id.pid);
            TextView tvName = (TextView) itemView.findViewById(R.id.name);
            TextView tvAge = (TextView) itemView.findViewById(R.id.age);
            tvID.setText(person.getId()+"");
            tvName.setText(person.getName());
            tvAge.setText(person.getAge()+"");
            return itemView;
        }
    }
}
