package com.example.lenovo.yuekao1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/9/21.
 */

public class Citydao {
    private MySqlite helper;
    private String json;

    Citydao(Context context)
    {
        helper=new MySqlite(context);
    }

    public void insert(String json)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("json",json);
        db.insert("city",null,values);
    }
    public String query()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("city",null, null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            json = cursor.getString(cursor.getColumnIndex("json"));
        }
        return json;
    }
}
