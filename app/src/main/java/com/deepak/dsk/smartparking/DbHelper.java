package com.deepak.dsk.smartparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dsk on 13-Apr-18.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="smartParking";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE="create table "+DbContract.TABLE_NAME+
            "(id integer primary key autoincrement,"
            +DbContract.USER_NAME+" text,"
            +DbContract.BOOKING_TIME+" integer,"
            +DbContract.BOOKING_DURATION+" integer,"
            +DbContract.BOOKING_DATE+" text,"
            +DbContract.LOCATION+" text,"
            +DbContract.OTP+" text,"
            +DbContract.CAR_NUM+" integer,"
            +DbContract.SLOT_NUMBER+" integer)";

    private static final String DROP="drop table if exists "+DbContract.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP);
        onCreate(sqLiteDatabase);
    }


    public void saveDetail(String userName,int bookingTime,int bookingDuration,String bookingDate
            ,int slotNumber,String location,String otp,int carNum,SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbContract.USER_NAME,userName);
        contentValues.put(DbContract.BOOKING_TIME,bookingTime);
        contentValues.put(DbContract.BOOKING_DURATION,bookingDuration);
        contentValues.put(DbContract.BOOKING_DATE,bookingDate);
        contentValues.put(DbContract.SLOT_NUMBER,slotNumber);
        contentValues.put(DbContract.LOCATION,location);
        contentValues.put(DbContract.OTP,otp);
        contentValues.put(DbContract.CAR_NUM,carNum);
        database.insert(DbContract.TABLE_NAME,null,contentValues);
    }



    public Cursor readDetail(SQLiteDatabase database)
    {
        String[] projection={"id",DbContract.USER_NAME,DbContract.BOOKING_TIME,DbContract.SLOT_NUMBER
                ,DbContract.BOOKING_DURATION,DbContract.BOOKING_DATE,DbContract.LOCATION,DbContract.OTP,DbContract.CAR_NUM};
        return (database.query(DbContract.TABLE_NAME,projection,null,null,null,null,null));
    }
}
