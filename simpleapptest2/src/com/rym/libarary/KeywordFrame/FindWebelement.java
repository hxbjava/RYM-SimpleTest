package com.rym.libarary.KeywordFrame;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rym.libarary.base.AutoTestBase;
import com.rym.libarary.utils.Log;

public class FindWebelement extends AutoTestBase{

	//通过文本的元素内容查找元素
	public static WebElement readWebElement(List<String> list)
	{
		WebElement e = null;
		String findType;
		String findText;
		if (platformName.toLowerCase().contains("android"))
		{
			findType=list.get(1);
			findText=list.get(2);
		}else
		{
			findType=list.get(3);
			findText=list.get(4);
		}
		Log.logInfo("查找方式:"+findType+"  查找内容:"+findText);
		switch (findType) {
		case "id":
			e=driver.findElement(By.id(findText));
			break;
		case "xpath":
			e=driver.findElement(By.xpath(findText));
			break;
		case "linkText":
			e=driver.findElement(By.linkText(findText));
			break;
		case "findElementsByclassName":
			//findText 例如: android.widget.EditText,1
			String[] sourceStr=findText.split(",");
			List<WebElement> listss = driver.findElements(By
					.className(sourceStr[0]));
			e=listss.get(Integer.parseInt(sourceStr[1]));
		}
		return e;
	}
	
	//读取表格（元素表）里相对应的元素内容
	public static List<String> readText(String text)
	{
		List<String> list= null ;
		List<List<String>> list3=ReadExcel2.readXls(excelPath,1);
		for (List<String> list4 : list3) {
			if (list4.get(0).toString().equals(text)) {
				list=list4;
				break;
			}
		}
		return list;
	}
}
