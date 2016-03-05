package com.example.sspuregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDB extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "sspuregister_db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "student_table";
	public final static String FIELD_id = "_id";
	public final static String FIELD_TEXT = "kid";


	public ToDoDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		
		String sqlmajor = "create table major (_id integer primary key autoincrement, "
				+ "name text, teachername text, phone text, email text, address text);";
		
		String sqllogin = "create table login (_id integer primary key autoincrement, "
				+ "logtime text, major text, flag int);";
		
		String sqlstu = "create table student (_id integer primary key autoincrement, "
				+ "kid text, code text, name text, myclass text,flag int default 0);";
		
		String sqlmode = "create table mode (_id integer primary key autoincrement, "
				+ "name text,stat text);";
		db.execSQL(sqlmajor);
		db.execSQL(sqllogin);
		db.execSQL(sqlstu);
		db.execSQL(sqlmode);
		
		ContentValues cv = new ContentValues();
		cv.put("name", "phone");
		cv.put("stat", "0");
		db.insert("mode", null, cv);
		
		cv.put("name", "sound");
		cv.put("stat", "0");
		db.insert("mode", null, cv);
		
	}
	
	public void alterTable(String name,String col) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "ALTER TABLE "+name+" ADD COLUMN "+col+" STRING default '0'";
		db.execSQL(sql);
	}
	
	public void createTable(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "create table "+name+" (_id integer primary key autoincrement, "
				+ "kid text, code text, name text, myclass text,times int default 1,absence int default 0,losecard int default 0);";
		db.execSQL(sql);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public Cursor select(String table) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(table, null, null, null, null, null, null);
		return cursor;
	}
	
	public Cursor selectstudent(String table,String kid) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(table, null, "kid=?", new String[] { String.valueOf(kid) }, null, null, null);
		return cursor;
	}
	
	public Cursor selectlogtime(String major) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("login", null, "flag=?and major=?", new String[] { String.valueOf("0"),String.valueOf(major) }, null, null, null);
		return cursor;
	}
	
	public Cursor selectlogin(String major) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("login", null, "major=?", new String[] { String.valueOf(major) } , null, null, null);
		return cursor;
	}
	
	
	public Cursor selectinitstu(String table,String col) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(table, null, col+"=?", new String[] { String.valueOf("1") }, null, null, null);
		return cursor;
	}
	
	public Cursor selectabststu(String table,String col) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(table, null, col+"=?", new String[] { String.valueOf("0") }, null, null, null);
		return cursor;
	}
	
	public Cursor selectuploadstu() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("student", null, "flag=?", new String[] { String.valueOf("0") }, null, null, null);
		return cursor;
	}
	
	public Cursor selectadyoff(String table,String col,String kid) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(table, null, col+"=?and kid=?", new String[] { String.valueOf("ÊÂ¼Ù"),String.valueOf(kid) }, null, null, null);
		return cursor;
	}

	public long insertstudent(String table,String kid,String code,String name,String myclass) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("kid", kid);
		cv.put("code", code);
		cv.put("name", name);
		cv.put("myclass", myclass);
		long row = db.insert(table, null, cv);
		return row;
	}
	
	public long insertlogin(String logtime,String major) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("logtime", logtime);
		cv.put("major", major);
		cv.put("flag", 0);
		long row = db.insert("login", null, cv);
		return row;
	}
	
	public Cursor checklogin(String logtime,String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("login", null, "logtime=?and major=?", new String[] { String.valueOf(logtime),String.valueOf(name) }, null, null, null);
		return cursor;
	}
	
	
	public Cursor selectcourse() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("major", null, null, null, null, null, null);
		return cursor;
	}
	
	public Cursor checkcourse(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("major", null, "name=?", new String[] { String.valueOf(name) }, null, null, null);
		return cursor;
	}
	
	
	public long insertmajor(String name,String teachername,String phone,String email,String address) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("teachername", teachername);
		cv.put("phone", phone);
		cv.put("email", email);
		cv.put("address", address);
		long row = db.insert("major", null, cv);
		return row;
	}
	

	public void delete(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where =  "name = ?";
		String[] whereValue = { name };
		db.delete("major", where, whereValue);
	}
	
	public void deletelog(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where =  "major = ?";
		String[] whereValue = { name };
		db.delete("login", where, whereValue);
	}

	public void update(String table,String col, String kid) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "kid= ?";
		String[] whereValue = { kid };
		ContentValues cv = new ContentValues();
		cv.put(col, "1");
		db.update(table, cv, where, whereValue);
	}
	
	public void updateuploadstu(String kid) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "kid= ?";
		String[] whereValue = { kid };
		ContentValues cv = new ContentValues();
		cv.put("flag", "1");
		db.update("student", cv, where, whereValue);
	}
	
	public void updateabs(String table,String col,String absence, String kid) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "kid= ?";
		String[] whereValue = { kid };
		ContentValues cv = new ContentValues();
		cv.put("absence", absence);
		cv.put(col, "ÊÂ¼Ù");
		db.update(table, cv, where, whereValue);
	}
	
	public void updatetimes(String table,String times, String kid) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "kid= ?";
		String[] whereValue = { kid };
		ContentValues cv = new ContentValues();
		cv.put("times", times);
		db.update(table, cv, where, whereValue);
	}
	
	public void updatetimesabs(String table,String times,String losecard, String kid) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "kid= ?";
		String[] whereValue = { kid };
		ContentValues cv = new ContentValues();
		cv.put("times", times);
		cv.put("losecard", losecard);
		db.update(table, cv, where, whereValue);
	}
	
	public void updatemajor(String name,String teachername, String phone,String email,String address) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "name= ?";
		String[] whereValue = { name };
		ContentValues cv = new ContentValues();
		cv.put("teachername", teachername);
		cv.put("phone", phone);
		cv.put("email", email);
		cv.put("address", address);
		db.update("major", cv, where, whereValue);
	}
	
	public void deletetable(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "drop table "+name+" ";
		db.execSQL(sql);
	}
	
	public Cursor selectmode(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("mode", null,"name=?", new String[] { String.valueOf(name) }, null, null, null);
		return cursor;
	}
	
	
	public void updatemode(String name,String stat) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "name= ?";
		String[] whereValue = { name };
		ContentValues cv = new ContentValues();
		cv.put("stat", stat);
		db.update("mode", cv, where, whereValue);
	}
	
	public void logintime(String logtime,String major) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "logtime= ? and major =? ";
		String[] whereValue = { logtime,major };
		ContentValues cv = new ContentValues();
		cv.put("flag", "1");
		db.update("login", cv, where, whereValue);
	}
	
}

