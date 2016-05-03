package com.rym.libarary.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.annotations.Parameters;

import com.rym.libarary.utils.Log;


public class GetPluginID {
	
	public static JSONArray list;
	
//	public static void main(String[] args)
//	{
//		GetPluginList("https://maam-dmzstg2.pingan.com.cn:9041/maam/getPluginList.do?sdkVersion=3.1.0.392&osVersion=7.1.2&deviceId=2abb53d0f829972ba905cca44662d89f1fd4fcda&appId=PA01100000000_01_SDK&appVersion=1.0&deviceType=ios");
//	}
//	
	
	public static JSONArray GetPluginList(String PluginList_url)
	{
		Log.logInfo("获取插件列表");
		String json=loadJSON(PluginList_url);
		JSONObject dataJson=JSONObject.fromObject(json);
		JSONObject dataList=dataJson.getJSONObject("body");
		list=dataList.getJSONArray("data");
		for (int i = 0; i < list.size(); i++) {
			Log.logInfo(list.getJSONObject(i).getString("name")+"  ----  "+list.getJSONObject(i).getString("pluginUid")+"  ----  "+list.getJSONObject(i).getString("needLogin"));	
		}
		return list;
	}
	
	public static String loadJSON(String PluginList_url)
	{
		StringBuilder json=new StringBuilder();
		try
		{
			URL oracle=new URL(PluginList_url);
			URLConnection yc=oracle.openConnection();
			BufferedReader br=new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine=null;
			while((inputLine=br.readLine())!=null)
			{
				json.append(inputLine);
			}
			br.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static JSONArray GetNeedLoginPluginList(JSONArray list)
	{
		JSONArray NeedLoginPluginList=new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			if (list.getJSONObject(i).getString("needLogin").contains("Y")) {
				if(!list.getJSONObject(i).getString("displayScenarios").contains("P"))
				{
				Log.logInfo("需要登录插件id ："+ list.getJSONObject(i).getString("pluginUid"));
				NeedLoginPluginList.add(list.get(i));
				}
			}
		}
		return NeedLoginPluginList;
	}
	
}
