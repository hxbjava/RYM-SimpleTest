package com.rym.libarary.testsuits;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.Elements_PersonalPage;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class MsgCenter_Test extends AutoTestBase{
	
	/**
	 * By huxuebing on 2016.4.5
	 * @param MsgCenter
	 */
	
	/**
	 * 1、无用户打开消息中心
	 * @param MsgCenter
	 */
//	@Test(groups={"p2"})
	@Parameters({"MsgCenter"})
	public void OpenMsgByleft(String MsgCenter)
	{
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
			}
		}
	}
	
	/**
	 * 2、一账通登陆后打开消息中心
	 * @param MsgCenter
	 * @param login_HostName
	 */
//	@Test(groups={"p2"})
	@Parameters({"MsgCenter","login_HostName"})
	public void OpenMsgByYZT(String MsgCenter,String login_HostName)
	{
		 Login.loginyztByHost(login_HostName,false);
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
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
			}
		}
	}
	
	/**
	 * 2、一账通登陆后通过左屏消息列表打开消息中心
	 * @param MsgCenter
	 * @param login_HostName
	 */
//	@Test(groups={"p2"})
	@Parameters({"MsgCenter","login_HostName"})
	public void OpenMsgByBlueList(String MsgCenter,String login_HostName)
	{
		 Login.loginyztByHost(login_HostName,false);
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
		if(appOperate.waitForText(10, "第 1 页（共 2 页）"))
		{
			appOperate.click(driver.findElement(By.xpath("//UIALink[@value='第 1 页（共 2 页）']")), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
			}
		}
	}
	
	/**
	 * 6、消息单条删除
	 * @param MsgCenter
	 */
//	@Test(groups={"p2"})
	@Parameters({"MsgCenter"})
	public void DeleteMsg(String MsgCenter)
	{
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要"))
			{
				Log.logInfo("已打开消息中心");
				DeleteMsg();
				Sleep.sleep(20);
			}
		}
	}
	
	public void DeleteMsg()
	{
		Sleep.sleep(2);
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		((AppiumDriver)driver).swipe(width*8/10, height*26/100, width*2/10, height*26/100, 1000);		
		Sleep.sleep(5);
		appOperate.click(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[6]")), "点击 删除消息");
	}
	
	/**
	 * 7、清空消息
	 * @param MsgCenter
	 */
	@Test(groups={"p2"})
	@Parameters({"MsgCenter"})
	public void ClearMsg(String MsgCenter)
	{
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要"))
			{
				Log.logInfo("已打开消息中心");
			
				if(appOperate.waitForText(10, "-未读"))
				{
					Log.logInfo("找到 －未读-点击未读");
					
					driver.findElement(By.xpath("//UIAStaticText[contains(@name,'未读')]")).click();

					//appOperate.click(driver.findElement(By.xpath("//UIAStaticText[contains(@name,'未读')]")), "点击 -未读");
				//	appOperate.click(driver.findElement(By.xpath("//UIAStaticText[@name='-未读']")), "点击 -未读");
					driver.findElement(By.xpath("//UIAStaticText[@name='全部']")).click();
		//			Sleep.sleep(5);
			//		appOperate.click(driver.findElement(By.xpath("//UIAStaticText[contains(@name,'未读')]")), "点击 -未读");
		//			appOperate.click(driver.findElement(By.xpath("//UIAStaticText[@name='全部']")), "点击 全部消息");
				}
				Sleep.sleep(5);
				Log.logInfo(driver.getPageSource());
				if(appOperate.waitForText(20, "清空"))
				{
					Log.logInfo("找到清空按钮");
					appOperate.click(driver.findElement(By.xpath("//UIAStaticText[@name='全部']")), "点击 清空消息");
					Sleep.sleep(20);
				}
			}
		}
	}
}
