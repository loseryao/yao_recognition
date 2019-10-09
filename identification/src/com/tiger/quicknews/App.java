
package com.tiger.quicknews;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tiger.quicknews.activity.LoginActivity;
import com.tiger.quicknews.db.SQLHelper;
import com.useridentify.db.IdenDBManager;

import org.androidannotations.annotations.EApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EApplication
public class App extends Application {
    private static IdenDBManager idm;
	private static App mAppApplication;
    private SQLHelper sqlHelper;
    private Map time_on_page= new HashMap<String,Long>();//浏览页面时间
	private String rhythme;//
	private String UserID;//用户编号
	private List wordscount= new ArrayList<Integer>();//单个新闻字数
	private int gesture=-1;//手势序列
	private List click_in_time = new ArrayList<Long>();//点击间隔时间
	private List page_seq = new ArrayList<String>();
	private long gestruetime;
	private long intevaltime=0;
	private List UpInte = new ArrayList<Long>();
	private List DownInte = new ArrayList<Long>();
	private List leftInte = new ArrayList<Long>();
	private List rightInte = new ArrayList<Long>();
	private float x=0;
	private float y=0;
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public List getUpInte() {
		return UpInte;
	}

	public void setUpInte(List upInte) {
		UpInte = upInte;
	}

	public List getDownInte() {
		return DownInte;
	}

	public void setDownInte(List downInte) {
		DownInte = downInte;
	}

	public List getLeftInte() {
		return leftInte;
	}

	public void setLeftInte(List leftInte) {
		this.leftInte = leftInte;
	}

	public List getRightInte() {
		return rightInte;
	}

	public void setRightInte(List rightInte) {
		this.rightInte = rightInte;
	}

	public long getIntevaltime() {
		return intevaltime;
	}

	public void setIntevaltime(long intevaltime) {
		this.intevaltime = intevaltime;
	}

	public long getGestruetime() {
		return gestruetime;
	}

	public void setGestruetime(long gestruetime) {
		this.gestruetime = gestruetime;
	}

	private int time_count=0;
	/**
	 * Horizontalspeed 水平速度
	 * Verticalspeed垂直速度
	 * speed 总速度
	 * Acceleration加速度
	 * Distance距离
	 * Horizontaldistance 水平距离
	 * Verticaldistance垂直距离
	 * UP上滑
	 * DOWN 下滑
	 * LEFT 左滑
	 * RIGHT 右滑
	 *
	 * **/
	
	private List UpHorizontalspeed = new ArrayList<Double>();
	private List UpVerticalspeed = new ArrayList<Double>();//
	private List Upspeed = new ArrayList<Double>();
	private List UpAcceleration = new ArrayList<Double>();
	private List UpStraightness = new ArrayList<Double>();
	private List UpDistance = new ArrayList<Double>();
	private List UpHorizontaldistance = new ArrayList<Double>();
	private List UpVerticaldistance = new ArrayList<Double>();
	private List UpAngle = new ArrayList<Double>();
	private List UpAngleSpeed = new ArrayList<Double>();
	private List UpAngleAcclerate=new ArrayList<Double>();
	private List UpJerk = new ArrayList<Double>();
	private List UpAngleJeck=new ArrayList<Double>();
	private List UpTime = new ArrayList<Double>();
	
	private List DownHorizontalspeed = new ArrayList<Double>();
	private List DownVerticalspeed = new ArrayList<Double>();
	private List Downspeed = new ArrayList<Double>();
	private List DownAcceleration = new ArrayList<Double>();
	private List DownStraightness = new ArrayList<Double>();
	private List DownDistance = new ArrayList<Double>();
	private List DownHorizontaldistance = new ArrayList<Double>();
	private List DownVerticaldistance = new ArrayList<Double>();
	private List DownAngle = new ArrayList<Double>();
	private List DownAngleSpeed = new ArrayList<Double>();
	private List DownAngleAcclerate=new ArrayList<Double>();
	private List DownJerk = new ArrayList<Double>();
	private List DownAngleJeck=new ArrayList<Double>();
	private List DownTime = new ArrayList<Double>();
	
	private List leftHorizontalspeed = new ArrayList<Double>();
	private List leftVerticalspeed = new ArrayList<Double>();
	private List leftspeed = new ArrayList<Double>();
	private List leftAcceleration = new ArrayList<Double>();
	private List leftStraightness = new ArrayList<Double>();
	private List leftDistance = new ArrayList<Double>();
	private List leftHorizontaldistance = new ArrayList<Double>();
	private List leftVerticaldistance = new ArrayList<Double>();
	private List leftangle = new ArrayList<Double>();
	private List leftAngleSpeed = new ArrayList<Double>();
	private List leftAngleAcclerate=new ArrayList<Double>();
	private List leftJerk = new ArrayList<Double>();
	private List leftAngleJeck=new ArrayList<Double>();
	private List leftTime = new ArrayList<Double>();
	
	
	private List rightHorizontalspeed = new ArrayList<Double>();
	private List rightVerticalspeed = new ArrayList<Double>();
	private List rightspeed = new ArrayList<Double>();
	private List rightAcceleration = new ArrayList<Double>();
	private List rightStraightness = new ArrayList<Double>();
	private List rightDistance = new ArrayList<Double>();
	private List rightHorizontaldistance = new ArrayList<Double>();
	private List rightVerticaldistance = new ArrayList<Double>();
	private List rightangle = new ArrayList<Double>();
	private List rightAngleSpeed = new ArrayList<Double>();
	private List rightAngleAcclerate=new ArrayList<Double>();
	private List rightJerk = new ArrayList<Double>();
	private List rightAngleJeck=new ArrayList<Double>();
	private List rightTime = new ArrayList<Double>();
	public List getUpTime() {
		return UpTime;
	}

	public void setUpTime(List upTime) {
		UpTime = upTime;
	}

	public List getDownTime() {
		return DownTime;
	}

	public void setDownTime(List downTime) {
		DownTime = downTime;
	}

	public List getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(List leftTime) {
		this.leftTime = leftTime;
	}

	public List getRightTime() {
		return rightTime;
	}

	public void setRightTime(List rightTime) {
		this.rightTime = rightTime;
	}

	public List getUpAngle() {
		return UpAngle;
	}

	public void setUpAngle(List upAngle) {
		UpAngle = upAngle;
	}

	public List getUpAngleSpeed() {
		return UpAngleSpeed;
	}

	public void setUpAngleSpeed(List upAngleSpeed) {
		UpAngleSpeed = upAngleSpeed;
	}

	public List getUpAngleAcclerate() {
		return UpAngleAcclerate;
	}

	public void setUpAngleAcclerate(List upAngleAcclerate) {
		UpAngleAcclerate = upAngleAcclerate;
	}

	public List getUpJerk() {
		return UpJerk;
	}

	public void setUpJerk(List upJerk) {
		UpJerk = upJerk;
	}

	public List getUpAngleJeck() {
		return UpAngleJeck;
	}

	public void setUpAngleJeck(List upAngleJeck) {
		UpAngleJeck = upAngleJeck;
	}

	public List getDownAngle() {
		return DownAngle;
	}

	public void setDownAngle(List downAngle) {
		DownAngle = downAngle;
	}

	public List getDownAngleSpeed() {
		return DownAngleSpeed;
	}

	public void setDownAngleSpeed(List downAngleSpeed) {
		DownAngleSpeed = downAngleSpeed;
	}

	public List getDownAngleAcclerate() {
		return DownAngleAcclerate;
	}

	public void setDownAngleAcclerate(List downAngleAcclerate) {
		DownAngleAcclerate = downAngleAcclerate;
	}

	public List getDownJerk() {
		return DownJerk;
	}

	public void setDownJerk(List downJerk) {
		DownJerk = downJerk;
	}

	public List getDownAngleJeck() {
		return DownAngleJeck;
	}

	public void setDownAngleJeck(List downAngleJeck) {
		DownAngleJeck = downAngleJeck;
	}

	public List getLeftangle() {
		return leftangle;
	}

	public void setLeftangle(List leftangle) {
		this.leftangle = leftangle;
	}

	public List getLeftAngleSpeed() {
		return leftAngleSpeed;
	}

	public void setLeftAngleSpeed(List leftAngleSpeed) {
		this.leftAngleSpeed = leftAngleSpeed;
	}

	public List getLeftAngleAcclerate() {
		return leftAngleAcclerate;
	}

	public void setLeftAngleAcclerate(List leftAngleAcclerate) {
		this.leftAngleAcclerate = leftAngleAcclerate;
	}

	public List getLeftJerk() {
		return leftJerk;
	}

	public void setLeftJerk(List leftJerk) {
		this.leftJerk = leftJerk;
	}

	public List getLeftAngleJeck() {
		return leftAngleJeck;
	}

	public void setLeftAngleJeck(List leftAngleJeck) {
		this.leftAngleJeck = leftAngleJeck;
	}

	public List getRightangle() {
		return rightangle;
	}

	public void setRightangle(List rightangle) {
		this.rightangle = rightangle;
	}

	public List getRightAngleSpeed() {
		return rightAngleSpeed;
	}

	public void setRightAngleSpeed(List rightAngleSpeed) {
		this.rightAngleSpeed = rightAngleSpeed;
	}

	public List getRightAngleAcclerate() {
		return rightAngleAcclerate;
	}

	public void setRightAngleAcclerate(List rightAngleAcclerate) {
		this.rightAngleAcclerate = rightAngleAcclerate;
	}

	public List getRightJerk() {
		return rightJerk;
	}

	public void setRightJerk(List rightJerk) {
		this.rightJerk = rightJerk;
	}

	public List getRightAngleJeck() {
		return rightAngleJeck;
	}

	public void setRightAngleJeck(List rightAngleJeck) {
		this.rightAngleJeck = rightAngleJeck;
	}

	
	
    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this;
        initImageLoader(getApplicationContext());
    }

    /** 获取Application */
    public static App getApp() {
        return mAppApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        // 整体摧毁的时候调用这个方法
    }

    /** 初始化ImageLoader */
    public static void initImageLoader(Context context) {
        String filePath = Environment.getExternalStorageDirectory() +
                "/Android/data/" + context.getPackageName() + "/cache/";

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, filePath);// 获取到缓存的目录地址
        Log.d("cacheDir", cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                        // .memoryCacheExtraOptions(480, 800) // max width, max
                        // height，即保存的每个缓存文件的最大长宽
                        // .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
                        // 75, null) // Can slow ImageLoader, use it carefully
                        // (Better don't use it)设置缓存的详细信息，最好不要设置这个
                        .threadPoolSize(3)// 线程池内加载的数量
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .memoryCache(new WeakMemoryCache())
                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
                        // * 1024)) // You can pass your own memory cache
                        // implementation你可以通过自己的内存缓存实现
                        .memoryCacheSize(2 * 1024 * 1024)
                        // /.discCacheSize(50 * 1024 * 1024)
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5
                                                                               // 加密
                        // .discCacheFileNameGenerator(new
                        // HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .discCacheFileCount(100) //缓存的File数量
                        .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        .imageDownloader(new BaseImageDownloader(context, 5 *
                                1000, 30 * 1000)) // connectTimeout (5 s),
                        // readTimeout(30)// 超时时间
                        .writeDebugLogs() // Remove for release app
                        .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }
    public void setTime_on_page(Map time_on_page)
	{
		this.time_on_page = time_on_page;
		
	}
	public void setRhythme(String rhythme)
	{
		this.rhythme = rhythme;
		
	}
	public void setGesture(int gesture)
	{
		this.gesture = gesture;
		
	}
	public void setPage_seq(List page_seq)
	{
		this.page_seq = page_seq;
		
	}
	
	public void setClick_in_time(List click_in_time){
		this.click_in_time = click_in_time;
	}
	public void setTime_count(int count)
	{
		this.time_count = count;
	}
	
	
	public void setUpVerticaldistance(List UpVerticaldistance)
	{
		this.UpVerticaldistance = UpVerticaldistance;
	}
	public void setUpHorizontaldistance(List UpHorizontaldistance)
	{
		this.UpHorizontaldistance = UpHorizontaldistance;
	}
	public void setUpDistance(List UpDistance)
	{
		this.UpDistance = UpDistance;
	}
	public void setUpStraightness(List UpStraightness)
	{
		this.UpStraightness = UpStraightness;
	}
	public void setUpAcceleration(List UpAcceleration)
	{
		this.UpAcceleration = UpAcceleration;
	}
	public void setUpspeed(List Upspeed)
	{
		this.Upspeed = Upspeed;
	}
	public void setUpVerticalspeed(List UpVerticalspeed)
	{
		this.UpVerticalspeed =UpVerticalspeed;
	}
	public void setUpHorizontalspeed(List UpHorizontalspeed)
	{
		this.UpHorizontalspeed = UpHorizontalspeed;
	}
	
	
	
	public void setDownVerticaldistance(List DownVerticaldistance)
	{
		this.DownVerticaldistance = DownVerticaldistance;
	}
	public void setDownHorizontaldistance(List DownHorizontaldistance)
	{
		this.DownHorizontaldistance = DownHorizontaldistance;
	}
	public void setDownDistance(List DownDistance)
	{
		this.DownDistance = DownDistance;
	}
	public void setDownStraightness(List DownStraightness)
	{
		this.DownStraightness = DownStraightness;
	}
	public void setDownAcceleration(List DownAcceleration)
	{
		this.DownAcceleration = DownAcceleration;
	}
	public void setDownSpeed(List DownSpeed)
	{
		this.Downspeed = DownSpeed;
	}
	public void setDownVerticalspeed(List DownVerticalspeed)
	{
		this.DownVerticalspeed =DownVerticalspeed;
	}
	public void setDownHorizontalspeed(List DownHorizontalspeed)
	{
		this.DownHorizontalspeed = DownHorizontalspeed;
	}
	
	
	
	public void setLeftVerticaldistance(List LeftVerticaldistance)
	{
		this.leftVerticaldistance = LeftVerticaldistance;
	}
	public void setLeftHorizontaldistance(List LeftHorizontaldistance)
	{
		this.leftHorizontaldistance = leftHorizontaldistance;
	}
	public void setLeftDistance(List leftDistance)
	{
		this.leftDistance = leftDistance;
	}
	public void setLeftStraightness(List leftStraightness)
	{
		this.leftStraightness = leftStraightness;
	}
	public void setLeftAcceleration(List LeftAcceleration)
	{
		this.leftAcceleration = leftAcceleration;
	}
	public void setLeftSpeed(List leftspeed)
	{
		this.leftspeed = leftspeed;
	}
	public void setLeftVerticalspeed(List LeftVerticalspeed)
	{
		this.leftVerticalspeed =LeftVerticalspeed;
	}
	public void setLeftHorizontalspeed(List leftHorizontalspeed)
	{
		this.leftHorizontalspeed = leftHorizontalspeed;
	}
	
	
	public void setRightVerticaldistance(List rightVerticaldistance)
	{
		this.rightVerticaldistance = rightVerticaldistance;
	}
	public void setRightHorizontaldistance(List rightHorizontaldistance)
	{
		this.rightHorizontaldistance = rightHorizontaldistance;
	}
	public void setRightDistance(List rightDistance)
	{
		this.rightDistance = rightDistance;
	}
	public void setRightStraightness(List rightStraightness)
	{
		this.rightStraightness = rightStraightness;
	}
	public void setRightAcceleration(List rightAcceleration)
	{
		this.rightAcceleration =rightAcceleration;
	}
	public void setRightSpeed(List rightspeed)
	{
		this.rightspeed = rightspeed;
	}
	public void setRightVerticalspeed(List rightVerticalspeed)
	{
		this.rightVerticalspeed =rightVerticalspeed;
	}
	public void setRightHorizontalspeed(List rightHorizontalspeed)
	{
		this.rightHorizontalspeed = rightHorizontalspeed;
	}
	public Map getTime_on_page()
	{
		return this.time_on_page;
	}
	public String getRhythme()
	{
		return this.rhythme;
		
	}
	
	public int getGesture()
	{
		return this.gesture;
		
	}
	public List getPage_seq()
	{
		return this.page_seq;
		
	}
	
	public List getClick_in_time(){
		return this.click_in_time;
	}
	
	public int getTime_count()
	{
		return this.time_count;
	}
	
	public List getUpVerticaldistance()
	{
		return this.UpVerticaldistance;
	}
	public List getUpHorizontaldistance()
	{
		return this.UpHorizontaldistance;
	}
	public List getUpDistance()
	{
		return this.UpDistance;
	}
	public List getUpStraightness()
	{
		return this.UpStraightness;
	}
	public List getUpAcceleration()
	{
		return this.UpAcceleration;
	}
	public List getUpspeed()
	{
		return this.Upspeed ;
	}
	public List getUpVerticalspeed()
	{
		return this.UpVerticalspeed;
	}
	public List getUpHorizontalspeed()
	{
		return this.UpHorizontalspeed;
	}
	
	
	
	public List getDownVerticaldistance()
	{
		return this.DownVerticaldistance;
	}
	public List getDownHorizontaldistance()
	{
		return this.DownHorizontaldistance;
	}
	public List getDownDistance()
	{
		return this.DownDistance;
	}
	public List getDownStraightness()
	{
		return this.DownStraightness;
	}
	public List getDownAcceleration()
	{
		return this.DownAcceleration;
	}
	public List getDownSpeed()
	{
		return this.Downspeed;
	}
	public List getDownVerticalspeed()
	{
		return this.DownVerticalspeed;
	}
	public List getDownHorizontalspeed()
	{
		return this.DownHorizontalspeed;
	}
	
	
	
	public List getLeftVerticaldistance()
	{
		return this.leftVerticaldistance;
	}
	public List getLeftHorizontaldistance()
	{
		return this.leftHorizontaldistance = leftHorizontaldistance;
	}
	public List getLeftDistance()
	{
		return this.leftDistance;
	}
	public List getLeftStraightness()
	{
		return this.leftStraightness;
	}
	public List getLeftAcceleration()
	{
		return this.leftAcceleration;
	}
	public List getleftSpeed()
	{
		return this.leftspeed;
	}
	public List getLeftVerticalspeed()
	{
		return this.leftVerticalspeed;
	}
	public List getLeftHorizontalspeed()
	{
		return this.leftHorizontalspeed;
	}
	
	
	public List getRightVerticaldistance()
	{
		return this.rightVerticaldistance;
	}
	public List getRightHorizontaldistance()
	{
		return this.rightHorizontaldistance;
	}
	public List getRightDistance()
	{
		return this.rightDistance;
	}
	public List getRightStraightness()
	{
		return this.rightStraightness;
	}
	public List getRightAcceleration()
	{
		return this.rightAcceleration;
	}
	public List getRightSpeed()
	{
		return this.rightspeed;
	}
	public List getRightVerticalspeed()
	{
		return this.rightVerticalspeed;
	}
	public List getRightHorizontalspeed()
	{
		return this.rightHorizontalspeed;
	}
	public void setUserID(String s)
	{
		this.UserID = s;
	}
	public String getUserId()
	{
		return this.UserID;
	}
	public void setIdenDBManager(IdenDBManager idma)
	{
		this.idm = idma;
	}
	public IdenDBManager getIdenDBManager()
	{
		return this.idm;
	}
	public void setWordsCount(List wc)
	{
		this.wordscount = wc;
	}
	public List getWordsCount()
	{
		return this.wordscount;
	}
	
	public void close()
	{
		time_on_page.clear();
		UserID= "";
		click_in_time.clear();
		page_seq.clear();
		UpHorizontalspeed.clear() ;
		UpVerticalspeed.clear();
		Upspeed.clear();
		UpAcceleration.clear();
		UpStraightness.clear();
		UpDistance.clear();
		UpHorizontaldistance.clear();
		UpVerticaldistance.clear();
		
		DownHorizontalspeed.clear();
		DownVerticalspeed.clear();
		Downspeed.clear();
		DownAcceleration.clear();
		DownStraightness.clear();
		DownDistance.clear();
		DownHorizontaldistance.clear();
		DownVerticaldistance.clear();
		
		leftHorizontalspeed.clear();
		leftVerticalspeed.clear();
		leftspeed.clear();
		leftAcceleration.clear();
		leftStraightness.clear();
		leftDistance.clear();
		leftHorizontaldistance.clear();
		leftVerticaldistance.clear();
		
		rightHorizontalspeed.clear();
		rightVerticalspeed.clear();
		rightspeed.clear();
		rightAcceleration.clear();
		rightStraightness.clear();
		rightDistance.clear();
		rightHorizontaldistance.clear();
		rightVerticaldistance.clear();
		
	}
}