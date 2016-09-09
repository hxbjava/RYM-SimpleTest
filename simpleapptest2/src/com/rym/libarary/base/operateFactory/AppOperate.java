package com.rym.libarary.base.operateFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rym.libarary.utils.Log;

public abstract class AppOperate {
	private static WebDriver driver;
	private List<WebElement> webElements;
	
	public AppOperate(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public abstract boolean swipeToLeft();
	
	public abstract boolean swipeToRight();
	
	public abstract void acceptAlert();
	
	public abstract Boolean IdentifyIsDisplay(String[] TargetText,WebElement element);
	
	public abstract void backToHomePage();
	
	public abstract void scrollToUp(String TargetText);
	
	public abstract void scrollToDown(String TargetText);
	
	public abstract void hideKeyboard();
	
	public abstract void closeH5();
	
	public abstract void checkH5();
	/**
	 * 模型轻触操作
	 * 
	 * @param x  x坐标
	 * @param y y坐标
	 */
	public void tapByCoordinate(double x,double y)
	{

	}
	
	/**
	 * 在制定时间内等待，直到文本出现在页面上
	 * 
	 * @param timeoutInSeconds 设置等待时间，单位：秒
	 * @param TargetText       等待出现的文本，可以设置多个。
	 * @return boolean
	 */
	public boolean waitForText(int timeoutInSeconds,String... TargetText)
	{
	//	Log.logStep("[Wait For Text : " + Arrays.toString(TargetText)+"] ");
		Log.logInfo("[Wait For Text : " + Arrays.toString(TargetText)+"] ");
		Boolean flag=false;
		String pageSource=null;
		//获取当前系统时间
		long currentTime=System.currentTimeMillis();
		while(true)
		{
			try {
				Thread.sleep(3*1000);
				if (driver !=null)
				{
					//获取页面资源
					pageSource=driver.getPageSource();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != pageSource)
			{
				//遍历页面资源查找需要的文本信息
				for (int i = 0; i < TargetText.length; i++) {
					flag=pageSource.contains(TargetText[i]);
				}
			}
			if (System.currentTimeMillis() -currentTime >=timeoutInSeconds * 1000 ||flag)
			{
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 设置对象查找超时时间
	 */
	public void setElementLocateTimeout(long time)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	/**
	 * 在制定的时间内判断制定的对象是否存在，兼容androiddriver&iosdriver。
	 * 
	 * @param seconds 指定时间
	 * @param By 指定对象
	 * @return boolean
	 */
	public boolean elementExists(int seconds,By... by)
	{
		boolean exists=false;
		setElementLocateTimeout(1);
		long start=System.currentTimeMillis();
		while(!exists&&((System.currentTimeMillis()-start)<=seconds*1000))
		{
			try {
				if(driver instanceof AndroidDriver)
				{
					exists=driver.findElements(by[0]).size()>0;
				}else
				{
					exists=driver.findElements(by[1]).size()>0;
				}
			} catch (NoSuchElementException e) {
				exists=false;
			}
		}
		setElementLocateTimeout(30);
		return exists;
	}
	
	/**
	 * 在制定的时间内判断制定的对象是否存在。
	 * 
	 * @param seconds 指定时间
	 * @param By 指定对象
	 * @return boolean
	 */
	public boolean elementExists(int seconds,By by)
	{
		boolean exists=false;
		setElementLocateTimeout(1);
		long start=System.currentTimeMillis();
		while(!exists&&((System.currentTimeMillis()-start)<=seconds *1000))
		{
			try {
				exists = driver.findElements(by).size() > 0;
			} catch (NoSuchElementException e) {
				exists=false;
			}
		}
		setElementLocateTimeout(30);
		return exists;
	}
	
	/**
	 * 模拟点击，在原声方法执行时添加日志
	 * 
	 */
	public void click(final WebElement element,String LogText)
	{
		//Log.logStep("["+LogText+"] ");
		Log.logInfo("[点击 "+element.toString().substring(element.toString().indexOf("->")));
		element.click();
	}
	
	/**
	 * 模拟输入，在原生方法执行时添加日志
	 */
	public void sendKeys(final WebElement element,String LogText,CharSequence... charSequences)
	{
		//Log.logStep("["+LogText+"] ");
		Log.logInfo("[输入字符 "+element.toString().substring(element.toString().indexOf("->")));
		element.sendKeys(charSequences);
	}
	
	/**
	 * 模拟清除，在原生方法执行时添加日志
	 */
	public void clear(final WebElement element,String LogText)
	{
	//	Log.logStep("["+LogText+"] ");
		Log.logInfo("[清空数据 "+element.toString().substring(element.toString().indexOf("->")));
		element.clear();
	}	
	
	/**
	 * 获取文本，在原生方法执行时添加日志
	 */
	public String getText(final WebElement element,String LogText)
	{
		//	Log.logStep("["+LogText+"] ");
		Log.logInfo("[获取文本 "+element.toString().substring(element.toString().indexOf("->")));
		return element.getText();
	}
	
	/**
	 * 模拟滚到至目标文本位置
	 */
	public void scorllTo(String TargetText)
	{
		//	Log.logStep("[滑动页面以发现 '"+TargetText+"'] ");
		Log.logInfo("[滑动页面以发现 '"+TargetText+"'] ");
	//	((AppiumDriver)driver).scrollTo(TargetText);
	
	}
	
	/**
	 * 模拟屏幕向下滑动
	 */
	public void swipeToDown(int during)
	{
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		Log.logInfo("［向下滑动］");
		((AppiumDriver)driver).swipe(width/2, height/6, width/2, height*3/4, during);
	}
	
	/**
	 * 模拟滑动
	 */
	public void swipe(int startWidth,int startHeight,int endWidth,int endHeight,int during)
	{
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		Log.logInfo("［滑动］");
		startWidth=8/10;
		Log.logInfo(startWidth+"-"+height*startHeight);
		((AppiumDriver)driver).swipe(width*startWidth, height*startHeight, width*endWidth, height*endHeight, during);
	}
	
	/**
	 * 模拟屏幕向上滑动
	 */
	public void swipeToUp(int during)
	{
		int width=((AppiumDriver)driver).manage().window().getSize().width;
		int height=((AppiumDriver)driver).manage().window().getSize().height;
		Log.logInfo("［向上滑动］");
		((AppiumDriver)driver).swipe(width/2, height*3/4, width/2, height/6, during);
	}
	
	/**
	 * 在指定时间内等待，直到对象能够被点击
	 */
	public boolean waitForElementClickable(By by,long timeOutInSeconds,long sleepInMillis)
	{
		try {
			setElementLocateTimeout(timeOutInSeconds);
			WebDriverWait wait = new WebDriverWait(driver,
					timeOutInSeconds - 5, sleepInMillis);
			return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
		} finally {
			setElementLocateTimeout(timeOutInSeconds);
		}
	}
	
	/**
	 * 在指定时间内等待，直到对象能够被点击，然后点击对象
	 */
	public boolean clickClickableElement(By androidBy,By iosBy,WebElement element,long timeOutInSeconds,
			long sleepInMillis,String... desc)
	{
		boolean clickable=false;
		try
		{
			if (driver instanceof AndroidDriver)
			{
				clickable=waitForElementClickable(androidBy, timeOutInSeconds, sleepInMillis);
			}else
			{
				clickable=waitForElementClickable(iosBy, timeOutInSeconds, sleepInMillis);
			}
			if(clickable)
			{		
				if(null==desc)
				{
					element.click();
				}else
				{
					click(element, desc[0]);
				}
			}
		}catch(Exception e)
		{
			Log.logError(e.getStackTrace());
			e.printStackTrace();
		}finally
		{
			return clickable;
		}
	}
	
	public void clickchangeModelButton()
	{
		Log.logInfo("切换插件模式");
		driver.findElement(By.name("exchangeButton")).click();
	}
	
}
