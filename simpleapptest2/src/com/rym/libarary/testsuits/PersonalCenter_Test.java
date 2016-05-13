package com.rym.libarary.testsuits;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class PersonalCenter_Test extends AutoTestBase{
	
	/**
	 * 个人中心用例1-1-2（插件为强登，用户未登陆），模拟h5一账通登录
	 */
	@Test(groups={"p1"})
	@Parameters({"LoginPersonalCenterName","login_name","login_password"})
	public void LoginPersonalCenterByH5(String LoginPersonalCenterName,String login_name,String login_password)
	{
		Log.logStep("个人中心用例1-1-2（插件为强登，用户未登陆），模拟h5一账通登录 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
		}
		Login.loginyztByH5(login_name, login_password);
		Log.logInfo("个人中心用例1-1-2（插件为强登，用户未登陆），模拟h5一账通登录 案例成功跑完");
	}
	
	/**
	 * 个人中心用例1-1-1（插件为强登，用户未登陆），模拟宿主一账通登录
	 */
	@Test(groups={"p1"})
	@Parameters({"LoginPersonalCenterName","login_HostName"})
	public void LoginPersonalCenterByHost(String LoginPersonalCenterName,String login_HostName)
	{
		Log.logStep("个人中心用例1-1-1（插件为强登，用户未登陆），模拟宿主一账通登录 开始跑");
		 if(appOperate.waitForText(20, "一账通"))
    	 {	
			 appOperate.click(driver.findElement(By.name("一账通")), "点击 一账通");
			 
    	 }
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
		}
		Login.loginyztByHost(login_HostName,true);
		Log.logInfo("个人中心用例1-1-1（插件为强登，用户未登陆），模拟宿主一账通登录 案例成功跑完");
	}
	
	/**
	 *  个人中心用例1-2（插件为强登，用户非一账通登陆），模拟H5低门槛一账通登录
	 */
//	@Test(groups={"p1"})
	@Parameters({"LoginPersonalCenterName","login_name","login_password","login_HostName"})
	public void LoginPersonalCenterByH5BeforNoYZT(String LoginPersonalCenterName,String login_name,String login_password,String login_HostName)
	{
		Log.logStep("个人中心用例1-2（插件为强登，用户非一账通登陆），模拟H5低门槛一账通登录 开始跑");
		Login.loginNoyztByHost(login_HostName);
		for(;;)
		{
			//通过死循环去判断刚才选择的账号是否还存在去判断是否登录成功
			if(appOperate.waitForText(5, login_HostName))
			{
				Sleep.sleep(10);
			}else
			{
				Log.logInfo("没有返回按钮");
				Sleep.sleep(1);
				break;
			}
		}		
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
			Login.loginyztByLow(login_name, login_password);
		}
		Log.logInfo("个人中心用例1-2（插件为强登，用户非一账通登陆），模拟H5低门槛一账通登录 案例成功跑完");
	
	}
	/**
	 *  个人中心用例1-3（插件为强登，用户一账通登陆），模拟宿主一账通登录
	 */
//	@Test(groups={"p1"})
	@Parameters({"LoginPersonalAferName","login_HostName"})
	public void LoginPersonalCenterByHostYZT(String LoginPersonalAferName,String login_HostName)
	{
		Log.logStep(" 个人中心用例1-3（插件为强登，用户一账通登陆），模拟宿主一账通登录 开始跑");
		Login.loginyztByHostgo(login_HostName);	
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalAferName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalAferName)), "点击个人中心");
			Sleep.sleep(5);
			if(appOperate.waitForText(20, "我的订单"))
			{
				Log.logInfo("个人中心用例1-3（插件为强登，用户一账通登陆），模拟宿主一账通登录 测试通过");
			}
		}
	}
	
	/**
	 * 个人中心用例1-4-1（插件为强登，用户已登陆），超时后点击
	 */
//	@Test(groups={"p1"})
	@Parameters({"LoginPersonalCenterName","login_name","login_password"})
	public void LoginYztTimeout(String LoginPersonalCenterName,String login_name,String login_password)
	{
		Log.logStep(" 个人中心用例1-4-1（插件为强登，用户已登陆），超时后点击 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
		}
		Login.loginyztByH5(login_name, login_password);
		appOperate.closeH5();
		appOperate.swipeToLeft();
		for (int i = 0; i < 70; i++) {
			
			appOperate.swipeToDown(1000);
			Sleep.sleep(10);
		}
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
			if(appOperate.waitForText(20, "一账通号/手机号/身份证号/邮箱"))
			 {
					Log.logInfo("个人中心用例1-4-1（插件为强登，用户已登陆），超时后点击 测试通过");
			 }
		}
	}
	
	/**
	 * 个人中心用例1-4-2（插件为强登，用户未登陆），超时后点击
	 */
//	@Test(groups={"p1"})
	@Parameters({"LoginPersonalCenterName"})
	public void LoginTimeout(String LoginPersonalCenterName)
	{
		Log.logStep(" 个人中心用例1-4-2（插件为强登，用户未登陆），超时后点击 开始跑");
		for (int i = 0; i < 70; i++) {
			
			appOperate.swipeToDown(1000);
			Sleep.sleep(10);
		}
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, LoginPersonalCenterName))
		{
			appOperate.click(driver.findElement(By.name(LoginPersonalCenterName)), "点击个人中心准备登录");
			Sleep.sleep(5);
			 if(appOperate.waitForText(20, "一账通号/手机号/身份证号/邮箱"))
			 {
					Log.logInfo("个人中心用例1-4-2（插件为强登，用户未登陆），超时后点击 测试通过");
			 }
		}
	}
	
	/**
	 * 个人中心用例1-5 插件不配置强登， 例如消息中心
	 */
//	@Test(groups={"p1"})
	@Parameters({"MsgCenter"})
	public void ClickMsgCenter(String MsgCenter)
	{
		Log.logStep("个人中心用例1-5 插件不配置强登， 例如消息中心 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(5, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 消息中心");
			Sleep.sleep(5);
		}
		while(true)
		{
			 if(appOperate.waitForText(20, "重要"))
			 {
					Log.logInfo("个人中心用例1-5 插件不配置强登， 例如消息中心 测试通过");
					break;
			 }
			 Sleep.sleep(5);
		}
	}
}
