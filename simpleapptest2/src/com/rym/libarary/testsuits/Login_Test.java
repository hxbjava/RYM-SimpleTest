package com.rym.libarary.testsuits;


import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.business.GetPluginID;
import com.rym.libarary.business.Login;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class Login_Test extends AutoTestBase{
		
		
//	@Test(groups={"p1"})
	public void atest()
	{
		GetPluginID.GetPluginList("https://maam-dmzstg2.pingan.com.cn:9041/maam/getPluginList.do?sdkVersion=3.1.0.392&osVersion=7.1.2&deviceId=2abb53d0f829972ba905cca44662d89f1fd4fcda&appId=PA01100000000_01_SDK&appVersion=1.0&deviceType=ios");
	}
	
	
//	@BeforeTest(alwaysRun=true)
	@Parameters({"appID"})
	public void strat(String appID)
	{
		Login.startApp(appID);
		Sleep.sleep(3);
	}




	
	
	
}
