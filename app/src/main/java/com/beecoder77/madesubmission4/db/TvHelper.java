package com.beecoder77.madesubmission4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.beecoder77.madesubmission4.model.Item;

import java.util.ArrayList;

import static com.beecoder77.madesubmission4.db.DatabaseContract.TABLE_TV;

public class TvHelper {

    private static final String DATABASE_TABLE = TABLE_TV;
    private static DatabaseHelper databaseHelper;
    private static TvHelper INSTANCE;
    private static SQLiteDatabase database;

    public TvHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public static TvHelper getINSTANCE(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TvHelper(context);

                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<Item> getAllTv(){
        ArrayList<Item> items = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null, DatabaseContract.TvColoumn._ID ,null);
        cursor.moveToFirst();
        Item mItem;
        if (cursor.getCount() > 0 ){
            do {
                mItem = new Item();
                mItem.setId(cursor.getInt(0));
                mItem.setDescription(String.valueOf(cursor.getString(2)));
                mItem.setPhoto(String.valueOf(cursor.getString(3)));
                mItem.setTitle(String.valueOf(cursor.getString(1)));
                items.add(mItem);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return items;
    }

    public Boolean getOne(String name){
        String querySingleRecord = "SELECT * FROM " + DATABASE_TABLE + " WHERE " +DatabaseContract.TvColoumn.TITLE+ " " + " LIKE " +"'"+name+"'" ;
        Cursor cursor = database.rawQuery(querySingleRecord,null);
        cursor.moveToFirst();
        Log.d("cursor", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0 ){

            return true;
        }else if(cursor.getCount() == 0){
            return false;
        }
        return false;
    }

    public long insertTv(Item mItem){
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.TvColoumn.TITLE,mItem.getTitle());
        args.put(DatabaseContract.TvColoumn.PHOTO,mItem.getPhoto());
        args.put(DatabaseContract.TvColoumn.DESCRIPTION,mItem.getDescription());
        return database.insert(DATABASE_TABLE,null,args);
    }

    public int deleteTv(String title){
        return database.delete(TABLE_TV, DatabaseContract.TvColoumn.TITLE+ " = " + "'"+title+"'" , null);
    }
}
