package com.beecoder77.madesubmission4.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "MOVIE";

    static final class MovieColoumn implements BaseColumns {
        static String TITLE = "title";
        static String DESCRIPTION = "overview";
        static String PHOTO = "IMAGE";
    }

    static String TABLE_TV="TV";

    static final class TvColoumn implements BaseColumns{
        static String TITLE = "title";
        static String DESCRIPTION = "overview";
        static String PHOTO = "IMAGE";
    }
}
