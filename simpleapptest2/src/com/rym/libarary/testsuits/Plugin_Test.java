package com.rym.libarary.testsuits;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.sf.json.JSONArray;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.GetPluginID;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class Plugin_Test extends AutoTestBase{
	
	
	/**
	 * 插件相关案例 By huxuebing on 2016.4.11
	 */
	JSONArray listneedLogin=new JSONArray();
	
	
//	@Test(groups = { "p3" })
	@Parameters({"PluginList_url"})
	public void clickPluginList2(String PluginList_url) {
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (list.getJSONObject(i).getString("needLogin").contains("Y")) {
				if(!list.getJSONObject(i).getString("displayScenarios").contains("P"))
				{
				Log.logInfo("需要登录插件id ："+ list.getJSONObject(i).getString("pluginUid"));
				listneedLogin.add(list.get(i));
				}
			}
		}
		Log.logInfo(listneedLogin.size());
		while (true) {
			List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
			Log.logInfo("当前页面插件个数为："+listelements.size());
			for (int i = 0; i < listelements.size(); i++) {
				Boolean flag = appOperate.elementExists(10,By.name(listelements.get(i).getText()));
				if (flag) {
					appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText()+"当前点击次数："+i);
					if (appOperate.waitForText(20,"一账通号/手机号/身份证号/邮箱")) {
						Log.logInfo("当前需要登录插件个数为："+listneedLogin.size());
						for (int j = 0; j < listneedLogin.size(); j++) {
							Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ listneedLogin.getJSONObject(j).getString("pluginUid"));
							if (listelements.get(i).getText().toString().equals(listneedLogin.getJSONObject(j).getString("pluginUid").toString())) {
								Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
								listneedLogin.remove(j);
								break;
							}
						}
						Log.logInfo("循环对比完");
					}			
					Sleep.sleep(2);
					if (appOperate.waitForText(2, "htmlbackhome")) {
						Sleep.sleep(2);
						Log.logInfo("进入点击htmlbackhome状态...");
						try {
							appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
						} catch (Exception e) {
							// TODO: handle exception
							Log.logError(e.getMessage());
						}
						Sleep.sleep(1);
						if (appOperate.waitForText(2, "htmlbackhome")) {
							appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
						}
					}
					if (appOperate.waitForText(2, "closeButton")) {
						appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
					}
					Sleep.sleep(2);
				}
			}
			if (listneedLogin.size() > 0) {appOperate.swipeToLeft();
			}else
			{
				Log.logInfo("案例完");
				break;
			}
		}
	}
	
	/**
	 * 4.2未登录时强登录插件是否弹出一账通登录页
	 */
	@Test(groups = { "p3" })
	@Parameters({"PluginList_url"})
	public void FindYZTByH5(String PluginList_url) {
		Log.logStep("4.2未登录时强登录插件是否弹出一账通登录页  开始跑");
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		listneedLogin=GetPluginID.GetNeedLoginPluginList(list);
		Log.logInfo(listneedLogin.size());
		ForCompared2("一账通号/手机号/身份证号/邮箱", "4.2未登录时强登录插件是否弹出一账通登录页 案例跑完",false);
	}
	
	/**
	 * 4.3未登录时强登录插件是否弹出宿主登录
	 */
	@Test(groups = { "p3" })
	@Parameters({"PluginList_url"})
	public void FindYZTByHost(String PluginList_url) {
		Log.logStep("4.3未登录时强登录插件是否弹出宿主登录  开始跑");
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		appOperate.click(driver.findElement(By.name("一账通")), "点击一账通模拟宿主登录");
		listneedLogin=GetPluginID.GetNeedLoginPluginList(list);
		Log.logInfo(listneedLogin.size());
		ForCompared2("选择", "4.3未登录时强登录插件是否弹出宿主登录 案例跑完",false);
	}
	
	/**
	 * 4.4非一账通登录时强登录插件是否弹出h5登录
	 */
	@Test(groups = { "p3" })
	@Parameters({"PluginList_url","login_HostName"})
	public void FindH5ByNoYZT(String PluginList_url,String login_HostName) {
		Log.logStep("4.4非一账通登录时强登录插件是否弹出h5登录  开始跑");
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		Login.loginNoyztByHost(login_HostName);
		listneedLogin=GetPluginID.GetNeedLoginPluginList(list);
		Log.logInfo(listneedLogin.size());
		ForCompared2("一账通登录", "4.4非一账通登录时强登录插件是否弹出h5登录 案例跑完",false);
	}
	
	/**
	 * 4.5一账通登录时打开强登陆插件是否能正常进入
	 */
	@Test(groups = { "p3" })
	@Parameters({"PluginList_url","login_HostName"})
	public void clickNeedLoginPlugin(String PluginList_url,String login_HostName)
	{
		Log.logStep("4.5一账通登录时打开强登陆插件是否能正常进入  开始跑");
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		Login.loginyztByHost(login_HostName,false);
		listneedLogin=GetPluginID.GetNeedLoginPluginList(list);
		Log.logInfo(listneedLogin.size());
		ForCompared2("一账通登录", "4.5一账通登录时打开强登陆插件是否能正常进入 案例跑完", true);
	}
	
	/**
	 * 4.6一账通登录后等待15分钟点击强登录插件
	 */
	@Test(groups = { "p3" })
	@Parameters({"PluginList_url","login_HostName"})
	public void YZTTimeout(String PluginList_url,String login_HostName)
	{
		int a=0;
		//设置一个boolean值 当查询到一个强登陆插件在当前页面点击成功时就退出所有的循环
		Log.logStep("4.6一账通登录后等待15分钟点击强登录插件  开始跑");
		Boolean Timeout=false;
		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
		Log.logInfo(list.size());
		Login.loginyztByHost(login_HostName,false);
		listneedLogin=GetPluginID.GetNeedLoginPluginList(list);
		Log.logInfo(listneedLogin.size());
		for (int i = 0; i < 70; i++) {
			
			appOperate.swipeToDown(1000);
			Sleep.sleep(10);
		}
//		Sleep.sleep(60);
		while (true) {
			List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
			Log.logInfo("当前页面插件个数为："+listelements.size());
			for(int j = 0; j < listneedLogin.size(); j++)
			{
				for (int i = 0; i < listelements.size(); i++)
				{
					Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ listneedLogin.getJSONObject(j).getString("pluginUid"));
					if (listelements.get(i).getText().toString().equals(listneedLogin.getJSONObject(j).getString("pluginUid").toString())) 
					{
						appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText()+"当前点击次数："+i);
						if(appOperate.waitForText(15, "一账通登录")||appOperate.waitForText(15, "选择"))
						{
							Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
					//		appOperate.click(driver.findElement(By.name("返回")), "点击返回按钮");
							//超时后查询到强登陆插件已打开并弹出登录页
							Timeout=true;
							break;
						}else
						{
							appOperate.closeH5();
							break;
						}
					}
				}
				//如果超时后查询到强登陆插件已打开并弹出登录页跳出循环
				if(Timeout)
				{
					break;
				}
				
			}
			//如果超时后查询到强登陆插件已打开并弹出登录页跳出循环
			if(Timeout)
			{
				Log.logInfo("4.6一账通登录后等待15分钟点击强登录插件 案例已成功跑完");
				break;
			}
			
			if (listneedLogin.size() > 0) 
			{
				if(a>=1)
				{
					appOperate.swipeToRight();
					a=0;
				}else
				{
				appOperate.swipeToLeft();
				a=a+1;
				}
			}else
			{
				break;
			}
		}
	
	}
	
	/**
	 * 4.7单行和盖楼模式切换
	 */
	@Test(groups = { "p3" })
	public void clickTable()
	{
		Log.logStep("4.7单行和盖楼模式切换  开始跑");
		if(appOperate.waitForText(20, "一账通"))
		{
			appOperate.swipeToLeft();
			Sleep.sleep(3);
			WebElement e=driver.findElement(By.name("exchangeButton"));
			int Y1=e.getLocation().getY();
			Log.logInfo(Y1);
			appOperate.click(e, "点击切换插件模式");
			Sleep.sleep(3);
			int Y2=(driver.findElement(By.name("exchangeButton"))).getLocation().getY();
			Log.logInfo(Y2);
			if (Y1!=Y2)
			{
				Log.logInfo("4.7单行和盖楼模式切换 案例已成功跑完");
			}
		}
		
	}
	
	public void ForCompared(String waitText,String LogText)
	{
		//循环次数，如果超过1次就右滑
				int a=0;
		while (true) {
			List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
			Log.logInfo("当前页面插件个数为："+listelements.size());
			if(listelements.size()==0)
			{
				appOperate.closeH5();
			}else
			{
				for (int i = 0; i < listelements.size(); i++) {
					Boolean flag = appOperate.elementExists(10,By.name(listelements.get(i).getText()));
					if (flag) {
						appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText()+"当前点击次数："+i);
						if (appOperate.waitForText(15,waitText)) {
							Log.logInfo("当前需要登录插件个数为："+listneedLogin.size());
							for (int j = 0; j < listneedLogin.size(); j++) {
								Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ listneedLogin.getJSONObject(j).getString("pluginUid"));
								if (listelements.get(i).getText().toString().equals(listneedLogin.getJSONObject(j).getString("pluginUid").toString())) {
									Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
									listneedLogin.remove(j);
									break;
								}
							}
							Log.logInfo("循环对比完");
							if(waitText.equals("选择"))
							{
								appOperate.click(driver.findElement(By.name("返回")), "点击返回按钮");
							}else
							{
								appOperate.closeH5();
								
							}
						}	
						else
						{
							appOperate.closeH5();
						}
						
						Sleep.sleep(2);
					}
				}
			}
			if (listneedLogin.size() > 0) 
			{
				if(a>=1)
				{
					appOperate.swipeToRight();
					a=0;
				}else
				{
				appOperate.swipeToLeft();
				a=a+1;
				}
			}else
			{
				Log.logInfo(LogText);
				break;
			}
		}
	}
	
	//循环对比查找强登陆插件
	public void ForCompared2(String waitText,String LogText,boolean isYZT)
	{
		//循环次数，如果超过1次就右滑
		int a=0;
		while (true) 
		{
			List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
			Log.logInfo("当前页面插件个数为："+listelements.size());
			if(listelements.size()==0)
			{
				appOperate.closeH5();
			}else
			{
				for (int j = 0; j < listneedLogin.size(); j++) {
					for (int i = 0; i < listelements.size(); i++) {
						Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ listneedLogin.getJSONObject(j).getString("pluginUid"));
						if (listelements.get(i).getText().toString().equals(listneedLogin.getJSONObject(j).getString("pluginUid").toString())) {
							Log.logInfo(listelements.get(i).getText().toString()+"该强登录插件已找到");
							appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText()+"当前点击次数："+i);
							if(isYZT)
							{
								if (!appOperate.waitForText(15,waitText))
								{
									Log.logInfo("未弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
									listneedLogin.remove(j);
								}
							}else
							{
								if (appOperate.waitForText(15,waitText))
								{
									Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
									listneedLogin.remove(j);
								}
							}
							
							if(waitText.equals("选择"))
							{
								appOperate.click(driver.findElement(By.name("返回")), "点击返回按钮");
							}else
							{
								appOperate.closeH5();
							}
							break;
						}
					}
				}
			}

				if (listneedLogin.size() > 0) 
				{
					Log.logInfo(a);
					if(a>=1)
					{
						appOperate.swipeToRight();
						a=0;
					}else
					{
					appOperate.swipeToLeft();
					a=a+1;
					}
				}else
				{
					Log.logInfo(LogText);
					break;
				}
		}
	}
}
//					Boolean flag = appOperate.elementExists(10,By.name(listelements.get(i).getText()));
//					if (flag) {
//						appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText()+"当前点击次数："+i);
//						if (appOperate.waitForText(15,waitText)) {
//							Log.logInfo("当前需要登录插件个数为："+listneedLogin.size());
//							for (int j = 0; j < listneedLogin.size(); j++) {
//								Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ listneedLogin.getJSONObject(j).getString("pluginUid"));
//								if (listelements.get(i).getText().toString().equals(listneedLogin.getJSONObject(j).getString("pluginUid").toString())) {
//									Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+(listneedLogin.size()-1));
//									listneedLogin.remove(j);
//									break;
//								}
//							}
//							Log.logInfo("循环对比完");
//							if(waitText.equals("选择"))
//							{
//								appOperate.click(driver.findElement(By.name("返回")), "点击返回按钮");
//							}else
//							{
//								appOperate.closeH5();
//								
//							}
//						}	
//						else
//						{
//							appOperate.closeH5();
//						}
//						
//						Sleep.sleep(2);
//					}
//				}
//			}
//			if (listneedLogin.size() > 0) {appOperate.swipeToLeft();
//			}else
//			{
//				Log.logInfo(LogText);
//				break;
//			}
//		}
//	}
//	

