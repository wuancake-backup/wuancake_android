package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SpUtils {
    private static SharedPreferences sp;

    public static void putBoolean(Context context,String key,boolean defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,defValue).commit();

    }
    public static boolean getBoolean(Context context,String key,boolean defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
    public static void putString(Context context, String key, String defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,defValue).commit();
    }
    public static String getString(Context context,String key,String defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }
    public static void putInt(Context context,String key,int defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,defValue).commit();
    }
    public static int getInt(Context context,String key,int defValue ) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }
}
