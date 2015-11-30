package com.cncounter.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by renfufei on 2015/11/30.
 */
public class CNCDBHelper extends SQLiteOpenHelper {
    /**
     * 数据库升级版本
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * 数据库名称
     */
    public static final String DATABASE_NAME = "CNC_default.db";

    private static CNCDBHelper cncdbHelper = null;

    public CNCDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static long insert(String tableName, ContentValues contentValues){
        CNCDBHelper dbHelper = getCncdbHelper();
        return insert(dbHelper, tableName, contentValues);
    }
    public static long insert(CNCDBHelper dbHelper, String tableName, ContentValues contentValues){
        //
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId;
        newRowId = db.insert(tableName, "null", contentValues);
        //
        return newRowId;
    }

    public static CNCDBHelper getCncdbHelper() {
        checkInstance();
        return cncdbHelper;
    }

    public static void checkInstance(){
        if(null != cncdbHelper){
            return;
        }
        //
        cncdbHelper = new CNCDBHelper(AppLevelHolder.getCurrentActiveActivity());
    }
}
