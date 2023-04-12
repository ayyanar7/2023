package com.ag.bta.utils.constant.database;

public class DBQuery {
    public static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE " +  TableName.ACCOUNT + "(" + ColumnName.ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ ColumnName.KEY + " INTEGER, " + ColumnName.YEAR + " INTEGER, " + ColumnName.MONTH + " INTEGER, "+ ColumnName.DATE + " INTEGER, "+ColumnName.JSON_OBJECT + " TEXT, "+ ColumnName.SALT + " TEXT NOT NULL, "+ColumnName.IV + " TEXT NOT NULL);";
    public static final String CREATE_TABLE_DESIGN = "CREATE TABLE " +  TableName.DESIGN + "(" + ColumnName.ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ ColumnName.TABS + " TEXT, " + ColumnName.YEAR + " INTEGER, " + ColumnName.MONTH + " INTEGER, "+ ColumnName.DATE + " INTEGER, "+ColumnName.JSON_OBJECT + " TEXT, "+ ColumnName.SALT + " TEXT NOT NULL, "+ColumnName.IV + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_SALES = "create table " + TableName.SALES + "(" + ColumnName.ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnName.YEAR + " INTEGER, " + ColumnName.MONTH + " TEXT NOT NULL, "+ ColumnName.DATE + " INTEGER, "+ColumnName.JSON_OBJECT + " TEXT, "+ ColumnName.SALT + " TEXT NOT NULL, "+ColumnName.IV + " TEXT NOT NULL);";

    public static final String CREATE_TABLE_PURCHASE = "create table " + TableName.PURCHSE + "(" + ColumnName.ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnName.YEAR + " INTEGER, " + ColumnName.MONTH + " TEXT NOT NULL, "+ ColumnName.DATE + " INTEGER, "+ ColumnName.JSON_OBJECT + " TEXT, "+ ColumnName.SALT + " TEXT NOT NULL, "+ColumnName.IV + " TEXT NOT NULL);";

}
