package com.useridentify.algorithm;


public class Instance {
	private String lable;
	private double[] featrueVetor;
	private int index;
	
	public Instance(double vec[],String label,int index)
	{
		this.lable = label;
		this.featrueVetor  = vec;
		this.index = index;
	}
 
	public int size()
	{
		return featrueVetor.length;
	}
	public String getLable()
	{
		return lable;
	}
	public double[] getData()
	{
		return featrueVetor;
	}
	public int getindex()
	{
		return index;
	}
}
