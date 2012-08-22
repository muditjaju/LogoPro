package com.example.logopro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// AUS FOLGENDEM TUTORIAL http://www.vogella.com/articles/AndroidSQLite/article.html
	
	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_COMMENT = "comment";
	
	private static final String DATABASE_NAME = "comments.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table "
												+ TABLE_COMMENTS + "(" + COLUMN_ID
												+ " integer primary key autoincrement, " + COLUMN_COMMENT
												+ " text net null); ";
	
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);		
	}

	@Override
	//Löscht lediglich die vorhande Datanbank und erstellt sie neu
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(db);
	}
	
}
