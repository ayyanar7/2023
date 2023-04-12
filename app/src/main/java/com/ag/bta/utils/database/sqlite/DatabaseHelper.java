package com.ag.bta.utils.database.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ag.bta.utils.constant.database.DBConstants;
import com.ag.bta.utils.constant.database.DBQuery;
import com.ag.bta.utils.constant.database.TableName;

/**
 * Created by shaloin on 1/6/17.
 */

public  final class DatabaseHelper extends SQLiteOpenHelper {


    // Creating table query



//    private static final String DATABASE_ALTER_CREDENTIALS_LINK = "ALTER TABLE "
//            + TABLE_NAME + " ADD COLUMN " + COLUMN_LINK + " TEXT;";

//    private static final String CREATE_PHOTO_TABLE = "create table "
//            + PHOTO_TABLE + " (" + ID
//            + " integer primary key autoincrement, " + DBConstants.COLUMN_PHOTO
//            + " blob " + "  );";

    private static DatabaseHelper sInstance;
    public DatabaseHelper(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBQuery.CREATE_TABLE_ACCOUNT);
        db.execSQL(DBQuery.CREATE_TABLE_DESIGN);
        db.execSQL(DBQuery.CREATE_TABLE_SALES);
        db.execSQL(DBQuery.CREATE_TABLE_PURCHASE);
        //db.execSQL(CREATE_PHOTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName.ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TableName.DESIGN);
        db.execSQL("DROP TABLE IF EXISTS " + TableName.PURCHSE);
        db.execSQL("DROP TABLE IF EXISTS " + TableName.SALES);
        onCreate(db);

//        if (oldVersion < 2) {
//            db.execSQL(DATABASE_ALTER_CREDENTIALS_DESCR);
//        }
//        if (oldVersion < 3) {
//            db.execSQL(DATABASE_ALTER_CREDENTIALS_LINK);
//        }
//        if (oldVersion < 4) {
//            db.execSQL(CREATE_PHOTO_TABLE);
//        }else
//        if (oldVersion > 4) {
//            db.execSQL(SQL_DELETE_ENTRIES);
//            db.execSQL(SQL_DELETE_PHOTO);
//            onCreate(db);
//        }
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }



}