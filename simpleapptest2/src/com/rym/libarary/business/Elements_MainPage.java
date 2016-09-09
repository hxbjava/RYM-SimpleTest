package com.rym.libarary.business;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rym.libarary.base.PageObjectBase;

public class Elements_MainPage extends PageObjectBase{

	public Elements_MainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 一账通
	 */
	@AndroidFindBy(id="com.paic.example.simpleapp:id/rb_yzt")
	@iOSFindBy(id ="一账通")
	public WebElement ClickYzt;
	
	/**
	 * 非一账通
	 */
	@AndroidFindBy(id="com.paic.example.simpleapp:id/rb_no_yzt")
	@iOSFindBy(id ="非一账通")
	public WebElement ClickNoYzt;
	
	/**
	 * 宿主登陆
	 */
	@AndroidFindBy(id="com.paic.example.simpleapp:id/btn_login")
	@iOSFindBy(id ="宿主登陆")
	public WebElement ClickSZLogin;
	
	/**
	 * 模拟宿主登录默认账号
	 */
	@AndroidFindBy(xpath="//android.widget.TextView[@text='rymtest001']")
	@iOSFindBy(id ="选择登陆用户")
	public WebElement ClickLoginMRZH;
	
	/**
	 * 模拟宿主登录账号
	 */
	 String aa="15006180031";
//	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='"+aa+"']")
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='rymtest001']")
	@iOSFindBy(id ="宿主登录")
	public WebElement ClickLoginZH;
	
	/**
	 * 模拟宿主登录按钮
	 */
	@AndroidFindBy(xpath="//android.widget.Button[@text='登录']")
	@iOSFindBy(id ="登陆")
	public WebElement ClickLoginByhost;
	
	/**
	 * 切换插件模式
	 */
	@AndroidFindBy(id ="exchangeButton" )
	@iOSFindBy(id ="exchangeButton")
	public WebElement ClickExchangeButton;
	
	/**
	 * 切换插件模式
	 */
	@AndroidFindBy(xpath="//android.widget.TextView[@text='个人中心']" )
	@iOSFindBy(id ="个人中心")
	public WebElement LoginPersonalCenterName;
	
	/**
	 * 切换插件模式
	 */
	@AndroidFindBy(xpath="//android.widget.TextView[@text='消息中心']" )
	@iOSFindBy(id ="消息中心")
	public WebElement MsgCenter;
}
