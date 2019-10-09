package com.useridentify.algorithm;


public class Lables {
	String labels[];
	public Lables(String lables[])
	{
		this.labels = lables;
	}
	int size()
	{
		if(labels!=null)
			return labels.length;
		return 0;
	}
	int getIndex(String label)
	{
		for(int i = 0;i<labels.length;i++)
		{
			if(label.equals(labels[i]))return i;
		}
		return -1;
	}
	String get(int index)
	{
		if(labels!=null)
			return labels[index];
		else return null;
	}
}
