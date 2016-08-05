package com.rym.libarary.testsuits;

import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
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
	@Test(groups={"p2"})
	@Parameters({"MsgCenter"})
	public void OpenMsgByleft(String MsgCenter)
	{
		Log.logStep(" 消息中心用例1-1 无用户打开消息中心 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要")||appOperate.waitForText(20, "重要-未读"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
				Log.logInfo(" 消息中心用例1-1 无用户打开消息中心 成功跑完");
			}
		}
	}
	
	/**
	 * 2、一账通登陆后打开消息中心
	 * @param MsgCenter
	 * @param login_HostName
	 */
	@Test(groups={"p2"})
	@Parameters({"MsgCenter","login_HostName"})
	public void OpenMsgByYZT(String MsgCenter,String login_HostName)
	{
		Log.logStep(" 消息中心用例1-2-1一账通登陆后打开消息中心 开始跑");
		 Login.loginyztByHost(login_HostName,false);	
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要")||appOperate.waitForText(20, "重要-未读"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
				Log.logInfo(" 消息中心用例1-2-1一账通登陆后打开消息中心 成功跑完");
			}
		}
	}
	
	/**
	 * 2、一账通登陆后通过左屏消息列表打开消息中心
	 * @param MsgCenter
	 * @param login_HostName
	 */
	@Test(groups={"p2"})
	@Parameters({"MsgCenter","login_HostName"})
	public void OpenMsgByBlueList(String MsgCenter,String login_HostName)
	{
		Log.logStep(" 消息中心用例1-2-2 一账通登陆后通过左屏消息列表打开消息中心 开始跑");
		 Login.loginyztByHost(login_HostName,false);
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, "第 1 页（共 2 页）"))
		{
			appOperate.click(driver.findElement(By.xpath("//UIALink[@value='第 1 页（共 2 页）']")), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要")||appOperate.waitForText(20, "重要-未读"))
			{
				Log.logInfo("已打开消息中心");
				appOperate.closeH5();
				Log.logInfo(" 消息中心用例1-2-2 一账通登陆后通过左屏消息列表打开消息中心 成功跑完");
			}
		}
	}
	
	/**
	 * 6、消息单条删除
	 * @param MsgCenter
	 */
	@Test(groups={"p2"})
	@Parameters({"MsgCenter","login_HostName"})
	public void DeleteMsg(String MsgCenter,String login_HostName)
	{
		if (platformName.toLowerCase().contains("android")) {
			Login.loginyztByHost(login_HostName, false);
		}
		Log.logStep(" 消息中心用例1-6 删除单条消息 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要")||appOperate.waitForText(20, "重要-未读"))
			{
				Log.logInfo("已打开消息中心");
				if (platformName.toLowerCase().contains("android")) {
					while(true){
					if(appOperate.waitForText(10, "其他-未读"))
					{
					break;
					}else
					{
						appOperate.click(driver.findElement(By.name("其他")), "点击 打开消息中心");
						Sleep.sleep(3);
						}
					}
				}
				DeleteMsg();
				Sleep.sleep(20);
				Log.logInfo(" 消息中心用例1-6 删除单条消息 成功跑完");
			}
		}
	}
	
	public void DeleteMsg()
	{
		Log.logInfo("进入删除");
		Sleep.sleep(2);
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;	
		Sleep.sleep(5);
		if (platformName.toLowerCase().contains("android")) {
			((AppiumDriver)driver).swipe(width*8/10, height*3/10, width*2/10, height*3/10, 1000);		
			Sleep.sleep(5);
			((AppiumDriver)driver).tap(1,width*8/10 , height*35/100, 1000);
			Sleep.sleep(2);
		}else{
			((AppiumDriver)driver).swipe(width*8/10, height*26/100, width*2/10, height*26/100, 1000);		
			Sleep.sleep(5);
		appOperate.click(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAStaticText[6]")), "点击 删除消息");
	
		}
	}
	
	/**
	 * 7、清空消息
	 * @param MsgCenter
	 */
//	@Test(groups={"p2"})
	@Parameters({"MsgCenter"})
	public void ClearMsg(String MsgCenter)
	{
		Log.logStep(" 消息中心用例1-7 清空消息 开始跑");
		appOperate.swipeToRight();
		if(appOperate.waitForText(10, MsgCenter))
		{
			appOperate.click(driver.findElement(By.name(MsgCenter)), "点击 打开消息中心");
			if(appOperate.waitForText(20, "重要")||appOperate.waitForText(20, "重要-未读"))
			{
				Log.logInfo("已打开消息中心");
			
				if(appOperate.waitForText(10, "-未读"))
				{
					Log.logInfo("找到 －未读-点击未读");
					
				 driver.findElement(By.name("-未读")).click();
					driver.findElement(By.xpath("//UIAStaticText[@name='全部']")).click();
				}
				Sleep.sleep(5);
				if(appOperate.waitForText(20, "清空"))
				{
					Log.logInfo("找到清空按钮");
					appOperate.click(driver.findElement(By.xpath("//UIAStaticText[@name='全部']")), "点击 清空消息");
					Sleep.sleep(20);
					Log.logInfo(" 消息中心用例1-7 清空消息 成功跑完");
				}
			}
		}
	}
}
