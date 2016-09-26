package db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class Province {

    /**
     * @param filePath
     * @return
     * 获取数据库中省份名称列表
     */
    public List<String> getProvinceNameList(String filePath){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("Province",null,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("ProName")));
            }while (cursor.moveToNext());
        }
        return  list;
    }

    /**
     * @param filePath
     * @return
     * 获取数据库中省份Id列表
     */
    public List<String> getProvinceIdList(String filePath){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("Province",null,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("ProID")));
            }while (cursor.moveToNext());
        }
        return  list;
    }


}
