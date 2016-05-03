package com.rym.libarary.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageObjectBase {
	
	/**
	 *   http://www.cnblogs.com/cosyman/p/page-objects.html
	 * @param driver
	 */
	
	public PageObjectBase(WebDriver driver)
	{
		if(driver.getClass().getSimpleName().toLowerCase().contains("android")||
				driver.getClass().getSimpleName().toLowerCase().contains("ios"))
		{
			PageFactory.initElements(new AppiumFieldDecorator(driver,6,TimeUnit.SECONDS),this);
		}
		else
		{
			PageFactory.initElements(driver, this);
		}
	}
}
