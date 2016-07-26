package com.rym.libarary.business;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.rym.libarary.base.PageObjectBase;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Elements_StartPage_Host extends PageObjectBase{
	
	public Elements_StartPage_Host(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 选择appid
	 */
	@AndroidFindBy(name="SDK")
	@iOSFindBy(name="AppId")
	public WebElement HostAppIDSelect;
	
	/**
	 * 选定appid
	 */
	@AndroidFindBy(name="E企赢")
	@iOSFindBy(name="养老险E企赢")
	public WebElement HostAppIDclick;
	
	/**
	 * 进入页面
	 */
	@AndroidFindBy(name="loading")
	@iOSFindBy(name="进入页面")
	public WebElement HostStartClick;
	
}
