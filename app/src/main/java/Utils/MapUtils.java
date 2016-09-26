package Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/13.
 */
public class MapUtils {
    public static void getBitmap(final String mapAddress, final HttpCallBackListener listener, final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {

                    URL url = new URL(mapAddress);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    if (listener != null) { // 回调onFinish()方法
                        listener.onSucceed(bitmap,i);
                    }
                } catch (Exception e) {

                    if (listener != null) { // 回调onError()方法
                        listener.onFail(e);
                    }
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public interface HttpCallBackListener {
        void onSucceed(Bitmap bitmap,int i);
        void onFail(Exception e);
    }
}
