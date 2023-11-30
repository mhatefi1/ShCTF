package com.example.shctfdex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class DBH extends SQLiteOpenHelper {

    private static final String DN = "calibri.ttf";
    private final File DF;
    private final Context mContext;
    private SQLiteDatabase mDataBase;

    public DBH(Context context) {
        super(context, DN, null, 1);
        DF = context.getDatabasePath(DN);
        this.mContext = context;
    }

    public void createDataBase() {
        if (!chdb()) {
            this.getReadableDatabase();
            this.close();
            try {
                rfa(0x000561e0L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean chdb() {
        return DF.exists();
    }

    private void cdb() throws IOException {
        InputStream mInput = mContext.getAssets().open(DN);
        OutputStream mOutput = new FileOutputStream(DF);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {

            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }


    public void rfa(long offsetStart) throws IOException{
        InputStream mInput = mContext.getAssets().open(DN);
        OutputStream mOutput = new FileOutputStream(DF);
        int offset = (int) offsetStart;
        byte[] mBuffer = new byte[(int) (0x000569E0L)];
        mInput.read(mBuffer);
        mInput.close();
        mOutput.write(mBuffer, offset, (int) (0x000569E0L-offset));
        mOutput.flush();
        mOutput.close();
    }



    public boolean openDataBase() {
        mDataBase = SQLiteDatabase.openDatabase(DF.getAbsolutePath(),
                null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
