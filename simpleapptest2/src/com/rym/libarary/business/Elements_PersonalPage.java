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
	@AndroidFindBy(id="com.paic.example.simpleapp:id/user-id-input")
//	@AndroidFindBy(accessibilityId="")
	@iOSFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
	public WebElement ClickAccount;
	
	/**
	 * 点击密码准备输入
	 */
	@AndroidFindBy(id="com.paic.example.simpleapp:id/user-psd-input")
	@iOSFindBy(xpath ="//*[@value='密码']")
	public WebElement ClickPassword;
	
	@AndroidFindBy(id="登 录 Link")
	@iOSFindBy(xpath = "//UIALink[@name='登 录']")
	public WebElement Clicklogin;
	
	/**
	 * 模拟宿主登录是选择账号
	 */
	
	@AndroidFindBy(id="rymtest001")
	@iOSFindBy(id="选择登陆用户")
	public WebElement ClickHostname;
	
	/**
	 * 模拟宿主登陆时点击登陆按钮
	 */
	@AndroidFindBy(id="登录")
	@iOSFindBy(id="登陆")
	public WebElement ClickHostLogin;
	
	/**
	 * 模拟宿主登陆
	 */
	@AndroidFindBy(id="宿主登录")
	@iOSFindBy(id="宿主登陆")
	public WebElement ClickByHost;
	
	/**
	 * 密码登录按钮
	 */
	@AndroidFindBy(id="密码登录 Link")
	@iOSFindBy(xpath = "//UIALink[@name='密码登录']")
	public WebElement ClickPwdLogin;
	
	
	/**
	 * 点击账号准备输入
	 */
//	@AndroidFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
//	@iOSFindBy(xpath ="//*[@value='一账通号/手机号/身份证号/邮箱']")
	@AndroidFindBy(id="user-id-input")
	@iOSFindBy(xpath ="//*[@value='请输入您的账号']")
	public WebElement ClickAccountLow;
	
	/**
	 * 点击账号准备输入
	 */
	@AndroidFindBy(id="user-psd-input")
	@iOSFindBy(xpath ="//*[@value='请输入您的密码']")
	public WebElement ClickPasswordLow;
	
	@AndroidFindBy(id="登 录 Link")
	@iOSFindBy(xpath = "//UIALink[@name='登 录']")
	public WebElement ClickloginLow;
}