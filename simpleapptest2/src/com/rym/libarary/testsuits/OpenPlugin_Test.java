package com.rym.libarary.testsuits;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.sf.json.JSONArray;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.GetPluginID;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class OpenPlugin_Test extends AutoTestBase {
	
	JSONArray listneedLogin=new JSONArray();

//	@DataProvider(name = "PluginList_url")
//	public static Object[][] PluginList_url() {
//		return new Object[][] { {
//		// new
//		// String("https://maam-dmzstg2.pingan.com.cn:9041/maam/getPluginList.do?sdkVersion=3.1.0.392&osVersion=7.1.2&deviceId=2abb53d0f829972ba905cca44662d89f1fd4fcda&appId=PA01100000000_01_SDK&appVersion=1.0&deviceType=ios")
//		new String(
//				"https://maam-dmzstg2.pingan.com.cn:9041/maam/getPluginList.do?sdkVersion=3.1.0.392&osVersion=9.0&deviceId=8f023bbd000d58e34cfea2fdca1a8901d3123291&appId=PA01100000000_01_SDK&appVersion=1.0&deviceType=ios") } };
//	}

	//
	// @Test(groups={"p2"},dataProvider="PluginList_url")
//	 @Test(groups={"p2"})
//	 public void clickPluginList()
//	 {
//		 Sleep.sleep(2);
//		 appOperate.swipeToLeft();
//		 List<WebElement> listelements=driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
//		 for (int i = 0; i < listelements.size(); i++) {
//			 Log.logInfo("寻找控件"+listelements.get(i).getText());
//			 Boolean
//			 flag=appOperate.elementExists(10,By.name(listelements.get(i).getText()));
//			 if(flag)
//			 {
//				 appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),
//				 "点击 "+listelements.get(i).getText());
//				 Sleep.sleep(20);
////				 if(appOperate.elementExists(10,By.name("closeButton")))
////				 {
////					 appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
////				 }
//		//		  appOperate.click(driver.findElement(By.name("回到主屏")), "点击 回到主屏");
//				 Sleep.sleep(2);
//				 listelements.remove(i);
//			 }
//		 }
//	 }

	
//	 @Test(groups={"p3"})
//	 @Parameters({"PluginList_url"})
//	 public void clickPluginList(String PluginList_url)
//	 {
//		 JSONArray list=GetPluginID.GetPluginList(PluginList_url);
//		 for (int i = 0; i < list.size(); i++) {
//			 Log.logInfo("寻找控件"+list.getJSONObject(i).getString("name"));
//			 Boolean flag=appOperate.elementExists(10,By.name(list.getJSONObject(i).getString("pluginUid").toString()));
//			 if(flag)
//			 {
//				 appOperate.click(driver.findElement(By.name(list.getJSONObject(i).getString("pluginUid").toString())),
//				 "点击 "+list.getJSONObject(i).getString("name")+"当前点击次数："+i);
//				 if(list.getJSONObject(i).getString("needLogin").contains("Y"))
//				 {
//					 if(appOperate.waitForText(20, "一账通号/手机号/身份证号/邮箱"))
//					 {
//					 Log.logInfo("已弹出登录");
//					 }
//				 }else
//				 {
//					 Sleep.sleep(10);
//				 }
//				 
//					if (appOperate.waitForText(2, "htmlbackhome")) {
//						appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
//						Sleep.sleep(1);
//						if (appOperate.waitForText(2, "htmlbackhome")) {
//							appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
//						}
//					}
//					if (appOperate.waitForText(2, "closeButton")) {
//						appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
//						Sleep.sleep(1);
//						if (appOperate.waitForText(2, "closeButton")) {
//							appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
//							}
//					}
//				 Sleep.sleep(2);
//				 list.remove(i);
//			 }
//			 else
//			 {
//				appOperate.swipeToLeft();
//				List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
//			 }
//		 }
//	 }

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
								Log.logInfo("已弹出登录,删除强登录插件"+listneedLogin.getJSONObject(j).getString("name")+" 剩余需要强登录的插件个数为："+listneedLogin.size());
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
	
//	@Test(groups = { "p2" })
//	@Parameters({"PluginList_url"})
//	// @Test(groups={"p2"})
//	public void clickPluginList(String PluginList_url) {
//		JSONArray list = GetPluginID.GetPluginList(PluginList_url);
//		for (int i = 0; i < list.size(); i++) {
//			if (list.getJSONObject(i).getString("pluginUid").toString().contains("PA0")) {
//			} else {
//				Log.logInfo("移除插件id ："+ list.getJSONObject(i).getString("pluginUid"));
//				list.remove(i);
//			}
//		}
//		while (true) {
//			List<WebElement> listelements = driver.findElements(By.xpath("//UIAButton[contains(@name,'PA')]"));
//			for (int i = 0; i < listelements.size(); i++) {
//				for (int j = 0; j < list.size(); j++) {
//					if(listelements.get(i).getText().toString()==" "||listelements.get(i).getText().toString()=="")
//					{
//						Log.logInfo("空");
//						break;
//					}
//					Log.logInfo("对比插件id：   页面获取id为： "+ listelements.get(i).getText() + "  json获取页面id为： "+ list.getJSONObject(j).getString("pluginUid"));
//					if (listelements.get(i).getText().toString().equals(list.getJSONObject(j).getString("pluginUid").toString())) {
//						Boolean flag = appOperate.elementExists(10,By.name(listelements.get(i).getText()));
//						if (flag) {
//							appOperate.click(driver.findElement(By.name(listelements.get(i).getText())),"点击 " + listelements.get(i).getText());
//							if (list.getJSONObject(j).getString("needLogin").contains("Y")) {
//								if (appOperate.waitForText(20,"一账通号/手机号/身份证号/邮箱")) {
//									Log.logInfo("已弹出登录");
//								}
//							}
//							Sleep.sleep(5);
//							for (int m=0;m<10;m++) {
//								if(appOperate.waitForText(2, "htmlbackhome"))
//								{
//									Sleep.sleep(5);
//									if(appOperate.waitForText(2, "网速不给力 点击重试"))
//									{
//										appOperate.click(driver.findElement(By.name("网速不给力 点击重试")), "点击关闭");
//									}
//								}else
//								{
//								break;
//								}
//							}
//							if (appOperate.waitForText(2, "htmlbackhome")) {
//								appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
//								Sleep.sleep(1);
//								if (appOperate.waitForText(2, "htmlbackhome")) {
//									appOperate.click(driver.findElement(By.name("htmlbackhome")), "点击关闭");
//								}
//							}
//							if (appOperate.waitForText(2, "closeButton")) {
//								appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
//								Sleep.sleep(1);
//								if (appOperate.waitForText(2, "closeButton")) {
//									appOperate.click(driver.findElement(By.name("closeButton")), "点击关闭");
//									}
//							}
//							// //
//							// appOperate.click(driver.findElement(By.name("回到主屏")),
//							// "点击 回到主屏");
//							Sleep.sleep(2);
//							list.remove(j);
//							break;
//						}
//
//					}
//				}
//			}
//			if (list.size() > 0) {appOperate.swipeToLeft();
//			}
//		}
//	}

}
