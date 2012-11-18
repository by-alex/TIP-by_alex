package com.by_alex.ti_a;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

public class sqlLiteHelper extends SQLiteOpenHelper{

	public static final String TABLE_OBJECT_NAME = "object";
	public static final String COLUMN_OBJECT_ID = "id";
	public static final String COLUMN_OBJECT_NAME = "name";
	public static final String COLUMN_OBJECT_TYPE = "type";
	public static final String COLUMN_OBJECT_DESC = "desc";
	public static final String COLUMN_OBJECT_VERS = "version";

	public static final String TABLE_ATTRIBUTE_NAME = "attribute";
	public static final String COLUMN_ATTRIBUTE_ID = "id";
	public static final String COLUMN_ATTRIBUTE_NAME = "name";
	public static final String COLUMN_ATTRIBUTE_VALUE = "value";
	public static final String COLUMN_ATTRIBUTE_OBJECT_ID = "object_id";

	public static final String DATABSE_NAME = "nfc.db";
	public static final int DATABASE_VERSION = 1;
	
	SQLiteDatabase dataB;
	
	private static final String CREATE_TABLE_OBJECT = 
		"CREATE TABLE object (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT, version TEXT, desc TEXT);";
	
	//	"CREATE TABLE " + TABLE_OBJECT_NAME + "(" + 
	//			COLUMN_OBJECT_ID + "INTEGER PRIMARY KEY, " + 
	//			COLUMN_OBJECT_NAME + "TEXT, " +
	//			COLUMN_OBJECT_TYPE + "TEXT, " +
	//			COLUMN_OBJECT_VERS + "TEXT, " +
	//			COLUMN_OBJECT_DESC + "TEXT);";
	
	private static final String CREATE_TABLE_ATTRIBUTE = 
		"CREATE TABLE attribut (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value TEXT, object_id INTEGER);";
	
	//	"CREATE TABLE " + TABLE_ATTRIBUTE_NAME + "(" + 
	//			COLUMN_ATTRIBUTE_ID + "INTEGER PRIMARY KEY, " + 
	//			COLUMN_ATTRIBUTE_NAME + "TEXT, " +
	//			COLUMN_ATTRIBUTE_VALUE + "TEXT, " +
	//			COLUMN_ATTRIBUTE_OBJECT_ID + "INTEGER);";//+
	//		//	"FOREIGN KEY (" + COLUMN_ATTRIBUTE_OBJECT_ID + ") REFERENCES " + TABLE_OBJECT_NAME + "(" + COLUMN_OBJECT_ID + "));";

	public sqlLiteHelper(Context cntxt){
		super(cntxt, DATABSE_NAME, null, DATABASE_VERSION);
		act_2.toaster("sqlLiteHelper");
		getWritableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		Log.i("HHH", "onCreate0");
		db.execSQL(CREATE_TABLE_OBJECT);
		Log.i("HHH", "onCreate1");
		db.execSQL(CREATE_TABLE_ATTRIBUTE);
		Log.i("HHH", "onCreate2");
		
		dataB = db;
	//	getReadableDatabase();
		for(int i = 0; i < 3; i++)
			insertValues("object "+i,"nfc#object","1.0","nfc object #"+i);
			
	//	Cursor r = 	dataB.query(
	//	TABLE_OBJECT_NAME,
	//	new String[]{COLUMN_OBJECT_NAME,COLUMN_OBJECT_TYPE},
	//	"",
	//	new String[]{},
	//	null,
	//	null,
	//	null);
	
	//	Log.i("HHH", "onCreate3" + dataB.query(
	//	TABLE_OBJECT_NAME,
	//	new String[]{COLUMN_OBJECT_NAME,COLUMN_OBJECT_TYPE},
	//	null,
	//	new String[]{},
	//	null,
	//	null,
	//	null
	//	).toString());
	Cursor r = dataB.rawQuery("SELECT * FROM "+TABLE_OBJECT_NAME, null);
		r.moveToFirst();
		Log.i("HHH", "onCreate3 "+r.getString(r.getColumnIndex("name")));
	//	getQ();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}
	
	public void getQ(){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor r = dataB.rawQuery("SELECT * FROM "+TABLE_OBJECT_NAME, null);
		Log.i("HHH", "onCreate3"+r.toString());
	}
		
	public void insertValues(String name, String type, String version, String description){
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_OBJECT_NAME, name);
		values.put(COLUMN_OBJECT_TYPE, type);
		values.put(COLUMN_OBJECT_VERS, version);
		values.put(COLUMN_OBJECT_VERS, description);
		Log.i("HHH", "insertValues "+values.toString());
		dataB.insert(TABLE_OBJECT_NAME, null, values);
	}
			
			
			
}
