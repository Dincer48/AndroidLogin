package com.example.asus.loginactivity;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG="DatabaseHelper";

    private static final String TABLE_NAME = "texts_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "DATA";

    public DatabaseHelper(Context context){super(context, TABLE_NAME, null,1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(String item){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,item );
        Log.d(TAG, "addData: Adding" + item + "to" + TABLE_NAME);
        long result=db.insert(TABLE_NAME,null,contentValues);

        return result !=-1;
    }
    public Cursor getData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM " + TABLE_NAME,null );
        return data;
    }
    public int getLastId(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM " + TABLE_NAME + "ORDER BY ID DESC LIMIT 1",null);
        data.moveToNext();
        return data.getInt(0);
    }
    public boolean deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COL1 + " = ?", new String[] {String.valueOf(id)});
        return result != -1;
    }
    public void resetTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

}
