package com.tiger.quicknews.activity;

import io.vov.vitamio.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.javaml.core.Dataset;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import com.tiger.quicknews.App;
import com.tiger.quicknews.R;
import com.useridentify.db.IdenDBManager;
import com.useridentify.util.IdenClasser;

import android.app.Activity;
import android.widget.TextView;

@Fullscreen
@EActivity(R.layout.activity_show_result)
public class ShowResultActivity extends Activity {
	private Map<String, Long> time_on_page;
	private String rhythme;
	private String UserId;
	private List<Integer> gesture;
	private List<Long> passtime;
	private List click_in_time;
	private List page_seq;
	private int time_count = 0;

	private List<Double> UpHorizontalspeed;
	private List<Double> UpVerticalspeed;
	private List<Double> Upspeed;
	private List<Double> UpAcceleration;
	private List<Double> UpDistance;
	private List<Double> UpHorizontaldistance;
	private List<Double> UpVerticaldistance;

	private List<Double> UpAngle;
	private List<Double> UpAngleSpeed;
	private List<Double> UpAngleAcclerate;
	private List<Double> UpJerk;
	private List<Double> UpAngleJeck;
	private List<Double> UpTime;

	private List<Long> UpInte;

	private List<Double> DownHorizontalspeed;
	private List<Double> DownVerticalspeed;
	private List<Double> Downspeed;
	private List<Double> DownAcceleration;
	private List<Double> DownDistance;
	private List<Double> DownHorizontaldistance;
	private List<Double> DownVerticaldistance;

	private List<Double> DownAngle;
	private List<Double> DownAngleSpeed;
	private List<Double> DownAngleAcclerate;
	private List<Double> DownJerk;
	private List<Double> DownAngleJeck;
	private List<Double> DownTime;

	private List<Long> DownInte;

	private List<Double> leftHorizontalspeed;
	private List<Double> leftVerticalspeed;
	private List<Double> leftspeed;
	private List<Double> leftAcceleration;
	private List<Double> leftDistance;
	private List<Double> leftHorizontaldistance;
	private List<Double> leftVerticaldistance;

	private List<Double> leftAngle;
	private List<Double> leftAngleSpeed;
	private List<Double> leftAngleAcclerate;
	private List<Double> leftJerk;
	private List<Double> leftAngleJeck;
	private List<Double> leftTime;
	private List<Long> leftInte;
	private List<Double> rightHorizontalspeed;
	private List<Double> rightVerticalspeed;
	private List<Double> rightspeed;
	private List<Double> rightAcceleration;
	private List<Double> rightDistance;
	private List<Double> rightHorizontaldistance;
	private List<Double> rightVerticaldistance;

	private List<Double> rightAngle;
	private List<Double> rightAngleSpeed;
	private List<Double> rightAngleAcclerate;
	private List<Double> rightJerk;
	private List<Double> rightAngleJeck;
	private List<Double> rightTime;
	private List<Long> rightInte;
	private List<Long> Inte;
	private List<Integer> wordscount;
	private App bean;
	private IdenDBManager idm;
	@ViewById(R.id.result)
	protected TextView result;
	@Extra("reOrlo")
	int reorlo;
	private IdenClasser idc;

	@AfterViews
	public void init() {
		bean = (App) getApplication();
		time_on_page = bean.getTime_on_page();
		rhythme = bean.getRhythme();
		UserId = bean.getUserId();
		// esture = bean.getGesture();
		click_in_time = bean.getClick_in_time();
		UpHorizontalspeed = bean.getUpHorizontalspeed();
		UpVerticalspeed = bean.getUpVerticalspeed();
		Upspeed = bean.getUpspeed();
		UpAcceleration = bean.getUpAcceleration();
		UpDistance = bean.getUpDistance();
		UpHorizontaldistance = bean.getUpHorizontaldistance();
		UpVerticaldistance = bean.getUpVerticaldistance();
		UpAngle = bean.getUpAngle();
		UpAngleSpeed = bean.getUpAngleSpeed();
		UpAngleAcclerate = bean.getUpAngleAcclerate();
		UpJerk = bean.getUpJerk();
		UpAngleJeck = bean.getUpAngleJeck();
		UpTime = bean.getUpTime();
		UpInte = bean.getUpInte();

		DownHorizontalspeed = bean.getDownHorizontalspeed();
		DownVerticalspeed = bean.getDownVerticalspeed();
		Downspeed = bean.getDownSpeed();
		DownAcceleration = bean.getDownAcceleration();
		DownDistance = bean.getDownDistance();
		DownHorizontaldistance = bean.getDownHorizontaldistance();
		DownVerticaldistance = bean.getDownVerticaldistance();
		DownAngle = bean.getDownAngle();
		DownAngleSpeed = bean.getDownAngleSpeed();
		DownAngleAcclerate = bean.getDownAngleAcclerate();
		DownJerk = bean.getDownJerk();
		DownAngleJeck = bean.getDownAngleJeck();
		DownTime = bean.getDownTime();
		DownInte = bean.getDownInte();

		leftHorizontalspeed = bean.getLeftHorizontalspeed();
		leftVerticalspeed = bean.getLeftVerticalspeed();
		leftspeed = bean.getleftSpeed();
		leftAcceleration = bean.getLeftAcceleration();
		leftDistance = bean.getLeftDistance();
		leftHorizontaldistance = bean.getLeftHorizontaldistance();
		leftVerticaldistance = bean.getLeftVerticaldistance();
		leftAngle = bean.getLeftangle();
		leftAngleSpeed = bean.getLeftAngleSpeed();
		leftAngleAcclerate = bean.getLeftAngleAcclerate();
		leftJerk = bean.getLeftJerk();
		leftAngleJeck = bean.getLeftAngleJeck();
		leftTime = bean.getLeftTime();
		leftInte = bean.getLeftInte();

		rightHorizontalspeed = bean.getRightHorizontalspeed();
		rightVerticalspeed = bean.getRightVerticalspeed();
		rightspeed = bean.getRightSpeed();
		rightAcceleration = bean.getRightAcceleration();
		rightDistance = bean.getRightDistance();
		rightHorizontaldistance = bean.getRightHorizontaldistance();
		rightVerticaldistance = bean.getRightVerticaldistance();
		rightAngle = bean.getRightangle();
		rightAngleSpeed = bean.getRightAngleSpeed();
		rightAngleAcclerate = bean.getRightAngleAcclerate();
		rightJerk = bean.getRightJerk();
		rightAngleJeck = bean.getRightAngleJeck();
		rightTime = bean.getRightTime();
		rightInte = bean.getRightInte();
		// Log.e("UpHorizontaldistance="+UpHorizontaldistance.rowsize(),
		// "UpHorizontaldistance="+UpHorizontaldistance.rowsize());
		wordscount = bean.getWordsCount();
		idm = new IdenDBManager(this);
		idc = new IdenClasser();
		String s = "";
		if (reorlo == 1)// 登陆
		{
			s = handleLogin();

		} else if (reorlo == 2)// 注册
		{
			s = handleRegister();
			LoginActivity_.intent(this).start();
		}
		result.setText(s);

	}

	public String handleRegister() {
		idm.insertBrowseing(time_on_page, UserId, wordscount);
		// idm.insertRhythm(UserId, click_in_time, gesture);
		idm.insertGestrue(UpInte, UpHorizontalspeed, UpVerticalspeed, Upspeed,
				UpAcceleration, UpDistance, UpHorizontaldistance,
				UpVerticaldistance, UpAngle, UpAngleSpeed, UpAngleAcclerate,
				UpJerk, UpAngleJeck, UpTime, "UpInfo", UserId);
		idm.insertGestrue(DownInte, DownHorizontalspeed, DownVerticalspeed,
				Downspeed, DownAcceleration, DownDistance,
				DownHorizontaldistance, DownVerticaldistance, DownAngle,
				DownAngleSpeed, DownAngleAcclerate, DownJerk, DownAngleJeck,
				DownTime, "DownInfo", UserId);
		idm.insertGestrue(leftInte, leftHorizontalspeed, leftVerticalspeed,
				leftspeed, leftAcceleration, leftDistance,
				leftHorizontaldistance, leftVerticaldistance, leftAngle,
				leftAngleSpeed, leftAngleAcclerate, leftJerk, leftAngleJeck,
				leftTime, "LeftInfo", UserId);
		idm.insertGestrue(rightInte, rightHorizontalspeed, rightVerticalspeed,
				rightspeed, rightAcceleration, rightDistance,
				rightHorizontaldistance, rightVerticaldistance, rightAngle,
				rightAngleSpeed, rightAngleAcclerate, rightJerk,
				rightAngleJeck, rightTime, "RightInfo", UserId);
		return "注册完成，亲爱的" + UserId;
	}

	public String handleLogin() {

		Map<String, Integer> resultright = new HashMap<String, Integer>(),
		// resultR=new HashMap<String,Integer>(),
		resultUp = new HashMap<String, Integer>(), resultDown = new HashMap<String, Integer>(), resultleft = new HashMap<String, Integer>(), resultBrow = new HashMap<String, Integer>(), resultTotal = new HashMap<String, Integer>();
		// Dataset trainBrow = idm.queryBrowsing();

		Dataset trainUp = idm.queryGestrue("UpInfo");
		Dataset trainDown = idm.queryGestrue("DownInfo");
		Dataset trainLeft = idm.queryGestrue("LeftInfo");
		Dataset trainRight = idm.queryGestrue("RightInfo");
		// Dataset trainnR = idm.queryRhythm();
		Dataset testUp = idc.getDatasetGesture(UpInte, UpHorizontalspeed,
				UpVerticalspeed, Upspeed, UpAcceleration, UpDistance,
				UpHorizontaldistance, UpVerticaldistance, UpAngle,
				UpAngleSpeed, UpAngleAcclerate, UpJerk, UpAngleJeck, UpTime,
				UserId);
		Dataset testDown = idc.getDatasetGesture(DownInte, DownHorizontalspeed,
				DownVerticalspeed, Downspeed, DownAcceleration, DownDistance,
				DownHorizontaldistance, DownVerticaldistance, DownAngle,
				DownAngleSpeed, DownAngleAcclerate, DownJerk, DownAngleJeck,
				DownTime, UserId);
		Dataset testleft = idc.getDatasetGesture(leftInte, leftHorizontalspeed,
				leftVerticalspeed, leftspeed, leftAcceleration, leftDistance,
				leftHorizontaldistance, leftVerticaldistance, leftAngle,
				leftAngleSpeed, leftAngleAcclerate, leftJerk, leftAngleJeck,
				leftTime, UserId);
		Dataset testright = idc.getDatasetGesture(rightInte,
				rightHorizontalspeed, rightVerticalspeed, rightspeed,
				rightAcceleration, rightDistance, rightHorizontaldistance,
				rightVerticaldistance, rightAngle, rightAngleSpeed,
				rightAngleAcclerate, rightJerk, rightAngleJeck, rightTime,
				UserId);
		ArrayList<String> users = (ArrayList<String>) idm.queryUser();
		int size = users.size(), i = 0, j = 1;
		String result="";
		double errornum=0,totalcount=0;
		List<String> userhit= new ArrayList<String>();
		while (i < size && j < size)// 取组合
		{
			trainUp = idm.queryTwoGestrue("UpInfo", users.get(i), users.get(j));
			trainDown = idm.queryTwoGestrue("DownInfo", users.get(i),users.get(j));
			trainLeft = idm.queryTwoGestrue("LeftInfo", users.get(i),users.get(j));
			trainRight = idm.queryTwoGestrue("RightInfo", users.get(i),users.get(j));
			if (trainUp.size() != 0 && testUp.size() != 0) {
				resultUp = idc.WinnowClasser(trainUp, testUp);

			}
			if (trainDown.size() != 0 && testDown.size() != 0) {

				resultDown = idc.WinnowClasser(trainDown, testDown);
			}
			if (trainLeft.size() != 0 && testleft.size() != 0) {
				resultleft = idc.WinnowClasser(trainLeft, testleft);

			}

			if (trainRight.size() != 0 && testright.size() != 0) {
				resultright = idc.WinnowClasser(trainRight, testright);

			}
			resultTotal = idc.merge(resultUp, resultDown, resultleft,
					resultright, resultBrow);
			String results=idc.identification(resultTotal);
			result+=idc.print(resultTotal)+"\n";
			if(results.equals("none"))errornum++;
			else userhit.add(results);
			if (j == size - 1) {
				i++;
				j = i;
			}
			j++;
			totalcount++;
		}
		if(errornum/totalcount>=0.5)return result+"请注册";
		else return result+idc.getFinalResult(userhit,users.size());
		// return idc.identification(resultTotal);
	}
	
}
