package com.useridentify.algorithm;


import java.util.ArrayList;
import java.util.Set;

public class Dataset {
	private ArrayList<Instance> InstanceList= new ArrayList<Instance>();
	public void add(Instance ins)
	{
		if(InstanceList!=null)
			InstanceList.add(ins);
		
	}
	public Instance get(int i)
	{
		if(!InstanceList.isEmpty())
			return InstanceList.get(i);
		return null;
	}
	
	public int rowsize()
	{
		if(!InstanceList.isEmpty())
			return InstanceList.size();
		return 0;
	}
	public int cowsize()
	{
		if(!InstanceList.isEmpty())
			return InstanceList.get(0).size();
		return 0;
	}

}
