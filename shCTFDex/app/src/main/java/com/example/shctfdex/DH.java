package com.example.shctfdex;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DH {

    private final String mt = "calibri";
    private final DBH dbh;
    private SQLiteDatabase mDb;

    public DH(Context context) {
        dbh = new DBH(context);

    }

    public void cd() {
        try {
            dbh.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            mDb = dbh.getReadableDatabase();
        } catch (SQLException mSQLException) {
            mSQLException.printStackTrace();
        }
    }

    public void close() {
        dbh.close();
    }

    public String gd() {
        String r = "null";
        try {
            String sql = "SELECT * FROM " + mt;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToPosition(0);
                r = mCur.getString(0);
                mCur.close();

            }
        } catch (SQLException mSQLException) {
            mSQLException.printStackTrace();
        }
        return r;
    }

    public String gdd() {
        String r = "null";
        try {
            String sql = "SELECT * FROM " + mt;
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToPosition(1);
                r = mCur.getString(0);
                mCur.close();

            }
        } catch (SQLException mSQLException) {
            mSQLException.printStackTrace();
        }
        return r;
    }

}
