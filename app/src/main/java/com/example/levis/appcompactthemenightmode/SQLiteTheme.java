package com.example.levis.appcompactthemenightmode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
/**
 * Created by Levis on 7/29/2017.
 */
public class SQLiteTheme extends SQLiteOpenHelper {

	private static final String TAG = SQLiteTheme.class.getSimpleName();

	// All Static variables
	// Database Version
	private Context context;
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android_api2";

	// Theme table name
	private static final String TABLE = "usertheme";

	// Theme Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_USERTHEMEID = "userthemeid";

	public SQLiteTheme(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_THEME_TABLE = "CREATE TABLE " + TABLE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_USERTHEMEID + " INTEGER" + ")";
		db.execSQL(CREATE_THEME_TABLE);

		Log.d(TAG, "Database Usertheme Created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user theme details in database
	 * */
	public void addUsertheme(Context context,String themeid) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERTHEMEID, themeid);
		// Inserting Row
		long id = db.insert(TABLE, null, values);
		db.close(); // Closing database connection
		Toast.makeText(context,"Success Changing Theme",Toast.LENGTH_SHORT).show();
		Log.d(TAG, "New Usertheme Inserted Into Sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserTheme() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("userthemeid", cursor.getString(1));

		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching Usertheme From Sqlite: " + user.toString());

		return user;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsertheme() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE, null, null);
		db.close();

		Log.d(TAG, "Deleted all Usertheme Info From Sqlite");
	}

}
