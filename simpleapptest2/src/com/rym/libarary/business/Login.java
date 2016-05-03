package com.rym.libarary.business;



import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class Login extends AutoTestBase{
	
	private static Elements_StartPage_Host elements_StartPage_Host=new Elements_StartPage_Host(driver);
	private static Elements_PersonalPage elements_PersonalPage=new Elements_PersonalPage(driver);
	/**
	 * 选择appid 进入simple
	 */
	public static void startApp(String appID)
	{
		//腾讯gt的确定按钮
		if(appOperate.waitForText(10, "确定"))
		{
			appOperate.click(driver.findElement(By.name("确定")), "点击 腾讯gt确认按钮");
		}
		appOperate.click(elements_StartPage_Host.HostAppIDSelect, "点击 appid  进行选择");
		appOperate.click(driver.findElement(By.name(appID)), " appid  选择为 "+appID);
		appOperate.click(elements_StartPage_Host.HostStartClick, "点击  进入页面 ");
		Sleep.sleep(3);
	}
	
	/**
	 * 登陆H5高门槛一帐通，帐密输入
	 */
	public static void loginyztByH5(String login_name,String login_password)
	{
		 if(appOperate.waitForText(20, "一账通号/手机号/身份证号/邮箱"))
    	 {
    		 Log.logInfo("已弹出登录");
			appOperate.click(elements_PersonalPage.ClickAccount, "点击 账号 进行输入");
			appOperate.sendKeys(elements_PersonalPage.ClickAccount, "输入 账号", login_name);
		 	appOperate.click(elements_PersonalPage.ClickPassword, "点击 密码 进行输入");
			appOperate.sendKeys(elements_PersonalPage.ClickPassword, "输入 密码", login_password);
		 	appOperate.hideKeyboard();
		 	Sleep.sleep(2);
		 	appOperate.click(elements_PersonalPage.Clicklogin, "找到［登 录］按钮，并点击!");
    	 }
		   Sleep.sleep(20);
	}
		
	/**
	 * 模拟宿主一账通登录
	 */
	public static void loginyztByHost(String login_HostName,Boolean IfPersonalCenter)
	{
		if(!IfPersonalCenter)
		{
			 if(appOperate.waitForText(20, "一账通"))
	    	 {	
				 appOperate.click(driver.findElement(By.name("一账通")), "点击 一账通");
				 if(appOperate.waitForText(20, "宿主"))
		    	 {	
					 appOperate.click(elements_PersonalPage.ClickByHost, "点击 宿主登录"); 
		    	 }
	    	 }
		}
		 if(appOperate.waitForText(20, "返回"))
    	 {	
			 appOperate.click(elements_PersonalPage.ClickHostname, "点击 选择登陆用户");
	//		 Log.logInfo("页面元素如下："+driver.getPageSource());
			
		//	 appOperate.click(driver.findElement(By.xpath("//*[@value='"+login_HostName+"']")), "选择宿主登陆账号");
			 if(driver instanceof IOSDriver)
			 {
				 appOperate.sendKeys(driver.findElement(By.xpath("//UIAPickerWheel")), "输入宿主账号", login_HostName);
				 appOperate.click(driver.findElement(By.name("确认")), "ios 点击 确认选择用户");
			 }
			 appOperate.click(elements_PersonalPage.ClickHostLogin, "点击登陆");
    	 }
		  Sleep.sleep(10);
		 while(true)
		 {
			 if(appOperate.waitForText(20, "选择"))
	    	 {	
				 appOperate.click(elements_PersonalPage.ClickHostLogin, "点击登陆");
				 Sleep.sleep(10);
	    	 }else
	    	 {break;}
		 }
	}
	
	/**
	 * 模拟宿主非一账通登陆
	 */
	public static void loginNoyztByHost(String login_HostName)
	{
		 if(appOperate.waitForText(20, "非一账通"))
    	 {	
			 appOperate.click(driver.findElement(By.name("非一账通")), "点击 非一账通");
			 appOperate.click(elements_PersonalPage.ClickByHost, "点击 宿主登陆");
			 if(appOperate.waitForText(20, "返回"))
	    	 {	
				 appOperate.click(elements_PersonalPage.ClickHostname, "点击 选择登陆用户");
	    	 }
	//		 Log.logInfo("页面元素如下："+driver.getPageSource());
		//	 appOperate.click(driver.findElement(By.xpath("//*[@value='"+login_HostName+"']")), "选择宿主登陆账号");
			 if(driver instanceof IOSDriver)
			 {
				 appOperate.sendKeys(driver.findElement(By.xpath("//UIAPickerWheel")), "输入宿主账号", login_HostName);
				 appOperate.click(driver.findElement(By.name("确认")), "ios 点击 确认选择用户");
			 }
			 appOperate.click(elements_PersonalPage.ClickHostLogin, "点击登陆");
    	 }
		  Sleep.sleep(20);
	}
	
	/**
	 * 登陆一帐通，低门槛帐密输入
	 */
	public static void loginyztByLow(String login_name,String login_password)
	{
		 if(appOperate.waitForText(20, "密码登录"))
    	 {
    		 Log.logInfo("已弹出登录");
    		 appOperate.click(driver.findElement(By.xpath("//UIALink[@name='密码登录']")), "点击 密码登录");
    		 Sleep.sleep(5);
    		 if(appOperate.waitForText(20, "注册账号"))
        	 {
    			 appOperate.click(elements_PersonalPage.ClickAccountLow, "点击 账号 进行输入");
    			 appOperate.sendKeys(elements_PersonalPage.ClickAccountLow, "输入 账号", login_name);
    			 appOperate.click(elements_PersonalPage.ClickPasswordLow, "点击 密码 进行输入");
    			 appOperate.sendKeys(elements_PersonalPage.ClickPasswordLow, "输入 密码", login_password);
    			 appOperate.hideKeyboard();
    			 Sleep.sleep(2);
    	//		 Log.logInfo("页面元素如下："+driver.getPageSource());
    			 appOperate.click(elements_PersonalPage.ClickloginLow, "找到［登 录］按钮，并点击!");
        	 }
			
    	 }
		   Sleep.sleep(20);
			 //通过死循环去判断是否登录成功
			 while(true)
			 {
				 if(appOperate.waitForText(5, "登 录"))
				 {
					 appOperate.click(elements_PersonalPage.ClickloginLow, "找到［登 录］按钮，并点击!");
					 Sleep.sleep(5);
				 }else
				 {
					 break;
				 }
			 }
	}
	/**
	 * 宿主一账通登陆,打开个人中心
	 */
	public static void loginyztByHostgo(String login_HostName)
	{
		 if(appOperate.waitForText(20, "一账通"))
    	 {	
			 appOperate.click(driver.findElement(By.name("一账通")), "点击 非一账通");
			 appOperate.click(elements_PersonalPage.ClickByHost, "点击 宿主登陆");
			 if(appOperate.waitForText(20, "返回"))
	    	 {	
				 appOperate.click(elements_PersonalPage.ClickHostname, "点击 选择登陆用户");
				 if(driver instanceof IOSDriver)
				 {
					 appOperate.sendKeys(driver.findElement(By.xpath("//UIAPickerWheel")), "输入宿主账号", login_HostName);
					 appOperate.click(driver.findElement(By.name("确认")), "ios 点击 确认选择用户");
				 }
				 appOperate.click(elements_PersonalPage.ClickHostLogin, "点击登陆");
	    	 }
		  Sleep.sleep(20);
    	 }
		 //通过死循环去判断是否登录成功
		 while(true)
		 {
			 if(appOperate.waitForText(5, login_HostName))
			 {
				 appOperate.click(elements_PersonalPage.ClickHostLogin, "点击登陆");
				 Sleep.sleep(5);
			 }else
			 {
				 break;
			 }
		 }
	}
}
