package db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class County {
    public List<String> getCountyNameList(String filePath, String cityID){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        if (cityID == null){
            Cursor cursor = sqLiteDatabase.query("County",null,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CountyName")));
                }while (cursor.moveToNext());
            }
        }else {
            Cursor cursor = sqLiteDatabase.query(
                    "County",null,"CityID = ?",new String[]{cityID},null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CountyName")));
                }while (cursor.moveToNext());
            }
        }

        return  list;
    }

    public List<String> getCountyIdList(String filePath,String cityID){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        if (cityID == null){
            Cursor cursor = sqLiteDatabase.query("County",null,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CountyID")));
                }while (cursor.moveToNext());
            }
        }else{
            Cursor cursor = sqLiteDatabase.query(
                    "County",null,"CityID = ?",new String[]{ cityID },null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("CountyID")));
                }while (cursor.moveToNext());
            }
        }


        return  list;
    }
    public List<String> getWeatherIdList(String filePath,String cityID){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        List<String> list = new ArrayList<>();
        if (cityID == null){
            Cursor cursor = sqLiteDatabase.query("County",null,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("WeatherID")));
                }while (cursor.moveToNext());
            }
        }else{
            Cursor cursor = sqLiteDatabase.query(
                    "County",null,"CityID = ?",new String[]{cityID},null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("WeatherID")));
                }while (cursor.moveToNext());
            }
        }
        return  list;
    }

    /**
     * @param filePath
     * @param countyName
     * @return
     * 根据县区名字查询天气ID
     */
    public String getWeatherId(String filePath,String countyName){
        SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase(filePath,null);
        String weatherID = new String();
            Cursor cursor = sqLiteDatabase.query(
                    "County",null,"CountyName = ?",new String[]{countyName},null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                weatherID = cursor.getString(cursor.getColumnIndex("WeatherID"));
            }while (cursor.moveToNext());
        }
        return  weatherID;
    }
}
