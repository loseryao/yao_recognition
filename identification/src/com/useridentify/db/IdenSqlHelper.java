package com.useridentify.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class IdenSqlHelper extends SQLiteOpenHelper{
	 public static final String DB_NAME = "Identify.db";// 数据库名称
	 public static final int VERSION = 1;
	 public static final String TABLE_UPINFO = "UpInfo";// UP表
	 public static final String TABLE_DOWNINFO = "DownInfo";// DOWN表
	 public static final String TABLE_LEFTINFO = "LeftInfo";// LEFT表
	 public static final String TABLE_RIGHTINFO = "RightInfo";// RIGHT表
	 public static final String TABLE_BROROWSING  = "BrowsingInfo ";// 页面浏览表
	 public static final String TABLE_RHYTHM  = "RhythmInfo ";// 页面浏览表
	 
	 public static final String ID = "id";//
	 public static final String USERID = "userId";//
	 public static final String SPEED = "speed";
	 public static final String DISTANCE = "distance";
	 public static final String HORIZONTALSPEED = "Horizontalspeed";
	 public static final String VERTICALSPEED = "Verticalspeed";
	 public static final String HORIZONTALDISTANCR = "Horizontaldistance";
	 public static final String VERTICALDISTANCE = "Verticaldistance";
	 public static final String ACCELERATION = "Acceleration";
	 public static final String ANGLE="Angle";
	 public static final String	ANGLESPEED="AngleSpeed";
	 public static final String ANGLEACCLERATE="AngleAcclerate";
	 public static final String JECK ="Jerk";
	 public static final String ANGLEJECK="AngleJeck";
	 public static final String	TIME="Time"; 
	 public static final String	INTE="Inte";
	 
	 
	 public static final String BROWSINGTIME = "BrowsingTime";
	 public static final String BROWSINGSPEED = "BrowsingSpeed";
	 public static final String PAGEID = "PageID";
	 public static final String INTETIME = "InteTime";
	 public static final String CLICKCATEGORY = "ClickCategory";
	 
	
	public IdenSqlHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	 
	 

	@Override
	public void onCreate(SQLiteDatabase db) {
		String Upsql = "create table if not exists " + TABLE_UPINFO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +USERID + " INTEGER , "
                +INTE + " DOUBLE , "
                +SPEED + " DOUBLE , "
                +HORIZONTALSPEED + " DOUBLE , " 
                +VERTICALSPEED + " DOUBLE , "
                +HORIZONTALDISTANCR + " INTEGER , "
                +VERTICALDISTANCE + " INTEGER , "
                +ACCELERATION + " DOUBLE , "
                +ANGLE + " DOUBLE , "
                +ANGLESPEED + " DOUBLE , "
                +ANGLEACCLERATE + " DOUBLE , "
                +JECK + " DOUBLE , "
                +ANGLEJECK + " DOUBLE , "
                +TIME + " DOUBLE , "
                +DISTANCE + " INTEGER)";
		String Downsql = "create table if not exists " + TABLE_DOWNINFO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
                +USERID + " INTEGER , "
                +INTE + " DOUBLE , "
                +SPEED + " DOUBLE , "
                +HORIZONTALSPEED + " DOUBLE , " 
                +VERTICALSPEED + " DOUBLE , "
                +HORIZONTALDISTANCR + " INTEGER , "
                +VERTICALDISTANCE + " INTEGER , "
                +ACCELERATION + " DOUBLE , "
                 +ANGLE + " DOUBLE , "
                +ANGLESPEED + " DOUBLE , "
                +ANGLEACCLERATE + " DOUBLE , "
                +JECK + " DOUBLE , "
                +ANGLEJECK + " DOUBLE , "
                +TIME + " DOUBLE , "
                +DISTANCE + " INTEGER)";
		String Leftsql = "create table if not exists " + TABLE_LEFTINFO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
            
                +USERID + " INTEGER , "
                +INTE + " DOUBLE , "
                +SPEED + " DOUBLE , "
                +HORIZONTALSPEED + " DOUBLE , " 
                +VERTICALSPEED + " DOUBLE , "
                +HORIZONTALDISTANCR + " INTEGER , "
                +VERTICALDISTANCE + " INTEGER , "
                +ACCELERATION + " DOUBLE , "
                +ANGLE + " DOUBLE , "
                +ANGLESPEED + " DOUBLE , "
                +ANGLEACCLERATE + " DOUBLE , "
                +JECK + " DOUBLE , "
                +ANGLEJECK + " DOUBLE , "
                +TIME + " DOUBLE , "
                +DISTANCE + " INTEGER)";
		String Rightsql = "create table if not exists " + TABLE_RIGHTINFO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
               
                +USERID + " INTEGER , "
                +INTE + " DOUBLE , "
                +SPEED + " DOUBLE , "
                +HORIZONTALSPEED + " DOUBLE , " 
                +VERTICALSPEED + " DOUBLE , "
                +HORIZONTALDISTANCR + " INTEGER , "
                +VERTICALDISTANCE + " INTEGER , "
                +ACCELERATION + " DOUBLE , "
                 +ANGLE + " DOUBLE , "
                +ANGLESPEED + " DOUBLE , "
                +ANGLEACCLERATE + " DOUBLE , "
                +JECK + " DOUBLE , "
                +ANGLEJECK + " DOUBLE , "
                +TIME + " DOUBLE , "
                +DISTANCE + " INTEGER)";
		String Browsingsql = "create table if not exists " + TABLE_BROROWSING +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
                +USERID + " INTEGER , "
                +BROWSINGSPEED + " DOUBLE , "
                +PAGEID + " VARCHAR(10) , "
                +BROWSINGTIME + " INTEGER)";
//		String Rhythmsql = "create table if not exists " + TABLE_RHYTHM +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
//              
//                +USERID + " INTEGER , "
//                +INTETIME + " INTEGER , "
//                +CLICKCATEGORY + " INTEGER)";
	
        db.execSQL(Upsql);
        db.execSQL(Leftsql);
        db.execSQL(Rightsql);
        db.execSQL(Downsql);
        db.execSQL(Browsingsql);
        //db.execSQL(Rhythmsql);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
