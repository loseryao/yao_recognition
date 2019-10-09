
package com.tiger.quicknews.activity;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Xml.Encoding;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.tiger.quicknews.App;
import com.tiger.quicknews.R;
import com.tiger.quicknews.http.HttpUtil;
import com.tiger.quicknews.http.Url;
import com.tiger.quicknews.listener.BackGestureListener;
import com.tiger.quicknews.utils.ACache;
import com.tiger.quicknews.utils.DialogUtil;
import com.tiger.quicknews.utils.StringUtils;
import com.tiger.quicknews.wedget.crouton.Crouton;
import com.tiger.quicknews.wedget.crouton.Style;
import com.tiger.quicknews.wedget.gesture.BaseActivityHelper;
import com.tiger.quicknews.wedget.slideingactivity.IntentUtils;
import com.tiger.quicknews.wedget.slideingactivity.SlidingActivity;

import org.androidannotations.annotations.Receiver;
import org.androidannotations.handler.ReceiverHandler;
import org.apache.http.util.EncodingUtils;



import io.vov.vitamio.utils.Log;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends SlidingActivity {
	float x1 = 0;
	float x2 = 0;
	float y1 = 0;
	float y2 = 0;
	List<Integer> listges;
	List<Long> lisClick;
	List UpHorizontalspeed;
	List UpVerticalspeed;
	List Upspeed ;
	List UpAcceleration;
	List UpStraightness ;
	List UpDistance ;
	List UpHorizontaldistance ;
	List UpVerticaldistance;
	List UpAngle;
	List UpAngleSpeed;
	List UpAngleAcclerate;
	List UpJerk;
	List UpAngleJeck;
	List UpTime;
	List UpInte;
	
	List DownHorizontalspeed ;
	List DownVerticalspeed ;
	List Downspeed ;
	List DownAcceleration;
	List DownStraightness ;
	List DownDistance;
	List DownHorizontaldistance ;
	List DownVerticaldistance;
	List DownAngle;
	List DownAngleSpeed;
	List DownAngleAcclerate;
	List DownJerk;
	List DownAngleJeck;
	List DownTime;
	List DownInte;
	
	List leftHorizontalspeed;
	List leftVerticalspeed ;
	List leftspeed ;
	List leftAcceleration;
	List leftStraightness;
	List leftDistance;
	List leftHorizontaldistance;
	List leftVerticaldistance;
	List leftAngle;
	List leftAngleSpeed;
	List leftAngleAcclerate;
	List leftJerk;
	List leftAngleJeck;
	List leftTime;
	List leftInte;
	
	
	List rightHorizontalspeed;
	List rightVerticalspeed;
	List rightspeed;
	List rightAcceleration;
	List rightStraightness;
	List rightDistance;
	List rightHorizontaldistance ;
	List rightVerticaldistance;
	List rightAngle;
	List rightAngleSpeed;
	List rightAngleAcclerate;
	List rightJerk;
	List rightAngleJeck;
	List rightTime;
	List rightInte;
	
	List Inte;
	int ges;
	
   
	private App bean;
    /** 手势监听 */
    // private GestureDetector mGestureDetector;
    /** 是否需要监听手势关闭功能 */
    private boolean mNeedBackGesture = false;
    // private BaseActivityHelper baseActivityHelper;
    private Dialog progressDialog;
    public static final int REQUEST_CODE = 1000;

    @Override
    public void onResume() {
        super.onResume();
        // baseActivityHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // baseActivityHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // baseActivityHelper.onDestroy();
    }
    
    public boolean isSupportSlide() {
        return true;
    }

    public String getUrl(String newId) {
        return Url.NewDetail + newId + Url.endDetailUrl;
    }

    public String getMsgUrl(String index, String itemId) {
        String urlString = Url.CommonUrl + itemId + "/" + index + "-40.html";
        return urlString;
    }

    public String getWeatherUrl(String cityName) throws UnsupportedEncodingException {
        // + Url.WeatherKey
        String urlString = Url.WeatherHost + URLEncoder.encode(cityName, "utf-8");
        return urlString;
    }
    @Override
    public void onUserInteraction() {
    	// TODO Auto-generated method stub
    	super.onUserInteraction();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	 //Log.e("TAGonTouchEvent", "onTouchEvent");
    	// TODO Auto-generated method stub
    	
    		
    	return super.onTouchEvent(event);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initGestureDetector();
        // baseActivityHelper = new BaseActivityHelper(this, isSupportSlide());
        // baseActivityHelper.onCreate();
    }

    private void initGestureDetector() {
        // if (mGestureDetector == null) {
        // mGestureDetector = new GestureDetector(getApplicationContext(),
        // new BackGestureListener(this));
        // }
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mNeedBackGesture) {
            // return mGestureDetector.onTouchEvent(ev) ||
            // super.dispatchTouchEvent(ev);
        }
        bean = (App)getApplication();
        ges = bean.getGesture();
    	lisClick = bean.getClick_in_time();
    	UpHorizontalspeed = bean.getUpHorizontalspeed();
    	UpVerticalspeed = bean.getUpVerticalspeed();
    	Upspeed = bean.getUpspeed();
    	UpAcceleration = bean.getUpAcceleration();
    	UpStraightness = bean.getUpStraightness();
    	UpDistance = bean.getUpDistance();
    	UpHorizontaldistance = bean.getUpHorizontaldistance();
    	UpVerticaldistance = bean.getUpVerticaldistance();
    	UpAngle=bean.getUpAngle();
    	UpAngleSpeed=bean.getUpAngleSpeed();
    	UpAngleAcclerate=bean.getUpAngleAcclerate();
    	UpJerk=bean.getUpJerk();
    	UpAngleJeck=bean.getUpAngleJeck();
    	UpTime=bean.getUpTime();
    	UpInte = bean.getUpInte();
    	
    	DownHorizontalspeed =bean.getDownHorizontalspeed();
    	DownVerticalspeed = bean.getDownVerticalspeed();
    	Downspeed = bean.getDownSpeed();
    	DownAcceleration = bean.getDownAcceleration();
    	DownStraightness = bean.getDownStraightness();
    	DownDistance = bean.getDownDistance();
    	DownHorizontaldistance = bean.getDownHorizontaldistance();
    	DownVerticaldistance = bean.getDownVerticaldistance();
    	DownAngle=bean.getDownAngle();
    	DownAngleSpeed=bean.getDownAngleSpeed();
    	DownAngleAcclerate=bean.getDownAngleAcclerate();
    	DownJerk=bean.getDownJerk();
    	DownAngleJeck=bean.getDownAngleJeck();
    	DownTime=bean.getDownTime();
    	DownInte = bean.getDownInte();
    	
    	
    	leftHorizontalspeed = bean.getLeftHorizontalspeed();
    	leftVerticalspeed = bean.getLeftVerticalspeed();
    	leftspeed = bean.getleftSpeed();
    	leftAcceleration = bean.getLeftAcceleration();
    	leftStraightness = bean.getLeftStraightness();
    	leftDistance = bean.getLeftDistance();
    	leftHorizontaldistance = bean.getLeftHorizontaldistance();
    	leftVerticaldistance = bean.getLeftVerticaldistance();
    	leftAngle=bean.getLeftangle();
    	leftAngleSpeed=bean.getLeftAngleSpeed();
    	leftAngleAcclerate=bean.getLeftAngleAcclerate();
    	leftJerk=bean.getLeftJerk();
    	leftAngleJeck=bean.getLeftAngleJeck();
    	leftTime=bean.getLeftTime();
    	leftInte = bean.getLeftInte();
    	
    	rightHorizontalspeed =bean.getRightHorizontalspeed();
    	rightVerticalspeed = bean.getRightVerticalspeed();
    	rightspeed = bean.getRightSpeed();
    	rightAcceleration = bean.getRightAcceleration();
    	rightStraightness = bean.getRightStraightness();
    	rightDistance = bean.getRightDistance();
    	rightHorizontaldistance = bean.getRightHorizontaldistance();
    	rightVerticaldistance = bean.getRightVerticalspeed();
    	rightAngle=bean.getRightangle();
    	rightAngleSpeed=bean.getRightAngleSpeed();
    	rightAngleAcclerate=bean.getRightAngleAcclerate();
    	rightJerk=bean.getRightJerk();
    	rightAngleJeck=bean.getRightAngleJeck();
    	rightTime=bean.getRightTime();
    	rightInte = bean.getRightInte();
    	
    	
    	long timepas = System.currentTimeMillis();
  	
    	
    	long intevaltime = 0;
    	if(ev.getAction() == MotionEvent.ACTION_DOWN) {
    		  //当手指按下的时候
    		  x1 = ev.getX();
    		  y1 = ev.getY();
    		  long gestruetime = System.currentTimeMillis();
    		  bean.setGestruetime(gestruetime);
    		  intevaltime=System.currentTimeMillis()-bean.getIntevaltime();
    		  if(intevaltime>140000000){
    			  intevaltime=0;
    		  }
    		  int gest = bean.getGesture();
    		  if(gest!=-1)
    		  {
    			  switch (gest) {
				case 1:UpInte.add(intevaltime);bean.setUpInte(UpInte);break;
				case 2:DownInte.add(intevaltime);bean.setDownInte(DownInte);break;
				case 3:leftInte.add(intevaltime);bean.setLeftInte(leftInte);break;
				case 4:rightInte.add(intevaltime);bean.setRightInte(rightInte);break;
    		  }
    			  
    		  }		  	 
    	}
    	if(ev.getAction() == MotionEvent.ACTION_UP) {
    		  //当手指离开的时候
    		  x2 = ev.getX();
    		  y2 = ev.getY();
    		  long gestruetime = System.currentTimeMillis()-bean.getGestruetime();
    		  intevaltime = System.currentTimeMillis();
    		  bean.setIntevaltime(intevaltime);
    		  double Hdistance = Math.abs(x1-x2);//
    		  double vdistance = Math.abs(y1-y2);
    		  double distance = Math.sqrt(Hdistance*Hdistance+vdistance*vdistance);
			   
			  double Hspeed = Hdistance/gestruetime;
			  double Vspeed = vdistance/gestruetime;
			  double speed = Math.sqrt(Hspeed*Hspeed+Vspeed*Vspeed);
			  double acce = speed/gestruetime;
			  double angle=0;
			  if(Hdistance!=0){
			  angle=(double)(Math.atan(vdistance/Hdistance)/Math.PI*180);
			  }
			  double angleSpeed = angle/gestruetime;
			  double angleAcclerate=angleSpeed/gestruetime;
			  double Jerk = acce/gestruetime;
			  double angleJeck = angleAcclerate/gestruetime;
			  double time = Double.parseDouble(String.valueOf(gestruetime));
    		  if(y1 - y2 > 50) 
    		  {
    			  ges=1;
    			  UpHorizontaldistance.add(Hdistance);
    			  UpVerticaldistance.add(vdistance);
    			  UpHorizontalspeed.add(Hspeed);
    			  UpVerticalspeed.add(Vspeed);
    			  Upspeed.add(speed);
    			  UpAcceleration.add(acce);
    			  UpDistance.add(distance);
    			  Log.e("Hdistance="+Hdistance, "Hdistance="+Hdistance);
    			  
    			  UpAngle.add(angle);
    			  UpAngleSpeed.add(angleSpeed);
    			  UpAngleAcclerate.add(angleSpeed);
    			  UpJerk.add(Jerk);
    			  UpAngleJeck.add(angleJeck);
    			  UpTime.add(time);
    			  
    			  bean.setGesture(ges);
    			  bean.setUpHorizontalspeed(UpHorizontalspeed);
    			  bean.setUpVerticalspeed(UpVerticalspeed);
    			  bean.setUpspeed(Upspeed);
    			  bean.setUpAcceleration(UpAcceleration);
    			  bean.setUpStraightness(UpStraightness);
    		      bean.setUpDistance(UpDistance);
    		      bean.setUpHorizontaldistance(UpHorizontaldistance);
    		      bean.setUpVerticaldistance(UpVerticaldistance);
    		      bean.setUpAngle(UpAngle);
    		      bean.setUpAngleSpeed(UpAngleSpeed);
    		      bean.setUpJerk(UpJerk);
    		      bean.setUpAngleJeck(UpAngleJeck);
    		      bean.setUpAngleAcclerate(UpAngleAcclerate);
    		      bean.setUpTime(UpTime);
    
    		    	
    		    	
    			 // Toast.makeText(BaseActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
    		  } 
    		  else if(y2 - y1 > 50) 
    		  {
    			  ges=2;
    			  DownHorizontaldistance.add(Hdistance);
    			  DownVerticaldistance.add(vdistance);
    			  DownHorizontalspeed.add(Hspeed);
    			  DownVerticalspeed.add(Vspeed);
    			  Downspeed.add(speed);
    			  DownAcceleration.add(acce);
    			  DownDistance.add(distance);
    			  
    			  DownAngle.add(angle);
    			  DownAngleSpeed.add(angleSpeed);
    			  DownAngleAcclerate.add(angleSpeed);
    			  DownJerk.add(Jerk);
    			  DownAngleJeck.add(angleJeck);
    			  DownTime.add(time);
    			  
    			  
    			  
    			  bean.setDownHorizontalspeed(DownHorizontalspeed);
    			  bean.setDownVerticalspeed(DownVerticalspeed);
    			  bean.setDownSpeed(Downspeed);
    			  bean.setDownAcceleration(DownAcceleration);
    		      bean.setDownDistance(DownDistance);
    		      bean.setDownHorizontaldistance(DownHorizontaldistance);
    		      bean.setDownVerticaldistance(DownVerticaldistance);
    		      
    		      bean.setDownAngle(DownAngle);
    		      bean.setDownAngleSpeed(DownAngleSpeed);
    		      bean.setDownJerk(DownJerk);
    		      bean.setDownAngleJeck(DownAngleJeck);
    		      bean.setDownAngleAcclerate(DownAngleAcclerate);
    		      bean.setDownTime(DownTime);
    		      bean.setGesture(ges);
    		  } 
    		  else if(x1 - x2 > 50) 
    		  {
    			  ges=3;
    			  leftHorizontaldistance.add(Hdistance);
    			  leftVerticaldistance.add(vdistance);
    			  leftHorizontalspeed.add(Hspeed);
    			  leftVerticalspeed.add(Vspeed);
    			  leftspeed.add(speed);
    			  leftAcceleration.add(acce);
    			  leftDistance.add(distance);
    			  
    			  
    			  
    			  leftAngle.add(angle);
    			  leftAngleSpeed.add(angleSpeed);
    			  leftAngleAcclerate.add(angleSpeed);
    			  leftJerk.add(Jerk);
    			  leftAngleJeck.add(angleJeck);
    			  leftTime.add(time);
    			  
    			  
    			  
    			  bean.setLeftHorizontalspeed(leftHorizontalspeed);
    			  bean.setLeftVerticalspeed(leftVerticalspeed);
    			  bean.setLeftSpeed(leftspeed);
    			  bean.setLeftAcceleration(leftAcceleration);
    			  bean.setLeftStraightness(leftStraightness);
    		      bean.setLeftDistance(leftDistance);
    		      bean.setLeftHorizontaldistance(leftHorizontaldistance);
    		      bean.setLeftVerticaldistance(leftVerticaldistance);
    		      
    		      bean.setLeftangle(leftAngle);
    		      bean.setLeftAngleSpeed(leftAngleSpeed);
    		      bean.setLeftJerk(leftJerk);
    		      bean.setLeftAngleJeck(leftAngleJeck);
    		      bean.setLeftAngleAcclerate(leftAngleAcclerate);
    		      bean.setLeftTime(leftTime);
    		      bean.setGesture(ges);
    		  } 
    		  else if(x2 - x1 > 50) 
    		  {
    			  ges=4;
    			  rightHorizontaldistance.add(Hdistance);
    			  rightVerticaldistance.add(vdistance);
    			  rightHorizontalspeed.add(Hspeed);
    			  rightVerticalspeed.add(Vspeed);
    			  rightspeed.add(speed);
    			  rightAcceleration.add(acce);
    			  rightDistance.add(distance);
    			  

    			  rightAngle.add(angle);
    			  rightAngleSpeed.add(angleSpeed);
    			  rightAngleAcclerate.add(angleSpeed);
    			  rightJerk.add(Jerk);
    			  rightAngleJeck.add(angleJeck);
    			  rightTime.add(time);
    			  
    			  
    			  bean.setRightHorizontalspeed(rightHorizontalspeed);
    			  bean.setRightVerticalspeed(rightVerticalspeed);
    			  bean.setRightSpeed(rightspeed);
    			  bean.setRightAcceleration(rightAcceleration);
    		      bean.setRightDistance(rightDistance);
    		      bean.setRightHorizontaldistance(rightHorizontaldistance);
    		      bean.setRightVerticaldistance(rightVerticaldistance);
    		      
    		      bean.setRightangle(rightAngle);
    		      bean.setRightAngleSpeed(rightAngleSpeed);
    		      bean.setRightJerk(rightJerk);
    		      bean.setRightAngleJeck(rightAngleJeck);
    		      bean.setRightAngleAcclerate(rightAngleAcclerate);
    		      bean.setRightTime(rightTime);
    		      bean.setGesture(ges);
    		  }	
//    		  else if((Hdistance < 50)&&(vdistance<50))
//    		  {
//    			  listges.add(5);
//    			  
//    		  }
    		  lisClick.add(gestruetime);
    		 }
    		
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 设置是否进行手势监听
     */
    public void setNeedBackGesture(boolean mNeedBackGesture) {
        this.mNeedBackGesture = mNeedBackGesture;
    }

    /**
     * 显示dialog
     * 
     * @param msg 显示内容
     */
    public void showProgressDialog() {
        try {

            if (progressDialog == null) {
                progressDialog = DialogUtil.createLoadingDialog(this);

            }
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏dialog
     */
    public void dismissProgressDialog() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dialog是否显示
     */
    public boolean isShow() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更具类打开acitvity
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, 0);

    }

    public void openActivityForResult(Class<?> pClass, int requestCode) {
        openActivity(pClass, null, requestCode);
    }

    /**
     * 更具类打开acitvity,并携带参数
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (requestCode == 0) {
            IntentUtils.startPreviewActivity(this, intent, 0);
            // startActivity(intent);
        } else {
            IntentUtils.startPreviewActivity(this, intent, requestCode);
            // startActivityForResult(intent, requestCode);
        }
        // actityAnim();
    }

    /**
     * 判断是否有网络
     * 
     * @return
     */
    public boolean hasNetWork() {
        return HttpUtil.isNetworkAvailable(this);
    }

    /**
     * 显示LongToast
     */
    public void showShortToast(String pMsg) {
        // ToastUtil.createCustomToast(this, pMsg, Toast.LENGTH_LONG).show();
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示ShortToast
     */
    public void showCustomToast(String pMsg) {
        // ToastUtil.createCustomToast(this, pMsg, Toast.LENGTH_SHORT).show();
        // Crouton.makeText(this, pMsg, Style.ALERT, R.id.toast_conten).show();
        Crouton.makeText(this, pMsg, Style.ALERT, R.id.toast_conten).show();

    }

    /**
     * 设置缓存数据（key,value）
     */
    public void setCacheStr(String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            ACache.get(this).put(key, value);
        }
    }

    /**
     * 获取缓存数据更具key
     */
    public String getCacheStr(String key) {
        return ACache.get(this).getAsString(key);
    }

    /**
     * 带动画效果的关闭
     */
    @Override
    public void finish() {
        super.finish();
        // baseActivityHelper.finish();
        //actityAnim();
    }

    /**
     * 系统默认关闭
     */
    public void defaultFinish() {
        super.finish();
        // baseActivityHelper.finish();
    }

    /**
     * 系统默认关闭
     */
    public void defaultFinishNotActivityHelper() {
        super.finish();
    }

    public void actityAnim() {
        // overridePendingTransition(R.anim.slide_in_right,
        // R.anim.slide_right_out);
    }

    /**
     * 返回
     */
    public void doBack(View view) {
        onBackPressed();
    }

}
