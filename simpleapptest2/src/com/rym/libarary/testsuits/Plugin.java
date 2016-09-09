package com.rym.libarary.testsuits;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class Plugin extends AutoTestBase{

	@Test(groups = { "p4" })
	@Parameters({"login_HostName"})
	public void OpenPlugin(String login_HostName) {
//		int width=((AppiumDriver)driver).manage().window().getSize().width;
//		int height=((AppiumDriver)driver).manage().window().getSize().height;
//		Log.logInfo(width+"--"+height);
//		Login.loginyztByHost(login_HostName, false);
//		Sleep.sleep(5);
//		appOperate.click(driver.findElement(By.name("PA02100000000_02_FGZ")),"点击 " );
//		Sleep.sleep(30);
//		if (appOperate.waitForText(15,"同康里")) {
//			Log.logInfo("111");
//		}
//		appOperate.swipeToRight();
//		Sleep.sleep(5);
//		appOperate.click(driver.findElement(By.name("个人中心")), "个人中心");
//		Sleep.sleep(30);
////		WebElement e=driver.findElement(By.xpath("//*[@value='一账通号/手机号/身份证号/邮箱']"));
////		e.sendKeys("张三");
//		List<WebElement> list = driver.findElements(By
//				.className("android.widget.EditText"));
//		appOperate.click(list.get(0), "点击 账号 进行输入");
//		appOperate.sendKeys(list.get(0), "输入 账号", "张三");
//		Sleep.sleep(30);
		//driver.findElement(By.id("进入页面")).click();
		Sleep.sleep(30);
	}
}
