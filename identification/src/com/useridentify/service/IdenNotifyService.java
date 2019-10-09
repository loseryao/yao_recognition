package com.useridentify.service;


import java.security.Provider;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.builder.Builder;

import com.tiger.quicknews.App;
import com.tiger.quicknews.activity.DetailsActivity_;
import com.tiger.quicknews.activity.LoginActivity;
import com.tiger.quicknews.activity.LoginActivity_;
import com.tiger.quicknews.activity.ShowResultActivity;
import com.tiger.quicknews.activity.ShowResultActivity_;
import com.useridentify.db.IdenDBManager;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

@EService
public class IdenNotifyService extends IntentService  {

	  private Timer timer = null;
	  private int reOrlo;
	  @SystemService
	  NotificationManager notificationManager;
	 
	  public IdenNotifyService() {
	      super("IdenNotifyService");
	      
	      
	
	  }

	  
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		
		// TODO Auto-generated method stub
		TimerTask task = new TimerTask() {  
		    @Override 
		    public void run() {  	    	
		        // TODO Auto-generated method stub  
		    	Looper.prepare();	   
		    	Toast.makeText(getApplicationContext(), "注册完成",
		    		     Toast.LENGTH_LONG).show();    
		    	Intent intent = ShowResultActivity_.intent(getApplicationContext()).get();
		    	intent.putExtra("reOrlo", 2);
		    	intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		    	startActivity(intent);
		    	Looper.loop();
		    	
		    	
		    	//LoginActivity_.intent(getBaseContext()).start();
		    	
		    }  
		};
		timer=new Timer();
		timer.schedule(task, 10*60*1000);
		
	}
}