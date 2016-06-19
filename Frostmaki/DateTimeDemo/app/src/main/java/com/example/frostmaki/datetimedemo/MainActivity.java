package com.example.frostmaki.datetimedemo;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



    public class MainActivity extends AppCompatActivity {
        public Button btnClick;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnClick = (Button) findViewById(R.id.btnclick);
            btnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });

        }

        public void showDialog(){
            FragmentManager fm=getFragmentManager();
            Date_Time_Fragment dateTimeFragment =new Date_Time_Fragment();
            dateTimeFragment.show(fm,"fragment_date_time");
        }

    }

