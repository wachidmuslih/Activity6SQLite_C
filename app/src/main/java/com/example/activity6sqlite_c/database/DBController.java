package com.example.activity6sqlite_c.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

//membuat pengendali database --> sqlopenelper
public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    //ddl
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teman (id omterger primary key, nama text, telepon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists teman");
        onCreate(db);
    }

    //mdl
    public void  insertData(HashMap<String, String> QueryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama", queryValue.get("nama"));
        nilai.put("telpon", queryVlaue.get("telpon"));
        basisdata.insert("teman", null, nilai);
        basisdata.close();
    }
}
