package com.example.frostmaki.appupdate;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.os.Handler;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class autoupdateManager {
    private static final String PATH = "http://192.168.56.1/autoupdate/version.html";

    private String mVersion_code;
    private String mVersion_name;
    private String mVersion_desc;
    private String mVersion_path;

    private Context mcontext;

    private ProgressBar mProgressBar;
    private Dialog mDownloadDialog;
    private NotificationManager mNotifyManager;
    private Notification notification;
    private NotificationCompat.Builder mBuilder;

    private String mSavePath;
    private int mProgress;
    private int length;

    private boolean mIsCancel = false;

    private static final int DOWNLOADING = 1;
    private static final int DOWNLOAD_FINISH = 2;

    public autoupdateManager(Context context){
        mcontext=context;
    }

    //检测软件是否需要更新
    public void checkUpdate(){
        if (isNetworkConnected(mcontext)==true){

            sendRequestWithHttpURLConnection();
        }else {
            Toast.makeText(mcontext,"网络未连接！！！",Toast.LENGTH_SHORT).show();
        }
    }

    //检测网络是否连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return true;
            }
        }
        return false;
    }



    //对JSON数据进行处理
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            String jsonDate=(String)msg.obj;
            try {
                JSONObject jsonObject = new JSONObject(jsonDate);
                Log.e("json内容",jsonObject.toString());
                mVersion_code = jsonObject.getString("version_code");
                mVersion_name = jsonObject.getString("version_name");
                mVersion_desc = jsonObject.getString("version_desc");
                mVersion_path = jsonObject.getString("version_path");

                if (isUpdate()) {
                    Toast.makeText(mcontext, "需要更新", Toast.LENGTH_SHORT).show();

                    // 显示提示更新对话框
                    showNoticDialog();

                } else {
                    Toast.makeText(mcontext, "已是最新版本", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //与本地版本比较是否更新
    public boolean isUpdate(){
        int serverVersion = Integer.parseInt(mVersion_code);
        int localVersion = 1;

        try {
            localVersion = mcontext.getPackageManager().getPackageInfo
                    ("com.example.frostmaki.appupdate", 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (serverVersion > localVersion)
            return true;
        else
            return false;

    }

    //从服务器获得JSON数据,
    public void sendRequestWithHttpURLConnection(){

        new Thread(new Runnable(){

            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL realURL=new URL(PATH);
                    connection=(HttpURLConnection)realURL.openConnection();

                    InputStreamReader in = new InputStreamReader(connection.getInputStream());

                    //从Internet获取网页,发送请求,将网页以流的形式读回来
                    if (connection.getResponseCode() != 200)
                        throw new RuntimeException("请求url失败");

                    BufferedReader buffer=new BufferedReader(in);
                    String inputLine=null;
                    String result="";
                    while(((inputLine=buffer.readLine())!=null)){
                        result+=inputLine+"\n";
                    }
                    in.close();


                    Message message=new Message();
                    message.obj=result.toString();
                    handler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    //有更新时显示提示更新对话框
    public void showNoticDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("提示");
        String message = "软件有更新，要下载安装吗？\n" + mVersion_desc;
        builder.setMessage(message);

        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
                //显示下载通知
                showNotification();
            }
        });

        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    //更新时显示正在下载对话框
    public void showDownloadDialog(){


        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("下载中");
        View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_progress, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 设置下载状态为取消
                mIsCancel = true;
                mBuilder.setContentTitle("下载已取消").setProgress(0, 0, false);
                mNotifyManager.notify(1, mBuilder.build());
            }
        });
        builder.setPositiveButton("隐藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 下载数据等（notification的显示）

            }
        });

        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        // 下载文件
        downloadAPK();

    }

    //// 下载数据等（notification的显示）
    public void showNotification(){
        mNotifyManager =
                (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
        //notification=new Notification(R.drawable.ic_launcher,"your notification",System.currentTimeMillis());
        mBuilder = new NotificationCompat.Builder(mcontext);
        mBuilder.setContentTitle("测试标题")//设置通知栏标题
                .setContentText("测试内容")
                .setProgress(0,0,true);
        mNotifyManager.notify(1, mBuilder.build());
    }
    /** 设置下载进度 */
    public void setNotify(int progress) {
        mBuilder.setProgress(100, progress, false); // 这个方法是显示进度条
        mNotifyManager.notify(1, mBuilder.build());
    }
    //开启新线程下载文件
    public void downloadAPK(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
                        mSavePath = sdPath + "appupdatedownload";

                        File dir = new File(mSavePath);
                        if (!dir.exists())
                            dir.mkdir();

                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(mVersion_path).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        length = conn.getContentLength();

                        File apkFile = new File(mSavePath, mVersion_name);
                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        while (!mIsCancel){
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float)count/length) * 100);
                            setNotify(mProgress);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(DOWNLOADING);

                            // 下载完成
                            if (numread < 0){
                                mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //下载到本地执行安装
    public void installAPK(){
        File apkFile = new File(mSavePath, mVersion_name);
        if (!apkFile.exists())
            return;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + apkFile.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        mcontext.startActivity(intent);
    }
    private Handler mUpdateProgressHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:

                    // 设置进度条
                    mProgressBar.setProgress(mProgress);

                    break;
                case DOWNLOAD_FINISH:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();

                    // 安装 APK 文件
                    installAPK();

            }
        }
    };
}
