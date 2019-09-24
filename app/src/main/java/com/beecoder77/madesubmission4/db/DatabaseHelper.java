package com.beecoder77.madesubmission4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "dbmovietv";

    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE)",
            DatabaseContract.TABLE_MOVIE,
            DatabaseContract.MovieColoumn._ID,
            DatabaseContract.MovieColoumn.TITLE,
            DatabaseContract.MovieColoumn.DESCRIPTION,
            DatabaseContract.MovieColoumn.PHOTO
    );

    private static final String SQL_CREATE_TABLE_TV= String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE,"+
                    "%s TEXT NOT NULL UNIQUE)",
            DatabaseContract.TABLE_TV,
            DatabaseContract.TvColoumn._ID,
            DatabaseContract.TvColoumn.TITLE,
            DatabaseContract.TvColoumn.DESCRIPTION,
            DatabaseContract.TvColoumn.PHOTO
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_TV);

        onCreate(db);
    }
}
