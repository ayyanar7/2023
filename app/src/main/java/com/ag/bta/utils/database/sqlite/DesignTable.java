package com.ag.bta.utils.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ag.bta.utils.constant.EncryptConstant;
import com.ag.bta.utils.constant.database.ColumnName;
import com.ag.bta.utils.constant.database.TableName;
import com.ag.bta.utils.DateUtils;

public class DesignTable extends DatabaseBridge {

        public DesignTable(Context c) {
    	super(c);
     }
        


        public void insert(  String key, String datajson ) {
            ContentValues contentValue = new ContentValues();
       // HashMap<String, String> contentValue = new HashMap<String, String> ();
        contentValue.put(ColumnName.TABS, key);
        contentValue.put(ColumnName.YEAR, DateUtils.getYear());
        contentValue.put(ColumnName.MONTH, DateUtils.getMonth());
        contentValue.put(ColumnName.DATE, DateUtils.getDay());
        contentValue.put(ColumnName.JSON_OBJECT, datajson);
        contentValue.put(ColumnName.SALT, new String(EncryptConstant.SALT));
        contentValue.put(ColumnName.IV, new String(EncryptConstant.IV));

         insert(TableName.DESIGN,  contentValue);
    }

public String queryJson(String id){
            String jsonObj = null;
            Cursor cursor = super.queryRow(TableName.DESIGN,ColumnName.TABS, ""+id);
                   try {
                       do {
                           jsonObj = cursor.getString(cursor.getColumnIndex(ColumnName.JSON_OBJECT));

                       }
                       while (cursor.moveToNext());
                   }finally {
                       if (cursor != null)
                           cursor.close();
                   }
                    return jsonObj;
}


public boolean checkTable(){
            return checkTable(TableName.DESIGN);
}

    public int update(String ClmName, String clmValue, String whereClauseClmName, String whereClauseClmValue){
        return update(TableName.DESIGN, ClmName, clmValue,whereClauseClmName, whereClauseClmValue);
    }
    public void  delete(String tableName, String clmName, String clmValue ){
        delete(  tableName,   clmName,   clmValue );
    }


}
