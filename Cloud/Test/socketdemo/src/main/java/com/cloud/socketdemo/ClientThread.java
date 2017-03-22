package com.cloud.socketdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Cloud on 2017/3/21.
 */

public class ClientThread implements Runnable {
    private Handler mHandler;       //定义向UI线程发送消息的Handle对象
    private Socket s;
    public Handler revHandler;     //定义接收UI线程的消息的Handler对象
    BufferedReader br;
    OutputStream os;
    public ClientThread(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        try {
            s = new Socket("192.168.8.107", 30000);
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            os=s.getOutputStream();
            //启动一个子线程来读取服务器响应的数据
            new Thread(){
                @Override
                public void run() {
                    String content=null;
                    try {
                        //不断的读取socket输入流中的内容
                        while ((content=br.readLine())!=null)
                        {
                            Message msg=new Message();
                            msg.what=0x123;
                            msg.obj=content;
                            mHandler.sendMessage(msg);
                        }
                        super.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            //为当前线程初始化Looper
            Looper.prepare();
            //接收ui线程用户输入的数据
            revHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==0x345)
                    {
                        try {
                            os.write((msg.obj.toString()+"\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
