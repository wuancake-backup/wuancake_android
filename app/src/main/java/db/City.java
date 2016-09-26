package db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class City {
    /**
     * @param filePath
     * @return
     * 获取数据库中城市名称列表
     */
    public List<String> getCityNameList(String filePath,String proId){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        if (proId == null){
            Cursor cursor = sqLiteDatabase.query("City",null,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CityName")));
                }while (cursor.moveToNext());
            }
        }else {
            Cursor cursor = sqLiteDatabase.query(
                    "City",null,"ProID = ?",new String[]{proId},null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CityName")));
                }while (cursor.moveToNext());
            }
        }

        return  list;
    }

    /**
     * @param filePath
     * @return
     * 获取数据库中城市Id列表
     */
    public List<String> getCityIdList(String filePath,String proId){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        if (proId == null){
        Cursor cursor = sqLiteDatabase.query("City",null,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex("CityID")));
            }while (cursor.moveToNext());
        }
        }else{
            Cursor cursor = sqLiteDatabase.query(
                    "City",null,"ProID = ?",new String[]{ proId },null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CityID")));
                }while (cursor.moveToNext());
            }
        }
        return  list;
    }
}

