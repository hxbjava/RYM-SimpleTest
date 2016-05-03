package com.rym.libarary.business;



import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rym.libarary.base.PageObjectBase;

public class Elements_PersonalPage extends PageObjectBase{

	public Elements_PersonalPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 点击账号准备输入
	 */
//	@AndroidFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
//	@iOSFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
	@FindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
	public WebElement ClickAccount;
	
	/**
	 * 点击账号准备输入
	 */
	@AndroidFindBy(xpath ="//*[@value='密码']")
	@iOSFindBy(xpath ="//*[@value='密码']")
	public WebElement ClickPassword;
	@iOSFindBy(xpath = "//UIALink[@name='登 录']")
	public WebElement Clicklogin;
	
	/**
	 * 模拟宿主登录是选择账号
	 */
	@AndroidFindBy(name="rymtest001")
	@iOSFindBy(name="选择登陆用户")
	public WebElement ClickHostname;
	
	/**
	 * 模拟宿主登陆时点击登陆按钮
	 */
	@AndroidFindBy(name="登录")
	@iOSFindBy(name="登陆")
	public WebElement ClickHostLogin;
	
	/**
	 * 模拟宿主登陆
	 */
	@AndroidFindBy(name="宿主登录")
	@iOSFindBy(name="宿主登陆")
	public WebElement ClickByHost;
	
	/**
	 * 点击账号准备输入
	 */
//	@AndroidFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
//	@iOSFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
	@FindBy(xpath ="//*[@value='请输入您的账号']")
	public WebElement ClickAccountLow;
	
	/**
	 * 点击账号准备输入
	 */
	@AndroidFindBy(xpath ="//*[@value='请输入您的密码']")
	@iOSFindBy(xpath ="//*[@value='请输入您的密码']")
	public WebElement ClickPasswordLow;
	
	@iOSFindBy(xpath = "//UIALink[@name='登 录']")
	public WebElement ClickloginLow;
}