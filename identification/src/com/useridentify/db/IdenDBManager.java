package com.useridentify.db;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class IdenDBManager {
	private IdenSqlHelper helper;  
    private SQLiteDatabase db;  
    private String TABLE_BROSWING;
    private String TABLE_RHYTHM;// 页面浏览表
    public IdenDBManager(Context context) {  
        helper = new IdenSqlHelper(context);  
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = helper.getWritableDatabase(); 
        TABLE_BROSWING = "BrowsingInfo";
        TABLE_RHYTHM  = "RhythmInfo" ;
    }  
    
    public void insertBrowseing(Map<String,Long> pageBroswing,
            String UserID,List<Integer> wordscount) 
    {
    	
        db.beginTransaction();  //开始事务  
        int i = 0;
        try {  
            for (Map.Entry<String, Long> entry : pageBroswing.entrySet()) {  
            	//int wc = (int)(wordscount.get(i));
            	double wc = Double.parseDouble((wordscount.get(i).toString()));
            	double Speed = entry.getValue()/wc;
                db.execSQL("INSERT INTO BrowsingInfo VALUES(null, ?, ?, ?,?)", new Object[]{UserID,Speed,entry.getKey(),entry.getValue() })
              ;
                i++;
            }  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
    }  
      
    /** 
     * insertRhythm 
     * 
     */  
//    public void insertRhythm(String USERID,List INTETIME,List CLICKCATEGORY) 
//    {
//    	db.beginTransaction();  //开始事务  
//        try {  
//        	int size = INTETIME.size()<CLICKCATEGORY.size()?INTETIME.size():CLICKCATEGORY.size();
//            for (int i = 0;i<size;i++) {  
//                db.execSQL("INSERT INTO RhythmInfo VALUES(null, ?, ?,?)", new Object[]{USERID,INTETIME.get(i),CLICKCATEGORY.get(i)});
//            }  
//            db.setTransactionSuccessful();  //设置事务成功完成  
//        } finally {  
//            db.endTransaction();    //结束事务  
//        }  
//    }  
    
    public void insertGestrue(List Inte,List Horizontalspeed,List Verticalspeed,
			   List speed,List Acceleration,
			   List Distance ,List Horizontaldistance ,
			   List Verticaldistance,
			   List Angle,List AngleSpeed,List AngleAcclerate,List Jerk,
			   List AngleJeck,List Time,String table,String UserId) {  
    			int begin = Horizontalspeed.size()-Inte.size();
    			Log.e("Horizontalspeed="+Horizontalspeed.size(), "Horizontalspeed="+Horizontalspeed.size());
    			Log.e("Inte="+Inte.size(), "Inte="+Inte.size());
    			db.beginTransaction();  //开始事务  
    			try {  
    				int j=0;
    				for (int i=0;i<Horizontalspeed.size();i++) {
    					
    					if(i-begin>=0)j=i-begin;
    					db.execSQL("INSERT INTO "+table+" VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[]{
    							UserId,
    							Inte.get(j),
    							speed.get(i),
    							Horizontalspeed.get(i),
    							Verticalspeed.get(i),
    							Horizontaldistance.get(i),
    							Verticaldistance.get(i),
    							Acceleration.get(i),
    							Angle.get(i),
    							AngleSpeed.get(i),
    							AngleAcclerate.get(i),
    							Jerk.get(i),
    							AngleJeck.get(i),
    							Time.get(i),
    							Distance.get(i)});
    				}  
    				db.setTransactionSuccessful();  //设置事务成功完成  
    			} finally {  
    				db.endTransaction();    //结束事务  
    			}  
    }  
       
    public Dataset queryGestrue(String table) {  
    	
    	Dataset dataset = new DefaultDataset();
    	Cursor c = db.query(table,null,null,null,null,null,null);//查询并获得游标;
    	int i = 0;
    	//public static final String ID = "id";//
        while (c.moveToNext()) {  
        	
        	String USERID =  c.getString(c.getColumnIndex("userId"));
        	double Inte = c.getDouble(c.getColumnIndex("Inte"));
        	double SPEED = c.getDouble(c.getColumnIndex("speed"));
        	double time=c.getInt(c.getColumnIndex("Time"));
        	double HORIZONTALSPEED = c.getDouble(c.getColumnIndex("Horizontalspeed"));
        	double VERTICALSPEED = c.getDouble(c.getColumnIndex("Verticalspeed"));
        	double VERTICALDISTANCE = c.getInt(c.getColumnIndex("Verticaldistance"));
        	double HORIZONTALDISTANCR = c.getInt(c.getColumnIndex("Horizontaldistance"));
        	double ACCELERATION = c.getDouble(c.getColumnIndex("Acceleration"));
        	double ANGLE=c.getDouble(c.getColumnIndex("Angle"));
        	double ANGLESPEED=c.getDouble(c.getColumnIndex("AngleSpeed"));
       	 	double ANGLEACCLERATE=c.getDouble(c.getColumnIndex("AngleAcclerate"));
       	 	double JECK =c.getDouble(c.getColumnIndex("Jerk"));
       	 	double ANGLEJECK=c.getDouble(c.getColumnIndex("AngleJeck"));
       	 	double TIME=c.getDouble(c.getColumnIndex("Time"));     	
        	double DISTANCE = c.getInt(c.getColumnIndex("distance"));
        	//Log.e("DISTANCE", ""+DISTANCE);
        	double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
        	//double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK,TIME, DISTANCE};
        	//double[] values = new double[] { Inte,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK, DISTANCE};
        	Instance ins = new DenseInstance(values,USERID); 
        	dataset.add(ins);
       }  
       
        c.close();  
        return dataset;  
    }
//    public Dataset queryGestrues(String table,List<String> users) {  
//    	Dataset data = new DefaultDataset();
//    	String where="";
//    	String sql="select * from "+table+" where ";
//    	for(int i = 0;i<users.size();i++)
//    	{
//    		
//    		if(i==users.size()-1)
//    		{
//    			where+= "userID ="+"'"+users.get(i)+"'";//构造sql语句
//    			break;
//    		}
//    		where+= " userID = "+"'"+users.get(i)+"'"+" or ";
//    	}
//    	sql=sql+where;
//    	Cursor c = db.rawQuery(sql, null);;//查询并获得游标;
//    	
//    	//Cursor c = db.query(table,null,null,null,null,null,null);//查询并获得游标;  
//    	//public static final String ID = "id";//
//        while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//        	double Inte = c.getDouble(c.getColumnIndex("Inte"));
//        	double SPEED = c.getDouble(c.getColumnIndex("speed"));
//        	double time=c.getInt(c.getColumnIndex("Time"));
//        	double HORIZONTALSPEED = c.getDouble(c.getColumnIndex("Horizontalspeed"));
//        	double VERTICALSPEED = c.getDouble(c.getColumnIndex("Verticalspeed"));
//        	double VERTICALDISTANCE = c.getInt(c.getColumnIndex("Verticaldistance"));
//        	double HORIZONTALDISTANCR = c.getInt(c.getColumnIndex("Horizontaldistance"));
//        	double ACCELERATION = c.getDouble(c.getColumnIndex("Acceleration"));
//        	double ANGLE=c.getDouble(c.getColumnIndex("Angle"));
//        	double ANGLESPEED=c.getDouble(c.getColumnIndex("AngleSpeed"));
//       	 	double ANGLEACCLERATE=c.getDouble(c.getColumnIndex("AngleAcclerate"));
//       	 	double JECK =c.getDouble(c.getColumnIndex("Jerk"));
//       	 	double ANGLEJECK=c.getDouble(c.getColumnIndex("AngleJeck"));
//       	 	double TIME=c.getDouble(c.getColumnIndex("Time"));     	
//        	double DISTANCE = c.getInt(c.getColumnIndex("distance"));
//        	//Log.e("DISTANCE", ""+DISTANCE);
//        	//double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
//        	//double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK,TIME, DISTANCE};
//        	double[] values = new double[] { Inte,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK, DISTANCE};
//        	Instance instance = new DenseInstance(values,USERID);
//        	data.add(instance);  	
//       }  
//        
//        c.close();  
//        return data;  
//    }  
//    public Dataset queryBrowsings(List<String> users)
//    {
//    	Dataset  data = new DefaultDataset();
//    	String where="";
//    	String sql="select * from "+TABLE_BROSWING+" where ";
//    	for(int i = 0;i<users.size();i++)
//    	{
//    		
//    		if(i==users.size()-1)
//    		{
//    			where+= "userID ="+"'"+users.get(i)+"'";//构造sql语句
//    			break;
//    		}
//    		where+= " userID = "+"'"+users.get(i)+"'"+" or ";
//    	}
//    	//Cursor c = db.rawQuery(sql, null);;//查询并获得游标;
//    	sql=sql+where;
//    	Cursor c = db.rawQuery(sql, null);;//查询并获得游标;
//    	//Cursor c = db.query(TABLE_BROSWING,null,null,null,null,null,null);//查询并获得游标; 
//    	while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//          	double BROWSINGSPEED = c.getInt(c.getColumnIndex("BrowsingSpeed"));
//        	double BROWSINGTIME = c.getDouble(c.getColumnIndex("BrowsingTime"));
//        	double[] values = new double[] { BROWSINGSPEED, BROWSINGTIME};
//        	Instance instance = new DenseInstance(values,USERID);
//        	data.add(instance);  	
//       }  
//    	return data;
//    }
    public Dataset queryTwoGestrue(String table,String user1,String user2) {  
    	Dataset data = new DefaultDataset();
    	String sql="select * from "+table+" where userID = ? or userID = ?";
    	Cursor c = db.rawQuery(sql, new String[]{user1,user2});;//查询并获得游标; 
    	//Cursor c = db.query(table,null,null,null,null,null,null);//查询并获得游标;  
    	//public static final String ID = "id";//
        while (c.moveToNext()) {  
        	String USERID =  c.getString(c.getColumnIndex("userId"));
        	double Inte = c.getDouble(c.getColumnIndex("Inte"));
        	double SPEED = c.getDouble(c.getColumnIndex("speed"));
        	double time=c.getInt(c.getColumnIndex("Time"));
        	double HORIZONTALSPEED = c.getDouble(c.getColumnIndex("Horizontalspeed"));
        	double VERTICALSPEED = c.getDouble(c.getColumnIndex("Verticalspeed"));
        	double VERTICALDISTANCE = c.getInt(c.getColumnIndex("Verticaldistance"));
        	double HORIZONTALDISTANCR = c.getInt(c.getColumnIndex("Horizontaldistance"));
        	double ACCELERATION = c.getDouble(c.getColumnIndex("Acceleration"));
        	double ANGLE=c.getDouble(c.getColumnIndex("Angle"));
        	double ANGLESPEED=c.getDouble(c.getColumnIndex("AngleSpeed"));
       	 	double ANGLEACCLERATE=c.getDouble(c.getColumnIndex("AngleAcclerate"));
       	 	double JECK =c.getDouble(c.getColumnIndex("Jerk"));
       	 	double ANGLEJECK=c.getDouble(c.getColumnIndex("AngleJeck"));
       	 	double TIME=c.getDouble(c.getColumnIndex("Time"));     	
        	double DISTANCE = c.getInt(c.getColumnIndex("distance"));
        	//Log.e("DISTANCE", ""+DISTANCE);
        	double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
        	//double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK,TIME, DISTANCE};
        	//double[] values = new double[] { Inte,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK, DISTANCE};
        	Instance instance = new DenseInstance(values,USERID);
        	data.add(instance);  	
       }  
        
        c.close();  
        return data;  
    }  
//    public Dataset queryTwoBrowsing(String userID1,String userID2)
//    {
//    	Dataset data = new DefaultDataset(); 
//    	//Cursor c = db.query(TABLE_BROSWING,null,null,null,null,null,null);//查询并获得游标; 
//    	String sql="select * from "+TABLE_BROSWING +" where userID = ? or userID = ?";
//    	Cursor c = db.rawQuery(sql, new String[]{userID1,userID2});;//查询并获得游标;  
//    	while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//          	double BROWSINGSPEED = c.getInt(c.getColumnIndex("BrowsingSpeed"));
//        	double BROWSINGTIME = c.getDouble(c.getColumnIndex("BrowsingTime"));
//        	double[] values = new double[] { BROWSINGSPEED, BROWSINGTIME};
//        	Instance instance= null;
//        	instance = new DenseInstance(values,USERID);    
//        	data.add(instance);  	
//       }  
//    	return data;
//    }
//    public Dataset queryGestrueByUserID(String table,String userid) {  
//    	Dataset data = new DefaultDataset(); 
//    	Cursor c = db.query(table,null,null,null,null,null,null);//查询并获得游标;
//    	//String sql="select * from ? where userID = ?";
//    	//Cursor c = db.rawQuery(sql, new String[]{table,userid});;//查询并获得游标;  
//    	//public static final String ID = "id";//
//        while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//        	double Inte = c.getDouble(c.getColumnIndex("Inte"));
//        	double SPEED = c.getDouble(c.getColumnIndex("speed"));
//        	double time=c.getInt(c.getColumnIndex("Time"));
//        	double HORIZONTALSPEED = c.getDouble(c.getColumnIndex("Horizontalspeed"));
//        	double VERTICALSPEED = c.getDouble(c.getColumnIndex("Verticalspeed"));
//        	double VERTICALDISTANCE = c.getInt(c.getColumnIndex("Verticaldistance"));
//        	double HORIZONTALDISTANCR = c.getInt(c.getColumnIndex("Horizontaldistance"));
//        	double ACCELERATION = c.getDouble(c.getColumnIndex("Acceleration"));
//        	double ANGLE=c.getDouble(c.getColumnIndex("Angle"));
//        	double ANGLESPEED=c.getDouble(c.getColumnIndex("AngleSpeed"));
//       	 	double ANGLEACCLERATE=c.getDouble(c.getColumnIndex("AngleAcclerate"));
//       	 	double JECK =c.getDouble(c.getColumnIndex("Jerk"));
//       	 	double ANGLEJECK=c.getDouble(c.getColumnIndex("AngleJeck"));
//       	 	double TIME=c.getDouble(c.getColumnIndex("Time"));     	
//        	double DISTANCE = c.getInt(c.getColumnIndex("distance"));
//        	//Log.e("DISTANCE", ""+DISTANCE);
//        	double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
//        	//double[] values = new double[] { Inte,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK,TIME, DISTANCE};
//        	Instance instance=null;
//        	if(USERID.equals(userid))
//        		instance = new DenseInstance(values,USERID);
//        	else
//        		instance = new DenseInstance(values,"other");
//        	data.add(instance);  	
//       }  
//        c.close();  
//        return data;  
//    }
//    public Dataset queryUpGestrueByUserID(String table,String userid) {  
//    	Dataset data = new DefaultDataset(); 
//    	Cursor c = db.query(table,null,null,null,null,null,null);//查询并获得游标;
//    	//String sql="select * from ? where userID = ?";
//    	//Cursor c = db.rawQuery(sql, new String[]{table,userid});;//查询并获得游标;  
//    	//public static final String ID = "id";//
//        while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//        	double Inte = c.getDouble(c.getColumnIndex("Inte"));
//        	double SPEED = c.getDouble(c.getColumnIndex("speed"));
//        	double time=c.getInt(c.getColumnIndex("Time"));
//        	double HORIZONTALSPEED = c.getDouble(c.getColumnIndex("Horizontalspeed"));
//        	double VERTICALSPEED = c.getDouble(c.getColumnIndex("Verticalspeed"));
//        	double VERTICALDISTANCE = c.getInt(c.getColumnIndex("Verticaldistance"));
//        	double HORIZONTALDISTANCR = c.getInt(c.getColumnIndex("Horizontaldistance"));
//        	double ACCELERATION = c.getDouble(c.getColumnIndex("Acceleration"));
//        	double ANGLE=c.getDouble(c.getColumnIndex("Angle"));
//        	double ANGLESPEED=c.getDouble(c.getColumnIndex("AngleSpeed"));
//       	 	double ANGLEACCLERATE=c.getDouble(c.getColumnIndex("AngleAcclerate"));
//       	 	double JECK =c.getDouble(c.getColumnIndex("Jerk"));
//       	 	double ANGLEJECK=c.getDouble(c.getColumnIndex("AngleJeck"));
//       	 	double TIME=c.getDouble(c.getColumnIndex("Time"));     	
//        	double DISTANCE = c.getInt(c.getColumnIndex("distance"));
//        	//Log.e("DISTANCE", ""+DISTANCE);
//        	double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
//        	//double[] values = new double[] { Inte,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK, DISTANCE};
//        	Instance instance=null;
//        	instance = new DenseInstance(values);	
//        	data.add(instance);  	
//       }  
//        c.close();  
//        return data;  
//    }  
    public List<String> queryUser()
    {
    	List<String> user = new ArrayList<String>();
    	String sql="select DISTINCT userId from DownInfo";
    	Cursor c = db.rawQuery(sql, null);;//查询并获得游标;  
    	 while (c.moveToNext()) {  
         	String USERID =  c.getString(c.getColumnIndex("userId"));
         	user.add(USERID);  	
        }  
    	//Collections.shuffle(user);
    	return user;
    } 
//    public Dataset queryBrowsing()
//    {
//    	Dataset data = new DefaultDataset(); 
//    	Cursor c = db.query(TABLE_BROSWING,null,null,null,null,null,null);//查询并获得游标; 
//    	while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//          	double BROWSINGSPEED = c.getInt(c.getColumnIndex("BrowsingSpeed"));
//        	double BROWSINGTIME = c.getDouble(c.getColumnIndex("BrowsingTime"));
//        	double[] values = new double[] { BROWSINGSPEED, BROWSINGTIME};
//        	Instance instance = new DenseInstance(values,USERID);
//        	data.add(instance);  	
//       }  
//    	return data;
//    }
//    public Dataset queryBrowsingByUserId(String userID)
//    {
//    	Dataset data = new DefaultDataset(); 
//    	Cursor c = db.query(TABLE_BROSWING,null,null,null,null,null,null);//查询并获得游标; 
////    	String sql="select * from "+TABLE_BROSWING +" where userID = ?";
////    	Cursor c = db.rawQuery(sql, new String[]{userID});;//查询并获得游标;  
//    	while (c.moveToNext()) {  
//        	String USERID =  c.getString(c.getColumnIndex("userId"));
//        	
//          	double BROWSINGSPEED = c.getInt(c.getColumnIndex("BrowsingSpeed"));
//        	double BROWSINGTIME = c.getDouble(c.getColumnIndex("BrowsingTime"));
//        	double[] values = new double[] { BROWSINGSPEED, BROWSINGTIME};
//        	Instance instance= null;
//        	if(USERID.equals(userID))
//        		instance = new DenseInstance(values,userID);
//        	else
//        		instance = new DenseInstance(values,"other");
//        
//        	data.add(instance);  	
//       }  
//    	return data;
//    }
    public boolean empty()
    {
    	Cursor c = db.query("UpInfo",null,null,null,null,null,null);//查询并获得游标; 
    	if(c.getCount()==0)return true;
    	return false;
    }
    public void excute(String sql)
    {
    	 db.execSQL(sql);
    }
    /** 
     * close database 
     */ 
    
    public void closeDB() {  
        db.close();  
    }  

}
