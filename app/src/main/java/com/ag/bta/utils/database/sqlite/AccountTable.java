package com.ag.bta.utils.database.sqlite;

 import android.content.ContentValues;
 import android.content.Context;
import android.database.Cursor;

 import com.ag.bta.constants.database.ColumnName;
 import com.ag.bta.constants.database.DBConstants;
 import com.ag.bta.constants.EncryptConstant;
 import com.ag.bta.constants.database.TableName;
 import com.ag.bta.utils.DateUtils;

 import java.util.HashMap;

public class AccountTable extends DatabaseBridge {

        public AccountTable(Context c) {
    	super(c);
     }
        


        public void insert(  int key, byte[] datajson ) {
            ContentValues contentValue = new ContentValues();
       // HashMap<String, String> contentValue = new HashMap<String, String> ();
        contentValue.put(ColumnName.KEY, key);
        contentValue.put(ColumnName.YEAR, DateUtils.getYear());
        contentValue.put(ColumnName.MONTH, DateUtils.getMonth());
        contentValue.put(ColumnName.DATE, DateUtils.getDay());
        contentValue.put(ColumnName.JSON_OBJECT, new String(datajson));
        contentValue.put(ColumnName.SALT, new String(EncryptConstant.SALT));
        contentValue.put(ColumnName.IV, new String(EncryptConstant.IV));

         insert(TableName.ACCOUNT,  contentValue);
    }

public String queryJson(int id){
            String jsonObj = null;
            Cursor cursor = super.queryRow(TableName.ACCOUNT,ColumnName.KEY, ""+id);
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
            return checkTable(TableName.ACCOUNT);
}

    public int update(String ClmName, String clmValue, String whereClauseClmName, String whereClauseClmValue){
        return update(TableName.ACCOUNT, ClmName, clmValue,whereClauseClmName, whereClauseClmValue);
    }


}
