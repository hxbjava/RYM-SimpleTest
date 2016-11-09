package com.rym.libarary.KeywordFrame;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.utils.Log;
import com.rym.libarary.utils.Sleep;

public class ReadApi extends AutoTestBase{

	public static void readApi(List<String> list)
	{

		if(list.get(4)==null||(platformName.toLowerCase().contains("ios")&&list.get(4).equals("ios"))||
				(platformName.toLowerCase().contains("android")&&list.get(4).equals("android"))){
			readApiList(list);
		}else
		{
			if(list.get(4).equals("ios")){Log.logInfo("该方法仅限ios执行");}
			if(list.get(4).equals("android")){Log.logInfo("该方法仅限android执行");}
		}
	}
	
	/**
	 *   list信息说明
	 *0. CaseSuite（案例）1. CaseID(步骤id)	2. Description（步骤描述）	3. Action_Keyword(操作)	
	 *4. ios or android(ios或android独有操作7)	 5. Element（元素封装）	6. PageObject(页面元素)	7. Parameter（传入参数）
	 */
	public static void readApiList(List<String> list)
	{

			switch (list.get(3)) {
			case "swipeToRight":
				appOperate.swipeToRight();
				Sleep.sleep(1);
				break;
			case "swipeToLeft":
				appOperate.swipeToLeft();
				Sleep.sleep(1);
				break;
			case "closeH5":
				appOperate.closeH5();
				Sleep.sleep(1);
				break;
			case "checkH5":
				appOperate.checkH5();
				Sleep.sleep(1);
				break;
			case "waitForText":
				Boolean b=appOperate.waitForText(Integer.parseInt(list.get(7)),list.get(6));
				Log.logInfo("断言结果:"+b);
				Assert.assertTrue(b);
				break;
			case "assertFalse":
				Boolean c=appOperate.waitForText(Integer.parseInt(list.get(7)),list.get(6));
				Log.logInfo("断言消失，结果为false正常，结果为:"+c);
				Assert.assertFalse(c);
				break;
			case "click":
				appOperate.click(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),"点击"+FindWebelement.readText(list.get(5)));
				break;
			case "sleep":
				Sleep.sleep(Integer.parseInt(list.get(7)));
				break;
			case "公共库":
				Log.logInfo("执行"+list.get(7));
				ForTest.CallCase(list.get(7));
				break;
			case "hideKeyboard":
				if (platformName.toLowerCase().contains("ios")) {
					appOperate.hideKeyboard();
				}
				Sleep.sleep(1);
				break;
			case "scrollToUp":
				appOperate.scrollToUp(list.get(7));
				Sleep.sleep(1);
				break;
			case "scrollToDown":
				appOperate.scrollToDown(list.get(7));
				Sleep.sleep(1);
				break;
			case "swipeToDown":
				appOperate.swipeToDown(Integer.parseInt(list.get(7)));
				Sleep.sleep(1);
				break;
			case "swipeToUp":
				appOperate.swipeToUp(Integer.parseInt(list.get(7)));
				Sleep.sleep(1);
				break;
			case "clickchangeModelButton":
				appOperate.clickchangeModelButton();
				Sleep.sleep(1);
				break;
			case "clear":
				appOperate.click(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),
						"点击"+FindWebelement.readText(list.get(5)));
				appOperate.clear(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),
						"点击"+FindWebelement.readText(list.get(5)));
				Sleep.sleep(1);
				break;
			case "sendKeys":
				appOperate.click(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),
						"点击"+FindWebelement.readText(list.get(5)));
				appOperate.sendKeys(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),
						"在对象"+FindWebelement.readText(list.get(5))+"输入字符",list.get(7));
				Sleep.sleep(1);
				break;
			case "swipe":
				//分割字符串
				String[] sourceStr=list.get(6).split(",");
				appOperate.swipe(Integer.parseInt(sourceStr[0]), Integer.parseInt(sourceStr[1]), 
						Integer.parseInt(sourceStr[2]), Integer.parseInt(sourceStr[3]), Integer.parseInt(sourceStr[4]));
				break;
			case "getText":
				appOperate.getText(FindWebelement.readWebElement(FindWebelement.readText(list.get(5))),
						"获取文本"+FindWebelement.readText(list.get(5)));
				break;
			}
	}
	
//	public static WebElement readWebElement(List<String> list)
//	{
//		WebElement e = null;
//		String findType;
//		String findText;
//		if (platformName.toLowerCase().contains("android"))
//		{
//			findType=list.get(1);
//			findText=list.get(2);
//		}else
//		{
//			findType=list.get(3);
//			findText=list.get(4);
//		}
//		Log.logInfo("查找方式:"+findType+"  查找内容:"+findText);
//		switch (findType) {
//		case "id":
//			e=driver.findElement(By.id(findText));
//			break;
//		case "xpath":
//			e=driver.findElement(By.xpath(findText));
//			break;
//		}
//		return e;
//	}
//	
//	public static List<String> readText(String text)
//	{
//		List<String> list= null ;
//		List<List<String>> list3=ReadExcel2.readXls("/Users/rymtest/Desktop/关键字驱动2.xls",1);
//		for (List<String> list4 : list3) {
//			if (list4.get(0).toString().equals(text)) {
//				list=list4;
//				break;
//			}
//		}
//		return list;
//	}
	
}
