package com.useridentify.util;




import io.vov.vitamio.utils.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import net.sf.javaml.classification.KDtreeKNN;
import net.sf.javaml.classification.MeanFeatureVotingClassifier;
import net.sf.javaml.classification.NearestMeanClassifier;
import net.sf.javaml.classification.bayes.NaiveBayesClassifier;
import net.sf.javaml.classification.tree.RandomForest;
import net.sf.javaml.classification.tree.RandomTree;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;





public class IdenClasser {
	public Map<String,Integer> WinnowClasser(Dataset train,Dataset test)
	{
		NearestMeanClassifier nmc = new NearestMeanClassifier();
		RandomTree rt = new RandomTree(13, new Random());
		KDtreeKNN kdtree = new KDtreeKNN(2);
		MeanFeatureVotingClassifier mean = new MeanFeatureVotingClassifier();
		nmc.buildClassifier(train);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i =0;i<test.size();i++)
		{
			String UserId = nmc.classify(test.get(i)).toString();
			if(!map.containsKey(UserId))
				map.put(UserId.toString(), 1);
			else
			{
				int b = (int)map.get(UserId);
				map.put(UserId.toString(), ++b);;
			}
		} 
		
		return map;
	}
	
	
//	public Dataset datasetMerge(Dataset d1,Dataset d2,Dataset d3,Dataset d4)//合并结果集
//	{
//		Dataset d=new  DefaultDataset();
//		for(int i = 0;i<d1.size();i++)
//		{
//			d.add(d1.get(i));
//		}
//		for(int i = 0;i<d2.size();i++)
//		{
//			d.add(d2.get(i));
//		}
//		for(int i = 0;i<d3.size();i++)
//		{
//			d.add(d3.get(i));
//		}
//		for(int i = 0;i<d4.size();i++)
//		{
//			d.add(d4.get(i));
//		}
//		return d;
//	}
	//获得手势Dataset
	public Dataset getDatasetGesture(List<Long> Inte,List<Double> Horizontalspeed,List<Double> Verticalspeed,
			   List<Double> speed,List<Double> Acceleration,
			   List<Double> Distance ,List<Double> Horizontaldistance ,
			   List<Double> Verticaldistance,List<Double> Angle,List<Double> AngleSpeed,List<Double> AngleAcclerate,List<Double> Jerk,
			   List<Double> AngleJeck,List<Double> Time,String UserId)
	{
		int size=Inte.size()<Horizontalspeed.size()?Inte.size():Horizontalspeed.size();
		Dataset dataset = new DefaultDataset();
		for(int i = 0;i<size;i++)
		{
			int j = 0;
			String USERID = UserId;
			//if(i-begin>=0)j=i-begin;
			double INTE = Double.parseDouble(Inte.get(j).toString());
        	double SPEED = speed.get(i);
        	double time =Time.get(i); 
        	double HORIZONTALSPEED = Horizontalspeed.get(i);
        	double VERTICALSPEED = Verticalspeed.get(i);
        	double VERTICALDISTANCE =(double)Verticaldistance.get(i);
        	double HORIZONTALDISTANCR = (double)Horizontaldistance.get(i);
        	double ACCELERATION = (double)Acceleration.get(i);
        	double ANGLE=Angle.get(i);
        	double ANGLESPEED=AngleSpeed.get(i);
       	 	double ANGLEACCLERATE=AngleAcclerate.get(i);
       	 	double JECK =Jerk.get(i);
       	 	double ANGLEJECK=AngleJeck.get(i);
       	 	double TIME=Time.get(i);   
        	double DISTANCE = (double)Distance.get(i);
        	//Log.e("DataDISTANCE", ""+DISTANCE);
        	double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,ANGLE,ANGLESPEED,ANGLEACCLERATE,JECK,ANGLEJECK,TIME, DISTANCE};
        	//double[] values = new double[] { SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK,TIME, DISTANCE};
        	//double[] values = new double[] { INTE,SPEED,HORIZONTALSPEED, VERTICALSPEED, VERTICALDISTANCE, HORIZONTALDISTANCR, ACCELERATION,JECK, DISTANCE};
        	Instance ins = new DenseInstance(values,null); 
        	dataset.add(ins);  	
		}
		return dataset;
	}
	public Dataset getDatasetBrowing(Map<String,Long> pageBroswing,
            String UserID,List<Integer> wordscount)
	{
		Dataset dataset= new DefaultDataset();
		int i = 0;
		 for (Map.Entry<String, Long> entry : pageBroswing.entrySet()) 
		 { 
			 	String USERID =  UserID;
			 	double wc = Double.parseDouble((wordscount.get(i).toString()));
			 	double BROWSINGSPEED = wc/Double.parseDouble(entry.getValue().toString());
		    	double BROWSINGTIME =Double.parseDouble(entry.getValue().toString());
		    	double[] values = new double[] { BROWSINGSPEED, BROWSINGTIME};
		    	Instance instance = new DenseInstance(values);
		    	dataset.add(instance);  
		 }
			
		return dataset;
	}
	
	
		 
	public String print(Map<String,Integer> map)
	{
		String result="";
		for(Map.Entry<String, Integer> entry : map.entrySet())    
		{    
			result+=entry.getKey()+": "+entry.getValue()+"\n";    
		} 
		return result;
	}
	public String getResult(Map<String,Integer> map,double totalcount)
	{
		double max=0;
		String result="";
		double total=0;
		for(Map.Entry<String, Integer> entry : map.entrySet())    
		{    
			total+=(double)entry.getValue();
			if(max<(double)entry.getValue())
			{
				max = (double)entry.getValue();
				result = entry.getKey();
				
			}    
		} 
		Log.e("max="+max+" total="+total+" "+max/total,"max="+max+" total="+total+" "+max/total );
		if(totalcount==2)return result;
		if(totalcount>2&&totalcount-max>2)return "请注册";//最大未超过50%
		return result;
	}
	public int getCounts(Map<String,Integer> map)
	{
		int result=0;
		for(Map.Entry<String, Integer> entry : map.entrySet())    
		{    
			result+=(int)entry.getValue();    
		} 
		
		return result;
		
		
	}
	//合并结果集
	public Map<String,Integer> merge(Map<String,Integer> m1,Map<String,Integer> m2,Map<String,Integer> m3,Map<String,Integer> m4,Map<String,Integer> m5)
	{
		Map<String,Integer> totalmap = m1;
		for(Map.Entry<String, Integer> entry : m2.entrySet())  
		{    
			if(totalmap.containsKey(entry.getKey()))
			{
				int count =totalmap.get(entry.getKey());
				count++;
				totalmap.put(entry.getKey(), count);
			}
			else
			{
				totalmap.put(entry.getKey(), 0);
			}
			
		}
		for(Map.Entry<String, Integer> entry : m3.entrySet())    
		{    
			if(totalmap.containsKey(entry.getKey()))
			{
				int count =totalmap.get(entry.getKey());
				count++;
				totalmap.put(entry.getKey(), count);
			}
			else
			{
				totalmap.put(entry.getKey(), 0);
			}
		}
		for(Map.Entry<String, Integer> entry : m4.entrySet())    
		{    
			if(totalmap.containsKey(entry.getKey()))
			{
				int count =totalmap.get(entry.getKey());
				count++;
				totalmap.put(entry.getKey(), count);
			}
			else
			{
				totalmap.put(entry.getKey(), 0);
			}
		}
		for(Map.Entry<String, Integer> entry : m5.entrySet())    
		{    
			if(totalmap.containsKey(entry.getKey()))
			{
				int count =totalmap.get(entry.getKey());
				count++;
				totalmap.put(entry.getKey(), count);
			}
			else
			{
				totalmap.put(entry.getKey(), 0);
			}
		}
		return totalmap;
	}
	
	public String identification( Map<String,Integer> m1)
	{
		double total=0,result=0;
		for(Map.Entry<String, Integer> entry : m1.entrySet())    
		{    
			total+=Double.parseDouble(entry.getValue().toString());
			//Log.e(entry.getKey()+" "+total,entry.getKey()+"  "+total);
		} 
		String userid="none";
		for(Map.Entry<String, Integer> entry : m1.entrySet())    
		{    
			//Log.e(""+entry.getValue()+"  "+entry.getKey(), ""+entry.getValue()+"  "+entry.getKey());
			if(entry.getValue()/total>0.8)
			{
				userid=entry.getKey();
			}    
		} 
		return userid;
	}
	public String getFinalResult(List<String> users,double totalcount)
	{
		Map<String,Integer> map = new HashMap<String,Integer>();  
		   
        for (String temp : users) {  
            Integer count = map.get(temp);  
            map.put(temp, (count == null) ? 1 : count + 1);  
        }  
        
        return getResult(map,totalcount);
	}
}
