package com.example.frostmaki.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_create;
    private Button button_add;
    private Button button_update;
    private Button button_delete;
    private Button button_query;
    private SqlitedatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new SqlitedatabaseHelper(this,"Bookstore.db",null,2);
        button_create=(Button)findViewById(R.id.firstcreate);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getReadableDatabase();
            }
        });

        button_add=(Button)findViewById(R.id.add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                ContentValues values=new ContentValues();
                //第一条数据
                values.put("name","How to learn SQLite");
                values.put("author","Tom");
                values.put("pages", "100");
                values.put("price", "59.5");
                db.insert("Book", null, values);
                values.clear();
                //第二条数据
                values.put("name","Effective SQLite");
                values.put("author","Richard");
                values.put("pages","68");
                values.put("price", "34.6");
                db.insert("Book", null, values);
                values.clear();
                //第三条数据
                values.put("name","SQLite Prime Plus");
                values.put("author","Lee");
                values.put("pages","500");
                values.put("price","78.6");
                db.insert("Book", null, values);
                values.clear();
                Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
            }
        });

        button_update=(Button)findViewById(R.id.update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",40.99);
                db.update("Book", values, "name=?", new String[]{"Effective SQLite"});
                values.clear();

                values.put("name", "Effective Android");
                db.update("Book",values,"price=?",new String[]{"40.99"});
                values.clear();
            }
        });

        button_delete=(Button)findViewById(R.id.delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                db.delete("Book", "pages=?", new String[]{"68"});
            }
        });

        button_query=(Button)findViewById(R.id.query);
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();

                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name =cursor.getString(cursor.getColumnIndex("name"));
                        String author =cursor.getString(cursor.getColumnIndex("author"));
                        int pages =cursor.getInt(cursor.getColumnIndex("pages"));
                        double price =cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.e("MainActivity","book name is   "+name);
                        Log.e("MainActivity","book author is "+author);
                        Log.e("MainActivity","book pages is  "+pages);
                        Log.e("MainActivity","book price is  "+price);
                    }while (cursor.moveToNext());
                }
            }
        });
    }
}
