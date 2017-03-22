package com.cloud.socketdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mBtnSend;        //发送按钮
    private TextView mTvShow;       //显示服务器发送的消息
    Handler mHandler;
    ClientThread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=(EditText) findViewById(R.id.et_mes);
        mBtnSend=(Button) findViewById(R.id.btn_send);
        mTvShow=(TextView) findViewById(R.id.show);
        //更新服务器消息
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123)
                {
                    mTvShow.append("\n"+msg.obj.toString());
                }
                super.handleMessage(msg);
            }
        };
        mThread=new ClientThread(mHandler);
        //客户端启动线程读取来自网络的数据
        new Thread(mThread).start();
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg=new Message();
                msg.what=0x345;
                msg.obj=mEditText.getText().toString();
                mThread.revHandler.sendMessage(msg);
                mEditText.setText("");  //清空
            }
        });
    }
}
