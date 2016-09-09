package com.rym.libarary.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.operateFactory.*;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;
import com.sun.jna.platform.win32.ShellAPI.APPBARDATA;
public class AutoTestBase {
	public static WebDriver driver;
	public String port;
	public static int timeout;
	public static String platformName;
	public static String udid;
	public static AppOperate appOperate;
	public static String date;
	public static String time;
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	/**
	 * 测试套件运行前准备:
	 * @param filePath        app安装包路径
	 * @param appName         app安装包名
	 * @param platformName    app平台 android/ios
	 * @param platformVersion app运行平台版本
	 * @param deviceName      app设备名
	 * @param appPackage      app包名,如com.pingan.rym
	 * @param appActivity     android参数：例如:com.pingan.yzt.SplashActivityPro
	 * @param port            app的服务端口
	 * @param udid            app的设备识别符
	 * @param timeout         app等待超时的时间，单位：秒
	 * @param remote_url      web的远程运行url，例如:http://172.1.2.3:8888/wd/hub
	 * @throws MalformedURLException 
	 */
	@BeforeTest(alwaysRun=true)
	@Parameters({"filePath","appName","platformName","platformVersion","deviceName","appPackage","appActivity",
		"port","udid","timeout","remote_url"})
	public void beforeSuite(String filePath,String appName,String platformName,String platformVersion,String deviceName,
			String appPackage,String appActivity,String port,String udid,int timeout,String remote_url) throws MalformedURLException
	{
		this.port=port;
		this.timeout=timeout;
		AutoTestBase.platformName=platformName;
		AutoTestBase.udid=udid;
		
		if(platformName.toLowerCase().contains("android")||platformName.toLowerCase().contains("ios"))
		{
			File appDir=new File(filePath);
			File app=new File(appDir,appName);
			
			System.setProperty("deviceID", udid);
			
			DesiredCapabilities capabilities =new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.0");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);;
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.UDID,udid);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
			capabilities.setCapability("appPackage",appPackage);
			capabilities.setCapability("unicodeKeyboard", "True");
            capabilities.setCapability("resetKeyboard", "True");
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability("autoAcceptAlerts", "True");
//            capabilities.setCapability("autoLaunch", "False");
           
            if(platformName.toLowerCase().contains("android"))
            {
            	 capabilities.setCapability("noReset", "True");
 //                capabilities.setCapability("autoWebview", "True");
            	driver=new AndroidDriver(new URL("http://0.0.0.0:"+port+"/wd/hub"), capabilities);
       
            	appOperate=new AndroidOperate((AndroidDriver)driver);
            }else
            {
            	driver = new IOSDriver(new URL("http://0.0.0.0:"+port+"/wd/hub"), capabilities);
            	appOperate=new IosOperate((IOSDriver)driver);
            }
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		}
//		((AppiumDriver)driver).launchApp();
		Login.startApp();
		Sleep.sleep(10);
	}

	 
	 /**
	  * 测试套件执行后关闭driver
	  */
//	@AfterSuite(alwaysRun=true)
	public void afterSuite()
	{
//		if (platformName.toLowerCase().contains("android") || platformName.toLowerCase().contains("ios")) {
//            ((AppiumDriver) driver).removeApp("com.pingan.rympush");
//		}
		Sleep.sleep(3);
			if (platformName.toLowerCase().contains("android"))
			{
				((AppiumDriver) driver).removeApp("com.paic.example.simpleapp");
			}else
			{
				((AppiumDriver) driver).removeApp("com.pingan.rympush");
			}

		Sleep.sleep(3);
		driver.quit();
	}
	
	/**
	 * 每个测试案例执行完毕后杀掉app在启动，方便恢复数据
	 * @param appID
	 */
	@AfterMethod(alwaysRun=true)
	public void afterTest()
	{
		
//	appOperate.backToHomePage();
	//	driver.quit();
		((AppiumDriver)driver).closeApp();
		Sleep.sleep(3);
		((AppiumDriver)driver).launchApp();
		Login.startApp();
		Sleep.sleep(3);
	}
	
	 /**
     * Description: Screen Shot.
     * 实现屏幕截屏功能。
     *
     * @param fileName 截屏文件名
     * @throws IOException IO异常
     */
	public static String ScreenShot(String fileName) throws IOException
	{
		String filePath_screenShots;
		String filePath_testngReports="output"+File.separator+date+File.separator+time
			+File.separator+udid+File.separator+"testngReports"+File.separator;
		 filePath_screenShots = "output" + File.separator + date + File.separator + time
				 + File.separator + udid + File.separator + "screenShots" + File.separator;
		FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File
				(filePath_screenShots+fileName+".png"));
		 return filePath_testngReports;
	}
	
	 public void sleep(long time)
	 {
	   try {
			Thread.sleep(time);
	   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
