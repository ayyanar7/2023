package com.ag.bta.utils.database;

 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

 import com.ag.bta.utils.constant.database.ColumnName;
 import com.ag.bta.utils.constant.database.TableName;
 import com.ag.bta.utils.database.sqlite.DatabaseBridge;

public class PurchaseTable extends DatabaseBridge {
	public PurchaseTable(Context c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

    public void insert(int year, String month,int date,byte[] salt,byte[] iv, byte[] datajson ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ColumnName.YEAR, year);
        contentValue.put(ColumnName.MONTH, month);
        contentValue.put(ColumnName.DATE, date);
        contentValue.put(ColumnName.SALT, new String(salt));
        contentValue.put(ColumnName.IV, new String(iv));
        contentValue.put(ColumnName.JSON_OBJECT, new String(datajson));
        insert(TableName.SALES, contentValue);
    }


    public Cursor fetch() {
        String[] columns = new String[] { ColumnName.ID, ColumnName.YEAR, ColumnName.MONTH,ColumnName.DATE,
                ColumnName.SALT,ColumnName.IV ,ColumnName.JSON_OBJECT };
        Cursor cursor = database.query(TableName.SALES, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int update(long _id, int year, String month,int date,String json) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnName.YEAR, year);
        contentValues.put(ColumnName.MONTH, month);
        contentValues.put(ColumnName.DATE, date);
        contentValues.put(ColumnName.JSON_OBJECT, json);
        int i = database.update(TableName.SALES, contentValues, ColumnName.ID + " = " + _id, null);
        return i;
    }
    public void  delete(String tableName, String clmName, String clmValue ){
        delete(  tableName,   clmName,   clmValue );
    }

}
