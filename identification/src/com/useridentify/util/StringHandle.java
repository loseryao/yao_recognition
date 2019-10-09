package com.useridentify.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.tiger.quicknews.R;
import com.useridentify.db.IdenDBManager;


public class StringHandle {
	private String s;
	public StringHandle(String str)
	{
		this.s = str;
		
	}
	public String clearString(String str)
	{
		String s=str.replaceAll(" ", "");
		return s; 
	}
	public int getWordsCount()
	{
		char[] t1 = null;
        t1 = s.toCharArray();
        int t0 = t1.length;
        int count = 0;
        for (int i = 0; i < t0; i++) 
        {
        	if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) 
        	{
        			count++;
           
        	}
        }
        return count;
	}
	public void Excute(InputStream is,IdenDBManager ids)
	{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String line = "";
		try {
			while ((line = in.readLine()) != null){
				Log.e(line, line);
				line = String.format(line);
				ids.excute(line);
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
