package studio.smartters.jewellary.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import studio.smartters.jewellary.pojo.DbItem;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "demo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table items(id text primary key,url text,gos text,type text,original_name text,sold text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public boolean addFav (DbItem data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", data.getId());
        contentValues.put("url", data.getUrl());
        contentValues.put("gos", data.getGos());
        contentValues.put("type", data.getName());
        contentValues.put("original_name", data.getOriginalName());
        contentValues.put("sold", data.getSold());
        Log.e("data",data.getGos());
        return db.insert("items", null, contentValues)>0;
    }
    public boolean removeFav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete("items","id=?",new String[]{id})>0;
    }
    public boolean isFav(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items where id=?", new String[]{id} );
        return res.getCount() > 0;
    }
    public   int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items ",null );
        return res.getCount();
    }
    public List<DbItem> getFav(){
        List<DbItem> list=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from items " ,null);
        if(res.getCount()>0) {
            res.moveToFirst();
            while (!res.isAfterLast()){
                list.add(new DbItem(res.getString(res.getColumnIndex("id")),res.getString(res.getColumnIndex("url")),res.getString(res.getColumnIndex("gos")),res.getString(res.getColumnIndex("type")),res.getString(res.getColumnIndex("original_name")),res.getString(res.getColumnIndex("sold"))));
                res.moveToNext();
            }
        }
        return list;
    }



}
