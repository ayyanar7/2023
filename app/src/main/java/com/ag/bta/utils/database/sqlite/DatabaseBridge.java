package com.ag.bta.utils.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.ag.bta.main.models.Login;
import com.ag.bta.utils.DateUtils;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.ag.bta.utils.constant.database.ColumnName;
import com.ag.bta.utils.constant.database.TableName;
public class DatabaseBridge   {
    String data1;
    String data2;
    protected DatabaseHelper dbHelper;

    protected Context context;

    protected SQLiteDatabase database;

    public DatabaseBridge(Context c) {

        context = c;
        open();
    }

    public DatabaseBridge open() throws SQLException {
        if(dbHelper == null) {
            dbHelper =   DatabaseHelper.getInstance(context);
            database = dbHelper.getWritableDatabase();
        }
        return this;
    }

    public void close() {
        if(dbHelper != null)
            dbHelper.close();
    }

    public void insert(String tableName,  ContentValues values ){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long newRowId=0;
        Boolean flag=false;

//        for (String key : hmap.keySet() ) {
//            values.put(key,  hmap.get(key));
//
//        }
        newRowId = db.insert(tableName, null, values);
        db.close();
    }

    public void deleteRow(String tableName, String whereClauseName, String whereClauseValue){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String whereClause=whereClauseName+"=?";
        String[] whereArgs=new String[] {whereClauseValue};
        db.delete(tableName,whereClause,whereArgs);
    }

//    public void update(String account,String pass){
//        SQLiteDatabase db=this.getWritableDatabase();
//        String sql="UPDATE "+ TABLE_NAME +" SET " +COLUMN_PASSWORD +" = " +pass + " WHERE "+ COLUMN_ACCOUNT +" = " +  account;
//        db.execSQL(sql);
//
//    }

    public int update(String tableName,String ClmName, String clmValue, String whereClauseClmName, String whereClauseClmValue) {
       com.ag.bta.utils.Log.d("whereClauseClmName : "+whereClauseClmName);
        com.ag.bta.utils.Log.d("whereClauseClmValue : "+whereClauseClmValue);
        com.ag.bta.utils.Log.d("clmValue : "+clmValue);
        com.ag.bta.utils.Log.d("ClmName : "+ClmName);
        com.ag.bta.utils.Log.d("tableName : "+tableName);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnName.YEAR, DateUtils.getYear());
        contentValues.put(ColumnName.MONTH, DateUtils.getMonth());
        contentValues.put(ColumnName.DATE, DateUtils.getDay());
        contentValues.put(ClmName, clmValue);

        String whereClause=whereClauseClmName + " =?";
        String[] whereArgs=new String[]{whereClauseClmValue};
        int update=db.update(tableName,contentValues,whereClause,whereArgs);
        return update;

    }
    public void deleteAll (String tableName){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, null, null);
    }


    public void delete(String tableName, String clmName, String clmValue ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause =  clmName +"="+clmValue;
        db.delete(tableName, whereClause, null);
    }


    public boolean checkTable(String tableName){
        SQLiteDatabase db = null;
        Cursor c = null;
        boolean rowExists = false;
        try {
            db = dbHelper.getWritableDatabase();
            c = db.rawQuery("SELECT * FROM  " + tableName, null);

            if (c.moveToFirst()) {
                rowExists = true;

            } else {
                rowExists = false;
            }
        }
        finally {
            if(c!=null){
                c.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return rowExists;
    }

    public Cursor query(String tableName, String[] columns) {
        if(columns == null){
            return null;
        }
        SQLiteDatabase db=dbHelper.getReadableDatabase();;
        Cursor cursor = db.query(tableName, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor queryRowElements(String tableName, String[] columns) {
        if(columns == null){
            return null;
        }
        SQLiteDatabase db=dbHelper.getReadableDatabase();;
        Cursor cursor = db.query(tableName, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor queryRow(String tableName, String whereClauseClmName, String whereclauseValue){
        SQLiteDatabase db=dbHelper.getReadableDatabase();;
        List<String> collection=new ArrayList<>();
        String acc=null;
        Cursor c=null;
        try{
            String query="SELECT  * FROM " + tableName +" where "+whereClauseClmName +" = ?";
            c=db.rawQuery(query,new String[]{whereclauseValue});
            if(c!=null){


                        c.moveToFirst();

            }
        }
        finally {

            if(db!=null){
                db.close();
            }
        }
        return c;
    }

    public List<String> queryColumns(String tableName, String whereClauseClmName){
        SQLiteDatabase db=dbHelper.getReadableDatabase();;
        List<String> collection=new ArrayList<>();
        String acc=null;
        Cursor c=null;
        try{
            String query="SELECT " + whereClauseClmName + " FROM " + tableName;
            c=db.rawQuery(query,null);
            if(c!=null){
                if(c.moveToFirst()){
                    do{
                        acc=c.getString(c.getColumnIndex(whereClauseClmName));
                        collection.add(acc);
                    }
                    while (c.moveToNext());
                }
            }
        }
        finally {
            if(c!=null){
                c.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return collection;
    }

    public String querySelectiveColumn(String tableName, String[] columnstoQuery, String whereClausClmName, String whereClausClmValue ){
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        Cursor cursor = null;
        try{
        cursor= db.query(tableName,   columnstoQuery
                , whereClausClmName + " = ?", new String[] { whereClausClmValue },
                null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                data1=cursor.getString(1);
            }while (cursor.moveToNext());
        }
    }
        finally {
        if(cursor!=null){
            cursor.close();
        }
        if(db!=null){
            db.close();
        }
    }
        return data1;
    }



    public List<Login> queryRecord(String tablename){
        SQLiteDatabase db=dbHelper.getReadableDatabase();;
        List<Login> accountData=new ArrayList<>();
        Login accounts =null;
        Cursor c=null;
        try{
            String query="SELECT * FROM " + tablename;
            c=db.rawQuery(query,null);
            if(c!=null){
                if(c.moveToFirst()){
                    do{
                       // String account=c.getString(c.getColumnIndex(DBConstants.COLUMN_ACCOUNT));
                        //String pass = c.getString(c.getColumnIndex(DBConstants.COLUMN_PASSWORD));
                        //String description = c.getString(c.getColumnIndex(DBConstants.COLUMN_DESCRIPTION));
                        //String link =  c.getString(c.getColumnIndex(DBConstants.COLUMN_LINK));
                        //accountData.add(new Login(account, pass, description, link));
                    }
                    while (c.moveToNext());
                }
            }
        }
        finally {
            if(c!=null){
                c.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return accountData;
    }

    public void setPic(Bitmap bitmap){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(ColumnName.PHOTO, stream.toByteArray());
        db.insert(TableName.PHOTO_TABLE, null, values);
        Log.d("AAAAAAAAAAAAAAAA", "set");
        db.close();
    }

    public void updatePic(Bitmap bitmap){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(ColumnName.PHOTO, stream.toByteArray());
        db.update(TableName.PHOTO_TABLE, values, ColumnName.ID+"=?", new String[]{"1"});
        Log.d("AAAAAAAAAAAAAAAA", "updated");
        db.close();
    }

    public Bitmap getPic(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(TableName.PHOTO_TABLE, new String[]{ColumnName.PHOTO}, null, null,null,null,null);
        byte[] image = new byte[]{};
        if (cursor.moveToFirst()) {
            do {
                image= cursor.getBlob(0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}

