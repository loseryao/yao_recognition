package com.useridentify.service;


import java.util.Timer;
import java.util.TimerTask;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.SystemService;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import com.tiger.quicknews.activity.DetailsActivity_;
import com.tiger.quicknews.activity.ShowResultActivity_;
@EService
public class IdenInformService  extends IntentService {

	  private Timer timer = null;
	  private int reOrlo;
	  @SystemService
	  NotificationManager notificationManager;
	 
	  public IdenInformService() {
	      super("IdenInformService");
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
		    	Intent intent = ShowResultActivity_.intent(getApplicationContext()).get();
		    	intent.putExtra("reOrlo", 1);
		    	intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		    	startActivity(intent);
		    	Looper.loop();
		    	
		    	//LoginActivity_.intent(getBaseContext()).start();
		    	
		    }  
		};
		timer=new Timer();
		timer.schedule(task, 1*60*1000);

		
		
	}

}
