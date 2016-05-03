package com.rym.libarary.testsuits;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class DownloadCenter_Test extends AutoTestBase{
	
//	@Test(groups={"p5"})
//	@Parameters({"LoginPersonalCenterName","login_name","login_password"})
	public void DownloadCenter()
	{
		appOperate.swipeToRight();
		Sleep.sleep(2);
		 Log.logInfo("页面元素如下："+driver.getPageSource());
	}
}
